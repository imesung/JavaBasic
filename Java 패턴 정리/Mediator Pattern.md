## 중재자 패턴

각 User가 Mediator를 통해 메시지를 전달하면 Mediator는 연결되어 있는 User들에게 메시지를 대신 전송해주는 패턴.

Ex. 단톡방 혹은 1:다 채팅

---

<img src="https://user-images.githubusercontent.com/40616436/86134706-e8f7eb80-bb24-11ea-891b-b1db6a0dbe18.png" alt="image" style="zoom:50%;" />

---

## User

~~~java
public abstract class User {
    private String name;
    private Mediator mediator;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @info Ex. 단톡방 접속
     * @param mediator
     */
    public boolean join(Mediator mediator) {
        this.mediator = mediator;
        return mediator.addUser(this);
    }

    /**
     * @info 단톡방에 메시지 전달.
     * @param data
     */
    public void sendData(String data) {
        mediator.mediate(data);
    }


    /**
     * @info 단톡방에서 user에게 메시지 전달 시 메시지를 각 user에 맞게 컨트롤
     * @param data
     */
    abstract public void handle(String data);
}
~~~



## Mediator

~~~java
public abstract class Mediator {
    protected List<User> users;

    public Mediator() {
        users = new ArrayList<>();
    }

    /**
     * @info user들 단톡방 접속 시 추가
     * @param user
     */
    public boolean addUser(User user) {
        return users.add(user);
    }

    /**
     * @info 중재자 역할(단톡방)로서 메시지를 user들에게 전달
     * @param data
     */
    public abstract void mediate(String data);
}

~~~



## Application

~~~java
public class Application {
    public static void main(String[] args) {
        Mediator mediator = new ChatMediator();

        User user1 = new ChatUser("mesung");
        User user2 = new ChatUser("junwoo");
        User user3 = new ChatUser("doyeon");
        User user4 = new ChatUser("sanghyun");

        user1.join(mediator);
        user2.join(mediator);
        user3.join(mediator);
        user4.join(mediator);
				
      	//메시지 전송
        user1.sendData("혜성 준우 안녕하세요.");
    }
}
~~~



## ChatMediator

~~~java
public class ChatMediator extends Mediator {
    @Override
    public void mediate(String data) {
        for(User user : users) {
            //해당 메시지를 헤성과 준우에게만 전송
            if(user.getName().equals("mesung") || user.getName().equals("junwoo")) {
                //메시지 전달
              	user.handle(data);
            }
        }
    }
}
~~~



## ChatUser

~~~java
public class ChatUser extends User{
    public ChatUser(String name) {
        super(name);
    }

  	//여기에서는 단톡방 예제로 인해 출력만 해주는데, 실무에서는 받은 데이터를 통해 여러 컨트롤이 가능.
    @Override
    public void handle(String data) {
        System.out.println(this.getName() + data);
    }
}

~~~



## GameMediator

~~~java
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

~~~



## GameUser

~~~java
public class GameUser extends User{

    public GameUser(String name) {
        super(name);
    }

    @Override
    public void handle(String data) {
        System.out.println("[게임 채널]");
        System.out.println(this.getName() + data);
    }
}
~~~



---

**장점**

Mediator 클래스를 사용함으로써 각 User 클래스 정의 시 Mediator 객체 이외에 다른 객체를 참조할 필요가 없으므로 설계 복잡도가 낮아지게 된다. 또한, User 객체가 추가되더라도 다른 User에는 영향을 받지 않으므로 확장성이 유리하다는 점을 알 수 있다.

User 객체로만 구성되어 있다면,

~~~java
user1.sendData(user2);
user1.sendData(user3);
user1.sendData(user4);

user2.sendData(user1);
user2.sendData(user3);
user2.sendData(user4);

//////////////////////
user1.sendDate();	//user2, 3, 4 모두 수신 가능

//위 예제와 같이 또 다른 채팅방도 가능
~~~

