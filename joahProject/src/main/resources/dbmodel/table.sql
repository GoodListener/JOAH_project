-- 회원
DROP TABLE IF EXISTS MEMBER RESTRICT;

-- 데이트게시글
DROP TABLE IF EXISTS BOARD RESTRICT;

-- 회원관심사
DROP TABLE IF EXISTS MEM_INT RESTRICT;

-- 댓글
DROP TABLE IF EXISTS COMMENT RESTRICT;

-- 사진
DROP TABLE IF EXISTS PHOTO RESTRICT;

-- 장소
DROP TABLE IF EXISTS LOCATION RESTRICT;

-- 장소배정
DROP TABLE IF EXISTS COUR_LOC RESTRICT;

-- 관심사
DROP TABLE IF EXISTS INTEREST RESTRICT;

-- 회원
CREATE TABLE MEMBER (
  ID     VARCHAR(20)  NOT NULL COMMENT '아이디', -- 아이디
  PWD    VARCHAR(255) NOT NULL COMMENT '비밀번호', -- 비밀번호
  NAME   VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  EMAIL  VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  GENDER VARCHAR(10)  NOT NULL COMMENT '성별', -- 성별
  AGE    INTEGER      NOT NULL COMMENT '나이', -- 나이
  PHOTO  VARCHAR(255) NULL     COMMENT '사진', -- 사진
  CID    VARCHAR(20)  NULL     COMMENT '커플아이디' -- 커플아이디
)
COMMENT '회원';

-- 회원
ALTER TABLE MEMBER
  ADD CONSTRAINT PK_MEMBER -- 회원 기본키
    PRIMARY KEY (
      ID -- 아이디
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_MEMBER
  ON MEMBER ( -- 회원
    EMAIL ASC, -- 이메일
    CID ASC    -- 커플아이디
  );

-- 회원 인덱스
CREATE INDEX IX_MEMBER
  ON MEMBER( -- 회원
    ID ASC -- 아이디
  );

-- 데이트게시글
CREATE TABLE BOARD (
  BNO      INTEGER      NOT NULL COMMENT '글번호', -- 글번호
  ID       VARCHAR(20)  NOT NULL COMMENT '게시자', -- 게시자
  TITLE    VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  CONTENT  TEXT         NOT NULL COMMENT '내용', -- 내용
  VIEWS    INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  CRE_DT   DATETIME     NOT NULL COMMENT '등록일', -- 등록일
  LIKES    INTEGER      NOT NULL COMMENT '좋아요', -- 좋아요
  DISLIKES INTEGER      NOT NULL COMMENT '나빠요', -- 나빠요
  HEART    CHAR(1)      NOT NULL COMMENT '찜', -- 찜
  ST_LOC   VARCHAR(255) NULL     COMMENT '출발', -- 출발
  ED_LOC   VARCHAR(255) NULL     COMMENT '도착', -- 도착
  ICON     VARCHAR(255) NOT NULL COMMENT '라벨', -- 라벨
  AGE_GR   INTEGER      NULL     COMMENT '연령대', -- 연령대
  COST_ALL INTEGER      NULL     COMMENT '전체비용' -- 전체비용
)
COMMENT '데이트게시글';

-- 데이트게시글
ALTER TABLE BOARD
  ADD CONSTRAINT PK_BOARD -- 데이트게시글 기본키
    PRIMARY KEY (
      BNO -- 글번호
    );

-- 데이트게시글 인덱스
CREATE INDEX IX_BOARD
  ON BOARD( -- 데이트게시글
    TITLE ASC, -- 제목
    ID ASC,    -- 게시자
    VIEWS ASC, -- 조회수
    LIKES ASC  -- 좋아요
  );

ALTER TABLE BOARD
  MODIFY COLUMN BNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '글번호';

ALTER TABLE BOARD
  AUTO_INCREMENT = 1;

-- 회원관심사
CREATE TABLE MEM_INT (
  ID       VARCHAR(20) NOT NULL COMMENT '아이디', -- 아이디
  INT_CODE CHAR(5)     NOT NULL COMMENT '관심사번호' -- 관심사번호
)
COMMENT '회원관심사';

-- 회원관심사
ALTER TABLE MEM_INT
  ADD CONSTRAINT PK_MEM_INT -- 회원관심사 기본키
    PRIMARY KEY (
      ID,       -- 아이디
      INT_CODE  -- 관심사번호
    );

-- 댓글
CREATE TABLE COMMENT (
  CNO     INTEGER     NOT NULL COMMENT '댓글번호', -- 댓글번호
  BNO     INTEGER     NOT NULL COMMENT '글번호', -- 글번호
  ID      VARCHAR(20) NOT NULL COMMENT '회원아이디', -- 회원아이디
  CONTENT TEXT        NOT NULL COMMENT '내용', -- 내용
  CRE_DT  DATETIME    NOT NULL COMMENT '게시일' -- 게시일
)
COMMENT '댓글';

-- 댓글
ALTER TABLE COMMENT
  ADD CONSTRAINT PK_COMMENT -- 댓글 기본키
    PRIMARY KEY (
      CNO -- 댓글번호
    );

ALTER TABLE COMMENT
  MODIFY COLUMN CNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

ALTER TABLE COMMENT
  AUTO_INCREMENT = 1;

-- 사진
CREATE TABLE PHOTO (
  PNO   INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
  BNO   INTEGER      NOT NULL COMMENT '글번호', -- 글번호
  PHOTO VARCHAR(255) NOT NULL COMMENT '사진' -- 사진
)
COMMENT '사진';

-- 사진
ALTER TABLE PHOTO
  ADD CONSTRAINT PK_PHOTO -- 사진 기본키
    PRIMARY KEY (
      PNO -- 사진번호
    );

ALTER TABLE PHOTO
  MODIFY COLUMN PNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

ALTER TABLE PHOTO
  AUTO_INCREMENT = 1;

-- 장소
CREATE TABLE LOCATION (
  LNO      INTEGER      NOT NULL COMMENT '장소번호', -- 장소번호
  INT_CODE CHAR(5)      NULL     COMMENT '관심사번호', -- 관심사번호
  NAME     VARCHAR(50)  NOT NULL COMMENT '가게이름', -- 가게이름
  CONTENT  TEXT         NULL     COMMENT '가게정보', -- 가게정보
  COST     INTEGER      NULL     COMMENT '간략한비용', -- 간략한비용
  PHOTO    VARCHAR(255) NOT NULL COMMENT '썸네일이미지', -- 썸네일이미지
  LAT      DOUBLE       NOT NULL COMMENT '위도', -- 위도
  LNG      DOUBLE       NOT NULL COMMENT '경도' -- 경도
)
COMMENT '장소';

-- 장소
ALTER TABLE LOCATION
  ADD CONSTRAINT PK_LOCATION -- 장소 기본키
    PRIMARY KEY (
      LNO -- 장소번호
    );

-- 장소 인덱스
CREATE INDEX IX_LOCATION
  ON LOCATION( -- 장소
    NAME ASC -- 가게이름
  );

ALTER TABLE LOCATION
  MODIFY COLUMN LNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '장소번호';

ALTER TABLE LOCATION
  AUTO_INCREMENT = 1;

-- 장소배정
CREATE TABLE COUR_LOC (
  BNO INTEGER NOT NULL COMMENT '글번호', -- 글번호
  LNO INTEGER NOT NULL COMMENT '장소번호' -- 장소번호
)
COMMENT '장소배정';

-- 장소배정
ALTER TABLE COUR_LOC
  ADD CONSTRAINT PK_COUR_LOC -- 장소배정 기본키
    PRIMARY KEY (
      BNO, -- 글번호
      LNO  -- 장소번호
    );

-- 관심사
CREATE TABLE INTEREST (
  INT_CODE  CHAR(5)      NOT NULL COMMENT '관심사번호', -- 관심사번호
  INT_CATEG VARCHAR(255) NOT NULL COMMENT '관심항목' -- 관심항목
)
COMMENT '관심사';

-- 관심사
ALTER TABLE INTEREST
  ADD CONSTRAINT PK_INTEREST -- 관심사 기본키
    PRIMARY KEY (
      INT_CODE -- 관심사번호
    );

-- 데이트게시글
ALTER TABLE BOARD
  ADD CONSTRAINT FK_MEMBER_TO_BOARD -- 회원 -> 데이트게시글
    FOREIGN KEY (
      ID -- 게시자
    )
    REFERENCES MEMBER ( -- 회원
      ID -- 아이디
    );

-- 회원관심사
ALTER TABLE MEM_INT
  ADD CONSTRAINT FK_MEMBER_TO_MEM_INT -- 회원 -> 회원관심사
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES MEMBER ( -- 회원
      ID -- 아이디
    );

-- 회원관심사
ALTER TABLE MEM_INT
  ADD CONSTRAINT FK_INTEREST_TO_MEM_INT -- 관심사 -> 회원관심사
    FOREIGN KEY (
      INT_CODE -- 관심사번호
    )
    REFERENCES INTEREST ( -- 관심사
      INT_CODE -- 관심사번호
    );

-- 댓글
ALTER TABLE COMMENT
  ADD CONSTRAINT FK_BOARD_TO_COMMENT -- 데이트게시글 -> 댓글
    FOREIGN KEY (
      BNO -- 글번호
    )
    REFERENCES BOARD ( -- 데이트게시글
      BNO -- 글번호
    );

-- 댓글
ALTER TABLE COMMENT
  ADD CONSTRAINT FK_MEMBER_TO_COMMENT -- 회원 -> 댓글
    FOREIGN KEY (
      ID -- 회원아이디
    )
    REFERENCES MEMBER ( -- 회원
      ID -- 아이디
    );

-- 사진
ALTER TABLE PHOTO
  ADD CONSTRAINT FK_BOARD_TO_PHOTO -- 데이트게시글 -> 사진
    FOREIGN KEY (
      BNO -- 글번호
    )
    REFERENCES BOARD ( -- 데이트게시글
      BNO -- 글번호
    );

-- 장소
ALTER TABLE LOCATION
  ADD CONSTRAINT FK_INTEREST_TO_LOCATION -- 관심사 -> 장소
    FOREIGN KEY (
      INT_CODE -- 관심사번호
    )
    REFERENCES INTEREST ( -- 관심사
      INT_CODE -- 관심사번호
    );

-- 장소배정
ALTER TABLE COUR_LOC
  ADD CONSTRAINT FK_LOCATION_TO_COUR_LOC -- 장소 -> 장소배정
    FOREIGN KEY (
      LNO -- 장소번호
    )
    REFERENCES LOCATION ( -- 장소
      LNO -- 장소번호
    );

-- 장소배정
ALTER TABLE COUR_LOC
  ADD CONSTRAINT FK_BOARD_TO_COUR_LOC -- 데이트게시글 -> 장소배정
    FOREIGN KEY (
      BNO -- 글번호
    )
    REFERENCES BOARD ( -- 데이트게시글
      BNO -- 글번호
    );