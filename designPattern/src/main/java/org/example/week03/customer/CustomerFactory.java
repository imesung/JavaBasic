package com.github.bakerybluprint.croissant.week_03.hs.homework.customer;

public class CustomerFactory {
    public SuperCustomer createCustomer(int gubun) {
        SuperCustomer superCustomer = null;
        if(gubun == 0) {
            superCustomer = new ExcutiveCustomer();
        } else if(gubun == 1) {
            superCustomer = new GeneralCustomer();
        } else {
            superCustomer = new RejectCustomer();
        }
        return superCustomer;
    }
}
