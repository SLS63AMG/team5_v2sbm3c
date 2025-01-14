CREATE TABLE inquiry (
    inquiryno NUMBER(10) NOT NULL,
    title VARCHAR2(500) NOT NULL,
    content CLOB NOT NULL,
    rdate DATE DEFAULT SYSDATE,
    state NUMBER(4) DEFAULT 1 NOT NULL,
    filename VARCHAR2(500),
    memberno NUMBER(10) NOT NULL,
    CONSTRAINT PK_inquiry_inquiryno PRIMARY KEY (inquiryno),
    CONSTRAINT FK_inquiry_memberno FOREIGN KEY (memberno) 
        REFERENCES member(memberno) 
        ON DELETE CASCADE
);

CREATE SEQUENCE inquiry_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE 
    CACHE 2
    NOORDER;
    
    
-- 문의 사항 신청
INSERT INTO inquiry(inquiryno, title, content, filename, memberno)
VALUES(inquiry_seq.nextval, '제목', '내용', '파일 내용', '멤버 번호');

-- 문의 사항 수정
UPDATE inquiry
SET title='제목', content='내용', rdate=sysdate, filename='파일 내용'
WHERE memberno = '멤버 번호';

-- 문의 사항 취소
DELETE inquiry
WHERE inquiryno='번호' AND memberno = '멤버 번호';
------------------------------------------------------------------------------
-- 사용자의 문의 사항 페이징
SELECT inquiryno, title, content, rdate, state, filename, memberno, name, r
FROM (
    SELECT inquiryno, title, content, rdate, state, filename, memberno, name, rownum as r
    FROM (
        SELECT i.inquiryno, i.title, i.content, i.rdate, i.state, i.filename, i.memberno, m.name
        FROM inquiry i INNER JOIN member m
        ON i.memberno = m.memberno
        WHERE (UPPER(i.title) LIKE '%' || UPPER('단어') || '%')
        AND i.memberno = '멤버 번호'
        ORDER BY i.rdate ASC
    )
);
WHERE r &gt;='시작번호' AND r &lt;='끝번호' <!-- WHERE r >= 1 AND r <= 3 -->

------------------------------------------------------------------------------

-- 관리자의 문의 사항 페이징
SELECT inquiryno, title, content, rdate, state, filename, memberno, name, r
FROM (
    SELECT inquiryno, title, content, rdate, state, filename, memberno, name, rownum as r
    FROM (
        SELECT i.inquiryno, i.title, i.content, i.rdate, i.state, i.filename, i.memberno, m.name
        FROM inquiry i INNER JOIN member m
        ON i.memberno = m.memberno
        WHERE (UPPER(i.title) LIKE '%' || UPPER('단어') || '%') OR (UPPER(m.name) LIKE '%' || UPPER('단어') || '%')

        <if test="state != null">
            AND i.state = #{state}
        </if>
        
        ORDER BY i.rdate ASC
    )
)
WHERE r &gt;='시작번호' AND r &lt;='끝번호' <!-- WHERE r >= 1 AND r <= 3 -->


-- 검색 갯수
SELECT count(*) as cnt
FROM inquiry i
INNER JOIN member m
ON i.memberno = m.memberno

<!-- 조건에 따라 선택적으로 WHERE 절을 추가 -->
<choose>
    <!-- order가 2인 경우 title만 검색 -->
    <when test="order == 2">
        WHERE UPPER(i.title) LIKE '%' || UPPER(#{word}) || '%'
    </when>

    <!-- order가 1인 경우 title과 name을 검색하고 state 조건도 추가 -->
    <when test="order == 1">
        WHERE (UPPER(i.title) LIKE '%' || UPPER(#{word}) || '%') 
           OR (UPPER(m.name) LIKE '%' || UPPER(#{word}) || '%')
        
        <!-- state가 존재하면 state 조건 추가 -->
        <if test="state != null">
            AND i.state = #{state}
        </if>
    </when>
    <!-- order가 1, 2가 아니면 기본적으로 WHERE를 사용하지 않음 -->
    <otherwise>
        WHERE 1 = 1
    </otherwise>
</choose>


------------------------------------------------------------------------------
    