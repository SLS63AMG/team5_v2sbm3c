DROP TABLE keyword CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE keyword;

-- 테이블 생성
CREATE TABLE keyword (
    keyno       NUMBER(10)    NOT NULL PRIMARY KEY, -- 키워드 번호
    searchkey   VARCHAR(50)   NOT NULL,            -- 검색 키워드
    searchcnt   NUMBER(10)    NOT NULL,            -- 검색 횟수
    searchdate  DATE          NOT NULL,            -- 검색 날짜
    interkey    VARCHAR(50)   NOT NULL,            -- 인터 키워드
    FOREIGN KEY (memberno) REFERENCES member (memberno) ON DELETE CASCADE
);


-- 테이블 및 컬럼 주석 추가
COMMENT ON TABLE keyword is '사용자 키워드'; 
COMMENT ON COLUMN keyword.keyno is '키워드 번호';
COMMENT ON COLUMN keyword.searchkey is '검색기록 키워드';
COMMENT ON COLUMN keyword.searchcnt is '검색기록 카운트';
COMMENT ON COLUMN keyword.searchdate is '검색기록 날짜';
COMMENT ON COLUMN keyword.interkey is '관심사 키워드';
COMMENT ON COLUMN keyword.memberno is '회원 번호';

DROP SEQUENCE keyword_seq;

CREATE SEQUENCE keyword_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;    
  
COMMIT;

