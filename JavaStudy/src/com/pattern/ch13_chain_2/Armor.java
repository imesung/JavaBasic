package com.pattern.ch13_chain_2;

public class Armor implements Defense {

    private Defense nextDefencse;
    private int def;

    public Armor() {
    }

    public Armor(int def) {
        this.def = def;
    }

    @Override
    public void defense(Attack attack) {

        //point : 요청을 처리하지 못하면 다음 객체로 가는 것이 아니라 무조건 다음 객체도 요청을 한다.
       process(attack);

       if(nextDefencse != null) {
           nextDefencse.defense(attack);
       }
    }

    private void process(Attack attack) {
        int amount = attack.getAmount();
        amount -= def;
        attack.setAmount(amount);
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setNextDefencse(Defense nextDefencse) {
        this.nextDefencse = nextDefencse;
    }
}
