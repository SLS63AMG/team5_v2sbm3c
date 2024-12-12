DROP TABLE condition

CREATE TABLE condition (
  conditionno   NUMBER(10)    NOT NULL,  
  conditionname VARCHAR2(100) NOT NULL,  -- 상태 이름 (예: 새 제품, 사용감 있음 등)
  description   VARCHAR2(300),           -- 상태 상세 설명
  contentsno    NUMBER(10)    NOT NULL,  -- 추가된 컬럼, 외래 키
  PRIMARY KEY (conditionno),
  FOREIGN KEY (contentsno) REFERENCES contents(contentsno)  -- 외래 키 제약 조건
);

-- 테이블 설명
COMMENT ON TABLE condition IS '물품 상태 정보 테이블';

-- 컬럼 설명
COMMENT ON COLUMN condition.conditionno IS '상태 정보 고유 번호';
COMMENT ON COLUMN condition.conditionname IS '물품 상태';
COMMENT ON COLUMN condition.description IS '물품 상태 상세 설명';
COMMENT ON COLUMN condition.contentsno IS '컨텐츠 번호';  -- 추가된 컬럼에 대한 설명
