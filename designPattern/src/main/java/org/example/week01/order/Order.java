package org.example.week01.order;

import org.example.week01.customer.Customer;
import org.example.week01.product.Product;

import java.util.HashMap;

public interface Order {
    public HashMap<String, String> orderSheetRequest(Customer customer, Product product);
}
