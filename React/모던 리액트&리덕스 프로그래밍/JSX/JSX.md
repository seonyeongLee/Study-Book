# JSX

* 자바스크립트를 확장한 언어
* 직관적으로 코드작성 가능

## JSX 문법
1. 스코프에 React가 있어야 함
    - JSX 태그 -> React.createElement 함수를 호출하는 형태로 변환
    - import React from 'react';

2. 인라인 표현식
    - {}로 감싸서 1+1 또는 someFunction()등의 표현식을 JSX 내부에 삽입
    - const fullnames = { inseong: '윤인성', mina: '윤미나' };
    
        const getFullname = nickname => fullnames[nickname];
        
        const element = < h1 >Hello, {getFullname('inseong')}</ h1 >;

3. JSX를 표현식으로 다루기

4. 속성 지정
    - <>내부에 <속성 이름>="값" 형태로 작성하면 해당 속성 값이 문자열로 들어감

5. 자식 요소 지정
    - const name = 'LSY';

        const heading = ( < h1 > Hello, {name} </ h1 > );


6. 빈 요소는 반드시 닫기
    - 자식 요소가 없는 태그를 만들 떄는 />를 명시적으로 지정해서 태그 닫기

### React.createElement 함수를 사용하는 것이 좋은 경우
* 첫 번째 매개변수 : 태그의 이름(또는 컴포넌트의 생성자)
* 두 번째 매개변수 : 속성(props)
* 세 번째 매개변수 이후 : 자식요소 지정
<br/>
* 속성과 자식 요소를 완전히 같게 사용하고 그의 이름만 변경하고 싶은 경우
