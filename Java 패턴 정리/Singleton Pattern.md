## 싱글톤 패턴

> 싱글톤이란, 하나의 인스턴스만 생성하도록 구현하는 것을 말한다.



**기본적인 설계**

<img src="https://user-images.githubusercontent.com/40616436/80898239-2239f800-8d3c-11ea-9a89-5b7c2eafe318.png" alt="image" style="zoom:50%;" />



**요구사항**

- 개발 중 시스템에서 스피커에 접근할 수 있는 클래스를 만들어달라.



**싱글톤 객체 정의**

~~~java
public class SystemSpeaker {
  static private SystemSpeaker instance;
  private int volume;

  private SystemSpeaker() {
    volume = 5;
  }

  //하나의 인스턴스만을 만들기 위한 메소드
  public static SystemSpeaker getInstance() {
    if(null == instance) {
      return instance = new SystemSpeaker();
    }
    return instance;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }
}
~~~



**Main**

~~~java
public static void main(String [] args) {
  SystemSpeaker systemSpeaker1 = SystemSpeaker.getInstance();
  SystemSpeaker systemSpeaker2 = SystemSpeaker.getInstance();

  System.out.println("systemSpeaker1 : " + systemSpeaker1);
  System.out.println("systemSpeaker2 : " + systemSpeaker2);
}
~~~



**실행결과**

![image](https://user-images.githubusercontent.com/40616436/80898717-8dd29400-8d41-11ea-8eef-250dc664e54a.png)

- 두개의 객체는 같은 주소를 가지고 있는 것을 확인할 수 있다.



**리플렉션을 사용해서 싱글톤 깨버리기**

- Java에서는 위와 같은 싱글톤 정의를 깨버릴 수 있는 경우도 있다.

~~~java
public static void main(String [] args) throws ClassCastException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
  SystemSpeaker systemSpeaker1 = SystemSpeaker.getInstance();
  SystemSpeaker systemSpeaker2 = SystemSpeaker.getInstance();

  System.out.println("systemSpeaker1 : " + systemSpeaker1);
  System.out.println("systemSpeaker2 : " + systemSpeaker2);

  //리플렉션을 활용하여 싱글톤 깨버리기
  Class<?> speakerClass = Class.forName("com.pattern.ch05_singleton.SystemSpeaker");
  Constructor<?> constructor = speakerClass.getDeclaredConstructor();
  //접근 제한자에 상관없이 모두 접근 가능
  constructor.setAccessible(true);
  SystemSpeaker systemSpeaker3 = (SystemSpeaker) constructor.newInstance();
  System.out.println(systemSpeaker3);

}
~~~

- 소스에서 보는 봐와 같이 reflection을 활용하여 해당 클래스의 생성자 메소드에 접근하여 아예 새로운 인스턴스를 만들 수 있다.

![image](https://user-images.githubusercontent.com/40616436/80899237-d17ccc00-8d48-11ea-94e1-890c3d32bfba.png)

- **그로 인해 정말 안정적인 싱글톤을 생성하고자 한다면 enum을 사용하여 싱글톤 객체를 만들 수 있도록 해야한다.**



**Enum 사용**

~~~java
public enum EnumSystemSpeaker {
  INSTANCE;

  private int volume = 5;

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }
}

//Main
//enum 사용
public static void main(String [] args) {
  System.out.println(System.identityHashCode(EnumSystemSpeaker.INSTANCE.getClass()));
	System.out.println(System.identityHashCode(EnumSystemSpeaker.INSTANCE.getClass()));
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/80901337-bf9d2800-8d4c-11ea-821b-dd04376f855c.png" alt="image" style="zoom:50%;" />

- 10진수로 된 메모리 주소가 동일하는 것을 확인할 수 있다.
- **Enum을 사용할 시 스레드 세이프하고 Serialization을 스스로 해결하며, reflection에서도 안정성을 보장하는 특징을 가지고 있다.**