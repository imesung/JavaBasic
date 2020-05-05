package org.example.week01.customer;

public class Customer {
    private final String userId;		//ID
    private final String userName;		//name
    private final int gubun;			//0: 임직원, 1: 일반고객
    private final long point;			// 보유포인트
    private final boolean blackConsumerFlg;	//

    public Customer(String userId, String userName, int gubun, long point, boolean blackConsumerFlg) {
        this.userId = userId;
        this.userName = userName;
        this.gubun = gubun;
        this.point = point;
        this.blackConsumerFlg = blackConsumerFlg;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getGubun() {
        return gubun;
    }

    public long getPoint() {
        return point;
    }

    public boolean isBlackConsumerFlg() {
        return blackConsumerFlg;
    }
}
