package com.pattern.ch15_observer;

public class ObserverMain {
    public static void main(String[] args) {
        Button button = new Button();

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(Button button) {
                System.out.println(button + " is Clicked");
            }
        });

        button.onClick();
    }
}
