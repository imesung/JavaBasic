package org.example.week01.customer;

import java.util.HashMap;
import java.util.Map;

public abstract class CustService {
    protected abstract boolean checkCustomerStatus(Customer customer);

    public Map<String, String> reqeustCustService(Customer customer) {
        Map<String, String> result = new HashMap<>();

        //고객 주문 가능 상태 체크
        boolean custSt = checkCustomerStatus(customer);
        result.put("custStCd", String.valueOf(custSt));

        return result;
    }
}
