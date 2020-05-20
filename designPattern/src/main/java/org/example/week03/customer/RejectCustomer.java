package com.github.bakerybluprint.croissant.week_03.hs.homework.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RejectCustomer implements SuperCustomer {
    private static Logger logger = LoggerFactory.getLogger(RejectCustomer.class);

    public RejectCustomer() {
        logger.info("해당 고객은 거래거절 고객입니다.");
    }

    @Override
    public int custGubun() {
        return 2;
    }
}
