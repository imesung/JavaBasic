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

    //고객 주문 가능 여부 확인
    public boolean chkCustStatus() {
        if(this.gubun == 0) {
            if(this.point == 0) {
                return false;
            }
        } else if(this.gubun == 1) {
            if(this.blackConsumerFlg) {
                return false;
            }
        } else if(blackConsumerFlg) {
            return false;
        }
        return true;
    }

    public boolean chkCustGubun(String giftStCd) {
        if(giftStCd.equals("false") && this.gubun == 1) {
            System.out.println("일반 고객으로 인해 사은품 재고 없어 주문 불가");
            return false;
        } else if(giftStCd.equals("true") && this.gubun == 0) {
            System.out.println("임직원 고객으로 인해 사은품 재고 있어 주문 불가");
            return false;
        }
        return true;
    }

    public boolean chkPoint() {
        if(this.point < 3000) {
            System.out.println("포인트 부족");
            return false;
        }
        return true;
    }

    public long usePoint(long prdPrc) {
        return prdPrc - this.point;
    }
}
