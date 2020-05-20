package org.example.week01.payment;

import org.apache.log4j.Logger;
import org.example.week01.customer.Customer;
import org.example.week01.product.Product;


public class Payment {
    private static final Logger logger = Logger.getLogger(Payment.class);
    private final String payGubun;

    public Payment(String payGubun) {
        this.payGubun = payGubun;
    }

    public boolean pay(Customer customer, Product product) {
        //고객이 포인트 3000원 이상 가지고 있음
        if(customer.chkPoint()) {
            if(product.chkPrdPrc()) {
                logger.info("포인트 및 "+this.payGubun+"로 결제 진행하였습니다.");
            }
        } else {
            logger.info(this.payGubun + "로 결제 진행하였습니다.");
        }
        return true;
    }
}
