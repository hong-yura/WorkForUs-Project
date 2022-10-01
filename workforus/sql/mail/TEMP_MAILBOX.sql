DROP TABLE tb_temp_mailbox;
DROP SEQUENCE temp_mailbox_seq;

-- 임시보관함 테이블 생성 쿼리
CREATE TABLE tb_temp_mailbox (
	  temp_mail_id			NUMBER         CONSTRAINT	PK_TB_TEMP_MAILBOX_TEMP_MAIL_ID PRIMARY KEY
	, send_emp_id			VARCHAR2(8)	   CONSTRAINT  FK_TB_TEMP_MAILBOX_EMP_ID REFERENCES tb_employees(emp_id)
	, temp_mail_title		VARCHAR2(100)
	, temp_mail_content		VARCHAR2(4000)
	, receive_email			VARCHAR2(50)
	, save_time				DATE			DEFAULT(SYSDATE)
);

-- 시퀀스 생성 쿼리
CREATE SEQUENCE temp_mailbox_seq START WITH 1 NOCACHE;

-- 임시보관함 추가 쿼리
INSERT INTO tb_temp_mailbox 
VALUES (temp_mailbox_seq.NEXTVAL, #{empId}, #{mailTitle}, #{mailContent}, #{receiveEmail}, SYSDATE  + (interval '9' hour));

-- 임시보관함 조회 쿼리
SELECT * FROM tb_temp_mailbox WHERE send_emp_id = 'A2022103';
