package com.github.bakerybluprint.croissant.week_03.hs.homework.order;

import com.github.bakerybluprint.croissant.week_03.hs.homework.customer.Customer;
import com.github.bakerybluprint.croissant.week_03.hs.homework.payment.Payment;
import com.github.bakerybluprint.croissant.week_03.hs.homework.product.Product;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceImpl extends OrderService {

    @Override
    public Map<String, String> orderSheetRequest(Customer customer, Product product) {
        Map<String, String> result = new HashMap<>();

        result.put("Status", "S");

        //1. 고객 주문 가능 상태 확인
        if (!custStatus(customer)) return result;

        //2. 상품 재고 상태 확인
        if(!stockStatus(product)) return result;

        //3. 사은품 재고 상태 확인
        if(!giftStatus(customer, product)) return result;

        //5. 결제 진행
        if (!payment(customer, product)) return result;

        result.put("Status", "E");

        return result;
    }

    private boolean stockStatus(Product product) {
        //상품 재고 상태 확인
        if(product.chkPrd()) {
            return true;
        }
        return false;
    }

    private boolean giftStatus(Customer customer, Product product) {
        return customer.chkCustGubun(String.valueOf(product.chkGift()));
    }

    private boolean custStatus(Customer customer) {
        //고객 상태 확인
        if(customer.chkCustStatus()) {
            return true;
        }
        return false;
    }

    private boolean payment(Customer customer, Product product) {
        Payment payment = new Payment("카카오페이");
        return payment.pay(customer, product);
    }
}
