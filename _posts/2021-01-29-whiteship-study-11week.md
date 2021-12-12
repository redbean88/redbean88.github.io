---
title : "자바스터디 11주차"
date : 2021-01-29
categories : [study]
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
 ( 위 예시에서 , 가을은 3으로 , 봄과 여름의 값으로 표현할수 있습니다. ~있을수 없는 일입니다~
- 네임스페이스가 없음 : 다른 int enum 타입과 구문하기 위해, SEASON_ 이라는 접두사를 항상 써야합니다.
- 취성(한계를 넘어서 파괴되는 것) : int enum는 컴파일 타입 상수이기 때문에, 새 상수가 추가되거나, 순서가 변경되면 소스 전체의 재 컴파일이 필요합니다. 그렇지 않으면, 정확한 동작을 보장하기 힘듭니다.
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

바이트코드를 보면 단순한 몇줄의 코드가 실제로는 java.lang.Enum 클래스의 제네릭을 이용하여 내부적으로는 구현되어 있음을 알 수 있습니다.


또한 위에서 열거한 문제를 해결하는 것 외에도 열거 형 유형에 임의의 메서드와 필드를 추가하고 임의의 인터페이스를 구현하는 등의 작업을 수행 할 수 있으며, Enum 형식은 모든 Object 메서드 구현을 이용 가능하고, 기본적으로는 Thread-safe 합니다.


### enum이 제공하는 메소드 (values()와 valueOf())

`values()` 열거형의 모든 값을 호출할때 사용한다

```java
package week11;

public class TestMain {
    public static void main(String[] args) {
        for (EnumTest enumTest : EnumTest.values()) {
            System.out.println("season => "+ enumTest);
        }
    }

    enum EnumTest {
        SPRING,SUMMER,FALL,WINTER;
    }

}
```

> 바이트코드

```
final class week11.TestMain$EnumTest extends java.lang.Enum<week11.TestMain$EnumTest> {
  public static final week11.TestMain$EnumTest SPRING;

  public static final week11.TestMain$EnumTest SUMMER;

  public static final week11.TestMain$EnumTest FALL;

  public static final week11.TestMain$EnumTest WINTER;

  public static week11.TestMain$EnumTest[] values();
    Code:
       0: getstatic     #1                  // Field $VALUES:[Lweek11/TestMain$EnumTest;
       3: invokevirtual #7                  // Method "[Lweek11/TestMain$EnumTest;".clone:()Ljava/lang/Object;
       6: checkcast     #8                  // class "[Lweek11/TestMain$EnumTest;"
       9: areturn

  public static week11.TestMain$EnumTest valueOf(java.lang.String);
    Code:
       0: ldc           #2                  // class week11/TestMain$EnumTest
       2: aload_0
       3: invokestatic  #12                 // Method java/lang/Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
       6: checkcast     #2                  // class week11/TestMain$EnumTest
       9: areturn

  static {};
    Code:
       0: new           #2                  // class week11/TestMain$EnumTest
       3: dup
       4: ldc           #22                 // String SPRING
       6: iconst_0
       7: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      10: putstatic     #25                 // Field SPRING:Lweek11/TestMain$EnumTest;
      13: new           #2                  // class week11/TestMain$EnumTest
      16: dup
      17: ldc           #28                 // String SUMMER
      19: iconst_1
      20: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      23: putstatic     #30                 // Field SUMMER:Lweek11/TestMain$EnumTest;
      26: new           #2                  // class week11/TestMain$EnumTest
      29: dup
      30: ldc           #32                 // String FALL
      32: iconst_2
      33: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      36: putstatic     #34                 // Field FALL:Lweek11/TestMain$EnumTest;
      39: new           #2                  // class week11/TestMain$EnumTest
      42: dup
      43: ldc           #36                 // String WINTER
      45: iconst_3
      46: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      49: putstatic     #38                 // Field WINTER:Lweek11/TestMain$EnumTest;
      52: iconst_4
      53: anewarray     #2                  // class week11/TestMain$EnumTest
      56: dup
      57: iconst_0
      58: getstatic     #25                 // Field SPRING:Lweek11/TestMain$EnumTest;
      61: aastore
      62: dup
      63: iconst_1
      64: getstatic     #30                 // Field SUMMER:Lweek11/TestMain$EnumTest;
      67: aastore
      68: dup
      69: iconst_2
      70: getstatic     #34                 // Field FALL:Lweek11/TestMain$EnumTest;
      73: aastore
      74: dup
      75: iconst_3
      76: getstatic     #38                 // Field WINTER:Lweek11/TestMain$EnumTest;
      79: aastore
      80: putstatic     #1                  // Field $VALUES:[Lweek11/TestMain$EnumTest;
      83: return
}


public class week11.TestMain {
  public week11.TestMain();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: invokestatic  #7                  // Method week11/TestMain$EnumTest.values:()[Lweek11/TestMain$EnumTest;
       3: astore_1
       4: aload_1
       5: arraylength
       6: istore_2
       7: iconst_0
       8: istore_3
       9: iload_3
      10: iload_2
      11: if_icmpge     38
      14: aload_1
      15: iload_3
      16: aaload
      17: astore        4
      19: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
      22: aload         4
      24: invokedynamic #19,  0             // InvokeDynamic #0:makeConcatWithConstants:(Lweek11/TestMain$EnumTest;)Ljava/lang/String;
      29: invokevirtual #23                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      32: iinc          3, 1
      35: goto          9
      38: return
}
```

`valueOf()` 해당 열거형에 해당하는 값이 있는지 확인한다. 해당하는 값이 없을 경우, 예외(IllegalArgumentException)를 반환한다.

```java
package week11;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(EnumTest.valueOf("SPRING"));     //SPRING
        System.out.println(EnumTest.valueOf("spring"));     //IllegalArgumentException
    }

    enum EnumTest {
        SPRING,SUMMER,FALL,WINTER;
    }
}
```

> 바이트코드

```
final class week11.TestMain$EnumTest extends java.lang.Enum<week11.TestMain$EnumTest> {
  public static final week11.TestMain$EnumTest SPRING;

  public static final week11.TestMain$EnumTest SUMMER;

  public static final week11.TestMain$EnumTest FALL;

  public static final week11.TestMain$EnumTest WINTER;

  public static week11.TestMain$EnumTest[] values();
    Code:
       0: getstatic     #1                  // Field $VALUES:[Lweek11/TestMain$EnumTest;
       3: invokevirtual #7                  // Method "[Lweek11/TestMain$EnumTest;".clone:()Ljava/lang/Object;
       6: checkcast     #8                  // class "[Lweek11/TestMain$EnumTest;"
       9: areturn

  public static week11.TestMain$EnumTest valueOf(java.lang.String);
    Code:
       0: ldc           #2                  // class week11/TestMain$EnumTest
       2: aload_0
       3: invokestatic  #12                 // Method java/lang/Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
       6: checkcast     #2                  // class week11/TestMain$EnumTest
       9: areturn

  static {};
    Code:
       0: new           #2                  // class week11/TestMain$EnumTest
       3: dup
       4: ldc           #22                 // String SPRING
       6: iconst_0
       7: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      10: putstatic     #25                 // Field SPRING:Lweek11/TestMain$EnumTest;
      13: new           #2                  // class week11/TestMain$EnumTest
      16: dup
      17: ldc           #28                 // String SUMMER
      19: iconst_1
      20: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      23: putstatic     #30                 // Field SUMMER:Lweek11/TestMain$EnumTest;
      26: new           #2                  // class week11/TestMain$EnumTest
      29: dup
      30: ldc           #32                 // String FALL
      32: iconst_2
      33: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      36: putstatic     #34                 // Field FALL:Lweek11/TestMain$EnumTest;
      39: new           #2                  // class week11/TestMain$EnumTest
      42: dup
      43: ldc           #36                 // String WINTER
      45: iconst_3
      46: invokespecial #24                 // Method "<init>":(Ljava/lang/String;I)V
      49: putstatic     #38                 // Field WINTER:Lweek11/TestMain$EnumTest;
      52: iconst_4
      53: anewarray     #2                  // class week11/TestMain$EnumTest
      56: dup
      57: iconst_0
      58: getstatic     #25                 // Field SPRING:Lweek11/TestMain$EnumTest;
      61: aastore
      62: dup
      63: iconst_1
      64: getstatic     #30                 // Field SUMMER:Lweek11/TestMain$EnumTest;
      67: aastore
      68: dup
      69: iconst_2
      70: getstatic     #34                 // Field FALL:Lweek11/TestMain$EnumTest;
      73: aastore
      74: dup
      75: iconst_3
      76: getstatic     #38                 // Field WINTER:Lweek11/TestMain$EnumTest;
      79: aastore
      80: putstatic     #1                  // Field $VALUES:[Lweek11/TestMain$EnumTest;
      83: return
}


public class week11.TestMain {
  public week11.TestMain();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #13                 // String SPRING
       5: invokestatic  #15                 // Method week11/TestMain$EnumTest.valueOf:(Ljava/lang/String;)Lweek11/TestMain$EnumTest;
       8: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      11: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
      14: ldc           #27                 // String spring
      16: invokestatic  #15                 // Method week11/TestMain$EnumTest.valueOf:(Ljava/lang/String;)Lweek11/TestMain$EnumTest;
      19: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      22: return
}
```

### java.lang.Enum

추상 크래스로 구현되어 있으며, 가용가능한 다양항 메소드가 구현되어 있다. ( 그렇기 때문에, 인터페이스가 아닌 추상클래스로 구현되어 있는게 아닌가 싶다)

Enum은 3가지 인터페이스를 추가로 상속 받고 있다. ( Constable, Comparable<E>, Serializable ) 

또한 Enum을 통한 set이라 map이 구현되어 있어 효과적으로 이용하도록 권고하고 있다.

```java

/**
 * This is the common base class of all Java language enumeration types.
 *
 * More information about enums, including descriptions of the
 * implicitly declared methods synthesized by the compiler, can be
 * found in section 8.9 of
 * <cite>The Java&trade; Language Specification</cite>.
 *
 * <p> Note that when using an enumeration type as the type of a set
 * or as the type of the keys in a map, specialized and efficient
 * {@linkplain java.util.EnumSet set} and {@linkplain
 * java.util.EnumMap map} implementations are available.
 *
 * @param <E> The enum type subclass
 * @serial exclude
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @see     Class#getEnumConstants()
 * @see     java.util.EnumSet
 * @see     java.util.EnumMap
 * @jls 8.9 Enum Types
 * @jls 8.9.3 Enum Members
 * @since   1.5
 */
@SuppressWarnings("serial") // No serialVersionUID needed due to
                            // special-casing of enum types.
public abstract class Enum<E extends Enum<E>>
        implements Constable, Comparable<E>, Serializable {
    /**
     * The name of this enum constant, as declared in the enum declaration.
     * Most programmers should use the {@link #toString} method rather than
     * accessing this field.
     */
    private final String name;

    /**
     * Returns the name of this enum constant, exactly as declared in its
     * enum declaration.
     *
     * <b>Most programmers should use the {@link #toString} method in
     * preference to this one, as the toString method may return
     * a more user-friendly name.</b>  This method is designed primarily for
     * use in specialized situations where correctness depends on getting the
     * exact name, which will not vary from release to release.
     *
     * @return the name of this enum constant
     */
    public final String name() {
        return name;
    }

    /**
     * The ordinal of this enumeration constant (its position
     * in the enum declaration, where the initial constant is assigned
     * an ordinal of zero).
     *
     * Most programmers will have no use for this field.  It is designed
     * for use by sophisticated enum-based data structures, such as
     * {@link java.util.EnumSet} and {@link java.util.EnumMap}.
     */
    private final int ordinal;

    /**
     * Returns the ordinal of this enumeration constant (its position
     * in its enum declaration, where the initial constant is assigned
     * an ordinal of zero).
     *
     * Most programmers will have no use for this method.  It is
     * designed for use by sophisticated enum-based data structures, such
     * as {@link java.util.EnumSet} and {@link java.util.EnumMap}.
     *
     * @return the ordinal of this enumeration constant
     */
    public final int ordinal() {
        return ordinal;
    }

    /**
     * Sole constructor.  Programmers cannot invoke this constructor.
     * It is for use by code emitted by the compiler in response to
     * enum type declarations.
     *
     * @param name - The name of this enum constant, which is the identifier
     *               used to declare it.
     * @param ordinal - The ordinal of this enumeration constant (its position
     *         in the enum declaration, where the initial constant is assigned
     *         an ordinal of zero).
     */
    protected Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    public String toString() {
        return name;
    }

    /**
     * Returns true if the specified object is equal to this
     * enum constant.
     *
     * @param other the object to be compared for equality with this object.
     * @return  true if the specified object is equal to this
     *          enum constant.
     */
    public final boolean equals(Object other) {
        return this==other;
    }

    /**
     * Returns a hash code for this enum constant.
     *
     * @return a hash code for this enum constant.
     */
    public final int hashCode() {
        return super.hashCode();
    }

    /**
     * Throws CloneNotSupportedException.  This guarantees that enums
     * are never cloned, which is necessary to preserve their "singleton"
     * status.
     *
     * @return (never returns)
     */
    protected final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Compares this enum with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * Enum constants are only comparable to other enum constants of the
     * same enum type.  The natural order implemented by this
     * method is the order in which the constants are declared.
     */
    public final int compareTo(E o) {
        Enum<?> other = (Enum<?>)o;
        Enum<E> self = this;
        if (self.getClass() != other.getClass() && // optimization
            self.getDeclaringClass() != other.getDeclaringClass())
            throw new ClassCastException();
        return self.ordinal - other.ordinal;
    }

    /**
     * Returns the Class object corresponding to this enum constant's
     * enum type.  Two enum constants e1 and  e2 are of the
     * same enum type if and only if
     *   e1.getDeclaringClass() == e2.getDeclaringClass().
     * (The value returned by this method may differ from the one returned
     * by the {@link Object#getClass} method for enum constants with
     * constant-specific class bodies.)
     *
     * @return the Class object corresponding to this enum constant's
     *     enum type
     */
    @SuppressWarnings("unchecked")
    public final Class<E> getDeclaringClass() {
        Class<?> clazz = getClass();
        Class<?> zuper = clazz.getSuperclass();
        return (zuper == Enum.class) ? (Class<E>)clazz : (Class<E>)zuper;
    }

    /**
     * Returns an enum descriptor {@code EnumDesc} for this instance, if one can be
     * constructed, or an empty {@link Optional} if one cannot be.
     *
     * @return An {@link Optional} containing the resulting nominal descriptor,
     * or an empty {@link Optional} if one cannot be constructed.
     * @since 12
     */
    @Override
    public final Optional<EnumDesc<E>> describeConstable() {
        return getDeclaringClass()
                .describeConstable()
                .map(c -> EnumDesc.of(c, name));
    }

    /**
     * Returns the enum constant of the specified enum type with the
     * specified name.  The name must match exactly an identifier used
     * to declare an enum constant in this type.  (Extraneous whitespace
     * characters are not permitted.)
     *
     * <p>Note that for a particular enum type {@code T}, the
     * implicitly declared {@code public static T valueOf(String)}
     * method on that enum may be used instead of this method to map
     * from a name to the corresponding enum constant.  All the
     * constants of an enum type can be obtained by calling the
     * implicit {@code public static T[] values()} method of that
     * type.
     *
     * @param <T> The enum type whose constant is to be returned
     * @param enumType the {@code Class} object of the enum type from which
     *      to return a constant
     * @param name the name of the constant to return
     * @return the enum constant of the specified enum type with the
     *      specified name
     * @throws IllegalArgumentException if the specified enum type has
     *         no constant with the specified name, or the specified
     *         class object does not represent an enum type
     * @throws NullPointerException if {@code enumType} or {@code name}
     *         is null
     * @since 1.5
     */
    public static <T extends Enum<T>> T valueOf(Class<T> enumType,
                                                String name) {
        T result = enumType.enumConstantDirectory().get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException(
            "No enum constant " + enumType.getCanonicalName() + "." + name);
    }

    /**
     * enum classes cannot have finalize methods.
     */
    @SuppressWarnings("deprecation")
    protected final void finalize() { }

    /**
     * prevent default deserialization
     */
    private void readObject(ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        throw new InvalidObjectException("can't deserialize enum");
    }

    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("can't deserialize enum");
    }

    /**
     * A <a href="{@docRoot}/java.base/java/lang/constant/package-summary.html#nominal">nominal descriptor</a> for an
     * {@code enum} constant.
     *
     * @param <E> the type of the enum constant
     *
     * @since 12
     */
    public static final class EnumDesc<E extends Enum<E>>
            extends DynamicConstantDesc<E> {

        /**
         * Constructs a nominal descriptor for the specified {@code enum} class and name.
         *
         * @param constantType a {@link ClassDesc} describing the {@code enum} class
         * @param constantName the unqualified name of the enum constant
         * @throws NullPointerException if any argument is null
         * @jvms 4.2.2 Unqualified Names
         */
        private EnumDesc(ClassDesc constantType, String constantName) {
            super(ConstantDescs.BSM_ENUM_CONSTANT, requireNonNull(constantName), requireNonNull(constantType));
        }

        /**
         * Returns a nominal descriptor for the specified {@code enum} class and name
         *
         * @param <E> the type of the enum constant
         * @param enumClass a {@link ClassDesc} describing the {@code enum} class
         * @param constantName the unqualified name of the enum constant
         * @return the nominal descriptor
         * @throws NullPointerException if any argument is null
         * @jvms 4.2.2 Unqualified Names
         * @since 12
         */
        public static<E extends Enum<E>> EnumDesc<E> of(ClassDesc enumClass,
                                                        String constantName) {
            return new EnumDesc<>(enumClass, constantName);
        }

        @Override
        @SuppressWarnings("unchecked")
        public E resolveConstantDesc(MethodHandles.Lookup lookup)
                throws ReflectiveOperationException {
            return Enum.valueOf((Class<E>) constantType().resolveConstantDesc(lookup), constantName());
        }

        @Override
        public String toString() {
            return String.format("EnumDesc[%s.%s]", constantType().displayName(), constantName());
        }
    }
}
```

### EnumSet

Enum의 Typesafe를 지원하며, 내부 구현은 비트 벡터로 구현하고 

```java
/**
 * A specialized {@link Set} implementation for use with enum types.  All of
 * the elements in an enum set must come from a single enum type that is
 * specified, explicitly or implicitly, when the set is created.  Enum sets
 * are represented internally as bit vectors.  This representation is
 * extremely compact and efficient. The space and time performance of this
 * class should be good enough to allow its use as a high-quality, typesafe
 * alternative to traditional {@code int}-based "bit flags."  Even bulk
 * operations (such as {@code containsAll} and {@code retainAll}) should
 * run very quickly if their argument is also an enum set.
 *
 * <p>The iterator returned by the {@code iterator} method traverses the
 * elements in their <i>natural order</i> (the order in which the enum
 * constants are declared).  The returned iterator is <i>weakly
 * consistent</i>: it will never throw {@link ConcurrentModificationException}
 * and it may or may not show the effects of any modifications to the set that
 * occur while the iteration is in progress.
 *
 * <p>Null elements are not permitted.  Attempts to insert a null element
 * will throw {@link NullPointerException}.  Attempts to test for the
 * presence of a null element or to remove one will, however, function
 * properly.
 *
 * <P>Like most collection implementations, {@code EnumSet} is not
 * synchronized.  If multiple threads access an enum set concurrently, and at
 * least one of the threads modifies the set, it should be synchronized
 * externally.  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the enum set.  If no such object exists,
 * the set should be "wrapped" using the {@link Collections#synchronizedSet}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access:
 *
 * <pre>
 * Set&lt;MyEnum&gt; s = Collections.synchronizedSet(EnumSet.noneOf(MyEnum.class));
 * </pre>
 *
 * <p>Implementation note: All basic operations execute in constant time.
 * They are likely (though not guaranteed) to be much faster than their
 * {@link HashSet} counterparts.  Even bulk operations execute in
 * constant time if their argument is also an enum set.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @since 1.5
 * @see EnumMap
 */
public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E>
    implements Cloneable, java.io.Serializable
{
    // declare EnumSet.class serialization compatibility with JDK 8
    private static final long serialVersionUID = 1009687484059888093L;

    /**
     * The class of all the elements of this set.
     */
    final transient Class<E> elementType;

    /**
     * All of the values comprising E.  (Cached for performance.)
     */
    final transient Enum<?>[] universe;

    EnumSet(Class<E>elementType, Enum<?>[] universe) {
        this.elementType = elementType;
        this.universe    = universe;
    }

    /**
     * Creates an empty enum set with the specified element type.
     *
     * @param <E> The class of the elements in the set
     * @param elementType the class object of the element type for this enum
     *     set
     * @return An empty enum set of the specified type.
     * @throws NullPointerException if {@code elementType} is null
     */
    public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
        Enum<?>[] universe = getUniverse(elementType);
        if (universe == null)
            throw new ClassCastException(elementType + " not an enum");

        if (universe.length <= 64)
            return new RegularEnumSet<>(elementType, universe);
        else
            return new JumboEnumSet<>(elementType, universe);
    }

    /**
     * Creates an enum set containing all of the elements in the specified
     * element type.
     *
     * @param <E> The class of the elements in the set
     * @param elementType the class object of the element type for this enum
     *     set
     * @return An enum set containing all the elements in the specified type.
     * @throws NullPointerException if {@code elementType} is null
     */
    public static <E extends Enum<E>> EnumSet<E> allOf(Class<E> elementType) {
        EnumSet<E> result = noneOf(elementType);
        result.addAll();
        return result;
    }

    /**
     * Adds all of the elements from the appropriate enum type to this enum
     * set, which is empty prior to the call.
     */
    abstract void addAll();

    /**
     * Creates an enum set with the same element type as the specified enum
     * set, initially containing the same elements (if any).
     *
     * @param <E> The class of the elements in the set
     * @param s the enum set from which to initialize this enum set
     * @return A copy of the specified enum set.
     * @throws NullPointerException if {@code s} is null
     */
    public static <E extends Enum<E>> EnumSet<E> copyOf(EnumSet<E> s) {
        return s.clone();
    }

    /**
     * Creates an enum set initialized from the specified collection.  If
     * the specified collection is an {@code EnumSet} instance, this static
     * factory method behaves identically to {@link #copyOf(EnumSet)}.
     * Otherwise, the specified collection must contain at least one element
     * (in order to determine the new enum set's element type).
     *
     * @param <E> The class of the elements in the collection
     * @param c the collection from which to initialize this enum set
     * @return An enum set initialized from the given collection.
     * @throws IllegalArgumentException if {@code c} is not an
     *     {@code EnumSet} instance and contains no elements
     * @throws NullPointerException if {@code c} is null
     */
    public static <E extends Enum<E>> EnumSet<E> copyOf(Collection<E> c) {
        if (c instanceof EnumSet) {
            return ((EnumSet<E>)c).clone();
        } else {
            if (c.isEmpty())
                throw new IllegalArgumentException("Collection is empty");
            Iterator<E> i = c.iterator();
            E first = i.next();
            EnumSet<E> result = EnumSet.of(first);
            while (i.hasNext())
                result.add(i.next());
            return result;
        }
    }

    /**
     * Creates an enum set with the same element type as the specified enum
     * set, initially containing all the elements of this type that are
     * <i>not</i> contained in the specified set.
     *
     * @param <E> The class of the elements in the enum set
     * @param s the enum set from whose complement to initialize this enum set
     * @return The complement of the specified set in this set
     * @throws NullPointerException if {@code s} is null
     */
    public static <E extends Enum<E>> EnumSet<E> complementOf(EnumSet<E> s) {
        EnumSet<E> result = copyOf(s);
        result.complement();
        return result;
    }

    /**
     * Creates an enum set initially containing the specified element.
     *
     * Overloadings of this method exist to initialize an enum set with
     * one through five elements.  A sixth overloading is provided that
     * uses the varargs feature.  This overloading may be used to create
     * an enum set initially containing an arbitrary number of elements, but
     * is likely to run slower than the overloadings that do not use varargs.
     *
     * @param <E> The class of the specified element and of the set
     * @param e the element that this set is to contain initially
     * @throws NullPointerException if {@code e} is null
     * @return an enum set initially containing the specified element
     */
    public static <E extends Enum<E>> EnumSet<E> of(E e) {
        EnumSet<E> result = noneOf(e.getDeclaringClass());
        result.add(e);
        return result;
    }

    /**
     * Creates an enum set initially containing the specified elements.
     *
     * Overloadings of this method exist to initialize an enum set with
     * one through five elements.  A sixth overloading is provided that
     * uses the varargs feature.  This overloading may be used to create
     * an enum set initially containing an arbitrary number of elements, but
     * is likely to run slower than the overloadings that do not use varargs.
     *
     * @param <E> The class of the parameter elements and of the set
     * @param e1 an element that this set is to contain initially
     * @param e2 another element that this set is to contain initially
     * @throws NullPointerException if any parameters are null
     * @return an enum set initially containing the specified elements
     */
    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2) {
        EnumSet<E> result = noneOf(e1.getDeclaringClass());
        result.add(e1);
        result.add(e2);
        return result;
    }

    /**
     * Creates an enum set initially containing the specified elements.
     *
     * Overloadings of this method exist to initialize an enum set with
     * one through five elements.  A sixth overloading is provided that
     * uses the varargs feature.  This overloading may be used to create
     * an enum set initially containing an arbitrary number of elements, but
     * is likely to run slower than the overloadings that do not use varargs.
     *
     * @param <E> The class of the parameter elements and of the set
     * @param e1 an element that this set is to contain initially
     * @param e2 another element that this set is to contain initially
     * @param e3 another element that this set is to contain initially
     * @throws NullPointerException if any parameters are null
     * @return an enum set initially containing the specified elements
     */
    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3) {
        EnumSet<E> result = noneOf(e1.getDeclaringClass());
        result.add(e1);
        result.add(e2);
        result.add(e3);
        return result;
    }

    /**
     * Creates an enum set initially containing the specified elements.
     *
     * Overloadings of this method exist to initialize an enum set with
     * one through five elements.  A sixth overloading is provided that
     * uses the varargs feature.  This overloading may be used to create
     * an enum set initially containing an arbitrary number of elements, but
     * is likely to run slower than the overloadings that do not use varargs.
     *
     * @param <E> The class of the parameter elements and of the set
     * @param e1 an element that this set is to contain initially
     * @param e2 another element that this set is to contain initially
     * @param e3 another element that this set is to contain initially
     * @param e4 another element that this set is to contain initially
     * @throws NullPointerException if any parameters are null
     * @return an enum set initially containing the specified elements
     */
    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4) {
        EnumSet<E> result = noneOf(e1.getDeclaringClass());
        result.add(e1);
        result.add(e2);
        result.add(e3);
        result.add(e4);
        return result;
    }

    /**
     * Creates an enum set initially containing the specified elements.
     *
     * Overloadings of this method exist to initialize an enum set with
     * one through five elements.  A sixth overloading is provided that
     * uses the varargs feature.  This overloading may be used to create
     * an enum set initially containing an arbitrary number of elements, but
     * is likely to run slower than the overloadings that do not use varargs.
     *
     * @param <E> The class of the parameter elements and of the set
     * @param e1 an element that this set is to contain initially
     * @param e2 another element that this set is to contain initially
     * @param e3 another element that this set is to contain initially
     * @param e4 another element that this set is to contain initially
     * @param e5 another element that this set is to contain initially
     * @throws NullPointerException if any parameters are null
     * @return an enum set initially containing the specified elements
     */
    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4,
                                                    E e5)
    {
        EnumSet<E> result = noneOf(e1.getDeclaringClass());
        result.add(e1);
        result.add(e2);
        result.add(e3);
        result.add(e4);
        result.add(e5);
        return result;
    }

    /**
     * Creates an enum set initially containing the specified elements.
     * This factory, whose parameter list uses the varargs feature, may
     * be used to create an enum set initially containing an arbitrary
     * number of elements, but it is likely to run slower than the overloadings
     * that do not use varargs.
     *
     * @param <E> The class of the parameter elements and of the set
     * @param first an element that the set is to contain initially
     * @param rest the remaining elements the set is to contain initially
     * @throws NullPointerException if any of the specified elements are null,
     *     or if {@code rest} is null
     * @return an enum set initially containing the specified elements
     */
    @SafeVarargs
    public static <E extends Enum<E>> EnumSet<E> of(E first, E... rest) {
        EnumSet<E> result = noneOf(first.getDeclaringClass());
        result.add(first);
        for (E e : rest)
            result.add(e);
        return result;
    }

    /**
     * Creates an enum set initially containing all of the elements in the
     * range defined by the two specified endpoints.  The returned set will
     * contain the endpoints themselves, which may be identical but must not
     * be out of order.
     *
     * @param <E> The class of the parameter elements and of the set
     * @param from the first element in the range
     * @param to the last element in the range
     * @throws NullPointerException if {@code from} or {@code to} are null
     * @throws IllegalArgumentException if {@code from.compareTo(to) > 0}
     * @return an enum set initially containing all of the elements in the
     *         range defined by the two specified endpoints
     */
    public static <E extends Enum<E>> EnumSet<E> range(E from, E to) {
        if (from.compareTo(to) > 0)
            throw new IllegalArgumentException(from + " > " + to);
        EnumSet<E> result = noneOf(from.getDeclaringClass());
        result.addRange(from, to);
        return result;
    }

    /**
     * Adds the specified range to this enum set, which is empty prior
     * to the call.
     */
    abstract void addRange(E from, E to);

    /**
     * Returns a copy of this set.
     *
     * @return a copy of this set
     */
    @SuppressWarnings("unchecked")
    public EnumSet<E> clone() {
        try {
            return (EnumSet<E>) super.clone();
        } catch(CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Complements the contents of this enum set.
     */
    abstract void complement();

    /**
     * Throws an exception if e is not of the correct type for this enum set.
     */
    final void typeCheck(E e) {
        Class<?> eClass = e.getClass();
        if (eClass != elementType && eClass.getSuperclass() != elementType)
            throw new ClassCastException(eClass + " != " + elementType);
    }

    /**
     * Returns all of the values comprising E.
     * The result is uncloned, cached, and shared by all callers.
     */
    private static <E extends Enum<E>> E[] getUniverse(Class<E> elementType) {
        return SharedSecrets.getJavaLangAccess()
                                        .getEnumConstantsShared(elementType);
    }

    /**
     * This class is used to serialize all EnumSet instances, regardless of
     * implementation type.  It captures their "logical contents" and they
     * are reconstructed using public static factories.  This is necessary
     * to ensure that the existence of a particular implementation type is
     * an implementation detail.
     *
     * @serial include
     */
    private static class SerializationProxy<E extends Enum<E>>
        implements java.io.Serializable
    {

        private static final Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum<?>[0];

        /**
         * The element type of this enum set.
         *
         * @serial
         */
        private final Class<E> elementType;

        /**
         * The elements contained in this enum set.
         *
         * @serial
         */
        private final Enum<?>[] elements;

        SerializationProxy(EnumSet<E> set) {
            elementType = set.elementType;
            elements = set.toArray(ZERO_LENGTH_ENUM_ARRAY);
        }

        /**
         * Returns an {@code EnumSet} object with initial state
         * held by this proxy.
         *
         * @return a {@code EnumSet} object with initial state
         * held by this proxy
         */
        @SuppressWarnings("unchecked")
        private Object readResolve() {
            // instead of cast to E, we should perhaps use elementType.cast()
            // to avoid injection of forged stream, but it will slow the
            // implementation
            EnumSet<E> result = EnumSet.noneOf(elementType);
            for (Enum<?> e : elements)
                result.add((E)e);
            return result;
        }

        private static final long serialVersionUID = 362491234563181265L;
    }

    /**
     * Returns a
     * <a href="{@docRoot}/serialized-form.html#java.util.EnumSet.SerializationProxy">
     * SerializationProxy</a>
     * representing the state of this instance.
     *
     * @return a {@link SerializationProxy}
     * representing the state of this instance
     */
    Object writeReplace() {
        return new SerializationProxy<>(this);
    }

    /**
     * @param s the stream
     * @throws java.io.InvalidObjectException always
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.InvalidObjectException {
        throw new java.io.InvalidObjectException("Proxy required");
    }
}
```
