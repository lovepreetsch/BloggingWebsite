package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.ConnectionManager;

@WebListener
public class ServerInitializationListener implements ServletContextListener {

    private Thread serverThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	try {
        System.out.println("\u001B[1mContext Initialized - Winkdoc Server Startup\u001B[0m");

        // Start the NotificationSocketServer in a separate thread.
//        serverThread = new Thread(NotificationSocketServer::startNotificationServer);
//        serverThread.start();

        // This gets the DB connection at the start of the server.
        System.out.println("\u001B[1m--Trying to Connect to Winkdoc Database--\u001B[0m");
        ConnectionManager.getConn();

    	}
    	catch (Exception ex) 
    	{
    		System.out.println("Failed to start cron or connect to the Winkdoc Database");
    		ex.printStackTrace();
    	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop the NotificationSocketServer when the web application is destroyed
        if (serverThread != null && serverThread.isAlive()) {
            serverThread.interrupt();
            try {
                serverThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
