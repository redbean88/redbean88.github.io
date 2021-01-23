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

### 4. GC(가비지 컬렉터)가 메모리 부족을 예방하는 방법을 설명하시오

가비지 컬렉터(GC)는 함수 스코프를 벗어나서 더이상 메모리에서 사용하지 않는 객체를 초기화합니다.
때문에 어플리케이션에서 사용하는 대량의 객체를 위와 같은 방식으로 처리하여, 메모리부족(OutOfMemory)를 방지 합니다.

### 5. ClassNotFoundException 와 NoClassDefFoundError 의 차아점은 무엇인가요?

`ClassNotFoundException` :   
클래스 로더가 클래스 패스에서 해당 클래스를 못 찾으면 발생한다. 이 에러가 발생하면 기본적으로 클래스 패스와 그 패스에 해당 클래스가 존재하는지 확인해야한다.

`NoClassDefFoundError` :
이것이 발생하면 이유를 찾기가 꽤나 골치아퍼 지는데, 이것은 컴파일타임때 요구되는 클래스가 존재하지만 
런타임때 클래스들이 바뀌거나, 제거되거나, 클래스의 `스태틱 초기화가 예외`를 던지면 이것이 발생한다. 
먼 소리냐하면 클래스패스에 클래스가 존재하더라도, 이 클래스에 요구되는 클래스들중 하나에 문제가 생겼다는 
이야기이다. 따라서 이 클래스와 의존관계를 맺는 모든것들을 살펴봐야한다.



출처: https://hamait.tistory.com/348 [HAMA 블로그]   
출처: https://blog.naver.com/ac7979/140117222243


### 6. String.length ()가 정확 하지 않은 이유는 무엇 입니까?

문자열 내의 문자 수만 고려하므로 정확하지 않습니다. 즉, BMP (Basic Multilingual Plane)라고 부르는 것 이외의 코드 포인트, 즉 값 U+10000이상을 가진 코드 포인트(이집트 상형문자 등의 특수문자)를 설명하지 못합니다 .

그 이유는 Java가 처음 정의되었을 때 목표 중 하나는 모든 텍스트를 유니 코드로 처리하는 것이 었습니다. 그러나 현재 상용 유니 코드외의 BMP 외부의 코드 포인트(특수문자)를 정의하지 않았습니다. 유니 코드가 이러한 코드 포인트를 정의 할 때까지 기다리며 변경하기에는 너무 오래 걸렸습니다.

자바에서는 , BMP 외부의 코드 포인트는 Java에서 서로 게이트 쌍 이라고하는 `두개의 문자`로 표시됩니다 . 기술적으로 Java의 문자는 UTF-16 코드 단위입니다.

문자열 내의 실제 문자 수, 즉 코드 포인트 수를 계산하는 올바른 방법은 다음 중 하나입니다.

```java
someString.codePointCount(0, someString.length())
```
또는 Java 8 이상 :
```java
someString.codePoints().count()
```

### 7. 아래의 두 double 값을 결과를 신뢰 할 수 없는 이유는 무엇인가요?

```java
d1 == d2
```

자바언어에서는 숫자가 아니면 원시타입이 아니기 때문에 동등연산사(==) 사용이 올바른 값을 반환하지 않는다. 때문에 아래와 같은 방법으로 비교해야한다.

> 예제

```java
final double d1 = Double.NaN;
final double d2 = Double.NaN;

System.out.println(d1 == d2);               //false
System.out.println(Double.compare(d1,d2));  //0
```

출처 : https://wikibook.co.kr/article/java-coding-with-pmd-badcomparison/

### 8. 다음코드의 문제점을 설명 하시오
```java
final byte[] bytes = someString.getBytes();
```

두 가지 문제점이 있다.
1. JVM은 각 OS의 기본 문자셋(charset)에 의존 한다
2. 해당 기본 문자셋이 모든 문자를 처리 할수 있다는 보장이 없다.(특수문자 등)

2번 문제가 되지 않는다고 한다면, 1번의 경우 windows의 기본 기본 문자셋은 `CP1252` 이며 리눅스는 `UTF-8`이다. 때문에, `é`와 같은 문자의 경우, OS에 따른 처리가 다를 수 있다.
때문에 문자셋을 정하는 코드를 사용하는 것이 좋다

```java
final byte[] bytes = someString.getBytes(StandardCharsets.UTF_8);
```

#### 9. JIT는 무엇입니까?

간단하게 말해 JAVA에서 컴파일러의 수행 시간을 단축하기위한 메커니즘으로
JAVA는 인터프리터언어와 컴파일언어의 특성을 둘 다 가지고 있다.
컴파일러는 바이트코드를 기계어로 바꿀때, JTL 저장소를 생성하여, 반복되는 코드는 해석기를 통하지 않고 저장소에서 가져와 이용한다. 때문에 반복돠는 중복 코드 발생시, 처리속도를 단축할 수 있다.

출처 : https://catch-me-java.tistory.com/11
출처 : https://homoefficio.github.io/2019/01/31/Back-to-the-Essence-Java-%EC%BB%B4%ED%8C%8C%EC%9D%BC%EC%97%90%EC%84%9C-%EC%8B%A4%ED%96%89%EA%B9%8C%EC%A7%80-2/

#### 10. 아래 코드에서 0.5가 출력되지 않는 이유를 설명하시오
```java
final double d = 1 / 2;

System.out.println(d);
```

JAVA에서는 상위 범위로 오토박싱(정확한 표현이 아닐수도 있습니다.)되며, 애매한 범위보다는 좁은 범위로 해석하게 됩니다. 때문에 위 코드는 double로 표현되어 있어도, int형으로 변환되어 표기됩니다. 따라서, 위 코드는 아래의 코드로 변경이 필요합니다

```java
final double d = 1.0 / 2;

System.out.println(d);
```

또는

```java
final double d = 1 / 2.0;

System.out.println(d);
```

#### 11. 아래의 코드에서 System.out::println 가 참조하는 타입은 무엇인가요?

```java
IntStream.range(0, 10).forEach(System.out::println);
```

`IntStream.range(0, 10)`은 `IntStream`을 반환하고 `IntStream`의 foreach 메소드는 `IntConsumer` 아규머트로 받아 , `System.out` 의 `PrintStream`으로 넘셔준다. 때문에 정답은 `IntConsumer` 이다.

#### 12. 아래 코드의 문제점은 무엇인가?

```java
final Path path = Paths.get(...);

Files.lines(path).forEach(System.out::println);
```

위 코드의 문제점은 Files.lines(path)이 닫히지 않았다는 것이다. `Stream` 은 `BaseStream`을 상속받는데, `BaseStream`은 `AutoCloseable` 상속 받고 있다. 때문에 try/catch 문을 이용하여, 처리해주면 된다.


```java
try (
    final Stream<String> stream = Files.lines(path);
) {
    stream.forEach(System.out::println);
}
```

#### 13. 아래의 코드 결과에 대해 설명하시오

```java
final List<Integer> list = new ArrayList<>();

list.add(1);
list.add(2);
list.add(3);

list.remove(2);

System.out.println(list); //[1,2]
```

리스트의 remove() 메소드는 두가지로 구현되어 있다.
1. remove(int index)
1. remove(Object obj)

JVM은 가장 명확한 표현을 찾는다. 때문에 remove(Object obj)가 아닌 remove(int index)
의 메소드를 실행 되기 때문에, 위와 같은 결과가 나온다. 2를 삭제 하기 위해서는 아래와 같은 코드로 변경 해야 한다.

```java
final List<Integer> list = new ArrayList<>();

list.add(1);
list.add(2);
list.add(3);

list.remove(Integer.valueOf(2));

System.out.println(list);
```

#### 14. 두 문장이 애너그램 여부를 확인하는 함수를 구현하라.(애너그램이란 어순이 바뀐 단어를 말한다. 예: HAVE > VAHE)

```java
public static boolean isAcronymMoreBetter(String s1, String s2) {
    char[] s1Chars = s1.toCharArray();
    char[] s2Chars = s2.toCharArray();
    Arrays.sort(s1Chars);
    Arrays.sort(s2Chars);
    return Arrays.equals(s1Chars, s2Chars);
}
```

#### 15. equals() 와 hashCode()의 객체 접근방식의 차이점을 설명 하시오

- equals()  
두 객체의 내용이 같은지 확인하는 Method입니다.
- hashCode()  
두 객체가 같은 객체인지 확인하는 Method입니다.

출처 : https://nesoy.github.io/articles/2018-06/Java-equals-hashcode

#### 16. enum은 상속이 가능한가요?

불가능 합니다.

#### 17. enum 은 Threadsafe한가요?

기본 구조는 Threadsafe하지만, 추가하는 작업에 메소드를 이용한다면 Threadsafe하지 않습니다.

출처 : https://stackoverflow.com/questions/2531873/how-thread-safe-is-enum-in-java