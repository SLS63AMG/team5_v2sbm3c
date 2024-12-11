DROP TABLE aikeyword CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE aikeyword;

-- 테이블 생성
CREATE TABLE aikeyword (
	aino	NUMBER(10)	NOT NULL PRIMARY KEY,
	searchno	NUMBER(10)	NOT NULL
);

COMMENT ON TABLE aikeyword is 'ai 추천 키워드'; 
COMMENT ON COLUMN aikeyword.aino is 'ai 추천 번호';
COMMENT ON COLUMN aikeyword.searchno is '검색 기록 번호';

DROP SEQUENCE aikeyword_seq;

CREATE SEQUENCE aikeyword_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  

