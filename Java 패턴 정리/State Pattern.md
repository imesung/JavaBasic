## 스테이트 패턴

상태를 객체로 나타내고 객체의 행동에 따라 상태가 변화.

---

<img src="https://user-images.githubusercontent.com/40616436/86128018-c1505580-bb1b-11ea-9c99-af8aba4092c7.png" alt="image" style="zoom:50%;" />

- Context : 인터페이스로 구성된 상태 객체를 가진 객체
- StateA, StateB : 액션

---

## Light(Context)

~~~java
//Context
public class Light{

    private LightState lightState;


    /**
     * @info 처음 상태는 꺼진 상태
     */
    public Light(LightState lightState) {
        this.lightState = lightState;
    }

    /**
     * @info 해당 행동을 통해 상태도 변경(on)
     */
    public void on() {
        this.lightState = this.lightState.on(this.lightState);
    }


    /**
     * @info 해당 행동을 통해 상태도 변경(off)
     */
    public void off() {
        this.lightState = this.lightState.off(this.lightState);
    }
}
~~~



## LightState(State)

~~~java
//State
public interface LightState {
    public LightState on(LightState lightState);
    public LightState off(LightState lightState);
}
~~~



## OffState, OnState(StateA, StateB)

~~~java
public class OffState implements LightState {
    @Override
    public LightState on(LightState lightState) {
        System.out.println("Light ON");
        return new OnState();
    }

    @Override
    public LightState off(LightState lightState) {
        System.out.println("Light Nothing");
        return lightState;
    }
}

public class OnState implements LightState {
    @Override
    public LightState on(LightState lightState) {
        System.out.println("Light Nothing");
        return lightState;
    }

    @Override
    public LightState off(LightState lightState) {
        System.out.println("Light Off");
        return new OffState();
    }
}
~~~



## Application

~~~java
public class Application {
    public static void main(String[] args) {
        Light light = new Light(new OffState());
        light.off();
        light.off();

        light.on();
        light.on();

        light.off();
        light.on();
    }
}
~~~

---

**전략 패턴과의 차이**

상태 패턴은 행동을 각 객체에 맞게 위임해주는 것은 전략 패턴과 매우 비슷하다. 

하지만 전략 패턴의 경우 알고리즘을 변경해주는 것이고, 상태 패턴의 경우 이벤트가 발생했을 때 상태에 따라서 다르게 해주고 상태도 변경해준다.

Ex. 전략 패턴의 경우 캐릭터에 따라 검을 휘두르거나 던질 수 있으나 상태 패턴에서는 캐릭터의 검이 캐릭터에 따라 총으로 변경될 수도 있는 것이다.



상태패턴(state pattern)은 어떤 상태나 타입을 다룬다. 즉 상태에 따른 행동을 캡슐화 한다.  전략패턴(strategy pattern)은 어떻게 객체가 일을 할지를 다룬다. 즉 알고리즘을 캡슐화 한다.  이 다른 목적을 수행하는 두 가지 패턴은 구현 방식이 아주 비슷하다. 두 가지 모두 객체에 의한 위임방식을 사용한다.



---

**장점**

State 패턴에서 클라이언트는 State 객체에 대해 자세히 몰라도 되며, 또 다른 State 객체를 추가하더라도 Context에는 영향을 주지 않으므로 State 수정에 대해 닫혀 있고 확장에 열려있는 OCP 원칙을 따르고 있기도 하다.

- 각 state가 수정해도 Light는 영향을 받지 않고, 새로운 TailLight라는 것이 추가되도 확장이 용이하다.

