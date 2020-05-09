package org.example.week01.customer;

public class CustServiceImpl extends CustService {

    //고객 상태 체크
    @Override
    protected boolean checkCustomerStatus(Customer customer) {
        boolean isCustStatus = customer.chkCustStatus();
        if(!isCustStatus) {
            System.out.println("해당 고객 주문 불가 상태");
        }
        return isCustStatus;
    }
}
