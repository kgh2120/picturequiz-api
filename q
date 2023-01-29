[33mcommit 5693a87f110caa5605e90b245e229ec7404a86f6[m[33m ([m[1;36mHEAD -> [m[1;32mfind-id-password[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Nov 27 23:42:02 2022 +0900

    FIX : 테스트 오류 해결

[33mcommit e54f9dad0840552293245a9b91ac09e2293351dd[m[33m ([m[1;31morigin/find-id-password[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Nov 27 23:34:09 2022 +0900

    FEAT : 아이디 찾기 & 임시 비밀번호 사용 기능 추가

[33mcommit 365d005e13dda846eef3007539acb372aeb23813[m[33m ([m[1;31morigin/dev[m[33m, [m[1;32mdev[m[33m)[m
Merge: c657ef3 4aae58c
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Thu Nov 24 14:38:26 2022 +0900

    Merge pull request #56 from kgh2120/change-limit
    
    페이징 쿼리 limit 수정

[33mcommit 4aae58cd2ec21fb99c707eb33d7c673a4495a637[m[33m ([m[1;32mchange-limit[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Nov 24 14:36:44 2022 +0900

    CHORE : 테스트 통과 하도록 수정

[33mcommit 5567b7b8f39e710c82b66916907b634c00ff4fa9[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Nov 24 14:32:32 2022 +0900

    CHORE : 페이징 쿼리 limit 수정

[33mcommit c657ef3e3adb451d914c6b60a3e2724486524dee[m
Merge: cb77e90 f227020
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Tue Nov 22 15:48:38 2022 +0900

    Merge pull request #55 from kgh2120/change-character-domain
    
    캐릭터 도메인 관련 로직 삭제

[33mcommit f2270202a15c1080f9fe7209e7453ff274b1c2cd[m[33m ([m[1;32mchange-character-domain[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Nov 22 15:45:45 2022 +0900

    FEAT : 캐릭터 관련 로직 삭제
    
    - 캐릭터 도메인 관련 로직 삭제
    - 퀴즈 도메인에 캐릭터 연관 필드는 캐릭터 이름만 유지
    - 퀴즈 추가 시 전달 받는 속성 캐릭터ID -> 캐릭터이름 으로 수정

[33mcommit cb77e90310bbeffe1678877e7d20eab4bc4af17d[m
Merge: 0bb1e13 296bc2c
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Fri Nov 18 10:41:10 2022 +0900

    Merge pull request #54 from kgh2120/db-setting
    
    새로운 프로필 설정

[33mcommit 296bc2ccef1bc779aa54a81ddf9867172adbb17d[m[33m ([m[1;32mdb-setting[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 18 10:38:06 2022 +0900

    TEST : 테스트 충돌 해결
    
    - 테스트 프로필에 맞느 설정파일 추가

[33mcommit 0f9990fbe10cfb981092563120525f83bcdf22e7[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 18 10:33:57 2022 +0900

    TEST : 테스트 충돌 해결
    
    - 테스트 프로필에 맞느 설정파일 추가

[33mcommit 1d0901862ed0f9a0c9d05b11bbcc19bdf2183009[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 18 10:27:49 2022 +0900

    BUILD : 새로운 프로필 관련 설정
    
    - github-action 설정 파일 수정
    - 도커 파일 수정

[33mcommit 875702f5d60015a8d9791e876f0cd12639519615[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 18 10:22:51 2022 +0900

    BUILD : Profile 분리해서 실행시키기

[33mcommit 0bb1e13c2fb1a46ac05dbcfc6b3e0eff4ea52c96[m
Merge: db143fe b5af3af
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Thu Nov 17 01:16:51 2022 +0900

    Merge pull request #53 from kgh2120/refresh-token
    
    RefreshToken 저장 및 업데이트 로직 수정

[33mcommit b5af3afcd1e083f1c6da155d2a984838b3dbfade[m[33m ([m[1;32mrefresh-token[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Nov 17 01:12:45 2022 +0900

    CHORE : RefreshToken 저장 및 업데이트 로직 수정

[33mcommit db143feb33a9eaf19463b5f59825b6efa22e89cd[m
Merge: 86ab3e2 e3517ef
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Wed Nov 16 15:22:57 2022 +0900

    Merge pull request #52 from kgh2120/validate-input
    
    유효성 검사 추가

[33mcommit e3517efc1c094d68f784d7d7a652ba39d1be94c6[m[33m ([m[1;32mvalidate-input[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Wed Nov 16 15:20:40 2022 +0900

    TEST : 유효성 검사 추가로 인한 컨트롤러 테스트케이스 입력값 변경

[33mcommit be1bd4ddf8d055c9426099981a37acb35c734bde[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Wed Nov 16 15:07:08 2022 +0900

    FEAT : 유효성 관련 예외 처리 추가

[33mcommit 95bd5b22beb774b117bf71810fea0c4e47db835e[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sat Nov 12 20:46:12 2022 +0900

    FEAT : 유효성 검사 추가

[33mcommit 86ab3e2fdfb4c9feab595b0430ff60c2643d3f05[m
Merge: ac249eb 6085d8b
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Sat Nov 12 14:19:07 2022 +0900

    Merge pull request #51 from kgh2120/tag-color
    
    태그 색상 추가

[33mcommit 6085d8b65550a872c6dfd4747057c5e5b6cc876b[m[33m ([m[1;31morigin/tag-color[m[33m, [m[1;32mtag-color[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sat Nov 12 14:15:35 2022 +0900

    FEAT : 태그 색상 추가

[33mcommit 6bc52467870d24d50c9c6f9fbd80ea5ef49407e8[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sat Nov 12 12:59:14 2022 +0900

    BUILD : docker-compose 설정

[33mcommit ac249eb4cdb1fe832f21cf00a85bdbaf13659013[m
Merge: 62d762a 8fe2caf
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Fri Nov 11 20:40:30 2022 +0900

    Merge pull request #50 from kgh2120/my-page-function
    
    마이 페이지 기능 추가

[33mcommit 8fe2caf14bc378f5e12647cb8f03d4716cdf9300[m[33m ([m[1;32mmy-page-function[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 11 20:37:52 2022 +0900

    CHORE : 비밀번호 변경 예외 추가

[33mcommit b3bb1ae2ac5801bb5972a245624733d41bcab6d3[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 11 20:32:15 2022 +0900

    FEAT : 비밀번호 변경 & 회원 탈퇴 기능 추가

[33mcommit d68f3e247ac10ad5fed59f4a38cec06faea23c5b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 11 19:55:32 2022 +0900

    BUILD : Docker 연결을 위한 Redis 설정 변경

[33mcommit 62d762ad6afa51c2d7d9fc6b51458354ad7c00b6[m
Merge: e740f8a 6138609
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Fri Nov 11 13:48:33 2022 +0900

    Merge pull request #48 from kgh2120/fix-by-front
    
    프론트 엔드 요청에 따른 백엔드 설정 변경

[33mcommit 6138609961951dc90f51954b85a70cd4b4373bc3[m[33m ([m[1;32mfix-by-front[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 11 13:46:45 2022 +0900

    FIX : 테스트 시 사용되는 CacheManager 변경
    
    SpringBootTest를 진행할 때, Redis로 캐시가 저장되어서, 원하는 테스트 결과가 나오지 않아서, 테스트 환경에서는
    RedisCacheManager가 아닌, ConcurrentMapCacheManager를 사용하도록 설정 해 주었다.

[33mcommit f1dc620de105c61b18b43780552c47d8bba4d6a8[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Nov 11 13:20:24 2022 +0900

    FIX : 프론트 엔드 요청에 따른 백엔드 설정 변경
    
    - 시큐리티 특정 URL에 대한 A&A 변경
    - 캐싱 설정 변경(Tag 추가 시 Cache Put 설정)
    - 퀴즈 검색 limit 증가

[33mcommit e740f8ac7677c4dbc6078fb69c34ef2e665958b2[m
Merge: 771771a 2113924
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Oct 31 21:22:10 2022 +0900

    Merge pull request #46 from kgh2120/issue#45
    
    도메인 클래스 이름 변경

[33mcommit 2113924165cebb55d3113e2c67a2031e78e686c1[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 31 21:18:14 2022 +0900

    FIX : QueryDSL 관련 객체 이름 변경 적용 #42

[33mcommit 17858799e3f4e9d0060b11745b5ba0cb822222ec[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 31 21:10:10 2022 +0900

    REFACTOR : 도메인 클래스이름 변경 #42

[33mcommit 771771aad884dc197ffd6c2b9e2f2d36310e20dd[m
Merge: d14b755 921efba
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Oct 31 21:06:29 2022 +0900

    Merge pull request #45 from kgh2120/issue#35
    
    퀴즈 하기 기능 추가

[33mcommit 921efba1ab5d162142b0314ed6df5b203da6a238[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 31 21:03:41 2022 +0900

    FEAT : 퀴즈 하기 기능 추가 #35

[33mcommit d14b75528ed917cf5eb92b5c8e08de671e9d6114[m
Merge: abcfd84 c0023c0
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Oct 31 20:20:26 2022 +0900

    Merge pull request #44 from kgh2120/issue#34
    
    내가 만든 퀴즈 보기 기능 추가

[33mcommit c0023c0149727a8b0b95662cdbf562c930c8e881[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 31 20:18:18 2022 +0900

    TEST : 테스트 실패 케이스 변경 #34

[33mcommit ab8919e7dbd5362d14df22b1af419e0d6bed507b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 31 20:07:37 2022 +0900

    FEAT : 내가 만든 퀴즈 보기 기능 관련 예외 처리 추가 #34

[33mcommit 9d17c95a154c415ef15e376407b49cc0b1f43655[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 31 19:49:47 2022 +0900

    FEAT : 내가 만든 퀴즈 보기 기능 추가 #34

[33mcommit abcfd8439878f8225f9080d2e9bf03003179d7cd[m
Merge: 882e213 55a3e82
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Oct 17 20:33:05 2022 +0900

    Merge pull request #43 from kgh2120/issue#33
    
    퀴즈 검색하기 기능 추가

[33mcommit 55a3e820ff3c883c70cdecd555e51ffd41789e89[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 17 20:31:07 2022 +0900

    TEST : 테스트 케이스 추가 #33

[33mcommit f5de41c79c7dd07f8d68746df93bc3766afb7ed5[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Oct 17 19:54:36 2022 +0900

    FEAT : 퀴즈 검색 기능 추가 #33

[33mcommit 5619b71b4eb4747ba5a6dcb5f84611f41721acea[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Oct 16 15:44:27 2022 +0900

    BUILD : QueryDsl 설정 추가 #33

[33mcommit 882e213e5773465649fdf288c773e88d0d9c9b3c[m
Merge: daa8c04 11581ca
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Sat Oct 15 15:44:25 2022 +0900

    Merge pull request #41 from kgh2120/issue#32
    
    퀴즈 추가하기 기능

[33mcommit 11581ca1d84fe624c307a8e02a0e77a54b3ab15b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sat Oct 15 15:42:51 2022 +0900

    TEST : 테스트 케이스 수정 #32

[33mcommit fea7505878b78f1238f94ada253ec405b02b355d[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sat Oct 15 15:31:30 2022 +0900

    TEST : 테스트 케이스 추가 #32

[33mcommit 975d8290c3483163de37fd9c3b02cef26bd9a563[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 18:50:37 2022 +0900

    FEAT : 퀴즈 등록 기능 추가 #32

[33mcommit c8a034fb5465f91be0cc4b91f8c9500a7f4c2941[m
Merge: bd8b789 66d49bc
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 17:07:21 2022 +0900

    Merge remote-tracking branch 'origin/issue#32' into issue#32

[33mcommit bd8b7899abf2888f8a764e2f5f3437cc22e6703b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 17:06:39 2022 +0900

    BUILD : s3 관련 의존성 추가 #32

[33mcommit 66d49bc7ab1810ee3fa0bc215b0ead98b4e8568b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 17:06:39 2022 +0900

    FIX : 해시태그 검색 캐싱 적용 안되는 부분 수정 #32

[33mcommit daa8c043eda47a1c5bc19e4b5b9b3ddf7d599cb0[m
Merge: 20986de 3cc026c
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Tue Oct 11 16:26:47 2022 +0900

    Merge pull request #40 from kgh2120/issue#31
    
    태그 추가 기능 추가 #30

[33mcommit 3cc026ce8a471fd13de79aaac6a8f950d4cd36b5[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 16:25:04 2022 +0900

    FIX : 태그 추가 시 exception 잘못 입력한 것 수정 #30

[33mcommit aee70f48dfe5cafc24a02c9d433e3868fed26038[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 16:20:51 2022 +0900

    FEAT : 태그 추가 기능 추가 #30

[33mcommit 20986dec32b657dfe02db07da64c7dd4346e73e5[m
Merge: 62ef2ae f546c92
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Tue Oct 11 15:21:00 2022 +0900

    Merge pull request #39 from kgh2120/issue#30
    
    해시태그 검색 기능 추가 #30

[33mcommit f546c92552bc777f33ab57c9821efffc791ee107[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 15:19:09 2022 +0900

    CHORE : 태그ID 팩토리 메서드 이외엔 생성자 사용 못하게 설정 #29

[33mcommit 335864b38b8c8f425094638ef7f5fc16db0d0298[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 15:17:49 2022 +0900

    FEAT : 해시태그 검색 캐싱 기능 추가 #29

[33mcommit e87371966d99671e9a6d1f9a51c953e228404721[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 15:15:36 2022 +0900

    FEAT : 해시태그 검색 기능 구현 #29

[33mcommit 62ef2ae9a08551505cb70d904da75fc007083782[m
Merge: 84c06af ddcfc88
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Tue Oct 11 14:23:38 2022 +0900

    Merge pull request #38 from kgh2120/issue#29
    
    Issue#29

[33mcommit ddcfc882330cdba11bd1141797492af7082a292a[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 14:20:43 2022 +0900

    FEAT : Redis 캐시 구현 #29

[33mcommit 8b6a31f9dfcf808bd5fdee9778fcb690cd62cc7c[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Oct 11 14:09:50 2022 +0900

    FEAT : 캐릭터 검색 기능 추가 #29

[33mcommit 84c06afd354f6e02d22729df888489906d5f2b2e[m
Merge: b505c2a 20ebb4f
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Sun Oct 9 21:08:26 2022 +0900

    Merge pull request #37 from kgh2120/issue#27
    
    Issue#27

[33mcommit 20ebb4f0f4704e2635936c7150cecc1683323837[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Oct 9 21:06:45 2022 +0900

    BUILD : 어플리케이션 설정 파일 수정 - 프로필 별 레디스 호스트 변경 #27

[33mcommit c0ba3f39bfdbd0525ca96c7c1b6d272a09221259[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Oct 9 21:06:12 2022 +0900

    INIT : 캐릭터 데이터 추가 # 27

[33mcommit b505c2a203242a8f43758bba316e8574ac98f9ea[m
Merge: 314b0df edf2098
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Fri Oct 7 01:26:35 2022 +0900

    Merge pull request #36 from kgh2120/issue#28
    
    도메인 클래스 생성하기

[33mcommit edf2098b902f773f293a3276f0f326aa31af0af3[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Oct 7 01:24:51 2022 +0900

    FIX : JPA 설정 변경 #28

[33mcommit ae73cdf94cbd93286cc07f23605eb131d074022f[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Oct 7 00:04:51 2022 +0900

    ADD : 어그리거트(퀴즈,캐릭터,태그)를 위한 클래스 생성 #28

[33mcommit 899ab3e09532132daadc4e1bc2de19653ff84474[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Oct 6 23:11:07 2022 +0900

    BUILD : DOCKER-COMPOSE 설정

[33mcommit 3fdf6670c877092fb605dbf4b29728e9dd95294c[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Oct 6 23:10:52 2022 +0900

    BUILD : DOCKER-COMPOSE 설정

[33mcommit bfae911c57e6fdc4b74348d10b59c55243f4f4b3[m[33m ([m[1;32mmain[m[33m)[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Sep 20 21:46:36 2022 +0900

    HOTFIX : CORS 설정 추가

[33mcommit 1198028b8496ac14762d562a14d273b29eb4cf8e[m
Merge: 3570b53 314b0df
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Sat Sep 17 16:38:49 2022 +0900

    HOTFIX : CORS 적용
    
    HOTFIX

[33mcommit 314b0df08647b1db9b6b50227017f4e011e219e9[m
Merge: a46d156 1bbc4a3
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Sat Sep 17 16:36:55 2022 +0900

    Merge pull request #25 from kgh2120/HOTFIX-CORS
    
    Hotfix CORS 추가

[33mcommit 1bbc4a38adba17e4dbb7b17af5562327f45feb9d[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sat Sep 17 16:35:30 2022 +0900

    FIX : CORS 설정 추가

[33mcommit 3570b5314300a4a8b8825acbd41572fcbc546f6b[m
Merge: b37c215 a46d156
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Sep 12 15:20:34 2022 +0700

    v.0.1.0 배포
    
    v.0.1.0 기능 리스트
    
    1. 회원 가입 spring security, UsernamePasswordFilter
    2. 이메일 인증 java mail sender, redis
    3. 닉네임 중복 검사
    4. 로그인
    5. acess token, refresh token (JWT)
    6. 마이 페이지 (정보 조회)

[33mcommit a46d156686a250224c425831e09e5b3bf0cb73aa[m
Merge: f2574aa 0fb22d5
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Sep 12 15:16:55 2022 +0700

    Merge pull request #23 from kgh2120/issue#15-jwt-exception-filter
    
    JWT 예외 처리 필터 추가

[33mcommit 0fb22d522817cbe39d4a724befdd2f1bc8855630[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Sep 12 17:15:25 2022 +0900

    TEST : UserControllerTest에 테스트 케이스 추가
    #15

[33mcommit 02519ccd97656fef92693d5431b8a670001cac99[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 15:28:18 2022 +0900

    TEST : UserControllerTest에 새로 추가된 VerificationServiceImpl MockBean추가
    #15

[33mcommit f70ab2a8654e916fcada966f3489ec7f635fe5c6[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 15:23:38 2022 +0900

    BUILD : github-action 파일 수정
    #15

[33mcommit c473bbed6b3863a2415617e0b72dc7a8ca27b4c1[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 15:22:06 2022 +0900

    CHORE : 메서드 이름 오타 수정
    #15

[33mcommit 021b5ae5c7a8a81aa6762d4d097763e28a6a2808[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 15:21:44 2022 +0900

    FEAT : JWT관련 에러 처리 핸들러 추가
    #15

[33mcommit f2574aa718fc080071670264e4b8c481dc20aff2[m
Merge: b0da490 fed7b12
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Sun Sep 11 12:51:05 2022 +0700

    Merge pull request #21 from kgh2120/issue#11-email-auth
    
    이메일 인증 기능 추가

[33mcommit fed7b12efba991953ca1babf3f9c45db8e6e9f13[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 14:49:26 2022 +0900

    BUILD : gitIgnore된 파일들 깃허브에서 받아서 CI 진행하기
    #11

[33mcommit 642cd7a580b46870346c9a654637a6f80e90224b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 14:40:24 2022 +0900

    CHORE : 이메일 코드 인증 시 Response body 내용 수정
    #11

[33mcommit 467946c3cf5aba3e82746c8f47ab0d37873215e8[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 14:39:26 2022 +0900

    FEAT : 이메일 전송 비동기 처리로 전환
    #11

[33mcommit e4acee9cc58134dd09d4e40719d162f5af6a823e[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 14:31:42 2022 +0900

    FEAT : 이메일 인증 기능 DTO추가 및 검증 추가
    #11

[33mcommit 17b7f0c1054a972eda24d9f59a2613095f5fc2ae[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 13:58:29 2022 +0900

    FEAT : 이메일 인증 기능 예외 처리 추가
    #11

[33mcommit beefbe0cee3164f6488b096fcf03827a9d55d626[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Sun Sep 11 13:48:39 2022 +0900

    FEAT : 이메일 인증 기능 구현
    #11

[33mcommit f1834cd5319a161f052959da84726953782bc716[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Sep 2 15:24:49 2022 +0900

    FEAT : 메일 보내기 기능 추가

[33mcommit 30d163cbe79bd3cc1c93d8dd9b20656dbff6ada7[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Sep 2 14:49:00 2022 +0900

    chore : remove git cache

[33mcommit 43ab50a9ac8ac4f807520b09871027e8c3fffc87[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Sep 2 14:47:14 2022 +0900

    chore : remove git cache

[33mcommit b0da490db717ef908aa3fe5c9de9c3b96b670c14[m
Merge: ed17297 d9f6577
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Fri Sep 2 09:13:36 2022 +0700

    Merge pull request #20 from kgh2120/issue#12-nickname
    
    닉네임 관련 기능 추가

[33mcommit d9f657771751a654c150d04997fbc3cc98a6ae8b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Sep 2 11:11:45 2022 +0900

    FEAT : 닉네임 설정 기능 추가
    #12

[33mcommit f97d12c015af4130824c8f3a98c8684852605bbe[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Sep 2 10:37:56 2022 +0900

    FEAT : 닉네임 중복 체크 기능 추가
    #12

[33mcommit ed17297363207e3a639be8280670264be38f0358[m
Merge: eaa2cc9 c194ffd
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Fri Sep 2 07:48:28 2022 +0700

    Merge pull request #19 from kgh2120/issue#16-login-logic
    
    로그인 예외 처리 로직 필터로 적용 시키기

[33mcommit c194ffdbfccc304d51f8cc90fd348abb62a14014[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Sep 2 09:46:21 2022 +0900

    TEST : 테스트 데이터 로그인 실패 케이스 추가
    #16

[33mcommit 59e54d13ed7d420bed7ba945d13fdf520dbc838c[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Sep 1 11:44:36 2022 +0900

    FEAT : login 필터 상 예외처리 추가
    #16

[33mcommit eaa2cc9709113fba4a9be7dee325a71d8c60ea18[m
Merge: 02fd933 7e3d152
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Thu Sep 1 08:49:57 2022 +0700

    Merge pull request #18 from kgh2120/issue#17-my-page
    
    FEAT : 내 정보 보기 기능 추가

[33mcommit 7e3d152810b0b9f87b41841c374475e48aeb7380[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Sep 1 10:48:40 2022 +0900

    TEST : login 테스트 수정
    #17

[33mcommit 88f8d80bf0e1818a7fba4eb4701e279b1d6dfd03[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Sep 1 10:45:21 2022 +0900

    FEAT : 내 정보 보기 기능 추가
    #17

[33mcommit 02fd9337c6c9632591167fde17f18b975e5d37c2[m
Merge: 3f348c0 cc0f9d9
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Thu Sep 1 07:27:52 2022 +0700

    Merge pull request #14 from kgh2120/issue#9-jwt
    
    JWT 관련 기능 추가

[33mcommit cc0f9d980f4e10b804842ec6be95bb9244611a58[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Thu Sep 1 09:26:20 2022 +0900

    FIX : 빌드 성공하도록 수정
    #9

[33mcommit 690e2cc02a5fdd04dff1ae6ebc8aeb924c66143c[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Wed Aug 31 17:35:57 2022 +0900

    chore : h2 console 접근 허용
    #9

[33mcommit 7aef69cea8c799e801c410cc7b4fe93e6826f6ce[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Wed Aug 31 17:35:39 2022 +0900

    feat : JWT 리프래쉬 기능 추가
    #9

[33mcommit 21d71e1a70f17bb7b0684c9cff81db4da9c47b27[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Wed Aug 31 17:07:07 2022 +0900

    feat : JWT 로그인 기능 추가
    #9

[33mcommit 3f348c020065428040943e2a1b8c7e7455a1b669[m
Merge: 6268db7 ae00dff
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Tue Aug 30 18:28:20 2022 +0700

    Merge pull request #13 from kgh2120/issue#8-signup-login
    
    회원가입 로그인 기능 추가

[33mcommit ae00dfff48d86dfdca978f02412e971a838550a6[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Tue Aug 30 20:26:42 2022 +0900

    feat : 회원가입 로그인 기능 추가
    #8

[33mcommit 6268db70d3c941172f5c5009fd76e7840ee794b1[m
Merge: a555ee2 9c526d3
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Aug 29 13:06:27 2022 +0700

    Merge pull request #10 from kgh2120/issue#5-setting-global-config
    
    프로젝트 글로벌 세팅

[33mcommit 9c526d3d84051277e67f0ef2b168c93bd6c34319[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Aug 29 15:04:45 2022 +0900

    ADD : Spring security 설정 클래스 생성
    #5

[33mcommit 47f01e331fd46130d73450a26d16cf8e6864fcff[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Aug 29 15:04:27 2022 +0900

    ADD : Exception Handler 생성
    
    전역적으로 예외 처리해주는 클래스 생성.
    예외 발생 시 리턴할 형태를 잡아주는 클래스 생성.
    예외 클래스 형태를 잡기 위한 설정 추가.
    #5

[33mcommit a555ee2f41c2e1afd3c40b24f18c539a87aa1707[m
Merge: 676db2a 52653bd
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Aug 29 12:33:05 2022 +0700

    Merge pull request #7 from kgh2120/issue#4-create-domain-class
    
    ADD : 도메인 관련 Entity class 생성

[33mcommit 52653bd32ddc3d962a7dd4e49a190f179b015015[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Aug 29 14:30:10 2022 +0900

    ADD : 도메인 관련 Entity class 생성
    
    도메인 관련 Entity class 생성하였다.
    - Users Class 생성
    - RefreshToken Class 생성
    #4

[33mcommit 676db2ad9095fdc3f0a0e60f6672949552b31974[m
Merge: b37c215 a5a9148
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Mon Aug 29 12:15:54 2022 +0700

    Merge pull request #6 from kgh2120/issue#3-create-directory
    
    프로젝트 패키지 구조 생성 #3

[33mcommit a5a9148f03de9810e50bf4d8769d11ec33bd20c5[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Aug 29 14:15:04 2022 +0900

    fix : 빌드 성공하도록 변경 #3
    
    - UserRepository extend 부분 삭제

[33mcommit f6123a094aacb71269008fc2c2cb1c1c21ecb1fb[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Mon Aug 29 14:09:25 2022 +0900

    init : 패키지 구조 생성

[33mcommit b37c2150e84da5f314e53e95b4d70e7342e0c854[m
Author: kgh2120 <76154390+kgh2120@users.noreply.github.com>
Date:   Fri Aug 26 17:37:29 2022 +0900

    create github ci file

[33mcommit c44e9d8d6f4a06c4a1881758d875e975c8e2e70b[m
Author: kyoohyun <kgh2120@naver.com>
Date:   Fri Aug 26 17:12:30 2022 +0900

    Initial commit
