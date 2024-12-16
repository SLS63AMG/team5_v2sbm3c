DROP TABLE aikeyword CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE aikeyword;

-- 테이블 생성
CREATE TABLE aikeyword (
	aino	NUMBER(10)	NOT NULL PRIMARY KEY,
    aikey VARCHAR(50)   NOT NULL,
    aidate  DATE          NOT NULL
	-- searchno	NUMBER(10)	NOT NULL
);

COMMENT ON TABLE aikeyword is 'ai 추천 키워드'; 
COMMENT ON COLUMN aikeyword.aino is 'ai 추천 번호';
COMMENT ON COLUMN aikeyword.aikey is 'ai 추천 키워드';
COMMENT ON COLUMN aikeyword.aidate is 'ai 키워드 생성 날짜';
-- OMMENT ON COLUMN aikeyword.searchno is '검색 기록 번호';

DROP SEQUENCE aikeyword_seq;

CREATE SEQUENCE aikeyword_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  

INSERT INTO aikeyword (aino, aikey, aidate) 
VALUES (keyword_seq.nextval, '아이폰', SYSDATE);

INSERT INTO aikeyword (aino, aikey, aidate) 
VALUES (keyword_seq.nextval, '아이폰6', SYSDATE);

INSERT INTO aikeyword (aino, aikey, aidate) 
VALUES (keyword_seq.nextval, '스마트폰', SYSDATE);

INSERT INTO aikeyword (aino, aikey, aidate) 
VALUES (keyword_seq.nextval, '애플', SYSDATE);
