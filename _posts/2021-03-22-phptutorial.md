---
date : 2021-03-22 00:00:00 +0000
title : php-tutorial
categories: [php]
---

# 본문서는 [php 튜토리얼](https://www.everdevel.com/php/intro) 문서를 요약한 문서입니다.

# 1. PHP 소개
  - APM : (Apache + php + mysql)

# 2. 출력문
   - php를 시작하기 위해서는 `<?php`를 작성하여 시작하고 `?>`를 작성하여 끝낸다
   - `<?`과 `php` 사이의 공백이 있는 형태는 정상 작동하지 않는다.

       예)`<? php`
   - hello world를 출력하는 예제
   ```
   <?php
    echo "hello world";
   ?>
   ```
   - echo 는 대소문자를 구분 하지 않는다.
   - 세미콜론(;)은 반드시 필요한 것은 아니다
   - 쌍따옴표("")가 아닌 따옴표('')로 표현도 가능하다.

# 3. 주석
  - 다중 주석 `/**/`
  - 한줄 주석 `//`
  - 주석 예제
  ```
  <?php
    //echo "hello world";
  ?>
  ```

# 4. 변수
  - 유용한 함수
    - gettype()을 사용하면 데이터형 확인 가능
    - var_dump()함수는 테이터형과 값을 출력
  - 다른 언어와 달리 변수를 선언할때 $(스트링)을 붙여서 사용
  - 변수 선언시에는 @,#,%,&,- 등의 숫자는 사용 불가
  - echo ""; 안에서 변수명 출력이 필요한 경우 `\` 를 붙여주면 변수명 출력 (따옴표('')를 사용시 미적용되며 문자 그대로 표시)
  - php는 변수의 데이터형을 따로 지정해 주지 않으며, 변수에 따라서 데이터형이 자동으로 형 변환
  - 변수 선언은 $변수면 = 초기화 데이터 형태를 사용
  - 에제
  ```
  <?php
    $test = "test";
    echo "\$test is $test";
  ?>
  ```
  - 정수형의 오버플로우 형변환
  - 정수값 표현을 초과하게 되면 정수형이 아닌 실수형으로 넘어감
  - 예제(32bit)
  ```
  <?php
  $int = 2147483647;
  echo "\$int id ";
  var_dump($int);
  echo "<br/>";
  $int += 1;
  echo "\$int id ";
  var_dump($int);
  echo "<br/>";
  ?>
  ```
  - 예제(64bit)
  ```
  <?php
  $int = 9223372036854775807;
  echo "\$int id ";
  var_dump($int);
  echo "<br/>";
  $int += 1;
  echo "\$int id ";
  var_dump($int);
  echo "<br/>";
  ?>
  ```
  - 변수명 입력 주의점
  - 변수명 입력시, 띄어쓰기 또는 {} 로 확실한 구분 필요
  - 예제
  ```
  <?php
  $name = "test";
  echo "$nameis a test";
  echo "$name is a test";
  echo "{$name} is a test";
  ?>
  ```

# 5. 상수
  - 변수와 달리 $를 미사용
  - 세번째 agument는 대소문자 구분
  - 상수는 최초 할당된 값이 변하지 않음
  - define("상수명","상수값",대소문자구별여부(true/false))
  - 출력시 상수명을 그대로 입력합니다.
  - 예제
  ```
  <?php
  define('test','test',true);
  echo test
  ?>
  ```
