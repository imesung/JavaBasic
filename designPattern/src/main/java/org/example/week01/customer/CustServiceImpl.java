package org.example.week01.customer;

public class CustServiceImpl extends CustService {

    //고객 상태 체크
    @Override
    protected boolean checkCustomerStatus(Customer customer) {
        int gubun = customer.getGubun();

        if(gubun == 0) {
            if(customer.getPoint() == 0) {
                return false;
            }
        } else if(gubun == 1) {
            if(customer.isBlackConsumerFlg()) {
                return false;
            }
        }
        return true;
    }
}
