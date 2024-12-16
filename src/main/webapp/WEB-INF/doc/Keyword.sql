DROP TABLE keyword CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE keyword;

-- 테이블 생성
CREATE TABLE keyword (
    keyno       NUMBER(10)    NOT NULL,       -- 키워드 번호
    searchkey   VARCHAR(50)   NOT NULL,       -- 검색 키워드
    searchdate  DATE          NOT NULL,       -- 검색 날짜
    memberno    NUMBER(10)    NOT NULL       -- 회원 번호 (memberno 컬럼 추가)
--    CONSTRAINT PK_KEYWORD PRIMARY KEY (keyno),
--    CONSTRAINT FK_KEYWORD_MEMBER FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
);

-- 테이블 및 컬럼 주석 추가
COMMENT ON TABLE keyword IS '사용자 키워드'; 
COMMENT ON COLUMN keyword.keyno IS '키워드 번호';
COMMENT ON COLUMN keyword.searchkey IS '검색기록 키워드';
COMMENT ON COLUMN keyword.searchdate IS '검색기록 날짜';
COMMENT ON COLUMN keyword.memberno IS '회원 번호';

DROP SEQUENCE keyword_seq;

CREATE SEQUENCE keyword_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;    

INSERT INTO keyword (keyno, searchkey, searchdate, memberno) 
VALUES (keyword_seq.nextval, '스마트폰', SYSDATE,1);

INSERT INTO keyword (keyno, searchkey, searchdate, memberno) 
VALUES (keyword_seq.nextval, '데님', SYSDATE, 1);

INSERT INTO keyword (keyno, searchkey, searchdate, memberno) 
VALUES (keyword_seq.nextval, '청바지', SYSDATE, 1);

INSERT INTO keyword (keyno, searchkey, searchdate, memberno) 
VALUES (keyword_seq.nextval, '패션', SYSDATE, 1);
  
COMMIT;

