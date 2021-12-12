---
title : "자바스터디 14주차"
date : 2021-02-25
categories : [study]
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

규칙에 따라 타입 파라미터 이름은 단일 대문자입니다. 이것은 이미 알고 있는 변수 명명 규칙 과는 뚜렷한 대조를 이루며 그럴만 한 이유가 있습니다. 이 규칙이 없으면 유형 변수와 일반 클래스 또는 인터페이스 이름의 차이를 구분하기 어려울 것입니다.

가장 일반적으로 사용되는 타입 파라미터 이름은 다음과 같습니다.

|변수|내용|
|:--|:--|
|E|요소 (Java Collections Framework에서 광범위하게 사용됨)|
|K|키|
|N|숫자|
|T|타입|
|V|값|
|S, U, V 등|2, 3, 4번때 타입|


#### 제네릭 유형 호출 및 인스턴스화

코드에서 위 예시의 BOX 클래스를 참조하면, T에 Integer와 같은 구체적인 값을 정의하는 제네릭 타입 호출을 수행하여야 합니다.  

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

제네릭 유형의 호출을 일반적으로 매개 변수화된 타입(parameterized type)이라고 합니다.

이 클래스를 인스턴스화하려면 평소처럼 new 키워드를 사용 하되 클래스 이름과 괄호 사이에 \<Integer> 를 넣으면 됩니다.

예) Box \<Integer> integerBox = new Box \<Integer>();

#### 다이아몬드

Java SE 7 이상에서는 컴파일러가 문맥에서 타입 아규먼트(type-Argument)를 결정하거나 추론 할 수 있다면 한 일반 클래스의 생성자를 호출하는 데 필요한  타입 아규먼트를 빈  타입 아규먼트 (\<>) 세트로 바꿀 수 있습니다. 이 꺾쇠 괄호 쌍 \<>은 비공식적으로 `다이아몬드` 라고 합니다 . 예를 들어 다음 문을 사용하여 Box <Integer> 의 인스턴스를 만들 수 있습니다 .

예) Box <Integer> integerBox = new Box<>();

#### 다중 타입 파라미터

제네릭 클래스는 여러 타입 파라미터를 가질 수 있습니다


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

위 코드의 main 메소드에서는 범위를 넘어가는 integerBox.inspect("some text")코드에 대해 오류를 발생합니다.
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
위 코드는 구현은 간단하지만 비교연산자(>)가 원시타입에만 적용되기 때문에, 컴파일이 불가능 합니다. 때문에 Comparable \<T> 인터페이스로 제한되는 형식 매개 변수를 사용하도록 변경합니다.

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

#### 무제한(Unbounded) 와일드 카드

- Object 클래스 에서 제공하는 기능을 사용하여 구현할 수있는 메서드를 작성하는 경우 .
- 코드가 타입 파라미터에 의존하지 않는 제네릭 클래스의 메서드를 사용하는 경우. 예를 들어, List.size 또는 List.clear 입니다. 

예) public static void process(List<?> list) { /* ... */ }  

#### 하한 바운디드(lower bounded) 와일드 카드

하위 경계와일드카드는 알 수 없는 타입을 특정 타입 또는 해당 타입의 부모 타입으로 제한합니다.

예) public static void process(List<? super Foo> list) { /* ... */ }  

#### 제네릭,상속,서브타입

일반적으로 부모 타입의 변수에는 자식 타입의 인스턴스 할당이 가능합니다
```java
Object someObject = new Object();
Integer someInteger = new Integer(10);
someObject = someInteger;   // 가능
```
이러한 형태는 제네릭에서도 가능합니다.

```java
public class Box<T> {

    public void add(T num) {
    }
}

public class TestMain {
    public static void main(String[] args) {
        Box<Number> box = new Box<Number>();
        box.add(new Integer(1));  //가능
        box.add(new Double(1.0)); //가능
    }
}
```

그렇다면 메소드 하나를 추가해 봅니다

```java
public class Box<T> {

    public void add(T num) {
    }

    public void boxTest(Box<Number> n){
    }
}
```
boxTest 메소드의 타입 파라미터 number입니다. 우리가 이전 예제와 같이 number의 하위 타입인 Integer나 Double 가능하다고 생각합니다.
하지만 직접적인 Box\<number>의 하위타입 예를들어 Box\<Integer>라는 타입이 아니기 때문에 사용은 불가능 합니다.

```java
public class TestMain {
    public static void main(String[] args) {
        Box<Number> box = new Box<Number>();
        box.add(new Integer(1));
        box.add(new Double(1.0));

        box.boxTest(new Integer(1));  //컴파일 에러
        box.boxTest(new Double(1));   //컴파일 에러

    }
}
```

![이미지](https://docs.oracle.com/javase/tutorial/figures/java/generics-subtypeRelationship.gif)  
_오라클 튜토리얼_

#### 제네릭과 서브타이핑

일반 클래스 또는 인터페이스를 확장(extends)하거나 구현(implements)하여 하위 유형을 지정할 수 있습니다. 클래스 또는 인터페이스의 타입 파라미터와 다른 클래스의 타입 파라미터 간의 관계는 extends 및 implements 절에 의해 결정됩니다.

Collections 클래스를 예로 들면 ArrayList \<E> 는 List \<E>를 구현 하고 List \<E>는 Collection \<E>을 확장 합니다. 따라서 ArrayList \<String> 은 Collection \<String> 의 하위 유형 인 List \<String> 의 하위 타입입니다. 타입 아규먼트를 변경하지 않는 한 타입간에 하위 타입 관계가 유지됩니다.

![이미지](https://docs.oracle.com/javase/tutorial/figures/java/generics-sampleHierarchy.gif)  
_오라클 튜토리얼_

PayloadList라는 인터페이스를 정의할 때 P라는 generic 타입을 메서드의 파라메터로 사용한다고했을때, 아래 코드와 같을 것입니다.

```java
interface PayloadList<E,P> extends List<E> {
  void setPayload(int index, P val);
  ...
}
```
List\<String>의 서브타입이면서 PayloadList가 타입 아규먼트로 다양하게 변경 할수 있습니다.

- PayloadList<String,String>
- PayloadList<String,Integer>
- PayloadList<String,Exception>

![이미지](https://docs.oracle.com/javase/tutorial/figures/java/generics-payloadListHierarchy.gif)  
_오라클 튜토리얼_

#### 와일드 카드와 서브타이핑

위 내용처럼 상속과 제네릭 관의 연관관계는 무관하지만 와일드 카드를 사용함에 따라 그 관계를 생성 할수 있습니다.

```java
Integer a = new Integer(0);
Number b = a;

List<Integer> c = new ArrayList<>();
List<Number> d = c;     //컴파일 에러
```

위 코드에서 a,b는 정상적으로 작동하지만, c,d는 정상적으로 작동하지 않습니다. 이는 제네릭의 경우 c와 d는 부모 자식 관계가 아니기 때문입니다.
공통의 부모는 List\<?>입니다

![이미지](https://docs.oracle.com/javase/tutorial/figures/java/generics-listParent.gif)  
_오라클 튜토리얼_

하지만, integer는 number의 하위 타입입니다. 때문에 상한 와일드카드를 이용하여 이 문제를 해결 할수 있습니다.

```java
public class TestMain {
    public static void main(String[] args) {
        Integer a = new Integer(0);
        Number b = a;

        List<? extends Integer> c = new ArrayList<>();
        List<? extends Number>  d = c;  // 가능. List<? extends Integer> 는 List<? extends Number> 하위 타입입니다.
    }
}
```

전체 구조는 아래와 같습니다.

![이미지](https://docs.oracle.com/javase/tutorial/figures/java/generics-wildcardSubtyping.gif)  
_오라클 튜토리얼_

#### 와일드 카드 사용지침

와일드 카드 사용시 혼한을 가중하는 부분은 상한과 하한을 어떻게 정하는가 입니다.  
때문에 오라클 문서에서는 몇가지 가이드 라인을 정하고 있습니다.
- in 변수 : in 변수는 데이터를 제공하는 변수를 말합니다. capy(src , dest) 메소드를 상상해 보면, src는 데이터를 제공하는 in 변수 입니다. 
- out 변수 : out 변수는 데이터의 사용처에 대한 변수 입니다. capy(src , dest) 메소드를 상상해 보면, dest는 데이터를 접근을 허용하는 out 변수 입니다. 
위 내용을 기반하여 가이드가 제공되고 있습니다.
- "in" 변수는 extends 키워드를 사용하여 상한 와일드 카드로 정의합니다.
- "out"변수는 super 키워드를 사용하여 하한 와일드 카드로 정의합니다.
- Object 클래스에 정의 된 메서드를 사용하여 "in"변수에 액세스 할 수있는 경우 제한되지 않은 와일드 카드를 사용하십시오.
- 코드가 "in"및 "out"변수로 변수에 액세스해야하는 경우 와일드 카드를 사용하지 마십시오.

자세한 내용은 [링크](https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html)를 참고 주십시오  

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


### 유형삭제(Erasure)

Java 언어에 Generics가 도입되어 컴파일타임에 보다 엄격한 유형 검사를 제공하고 일반 프로그래밍을 지원합니다. 제네릭을 구현하기 위해 Java 컴파일러는 다음에 유형 삭제를 적용합니다.

- 제네릭 타입의 모든 타입 파라미터를 해당 범위 또는 타입 파라미터가 제한되지 않은 경우 Object로 바꿉니다. 따라서 생성 된 바이트 코드에는 일반 클래스, 인터페이스 및 메서드 만 포함됩니다.
- 유형 안전성을 유지하기 위해 필요한 경우 타입 캐스트를 삽입합니다.
- 확장 된 제네릭 타입에서 다형성을 유지하는 브리지 메서드를 생성합니다.
유형 삭제는 매개 변수화 된 유형에 대해 새 클래스가 생성되지 않도록합니다. 결과적으로 제네릭은 런타임 오버 헤드를 발생시키지 않습니다.

#### 일반 유형삭제

유형 삭제 프로세스는 Java 컴파일러는 모든 타입 파라미터를 지우고 타입 파라미터가 바인드 된 경우 각각을 첫 번째 바인드로 대체하고, 타입 파라미터가 바인드 되지 않은 경우 Object로 대체합니다.

```java
public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() { return data; }
}
```

> 바이트코드

```
public class week14.Node<T> {
  public week14.Node(T, week14.Node<T>);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #7                  // Field data:Ljava/lang/Object;
       9: aload_0
      10: aload_2
      11: putfield      #13                 // Field next:Lweek14/Node;
      14: return

  public T getData();
    Code:
       0: aload_0
       1: getfield      #7                  // Field data:Ljava/lang/Object;
       4: areturn
}
```
실제로 Object로 변경된 것을 코드에서 확인 할수 있습니다. 다음은 일반 Node 클래스는 제한된 타입 파라미터를 사용합니다.
Java 컴파일러는 바인딩 된 타입 파라미터 T 를 첫 번째 바인딩 된 클래스 인 Comparable로 대체합니다.

```java
public class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() { return data; }
}
```

> 바이트코드

```
public class week14.Node<T extends java.lang.Comparable<T>> {
  public week14.Node(T, week14.Node<T>);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #7                  // Field data:Ljava/lang/Comparable;
       9: aload_0
      10: aload_2
      11: putfield      #13                 // Field next:Lweek14/Node;
      14: return

  public T getData();
    Code:
       0: aload_0
       1: getfield      #7                  // Field data:Ljava/lang/Comparable;
       4: areturn
}
```

이러한 바이트코드 변경은 제네릭 메소드에서도 마찬가지로 적용됩니다.

#### 유형 삭제의 영향과 브리지 메소드
때로는 유형 삭제로 인해 예상치 못한 상황이 발생할 수 있습니다. 

```java
public class Node<T> {

    public T data;

    public Node(T data) { this.data = data; }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

public class MyNode extends Node<Integer> {
    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}

public static void main(String[] args) {
      MyNode mn = new MyNode(5);
      Node n = mn;            // 원시 타입 -컴파일러는 확인 경고를 표시한다.
      n.setData("Hello");     // 에러 : ClassCastException
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
       0: new           #7                  // class week14/MyNode
       3: dup
       4: iconst_5
       5: invokestatic  #9                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       8: invokespecial #15                 // Method week14/MyNode."<init>":(Ljava/lang/Integer;)V
      11: astore_1
      12: aload_1
      13: astore_2
      14: aload_2
      15: ldc           #18                 // String Hello
      17: invokevirtual #20                 // Method week14/Node.setData:(Ljava/lang/Object;)V
      20: return
}
```

위 코드는 아래와 같은 순서로 진행됩니다.

1. n.setData ( "Hello"); MyNode 클래스의 객체에서 부모 클래스의 setData(T) 메서드를 실행 시킵니다.
2. setData(T)는 본문에서 n 이 참조하는 객체의 데이터 필드의 타입은 String에 할당됩니다 .
3. 참조를 통해 mn의 객체의 데이터 필드에 액세스 가능하며, 해당 데이터 필드는 Integer로 할당된다.(Mynode의 경우, Node\<Integer>를 상속받았기 때문입니다.)
4. String 을 Integer 에 할당하려고 하면 Java 컴파일러에 의해 할당에 삽입 된 캐스트에서 ClassCastException이 발생합니다 .

#### 브릿지 메소드

매개 변수화된 클래스를 확장하거나 매개 변수화된 인터페이스를 구현하는 클래스 또는 인터페이스를 컴파일 할 때,
 컴파일러는 유형 삭제 프로세스의 일부로 __브릿지 메서드__ 라는 합성 메서드를 생성할 때도 있습니다.
일반적으로 브리지 메서드에 대해 걱정할 필요가 없지만 스택 추적시 당황 할 수 있습니다.

위의 Node 클래스가 유형삭제가 됬을 경우, 아래와 같은 코드로 변경됩니다.
```java
public class Node {

    public Object data;

    public Node(Object data) { this.data = data; }

    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}
public class MyNode extends Node {

    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
public static void main(String[] args) {
      MyNode mn = new MyNode(5);
      Node n = mn;            // 원시 타입 -컴파일러는 확인 경고를 표시한다.
      n.setData("Hello");     // 에러 미발생
      System.out.println(mn.data);    // Hello
  }
```

>바이트코드

```
public class week14.TestMain {
  public week14.TestMain();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class week14/MyNode
       3: dup
       4: iconst_5
       5: invokestatic  #9                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       8: invokespecial #15                 // Method week14/MyNode."<init>":(Ljava/lang/Integer;)V
      11: astore_1
      12: aload_1
      13: astore_2
      14: aload_2
      15: ldc           #18                 // String Hello
      17: invokevirtual #20                 // Method week14/Node.setData:(Ljava/lang/Object;)V
      20: getstatic     #26                 // Field java/lang/System.out:Ljava/io/PrintStream;
      23: aload_1
      24: getfield      #32                 // Field week14/MyNode.data:Ljava/lang/Object;
      27: invokevirtual #36                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      30: return
}
```

유형 삭제 후 메서드 시그니처가 일치하지 않습니다. (Node의 setData는 파라미터로 object를 MyNode는 setData의 파라미터로 Integer를 가진다)
따라서 MyNode setData 메서드는 Node setData 메서드를 오버라이딩하지 않습니다.

이 문제를 해결하고 유형 삭제 후 일반 유형 의 다형성 을 보존하기 위해 Java 컴파일러는 하위 유형이 예상대로 작동하는지 확인하는 브릿지 메소드를 생성합니다. 

```java
class MyNode extends Node {

    // 컴파일러에 의해 생성된 브릿지 메소드
    public void setData(Object data) {
        setData((Integer) data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }

    // ...
}
```

#### 수정 불가한 타입

수정불가한 타입에 관해서는 [링크](https://docs.oracle.com/javase/tutorial/java/generics/nonReifiableVarargsType.html)로 대체합니다

# 출처
https://docs.oracle.com/javase/tutorial/essential/io/streams.html  
자바의 정석 3판