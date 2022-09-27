DROP TABLE tb_receive_mail;

-- 메일수신 테이블 생성 쿼리
CREATE TABLE tb_receive_mail (
	  mail_id			VARCHAR2(8)			
	, emp_id			VARCHAR2(30)		
	, mail_folder		NUMBER				DEFAULT 1
	, mail_send_email		VARCHAR2(50)		CONSTRAINT NN_TB_RECEIVE_MAIL_MAIL_SEND_ID  NOT NULL
	, mail_read_time	TIMESTAMP			CONSTRAINT NN_TB_RECEIVE_MAIL_READ_TIME 	NOT NULL
	, mail_read_fl		CHAR(1)				DEFAULT 'N'
	, spam_id 			VARCHAR2(8)			DEFAULT '1970-01-01'
	, CONSTRAINT PK_TB_RECEIVE_MAIL_MAIL_ID PRIMARY KEY(mail_id)
	, CONSTRAINT FK_TB_RECEIVE_MAIL_SPAM_ID FOREIGN KEY(spam_id) REFERENCES tb_spam_mail_keywords(spam_id)
	, CONSTRAINT FK_TB_RECEIVE_MAIL_MAIL_ID FOREIGN KEY(mail_id) REFERENCES tb_send_mail(mail_id)
);

-- 특정 사원의 메일수신 최신순 조회 쿼리
SELECT *
  FROM tb_receive_mail
 WHERE emp_id = #{empId}
 ORDER BY 1 DESC;

-- 메일수신 추가 쿼리
INSERT INTO tb_receive_mail (mail_id, emp_id, mail_send_email)
VALUES (#{mailId}, (SELECT emp_id from tb_employees Where emp_email = #{receiveEmail})
	 , #{empId});
	 
-- 메일수신 수정 쿼리
UPDATE TB_RECEIVE_MAIL 
   SET MAIL_SEND_EMAIL = 'rbdus96@workforus.site'
 WHERE mail_id = 'S1';