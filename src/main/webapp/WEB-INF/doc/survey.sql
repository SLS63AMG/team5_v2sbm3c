DROP TABLE survey;
CREATE TABLE survey(
    surveyno                   NUMBER(10) NOT NULL,
    topic                         VARCHAR2(200) NOT NULL,
    startdate                     VARCHAR2(10) NOT NULL,
    enddate                     VARCHAR2(10) NULL ,
    poster                        VARCHAR2(100) NULL ,
    postersaved                 VARCHAR(100)          NULL,  -- 저장된 파일명, image
    posterthumb               VARCHAR(100)          NULL,   -- preview image
    postersize                   NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈
    cnt                            NUMBER(7) DEFAULT 0 NOT NULL,
    continueyn                  CHAR(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT PK_survey_surveyno PRIMARY KEY (surveyno)
);



COMMENT ON TABLE survey is '설문조사';
COMMENT ON COLUMN survey.surveyno is '설문 조사 번호';
COMMENT ON COLUMN survey.topic is '제목';
COMMENT ON COLUMN survey.startdate is '시작 날짜';
COMMENT ON COLUMN survey.enddate is '종료 날짜';
COMMENT ON COLUMN survey.poster is '포스터 파일';
COMMENT ON COLUMN survey.postersaved is '포스터 저장된 파일명';
COMMENT ON COLUMN survey.posterthumb is '포스터 썸네일';
COMMENT ON COLUMN survey.postersize is '포스터 이미지 크기';
COMMENT ON COLUMN survey.cnt is '참여 인원';
COMMENT ON COLUMN survey.continueyn is '진행 여부';

CREATE SEQUENCE survey_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9999999999
    CACHE 2
    NOCYCLE;
    
    
    
SELECT *
FROM (
    SELECT ROW_NUMBER() OVER (ORDER BY surveyno DESC) AS rownum,
           surveyno, topic, startdate, enddate, poster, postersaved, posterthumb, postersize, cnt, continueyn
    FROM survey
) subquery
WHERE subquery.rownum BETWEEN 1 AND 3;

SELECT *
FROM (
    SELECT ROW_NUMBER() OVER (ORDER BY surveyno DESC) AS rownum,
           surveyno, topic, startdate, enddate, poster, postersaved, posterthumb, postersize, cnt, continueyn
    FROM survey
) subquery -- 하위 쿼리에 별칭을 명시
WHERE rownum BETWEEN 1 AND 3;


WITH numbered_rows AS (
    SELECT ROW_NUMBER() OVER (ORDER BY surveyno DESC) AS rownum,
           surveyno, topic, startdate, enddate, poster, postersaved, posterthumb, postersize, cnt, continueyn
    FROM survey
)
SELECT *
FROM numbered_rows
WHERE rownum BETWEEN 1 AND 3;

