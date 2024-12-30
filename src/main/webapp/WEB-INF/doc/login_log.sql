-- CREATE
CREATE TABLE login_log (
	logno	NUMBER(10)	NOT NULL,
	conip	VARCHAR(40)	NOT NULL,
	id	    VARCHAR2(200)	NOT NULL,
	jdate	DATE	NOT NULL,
	sw	    CHAR(1)	NOT NULL	DEFAULT 'N'
);

-- 시퀀스
CREATE SEQUENCE log_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE 
    CACHE 2
    NOORDER;

-- 로그인 기록 등록록
INSERT INTO login_log (logno, conip, id, jdate, sw)
VALUES (log_seq.nextval, '아이피', '아이디', SYSDATE, 'N');

-- 로그인 기록 삭제제
DELETE FROM login_log
WHERE logno=1;



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



-- 로그인 로그 페이징

-- order_state
-- word
-- date_state
-- date
-- startDate    |   endDate
-- startRow     |   endRow

SELECT logno, conip, id, jdate, sw, r
FROM (
    SELECT logno, conip, id, jdate, sw,
           ROW_NUMBER() OVER (
               <choose>
                    <!-- 정렬 기준에 따른 동적 ORDER BY -->
                    <when test="order_state == 'id_asc'">
                        ORDER BY id ASC
                    </when>
                    <when test="order_state == 'id_desc'">
                        ORDER BY id DESC
                    </when>
                    <when test="order_state == 'jdate_asc'">
                        ORDER BY jdate ASC
                    </when>
                    <when test="order_state == 'jdate_desc'">
                        ORDER BY jdate DESC
                    </when>
                    <when test="order_state == 'sw_asc'">
                        ORDER BY sw ASC
                    </when>
                    <when test="order_state == 'sw_desc'">
                        ORDER BY sw DESC
                    </when>
                    <!-- 기본값으로 logno 오름차순 정렬 -->
                    <otherwise>
                        ORDER BY logno ASC
                    </otherwise>
                </choose>
           ) AS r
    FROM login_log
    <where>
        <!-- 검색어 조건 -->
        <if test="word != null and word != ''">
            AND (UPPER(conip) LIKE '%' || UPPER(#{word}) || '%')
            OR (UPPER(id) LIKE '%' || UPPER(#{word}) || '%')
        </if>

        <!-- 날짜 상태에 따른 조건 -->
        <if test="date_state != null">
            <choose>
                <when test="date_state == 'eq'">
                    AND jdate = TO_DATE(#{date}, 'YYYY-MM-DD')
                </when>
                <when test="date_state == 'be'">
                    AND jdate BETWEEN TO_DATE(#{startDate}, 'YYYY-MM-DD') AND TO_DATE(#{endDate}, 'YYYY-MM-DD')
                </when>
                <when test="date_state == 'bi'">
                    AND jdate > TO_DATE(#{date}, 'YYYY-MM-DD')
                </when>
                <when test="date_state == 'sm'">
                    AND jdate < TO_DATE(#{date}, 'YYYY-MM-DD')
                </when>
            </choose>
        </if>

    </where>
)
WHERE r >= #{startRow} AND r <= #{endRow}  -- 페이징 번호 범위 설정 (시작번호, 끝번호)
ORDER BY r ASC




































-- 검색 항목 갯수
-- word : 검색어

-- date_state(eq, bi, sm)
-- date : 지정 날짜

-- date_state(be)
-- startDate : 시작 날자짜  |   endDate : 끝 날짜


SELECT count(*) as cnt
FROM login_log
<where>

  <!-- word 검색이 있을 경우 -->
  <if test="word != null and word != ''"> 
    (UPPER(conip) LIKE '%' || UPPER(#{word}) || '%')
    OR (UPPER(id) LIKE '%' || UPPER(#{word}) || '%')
  </if>


  <!-- 날짜 상태에 따른 필터링 -->
  <if test="date_state != null">
    <choose>
      <when test="date_state == 'eq'">
        AND jdate = TO_DATE(#{date}, 'YYYY-MM-DD') -- 정확히 같은 날짜
      </when>
      <when test="date_state == 'be'">
        AND jdate BETWEEN TO_DATE(#{startDate}, 'YYYY-MM-DD') AND TO_DATE(#{endDate}, 'YYYY-MM-DD') -- 날짜1과 날짜2 사이
      </when>
      <when test="date_state == 'bi'">
        AND jdate > TO_DATE(#{date}, 'YYYY-MM-DD') -- 큰 날짜
      </when>
      <when test="date_state == 'sm'">
        AND jdate < TO_DATE(#{date}, 'YYYY-MM-DD') -- 작은 날짜
      </when>
    </choose>
  </if>


</where>






