package com.pattern.ch16_mediator;

public class GameMediator extends Mediator{
    @Override
    public void mediate(String data) {
        for(User user : users) {
            //해당 메시지를 도연과 상연에게 메시지 전송
            if(user.getName().equals("doyeon") || user.getName().equals("sanghyun")) {
                user.handle(data);
            }
        }
    }
}
