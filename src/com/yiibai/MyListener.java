package com.yiibai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {

        String jdbcDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost/test";

        // Database credentials
        String dbUser = "root";
        final String passwd = "545253234";
        try {
            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(dbURL, dbUser, passwd);
            String query="CREATE TABLE `employees` (" + 
                    "  `id` int(10) unsigned NOT NULL AUTO_INCREMENT," + 
                    "  `name` varchar(64) NOT NULL DEFAULT ''," + 
                    "  `age` int(3) unsigned NOT NULL DEFAULT '0'," + 
                    "  `address` varchar(254) DEFAULT NULL," + 
                    "  `salary` float(8,2) unsigned DEFAULT NULL," + 
                    "  PRIMARY KEY (`id`)" + 
                    ") ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";  
            PreparedStatement ps=con.prepareStatement(query);  
            ps.executeUpdate();  

            System.out.println(query);  

            // storing connection object as an attribute in ServletContext
            ServletContext ctx = event.getServletContext();
            ctx.setAttribute("mycon", con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Servlet has contextDestroyed...");
    }

}

/*package com.yiibai;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

*//**
 * Application Lifecycle Listener implementation class MyListener
 *
 *//*
@WebListener
public class MyListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {

        String jdbcDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost/testdb";

        // Database credentials
        String dbUser = "root";
        final String passwd = "123456";
        try {
            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(dbURL, dbUser, passwd);
            // storing connection object as an attribute in ServletContext
            ServletContext ctx = event.getServletContext();
            ctx.setAttribute("mycon", con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Servlet has contextDestroyed...");
    }

}*/