CREATE TABLE answer (
    answerno    NUMBER(10)    NOT NULL,
    content     CLOB          NOT NULL,
    rdate       DATE          DEFAULT SYSDATE,
    filename        VARCHAR2(500) NULL,
    inquiryno     NUMBER(10)    NOT NULL,
    memberno     NUMBER(10)    NOT NULL,
    CONSTRAINT PK_answer_answerno PRIMARY KEY (answerno),
    CONSTRAINT FK_answer_inquiryno FOREIGN KEY (inquiryno) REFERENCES inquiry (inquiryno)ON DELETE CASCADE,
    CONSTRAINT FK_answer_memberno FOREIGN KEY (memberno) REFERENCES member (memberno)ON DELETE CASCADE
);

CREATE SEQUENCE answer_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE 
    CACHE 2
    NOORDER;
    

-- 답변 등록
INSERT INTO answer(inquiryno, content, rdate, filename, inquiryno, memberno)
VALUES(inquiry_seq.nextval, '내용', SYSDATE, '파일 이름', '문의 번호', '유저 번호');

UPDATE inquiry
SET state=2
WHERE inquiryno='문의 번호';

-- 답변 수정
UPDATE answer
SET content='내용', rdate=sysdate, filename='파일 이름'
WHERE answerno='답변 번호';

-- 답변 삭제
DELETE answer
WHERE answerno = '답변 번호';

UPDATE inquiry
SET state=1
WHERE inquiryno='문의 번호';

-- 답변 가져오기
SELECT a.answerno, a.content, a.rdate, a.filename, a.inquiryno, a.memberno, m.name
FROM answer a INNER JOIN inquiry i
ON a.inquiryno = i.inquiryno
INNER JOIN member m
ON a.memberno = m.memberno
WHERE inquiryno = '문의 번호'



UPDATE inquiry
SET 
<if test="state == 1">state = 1</if> 
<if test="state == 2">state = 2</if>
WHERE inquiryno = #{inquiryno}







