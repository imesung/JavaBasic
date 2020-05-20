package com.github.bakerybluprint.croissant.week_03.hs.homework.customer;

import com.github.bakerybluprint.croissant.week_03.hs.homework.login.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer {
    private static Logger logger = LoggerFactory.getLogger(Customer.class);

    private final String userId;		//ID
    private final String userName;		//name
    private final SuperCustomer customerGubun;  //고객 상태
    private final long point;			// 보유포인트
    private final boolean blackConsumerFlg;	//거래거절 고객 여부

    private final CustomerFactory customerFactory = new CustomerFactory();  //고객 상태

    public Customer(String userId, String userName, int gubun, long point, boolean blackConsumerFlg) {
        this.userId = userId;
        this.userName = userName;
        this.point = point;
        this.blackConsumerFlg = blackConsumerFlg;

        //로그인 여부 확인
        Login login = Login.getInstance();
        login.loginYn(this);

        //팩토리 메소드 패턴으로 고객 종류에 따른 고객 생성
        this.customerGubun = customerFactory.createCustomer(gubun);
    }

    //고객 주문 가능 여부 확인
    public boolean chkCustStatus() {
        if(this.customerGubun.custGubun() == 0) {
            if(this.point == 0) {
                logger.info("해당 고객 주문 불가합니다.");
                return false;
            }
        } else if(this.customerGubun.custGubun() == 1) {
            if(this.blackConsumerFlg) {
                logger.info("해당 고객 주문 불가합니다.");
                return false;
            }
        } else if(this.customerGubun.custGubun() == 2) {
            logger.info("해당 고객 주문 불가합니다.");
            return false;
        }
        logger.info("해당 고객 주문 가능합니다.");
        return true;
    }

    public boolean chkCustGubun(String giftStCd) {
        if(giftStCd.equals("false") && this.customerGubun.custGubun() == 1) {
            logger.info("일반 고객으로 인해 사은품 재고 없어 주문 불가");
            return false;
        } else if(giftStCd.equals("true") && this.customerGubun.custGubun() == 0) {
            logger.info("임직원 고객으로 인해 사은품 재고 있어 주문 불가");
            return false;
        }
        return true;
    }

    public boolean chkPoint() {
        if(this.point < 3000) {
            logger.info("포인트 부족");
            return false;
        }
        return true;
    }

    public void custLoginSuccess() {
        logger.info(this.userId + " 로그인이 완료되었습니다.");
    }
}
