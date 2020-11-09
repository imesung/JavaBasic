## HTTP 1.0 vs HTTP 1.1

### HTTP 1.0

HTTP 1.0은 단순히 open/operation/close 방식을 취하고 있는 단순한 구조이다.

TCP Connection당 하나의 URL만 fetch하며, 매번 request/response가 끝나면 연결이 끊기므로 필요할 때마다 다시 연결해야한다는 단점이 있어 속도가 현저히 느리다. URL 크기가 작고 한번에 가져올 수 있는 데이터의 양이 제한되어 있다.

이 처럼, 반복되는 disconnect 현상으로 인해 한 서버에 계속해서 접속을 시도하게 되면 과부하가 걸리고 성능이 떨어지게 되는데 이런 문제를 해결하기 위해서 등장한 것이 **HTTP 1.1**이다.



### HTTP 1.1

HTTP의 인터넷에서 impact를 줄이고 cache를 두어 인터넷 프로토콜 수행이 빠르게 될 수 있도록 성능을 향상하고 있는 것이다.

HTTP 1.1은 multiple request에 대한 처리가 가능하고 request/response가 파이프라인 방식으로 진행이 가능하다.

<img src="https://user-images.githubusercontent.com/40616436/98549643-6fe08000-22de-11eb-839c-1f497c552637.png" alt="image" style="zoom:50%;" />

