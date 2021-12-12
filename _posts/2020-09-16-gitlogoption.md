---
title : git log option
date : 2020-09-16
categories : tip
---
+ git log option 정리
+ 주요 옵션
+ 예)

```
$ git log --name-only -2
commit c555ea057d291bcca9e4c880505bde5159fa1a5e (HEAD -> dev, origin/master, origin/dev, master)
Author: ayj1002
Date:   Wed Sep 16 09:24:52 2020 +0900

    포틀릿 사진 url 변경

src/main/java/egovframework/com/cop/bbs/web/EgovBBSManageController.java

commit 3411d65791260e9d33e38c61cdd2cfeded475283
Author: dackpro00
Date:   Mon Sep 14 15:58:05 2020 +0900

    포틀릿 jsop clean 적용 , 사용자 정보 확인 API 추가 및 예외 처리

.classpath
.gitignore
.settings/org.eclipse.wst.common.component
pom.xml
src/main/java/egovframework/com/cop/bbs/web/EgovBBSManageController.java
src/main/java/egovframework/com/uat/uia/web/EgovSessionInterceptor.java
src/main/java/egovframework/com/uat/uia/web/LoginCheckController.java
src/main/resources/egovframework/egovProps/globals.properties
```

|옵션|설명|
|:----|:-----|
|-p | 각 커밋에 적용된 패치를 보여준다.|
|--stat | 각 커밋에서 수정된 파일의 통계정보를 보여준다.|
|--shortstat | --stat 명령의 결과 중에서 수정한 파일, 추가된 라인, 삭제된 라인만 보여준다.|
|--name-only | 커밋 정보중에서 수정된 파일의 목록만 보여준다.|
|--name-status | 수정된 파일의 목록을 보여줄 뿐만 아니라 파일을 추가한 것인지, 수정한 것인지, 삭제한 것인지도 보여준다.|
|--abbrev-commit | 40자 짜리 SHA-1 체크섬을 전부 보여주는 것이 아니라 처음 몇 자만 보여준다.|
|--relative-date | 정확한 시간을 보여주는 것이 아니라 “2 weeks ago” 처럼 상대적인 형식으로 보여준다.|
|--graph | 브랜치와 머지 히스토리 정보까지 아스키 그래프로 보여준다.|
|--pretty | 지정한 형식으로 보여준다. 이 옵션에는 oneline, short, full, fuller, format이 있다. format은 원하는 형식으로 출력하고자 할 때 사용한다.|
|--oneline | --pretty=oneline --abbrev-commit 두 옵션을 함께 사용한 것과 같다.|

+ 출력 포멧 형식 옵션
+예)

```
$ git log --pretty=format:"%h - %an, %ar : %s"
ca82a6d - Scott Chacon, 6 years ago : changed the version number
085bb3b - Scott Chacon, 6 years ago : removed unnecessary test
a11bef0 - Scott Chacon, 6 years ago : first commit
```

|옵션|설명|
|:--|:--|
|%H | 커밋 해시|
|%h | 짧은 길이 커밋 해시|
|%T | 트리 해시|
|%t | 짧은 길이 트리 해시|
|%P | 부모 해시|
|%p | 짧은 길이 부모 해시|
|%an | 저자 이름|
|%ae | 저자 메일|
|%ad | 저자 시각 (형식은 –-date=옵션 참고)|
|%ar | 저자 상대적 시각|
|%cn | 커미터 이름|
|%ce | 커미터 메일|
|%cd | 커미터 시각|
|%cr | 커미터 상대적 시각|
|%s | 요약|


+ 필터 기능
+ 예)

```
$ git log -3
commit c555ea057d291bcca9e4c880505bde5159fa1a5e (HEAD -> dev, origin/master, origin/dev, master)
Author: ayj1002
Date:   Wed Sep 16 09:24:52 2020 +0900

    포틀릿 사진 url 변경

commit 3411d65791260e9d33e38c61cdd2cfeded475283
Author: dackpro00
Date:   Mon Sep 14 15:58:05 2020 +0900

    포틀릿 jsop clean 적용 , 사용자 정보 확인 API 추가 및 예외 처리

commit 626b2e24824c50629a8dcfcb8aa16a64685b8d42
Author: SVN관리
Date:   Thu Sep 3 16:08:22 2020 +0900

    업데이트 'README.md'

```

|옵션|설명|
|:---|:---|
|-(n) | 최근 n 개의 커밋만 조회한다.|
|--since, --after | 명시한 날짜 이후의 커밋만 검색한다.|
|--until, --before | 명시한 날짜 이전의 커밋만 조회한다.|
|--author | 입력한 저자의 커밋만 보여준다.|
|--committer | 입력한 커미터의 커밋만 보여준다.|
|--grep | 커밋 메시지 안의 텍스트를 검색한다.|
|-S | 커밋 변경(추가/삭제) 내용 안의 텍스트를 검색한다.|


[참조문서](https://git-scm.com/book/ko/v2/Git%EC%9D%98-%EA%B8%B0%EC%B4%88-%EC%BB%A4%EB%B0%8B-%ED%9E%88%EC%8A%A4%ED%86%A0%EB%A6%AC-%EC%A1%B0%ED%9A%8C%ED%95%98%EA%B8%B0)
