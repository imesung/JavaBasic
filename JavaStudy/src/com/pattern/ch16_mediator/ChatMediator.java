package com.pattern.ch16_mediator;

public class ChatMediator extends Mediator {
    @Override
    public void mediate(String data) {
        for(User user : users) {
            //해당 메시지를 헤성과 준우에게만 전송
            if(user.getName().equals("mesung") || user.getName().equals("junwoo")) {
                user.handle(data);
            }
        }
    }
}
