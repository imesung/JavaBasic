package org.example.week01.payment;

import org.example.week01.customer.Customer;
import org.example.week01.product.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class PaymentService {
    protected abstract String mtdOfPayment();
    protected abstract boolean pay(Customer customer, Product product);

    public Map<String, String> requestPayment(Customer customer, Product product) {
        Map<String, String> result = new HashMap<>();

        boolean payFlg = pay(customer, product);

        result.put("payFlg", String.valueOf(payFlg));

        return result;
    }
}


