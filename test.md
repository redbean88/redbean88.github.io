|| @Entity



- 테이블과의 매핑

- @Entity가 붙은 클래스는 JPA가 관리하는 것으로, 엔티티라고 불림

	

> 속성

Name : JPA에서 사용할 엔티티 이름을 지정.

       보통 기본값인 클래스 이름을 사용



> 주의사항

- 기본 생성자는 필수 (JPA가 엔티티 객체 생성 시 기본 생성자를 사용)

- final 클래스, enum, interface, inner class 에는 사용할 수 없음

- 저장할 필드에 final 사용 불가




|| @Table



- 엔티티와 매핑할 테이블을 지정

- 생략 시 매핑한 엔티티 일므을 테이블 이름으로 사용



> 속성 

Name : 매핑할 테이블 이름 (default. 엔티티 이름 사용)

Catalog : catalog 기능이 있는 DB에서 catalog 를 매핑 (default. DB 명)

Schema : schema 기능이 있는 DB에서 schema를 매핑

uniqueConstraints : DDL 생성 시 유니크 제약조건을 만듦

     스키마 자동 생성 기능을 사용해서 DDL을 만들 때만 사용





|| 데이터베이스 스키마 자동 생성



- JPA는 데이터베이스 스키마를 자동으로 생성하는 기능을 지원

- 클래스의 매핑 정보와 데이터베이스 방언을 사용하여 데이터베이스 스키마 생성 

- 애플리케이션 실행 시점에 데이터베이스 테이블을 자동으로 생성



- 스키마 자동 생성 기능 사용을 위해 persistence.xml에 속성 추가

1
<property name="hibernate.hbm2ddl.auto" value="create" />
cs


hibernate.hbm2ddl.auto 속성

create : 기존 테이블을 삭제하고 새로 생성(DROP + CREATE)

create-drop : CREATE 속성에 추가로 애플리케이션을 종료할 때 생성한 DDL을 제거 (DROP + CREATE + DROP)

update : DB 테이블과 엔티티 매핑 정보를 비교해서 변경 사항만 수정

validate : DB 테이블과 엔티티 매핑정보를 비교해서 차이가 있으면 경고를 남기고 애플리케이션을 실행하지 않음.

DDL을 수행하지 않음

none : 자동 생성 기능을 사용하지 않음



> 주의사항

- 개발 초기 단계는 create 또는 update

- 초기화 상태로 자동화된 테스트를 진행하는 개발자 환경과 CI서버는 create 또는 create-drop

- 테스트 서버는 update 또는 validate

- 스테이징과 운영 서버는 validate 또는 none



+



** 테이블 명이나 컬럼 명이 생략되면 자바의 카멜 표기법을 테이블의 언더스코어 표기법으로 매핑하는 방법

Before.

1
2
@Column(name="role_type")
private RoleType roleType;
cs


After.

persistence.xml에 속성 추가

1
<property name="hibernate.ejb.naming_strategy" value="org.hibernate.cfg.ImprovedNamingStrategy" />
cs




|| 기본 키 매핑



- 영속성 컨텍스트는 엔티티를 식별자 값으로 구분하므로 엔티티를 영속 상태로 만들기 위해 식별자 값이 반드시 필요



@GeneratedValue

<기본 키 생성 전략>

- 직접 할당 : 기본 키를 애플리케이션에 직접 할당

ㄴ em.persist()를 호출하기 전 애플리케이션에서 직접 식별자 값을 할당해야 함. 식별자 값이 없을 경우 예러 발생



- 자동 생성 : 대리 키 사용 방식

* IDENTITY : 기본 키 생성을 데이터베이스에 위임 (= AUTO_INCREMENT)

1
2
3
4
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "ID")
private Long id;
Colored by Color Scripter
cs
- Statement.getGeneratedKeys() 를 사용해서 데이터를 저장함과 동시에 생성된 기본 키 값을 얻어 올 수 있음.



	* SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본 키를 할당, 

   데이터베이스 시퀀스에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장

   유일한 값을 순서대로 생성(오라클, PostgreSQL, DB2, H2)



	* TABLE : 키 생성 테이블을 사용

키 생성 전용 테이블 하나를 만들고 여기에 이름과 값으로 사용할 컬럼을 만들어

데이터베이스 시퀀스를 흉내내는 전략. 테이블을 사용하므로 모든 데이터베이스에 적용 가능



	* AUTO : 선택한 데이터베이스 방언에 따라 방식을 자동으로 선택(Default)

  Ex) 오라클 DB 선택 시 SEQUENCE, MySQL DB 선택 시 IDENTITY 사용



** 키 생성 전략 사용 시 persistence.xml 에 아래 속성 추가

1
<property name="hibernate.id.new_generator_mappings" value="true" />
cs





|| @Column



객체 필드를 테이블 컬럼에 매핑

속성 중 name, nullable이 주로 사용되고 나머지는 잘 사용되지 않음



1
2
3
4
5
6
7
8
9
10
11
12
13
14
@Column(nullable = false)
private Strin data;
 
@Column(unique = true)
private Strin data;
 
@Column(columnDefinition = "varchar(100) default 'EMPTY'")
private Strin data;
 
@Column(length = 400)
private Strin data;
 
@Column(precision = 10, scale = 2)
private BigDecimal data;
Colored by Color Scripter
cs


> 속성

name : 필드와 매핑할 테이블 컬럼 이름 (default. 객체의 필드 이름)

nullable (DDL) : null 값의 허용 여부 설정, false 설정 시 not null (default. true)

   @Column 사용 시 nullable = false 로 설정하는 것이 안전

unique (DDL) : @Table 의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니크 제약조건을 적용

columnDefinition (DDL) : 데이터베이스 컬럼 정보를 직접 줄 수 있음, default 값 설정

   (default. 필드의 자바 타입과 방언 정보를 사용해 적절한 컬럼 타입을 생성)

length (DDL) : 문자 길이 제약조건, String 타입에만 사용 (default. 255)

percision, scale (DDL) : BigDecimal, BigInteger 타입에서 사용. 아주 큰 숫자나 정밀한 소수를 다룰 때만 사용

(default. precision = 19, scale = 2)





|| @Enumerated



자바의 enum 타입을 매핑할 때 사용



> 속성

value : EnumType.ORDINAL : enum 순서를 데이터베이스에 저장

 EnumType.STRING : enum 이름을 데이터베이스에 저장

 (default. EnumType.ORDINAL)





|| @Temporal



날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용



1
2
3
4
5
6
7
8
@Temporal(TemporalType.DATE)
private Date date; // 날짜
 
@Temporal(TemporalType.TIME)
private Date date; // 시간
 
@Temporal(TemporalType.TIMESTAMP)
private Date date; // 날짜와 시간
cs


> 속성

value : TemporalType.DATE : 날짜, 데이터베이스 data 타입과 매핑 (2020-12-18)

 TemporalType.TIME : 시간, 데이터베이스 time 타입과 매핑 (23:36:33)

 TemporalType.TIMESTAMP : 날짜와 시간, 데이터베이스 timestamp 타입과 매핑 (2020-12-18 23:36:33)

 (default. TemporalType은 필수로 지정)



@Temporal 을 생략하면 자바의 Date와 가장 유사한 timestamp로 정의





|| @Lob



데이터베이스 BLOB, CLOB 타입과 매핑



> 속성

지정 속성이 없음

대신 매핑하는 필드 타입이 문자면 CLOB로 나머지는 BLOB로 매핑





|| @Transient



이 필드는 매핑하지 않음

데이터베이스에 저장하지 않고 조회하지도 않음

객체에 임시로 어떤 값을 보관하고 싶을 때 사용





|| @Access



JPA가 엔티티 데이터에 접근하는 방식을 지정



- 필드 접근 : AccessType.FIELD 로 지정

  필드에 직접 접근 (private도 접근 가능)

- 프로퍼티 접근: AccessType.PROPERTY 로 지정

 접근자 Getter를 사용




출처: https://data-make.tistory.com/610 [Data Makes Our Future]


4. 엔티티 매핑
대표적인 매핑 어노테이션

XML에 기입해도 되지만 어노테이션 방식이 좀 더 쉽고 직관적

객체와 테이블 매핑 : @Entity, @Table
기본 키 매핑 : @Id
필드와 컬럼 매핑 : @Column
연관관계 매핑 : @ManyToOne, @JoinColumn

4.1 Entity
JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 어노테이션을 필수로 붙여야 한다.

 

@Entity가 붙은 클래스는 JPA가 관리.

@Entity 적용시 주의사항

기본 생성자는 필수. (파라미터가 없는 public 또는 protected 생성자)
final 클래스, enum, interface, inner 클래스에는 사용할 수 없다.
저장할 필드에 final을 사용하면 안된다
JPA가 엔티티 객체를 생성할 때 기본 생성자를 사용하므로 이 생성자는 반드시 있어야 한다. 자바는 생성자가 하나도 없으면 다음과 같은 기본 생성자를 자동으로 만든다.

public Member() {}  // 기본 생성자
문제는 다음과 같이 생성자를 하나 이상 만들면 자바는 기본 생성자를 자동으로 만들지 않는다. 이때는 기본 생성자를 직접 만들어야 한다.

public Member() {}  // 기본 생성자

public Member(String name) {
    this.name = name;
}
4.2 @Table
엔티티와 매핑할 테이블을 지정한다.

@Entity
@Table(name="MEMBER")
public class Member {
    ...
}
4.3 다양한 매핑 사용
package jpabook.start;

import javax.persistence.*;  
import java.util.Date;

@Entity
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10) //추가
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private String temp;

    //Getter, Setter

    ...
}


package jpabook.start;

public enum RoleType {
    ADMIN, USER
}
코드 설명

roleType : 자바의 enum을 사용해서 회원 타입을 구분. 자바의 enum을 사용하려면 @Enumerated 어노테이션으로 매핑.
createDate, lastModifiedDate : 자바의 날짜 타입은 @Temporal을 사용해서 매핑
description : 회원을 설명하는 필드는 길이 제한이 없다. 데이타베이스 VARCHAR 타입 대신에 CLOB 타입으로 저장.
@Lob를 사용하면 CLOB, BLOB 타입을 매핑할 수 있다.
4.4 데이터베이스 스키마 자동 생성
JPA는 데이터베이스 스키마를 자동으로 생성하는 기능을 지원

<property name="hibernate.hbm2ddl.auto" value="create" />
어플리케이션 실행 시점에 데이터베이스 테이블을 자동으로 생성한다.

<property name="hibernate.show_sql" value="true" />
콘솔에 실행되는 DDL을 출력한다.

스키마 자동 생성 기능이 만든 DDL은 운영환경에서 사용할 만큼 완벽하지 않음.
개발 환경에서 사용하거나 매핑시 참고하는 용도로 사용.
하이버네이트 설정 옵션

hibernate.show_sql : 실행한 SQL을 출력.
hibernate.format_sql : SQL을 보기 좋게 정렬함.
hibernate.use_sql_comments : 쿼리 출력 시 주석도 함께 출력
hibernate.id.new_generator_mappings : JPA 표준에 맞는 새로운 키 생성 전략을 사용함.
하이버네이트 설정

create : Session factory가 실행될 때에 스키마를 지우고 다시 생성. 클래스패스에 import.sql 이 존재하면 찾아서, 해당 SQL도 함께 실행함.
create-drop : create와 같지만 session factory가 내려갈 때 스키마 삭제.
update : 시작시, 도메인과 스키마 비교하여 필요한 컬럼 추가 등의 작업 실행. 데이터는 삭제하지 않음.
validate : Session factory 실행시 스키마가 적합한지 검사함. 문제가 있으면 예외 발생.
개발시에는 create가, 운영시에는 auto 설정을 빼거나 validate 정도로 두는 것이 좋아 보인다.
update로 둘 경우에, 개발자들의 스키마가 마구 꼬여서 결국은 drop 해서 새로 만들어야 하는 사태가 발생한다
HBM2DDL 주의사항

운영 서버에서 create, create-drop, update처럼 DLL을 수정하는 옵션은 절대 사용하면 안된다. 오직 개발 서버나 개발 단계에서만 사용 해야한다. 이 옵션들은 운영 중인 데이터베이스의 테이블이나 컬럼을 삭제할 수 있다.

 

개발 환경에 따른 추천 전략은 다음과 같다.

개발 초기 단계는 create 또는 update
초기화 상태로 자동화된 테스트를 진행하는 개발자 환경과 CI 서버는 create 또는 create-drop
테스트 서버는 update 또는 validate
스테이징과 운영 서버는 validate 또는 none
이름 매핑 전략

테이블 명이나 컬럼 명이 생략되면 자바의 카멜케이스 표기법을 언더스코어 표기법으로 매핑한다

하이버네이트는 org.hibernate.cfg.ImprovedNamingStrategy 클래스를 제공한다. 이 클래스는
테이블 명이나 컬럼 명이 생략되면 자바의 카멜케이스 표기법을 언더스코어 표기법으로 매핑한다.

<property name="hibernate.ejb.naming_strategy" value="org.hibernate.cfg.ImprovedNamingStrategy" />
4.5 DDL 생성 기능
제약 조건을 추가할 수 있다.

@Entity
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10) //추가
    private String username;
    ...
}
nullable = false : not null 제약 조건 추가
length = 10 : 크기를 지정
create table MEMBER (
    ...
    NAME varchar(10) not null,
    ...
)
단지 DDL을 자동으로 생성할 때만 사용되고, JPA 실행 로직에는 영향을 주지 않는다. 따라서 스키마 자동 생성 기능을 사용하지 않고 직접 DDL을 만든다면 사용할 이유가 없다. 이 기능을 사용하면 애플리케이션 개발자가 엔티티만 보고도 손쉽게 다양한 제약 조건을 파악할 수 있는 장점이 있다.

4.6 기본 키 매핑
@Entity
public class Member {

    @Id
    @Column(name = "ID")
    private String id;
JPA가 제공하는 데이터베이스 기본 키 생성 전략

데이터베이스 벤더마다 기본 키 생성을 지원하는 방식이 다름

기본키 생성 전략 방식
직접 할당 : 기본 키를 어플리케이션이 직접 할당

자동 생성 : 대리 키 사용 방식

INDENTITY : 기본 키 생성을 데이터베이스에 위임.
SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본 키를 할당.
TABLE : 키 생성 테이블을 사용한다.
기본키 생성 방법

기본 키를 직접 할당 : @Id만 사용
자동 생성 전략 사용 : @GeneratedValue 추가 및 키 생성 전략 선택.
키 생성 전략 사용을 위한 속성 추가

<property name="hibernate.id.new_generator_mappings" value="true" />
4.6.1 기본 키 직접 할당 전략
기본 키를 직접 할당하려면 다음 코드와 같이 @Id로 매핑하면 된다.

@Id
@Column(name = "id")
private String id;
@Id 적용 가능한 자바 타입

자바 기본형
자바 래퍼형
String
java.util.Date
java.sql.Date
java.math.BigDecimal
java.math.BigInteger
기본 키 할당하는 방법

Board board = new Board();
board.setId("id1");         //기본 키 직접 할당
em.persist(board);
4.6.2 IDENTITY 전략
IDENTITY는 기본 키 생성을 데이타베이스에 위임하는 전략.
주로 MySQL, PostgreSQL, SQL Server, DB2, H2에서 사용.
MySQL의 AUTO_INCREMENT 기능

CREATE TABLE BOARD {
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DATA VARCHAR(255)
};

INSERT INTO BOARD(DATA) VALUES('A');
INSERT INTO BOARD(DATA) VALUES('B');
Board 테이블 결과

ID	DATA
1	A
2	B
IDENTITY 전략

데이터베이스에 값을 저장하고 나서야 기본 키 값을 구할 수 있을 때 사용.
em.persist() 호출시 INSERT SQL을 즉시 데이터베이스에 전달.
식별자를 조회해서 엔티티의 식별자에 할당.
쓰기 지연이 동작하지 않는다.
@Entity
public class Professor {
  @Id 
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String name;
  private long salary;
  ...
}
4.6.3 SEQUENCE 전략
유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트
주로 오라클, PostgreSQL, DB2, H2 데이터베이스에서 사용.

시퀀스 관련 SQL

CREATE TABLE BOARD (
    ID BIGINT NOT NULL PRIMARY KEY,
    DATA VARCHAR(255)
)

//시퀀스 생성
CREATE SEQUENCE BOARD_SEQ START WITH 1 INCREMENT BY 1;
시퀀스 매핑 코드

@Entity
@SequenceGenerator(
    name = "BOARD_SEQ_GENERATOR",
    sequenceName = "BOARD_SEQ",
    initialValue = 1,
    allocationSize = 1)
public class Board {

    @Id
    @GeneraedValue(strategy = GenerationType.SEQUNCE,
                    generator = "BOARD_SEQ_GENERATOR")
    private Long id;
}
시퀀스 사용 코드

private static void logic(EntityManager em) {
    Board board = new Board();
    em.persist(board);
    System.out.println("board.id = " + board.getId());
}
시퀀스 사용 코드는 IDENTITY 전략과 가티만 내부 동작 방식은 다르다.

먼저 데이터베이스 시퀀스를 사용해서 식별자를 조회.
조회한 식별자를 엔티티에 할당.
엔티티를 영속성 컨텍스트에 저장.
트랜잭션 커밋.
플러시 - 데이터베이스에 저장.
주의

equenceGenerator.allocationSize 기본값이 50.
반드시 1로 설정.
@SepuenceGenerator

속성	기능	기본값
name	식별자 생성기 이름	필수
sequenceName	데이터베이스에 등록되어 있는 시퀀스 이름	hibernate_sequence
initialValue	DDL 생성 시에만 사용됨. 시퀀스 DDL을 생성할 때 처음 시작 하는수를 지정한다.	1
allocationSize	시퀀스 한 번 호출에 증가하는 수(성능 최척화에 사용됨)	50
catalog.schema	데이터베이스 catalog, schema 이름	 
매핑할 DDL은 다음과 같다.

create sequence [sequenceName] 
start with [initialValue] increment by allocationSize
jpa 표준 명세서에는 SEQUENCEnAME의 기본 값을 jpa 구현체가 정의하도록 헀다.
위에서 설명한 기본값은 하이버네이트 기준이다.

4.6.4 TABLE 전략
키 생성 전용 테이블을 하나 만들고 여기에 이름과 값을 사용할 컬럼을 만들어 데이터베이스 시퀀스를 흉내내는 전략.
테이블을 사용하므로 모든 데이터베이스에 적용 할 수 있다.
TABLE 전략 키 생성 테이블

create table MY_SEQUENCES (
    sequence_name varchar(255) not null,
    next_val bigint,
    primary key (sequence_name)
)
SEQUENCE_NAME 컬럼을 시퀀스 이름으로 사용하고 NEXT_VAL 컬럼을 시퀀스 값으로 사용한다. 참고로
컬럼의 이름은 변경할 수 있는데 여기서 사용한 것이 기본 값이다.

TABLE 전략 매핑 코드

@Entity
@TableGenerator(
    name = "BOARD_SEQ_GENERATOR",
    table = "MY_SEQUENCES",
    pkColumnValue = "BOARD_SEQ", allocationSize = 1)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
                generator = "BOARD_SEQ_GENERATOR")
    private Long id;
}
@TableGenerator을 사용해서 테이블 키 생성기를 등록 (BOARE_SEQ_GENERATOR라는 이름의 테이블 키 생성기를 등록하고 방금 생성한 My_SEQUENCES 테이블을 키 생성용 테이블로 매핑함)
TABLE 전략을 사용하기 위해 GenerationType.TABLE을 선택했다.
@GeneratedBalue.generator에 방금 만든 테이블 키 생성기를 지정.
이제부터 id 식별자 값은 BOARD_SEQ_GENERATOR 테이블 키 생성기가 할당.
TABLE 전략 매핑 사용 코드

private static void logic(EntityManger em) {
    Board board = new Board();
    em.persist(board);
    System.out.println("board.id = " + board.getId());
}
// 출력 : board.id = 1
MY_SEQUENCES 테이블에 값이 없으면 JPA가 값을 INSERT
미리 넣어둘 필요가 없다.
시퀀스 대신에 테이블을 사용한다는 것만 제외하면 SEQUENCE 전략과 동일

 

@TableGenerator

@TableGenerator 속성 정리

속성	기능	기본값
name	식별자 생성기 이름	필수
table	키생성 테이블명	hibernate_sequences
table	키생성 테이블명	hibernate_sequences
pkCoumnName	시퀀스 컬럼명	sequence_name
valueColumnName	시퀀스 값 컬럼명	next_val
pkColumnValue	키로 사용할 값 이름	엔티티이름
initialValue	초기 값, 마지막으로 생성된 값이 기준이다.	0
allocationSize	시퀀스 한 번 호출에 증가하는 수(성능 최적회에 사용됨)	50
catalog, schema	데이터베이스 catalog, schema 이름	 
uniqueConstraints(DDL)	유니크 제약 조건을 지정할 수 있다.	 
JPA 표준 명세서에는 table, pkColumnName, valueColumnName의 기본값을 JPA 구현체가 정의하도록 했다. 위에서 설명한 기본값은 하이버네이트 기준이다.

 

표 4.8 매핑할 DDL,테이블명{table}


| {pkColumnName} | {valueColumnName} |
|----------| ------------------- |
|{pkColumnName}|{initialValue}|

 

TABLE 전략과 최적화

TABLE 전략은 값을 조회하면서 SELECT 쿼리를 사용하고 다음 값으로 증가시키기 위해 UPDATE 쿼리를 사용한다.
이 전략은 SEQUENCE 전략과 비교해서 데이터베이스와 한 번 더 통신하는 단점이 있다. TABLE 전략을 최적화하려면 @TableGenerator.allocationSize를 사용하면 된다. 이 값을 사용해서 최적화하는 방법은 SEQUENCE 전략고 같다.

4.6.5 AUTO 전략
GenerationType.AUTO는 선택한 데이터베이스 방언에 따라 INDENTITY, SEQUENCE, TABLE 전략 중 하나를 자동으로 선택.

AUTO 전략 매핑 코드

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    ...
}
@GeneratedValue.strategy의 기본값은 AUTO.

다음과 같이 사용해도 결과는 같다.

import javax.persistence.GeneratedValue;

@Id @GeneratedValue
private Long id;
장점

데이터베이스를 변경해도 코드를 수정할 필요가 없다.
키 생성 전략이 확정되지 않은 개발 초기 단계, 프로토타입 개발시 편리.
4.6.6 기본 키 매핑 정리
영속성 컨텍스트는 엔티티를 식별자 값으로 구분하므로 엔티티를 영속 상태로 만들려면 식별자 값이
반드시 있어야 한다. em.persist()를 호출한 직후에 발생하는 일을 식별자 할당 전략별로 정리하면 다음과 같다.

직접 할당 : em.persist()를 호출하기 전에 애플리케이션에서 직접 식별자 값을 할당해야 한다. 만약 식별자 값이 없으면 예외가 발생한다.
SEQUENCE : 데이터베이스 시퀀스에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장한다.
TABLE : 데이터베이스 시퀀스 생성용 테이블에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장한다.
IDENTITY : 데이터베이스에 엔티티를 저장해서 식별자 값을 획득한 후 영속성 컨텓스트에 저장한다(IDENTITY 전략은 테이블에
데이터를 저장해야 식별자 값을 획득할 수 있다.)
권장하는 식별자 선택 전략
데이터베이스 기본 키는 다음 3가지 조건을 모두 만족해야 한다.

null값을 허용하지 않는다.
유일해야 한다.
변해선 안 된다.
테이블의 기본 키를 선택하는 전략은 크게 2가지가 있다.

자연 키
비지니스에 의미가 있는 키
주민등록번호, 이메일, 전화번호
대리 키
비지니스와 관련 없는 임의로 만들어진 키, 대체 키.
오라클 시퀀스, auto_increment, 키생성 테이블
기본 키의 조건을 현재는 물론이고 미래까지 충족하는 자연 키를 찾기는 쉽지 않다.
대리 키는 비즈니스와 무관한 임의의 값이므로 요구사항이 변경되어도 기본 키가 변경되는 일은 드물다.
대리 키를 기본키로 사용하되 주민등록번호나 이메일처럼 자연 키의 후보가 되는 컬럼들은 필요에 따라 유니크 인덱스를 설정해서 사용하는 것을 권장한다.

그래서 JPA는 모든 엔티티에 일관된 방시으로 대리 키 사용을 권장한다.


주의
기본 키는 변하면 안된다는 기본 원칙으로 인해, 저장된 엔티티의 기본 키 값은 절대 변경하면 안 된다. 이 경우 JPA는 예외를 발생시키거나 정상 동작하지 않는다. setId() 같이 식별자를 수정하는 메소드를 외부에 공개하지 않는 것도 문제를 예방하는방법이 될 수 있다.

4.7 필드와 컬럼 매핑 : 레퍼런스
필드와 컬럼 매핑

@Column : 컬럼을 매핑한다

@Enumerated : 자바의 enum 타입을 매핑한다.

@Temporal : 날짜 타입을 매핑한다.

@Lob : BLOB, CLOB 타입을 매핑한다.

@Transient : 특정 필드를 데이터베이스에 매핑하지 않는다.

기타

@Access : JPA가 엔티티에 접근하는 방식을 지정한다.

@Column

@Column의 속성

name : 맵핑할 테이블의 컬럼 이름을 지정합니다;
insertable : 엔티티 저장시 선언된 필드도 같이 저장합니다.
updateable : 엔티티 수정시 이 필드를 함께 수정합니다.
table : 지정한 필드를 다른 테이블에 맵핑할 수 있도록 합니다.
nullable : NULL을 허용할지, 허용하지 않을지 결정합니다.
unique : 제약조건을 걸 때 사용합니다.
columnDefinition : DB 컬럼 정보를 직접적으로 지정할 때 사용합니다.
length : varchar의 길이를 조정합니다. 기본값으로 255가 입력됩니다.
precsion, scale : BigInteger, BigDecimal 타입에서 사용합니다. 각각 소수점 포함 자리수, 소수의 자리수를 의미합니다.
@Enumerated

value(속성)
EnumType.ORDINAL : enum 순서를 데이터베이스에 저장 (기본 값)
EnumType : enum 이름을 데이터베이스에 저장
EnumType.ORDINAL은 enum에 정의된 순서대로 ADMIN은 0, USER는 1 값이 데이터베이스에 저장된다.

장점 : 데이터베이스에 저장되는 데이터 크기가 작다.
단점 : 이미 저장된 enum의 순서를 변경할 수 없다.
EnumType.STRING은 enum 이름 그대로 ADMIN은 'ADMIN', USER는 'USER'라는 문자로 데이터베이스에 저장된ㄷ.

장점 : 저장된 enum의 순서가 바뀌거나 enum이 추가되어도 안전하다.
단점 : 데이터베이스에 저장되는 데이터 크기가 ORDINAL에 비해서 크다.
주의
기본값인 ORDINAL은 주의해서 사용해야 된다.

ADMIN(0번), USER(1번) 사이에 enum이 하나 추가되서 ADMIN(0번). NEW(1번), USER(2번)로 설정되면서 이제부터 USER는 2로
저장되지만 기존에 데이터베이스에 저장된 값은 여전히 1로 남아 있다. 따라서 이런 문제가 발생하지 않는 EnumType.STRING을 권장한다.

 

@Temporal

속성
TemporalType.DATE : 날짜, 데이터베이스, date 타입과 매핑(예 : 2013-10-11)
TemporalType.TIME : 시간, 데이터베이스 time 타입과 매핑(예 : 11:11:11)
TemporalType.TIMESTAMP : 날짜와 시간, 뎅터베이스 timestamp 타입과 매핑(예 : 2013-10-11 11:11:11)
TemporalType은 필수로 지정해야 한다.
ex)

@Temporal(Temporal.TIMESTAMP) // TIMESTAMP 대신에 DATE or TIME가 들어가 수 있다.
private Date timetamp;
생성된 DDL

timestamp timestamp;

 

@Lob

속성 정리

@Lob에는 지정할 수 있는 속성이 없다. 대신에 매핑하는 필드 타입이 문자면 CLOB으로 매핑하고 나머지는 BLOG로 매핑한다

CLOB : String, char[], java.sql.CLOB
BLOB : byte[], java.sql.BLOB
@Transient

이 필드는 매핑하지 않는다. 따라서 데이터베이스에 저장하지도 않고 조회하지도 않는다. 객체에 임시로 어떤 값을 보관하고 싶을 때 사용한다.

 

@Access

JPA가 엔티티 데이터에 접근하는 방식을 지정

필드 접근
-AccessType.FIELD로 지정
필드에 직접 접근
private이어도 접근
프로퍼티 접근
AccessType.PROPERTY로 접근
접근자(Getter)를 사용
 



출처: https://junghyungil.tistory.com/141?category=943340 [인생을 코딩하다.]
