DROP TABLE productrecom CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE productrecom;

-- 테이블 생성
CREATE TABLE productrecom (
	productrecomno	NUMBER(10)	NOT NULL PRIMARY KEY,
	scoreid	NUMBER(10)	NOT NULL,
	FOREIGN KEY (contentsno) REFERENCES contents (contentsno) ON DELETE CASCADE,
    FOREIGN KEY (memberno) REFERENCES member (memberno) ON DELETE CASCADE
);

COMMENT ON TABLE productrecom is '제품 추천'; 
COMMENT ON COLUMN productrecom.productrecomno is '제품 추천 번호';
COMMENT ON COLUMN productrecom.socreid is '점수 계산';

DROP SEQUENCE productrecom_seq;

CREATE SEQUENCE productrecom_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  



