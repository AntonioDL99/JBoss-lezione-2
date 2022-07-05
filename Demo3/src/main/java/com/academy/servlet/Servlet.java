package com.academy.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "starter", urlPatterns = "/servlet", loadOnStartup = 1)
public class Servlet extends HttpServlet {
    private Connection con;

    public static Connection getConnection() {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/classicmodels");
            con = ds.getConnection();
            System.out.println(con);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public void viewTable() throws SQLException {
        String query = "select * from classicmodels.customers";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String cn = rs.getString("customername");
                System.out.println(cn);
            }
        } catch (SQLException e) {
            new Exception(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        try {
            viewTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sono nella doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("Sono nella doPost");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        System.out.println("Sono nella doPut");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Sono nella destroy");
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
       con = getConnection();
        System.out.println("Sono nella init");
    }
}


