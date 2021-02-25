---
title : "자바스터디 14주차"
date : 2021-02-25
categories : study
---

# GOAL
자바의 제네릭에 대해 학습하세요.

# 학습할 것 (필수)

- 제네릭 사용법
- 제네릭 주요 개념 (바운디드 타입, 와일드 카드)
- 제네릭 메소드 만들기
- Erasure


### 제네릭 사용법

#### 제네릭

소프트웨어 프로젝트에서 버그 발생은 당연한 사실입니다. 신중한 계획, 프로그래밍 및 테스트는 버그 발생률을 줄이는 데 도움이 될 수 있지만 코드 어딘가에는 버그가 존재할 수 있습니다. 이것은 새로운 기능이 도입되고 코드 기반이 크기와 복잡성이 증가함에 따라 특히 그 빈도가 능가합니다.

다행히도 일부 버그는 다른 버그보다 감지하기 쉽습니다. 예를 들어 컴파일 타임 버그는 초기에 발견 할 수 있습니다. 컴파일러의 오류 메시지를 사용하여 문제가 무엇인지 파악하고 즉시 수정할 수 있습니다. 그러나 런타임 버그는 훨씬 더 문제가 될 수 있습니다. 런타입 버그는 바로 발견하기 어려우며, 또한 문제의 실제 원인을 발견하기 어려울 수도 있습니다.

제네릭은 이러한 에러를 런타임이 아닌 __컴파일__ 시 검증할 수 있도록 해주며 그로인한 안정성을 추구할 수 있습니다.(JDK 1.5버전 부터 사용 가능합니다.)

#### 사용하는 이유

제네릭은 클래스, 인터페이스 및 메서드를 정의 할 때 (클래스 및 인터페이스의)매개 변수의 타입을 정의 할수 있습니다. 일반적으로 메서드에 사용하는 파라미터 형식으로 매개변수 타입을 정의 할 수 있습니다.

제네릭을 사용하는 코드는 제네릭이 아닌 코드에 비해 많은 이점이 있습니다.

- 컴파일 타임시, 강력한 타입 체크  
 Java 컴파일러는 강력한 타입 검사를 수행하며 코드가 타입 안전성(type-safety)을 위반하면 오류를 발생시킵니다. 컴파일 타임 오류를 수정하는 것은 찾기 어려울 수있는 런타임 오류를 수정하는 것보다 쉽습니다.

- 형변환 제거  
제네릭을 사용하지 않는 다음코드는 형변환이 필요합니다
```java
List list = new ArrayList();
list.add("hello");
String s = (String) list.get(0);
```
하지만 제네릭을 사용시 형변환이 필요 없습니다.
```java
List<String> list = new ArrayList<String>();
list.add("hello");
String s = list.get(0);   // 형 변환 필요 없음
```
- 프로그래머가 일반 알고리즘을 구현할 수 있도록합니다.
프로그래머는 제네릭을 사용하여 다양한 유형의 컬렉션에서 작동하고 사용자 정의 할 수 있으며 타입에 안전(type-safety)하고 읽기 쉬운 제네릭 알고리즘을 구현할 수 있습니다.

#### 선언
class Box\<T1,T2,...\>

|명칭|내용|
|:--|:--|
|T1,T2,...|타입 변수 또는 타입 매개변수|
|Box|원시타입|

#### 비교

위의 선언 방식을 이용하여 제네릭이 적용된 클래스와 비적용된 클래스를 비교해 봅니다.

> 제네릭 비적용

```java
public class NoGenericBox {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}
```

> 바이트코드

```
public class week14.NoGenericBox {
  public week14.NoGenericBox();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void set(java.lang.Object);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field object:Ljava/lang/Object;
       5: return

  public java.lang.Object get();
    Code:
       0: aload_0
       1: getfield      #2                  // Field object:Ljava/lang/Object;
       4: areturn
}
```

> 제네릭 적용

```java
public class GenericBox<T> {
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
```

> 바이트코드

```
public class week14.GenericBox<T> {
  public week14.GenericBox();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void set(T);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field t:Ljava/lang/Object;
       5: return

  public T get();
    Code:
       0: aload_0
       1: getfield      #2                  // Field t:Ljava/lang/Object;
       4: areturn
}

```

위 코드 보면 제네릭을 사용시 putfield,getfield 함수를 이용하고 있습니다. 두 함수는 참조된 필드의 타입을 확인하며,  해당 타입의 필드가 static일 경우, IncompatibleClassChangeError에러를 발생 시킵니다. 또한 final일 경우, 초기화를 반드시 진행 해야하며, 그렇지 않으면 IllegalAccessError에러를 발생 시킵니다.

위 코드를 확인하면, Object는 모두 T로 변환됩니다. 이러한 타입 변수는 원시 타입을 포함한 원시타입 이외의 클래스, 인터페이스, 배열 등올 설정가능합니다.

#### 타입변수 명명규칙

규칙에 따라 유형 매개 변수 이름은 단일 대문자입니다. 이것은 이미 알고 있는 변수 명명 규칙 과는 뚜렷한 대조를 이루며 그럴만 한 이유가 있습니다. 이 규칙이 없으면 유형 변수와 일반 클래스 또는 인터페이스 이름의 차이를 구분하기 어려울 것입니다.

가장 일반적으로 사용되는 유형 매개 변수 이름은 다음과 같습니다.

|변수|내용|
|:--|:--|
|E|요소 (Java Collections Framework에서 광범위하게 사용됨)|
|K|키|
|N|숫자|
|T|타입|
|V|값|
|S, U, V 등|2, 3, 4번때 타입|


#### 제네릭 유형 호출 및 인스턴스화

코드에서 위 예시의 BOX 클래스를 참조하여면, T에 Integer와 같은 구체적인 값을 정의하는 제네릭 타입 호출을 수행하여야 합니다.  

예) Box \<Integer> integerBox;  

제네릭 타입 선언이 일반 메소드 선언과 유사하다고 생각 할수 있으나, 일수를 메서드에 전달하는 대신 타입 아규먼트(type-argument)를 Box 클래스에 전달합니다.


_타입 파라미터(Type Parameter)와 타입 아규먼트(type-Argument)의 이해_  
많은 개발자들이 타입 파라미터와 타입 아규먼드를 용어를 같은 의미로 사용하지만 두 용어는 동일하지 않습니다. 타입 파라미터(Type Parameter)는 형식이 확정되지 않은 형태를 말하며, 타입 아규먼트(type-Argument)은 형식이 정의된 형태를 말합니다. 예를들면 foo\<T>의 T는 타입 파라미터라고 하며, foo\<String>의 String은 타입 아규먼트(type-Argument)라고 합니다.

|변수|내용|
|:--|:--|
|Type parameter| 단순한 자리 표시자 예)"\<T>"|
|Type argument| 선언시 넘겨주는 타입 파라미터  예), "\<String>"|
|Parameterized type (generic)| 규격화된 타입 파라미터 예) "List\<T>"|
|Parameterized type (concrete)| 선언된 타입의 규격화된 파라미터  예) "List\<String>"|

참고: https://stackoverflow.com/questions/12551674/what-is-meant-by-parameterized-type

다른 변수 선언과 마찬가지로 위 코드는 실제로 새 Box 객체를 생성하지 않습니다. 단순히 integerBox 가 "Integer 타입의 Box"에 대한 참조를 보유 할 것이라고 선언하는 것입니다. 이는 Box \<Integer> 를 읽는 방법입니다.

제네릭 유형의 호출을 일반적으로 매개 변수화된 타입( parameterized type)이라고 합니다.

이 클래스를 인스턴스화하려면 평소처럼 new 키워드를 사용 하되 클래스 이름과 괄호 사이에 \<Integer> 를 넣으면 됩니다.

예) Box \<Integer> integerBox = new Box \<Integer>();

#### 다이아몬드

Java SE 7 이상에서는 컴파일러가 문맥에서 타입 아규먼트(type-Argument)를 결정하거나 추론 할 수 있다면 한 일반 클래스의 생성자를 호출하는 데 필요한  타입 아규먼트를 빈  타입 아규먼트 (\<>) 세트로 바꿀 수 있습니다. 이 꺾쇠 괄호 쌍 \<>은 비공식적으로 `다이아몬드` 라고 합니다 . 예를 들어 다음 문을 사용하여 Box <Integer> 의 인스턴스를 만들 수 있습니다 .

예) Box <Integer> integerBox = new Box<>();

#### 다중 타입 파라미터

제네릭 클래스는 여러 유형 매개 변수를 가질 수 있습니다


```java
public interface Pair<K,V> {
    public K getKey();
    public V getValue();
}

public class OrderedPair<K,V> implements Pair<K,V> {

    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
}

```

> 바이트코드

```
public interface week14.Pair<K, V> {
  public abstract K getKey();

  public abstract V getValue();
}

public class week14.OrderedPair<K, V> implements week14.Pair<K, V> {
  public week14.OrderedPair(K, V);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #2                  // Field key:Ljava/lang/Object;
       9: aload_0
      10: aload_2
      11: putfield      #3                  // Field value:Ljava/lang/Object;
      14: return

  public K getKey();
    Code:
       0: aload_0
       1: getfield      #2                  // Field key:Ljava/lang/Object;
       4: areturn

  public V getValue();
    Code:
       0: aload_0
       1: getfield      #3                  // Field value:Ljava/lang/Object;
       4: areturn
}

```

위에서 언급한 내용처럼 다중의 타입으로 이용가능하며 아래와 같이 유연하게 사용가능합니다.


```java
public class TestMain {
    public static void main(String[] args) {
        OrderedPair<String, Integer> p1 = new OrderedPair<>("Even",8);
        OrderedPair<String, String> p2 = new OrderedPair<>("Hello","world");
    }
}
```

> 바이트코드

```
public class week14.TestMain {
  public week14.TestMain();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class week14/OrderedPair
       3: dup
       4: ldc           #3                  // String Even
       6: bipush        8
       8: invokestatic  #4                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      11: invokespecial #5                  // Method week14/OrderedPair."<init>":(Ljava/lang/Object;Ljava/lang/Object;)V
      14: astore_1
      15: new           #2                  // class week14/OrderedPair
      18: dup
      19: ldc           #6                  // String Hello
      21: ldc           #7                  // String world
      23: invokespecial #5                  // Method week14/OrderedPair."<init>":(Ljava/lang/Object;Ljava/lang/Object;)V
      26: astore_2
      27: return
}
```

new OrderedPair \<String, Integer> 코드는 K 를 String으로 , V 를 Integer 로 인스턴스화 합니다. 따라서 OrderedPair 생성자 의 매개 변수 유형은 각각 String 및 Integer 입니다. 오토 박싱으로 인해 String 및 int를 클래스에 전달하는 것도 가능합니다.

또한 매개 변수화된 타입을 이용하는 것 또한 가능합니다.

예) OrderedPair<String, Box\<Integer>> p = new OrderedPair<>("primes", new Box\<Integer>(...));

#### 원시타입

원시타입(raw type)은 타입 어규먼트가 없는 제네릭 클래스를 말합니다. 
위에서 이용한 GenericBox\<T>를 예로 들어보겠습니다.

```java
GenericBox raw = new GenericBox();
```
위 코드와 같이 타입 아규먼트를 사용하지 않은 제네릭 인스턴스를 말하며, 따라서 위 코드의 GenericBox은 GenericBox\<T>의 원시 타입입니다. (다만, 제네릭이 아닌 클래스 또는 인터페이스의 경우는 원시타입이라고 하지 않습니다.)

원시타입의 존재 이유는 JDK 5.0 이전에 생성된 레거지 코드에는 원시타입의 형태로 표시 됩니다. 때문에 기본적으로 원시 타입을 이용하면, 제네릭 생성 이전에 구현한 상태로 작동합니다. 또한 이전 버전과의 호환성을 위해 매개 변수화된 타입을 원시 타입에 할당 할수 있습니다.

```java
GenericBox raw = new GenericBox();
GenericBox<String> raw2 = raw;
```
그러나 매개 변수화된 타입에 원시 타입을 할당시 경고가 표시됩니다.
```java
GenericBox raw = new GenericBox();
GenericBox<String> raw2 = raw;
```
![이미지](https://github.com/redbean88/redbean88.github.io/blob/master/img/genericex01.png?raw=true)

이러한 경고는 런타임시 에러 발생의 가능성을 경고하는 것입니다.
따라서, 원시 타입을 사용하지 않아야합니다.

### 제네릭 주요 개념 (바운디드 타입, 와일드 카드)

#### 바운디드 타입

제네리 타입에서 타입 아규먼트로 사용할 수 있는 타입을 제한하려는 경우가 있을 수 있습니다. 예를 들어 숫자에 대해 작동하는 메서드는 Number또는 해당 하위 클래스의 인스턴스 만 허용하려고 할 수 있습니다. 이것이 바운디드 타입(Bounded Type Parameters)의 용도입니다.

바운디드 타입을 선언하려면 타입 파라미터의 이름, extends키워드, 상위 경계(upper bound)를 입력하면됩니다. 여기서 extends는 "상속" 또는 "구현"등의 일반적인 의미로 사용됩니다.

```java
public class Box<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.set(new Integer(10));    // 오토박싱이 일어납니다.
        integerBox.inspect("some text"); // 에러 : 문자열입니다.
    }
}
```

> 바이트코드

```
public class week14.Box<T> {
  public week14.Box();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void set(T);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field t:Ljava/lang/Object;
       5: return

  public T get();
    Code:
       0: aload_0
       1: getfield      #2                  // Field t:Ljava/lang/Object;
       4: areturn

  public <U extends java.lang.Number> void inspect(U);
    Code:
       0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: aload_0
       4: getfield      #2                  // Field t:Ljava/lang/Object;
       7: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
      10: invokevirtual #5                  // Method java/lang/Class.getName:()Ljava/lang/String;
      13: invokedynamic #6,  0              // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
      18: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      21: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      24: aload_1
      25: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
      28: invokevirtual #5                  // Method java/lang/Class.getName:()Ljava/lang/String;
      31: invokedynamic #8,  0              // InvokeDynamic #1:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
      36: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      39: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #9                  // class week14/Box
       3: dup
       4: invokespecial #10                 // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: new           #11                 // class java/lang/Integer
      12: dup
      13: bipush        10
      15: invokespecial #12                 // Method java/lang/Integer."<init>":(I)V
      18: invokevirtual #13                 // Method set:(Ljava/lang/Object;)V
      21: return
}
```

위 코드의 main 메소드에서는 범위를 넘어가는 
integerBox.inspect("some text")코드에 대해 오류를 발생합니다.
![이미지](https://github.com/redbean88/redbean88.github.io/blob/master/img/genericex02.png?raw=true)

같은 방식으로 클래스에도 적용이 가능합니다.

#### 다중 경계(Multiple Bounds)

타입 파라미터는 단일 뿐아니라 다중 경계도 정의 할수 있습니다.
경계 중 하나가 클래스 인 경우 먼저 지정해야 하며,
클래스는 하나만 인터페이스는 여러개를 사용 가능합니다.(확인필요)  
예) \<T extends B1 & B2 & B3>  
위 예문에서 B2와 B3는 클래스일수 없으며, 클래스일 경우, 컴파일 에러가 발생합니다.

#### 바운디드 타입 알고리즘의 핵심

아래의 예제는 바운디드 타입이 이용하는 핵심 알고리즘 중 하나입니다.
아래의 코드는 지정된 요소인 elem보다 큰 배열 요소 수를 계산하는 솔루션 입니다.

```java
public static <T> int countGreaterThan(T[] anArray, T elem) {
    int count = 0;
    for (T e : anArray)
        if (e > elem)  // 컴파일에러
            ++count;
    return count;
}
```
위 코드는 구현은 간단하지만 비교연산자(>)가 원시타입에만 적용되기 때문에, 컴파일이 불가능 합니다. 때문에 Comparable <T> 인터페이스로 제한되는 형식 매개 변수를 사용하도록 변경합니다.

```java
public interface Comparable<T> {
        public int compareTo(T o);

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }
}
```



#### 와일드 카드

제네릭 코드에서 `와일드 카드`라고하는 물음표 (?) 는 알 수없는 유형을 나타냅니다. 와일드 카드는 다양한 상황에서 사용할 수 있습니다. 매개 변수, 필드 또는 지역 변수의 타입 때때로 리턴 타입으로 사용됩니다. 와일드 카드는 제네릭 메서드 호출, 제네릭 클래스 인스턴스 생성 또는 수퍼 타입의 유형 인수로 사용되지는 않습니다.

#### 상한 바운디드(Upper Bounded) 와일드 카드

상한 와일드 카드를 사용하여 변수에 대한 제한을 완화 할 수 있습니다. 예를 들어 List\<Integer>, List\<Double>,List\<Number>를 동시에 사용하고 싶을때 이 방법을 사용합니다.

예) public static void process(List<? extends Foo> list) { /* ... */ }  

- 예문 추가 예정

#### 무제한(Unbounded) 와일드 카드

- Object 클래스 에서 제공하는 기능을 사용하여 구현할 수있는 메서드를 작성하는 경우 .
- 코드가 타입 파라미터에 의존하지 않는 제네릭 클래스의 메서드를 사용하는 경우. 예를 들어, List.size 또는 List.clear 입니다. 

- 예문 추가 예정

#### 하한 바운디드(lower bounded) 와일드 카드

하위 경계와일드카드는 알 수 없는 타입을 특정 타입 또는 해당 타입의 부모 타입으로 제한합니다.

- 예문 추가 예정




### 제네릭 메소드 만들기

#### 제네릭 메소드
제네릭 메서드는 자신만의 타입 파라미터를 이용하는 메서드입니다. 일반적인 제네릭 형식을 선언하는 것과 비슷하지만 타입 파라미터의 범위는 선언 된 메서드로 제한됩니다. 제네릭 클래스 생성자뿐만 아니라 정적 및 비 정적 제네릭 메서드에도 사용가능합니다.

제네릭 메서드의 구문에는 메서드의 리턴 타입 앞에 꺾쇠 괄호 넣어 타입 파라미터 목록을 표시합니다. 정적 제네릭 메서드의 경우 타입 파라미터는 메서드의 리턴 타입 앞에 나타나야합니다.

```java
public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}
```

> 바이트코드

```
public class week14.Util {
  public week14.Util();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static <K, V> boolean compare(week14.Pair<K, V>, week14.Pair<K, V>);
    Code:
       0: aload_0
       1: invokeinterface #2,  1            // InterfaceMethod week14/Pair.getKey:()Ljava/lang/Object;
       6: aload_1
       7: invokeinterface #2,  1            // InterfaceMethod week14/Pair.getKey:()Ljava/lang/Object;
      12: invokevirtual #3                  // Method java/lang/Object.equals:(Ljava/lang/Object;)Z
      15: ifeq          40
      18: aload_0
      19: invokeinterface #4,  1            // InterfaceMethod week14/Pair.getValue:()Ljava/lang/Object;
      24: aload_1
      25: invokeinterface #4,  1            // InterfaceMethod week14/Pair.getValue:()Ljava/lang/Object;
      30: invokevirtual #3                  // Method java/lang/Object.equals:(Ljava/lang/Object;)Z
      33: ifeq          40
      36: iconst_1
      37: goto          41
      40: iconst_0
      41: ireturn
}
```

위 메서드는 아래와 같이 사용가능합니다.

```java
public class TestMain {
    public static void main(String[] args) {
        Pair<Integer,String> p1 = new OrderedPair<>(1,"apple");
        Pair<Integer,String> p2 = new OrderedPair<>(2,"berry");
        boolean isSame = Util.<Integer,String>compare(p1,p2);
        boolean isSame2 = Util.compare(p1,p2);
    }
}
```
Util.<Integer,String>compare(p1,p2)과 같이 명시적으로도 사용할 수 있으나, 컴파일러는 타입 추론은 통해서 Util.compare(p1,p2)와 같은 형태로도 이용 가능합니다.  
타입 추론에 관한 내용은 [링크](https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html)로 대체합니다.


### Erasure

# 출처
https://docs.oracle.com/javase/tutorial/essential/io/streams.html  