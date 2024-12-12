CREATE TABLE condition (
  conditionno   NUMBER(10) NOT NULL,  
  conditionname VARCHAR2(100) NOT NULL,  -- 상태 이름 (예: 새 제품, 사용감 있음 등)
  description    VARCHAR2(300),          -- 상태 상세 설명
  PRIMARY KEY (conditionno)            -- 중복되지 않는 고유 ID
);

-- 테이블 설명
COMMENT ON TABLE condition IS '물품 상태 정보 테이블';

-- 컬럼 설명
COMMENT ON COLUMN condition.conditionno IS '상태 정보 고유 번호';
COMMENT ON COLUMN condition.conditionname IS '물품 상태';
COMMENT ON COLUMN conditiondescription IS '물품 상태 상세 설명';