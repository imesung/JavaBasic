package com.github.bakerybluprint.croissant.week_03.hs.homework.login;

import com.github.bakerybluprint.croissant.week_03.hs.homework.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login {
    private static Logger logger = LoggerFactory.getLogger(Login.class);
    private static boolean loginChk = false;

    private Login() {
        loginChk = true;
    }

    public static Login getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final Login INSTANCE = new Login();
    }

    public boolean loginYn(Customer customer) {
        if(loginChk) {
            customer.custLoginSuccess();
        }
        return loginChk;
    }
}
