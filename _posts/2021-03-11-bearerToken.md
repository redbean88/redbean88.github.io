---
date : 2021-03-11
title : bearer Token
categories: [ auth]
---


## Stateful 서버

클라이언트의 요청을 받을 때  마다, 클라이언트의 상태를 계속해서 유지하고, 그 정보를 서비스 제공에 이용
ex) 세션 |유지 서버

## Stateless 서버

상태를 유지하지 않는 서버 ( 확장성이 높아진다)

## 서버 기반 인증의 문제점

- 세션
  - 유저수 증가에 따른 부하 증가(메모리 또는 기타)
- 확장성
  - 트래픽 분산을 위한 확장의 어려움
- CORS
  - 여러 도매인에서 쿠키 관리의 어려움

# Json Web Token(JWT)

- 용도
  - 회원인증
  - 정보교환

- 생김새
```
aaaaaa.bbbbbbb.ccccccccc
(header)(payload)(Signature)
```
- 해더(header)
  - typ : 토큰 타입 (JWT)
  - alg : 해싱 알고리즘 (HMAC SHA256, RSA)
```javascript
{
  "typ" : "JWT"
  "alg" : "HMAC256"
}
```

```javascript

const header = {
  "typ" : "JWT"
  "alg" : "HMAC256"
}

//encode to base64
const encodeHeader = new Buffer(JSON.stringify(header))
                                .toString('base64')
                                .replace("=",''); //패딩 제거

```

- 정보(payload)
  - 클레임
    - 정보의 한 `조각`을 클레임(claim)이라고 부름(name/value)
    - 등록된(registered), 공개(public), 비공개(private) 으로 구분
  - 등록된(registered) 클레임
    - 이미 정해진 클레임
    - 모두 선택적(optional)
    - |클레임명|상세내용|
      |:--|:--|
      |iss|토큰발급자|
      |sub|토큰제목|
      |aud|토큰대상자|
      |exp|토큰 만료시간(numbericDate)|
      |nbf|토큰 활성시간(Not Before)|
      |iat|토큰이 발급된 시간(age파악가능)|
      |jti|JWT 고유 식별자|
  - 공개(public)클레임
    - 충돌 방지된 이름(URI 형식의 이름을 가짐)
    ```javascript
    {
      "https://linkHub.com" : true
    }
    ```
  - 비공개(private) 클레임
    - 클라이언트와 서버의 형희하에 사용되는 클레임 이름
    - 충돌 가능성이 있음
    ```javascript
    {
      "username" : "test"
    }
    ```

- 예제

```javascript
{
  "iss" : "LinkHub.com",
  "exp" : "1485300000000",
  "https://linkHub.com" : true,
  "username" : "test"

}
```

- 서명(Signature)
  - 해더의 인코딩값과, 정보의 인코딩 값을 합친후 비밀키로 해쉬를 생성
  - hex => base64 인코딩
  ```
  HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload) , secret)
  ```

  ```javascript

  const crypto = require('crypto');
  const Signature = crypto.createHmac('sha256', 'secret')
                                .update(encodeHeader + '.' + encodepayload)
                                .digest('base64')
                                .replace("=",''); //패딩 제거

  ```

  - 토큰 완성
  
