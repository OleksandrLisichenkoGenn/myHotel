package com.lisichenko.listener;

import org.apache.log4j.BasicConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener for the start new Thread for call the method which
 * will check order for paying among two days from create time order.
 */
@WebListener
public class StartServerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicConfigurator.configure();
        PayChecker runnable = new PayChecker();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
