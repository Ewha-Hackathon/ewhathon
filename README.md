# 🌱 벗돋움

## ❓ 문제 정의


## 🛠 서비스 정의


## 📋 기능 설명

### 📚 domain package
- **User**: 사용자의 정보와 관련된 엔티티. 속성: `username`, `email`, `password`.
- **Category**: 행사의 다양한 분야(공연, 학술, 전시 등)를 정의하는 열거형.
- **Event**: 행사 정보와 관련된 엔티티. 속성: `category`, `title`, `location`, `host`, `post_date`, `start_date`, `end_date`, `free`, `filepath`, `heart_count`, `rsvp_count`.
- **Hashtag**: 행사의 분위기나 특성을 나타내는 해시태그를 정의하는 열거형.
- **Heart**: 등록된 행사글에 대한 좋아요를 관리하는 엔티티. 연결: `user_id`, `event_id`.
- **Rsvp**: 등록된 행사글에 대한 참석 여부를 관리하는 엔티티. 연결: `user_id`, `event_id`.
- **Suggestion**: 추천 해시태그와 관련된 엔티티. 연결: `event_id`, 저장: `suggestedKeywords`.

### 🗃️ Repository
- EventRepository
- HeartRepository
- RsvpRepository
- SuggestionRepository
- UserRepository

### 🔐 Authentication Package
- **UserController**
- **UserDTO**
- **UserService**
  - `saveUser`: 유저 등록 (이화여자대학교 이메일 도메인만 허용).
  - `loginUser`: 로그인 기능.
  - `isEmailAvailable`: 이메일 중복 검사.

### 🎉 Event Package
- **EventController**
- **EventRequestDto**
- **EventResponseDto**
- **EventService**
  - `createEvent`: 행사글 등록 (`selectedKeywords`는 null로 저장).
  - `showEventDetail`: 행사글 상세보기.

### ❤️ Heart Package
- **HeartController**
- **HeartRequestDto**
- **HeartService**
  - `insert`: 좋아요 표시.
  - `delete`: 좋아요 취소.

### 📝 RSVP Package
- **RsvpController**
- **RsvpRequestDto**
- **RsvpService**
  - `insert`: 참여 표시.
  - `delete`: 참여 취소.

### 🏠 Home Package
- **HomeController**
- **HomeResponseDto**
- **HomeService**
  - `getHomeInfo`: 좋아요가 많은 행사글, 최신 등록글 정보 불러오기.

### 🙍‍♂️ MyPage Package
- **MypageController**
- **MypageResponseDto**
- **MypageService**
  - `getMypageInfo`: 유저 정보, 유저가 좋아한/참여 이벤트, 유저가 주최한 이벤트 정보 불러오기.

### 🔍 Search Package
- **SearchController**
- **SearchService**
  - `searchEvents`: 제목, 분야, 시작/종료 날짜, 무료 여부, 키워드로 이벤트 검색.
- **SearchSpecifications**
  - 검색 쿼리의 사양 포함.

### 🏷️ Suggestion Package
- **SuggestionController**
- **SuggestService**
  - `fetchSuggestedKeywords`: flask 서버와 연결하여 `keword_2.py`에서 생성된 '추천 해시태그' 리스트 가져오기.
  - `saveKeywords`: 추천 해시태그 리스트를 단일 해시태그로 끊어 `suggestedKeywords`에 저장.
  - `saveSelectedKeywords`: 사용자가 선택한 해시태그를 `selectedKeywords`에 저장.

### 🔒 Security Config
- 사용자 보안 관련 설정, 비밀번호 해싱 등 관리.
