## 메멘토 패턴

메멘토라는 객체를 이용하여 객체의 상태를 저장하고 추 후에 메멘토 객체를 이용하여 객체의 이전 상태로 복구.

---

<img src="https://user-images.githubusercontent.com/40616436/86130835-bd263700-bb1f-11ea-85a5-97a96c61a3ca.png" alt="image" style="zoom:50%;" />

---

## Originator

~~~java
public class Originator {

    String state;

    /**
     *
     * @info 메멘토에 상태 저장
     */
    public Memento createMemento() {
        return new Memento(state);
    }

    /**
     *
     * @info 메멘토를 활용하여 상태 복구
     */
    public void resotreMemento(Memento memento) {
        this.state = memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
~~~



## Memento

~~~java
public class Memento {

    private String state;

    protected Memento(String state) {
        this.state = state;
    }

    protected String getState() {
        return state;
    }
}
~~~



## Application

~~~java
public class Application {

    public static void main(String[] args) {

        Stack<Memento> mementos = new Stack<>();

        Originator originator = new Originator();

        originator.setState("state 1");
        mementos.push(originator.createMemento());
        originator.setState("state 2");
        mementos.push(originator.createMemento());
        originator.setState("state 3");
        mementos.push(originator.createMemento());
        originator.setState("final state");
        mementos.push(originator.createMemento());

        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //final state
        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //state 1
        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //state 2
        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //state 3
      
      
      	/////////////////////////////////////////////
        //사용자가 필요한 것만 저장 후 복구
        Stack<Memento> customMemento = new Stack<>();
        originator.setState("custom1");
        customMemento.push(originator.createMemento());
        originator.setState("custom2");
        originator.setState("custom3");
        originator.setState("custom4");
        System.out.println(originator.getState());
        originator.resotreMemento(customMemento.pop());
        System.out.println(originator.getState());
    }

}
~~~

---

**장점**

시스템을 사용하면서 핵심 객체가 자주 사용되는데, 이 때 핵심 객체의 상태가 매우 중요할 때 해당 상태를 별도의 객체에 저장 후 핵심 객체를 사용하므로 안전성을 보장할 수 있고, 핵심 데이터를 계속해서 캡슐화할 수 있다.