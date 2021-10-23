---
title : Java_Interview_Questions
date : 2021-01-21 00:00:00 +0000
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

## 4. GC(가비지 컬렉터)가 메모리 부족을 예방하는 방법을 설명하시오

가비지 컬렉터(GC)는 함수 스코프를 벗어나서 더이상 메모리에서 사용하지 않는 객체를 초기화합니다.
때문에 어플리케이션에서 사용하는 대량의 객체를 위와 같은 방식으로 처리하여, 메모리부족(OutOfMemory)를 방지 합니다.

## 5. ClassNotFoundException 와 NoClassDefFoundError 의 차아점은 무엇인가요?

`ClassNotFoundException` :   
클래스 로더가 클래스 패스에서 해당 클래스를 못 찾으면 발생한다. 이 에러가 발생하면 기본적으로 클래스 패스와 그 패스에 해당 클래스가 존재하는지 확인해야한다.

`NoClassDefFoundError` :
이것이 발생하면 이유를 찾기가 꽤나 골치아퍼 지는데, 이것은 컴파일타임때 요구되는 클래스가 존재하지만 
런타임때 클래스들이 바뀌거나, 제거되거나, 클래스의 `스태틱 초기화가 예외`를 던지면 이것이 발생한다. 
먼 소리냐하면 클래스패스에 클래스가 존재하더라도, 이 클래스에 요구되는 클래스들중 하나에 문제가 생겼다는 
이야기이다. 따라서 이 클래스와 의존관계를 맺는 모든것들을 살펴봐야한다.



출처: https://hamait.tistory.com/348 [HAMA 블로그]   
출처: https://blog.naver.com/ac7979/140117222243


## 6. String.length ()가 정확 하지 않은 이유는 무엇 입니까?

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

## 7. 아래의 두 double 값을 결과를 신뢰 할 수 없는 이유는 무엇인가요?

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

## 8. 다음코드의 문제점을 설명 하시오
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

## 9. JIT는 무엇입니까?

간단하게 말해 JAVA에서 컴파일러의 수행 시간을 단축하기위한 메커니즘으로
JAVA는 인터프리터언어와 컴파일언어의 특성을 둘 다 가지고 있다.
컴파일러는 바이트코드를 기계어로 바꿀때, JTL 저장소를 생성하여, 반복되는 코드는 해석기를 통하지 않고 저장소에서 가져와 이용한다. 때문에 반복돠는 중복 코드 발생시, 처리속도를 단축할 수 있다.

출처 : https://catch-me-java.tistory.com/11
출처 : https://homoefficio.github.io/2019/01/31/Back-to-the-Essence-Java-%EC%BB%B4%ED%8C%8C%EC%9D%BC%EC%97%90%EC%84%9C-%EC%8B%A4%ED%96%89%EA%B9%8C%EC%A7%80-2/

## 10. 아래 코드에서 0.5가 출력되지 않는 이유를 설명하시오
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

## 11. 아래의 코드에서 System.out::println 가 참조하는 타입은 무엇인가요?

```java
IntStream.range(0, 10).forEach(System.out::println);
```

`IntStream.range(0, 10)`은 `IntStream`을 반환하고 `IntStream`의 foreach 메소드는 `IntConsumer` 아규머트로 받아 , `System.out` 의 `PrintStream`으로 넘셔준다. 때문에 정답은 `IntConsumer` 이다.

## 12. 아래 코드의 문제점은 무엇인가?

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

## 13. 아래의 코드 결과에 대해 설명하시오

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

## 14. 두 문장이 애너그램 여부를 확인하는 함수를 구현하라.(애너그램이란 어순이 바뀐 단어를 말한다. 예: HAVE > VAHE)

```java
public static boolean isAcronymMoreBetter(String s1, String s2) {
    char[] s1Chars = s1.toCharArray();
    char[] s2Chars = s2.toCharArray();
    Arrays.sort(s1Chars);
    Arrays.sort(s2Chars);
    return Arrays.equals(s1Chars, s2Chars);
}
```

## 15. equals() 와 hashCode()의 객체 접근방식의 차이점을 설명 하시오

- equals()  
두 객체의 내용이 같은지 확인하는 Method입니다.
- hashCode()  
두 객체가 같은 객체인지 확인하는 Method입니다.

출처 : https://nesoy.github.io/articles/2018-06/Java-equals-hashcode

## 16. enum은 상속이 가능한가요?

불가능 합니다.

## 17. enum 은 Threadsafe한가요?[check]

기본 구조는 Threadsafe하지만, 추가하는 작업에 메소드를 이용한다면 Threadsafe하지 않습니다.

출처 : https://stackoverflow.com/questions/2531873/how-thread-safe-is-enum-in-java

## 18. JVM이 로컬변수와 객체를 저장하는 방법에 대해 설명하시오

로컬 변수는 stack영역에, 객체는 heap영역에 저장되며 해당 변수는 heap영역의 객체에 대한 참조값입니다.

## 19. 아래 코드의 문제점에 대해 설명하시오

```java
public class Foo {
    public Foo() {
        doSomething();
    }

    public void doSomething() {
        System.out.println("do something acceptable");
    }
}

public class Bar extends Foo {
    public void doSomething() {
        System.out.println("yolo");
        Zoom zoom = new Zoom(this);
    }
}
```
`Bar` 객체가 생성되기전 부모생상자(super()메소드는 명시적으로 정의하지 않아도 자동으로 호출 합니다.) `Foo` 의 생성자를 호출하면, 생성자는 `doSomething()`메소드를 호출 하고 해당 메소드는 오버라이딩된 `Bar` 객체의 `doSomething()`를 호출합니다. 때문에 `new Zoom(this);`의 this는 부모 생성자가 완전히 초기화 되지 않은 객체가 들어오기 때문에 이슈가 발생할 수 있습니다.

## 20. volatile 변수는 언제 사용하나요?

volatile keyword는 Java 변수를 Main Memory에 저장하겠다라는 것을 명시하는 것입니다.
자바에서는 속도향상을 위해, 초기 메모리에서 값을 가져와 CPU 캐시의 저장 후, 해당 값을 각 Thread에서 사용합니다. 때문에 메모리에서 값을 가져와 변경 후 메모리에 값을 변경하기 전에 다른 Thread에서 값을 가져갈 경우, 해당 값을 다를수 있습니다.   
volatile이용하면 항상 메모리 값의 최신화 할 수 있으나, 원자성이 보장되지 않으면, 이슈가 발생할 수 있습니다.   
출처 : https://nesoy.github.io/articles/2018-06/Java-volatile

## 21. synchronized 메소드나 블록을 사용해야 하는 이유가 뭔가요?

Thread 가 사용되고 있고 여러 Thread가 동기화 된 코드 섹션을 거쳐야하는 경우 한 번에 하나만 실행될 수 있습니다. 이것은 공유 변수가 여러 스레드에 의해 업데이트되지 않도록하는 데 사용됩니다.

## 22. HashMap  ConcurrentHashMap

ConcurrentHashMap는 Thread-safe 하며, HashMap는 Thread-safe 하지 않습니다.ConcurrentHashMap는 NULL을 허용하지 않으며, HashMap는 NULL을 허용합니다.

## 23. equals() 와 hashCode() 는 언제 재정의 해야 할까요?

hash-based 컬렉션의 성능 향상을 위해 사용 가능합니다.

출처 : https://nesoy.github.io/articles/2018-06/Java-equals-hashcode

## 24. 서비스랑 무엇인가요?

서비스는 잘 정의되고 독립적이며 다른 서비스의 컨텍스트나 상태에 의존하지 않는 함수입니다.

## 25. System.gc()의 가장 좋은 활용법은 무엇일까요?

`System.gc()` 메모리 누수 분석에 사용하면 좋습니다. 대부분의 프로파일러는 해당 메소드 호출을 통해 메모리 스냅샷을 생성합니다.

## 26. 마커인터페이스란 무엇인가요?

마커인터페이스는 필드나 메서드가 없는 인터페이스를 말합니다. 이러한 마커인터페이스는 JVM에게 특정한 정보를 알리기 위한 용도로 사용되며, Serializable, Clonable, Remote 인터페이스 등이 있습니다.

## 27. 마커인터페이스보다 어노태이션이 좋은 이유는 무엇인가요?

어노테이션을 사용하면 별도의 유형을 만들지 않고도 클래스에 대한 메타 데이터를 소비자에게 전달하는 동일한 목적을 달성 할 수 있습니다. 또한, 프로그래머가 더 정교한 정보를 "소비"하는 클래스에 전달할 수 있습니다.

## 28. checked 와 unchecked exceptions은 무엇이며, 언제 사용하나요?

`checked exception`은 반드시 처리해아 하는 예외 이며, 컴파일러에 의해 처리 됩니다.
`unchecked exception`은 반드시 잡아야 하는 예외는 아니며, 런타임시에 발생 합니다.
회복 가능한 상황일 경우, `checked exception`을 사용하는 것이 좋습니다.

참고 : https://yadon079.github.io/2021/java%20study%20halle/week-09#runtimeexception%EA%B3%BC-re%EA%B0%80-%EC%95%84%EB%8B%8C-%EA%B2%83%EC%9D%98-%EC%B0%A8%EC%9D%B4%EB%8A%94

## 29. int a = 1L; 는 컴파일이 불가능 하고, int b = 0; b += 1L; 컴파일이 가능합니다. 이유가 뭘까요?

`+=`사용시, 직접문은 복합문으로 변경되어 컴파일러는 내부적으로 오토캐스팅을 진행합니다. 때문에 컴파일에러가 발생하지 않습니다. 하지만 단순 `1L`와 같은 직접문의 경우, 오토케스팅이 진행되지 않아 컴파일어에서 타입에러를 발생 시킨다.

## 30. 자바에서 다중상속을 인터페이스에서는 허용하지만, 클래스에서는 허용하지 않는 이유는 무엇인가요?

클래스를 확장하면 모호성 문제가 발생할 수 있습니다. 반면에 인터페이스 측면에서 한 클래스의 단일 메서드 구현은 둘 이상의 인터페이스를 제공 할 수 있습니다.

## 31. 아래 코드에서 인스턴스가 null이지만, `NullPointerException` 이 발생하지 않는 이유는 무엇인가요?

```java
Test t = null;
t.someMethod();


 public static void someMethod() {
  ...
}
```

해당 코드는 인스턴스가 필요없는 정적 메소드이기 때문입니다. 정적 메소드는 인스턴스가 아닌 클래스에 속하기 때문에 null예외를 발생하지 않습니다.

## 32. 아래코드에서 첫번째는 true 두번째는 false가 출력되는 이우는 무엇인가요?

```java
public class Test
{
    public static void main(String[] args)
    {
        Integer a = 1000, b = 1000;
        System.out.println(a == b); //false

        Integer c = 100, d = 100;
        System.out.println(c == d); //true
    }
}
```

해당 현상은 JVM의 캐시때문에 발생하는 현상입니다. JVM은 Integer가 범위(-128에서 127) 내에 있을 경우, 새로운 인스턴스 생성하지 않으며, 기존의 생성된 인스턴스의 참조 값을 이용합니다.
때문에, 위 코드에서 `d`를 위한 참조는 생성되지 않으며, `c`의 참조 값을 이용합니다. 그렇기에 두 번째 출력은 true가 됩니다.

## 33. 두 개의 문장이 아나그램인지 확인하시오

```java
String s1="home";
String s2="mohe";
```

```java
boolean result = new String(Arrays.sort(s1.toCharArray()))
                  .equals(new String(Arrays.sort(s2.toCharArray())));
```

## 34. String("Java Programming")을 반복문과 재귀를 사용하지 않고 반전시키시오.

```java
System.out.println("reverse = " + new StringBuilder(givenString).reverse());
```

## 35. ArrayList를 사용하는 경우와 LinkedList를 사용하는 경우를 논하시오.

ArrayList는 검색시 LinkedList보다 빠르기 때문에 검색이 빈번한 경우, 더 유용하며 `O(1)`
삭제나 삽입 시에는, LinkedList가 더 유리합니다. `O(1)`

## 36. Iterator 와 ListIterator 의 차이점이 무엇인가요?

Iterator는 set,list에 시용가능하며, 단반향이동만 지원하며, ListIterator는 list에만 사용가능하며, 양방향이동이 가능하고 메소드를 통해서 인덱스를 가져올수 있다.

출처 : http://www.tcpschool.com/java/java_collectionFramework_iterator

## 37. 제네릭의 사용 이점이 뭔가요?

컴파일 타임에 강력한 타입체크 기능을 지원하여, 런타임시 오류보다 더 쉬운 오류체크를 가능하도록 합니다.

참고 : https://namjackson.tistory.com/18
