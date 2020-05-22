## Wrapper Class 캐싱

Wrapper Class는 Primitive type을 Reference Type으로 사용하기 위해 만든 클래스이다. Primitive Type의 값을 Wrapper Class의 인스턴스로 변환하거나 그 반대로 변환되는 작업이 있는데, 이를 박싱 과정이라고 한다.

- Boxing : Primitive Type -> Reference Type
- UnBoxing : Reference Type -> Primitive Type

그런데 Java 1.5 이상 부터는 이런 과정을 자동으로 지원해주고 있다.

~~~java
Integer num1 = 1;	//Primitive Type을 Reference Type으로 Auto Boxing
Integer num1 = new Integer(1);

int num2 = num1;	//Reference Type을 Primitive Type으로 Auto UnBoxing
int num2 = num1.intValue();
~~~

---

근데 Integer라는 Wrapper Class의 내부를 살펴보던 중 신기한 점을 발견할 수 있었다.

그것은 바로 Integer 내부에 IntegerCache라는 내부 클래스이다.

~~~java
private static class IntegerCache {
  static final int low = -128;
  static final int high;
  static final Integer[] cache;
  static Integer[] archivedCache;

  static {
    // high value may be configured by property
    int h = 127;
    String integerCacheHighPropValue =
      VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
    if (integerCacheHighPropValue != null) {
      try {
        h = Math.max(parseInt(integerCacheHighPropValue), 127);
        // Maximum array size is Integer.MAX_VALUE
        h = Math.min(h, Integer.MAX_VALUE - (-low) -1);
      } catch( NumberFormatException nfe) {
        // If the property cannot be parsed into an int, ignore it.
      }
    }
    high = h;

    // Load IntegerCache.archivedCache from archive, if possible
    VM.initializeFromArchive(IntegerCache.class);
    int size = (high - low) + 1;

    // Use the archived cache if it exists and is large enough
    if (archivedCache == null || size > archivedCache.length) {
      Integer[] c = new Integer[size];
      int j = low;
      for(int i = 0; i < c.length; i++) {
        c[i] = new Integer(j++);
      }
      archivedCache = c;
    }
    cache = archivedCache;
    // range [-128, 127] must be interned (JLS7 5.1.7)
    assert IntegerCache.high >= 127;
  }

  private IntegerCache() {}
}
~~~



브릿지 패턴, 어댑터 패턴, 전략 패턴



