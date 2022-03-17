<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
"**Contents**"

- [프로젝트 생성](#%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%83%9D%EC%84%B1)
- [라이브러리 살펴보기](#%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%82%B4%ED%8E%B4%EB%B3%B4%EA%B8%B0)
- [View 환경 설정](#view-%ED%99%98%EA%B2%BD-%EC%84%A4%EC%A0%95)
- [H2 데이터베이스 설치](#h2-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%84%A4%EC%B9%98)
- [JPA와 DB 설정, 동작확인](#jpa%EC%99%80-db-%EC%84%A4%EC%A0%95-%EB%8F%99%EC%9E%91%ED%99%95%EC%9D%B8)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# 프로젝트 생성

install Lombok plugin  
Preferences | Build, Execution, Deployment | Compiler | Annotation Processors > Enable annotation processing

# 라이브러리 살펴보기

`./gradlew dependencies`  
`implementation 'org.hibernate.validator:hibernate-validator:7.0.4.Final'` 추가

# View 환경 설정

org.thymeleaf:thymeleaf:3.0.15.RELEASE  
장점: 네추럴 템플릿, 그냥 브라우저에서 열림.  
단점: 2버전 html 문법에 예민. 3버전에서 나아졌음.  

[스프링 공식 튜토리얼](https://spring.io/guides/gs/serving-web-content/)

http://127.0.0.1:8080/hello

`implementation 'org.springframework.boot:spring-boot-devtools'` 추가
서버 재시작 없이 View 파일 변경됨. > 인텔리J 컴파일 방법: 메뉴 build Recompile

# H2 데이터베이스 설치

```
$ brew install h2
$ h2
```

jdbc:h2:\~/jpashop (최소 한번)  
\~/jpashop.mv.db 파일 생성 확인  
이후 부터는 jdbc:h2:tcp://localhost/\~/jpashop 접속  

# JPA와 DB 설정, 동작확인

[GitHub - gavlyukovskiy/spring-boot-data-source-decorator: Spring Boot integration with p6spy, datasource-proxy, flexy-pool and spring-cloud-sleuth](https://github.com/gavlyukovskiy/spring-boot-data-source-decorator)

`implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0'`
```
2022-03-17 01:54:17.306  INFO 75003 --- [           main] p6spy                                    : #1647449657306 | took 0ms | statement | connection 3| url jdbc:h2:tcp://localhost/~/jpashop
select member0_.id as id1_0_0_, member0_.username as username2_0_0_ from member member0_ where member0_.id=?
select member0_.id as id1_0_0_, member0_.username as username2_0_0_ from member member0_ where member0_.id=1;
```
