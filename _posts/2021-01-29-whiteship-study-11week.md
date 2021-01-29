---
title : "자바스터디 11주차"
date : 2021-01-29
categories : study
---

# GOAL
자바의 열거형에 대해 학습하세요.

# 학습할 것 (필수)
- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet

### enum 정의하는 방법

#### Enum(열거형)이란?

자바에서 열거형은 1.5버전부터 적용된 기술로, 이전에 열거형 처리를 위해서는 int Enum 패턴을 사용했습니다.

```java
public class EnumTest {
    // int Enum Pattern-심각한 문제가 있습니다!
    public static final int SEASON_WINTER = 0;
    public static final int SEASON_SPRING = 1;
    public static final int SEASON_SUMMER = 2;
    public static final int SEASON_FALL = 3;
}
```

> 바이트코드

```
public class test.EnumTest {
  public static final int SEASON_WINTER;

  public static final int SEASON_SPRING;

  public static final int SEASON_SUMMER;

  public static final int SEASON_FALL;

  public test.EnumTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
}
```

위와 같은 패턴에는 다음같은 문제가 발생합니다.
- 형식이 안전하지 않음(Type-safe) : 위 코드에서 보면 봄,여름,가을,겨울 중 __택 1__ 해야하지만 합연산을 통해 봄이면서 여름 이고, 겨울이며 여름의 표현이 가능합니다.
- 네임스페이스가 없음 : 다른 int enum 타입과 구문하기 위해, SEASON_ 이라는 접두사를 항상 써야합니다.
- 취성(한계를 넘어서 파괴되는 것) : int enum는 컴파일 타입 상수이기 때문에, 새 상수가 추가되거나, 순서가 변경되면 소스의 재 컴파일이 필요합니다. 그렇지 않으면, 정확한 동작을 보장하기 힘듭니다.
- 비효과적 정보 : 단순이 int 값만 표기되기에, 의미있는 값이되기 힘듭니다.

Typesafe Enum 패턴 ( Effective Java Item 21 참조) 을 사용하여 이러한 문제를 해결할 수 있지만이 해당 패턴사용해야 되는 현상자체에 있습니다. 
매우 장황하여 오류가 발생하기 쉬우 며 해당 enum 상수를 switch명령문 에서 사용할 수 없습니다.
때문에 1.5 부터 열거 유형에 대해 지원을 하게 되었습니다. 가장 간단한 형식에서 이러한 열거 형은 C, C ++ 및 C # 대응 항목과 비슷합니다.

```java
public enum Season {
     WINTER, SPRING, SUMMER, FALL
}
```

> 바이트코드

```
public final class test.Season extends java.lang.Enum<test.Season> {
  public static final test.Season WINTER;

  public static final test.Season SPRING;

  public static final test.Season SUMMER;

  public static final test.Season FALL;

  public static test.Season[] values();
    Code:
       0: getstatic     #1                  // Field $VALUES:[Ltest/Season;
       3: invokevirtual #2                  // Method "[Ltest/Season;".clone:()Ljava/lang/Object;
       6: checkcast     #3                  // class "[Ltest/Season;"
       9: areturn

  public static test.Season valueOf(java.lang.String);
    Code:
       0: ldc           #4                  // class test/Season
       2: aload_0
       3: invokestatic  #5                  // Method java/lang/Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
       6: checkcast     #4                  // class test/Season
       9: areturn

  static {};
    Code:
       0: new           #4                  // class test/Season
       3: dup
       4: ldc           #7                  // String WINTER
       6: iconst_0
       7: invokespecial #8                  // Method "<init>":(Ljava/lang/String;I)V
      10: putstatic     #9                  // Field WINTER:Ltest/Season;
      13: new           #4                  // class test/Season
      16: dup
      17: ldc           #10                 // String SPRING
      19: iconst_1
      20: invokespecial #8                  // Method "<init>":(Ljava/lang/String;I)V
      23: putstatic     #11                 // Field SPRING:Ltest/Season;
      26: new           #4                  // class test/Season
      29: dup
      30: ldc           #12                 // String SUMMER
      32: iconst_2
      33: invokespecial #8                  // Method "<init>":(Ljava/lang/String;I)V
      36: putstatic     #13                 // Field SUMMER:Ltest/Season;
      39: new           #4                  // class test/Season
      42: dup
      43: ldc           #14                 // String FALL
      45: iconst_3
      46: invokespecial #8                  // Method "<init>":(Ljava/lang/String;I)V
      49: putstatic     #15                 // Field FALL:Ltest/Season;
      52: iconst_4
      53: anewarray     #4                  // class test/Season
      56: dup
      57: iconst_0
      58: getstatic     #9                  // Field WINTER:Ltest/Season;
      61: aastore
      62: dup
      63: iconst_1
      64: getstatic     #11                 // Field SPRING:Ltest/Season;
      67: aastore
      68: dup
      69: iconst_2
      70: getstatic     #13                 // Field SUMMER:Ltest/Season;
      73: aastore
      74: dup
      75: iconst_3
      76: getstatic     #15                 // Field FALL:Ltest/Season;
      79: aastore
      80: putstatic     #1                  // Field $VALUES:[Ltest/Season;
      83: return
}

```



### enum이 제공하는 메소드 (values()와 valueOf())

### java.lang.Enum

### EnumSet


마감일시
2021년 1월 30일 토요일 오후 1시까지.
