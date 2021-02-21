package com.lisichenko.db;

import com.lisichenko.entities.Account;
import com.lisichenko.entities.Order;
import com.lisichenko.entities.Room;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/***
 *
 * Data Base Manager is class for manipulations with data base.
 *
 */
public class DBManager {
    private static final String PRICE = "price";
    private static final String TYPE = "type";
    private static final String PLACES = "places";
    private static final String ROOMS_ID = "rooms.id";
    private static final String ID = "id";
    private static final String ROOM_ID = "room_id";
    private static final String STATUS = "status";
    private static final String ROOM_TYPE_ID = "room_type_id";
    private static final String ORDERS_ID = "orders.id";
    private static final String DAYS = "days";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String ROLE_ID = "role_id";
    private static final String EMAIL = "email";
    private static final String CREATE_TIME = "create_time";
    private static final String TOTAL_PRICE = "total_price";
    private static final int ORDER_NEW = 1;
    private static final int ORDER_ACCEPT_BY_ADMIN = 2;
    private static final int ORDER_PAID = 3;
    private static final int ORDER_TIME_IS_OVER = 4;
    private static final int ROOM_FREE = 1;
    private static final int ROOM_BOOKED = 2;
    private static final int ROOM_OCCUPIED = 3;
    private static final String url = "jdbc:mysql://localhost/my_hotel?serverTimezone=" + TimeZone.getDefault().getID();
    private static final Logger LOG = Logger.getLogger(DBManager.class);
    private static DBManager dbManager;

    public static DBManager getInstance() {
        if (dbManager == null) {
            try {
                dbManager = new DBManager();
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                LOG.error(e);
                throw new RuntimeException(e);
            }
        }
        return dbManager;
    }

    public void createAccount(String name, String email, String password) {
        String insert = "insert into accounts (name, email, password) value (?,?,?);";
        try (Connection connection = getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    public List<Room> getAllRooms(int limit, int offset, String sort) {
        String selectRooms = "select * from rooms join room_types on rooms.room_type_id = room_types.id " +
                "join room_statuses on rooms.room_status_id = room_statuses.id order by " + sort + " limit ? offset ?;";
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectRooms)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt(ROOMS_ID));
                room.setPrice(resultSet.getBigDecimal(PRICE));
                room.setPlaces(resultSet.getInt(PLACES));
                room.setStatus(resultSet.getString(STATUS));
                room.setType(resultSet.getInt(ROOM_TYPE_ID));
                rooms.add(room);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return rooms;
    }

    public List<Order> getOrdersForAdmin() {
        List<Order> orders = new ArrayList<>();
        String sqlQuerySelect = "select  * from orders join order_statuses on orders.order_status_id = order_statuses.id " +
                "join room_types on orders.room_type_id = room_types.id join accounts on orders.account_id = accounts.id order by order_status_id;";
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuerySelect)) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(ORDERS_ID));
                order.setPlaces(resultSet.getInt(PLACES));
                order.setDays(resultSet.getInt(DAYS));
                order.setType(resultSet.getString(TYPE));
                order.setAccountEmail(resultSet.getString(EMAIL));
                order.setOrderStatus(resultSet.getString(STATUS));
                order.setRoomId(resultSet.getInt(ROOM_ID));
                order.setCreateTime(resultSet.getString(CREATE_TIME));
                order.setTotalPrice(resultSet.getBigDecimal(TOTAL_PRICE));
                orders.add(order);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return orders;
    }

    public Account getAccount(String email) {
        String getAccountQuery = "select * from accounts where email=?;";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(getAccountQuery)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setCreateTime(resultSet.getString(CREATE_TIME));
                account.setEmail(resultSet.getString(EMAIL));
                account.setId(resultSet.getInt(ID));
                account.setPassword(resultSet.getString(PASSWORD));
                account.setName(resultSet.getString(NAME));
                account.setRoleId(resultSet.getInt(ROLE_ID));
                resultSet.close();
                return account;
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    public void createRawOrder(int accountId, int days, int places, int typeOfRoom) {
        String insertNewOrder = "insert into orders (places, days, room_type_id ,account_id, order_status_id) values (?,?,?,?,?);";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertNewOrder)) {
            preparedStatement.setInt(1, places);
            preparedStatement.setInt(2, days);
            preparedStatement.setInt(3, typeOfRoom);
            preparedStatement.setInt(4, accountId);
            preparedStatement.setInt(5, ORDER_NEW);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    public void createSpecificRoomOrder(int idAccount, int idRoom, int days) {
        Connection connection = getConnection();
        String sqlQueryInsert = "insert into orders (account_id, room_id, days,order_status_id,places, room_type_id, total_price) values (?,?,?,?,?,?,?);";
        String sqlQueryUpdate = "update rooms set room_status_id=? where id=?;";
        try (PreparedStatement firstPreparedStatement = connection.prepareStatement(sqlQueryInsert);
             PreparedStatement secondPreparedStatement = connection.prepareStatement(sqlQueryUpdate)) {
            connection.setAutoCommit(false);
            Room room = getRoom(idRoom, connection);
            firstPreparedStatement.setInt(1, idAccount);
            firstPreparedStatement.setInt(2, idRoom);
            firstPreparedStatement.setInt(3, days);
            firstPreparedStatement.setInt(4, ORDER_NEW);
            firstPreparedStatement.setInt(5, room.getPlaces());
            firstPreparedStatement.setInt(6, room.getType());
            BigDecimal totalPrice = room.getPrice().multiply(BigDecimal.valueOf(days));
            firstPreparedStatement.setBigDecimal(7, totalPrice);
            firstPreparedStatement.execute();

            secondPreparedStatement.setInt(1, ROOM_BOOKED);
            secondPreparedStatement.setInt(2, idRoom);
            secondPreparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                LOG.error(throwable);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e);
            }
        }
    }

    public void updateOrderByAdmin(int orderId) {
        String sqlQueryUpdate = "update orders set order_status_id=? where id=?";
        changeStatusesRoomAndOrder(orderId, sqlQueryUpdate, ORDER_ACCEPT_BY_ADMIN, ROOM_BOOKED);
    }

    public List<Order> getOrdersForCustomer(int accountId) {
        List<Order> list = new ArrayList<>();
        String sql = "select * from orders join order_statuses os on os.id = orders.order_status_id " +
                "join room_types rt on orders.room_type_id = rt.id join accounts a on a.id = orders.account_id where account_id=? order by order_status_id;";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setRoomId(resultSet.getInt("ROOM_ID"));
                order.setDays(resultSet.getInt(DAYS));
                order.setId(resultSet.getInt(ORDERS_ID));
                order.setType(resultSet.getString(TYPE));
                order.setPlaces(resultSet.getInt(PLACES));
                order.setTotalPrice(resultSet.getBigDecimal("total_price"));
                order.setCreateTime(resultSet.getString("orders.create_time"));
                order.setAccountEmail(resultSet.getString(EMAIL));
                order.setOrderStatus(resultSet.getString(STATUS));
                list.add(order);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return list;
    }

    public void payOrder(int orderId) {
        String sql = "update orders set order_status_id=? where id=?;";
        changeStatusesRoomAndOrder(orderId, sql, ORDER_PAID, ROOM_OCCUPIED);
    }


    public void updateRawOrder(int orderId, int roomId) {
        Connection connection = getConnection();
        String update = "update orders set room_id=?, total_price=? where id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            int days = getDays(orderId, connection);
            BigDecimal price = new BigDecimal(getPrice(roomId, connection));
            BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(days));
            preparedStatement.setInt(1, roomId);
            preparedStatement.setBigDecimal(2, totalPrice);
            preparedStatement.setInt(3, orderId);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            try {
                connection.rollback();
            } catch (SQLException exception) {
                LOG.error(exception);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                LOG.error(exception);
            }
        }
    }

    private String getPrice(int roomId, Connection connection) throws SQLException {
        String sql = "select price from rooms where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return String.valueOf(resultSet.getBigDecimal(PRICE));
        } catch (SQLException e) {
            LOG.error(e);
            throw e;
        }
    }

    private int getDays(int orderId, Connection connection) throws SQLException {
        String sql = "select days from orders where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(DAYS);
        } catch (SQLException e) {
            LOG.error(e);
            throw e;
        }
    }

    public List<Room> getAllRooms(int places, String type) {
        List<Room> rooms = new ArrayList<>();
        String sqlQuerySelect = "select * from rooms join room_types on rooms.room_type_id = room_types.id join room_statuses" +
                "    on rooms.room_status_id = room_statuses.id where places=? and type=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuerySelect)) {
            preparedStatement.setInt(1, places);
            preparedStatement.setString(2, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setPrice(resultSet.getBigDecimal(PRICE));
                room.setPlaces(resultSet.getInt(PLACES));
                room.setStatus(resultSet.getString(STATUS));
                room.setType(resultSet.getInt(ROOM_TYPE_ID));
                room.setId(resultSet.getInt(ROOMS_ID));
                rooms.add(room);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return rooms;
    }

    public int getCountOfPages() {
        int count = 0;
        String sql = "select count(*) from rooms;";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return count;
    }

    public void checkerPaidOrder() {
        String select = "select * from orders join order_statuses os on os.id = orders.order_status_id where order_status_id = 2;";
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(select)) {
            connection.setAutoCommit(false);

            while (resultSet.next()) {
                Timestamp createTime = Timestamp.valueOf(resultSet.getString(CREATE_TIME));
                LocalDateTime timeForPaid = createTime.toLocalDateTime().plusDays(2);
                LocalDateTime timeIsNow = LocalDateTime.now();
                if (timeIsNow.isAfter(timeForPaid)) {
                    String disableOrder = "update orders set order_status_id=" + ORDER_TIME_IS_OVER + " where id=" + resultSet.getInt(ID);
                    connection.createStatement().execute(disableOrder);
                    String changeStatusOfRoom = "update rooms set room_status_id=" + ROOM_FREE + " where id=" + resultSet.getInt(ROOM_ID);
                    connection.createStatement().execute(changeStatusOfRoom);
                }
            }
            connection.commit();
        } catch (SQLException throwable) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error(e);
            }
            LOG.error(throwable);
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    private Room getRoom(int idRoom, Connection connection) {
        Room room = null;
        String sqlQuery = "select * from rooms join room_types on rooms.room_type_id =" +
                " room_types.id join room_statuses on rooms.room_status_id = room_statuses.id where rooms.id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            room = new Room();
            preparedStatement.setInt(1, idRoom);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                room.setPrice(resultSet.getBigDecimal(PRICE));
                room.setPlaces(resultSet.getInt(PLACES));
                room.setType(resultSet.getInt(ROOM_TYPE_ID));
                room.setStatus(resultSet.getString(STATUS));
                room.setId(resultSet.getInt(ROOMS_ID));
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return room;
    }

    private void changeStatusesRoomAndOrder(int orderId, String sqlQuery, int orderStatus, int roomStatus) {
        String sqlQuerySelect = "select room_id from orders where orders.id=?;";
        Connection connection = getConnection();
        try (PreparedStatement firstStatement = connection.prepareStatement(sqlQuery);
             PreparedStatement secondStatement = connection.prepareStatement(sqlQuerySelect)) {

            connection.setAutoCommit(false);
            firstStatement.setInt(1, orderStatus);
            firstStatement.setInt(2, orderId);
            firstStatement.execute();
            secondStatement.setInt(1, orderId);
            ResultSet resultSet = secondStatement.executeQuery();
            resultSet.next();
            int roomId = resultSet.getInt(ROOM_ID);
            updateRoom(roomId, roomStatus, connection);
            connection.commit();

        } catch (SQLException e) {
            LOG.error(e);
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                LOG.error(throwable);
            }
        }
    }

    private void updateRoom(int roomId, int status, Connection connection) {
        String sqlQueryUpdate = "update rooms set room_status_id=? where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryUpdate)) {
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, roomId);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
}
