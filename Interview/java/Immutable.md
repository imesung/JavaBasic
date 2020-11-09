## Immutable

Immutable Class는 변경이 불가능한 클래스이며, 레퍼런스 타입의 객체이기 때문에 heap영역에 생성된다.

Immutable Class는 변경이 불가능한 것이지 재할당은 가능하다. (소스로 살펴보자)

- ~~~java
  //String, Boolean, Integer, Float, Long 등등이 해당
  String str = "test";
  str = "test2";	//재할당
  ~~~

  ​	![image](https://user-images.githubusercontent.com/40616436/74583503-00698880-500b-11ea-9844-ad0cd620eda7.png)

- 소스를 통해 확인해보자

  ~~~java
  String str = "test";
  System.out.println(str.hashCode());	//hashCode는 주소값을 해싱
  str = "test2";
  System.out.println(str.hashCode());
  ~~~

  



**Immutable 특징**

**장점**

- 멀티 스레드 환경에서 thread-safe가 가능하다.
- 안전한 공유와 재사용이 가능하기 때문에 캐시를 사용하여 성능향상과 메모리 효율을 도모한다.
- ![image](https://user-images.githubusercontent.com/40616436/74584692-7fb18900-5018-11ea-8cb4-861f7e3c71da.png)



**단점**

- 객체가 가지는 값마다 새로운 객체가 필요하다. 즉, 새로운 메모리 공간을 차지하게 되어 성능저하가 발생할 수 있다.



**String의 Immutable**

String 객체에서는 Immutable함을 소스를 통해 확인할 수 있다.

String 메소드 중에서 concat() 메소드는 해당 객체에 문자열을 붙여주는 역할을 하는데, **새로운 String 객체를 생성해서 return해주는 로직을 가지고 있다.**

- ~~~java
  public String concat(String str) {
    if (str.isEmpty()) {
      return this;
    } else if (this.coder() == str.coder()) {
      byte[] val = this.value;
      byte[] oval = str.value;
      int len = val.length + oval.length;
      byte[] buf = Arrays.copyOf(val, len);
      System.arraycopy(oval, 0, buf, val.length, oval.length);
      return new String(buf, this.coder);	//객체 생성하여 반환
    } else {
      int len = this.length();
      int olen = str.length();
      byte[] buf = StringUTF16.newBytesFor(len + olen);
      this.getBytes(buf, 0, (byte)1);
      str.getBytes(buf, len, (byte)1);
      return new String(buf, (byte)1);	//객체 생성하여 반환
    }
  }
  ~~~



**String과 StringBuilder**

불변인 String에 비해 StringBuilder는 불변이 아니다. 즉, **객체 생성 후 참조값이 변경하면 새로운 객체가 생성되지 않는다는 것이다.**

~~~java
public class MyImmutable {
    private final StringBuilder name;

    public MyImmutable(StringBuilder name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyImmutable{" +
                "name='" + name + '\'' +
                '}';
    }
}

//main
StringBuilder name = new StringBuilder("name");
MyImmutable myImmutable = new MyImmutable(name);
name = name.append("update");
System.out.println(name);
System.out.println(myImmutable.toString());
~~~

![image](https://user-images.githubusercontent.com/40616436/74584627-de2a3780-5017-11ea-9aaf-953799030d26.png)

**StringBuilder는 참조하는 객체가 그대로 유지되기 때문에, MyImmutable이 참조하고 있는 name이 변경 시 동일하게 변경되는 것을 확인할 수 있다.**