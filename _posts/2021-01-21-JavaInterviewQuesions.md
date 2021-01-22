---
title : Java_Interview_Questions
date : 2021-01-21
categories : interview
---

> 아래 내용은 해당 페이지를 번역한 내용입니다.   
[Java Interview Questions](https://arc.dev/interview/java-interview-questions-and-answers)

# 자바 개발자 인터뷰에서 물어보는 질문 37가지

## 1. String, StringBuffer , StringBuilder 간의 차이를 설명하시오

__String__ 은 (기본적으로) 불변 객체이기 때문에 수정한다고 하여도 해당 객체가 변경 되는 것이 아닌 새로운 객체로 `변경` 됩니다. 그렇기에 구버전 jdk의 경우, __StringBuffer__ 를 사용하여 해당 문제를 해결하였습니다. 하지만 __StringBuffer__ 의 경우, 동기화에 따른 추가 비용이 발생하기 때문에, `동기화` 비용이 발생하지 않는 __StringBuilder__ 를 사용하는 것을 권장하고 있습니다.

### 요약
1. __String__ 불변객체이므로 수정에 따른 비용 발생
1. __StringBuffer__ 로 해당 현상을 해결하지만, 동기화 비용 발생
1. __StringBuilder__ 는 동기화 처리 비용이 발생하지 않기에 __StringBuilder__ 사용을 권장

### 추가
1. 최근 jdk의 경우, __String__ 도 수정시, 내부적으로 빌더를 통한 변경이 이루어짐

## 2. CLI에서 classpath 를 여러개 설저하는 방법은 설명하시오
```
java -cp /dev/myapp.jar:/dev/mydependency.jar com.codementor.MyApp
```

## 3.  final, finalize, finally 간의 차이를 설명하시오

__final__ 는 클래스의 경우, `오버라이드나 재정의를 할수 없게` 만들며, 필드의 경우, `수정을 불가`하게 만듭니다.
__finalize__ 는 `GC(가비지 콜렉터)가 실행될때`, 실행되는 메서드로 해당 객체가 사용하는 메모리가 정리될때, `수행해야 되는 프로세스를 정의`할 수 있습니다.
__finally__ 는 try/catch 문에 사용하여, `반드시` 실행되어야 하는 프로세스를 정의할 수 있습니다.

### 4 GC(가비지 컬렉터)의 메모리 부족을 예방하는 방법을 설명하시오

__