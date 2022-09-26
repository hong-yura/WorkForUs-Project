-- 테이블 생성
CREATE TABLE tb_board_posts(
	  post_id			NUMBER 			CONSTRAINT PK_TB_BOARD_POSTS_POST_ID PRIMARY KEY
	, emp_id			VARCHAR2(30)	CONSTRAINT FK_TB_BOARD_POSTS_EMP_ID REFERENCES TB_EMPLOYEES(EMP_ID)
	, board_id 			NUMBER 			CONSTRAINT FK_TB_BOARD_POSTS_BOARD_ID REFERENCES TB_BOARDS(BOARD_ID)
	, post_title		VARCHAR2(300)
	, post_pw			VARCHAR2(50)
	, post_content		VARCHAR2(2000)
	, post_add_dt		DATE			DEFAULT (SYSDATE) CONSTRAINT NN_TB_BOARD_POSTS_POST_ADD_DT NOT NULL
	, post_modify_dt	DATE
	, post_remove_dt	DATE
	, deleted_yn		VARCHAR2(1)		DEFAULT ('N')
	, view_cnt 			NUMBER
	, notice_yn			VARCHAR2(1)
	, temporary_post_yn	VARCHAR2(1)
	, report_yn			VARCHAR2(1)		DEFAULT ('N')
	, like_cnt 			NUMBER
);

-- 시퀀스 생성
CREATE SEQUENCE board_post_seq NOCACHE;

-- 데이터 추가
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 1, '7월 공지입니다.', 				'', '테스트 중입니다.', SYSDATE, '', '', 'N', 0, 'Y', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 1, '8월 공지입니다.', 				'', '테스트 중입니다.', SYSDATE, '', '', 'Y', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 1, '9월 공지입니다.', 				'', '테스트 중입니다.', SYSDATE, '', '', 'N', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 1, '10월 공지입니다.', 				'', '테스트 중입니다.', SYSDATE, '', '', 'Y', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 1, '11월 공지입니다.', 				'', '테스트 중입니다.', SYSDATE, '', '', 'Y', 0, 'N', 'N', 'N', 0);

INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 2, '9월 1주차 식단표', 				'', '밥 먹고 튼튼해지자', SYSDATE, '', '', 'N', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 2, '9월 2주차 식단표', 				'', '벌써 배가 고파요', SYSDATE,  '', '', 'N', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 2, '9월 3주차 식단표', 				'', '테스트 중입니다.', SYSDATE,  '', '', 'N', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 2, '9월 4주차 식단표', 				'', '테스트 중입니다.', SYSDATE,  '', '', 'N', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 2, '10월 1주차 식단표', 				'', '테스트 중입니다.', SYSDATE,  '', '', 'N', 0, 'N', 'N', 'N', 0);

INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022116', 7, '안녕하세요. 박보검입니다.', 			'', '테스트 중입니다.', SYSDATE, '', '', 'N', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022116', 7, '공지1', 						'', '테스트 중입니다.', SYSDATE, '', '', 'N', 0, 'Y', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022115', 7, '안녕하세요. 원빈입니다.', 			'', '테스트 중입니다.', SYSDATE, '', '', 'N', 0, 'N', 'N', 'N', 0);
INSERT INTO TB_BOARD_POSTS VALUES(board_post_seq.NEXTVAL, 'A2022100', 7, '테스트1', 						'', '테스트 중입니다.', SYSDATE, '', '', 'N', 0, 'N', 'N', 'N', 0);
