
# 🎬 유튜브 추천 사이트 만들기


# 🔎 1. 프로젝트 소개

- **주제**
    - 유튜브 구독 추천
    - 카드 상단: 유튜브 재미있던 것 썸네일
        - 크롤링 사용
    - 카드 하단: 설명, 댓글

- **기능**
    - signup
        - ID 조건: 4자 이상 12자 이하, 영어 대문자, 소문자, 숫자만 가능(특수문자 불가)
        - PW 조건: 4자이상 32자 이하, 영어 대소문자, 숫자 가능(특수문자 불가)
        - ID, Nickname: 중복 불가능
    - login : 로그인기능(access, refresh 토큰 발급)
    - logout: 로그아웃 기능(refresh 토큰 삭제)
    - Post CRUD : post에 member join하여 추가 기능 구현 용이하도록
        - Create: 로그인했을 때만 작성 가능
        - Read: 로그인하지 않았을 경우 포스트 조회만 가능.
            - 게시글 상세 조회
            - 게시글 전체 조회
        - Update: 작성자일 경우에만 글 수정(content만) 가능
        - Delete: 작성자일 경우에만 삭제 가능

- **추가 기능(논의)**
    - mypage
    - comment 
    - like
    

# 👥 2. 팀원 정보

| 이름 | FE/BE | 깃허브 |
| --- | --- | --- |
| 박세린 | BE | https://github.com/Serapple |
| 손지아 | BE | https://githup.com/JJIaa |
| 전선향 | BE | https://github.com/petal416 |
| 이상혁 | FE |  |
| 이태권 | FE |  https://github.com/momoco-git |

- FE Github:  [https://github.com/momoco-git/miniproject](https://github.com/momoco-git/miniproject)
- BE Github:  https://github.com/Serapple/Miniproject_BE

# 🖼 3. 와이어프레임

<img width="799" alt="%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-09-02_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_11 05 48" src="https://user-images.githubusercontent.com/110282569/188422856-d35c379c-9f6b-4f7a-90e0-14478d097071.png">

[와이어프레임.pdf](https://github.com/Serapple/miniproject_BE/files/9488884/default.pdf)



# 📆 4. 진행 계획

`9월 2일 (금)`

- 오후 7시 S.A 제출
    - [x]  주제 선정
    - [x]  와이어프레임
    - [x]  API 설계
    - [x]  1주 진행 계획
- 개념 공부

`9월 3일 (토)`

- 개념 공부
- 기능 구현 시작
- S.A. 서면 피드백 확인
    - [x]  피드백 확인 후 수정 완료

`9월 4일 (일)`

- WIL 작성하기

`9월 5일 (월)`

- 기능 구현 점검
    - [x]  BE 진행상황 대시보드 업로드
    - [ ]  FE 진행상황 대시보드 업로드
- 중간 멘토링
    - [ ]  중간 멘토링 Question 작성

`9월 6일 (화)`

- 기능 구현
- 추가기능 논의

`9월 7일 (수)`

- 작업 통합

`9월 8일 (목)`

- 버그 수정, 배포
- 유튜브 영상 촬영
- 발표(이상혁)


# 🧾 5. API 설계

![image](https://user-images.githubusercontent.com/110282569/188882660-c3211779-cab3-41e2-b521-92c32a8352c7.png)
![image](https://user-images.githubusercontent.com/110282569/188882737-8cc50220-5851-40d5-bef8-ab525f1fccc8.png)
![image](https://user-images.githubusercontent.com/110282569/188882788-d028a66c-399e-4b0d-9c0b-a73349607207.png)
![image](https://user-images.githubusercontent.com/110282569/188882838-b3758b0c-03db-4fac-ace4-ed1778624590.png)

# 🗂 6. ERD
<img width="691" alt="스크린샷 2022-09-07 오후 9 35 58" src="https://user-images.githubusercontent.com/110282569/188879917-6272fd1f-33c3-4721-8652-03aa3e1d0b4b.png">


