-- 콘텐츠 테이블 생성
CREATE TABLE contents (
    contentsno NUMBER(10) NOT NULL,             -- 콘텐츠 번호
    title VARCHAR2(300) NOT NULL,               -- 제목
    recom NUMBER(7) DEFAULT 0 NOT NULL,         -- 관심수
    cnt NUMBER(7) DEFAULT 0 NOT NULL,           -- 조회수
    word VARCHAR2(300) NOT NULL,                -- 검색어
    rdate DATE NOT NULL,                        -- 등록일
    file1 VARCHAR2(300) NOT NULL,               -- 썸네일
    size1 NUMBER(10) DEFAULT 0 NOT NULL,        -- 썸네일 파일 크기
    price NUMBER(10) DEFAULT 0 NOT NULL,        -- 판매가
    memberno NUMBER(10) DEFAULT 0 NOT NULL,     -- 회원 번호
    cateno2 NUMBER(10) NOT NULL,                -- 카테고리 번호
    categrpno NUMBER(10) NOT NULL,              -- 카테고리 그룹 번호
    CONSTRAINT PK_CONTENTS PRIMARY KEY (contentsno),
    CONSTRAINT FK_CONTENTS_CATE FOREIGN KEY (cateno2) REFERENCES cate(cateno) ON DELETE CASCADE,
    CONSTRAINT FK_CONTENTS_CATEGRP FOREIGN KEY (categrpno) REFERENCES categrp(categrpno) ON DELETE CASCADE
);

-- 각 컬럼에 주석 추가
COMMENT ON COLUMN contents.contentsno IS '콘텐츠 번호';
COMMENT ON COLUMN contents.title IS '제목';
COMMENT ON COLUMN contents.recom IS '관심수';
COMMENT ON COLUMN contents.cnt IS '조회수';
COMMENT ON COLUMN contents.word IS '검색어';
COMMENT ON COLUMN contents.rdate IS '등록일';
COMMENT ON COLUMN contents.file1 IS '썸네일';
COMMENT ON COLUMN contents.size1 IS '썸네일 파일 크기';
COMMENT ON COLUMN contents.price IS '판매가';
COMMENT ON COLUMN contents.memberno IS '회원 번호';
COMMENT ON COLUMN contents.cateno2 IS '카테고리 번호';
COMMENT ON COLUMN contents.categrpno IS '카테고리 그룹 번호';
