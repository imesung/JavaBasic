package com.pattern.ch16_mediator;

public class Application {
    public static void main(String[] args) {
        Mediator chatMediator = new ChatMediator();

        User user1 = new ChatUser("mesung");
        User user2 = new ChatUser("junwoo");
        User user3 = new ChatUser("doyeon");
        User user4 = new ChatUser("sanghyun");

        user1.join(chatMediator);
        user2.join(chatMediator);
        user3.join(chatMediator);
        user4.join(chatMediator);

        user3.sendData("혜성 준우 안녕하세요.");
        ///////////////////////////////////////////
        Mediator gameMediator = new GameMediator();

        User user5 = new GameUser("mesung");
        User user6 = new GameUser("junwoo");
        User user7 = new GameUser("doyeon");
        User user8 = new GameUser("sanghyun");

        user5.join(gameMediator);
        user6.join(gameMediator);
        user7.join(gameMediator);
        user8.join(gameMediator);

        user5.sendData("도연 상현 안녕하세요.");
    }
}
