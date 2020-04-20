## Proxy Pattern vs Decorator Pattern
- 두 패턴 모두 기존에 존재하는 객체의 동일한 인터페이스를 이용해서 다양한 행동을 추가하기 위한 의도를 가지고 있다.
  - Ex. MyClass라는 객체에 doAction()이라는 메소드가 있을 시, 다른 객체에서도 동일한 시그니처의 doAction() 메소드를 호출하여 
  MyClass의 doAction()을 실행하는 동시에 다른 행동을 추가해주는 것이다.
- 이런 측면으로 보면, 두 패턴이 동일하게 보일 수 있지만 '의도'에는 미묘한 차이가 있다.
- **Proxy는 추가적인 '컨트롤'을 제공하는 것이고, Decorator는 추가적인 '기능'을 제공하는 것이다.**

**Decorator**
  - MyClass 객체의 doAction()이 'mesung'을 출력하는 것이라면, 데코레이터 역할의 MySubClass 객체의 doAction()에서는 
  MyClass 객체의 doAction()을 호출하여 "mesung"을 출력하는 동시에 그 위 아래로 추가적인 기능("open", "close")을 처리해주는 것이다.
  - **즉, 주요 기능에 부가적인 기능을 추가해주는 것이 데코레이터의 의도이다.**
  
**Proxy**
  - MyClass 객체의 doAction()이 "mesung"을 현재 로컬에 출력하는 것이라면, Proxy 역할의 MySubClass 객체의 
  doAction()는 "mesung"을 Remote 컴퓨터로 보내서 출력해주는 역할을 하는 것이다.
  - 즉, 주요 기능에 추부가적인 기능을 추가해주는 것이 아니라, **주요 기능을 다양한 방식으로 컨트롤 해주는 역할을 하는 것이다.**
    - 외부로 보내든지, laze initailization을 한다든지, cache 된 것을 사용한다든지..!
    
 ![image](https://user-images.githubusercontent.com/40616436/79535434-6b414980-80b8-11ea-9568-a142284ab24d.png)

- 그림으로 보듯이 서로 구조는 같다. 다만 의도에 따라 나뉜다는 점을 명시해야 한다.

https://hamait.tistory.com/868
