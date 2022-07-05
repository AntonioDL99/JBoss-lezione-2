package com.academy.datasource;

import com.academy.servlet.Servlet;
import customers.Customer;


import java.rmi.server.ServerCloneException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDataProvider {

    public List<String> getCustomerName() throws SQLException {
        List<String> names = new ArrayList<>();
        Connection con = Servlet.getConnection();
        String query = "select * from classicmodels.customers";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                names.add(rs.getString("customername"));
            }
        } catch (SQLException e) {
            new Exception(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return names;
    }

    public List<String> getCustomerNameById(int id) throws SQLException {
        List<String> names = new ArrayList<>();
        Connection con = Servlet.getConnection();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
           stat = con.prepareStatement("select * from classicmodels.customers Where customernumber= ?");
           stat.setInt(1, id);
            rs = stat.executeQuery();
           while(rs.next()){
               names.add(rs.getString("customername"));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            rs.close();
            con.close();
        }
        return names;
    }

    public List<String> updateCustomer(int id, String name) throws SQLException {
        List<String> update = new ArrayList<>();
        Connection con = Servlet.getConnection();
        PreparedStatement stat = null;
        ResultSet rs = null;

        stat = con.prepareStatement("update customers set customername = 'Ciccio Pasticcio' where id= ? ");
        stat.setInt(1,id);
        stat.setString(1, name);
        rs = stat.executeQuery();
        while(rs.next()){
            update.add(rs.getString("customername"));
        }
        return update;
    }

    public int insertCustomer(Customer customer) throws SQLException {
        System.out.println("Sono all'inizio della insert");
        Connection con = Servlet.getConnection();
        PreparedStatement stat = null;

        stat = con.prepareStatement("insert into classicmodels.customers (customernumber, customername, contactlastname, contactfirstname, phone, addressline1, addressline2, city, state, postalcode," +
                "country, salesrepemployeenumber, creditlimit) values (150,'Coop', ?, ?, '333333', 'via del test', 'via del test 2', 'test', 'Italia', '809090', 'test', 1504, 34564)");
        stat.setString(1, customer.getNome());
        stat.setString(2, customer.getCognome());

        int rs = stat.executeUpdate();
        System.out.println("Sono alla fine della insert");
        return rs;
    }

    public int deleteCustomer() throws  SQLException {
        System.out.println("Sono all'inizio della delete");
        Connection con = Servlet.getConnection();
        PreparedStatement del = null;

        del = con.prepareStatement("delete from classicmodels.customers where customername = 'Atelier Graphique'");
        int rs = del.executeUpdate();
        System.out.println("Sono alla fine della delete");
        return rs;
    }

    public List<String> selectCustomer() throws SQLException {
        System.out.println("Sono all'inizio della select");
        List<String> selectCustomer = new ArrayList<>();
        Connection con = Servlet.getConnection();
        PreparedStatement sel = null;
        ResultSet rs = null;

        sel = con.prepareStatement("select * from classicmodels.customers");
        rs = sel.executeQuery();

        while(rs.next()){
            selectCustomer.add((rs.getString(2)));
        }
        System.out.println("Sono alla fine della select");
        con.close();
        rs.close();
        return selectCustomer;
    }
}
