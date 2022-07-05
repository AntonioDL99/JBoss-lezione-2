package com.academy.service;

import com.academy.datasource.CustomerDataProvider;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebService
public class CustomerService {

    private Connection con;

    @WebMethod (operationName = "HelloAcademy")
    public String  testStringa(){
        System.out.println("Sono nel test string");
        String t1 = "ciao";
        return t1;
    }

    @WebMethod (operationName = "operazione_select")
    public List<String> getCustomerName() throws SQLException {
        CustomerDataProvider customerDataProvider = new CustomerDataProvider();
        return customerDataProvider.getCustomerName();
    }

    @WebMethod (operationName = "operazione_select_by_Id")
    public List<String> getCustomerNameById(@WebParam (name = "id") int id) throws SQLException {
        CustomerDataProvider customerDataProvider = new CustomerDataProvider();
        return customerDataProvider.getCustomerNameById(id);
    }
}
