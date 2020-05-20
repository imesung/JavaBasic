package org.example.week01.login;

import org.apache.log4j.Logger;
import org.example.week01.customer.Customer;

public class Login {
    Logger logger = Logger.getLogger(Login.class);
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
