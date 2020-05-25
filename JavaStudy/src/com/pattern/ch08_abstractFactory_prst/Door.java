package com.pattern.ch08_abstractFactory_prst;

public abstract class Door {
    private DoorStatus doorStatus;  //

    public Door() { doorStatus = DoorStatus.CLOSED; }
    public DoorStatus getDoorStatus() { return doorStatus; }

    // primitive 또는 hook 메서드
    protected abstract void doClose();
    // 템플릿 메서드: 문을 닫는 기능 수행
    public void close() {
        // 이미 문이 닫혀 있으면 아무 동작을 하지 않음
        if(doorStatus == DoorStatus.CLOSED)
            return;
        // primitive 또는 hook 메서드. 하위 클래스에서 오버라이드
        doClose(); // 실제로 문을 닫는 동작을 수행
        doorStatus = DoorStatus.CLOSED; // 문의 상태를 닫힘으로 기록
    }

    // primitive 또는 hook 메서드
    protected abstract void doOpen();
    // 템플릿 메서드: 문을 여는 기능 수행
    public void open() {
        // 이미 문이 열려 있으면 아무 동작을 하지 않음
        if(doorStatus == DoorStatus.OPENED)
            return;
        // primitive 또는 hook 메서드. 하위 클래스에서 오버라이드
        doOpen(); // 실제로 문을 여는 동작을 수행
        doorStatus = DoorStatus.OPENED; // 문의 상태를 열림으로 기록
    }
}
