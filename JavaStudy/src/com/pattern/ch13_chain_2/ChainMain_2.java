package com.pattern.ch13_chain_2;

public class ChainMain_2 {
    public static void main(String[] args) {
        Attack attack = new Attack(100);

        Armor armor1 = new Armor(10);
        Armor armor2 = new Armor(15);

        armor1.setNextDefencse(armor2);

        //첫번째 공격
        armor1.defense(attack);

        System.out.println(attack.getAmount());


        //Point 2
        Defense defense = new Defense() {
            @Override
            public void defense(Attack attack) {
                int amount = attack.getAmount();
                attack.setAmount(amount -= 50);

            }
        };

        attack.setAmount(100);

        //게임 도중 방어 갑옷 추가 착용
        armor2.setNextDefencse(defense);

        //두번째 공격
        armor1.defense(attack);

        System.out.println(attack.getAmount());

        //동적으로도 책임 사슬에 책임을 더 추가할 수 있다!
    }
}
