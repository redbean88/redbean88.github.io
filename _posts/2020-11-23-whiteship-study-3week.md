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

> 산술 연산자

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

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/Arithmetic?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>
