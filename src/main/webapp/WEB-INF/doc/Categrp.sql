-- 카테고리 그룹 테이블 생성
CREATE TABLE categrp (
    categrpno NUMBER(10) NOT NULL, -- 카테고리 그룹 번호
    name VARCHAR2(30) NOT NULL,    -- 카테고리 그룹 이름
    CONSTRAINT PK_CATEGRP PRIMARY KEY (categrpno)
);

-- 각 컬럼에 주석 추가
COMMENT ON COLUMN categrp.categrpno IS '카테고리 그룹 번호';
COMMENT ON COLUMN categrp.name IS '카테고리 그룹 이름';
COMMENT ON COLUMN categrp.rdate IS '등록일';

-- 테이블 주석 추가
COMMENT ON TABLE categrp IS '카테고리 그룹 테이블';
