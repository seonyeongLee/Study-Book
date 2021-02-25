* 프로젝트 환경설정 파일
      
      1. web.xml : 웹 어플리케이션 환경설정 파일
      2. webAppConfig.xml : 스프링 웹 관련 환경설정 파일
                          : 스프링 MVC 설정 + 웹 계층 담당
      3. appConfig.xml : 스프링 애플리케이션 관려 환경설정 파일
                       : 비즈니스 로직, 도메인 계층, 서비스 계층, 데이터 저장 계층 담당

* 의존성 전이(Transition dependency)
  
      spring-mvc는 spring-core에 의조
      => spring-mvc사용시 spring-core필요

* Given, When, Then
      
      Given : 테스트할 상황을 설정
      When  : 테스트 대상을 실행
      Then  : 결과를 검증
