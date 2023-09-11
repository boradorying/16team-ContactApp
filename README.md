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

### 1. 스플래쉬 스크린 (SplashActivity)

<img src="https://github.com/16-team/16-team/assets/139097952/38c56c37-f0b3-4f99-9f0a-7f184c4b52be"  width="200">

- 앱의 완성도를 높이기 위해 Lottie 를 사용해 SplashScreen 추가

### 2. 메인 페이지 (리스트 뷰) (MainActivity,ContactFragment)

<img src="https://github.com/16-team/16-team/assets/139097952/54010b90-a1b4-40a6-bde6-9a8c6236d432"  width="200">
<img src="https://github.com/16-team/16-team/assets/139097952/3e39a7f3-54be-4dd4-9263-1cab29e2e926"  width="200"  height="200" gravity>
<img src="https://github.com/16-team/16-team/assets/139097952/5980aefb-d134-45a8-a6d6-0e78209dffbf"  width="200">

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

<img src="https://github.com/16-team/16-team/assets/139097952/e7195063-b84f-48ef-870b-399272bca37a"  width="200">

- 화면에 표현된 유저들을 자음 순서로 나열되게 구현
- 좋아요 기능 구현

### 3. 마이 페이지 (MyPageFragment)

<img src="https://github.com/16-team/16-team/assets/139097952/37df1399-82f7-455f-9ab1-ca74b47f3034"  width="200">
<img src="https://github.com/16-team/16-team/assets/139097952/d736f71a-47b9-4a7f-8172-7b475ff248d1"  width="500">

- 본인의 정보를 수정할 수 있는 기능 구현

### 4. 디테일 페이지 (DetailActivity)

<img src="https://github.com/16-team/16-team/assets/139097952/1c966de8-9f61-4fdf-975f-95e3779f9734"  width="200">

- 메인 페이지에서 좋아요를 누른 연락처가 디테일 페이지에 들어와도 적용이 되어있는 기능 구현
- 디테일 페이지에서 좋아요 설정시 토스트 메시지 출력
- 전화와 동일하게 intent를 사용한 권한 요청 방식으로 문자기능 구현

### 5. 연락처 추가 기능 및 알림 기능

<img src="https://github.com/16-team/16-team/assets/139097952/92c73d95-1a14-42d3-9ca7-603bec2f5bcc"  width="200">
<img src="https://github.com/16-team/16-team/assets/139097952/e091898b-ebac-4ee6-8c24-b0202aa040fb"  width="200">

- 이름, 전화번호, 이메일, 갤러리에서 불러온 사진(URI) 의 정보를 저장버튼을 눌렀을 때 입력한 정보를  dataclass를 통해 recyclerView 어댑터로 전달.
- 사진 선택을 안 했을 때 기본사진으로 나오게 했음.
- CoroutineScope, PendongIntent 를 사용하여 버튼을 누르면 어플 상단 알림바에 Message 표시시

### 6. 연락처 동기화 기능

<img src="https://github.com/16-team/16-team/assets/139097952/7289530e-b7a7-45bd-a347-f3dc00ffb5ac"  width="150">
<img src="https://github.com/16-team/16-team/assets/139097952/10e158b3-d0f4-4564-807a-d721b34999f7"  width="150">

- ContentResolver, READ_CONTACT를 사용하여 연락처 동기화
    - RegisterForActivityResult의 권한을 콜백으로 받아오는 방식으로 구현
