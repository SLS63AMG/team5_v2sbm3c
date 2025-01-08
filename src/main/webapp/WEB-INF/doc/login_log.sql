-- CREATE
CREATE TABLE login_log (
    logno    NUMBER(10)    NOT NULL,
    conip    VARCHAR(40)   NOT NULL,
    id       VARCHAR2(200) NOT NULL,
    jdate    DATE          NOT NULL,
    sw       CHAR(1)       NOT NULL DEFAULT 'N',
    CONSTRAINT PK_loginlog_logno PRIMARY KEY (logno)  -- logno를 기본키로 지정
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



-- 로그인 로그 페이징
-- word: 검색어 (로그인 ID에서 검색).

-- order_state: 정렬 기준 (예: 'id_asc', 'id_desc', 'jdate_asc' 등).
-- login_state: 로그인 상태 (예: 'Y', 'N' 등).
-- date_state: 날짜 조건의 상태 (예: 'eq', 'be', 'bi', 'sm' 등).
-- date: 날짜 (단일 날짜, date_state가 'eq'일 때 사용).
-- startDate: 시작 날짜 (단일 날짜, date_state가 'be'일 때 사용).
-- endDate: 끝 날짜 (단일 날짜, date_state가 'be'일 때 사용).
-- start_num: 페이징 시작 번호.
-- end_num: 페이징 끝 번호.

<select id="selectLoginLogs" parameterType="HashMap" resultType="com.example.domain.LoginLogVO">
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
            1 = 1
            <!-- 검색어 조건 -->
            <if test="word != null and word != ''">
                AND UPPER(id) LIKE '%' || UPPER(#{word}) || '%'
            </if>

            <!-- 로그인 상태 조건 -->
            <if test="login_state != null and login_state != ''">
                AND sw = #{login_state}
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
                        AND jdate &gt; TO_DATE(#{date}, 'YYYY-MM-DD')
                    </when>
                    <when test="date_state == 'sm'">
                        AND jdate &lt; TO_DATE(#{date}, 'YYYY-MM-DD')
                    </when>
                </choose>
            </if>
        </where>
    )
    WHERE r &gt;= #{start_num} AND r &lt;= #{end_num}  -- 페이징 번호 범위 설정 (시작번호, 끝번호)
    ORDER BY r ASC
</select>



-- 검색 항목 갯수
-- word : 검색어

-- date_state(eq, bi, sm)
-- date : 지정 날짜

-- date_state(be)
-- startDate : 시작 날자짜  |   endDate : 끝 날짜
SELECT count(*) as cnt
FROM login_log
<where>
    1 = 1
    <!-- 검색어 조건 -->
    <if test="word != null and word != ''">
        AND UPPER(id) LIKE '%' || UPPER(#{word}) || '%'
    </if>

    <!-- 로그인 상태 조건 -->
    <if test="login_state != null and login_state != ''">
        AND sw = #{login_state}
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
                AND jdate &gt; TO_DATE(#{date}, 'YYYY-MM-DD')
            </when>
            <when test="date_state == 'sm'">
                AND jdate &lt; TO_DATE(#{date}, 'YYYY-MM-DD')
            </when>
        </choose>
    </if>
</where>






