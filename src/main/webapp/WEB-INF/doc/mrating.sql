CREATE TABLE mrating (
    storeno NUMBER(10) NOT NULL,
    memberno NUMBER(10) NOT NULL,
    rating NUMBER(5, 2) NOT NULL,
    CONSTRAINT FK_mrating_storeno FOREIGN KEY (storeno) REFERENCES store(storeno) ON DELETE CASCADE,
    CONSTRAINT FK_mrating_memberno FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
);

CREATE SEQUENCE mrating_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999
    NOCYCLE 
    CACHE 2
    NOORDER;






