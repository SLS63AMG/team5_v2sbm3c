-- 카테고리 테이블 생성
DROP TABLE cate CASCADE CONSTRAINTS;

CREATE TABLE cate (
    cateno     NUMBER(10)     NOT NULL,       -- 카테고리 번호
    name       VARCHAR2(100)    NOT NULL,       -- 카테고리 이름
    cnt        NUMBER(7)      DEFAULT 0 NOT NULL, -- 관련 자료 수
    rdate      DATE           NOT NULL,       -- 등록일
    categrpno  NUMBER(10)     NOT NULL,       -- 카테고리 그룹 번호
    categrpname VARCHAR2(100) NOT NULL
  --  CONSTRAINT PK_CATE PRIMARY KEY (cateno),
  --  CONSTRAINT FK_CATE_CATEGRP FOREIGN KEY (categrpno) REFERENCES categrp(categrpno) ON DELETE CASCADE
);


-- 각 컬럼에 주석 추가
COMMENT ON TABLE cate IS '카테고리 테이블';
COMMENT ON COLUMN cate.cateno IS '카테고리 번호';
COMMENT ON COLUMN cate.name IS '카테고리 이름';
COMMENT ON COLUMN cate.cnt IS '관련 자료 수';
COMMENT ON COLUMN cate.rdate IS '등록일';
COMMENT ON COLUMN cate.categrpno IS '카테고리 그룹 번호';
COMMENT ON COLUMN cate.categrpname IS '카테고리 그룹 이름';

DROP SEQUENCE cate_seq;

CREATE SEQUENCE cate_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  

INSERT INTO cate (cateno, name, cnt, rdate, categrpno, categrpname) 
VALUES (cate_seq.nextval, '아이폰6xe', 1, SYSDATE, 1, '스마트폰');

INSERT INTO cate (cateno, name, cnt, rdate, categrpno, categrpname) 
VALUES (cate_seq.nextval, '아키라 플레어 데님팬츠', 1, SYSDATE, 2, '청바지');

INSERT INTO cate (cateno, name, cnt, rdate, categrpno, categrpname) 
VALUES (cate_seq.nextval, 'stu 가디건', 1, SYSDATE, 2, '패션');

INSERT INTO cate (cateno, name, cnt, rdate, categrpno, categrpname) 
VALUES (cate_seq.nextval, '갤럭시S22', 1, SYSDATE, 1, '스마트폰');

INSERT INTO cate (cateno, name, cnt, rdate, categrpno) 
VALUES (cate_seq.nextval, '6', '아이폰6xe', 1, SYSDATE, 5);


commit;








