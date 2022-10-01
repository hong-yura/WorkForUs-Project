DROP TABLE tb_send_mail;
DROP SEQUENCE send_mail_seq;

-- 메일발신 테이블 생성 쿼리
CREATE TABLE tb_send_mail (
	  mail_id			VARCHAR2(8)		CONSTRAINT PK_TB_SEND_MAIL_MAIL_ID			PRIMARY KEY
	, emp_id			VARCHAR2(30)	CONSTRAINT NN_TB_SEND_MAIL_EMP_ID			NOT NULL
	, receive_email		VARCHAR2(50)	CONSTRAINT NN_TB_SEND_MAIL_RECEIVE_ID		NOT NULL
	, mail_ref			VARCHAR2(30)	
	, mail_title		VARCHAR2(100)	CONSTRAINT NN_TB_SEND_MAIL_MAIL_TITLE		NOT NULL
	, mail_content		VARCHAR2(4000)	
	, mail_send_time	DATE			DEFAULT SYSDATE
	, mail_essential	CHAR(1)			DEFAULT 'N'
	, CONSTRAINT CK_TB_SEND_MAIL_MAIL_ESSENTIAL	CHECK(mail_essential IN ('Y','N')) 
);

-- 메일발신 시퀀스 생성 쿼리
CREATE SEQUENCE send_mail_seq NOCACHE;

-- 메일발신 추가 쿼리 
INSERT INTO tb_send_mail(mail_id, mail_title, mail_content, emp_id, receive_id, mail_send_time)
VALUES (('S' || send_mail_seq.NEXTVAL), '메일제목', '내용', '발신사번', '수신사번', SYSDATE);

-- 메일발신 수정 쿼리
UPDATE TB_SEND_MAIL SET MAIL_TITLE = '변경제목' WHERE MAIL_ID = 'S51';

-- 특정 발신메일 삭제 쿼리
DELETE FROM TB_SEND_MAIL tsm WHERE mail_id = 'S64';
