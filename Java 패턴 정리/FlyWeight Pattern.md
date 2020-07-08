## 플라이웨이트 패턴

객체의 내부에서 참조하는 객체를 직접 만드는 것이 아니라, 플라이웨이트 패턴을 사용해서 객체가 없으면 만들고, 있으면 객체를 공유하는 방식의 패턴이다.

플라이 웨이트 패턴을 통해 메모리 공간을 절약할 수 있다.

---

**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/86915345-cedd8f00-c15c-11ea-96a6-759675589bff.png" alt="image" style="zoom:50%;" />

FlyWeight를 다루는데 Client가 직접적으로 다루지 않고, FlyWeightFactory를 통해 다루는 것을 볼 수 있다.

---

**예시**

Client가 어떤 Image라는 객체를 반복적으로 필요로 하고자 할 때, Image 객체는 반복적으로 새로운 객체로 생성하게 된다. 그러나 이 때, 플라이웨이트 패턴을 사용하게 되면, Image 객체를 반복적으로 새로운 객체로 생성하는 것이 아니라 기존에 Image 객체가 있으면 기존 객체를 Client에게 전달하는 것이다.

이렇게 같은 내용의 Image 객체를 사용하게 되면 GC 대상과 메모리 할당이 반복적으로 일어나게 된다.

---

**소스 확인**

~~~java
//FlyWeight
public class FlyWeight {
    private String data;

    public FlyWeight(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

//FlyWeightFactory
public class FlyWeightFactory {
    private Map<String, FlyWeight> pool;

    public FlyWeightFactory() {
        this.pool = new TreeMap<>();
    }

    public FlyWeight getFlyWeight(String key) {
        FlyWeight flyWeight = pool.get(key);
        if(flyWeight != null && pool.containsKey(key)) {
            System.out.println("재 사용" +  key.getClass().getName());
        } else {
            flyWeight = new FlyWeight(key);
            pool.put(key, flyWeight);
            System.out.println("새로 생성" + key.getClass().getName());
        }
        return flyWeight;
    }
}

//Main
public class Main {
    public static void main(String[] args) {
        FlyWeightFactory flyWeightFactory = new FlyWeightFactory();
        FlyWeight flyWeight = flyWeightFactory.getFlyWeight("AA");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("AA");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("AA");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("BB");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("AA");
        System.out.println(flyWeight);
    }
}
~~~

---

**정리**

해당 패턴을 사용하게 되면 자주 사용하는 객체를 필요할 때마다 새로 생성하지 않고, Factory를 통해 생성된 객체를 사용함으로써 메모리를 효율적으로 사용할 수 있다.

---

**String과 String Constant Pool**

우리가 살펴본 플라이웨이트 패턴은 사실, Java의 대표적인 클래스인 String에서도 확인할 수가 있다.

바로 Java 메모리 영역 중 Heap 영역에 있는 String Constant Pool이 플라이웨이 패턴을 적용하고 있다.

아래 소스를 살펴보자.

~~~java
String s1 = "hello";
System.out.println(s1.hashCode());
String s2 = "hello";
System.out.println(s2.hashCode());
~~~

위 소스의 실행 결과는 어떤 값이 나올까? 

~~~java
99162322
99162322
~~~

위와 같이 같은 수의 값이 출력되는 것을 볼 수 있다.

> String.hashCode()
>
> - hashCode의 대해서 완벽히 설명하려면 매우 깊이 있게 이야기를 해야하니, 간단한 코멘트로 설명을 대신 합니다.
> - hashCode는 Object의 메소드로서 현재 클래스 주소값(16진수)을 10진수 형태로 나타낸 코드 값으로 이해하면 됩니다.

그렇다면 다음 소스의 결과는 어떤 값이 나올까?

~~~java
String s3 = new String("hi");
System.out.println(s3.hashCode());
String s4 = "hi";
System.out.println(s4.hashCode());

//342155
//332911
~~~

값은 동일한데 hashCode의 값은 다른 것을 볼 수 있다.

이 처럼, 같은 String 타입의 같은 값인데 hashCode가 동일하지 않는 것을 볼 수가 있다. 

결론을 말하면, String을 리터럴("hello" || "hi") 방식으로 초기화하게 되면 해당 String 객체는 Heap 영역에 생성되는 것이 아니라 Heap 영역 내에 String Constant Pool에 생성되게 되는 것이다. 또한, 같은 리터럴("hello"와 "hello" 처럼 값 동일)을 초기화 하게 되면, 새로운 String을 생성하는 것이 아니라 String Constant Pool에서 꺼내와 초기화를 시켜주는 것이다.

> 리터럴 방식으로 초기화할 때 String Constant Pool에 같은 값이 있는 지 확인이 가능한 이유는, 리터럴 방식으로 초기화 시 String.intern() 메소드를 사용하기 때문이다.
>
> 즉, 해당 Pool에 초기화 하고자 하는 문자열이 존재하면 그 문자열의 주소값을 반환하고, 없으면 새로운 주소를 반환하게 되는 것이다.

**즉, 이 String 리터럴 방식이 바로 플라이웨이트 패턴을 적용한 것이다.**

JNI(Java Native Interface)로 구성된 intern()이라는 메소드가 플라이웨이트 팩토리 역할을 하고 있는 것이다. 해당 객체가 있으면 그 객체를 반환해주고 없으면 새로 생성해서 반환해주는 것처럼 말이다.