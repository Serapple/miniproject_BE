
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

| 기능 | Method | URL | request | response | FE 담당자 | BE 담당자 | 진행 상황 | 논의 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 로그인 | POST | /api/member/login | {username: “test”, password:”1234”} | id 오류시: {"존재하지 않는 아이디입니다."} pw 오류시: {"비밀번호를 확인해주세요"} 성공시:{”redirect:/api/post"} | 이태권 | 박세린 | FE 진행,BE 완료 |  |
| 로그아웃 | GET | /api/auth/member/logout |  | token 오류시: {”토큰이 유효하지 않습니다.”}사용자 오류시: {”사용자를 찾을 수 없습니다.”}성공시: {”edirect:/api/post”} | 이태권 | 전선향 | FE 진행,BE 완료 |  |
| 회원가입 | POST | /api/member/signup | {username: ”test”, nickname: “nickname”, password: “1234”,passwordConfirm:”1234”} | username 중복시: {”중복된 아이디입니다.”} nickname 중복시: {”중복된 닉네임입니다.”} pw/pwconfirm 불일치시:{” 비밀번호와 비밀번호 확인이 일치하지 않습니다.”}성공시: {”redirect:/api/post”} | 이태권 | 손지아 | FE 진행,BE 완료 |  |
| 게시글 작성 | POST | /api/auth/post | {title: “제목”,  “content:”작성내용”,  url:”영상 주소”} | 로그인이 되어있지 않은 경우: {”로그인이 필요합니다”} 유효하지 않은 토큰일 경우:{”로그인이 필요합니다.”} 성공시: {”redirect:/api/post”} | 공동 | 전선향 | FE 진행,BE 완료 |  |
| 게시글 수정 | PATCH | /api/auth/post/{id} | {”content”:”작성내용”} | 로그인이 되어있지 않은 경우: {”로그인이 필요합니다”} 유효하지 않은 토큰일 경우:{”로그인이 필요합니다.”} 존재하지 않는 post일 경우: {”존재하지 않는 게시글 id입니다”} 수정자가 작성자가 아닐 경우:{"작성자만 수정할 수 있습니다.”} 성공시: {”redirect:/api/post”} | 공동 | 박세린 | FE 진행,BE 완료 |  |
| 게시글 삭제 | DELETE | /api/auth/post/{id} |  | 로그인이 되어있지 않은 경우: {”로그인이 필요합니다”} 유효하지 않은 토큰일 경우:{”로그인이 필요합니다.”} 존재하지 않는 post일 경우: {”존재하지 않는 게시글 id입니다”} 수정자가 작성자가 아닐 경우:{"작성자만 수정할 수 있습니다.”} 성공시: {”redirect:/api/post”} | 공동 | 박세린 | FE 진행,BE 완료 |  |
| 게시글 전체 조회 | GET | /api/post |  | { "createdAt": "2022-09-05T16:34:39.767648", "modifiedAt": "2022-09-05T17:46:46.011637", "id": 9, "title": "꿀잼 유튜브 추천", "content": "content수정하기", "youtubeUrl": "abcdefutube", "member": { "createdAt": "2022-09-05T16:32:33.624883", "modifiedAt": "2022-09-05T16:32:33.624883", "id": 4, "username": "testtest", "nickname": "test", "hibernateLazyInitializer": {} } }, { "createdAt": "2022-09-05T16:33:58.111287", "modifiedAt": "2022-09-05T16:33:58.111287", "id": 8, "title": "꿀잼 유튜브 추천", "content": "정말 재미있어요", "youtubeUrl": "abcdefutube", "member": { "createdAt": "2022-09-05T11:58:27.920115", "modifiedAt": "2022-09-05T11:58:27.920115", "id": 1, "username": "testtesttest", "nickname": " t", "hibernateLazyInitializer": {}}} | 이상혁 | 전선향 | FE 진행,BE 완료 |  |
| 게시글 상세 조회 | GET | /api/post/{id} |  | 존재하지 않는 post일 경우: {”존재하지 않는 게시글 id입니다”} 성공시: { "createdAt": "2022-09-05T16:34:39.767648", "modifiedAt": "2022-09-05T17:46:46.011637", "id": 9, "title": "꿀잼 유튜브 추천", "content": "content수정하기", "youtubeUrl": "abcdefutube", "member": { "createdAt": "2022-09-05T16:32:33.624883", "modifiedAt": "2022-09-05T16:32:33.624883", "id": 4, "username": "testtest", "nickname": "test", "hibernateLazyInitializer": {}} } | 이상혁 | 전선향 | FE 진행,BE 완료 |  |
| 게시글 썸네일 크롤링 |  |  |  |  |  | 손지아 | BE 진행 |  |

# 🗂 6. ERD
![%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-09-05_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_6 40 47](https://user-images.githubusercontent.com/110282569/188422559-a7ff21c8-bb6e-4dac-b8d5-2056202c148c.png)

