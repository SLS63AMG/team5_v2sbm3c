-- 카테고리 테이블 생성
CREATE TABLE cate (
    cateno NUMBER(10) NOT NULL,       -- 카테고리 번호
    name VARCHAR2(30) NOT NULL,       -- 카테고리 이름
    cnt NUMBER(7) NOT NULL DEFAULT 0, -- 관련 자료 수
    rdate DATE NOT NULL,              -- 등록일
    categrpno NUMBER(10) NOT NULL,    -- 카테고리 그룹 번호
    CONSTRAINT PK_CATE PRIMARY KEY (cateno),
    CONSTRAINT FK_CATE_CATEGRP FOREIGN KEY (categrpno) REFERENCES categrp(categrpno) ON DELETE CASCADE
);

-- 각 컬럼에 주석 추가
COMMENT ON COLUMN cate.cateno IS '카테고리 번호';
COMMENT ON COLUMN cate.name IS '카테고리 이름';
COMMENT ON COLUMN cate.cnt IS '관련 자료 수';
COMMENT ON COLUMN cate.rdate IS '등록일';
COMMENT ON COLUMN cate.categrpno IS '카테고리 그룹 번호';

-- 테이블 주석 추가
COMMENT ON TABLE cate IS '카테고리 테이블';
