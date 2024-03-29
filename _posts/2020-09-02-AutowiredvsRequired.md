---
title : "@Autowired vs @Required on setter"
date : 2020-09-02
categories : [why]
---
+ @Autowired vs @Required on setter
	+ 두코드 사이의 차이점이 뭔가요?
	
	```java
	class MyClass {
	   @Autowired
	   MyService myService;
	}

	class MyClass {
	   MyService myService;

	   @Required
	   public void setMyService(MyService val) {
	       this.myService = val;
	   }
	}
	```
	
	+ 선택된 답변
		+ _@Autowired_ 는 지정된 dataType에 맞는 데이터를 자동으로 주입해 줍니다. 사용에는 제한이 없습니다. 생성자나 필드에도 사용가능하죠.
	_@Required_ 는 부분적인 속성의 존재여부를 확인합니다. 때문에, 해당 _@Required_ 을사용한 속성의 경우, 해당 값이 없을 경우, 예외를 발생합니다.
		+ _@Autowired(required=false)_ 형태를 통해 _@Autowired_ 에서도 값을 체크 가능합니다.

	[stackOverflow](https://stackoverflow.com/questions/18884670/autowired-vs-required-on-setter/18887438)
	
