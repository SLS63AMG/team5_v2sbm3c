DROP TABLE keyword CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE keyword;

-- 테이블 생성
CREATE TABLE keyword (
    searchno       NUMBER(10)    NOT NULL,       -- 검색어 번호
    searchword   VARCHAR(50)   NOT NULL,       -- 검색어
    searchdate  DATE          NOT NULL,       -- 검색 날짜
    CONSTRAINT PK_keyword PRIMARY KEY (searchno),
    CONSTRAINT FK_KEYWORD_MEMBER FOREIGN KEY (searchno) REFERENCES member(memberno) ON DELETE CASCADE
);

-- 테이블 및 컬럼 주석 추가
COMMENT ON TABLE keyword IS '사용자 검색어'; 
COMMENT ON COLUMN keyword.searchno IS '검색어 번호';
COMMENT ON COLUMN keyword.searchword IS '검색어';
COMMENT ON COLUMN keyword.searchdate IS '검색어 기록 날짜';

DROP SEQUENCE keyword_seq;

CREATE SEQUENCE keyword_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;    

INSERT INTO keyword (searchno, searchword, searchdate, memberno) 
VALUES (keyword_seq.nextval, '아이폰6', SYSDATE,1);

INSERT INTO keyword (searchno, searchword, searchdate, memberno) 
VALUES (keyword_seq.nextval, '플레어 데님', SYSDATE, 1);

INSERT INTO keyword (searchno, searchword, searchdate, memberno) 
VALUES (keyword_seq.nextval, 'stu 가디건', SYSDATE, 1);

INSERT INTO keyword (searchno, searchword, searchdate, memberno) 
VALUES (keyword_seq.nextval, '갤럭시 울트라22s', SYSDATE, 1);
  
COMMIT;

