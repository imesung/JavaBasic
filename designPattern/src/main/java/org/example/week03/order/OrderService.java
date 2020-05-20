package com.github.bakerybluprint.croissant.week_03.hs.homework.order;

import com.github.bakerybluprint.croissant.week_03.hs.homework.customer.Customer;
import com.github.bakerybluprint.croissant.week_03.hs.homework.product.Product;

import java.util.Map;

public abstract class OrderService {
    protected abstract Map<String, String> orderSheetRequest(Customer customer, Product product);

    public boolean createOrder(Customer customer, Product product) {

        Map<String, String> orderStatusResult = orderSheetRequest(customer, product);
        if(orderStatusResult.get("Status").equals("E")) {
            return true;
        }
        return false;
    }
}
