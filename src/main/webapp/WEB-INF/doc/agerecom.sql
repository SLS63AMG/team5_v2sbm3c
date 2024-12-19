DROP TABLE agerecom CASCADE CONSTRAINTS;

CREATE TABLE agerecom (
    agerecomno NUMBER(10) NOT NULL, -- 고유 ID   
    agerecomdate DATE NOT NULL, -- 추천된 날짜
    CONSTRAINT PK_agerecom PRIMARY KEY (agerecomno),
    CONSTRAINT FK_agerecom_CONTENTS FOREIGN KEY (agerecomno) REFERENCES contents(contentsno) ON DELETE CASCADE,
    CONSTRAINT FK_agerecom_MEMBER FOREIGN KEY (agerecomno) REFERENCES member(memberno) ON DELETE CASCADE
);

COMMENT ON TABLE agerecom IS '연령별 추천';
COMMENT ON COLUMN agerecom.agerecomno   IS '연령별 추천 번호';
COMMENT ON COLUMN agerecom.agerecomdate   IS '연령별 추천 날짜';

DROP SEQUENCE agerecom_seq;

CREATE SEQUENCE agerecom_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  
  
COMMIT;





