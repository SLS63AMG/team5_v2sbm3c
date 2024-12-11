-- CREATE 시작
-- Create(테이블 생성)
CREATE TABLE member(
    memberno   NUMBER(10)    NOT NULL,
    name       VARCHAR2(100) NOT NULL,
    mid        VARCHAR2(200) NOT NULL,
    mpasswd    VARCHAR2(500) NOT NULL,
    tel        VARCHAR2(20),
    email      VARCHAR2(100) NOT NULL,
    zipcode    VARCHAR2(200),
    address1   VARCHAR2(400),
    address2   VARCHAR2(400),
    grade      NUMBER(10)    DEFAULT 10 NOT NULL,
    state      NUMBER(10)    DEFAULT 0 NOT NULL,
    sdate      DATE          DEFAULT SYSDATE,
    udate      DATE,
    ddate      DATE,
    token      VARCHAR2(200),
    CONSTRAINT PK_member_no PRIMARY KEY (memberno),
    CONSTRAINT UQ_member_token UNIQUE (token)
);

-- Create(시퀀스 생성)
CREATE SEQUENCE member_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9999999999
    CACHE 2
    NOCYCLE;
    
-- Create(코멘트 생성)
COMMENT ON COLUMN member.memberno   IS '회원 번호';
COMMENT ON COLUMN member.name       IS '이름';
COMMENT ON COLUMN member.mid        IS '아이디';
COMMENT ON COLUMN member.mpasswd    IS '비밀번호';
COMMENT ON COLUMN member.tel        IS '전화 번호';
COMMENT ON COLUMN member.email      IS '이메일';
COMMENT ON COLUMN member.zipcode    IS '우편 번호';
COMMENT ON COLUMN member.address1   IS '도로명 주소';
COMMENT ON COLUMN member.address2   IS '상세 주소';
COMMENT ON COLUMN member.grade      IS '권한';
COMMENT ON COLUMN member.state      IS '상태';
COMMENT ON COLUMN member.sdate      IS '가입일';
COMMENT ON COLUMN member.udate      IS '수정일';
COMMENT ON COLUMN member.ddate      IS '탈퇴일';
COMMENT ON COLUMN member.token      IS '식별 토큰';
-- CREATE 끝

-------------------------Member.xml 기본 시작-------------------------
------1-------------2----------------3-------------------4-----------
-- 회원 가입 → 로그인 + 토큰 부여 → 토큰을 통한 식별 → 로그아웃 + 토큰 삭제 --
---------------------------------------------------------------------
-- 1-1. 아이디 중복 체크 ※ checkID
SELECT COUNT(mid) as cnt
FROM member
WHERE mid='아이디';

-- 1-2. 회원 가입 ※ sign_up
INSERT INTO member(memberno, name, mid, mpasswd, tel, email, zipcode, address1, address2, sdate)
VALUES(member_seq.nextval, '이름', '아이디', '비밀번호', '전화번호', '이메일', '우편번호', '도로명주소', '상세주소', sysdate);
------------------------------------------------------------------------------------------------
-- 2-1. 로그인(1:성공, 0:실패) ※ login
SELECT COUNT(memberno) as cnt
FROM member
WHERE mid='아이디' AND mpasswd='비밀번호';

-- 2-2. 토큰 중복 확인(1:토큰 재생성 후 다시 확인, 0:중복값 없음) ※ checkToken
SELECT COUNT(token) as cnt
FROM member
WHERE token='식별토큰';

-- 2-2. 토큰 부여 ※ token_grant
UPDATE member
SET token=''
WHERE mid='아이디';

-- 2-3. 세션 기본 값 호출 ※ basic_session
SELECT name, mid, grade
FROM member
WHERE token='식별토큰';
------------------------------------------------------------------------------------------------
-- 3-1. 토큰을 통한 정보 가져오기(기본 표기 정보 가져오기) ※ basic_info
SELECT name, mid
FROM member
WHERE token='식별토큰';

-- 3-2. 토큰을 통한 상세 정보 가져오기(프로필 정보 가져오기) ※ detail_info
SELECT name, mid, tel, email, zipcode, address1, address2, state
FROM member
WHERE token='token';

-- 3-3. 회원 정보 수정 ※ propile_update
UPDATE member
SET name='이름', tel='전화번호', email='이메일', zipcode='우편번호', address1='도로명 주소', address2='상세 주소', udate=sysdate
WHERE token='식별토큰';

-- 3-4. 비밀 번호 조회(1:현재 비밀번호, 0:현재 비밀번호 아님) ※ checkPasswd
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=1 AND mpasswd='비밀번호';

-- 3-5. 비밀 번호 수정 ※ update_passwd
UPDATE member
SET mpasswd = '비밀번호', udate=sysdate
WHERE token='식별토큰';

-- 3-6. 1차 회원 탈퇴 ※ leave_member
UPDATE member
SET grade=100, state = 3
WHERE token='';

-- 3-7. 회원 정보 삭제 ※ member_del
DELETE FROM member
WHERE memberno = 1;

------------------------------------------------------------------------------------------------
-- 4-1. 배정된 토큰 삭제 ※ token_del
UPDATE member
SET token = ''
WHERE token='식별토큰';
-- 4-2. 로그아웃 ※ logout
-- 세션 삭제
-------------------------Member.xml 기본 끝-------------------------


-------------------------Member.xml 관리자 시작-------------------------
-- 1. 모든 회원 목록 ※ member_list
SELECT memberno, name, mid, tel, email, zipcode, address1, address2, grade, state
FROM member;

-- 2. 특정 회원 정보 ※ member_read
SELECT memberno, name, mid, tel, email, zipcode, address1, address2, grade, state
FROM member
WHERE memberno = 1;

-- 3. 특정 회원 정보 수정 ※ member_update
UPDATE member
SET name='이름', mid='아이디', tel='전화번호', email='이메일', 
zipcode='우편번호', address1='도로명 주소', address2='상세 주소', 
grade='권한', state='상태'
WHERE memberno = 1;
-------------------------Member.xml 관리자 끝-------------------------








