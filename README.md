# 🦉연락처 과제
## **📘Notion 주소**
[https://github.com/16-team/16-team](https://github.com/16-team/16-team)

## 😎팀원 소개
| 이름 | 역할 | 개인 git hub 주소 |
| --- | --- | --- |
| 이승현 | 팀장 | https://github.com/shyr0809 |
| 남경화 | 팀원 | https://github.com/KyungHwa0 |
| 박세준 | 팀원 | https://github.com/boradoryingboradorying |
| 추민수 | 팀원 | https://github.com/SoftyChoo |

## 📽️프로젝트 소개

---

## Application Version
* minSdk 28
* targetSdk 33

## 💻기술스택

- Language - kotlin
- IDE - Android Studio

## 📃구현 페이지

---
## 📃구현 페이지

### 1. 스플래쉬 스크린 (SplashActivity)

(이미지 넣을 곳)

- 앱의 완성도를 높이기 위해 Lottie 를 사용해 SplashScreen 추가

### 2. 메인 페이지 (리스트 뷰) (MainActivity,ContactFragment)

(이미지 넣을 곳)

- addTextChangeListener & TextWatcher를 사용하여 실제의 연락처의 검색 기능을 넣어 원하는 유저를 쉽게 찾을 수 있도록 구현
- ItemTouchHelper를 사용해 Swipe구현하고 Intent를 사용한 권한 요청 방식으로 전화 기능 구현
- 좋아요 기능 구현
- 화면에 표현된 유저들을 자음 순서로 나열되게 구현
- 연락처 추가 버튼을 눌렀을 때 연락처 추가 다이얼로그가 뜸
- 상단의 Icon 선택에 따라 View Type (Grid/List) 을 선택할 수 있도록 구현
    - getItemViewType 메서드를 이용
- 화면 하단의 탭 레이아웃으로 메인페이지와 마이페이지 화면 이동
    - ViewPager2 & TabLayout 커스텀을 통해 화면 하단 TabLayout Design을 구현

### 2-1. 메인페이지 (그리드 뷰) (MainActivity,ContactFragment)

(이미지 넣을 곳)

- 화면에 표현된 유저들을 자음 순서로 나열되게 구현
- 좋아요 기능 구현

### 3. 마이 페이지 (MyPageFragment)

(이미지 넣을 곳)

- 본인의 정보 수정할 수 있는 기능 구

### 4. 디테일 페이지 (DetailActivity)

(이미지 넣을 곳)

- 메인 페이지에서 좋아요를 누른 연락처가 디테일 페이지에 들어와도 적용이 되어있는 기능 구현
- 디테일 페이지에서 좋아요 설정시 토스트 메시지 출력
- 전화와 동일하게 intent를 사용한 권한 요청 방식으로 문자기능 구현

### 5. 연락처 추가 기능

(이미지 넣을 곳)

- ContactFragment 에서 floating button (연락처 추가 버튼) 을 누를 때 customdialog 형식으로 연락처 추가 다이얼로그를 띄움.
- 이름, 전화번호, 이메일, 갤러리에서 불러온 사진(URI) 의 정보를 저장버튼을 눌렀을 때 입력한 정보를  dataclass를 통해 recyclerView 어댑터로 전달.
- 사진 선택을 안 했을 때 기본사진으로 나오게 했음.

### 6. 그 외 기능

(이미지 넣을 곳)

- ContentResolver, READ_CONTACT를 사용하여 연락처 동기화
    - RegisterForActivityResult의 권한을 콜백으로 받아오는 방식으로 구현
