---
title : "자바스터디 6주차"
date : 2020-12-20
categories : study
---

# GOAL
> 자바의 상속에 대해 학습하세요.

# 학습할 것 (필수)
### 자바 상속의 특징
### super 키워드
### 메소드 오버라이딩
Method overriding is one of the ways in which Java supports Runtime Polymorphism. 
메소드 오버라이딩은 자바에서 다형성을 __런타임__ 시 지원하는 한가지 방법이다.
### 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
Dynamic method dispatch is the mechanism by which a call to an overridden method is resolved at run time, rather than compile time.
다이다믹 메소드 디스패치는 오버라이딩된 메소드를 처리할때 컴파일시 처리가 아닌 __런타임__ 시 처리하는 메커니즘이다.

When an overridden method is called through a superclass reference,
부모클래스를 통해 오버라이딩 된 메소드가 호출 될 경우(자식 클래스를 부모클래스로 형 변환하여, 오버라이딩된 메소드를 호출 할 경우)
Java determines which version(superclass/subclasses) of that method is to be executed based upon the type of the object being referred to at the time the call occurs.
자바에서는 호출할 메소드 실행시, 호출 당시의 참조된(가르키는) 객체에 기반하여 해당 메소드를 결정 하게된다.
Thus, this determination is made at run time
다시말해, __런타임__ 시에, 어떤 객체에 어떤 메소드를 실행 할지가 결정된다.

At run-time,
런타임시,
it depends on the type of the object being referred to (not the type of the reference variable) that determines which version of an overridden method will be executed A superclass reference variable can refer to a subclass object.
참조된 객체의 타입에 의존하며(레퍼런스 변수의 타입이 아니다. 어렵다면 아래 예제를 참고), 해당 객체는 부모클래스가 참조 __할 수있는__ 자식클래스의 실행될 오버라이딩된 메소드를 결정하게 된다.

그것은 객체의 호출되는 방식에 따르는 그것은 오버히든된 방법타입에   (호출되는데 그 결정은 오벌히든된 방법은 버전이 실행된다 부모클래스가 자시클래스를 호출할 수 있는 

This is also known as upcasting. Java uses this fact to resolve calls to overridden methods at run time.
### 추상 클래스
### final 키워드
### Object 클래스

마감일시
2020년 12월 26일 토요일 오후 1시까지.
