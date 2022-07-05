package com.academy.service;


import com.academy.datasource.CustomerDataProvider;
import customers.Customer;

import javax.jws.WebParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path(value = "/resources")
public class CustomerRestService {

    @GET
    @Path(value = "/hello_World")
    @Produces(MediaType.APPLICATION_XML)
    public String helloWorld(){

        return "Hello_World";
    }

    @PUT
    @Path(value = "/hello_customer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> helloCUstomer() throws SQLException {
        CustomerDataProvider customerDataProvider = new CustomerDataProvider();

        return customerDataProvider.updateCustomer(1, "Ciccio_Pasticcio");
    }

    @POST
    @Path(value = "/hello_insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<String> selectfromCustomer(@WebParam (name = "customer")Customer customer) throws SQLException {
        CustomerDataProvider customerDataProvider = new CustomerDataProvider();
        int esito = customerDataProvider.insertCustomer(customer);
        if(esito > 0){
            return customerDataProvider.selectCustomer();
        } else {
            return null;
        }
    }

    @DELETE
    @Path(value = "/hello_delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int deleteCustomer() throws SQLException{
        CustomerDataProvider customerDataProvider = new CustomerDataProvider();
        return customerDataProvider.deleteCustomer();
    }

}
