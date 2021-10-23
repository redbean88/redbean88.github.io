---
titie : iframe 메세지 전달
date : 2020-08-20 00:00:00 +0000
categories : tip
---
+ 기본
	+ 송신
	```
	window.parent.postMessage( data, [ports], targetOrigin ); //자식 > 부모
	child.contentWindow.postMessage( data, [posts] , targetOrigin ); // 부모 > 자식
	```
	+ 수신
	```
	window.addEventListener( 'message', function( e ) {
    	// e.data가 전달받은 메시지
    	console.log( e.data );
	} );
	```
출처 : http://blog.302chanwoo.com/2016/08/postmessage/
