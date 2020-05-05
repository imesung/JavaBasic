package org.example.week01.payment;

public class PaymentServiceImpl extends PaymentService
{
    @Override
    protected String mtdOfPayment() {
        //카카오페이, 신용카드, 현금결제, 포인트사용
        return "카카오페이";
    }

    @Override
    protected boolean pay(long point, long prc) {
        long prdPrc = 0;
        if(point < 3000) {
            System.out.println("결제수단 : " + mtdOfPayment());
            System.out.println("결제 금액 : " + prc);
            System.out.println("결제 완료되었습니다.");
            return true;
        }

        if(prc >= 3000) {
            if(prc > point) {
                prdPrc = prc - point;
                System.out.println("결제수단 : " + mtdOfPayment());
                System.out.println("포인트 사용 : " + point);
                System.out.println("결제 금액 : " + prdPrc);
                System.out.println("결제 완료되었습니다.");
                return true;
            } else {
                System.out.println("결제수단 : 포인트 사용");
                System.out.println("포인트 사용 : " + point);
                System.out.println("결제 완료되었습니다.");
                return true;
            }
        }
        return false;
    }
}
