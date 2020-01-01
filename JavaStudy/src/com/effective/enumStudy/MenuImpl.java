package com.effective.enumStudy;

public enum MenuImpl {
    PIZZA(2000){
        public void show(){
            System.out.println("피자 가격은 " + getValue() + "입니다.");
        }
    },
    BURGER(1000){
        public void show(){
            System.out.println("햄버거 가격은 " + getValue() + "입니다.");
        }
    },
    COKE(500) {
        public void show(){
            System.out.println("콜라 가격은 " + getValue() + "입니다.");
        }
    };
    int value;

    private MenuImpl(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    //추상메서드를 사용하면 상수에서 재정의가 가능하므로, enum의 확장성이 증가한다.
    //즉, 상수를 사용할 때 꼭 필요로 하는 메서드를 추가할 수 있다.
    public abstract void show();
}
