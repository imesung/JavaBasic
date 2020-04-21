package com.pattern.ch03_template;

public class DefaultGameConnectHelper extends AbstGameConnectHelper{
    @Override
    protected String doSecurity(String string) {
        System.out.println("강화된 알고리즘 암호화 디코드");
        return string;
    }

    @Override
    protected boolean authentication(String id, String password) {
        System.out.println("아이디/암호 확인 과정");
        return true;
    }

    @Override
    protected int authorization(String userName) {
        System.out.println("권한 확인");

        //추가 요구 사항 : 서버에서 10시에 유저 이름으로 유저의 나이를 확인 후 성인이 아니면 shutdown

        return -1;
    }

    @Override
    protected String conection(String info) {
        System.out.println("마지막 접속 단계");
        return info;
    }
}
