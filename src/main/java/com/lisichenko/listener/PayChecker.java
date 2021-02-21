package com.lisichenko.listener;

import com.lisichenko.db.DBManager;
import org.apache.log4j.Logger;

/**
 * Thread for the check paid order or not.
 */
public class PayChecker implements Runnable {
    private static final Logger LOG = Logger.getLogger(PayChecker.class);

    @Override
    public void run() {
        DBManager dbManager = DBManager.getInstance();
        LOG.debug("Check for pay start");
        while (true) {
            try {
                dbManager.checkerPaidOrder();
                Thread.sleep(3000);
                LOG.debug("Check for pay finished");

            } catch (InterruptedException e) {
                LOG.error("Interrupt exception => " + e);
            }
        }
    }
}
