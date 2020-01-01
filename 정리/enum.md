### enum

열거형이라고 칭하고, JDK5부터 추가되었다.

JDK5 전에는 상수를 활용하여 열거형의 개념을 표현했다.

```java
public class EnumStudy {
    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    
    public static void main(String[] args) {
        String gender;
        
        gender = EnumStudy.MALE;
        gender = EnumStudy.FEMALE;
    }
}
```

**상수를 사용했을 때 문제가 발생한다는 것을 찾을 수 있었다**

- gender라는  String 변수는 처음 `"MALE"`이라는 변수로 고정되기를 원하는데, 다음 라인에서 `"FEMALE"`로 변경되는 것을 볼 수가 있다.

**해결**

```java
public enum Gender {
    MALE, FEMALE;
}

//main
public static void main(String[] args) {
    Gender gender;

    gender = Gender.MALE;
    System.out.println(gender);
    gender = Gender.FEMALE;
    System.out.println(gender);
    //Gender타입의 변수에는 MALE 혹은 FEMALE만 대입 가능하다.
}
```



---

enum의 생성자 및 getter

```java
public enum Menu {
    PIZZA(2000), BURGER(1000), COKE(500);
    int value;

    private Menu(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

Menu는 PIZZA, BURGER등 각각의 객체를 가지고 있고 매개변수(PIZZA(2000))도 가지고 있다.

또한, 해당 매개변수를 가져오는 getter와 각 객체를 생성하는 생성자도 가지고 있는 것을 볼 수있다.

그런데 생성자를 자세히 보면 private으로 선언되어 있는 것을 확인할 수 있는데, 이유는 enum은 고정된 상수의 집합이므로 **컴파일 타임**에 값을 알아야 하고 외부를 통해 생성자를 호출할 수도 없어야 한다.

- Java에서 컴파일은 Java로 된 소스코드를 JVM이 인식할 수 있는 JVM 명령어 코드(바이트 코드)로 변환하는 것을 의미한다. 그 후 해당 바이트코드는 class 영역에 로드된다. 
- 즉, enum class(method) 영역에 할당된다.

**결과적으로 enum은 컴파일시에 정해지고 클라이언트에서 생성할 수 없기 때문에 타입 안정성이 보장되는 것이고, 싱글톤 패턴을 구현하는데 자주 사용되는 것이다.**



---

Java에서 enum의 확장

- Java에서는 enum에 메서드를 만들어줄 수 있다.

```java
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

//main
public static void main(String [] args) {
    MenuImpl menuImpl = MenuImpl.PIZZA;
    menuImpl.show();	//피자 가격은 2000입니다.
}
```

해당 MenuImpl enum의 구조는 아래와 같다.

<img width="300" alt="enum 확장" src="https://user-images.githubusercontent.com/40616436/71641656-1a613d00-2ce3-11ea-82e1-29824169c7a9.PNG">

---



enum 싱글톤

싱글톤 객체를 만들고자할 때도 enum을 자주 사용한다.

```java
public enum EnumSingle {
    INSTANCE(30, "richard");

    String name;
    int age;

    EnumSingle(int age, String name) {
        this.age = age;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    
}

//main
public static void main(String [] args) {
    EnumSingle enumSingle = EnumSingle.INSTANCE;
}
```

EnumSingle이라는 클래스는 어떤 짓을 해도 하나의 객체만으로 남아있을 것이다.

직렬화 및 리플렉션을 통한 인스턴스 생성도 불가능하다!

---



그렇다면 enum은 언제 사용하는 걸까?

- 서로 연관된 상수들을 사용하고 자 할 때
- 싱글톤 객체를 선언하고 싶을 때

---



**enum의 정리**

- enum을 사용하면 상수라는 것을 명확히 표현할 수 있으며 코드의 가독성이 증가한다.
- 인스턴스 생성(접근제어자가 없음)과 상속을 방지하여 타입 안정성이 보장된다.
- 또한 원시 타입과 같이 switch문에서 사용이 가능하다.
- enum은 메서드를 사용하여 추가적인 행동을 정의할 수 있다.
- enum의 열거 상수는 클래스 영역에 할당되고 각 객체는 heap영역에 할당되어 열거 상수가 각 객체를 참조하고 있는 것이다.
  - <img width="415" alt="enum 참조" src="https://user-images.githubusercontent.com/40616436/71641791-e38c2680-2ce4-11ea-80ba-4cd9d4200bd5.PNG">
- 싱글톤으로 사용 가능하다.
  - 직렬화, 리플렉션을 통한 인스턴스 생성도 불가능하다.