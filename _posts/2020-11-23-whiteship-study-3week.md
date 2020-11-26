---
title : "자바스터디 3주차"
date : 2020-11-23
categories : study
---

# GOAL
> 자바가 제공하는 다양한 연산자를 학습하세요.

1. 학습할 것
1. 산술 연산자
1. 비트 연산자
1. 관계 연산자
1. 논리 연산자
2. instanceof
3. assignment(=) operator1
4. 화살표(->) 연산자
5. 3항 연산자
6. 연산자 우선 순위
7. (optional) Java 13. switch 연산자

### 산술 연산자

|산술 연산자|설명|
|:---:|---|
|+|연산자를 기준으로 우항과 좌항의 합연산을 수행합니다.|
|-|연산자를 기준으로 우항과 좌항의 차연산을 수행합니다|
|*|연산자를 기준으로 우항과 좌항의 곱연산을 수행합니다|
|/|연산자를 기준으로 좌항을 우항으로 나누기 연산을 수행합니다. 우항이 0일 경우, 예외를 발생 합니다.|
|%|연산자를 기준으로 좌항을 우항으로 나눈 나머지 연산을 수행합니다. 우항이 0일 경우, 예외를 발생 합니다.|

> 나눗셈 연산
단순하게 나머지 연산만 수행 했을 경우, 소수점표기가 되지 않으며, 소수점 연산이 필요한경우, 
1. 좌,우항중 하나를 실수로 표기한후, double로 받아 표기.
2. java.math의 BigDecimal 이용 (추천)
[관련 링크](https://mainia.tistory.com/2020)

> 숫자 승격
자바의 레퍼선스 객체의 오토박싱과 유사한 처리 상세내용은 링크를 확인해 보자
[숫자승격](https://kils-log-of-develop.tistory.com/336)

<iframe height="400px" width="100%" src="https://repl.it/@redbean1/Arithmetic?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

### 비트연산자

|비트 연산자|설명|
|:---:|---|
|&|연산자를 기준으로 우항과 좌항의 대응되는 비트가 모두 1이면 1을 반환. (AND 연산)|
|\\||연산자를 기준으로 우항과 좌항의 대응되는 비트 중에서 하나라도 1이면 1을 반환. (OR 연산)|
|^|연산자를 기준으로 우항과 좌항의 대응되는 비트가 서로 다르면 1을 반환. (XOR 연산)|
|~|피연산자의 비트를 1이면 0, 0이면 1로 반전. (NOT 연산, 1의 보수)|
|<<|연산자를 기준으로 우항의 수만큼 좌항의 비트를 모두 왼쪽으로 이동. (Left shift 연산)|
|>>|연산자를 기준으로 우항의 수만큼 좌항의 비트를 모두 오른쪽으로 이동. (Right shift 연산)|

> 사용이유
비트연산의 경우 , 연산 속도가 빠름

<iframe height="400px" width="100%" src="https://repl.it/@redbean1/bit?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>
