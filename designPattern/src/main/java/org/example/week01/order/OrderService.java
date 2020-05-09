package org.example.week01.order;

import org.example.week01.customer.Customer;
import org.example.week01.product.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class OrderService {
    protected abstract Map<String, String> orderSheetRequest(Customer customer, Product product);
    protected abstract boolean checkLoginStatus();

    public boolean createOrder(Customer customer, Product product) {

        Map<String, String> orderStatusResult = orderSheetRequest(customer, product);
        if(orderStatusResult.get("Status").equals("E")) {
            return true;
        }
        return false;
    }
}
