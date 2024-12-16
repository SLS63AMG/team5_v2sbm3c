DROP TABLE productrecom CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE productrecom;

-- 테이블 생성
-- productrecom 테이블 생성
CREATE TABLE productrecom (
    productrecomno NUMBER(10) NOT NULL PRIMARY KEY,   -- 제품 추천 번호
    scoreid        NUMBER(10) NOT NULL,               -- 점수 계산
    contentsno     NUMBER(10) NOT NULL,               -- 콘텐츠 번호
    memberno       NUMBER(10) NOT NULL,               -- 회원 번호
    CONSTRAINT FK_PRODUCTRECOM_CONTENTS FOREIGN KEY (contentsno) REFERENCES contents (contentsno) ON DELETE CASCADE,
    CONSTRAINT FK_PRODUCTRECOM_MEMBER FOREIGN KEY (memberno) REFERENCES member (memberno) ON DELETE CASCADE
);

-- productrecom 테이블 주석 추가
COMMENT ON TABLE productrecom IS '제품 추천'; 
COMMENT ON COLUMN productrecom.productrecomno IS '제품 추천 번호';
COMMENT ON COLUMN productrecom.scoreid IS '점수 계산';
COMMENT ON COLUMN productrecom.contentsno IS '콘텐츠 번호';
COMMENT ON COLUMN productrecom.memberno IS '회원 번호';

DROP SEQUENCE productrecom_seq;

CREATE SEQUENCE productrecom_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  



