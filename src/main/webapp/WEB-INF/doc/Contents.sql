-- 콘텐츠 테이블 생성
CREATE TABLE contents (
    contentsno NUMBER(10) NOT NULL,   -- 콘텐츠 번호
    content CLOB NOT NULL,            -- 콘텐츠 내용
    recom NUMBER(7) NOT NULL DEFAULT 0, -- 추천수
    cnt NUMBER(7) NOT NULL DEFAULT 0, -- 조회수
    replycnt NUMBER(7) NOT NULL DEFAULT 0, -- 댓글수
    word VARCHAR2(300) NOT NULL,      -- 검색어 (수정: VARCHAR2(200) → VARCHAR2(300))
    rdate DATE NOT NULL,              -- 등록일
    file1 VARCHAR2(300) NOT NULL,     -- 메인 파일 경로 (수정: VARCHAR2(100) → VARCHAR2(300))
    thumb1 VARCHAR2(300) NOT NULL,    -- 썸네일 경로 (수정: VARCHAR2(100) → VARCHAR2(300))
    size1 NUMBER(10) NOT NULL DEFAULT 0, -- 파일 크기
    price NUMBER(10) NOT NULL DEFAULT 0, -- 정가
    dc NUMBER(10) NOT NULL DEFAULT 0,    -- 할인률
    saleprice NUMBER(10) NOT NULL DEFAULT 0, -- 판매가
    salecnt NUMBER(10) NOT NULL DEFAULT 0,   -- 재고 수량
    edition VARCHAR2(50) NOT NULL,      -- 에디션 정보 (수정: CHAR(20) → VARCHAR2(50))
    url VARCHAR2(500) NOT NULL,         -- 콘텐츠 URL
    cateno2 NUMBER(10) NOT NULL,        -- 카테고리 번호
    categrpno NUMBER(10) NOT NULL,      -- 카테고리 그룹 번호
    CONSTRAINT PK_CONTENTS PRIMARY KEY (contentsno),
    CONSTRAINT FK_CONTENTS_CATE FOREIGN KEY (cateno2) REFERENCES cate(cateno) ON DELETE CASCADE,
    CONSTRAINT FK_CONTENTS_CATEGRP FOREIGN KEY (categrpno) REFERENCES categrp(categrpno) ON DELETE CASCADE
);

-- 각 컬럼에 주석 추가
COMMENT ON COLUMN contents.contentsno IS '콘텐츠 번호';
COMMENT ON COLUMN contents.content IS '콘텐츠 내용';
COMMENT ON COLUMN contents.recom IS '추천수';
COMMENT ON COLUMN contents.cnt IS '조회수';
COMMENT ON COLUMN contents.replycnt IS '댓글수';
COMMENT ON COLUMN contents.word IS '검색어';
COMMENT ON COLUMN contents.rdate IS '등록일';
COMMENT ON COLUMN contents.file1 IS '메인 파일 경로';
COMMENT ON COLUMN contents.thumb1 IS '썸네일 경로';
COMMENT ON COLUMN contents.size1 IS '파일 크기';
COMMENT ON COLUMN contents.price IS '정가';
COMMENT ON COLUMN contents.dc IS '할인률';
COMMENT ON COLUMN contents.saleprice IS '판매가';
COMMENT ON COLUMN contents.salecnt IS '재고 수량';
COMMENT ON COLUMN contents.edition IS '에디션 정보';
COMMENT ON COLUMN contents.url IS '콘텐츠 URL';
COMMENT ON COLUMN contents.cateno2 IS '카테고리 번호';
COMMENT ON COLUMN contents.categrpno IS '카테고리 그룹 번호';
