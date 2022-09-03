
# 유튜브 추천 사이트 만들기


# 1. 프로젝트 소개

- 주제
    - 유튜브 구독 추천
    - 카드 상단: 유튜브 추천 영상 썸네일
    - 카드 하단: 제목, 설명

# 2. 팀원 정보

| 이름 | FE/BE | 깃허브 |
| --- | --- | --- |
| 박세린 | BE | https://github.com/Serapple |
| 손지아 | BE | https://githup.com/JJIaa |
| 전선향 | BE | https://github.com/petal416 |
| 이상혁 | FE |  |
| 이태권 | FE |  https://github.com/momoco-git |

- FE Github:  [https://github.com/momoco-git/miniproject](https://github.com/momoco-git/miniproject)
- BE Github:  https://github.com/Serapple/Miniproject_BE

# 3. 와이어프레임

![image](https://user-images.githubusercontent.com/110282569/188261069-87a92422-e17a-4046-9f6c-64c849a1204f.png)


# 4. 진행 계획

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

`9월 4일 (일)`

- WIL 작성하기

`9월 5일 (월)`

- 기능 구현 점검
- 중간 멘토링

`9월 6일 (화)`

- 기능 구현
- 추가기능 논의

`9월 7일 (수)`

- 작업 통합

`9월 8일 (목)`

- 버그 수정, 배포
- 유튜브 영상 촬영
- 발표(이상혁)

# 5. API 설계

| 기능 | Method | URL | request | response | FE 담당자 | BE 담당자 | 진행 상황 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 로그인 | POST | /api/member/login | {username: “test”,password:”1234”} |  | 이태권 | 박세린 | BE 진행,FE 진행 |
| 로그아웃 | GET | /api/auth/member/logout |  |  | 이태권 | 전선향 | BE 진행,FE 진행 |
| 회원가입 | POST | /api/member/signup | {username: ”test”, nickname: “nickname”, password: “1234”, passwordCheck:”1234”} |  | 이태권 | 손지아 | BE 진행,FE 진행 |
| 게시글 작성 | POST | /api/auth/post | {title: “제목”, “content:”작성내용”,  url:”영상 주소”} |  | 공동 | 공동 | BE 진행,FE 진행 |
| 게시글 수정 | PATCH | /api/auth/post/{id} | {”content”:”작성내용”} |  | 공동 | 공동 | BE 진행,FE 진행 |
| 게시글 삭제 | DELETE | /api/auth/post/{id} |  |  | 공동 | 공동 | BE 진행,FE 진행 |
| 게시글 전체 조회 | GET | /api/post |  |  | 이상혁 | 공동 | BE 진행,FE 진행 |
| 게시글 상세 조회 | GET | /api/post/{id} |  |  | 이상혁 | 공동 | BE 진행,FE 진행 |

# 6. ERD

(박세린)
