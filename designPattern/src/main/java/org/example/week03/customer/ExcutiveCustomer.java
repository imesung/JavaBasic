package com.github.bakerybluprint.croissant.week_03.hs.homework.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcutiveCustomer implements SuperCustomer{
    private static Logger logger = LoggerFactory.getLogger(ExcutiveCustomer.class);

    public ExcutiveCustomer() {
        logger.info("해당 고객은 임직원 고객입니다.");
    }

    @Override
    public int custGubun() {
        return 0;
    }
}
