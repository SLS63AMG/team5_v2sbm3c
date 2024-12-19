DROP TABLE contents CASCADE CONSTRAINTS;

CREATE TABLE contents (
	contentsno	NUMBER(10)	NOT NULL,
	site	VARCHAR2(50)	NOT NULL,
	title VARCHAR2(300)	NOT NULL,
	content	VARCHAR2(4000)	NULL,
	region	VARCHAR2(1000)	NULL,
	rdate	DATE	NOT NULL,
	price	NUMBER(10)	DEFAULT 0 NOT NULL,
	recom	NUMBER(7)	DEFAULT 0 NOT NULL,
	cnt	NUMBER(7)	DEFAULT 0 NOT NULL,
	state	VARCHAR2(100)	NOT NULL,
	url	VARCHAR2(2000)	NOT NULL,
	file1	VARCHAR2(500)	NOT NULL,
	size1	VARCHAR2(10)	DEFAULT 0 NOT NULL,
  CONSTRAINT PK_CONTENTS PRIMARY KEY (contentsno)
  -- FOREIGN KEY (cateno) REFERENCES cate(cateno) ON DELETE CASCADE,
  -- FOREIGN KEY (categrpno) REFERENCES categrp(categrpno) ON DELETE CASCADE
);

-- 각 컬럼에 주석 추가
COMMENT ON TABLE contents is '제품 컨텐츠'; 
COMMENT ON COLUMN contents.contentsno IS '제품 컨텐츠 번호';
COMMENT ON COLUMN contents.site IS '사이트';
COMMENT ON COLUMN contents.title IS '제목';
COMMENT ON COLUMN contents.content IS '내용';
COMMENT ON COLUMN contents.region IS '지역명';
COMMENT ON COLUMN contents.rdate IS '등록일';
COMMENT ON COLUMN contents.price IS '판매가';
COMMENT ON COLUMN contents.recom IS '관심수';
COMMENT ON COLUMN contents.cnt IS '조회수';
COMMENT ON COLUMN contents.state IS '판매상태';
COMMENT ON COLUMN contents.url IS '사이트 주소';
COMMENT ON COLUMN contents.file1 IS '제품 이미지';
COMMENT ON COLUMN contents.size1 IS '메인 이미지 크기';
-- COMMENT ON COLUMN contents.cateno IS '카테고리 번호';
-- COMMENT ON COLUMN contents.categrpno IS '카테고리 그룹 번호';

DROP SEQUENCE contents_seq;

CREATE SEQUENCE contents_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  

commit;










