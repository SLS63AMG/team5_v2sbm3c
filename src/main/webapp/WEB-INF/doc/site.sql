CREATE TABLE site (
  siteno     NUMBER(10)    NOT NULL,  
  sitename   VARCHAR2(100) NOT NULL, 
  siteurl    VARCHAR2(100) NOT NULL,
  contentsno NUMBER(10)    NOT NULL,  -- 추가된 컬럼, 외래 키
  PRIMARY KEY (siteno),
  FOREIGN KEY (contentsno) REFERENCES contents(contentsno)  -- 외래 키 제약 조건
);

-- 테이블 설명
COMMENT ON TABLE site IS '사이트';

-- 컬럼 설명
COMMENT ON COLUMN site.siteno IS '사이트 번호';
COMMENT ON COLUMN site.sitename IS '사이트 이름';
COMMENT ON COLUMN site.siteurl IS '사이트 주소';
COMMENT ON COLUMN site.contentsno IS '컨텐츠 번호';  -- 추가된 컬럼에 대한 설명
