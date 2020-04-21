package com.pattern.ch03_template;

public abstract class AbstGameConnectHelper {
    protected abstract String doSecurity(String string);
    protected abstract boolean authentication(String id, String password);
    protected abstract int authorization(String userName);
    protected abstract String conection(String info);

    //템플릿 메소드
    public String requestConnection(String encodedInfo) {

        //보안 과정 -> 암호화 된 문자열을 디코드(복호화) 한다.
        String decodedInfo = doSecurity(encodedInfo);

        //인증 과정
        String id = "aaa";
        String password = "bbb";
        if(!authentication(id, password)) {
            throw new Error("아이디 암호 불일치");
        }

        //권한 과정
        String userName = "mesung";
        int i = authorization(userName);
        switch (i) {
            case -1:
                throw new Error("미성년자 사용 금지");
            case 0: //게임 매니저
                break;
            case 1: //유료 회원
                break;
            case 2: //무료 회원
                break;
            case 3: //권한 없음
                break;
            default://기타 상황
        }

        //접속 과정
        return conection(decodedInfo);
    }
}
