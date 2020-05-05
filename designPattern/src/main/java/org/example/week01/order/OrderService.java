package org.example.week01.order;

import org.example.week01.customer.Customer;
import org.example.week01.product.Product;

import java.util.HashMap;

public abstract class OrderService {
    protected abstract HashMap<String, String> orderSheetRequest(Customer customer, Product product);
    protected abstract boolean checkLoginStatus();

    public void createOrder(Customer customer, Product product) {

        HashMap<String, String> orderStatusResult = orderSheetRequest(customer, product);
    }
}
