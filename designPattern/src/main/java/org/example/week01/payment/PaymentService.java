package org.example.week01.payment;

import org.example.week01.customer.Customer;
import org.example.week01.product.Product;

import java.util.HashMap;

public abstract class PaymentService {
    protected abstract String mtdOfPayment();
    protected abstract boolean pay(long point, long prc);

    public HashMap<String, String> requestPayment(Customer customer, Product product) {
        HashMap<String, String> result = new HashMap<>();

        String chkMtdOfPayment = mtdOfPayment();
        boolean payFlg = pay(customer.getPoint(), product.getPrdPrc());

        result.put("payFlg", String.valueOf(payFlg));

        return result;
    }
}
