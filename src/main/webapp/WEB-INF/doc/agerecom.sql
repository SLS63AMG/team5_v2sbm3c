DROP TABLE agerecom CASCADE CONSTRAINTS;

CREATE TABLE agerecom (
    agerecomno NUMBER(10) NOT NULL, -- 고유 ID   
    agerecomdate DATE NOT NULL, -- 추천된 날짜
    contentsno NUMBER(10) NOT NULL,
    memberno NUMBER(10) NOT NULL,
    CONSTRAINT PK_agerecom PRIMARY KEY (agerecomno),
    FOREIGN KEY (contentsno) REFERENCES contents(contentsno) ON DELETE CASCADE,
    FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
);

COMMENT ON TABLE agerecom IS '연령별 추천';
COMMENT ON COLUMN agerecom.agerecomno   IS '연령별 추천 번호';
COMMENT ON COLUMN agerecom.agerecomdate   IS '연령별 추천 날짜';
COMMENT ON COLUMN agerecom.contentsno   IS '제품 추천 번호';
COMMENT ON COLUMN agerecom.memberno   IS '회원 번호';

DROP SEQUENCE agerecom_seq;

CREATE SEQUENCE agerecom_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  
  
COMMIT;





