package org.example.week01.customer;

public class Customer {
    private String userId;		//ID
    private String userName;		//name
    private int gubun;			//0: 임직원, 1: 일반고객
    private long point;			// 보유포인트
    private boolean blackConsumerFlg;	//

    public Customer(String userId, String userName, int gubun, long point, boolean blackConsumerFlg) {
        this.userId = userId;
        this.userName = userName;
        this.gubun = gubun;
        this.point = point;
        this.blackConsumerFlg = blackConsumerFlg;
    }

}
