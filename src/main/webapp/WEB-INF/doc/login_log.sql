-- CREATE
CREATE TABLE login_log (
	logno	NUMBER(10)	NOT NULL,
	conip	VARCHAR(40)	NOT NULL,
	id	    VARCHAR2(200)	NOT NULL,
	jdate	DATE	NOT NULL,
	sw	    CHAR(1)	NOT NULL	DEFAULT 'N'
);

-- 시퀀스
CREATE SEQUENCE notice_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE 
    CACHE 2
    NOORDER;

-- 공지사항 등록
INSERT INTO notice (noticeno, title, content, cdate, memberno)
VALUES (notice_seq.nextval, '제목', '내용', SYSDATE, 123);

-- 공지사항 업데이트
UPDATE notice
SET title='제목', content='내용', udate=sysdate, visible='Y', importance=123
WHERE noticeno=123;

-- 공지사항 조회수 상승
UPDATE notice
SET cnt = cnt + 1
WHERE noticeno=123;

-- 공지사항 삭제
DELETE FROM notice
WHERE noticeno=123;

-- 공지사항 페이징
SELECT n.noticeno, n.title, n.cdate, n.udate, n.cnt, n.importance, n.visible, n.memberno, m.name, r
FROM (
    SELECT n.noticeno, n.title, n.cdate, n.udate, n.cnt, n.importance, n.visible, n.memberno, m.name, rownum as r
    FROM (
	SELECT n.noticeno, n.title, n.cdate, n.udate, n.cnt, n.importance, n.visible, n.memberno, m.name
	FROM notice n INNER JOIN member m
	ON n.memberno = m.memberno
	WHERE UPPER(n.title) LIKE '%' || UPPER('word') || '%'
    )
    ORDER BY n.importance ASC, r ASC
)
WHERE r &gt;='시작번호' AND r &lt;='끝번호' <!-- WHERE r >= 1 AND r <= 3 -->


-- 검색 항목 갯수
SELECT count(*) as cnt
FROM notice
<if test="word != null and word != ''"> <!-- 검색 상태라면 WHERE 생성-->
WHERE (UPPER(title) LIKE '%' || UPPER(#{word}) || '%')
</if>;






