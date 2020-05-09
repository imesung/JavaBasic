package org.example.week01.payment;

import org.example.week01.customer.Customer;
import org.example.week01.product.Product;

public class PaymentServiceImpl extends PaymentService {
    @Override
    protected String mtdOfPayment() {
    //카카오페이, 신용카드, 현금결제, 포인트사용
        return "카카오페이";
    }

    @Override
    protected boolean pay(Customer customer, Product product) {
        if(product.payPrd(customer)) {
            System.out.println("결제 성공");
            return true;
        } else {
            System.out.println("결제 실패");
            return false;
        }
    }
}