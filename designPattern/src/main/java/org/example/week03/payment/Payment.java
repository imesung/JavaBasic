package com.github.bakerybluprint.croissant.week_03.hs.homework.payment;

import com.github.bakerybluprint.croissant.week_03.hs.homework.customer.Customer;
import com.github.bakerybluprint.croissant.week_03.hs.homework.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Payment {
    private static Logger logger = LoggerFactory.getLogger(Payment.class);
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
