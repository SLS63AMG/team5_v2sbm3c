DROP TABLE recent CASCADE CONSTRAINTS;

CREATE TABLE recent (
    recentno NUMBER(10) NOT NULL, -- 고유 ID
    CONSTRAINT PK_recent PRIMARY KEY (recentno),
    CONSTRAINT FK_recent_CONTENTS FOREIGN KEY (recentno) REFERENCES contents(contentsno) ON DELETE CASCADE,
    CONSTRAINT FK_recent_MEMBER FOREIGN KEY (recentno) REFERENCES member(memberno) ON DELETE CASCADE
);

COMMENT ON TABLE recent IS '최근 본 기록'; 
COMMENT ON COLUMN recent.recentno   IS '최근 본 기록 번호';

DROP SEQUENCE recent_seq;

CREATE SEQUENCE recent_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  
  
COMMIT;
  
  
  