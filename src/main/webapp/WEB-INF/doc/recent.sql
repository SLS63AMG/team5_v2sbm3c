DROP TABLE recent CASCADE CONSTRAINTS;

CREATE TABLE recent (
    recentno NUMBER(10) NOT NULL, -- 고유 ID
    contentsno NUMBER(10) NOT NULL,
    memberno NUMBER(10) NOT NULL,
    CONSTRAINT PK_recent PRIMARY KEY (recentno),
    FOREIGN KEY (contentsno) REFERENCES contents(contentsno) ON DELETE CASCADE,
    FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
);

COMMENT ON TABLE recent IS '최근 본 기록'; 
COMMENT ON COLUMN recent.recentno   IS '최근 본 기록 번호';
COMMENT ON COLUMN recent.contentsno   IS '제품 추천 번호';
COMMENT ON COLUMN recent.memberno   IS '회원 번호';


DROP SEQUENCE recent_seq;

CREATE SEQUENCE recent_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;  
  
COMMIT;
  
  
  