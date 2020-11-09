## HTTP1.1 vs HTTP 2

### HTTP 1.1

HTTP 1.1은 기본적으로 하나의 연결당 하나의 요청과 응답을 처리하기 때문에 **동시 전송 문제와 다수의 리소스를 처리하**는 것에 대해 **속도와 성능 이슈**를 가지고 있다.

HTTP 1.1의 대표적인 문제점은 HOL Blocking, RTT 증가, 헤비한 Header를 가지고 있다.

**HOL(Head Of Line) Blocking - 특정 응답 지연**

- 하나의 응답이 지연될 시 해당 응답이 완료될 때까지 다음 요청은 무한 대기 상태이다.

**RTT(Round Trip Time) 증가**

- 하나의 Connection 당 하나의 요청을 처리하므로 요청별로 Connection을 만들어야 한다.
- 이로 인해, **3-way HandShake가 반복적으로 일어나게 되며 불필요한 RTT 증가와 네트워크 지연을 초래**하게 된다.

**헤비한 Header 구조**

- HTTP 1.1 헤더에는 많은 메타 정보들이 저장되는데, 사용자가 방문한 웹페이지는 **다수의 HTTP 요청**이 발생하게 된다.
- 이런 경우, 매 요청마다 **중복된 헤더 값을 전송**하게 되고, 해당 도메인에 설정된 **cookie 정보도 매 요청 시 헤더에 포함**되므로 **전송하려는 값보다 헤더 값이 더 클 수 있다.**



### HTTP 2.0

HTTP 2.0은 Multiplexed Streams, Stream Prioritization, Server Push, Header Compression을 사용하여 성능을 향상시켰다.

**Multiplexed Streams**

- 한 커넥션에 여러 개의 메시지를 주고 받을 수 있다.

**Stream Prioritization**

- 요청 리소스간의 의존관계(우선순위)를 설정할 수 있다.
- Ex. 클라이언트가 요청한 HTML 문서에 CSS 파일 1개와 IMAGE 파일 2개가 존재하는데, 만약 IMAGE 파일보다 CSS 파일의 수신이 늦어지는 경우 브라우저 렌더링이 늦어질 수가 있는데, 이 경우 리소스간의 우선순위를 정함으로써 해결이 가능하다.

**Server Push**

- HTML 문서 상에 필요한 리소스를 클라이언트의 요청이 있어야지만 보낼 수 있다.
- 클라이언트가 HTML 문서를 요청할 시 해당 HTML에 여러 개의 리소스(CSS, IMAGE)가 포합될 경우 HTTP 1.1과 HTTP 2.0의 차이가 존재한다.
  - HTTP 1.1 : HTML 문서를 해석하기 위해 필요한 리소스를 재요청할 수 있다.
  - HTTP 2.0 : HTML 문서를 해석하기 위한 리소스를 재요청하지 않고 Server Push 기법을 활용하여 한방에 해석이 가능하다.

**Header Compression**

- Header 정보를 **HPACK 압축 방식**을 이용하여 압축 후 전송한다.
- Ex. 클라이언트가 두 번의 요청을 보낸다고 가정할 때 HTTP 1.1과 HTTP 2.0의 차이를 살펴보자
  - HTTP 1.1 : 두 개의 요청 Header에 중복값이 존재하더라도 그냥 중복 전송을 진행한다.
  - HTTP 2.0 : **Header에 중복 값이 존재**할 시 **static/Dynamic Header Table 개념**을 이용해 중복 Header를 검출 후 중복된 Header는 index 값만 전송하고, **중복되지 않는 정보 값**은 **Huffman Encoding 기법**으로 인코딩하여 전송하는 것이다.

<img src="https://user-images.githubusercontent.com/40616436/98551542-c484fa80-22e0-11eb-9ee2-c76d410d4a1c.png" alt="image" style="zoom:50%;" />

### 쉬운 그림으로 보는 HTTP 1.1과 HTTP 2.0

<img src="https://user-images.githubusercontent.com/40616436/98551640-e41c2300-22e0-11eb-808c-fb594a1d3957.png" alt="image" style="zoom:50%;" />

**HTTP 1.x 버전 대신 HTTP 2.0을 사용해도 응답속도가 15%~50% 정도 향상된다.**