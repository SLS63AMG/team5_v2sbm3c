CREATE TABLE surveyitem(
surveyitemno                  NUMBER(10) NOT NULL, 
surveyno                      NUMBER(10) NULL , -- FK
itemseq                        NUMBER(2) DEFAULT 1 NOT NULL,
item                           VARCHAR2(200) NOT NULL,
itemcnt                        NUMBER(7) DEFAULT 0 NOT NULL,
    CONSTRAINT PK_surveyitem_surveyitemno PRIMARY KEY (surveyitemno),
    CONSTRAINT FK_surveyitem_surveyno FOREIGN KEY (surveyno) REFERENCES survey(surveyno) ON DELETE CASCADE
);

COMMENT ON TABLE surveyitem is '설문 조사 항목';
COMMENT ON COLUMN surveyitem.surveyitemno is '설문 조사 항목 번호';
COMMENT ON COLUMN surveyitem.surveyno is '설문 조사 번호';
COMMENT ON COLUMN surveyitem.itemseq is '항목 출력 순서';
COMMENT ON COLUMN surveyitem.item is '항목';
COMMENT ON COLUMN surveyitem.itemcnt is '항목 선택 인원';



