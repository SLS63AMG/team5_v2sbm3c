CREATE TABLE store (
    storeno     NUMBER(10)      NOT NULL,
    name        VARCHAR2(150)   NOT NULL,
    distinction VARCHAR2(50)    NOT NULL,
    reviewcnt   NUMBER(10)     NOT NULL,
    adrress1    VARCHAR2(500)  NOT NULL,
    adrress2    VARCHAR2(500)  NULL,
    busihours   VARCHAR2(500)  NULL,
    tel         VARCHAR2(100)  NULL,
    reservation CHAR(1)        NULL,
    rsite       VARCHAR2(1000) NULL,
    msite       VARCHAR2(1000) NOT NULL,
    CONSTRAINT PK_store_storeno PRIMARY KEY (storeno)
);

CREATE TABLE menu (
    menuno      NUMBER(10)     NOT NULL,
    name        VARCHAR2(500)  NOT NULL,
    explanation VARCHAR2(500)  NULL,
    price       NUMBER(10)     NULL,
    photo       VARCHAR2(500)  NOT NULL,
    storeno     NUMBER(10)     NOT NULL,
    CONSTRAINT PK_menu_menuno PRIMARY KEY (menuno),
    CONSTRAINT FK_menu_storeno FOREIGN KEY (storeno) REFERENCES store(storeno) ON DELETE CASCADE
);

CREATE TABLE review (
    reviewno    NUMBER(10)     NOT NULL,
    content     VARCHAR2(500)  NOT NULL,
    photo       VARCHAR2(500)  NULL,
    storeno     NUMBER(10)     NOT NULL,
    CONSTRAINT PK_review_reviewno PRIMARY KEY (reviewno),
    CONSTRAINT FK_review_storeno FOREIGN KEY (storeno) REFERENCES store(storeno) ON DELETE CASCADE
);

CREATE TABLE store_photo (
    photono     NUMBER(10)     NOT NULL,
    photo       VARCHAR2(500)  NULL,
    storeno     NUMBER(10)     NOT NULL,
    CONSTRAINT PK_store_photo_photono PRIMARY KEY (photono),
    CONSTRAINT FK_store_photo_storeno FOREIGN KEY (storeno) REFERENCES store(storeno) ON DELETE CASCADE
);


-- store 테이블 시퀀스
CREATE SEQUENCE store_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE 
    CACHE 2
    NOORDER;

-- menu 테이블 시퀀스
CREATE SEQUENCE menu_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE
    CACHE 2
    NOORDER;

-- review 테이블 시퀀스
CREATE SEQUENCE review_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE
    CACHE 2
    NOORDER;

-- store_photo 테이블 시퀀스
CREATE SEQUENCE store_photo_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE
    CACHE 2
    NOORDER;
