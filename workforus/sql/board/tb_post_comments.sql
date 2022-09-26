-- 테이블 생성
CREATE TABLE tb_post_comments(
	  comment_id 		NUMBER 			CONSTRAINT PK_TB_BOARD_COMMENTS_COMMENT_ID PRIMARY KEY
	, post_id 			NUMBER 			CONSTRAINT FK_TB_BOARD_COMMENTS_POST_ID REFERENCES TB_BOARD_POSTS(POST_ID)
	, comment_content	VARCHAR2(2000)	
	, emp_id			VARCHAR2(30)	CONSTRAINT FK_TB_BOARD_COMMENTS_EMP_ID REFERENCES TB_EMPLOYEES(EMP_ID)
	, emp_nm			VARCHAR2(10)
	, group_no			NUMBER
	, comment_depth		NUMBER			CONSTRAINT CK_TB_BOARD_COMMENTS_COMMENT_DEPTH CHECK (COMMENT_DEPTH IN (0,1))
	, comment_sort		NUMBER 
	, anonymity_yn		VARCHAR2(1)		DEFAULT('N')
	, comment_remove_yn	VARCHAR2(1)		DEFAULT('N')
	, comment_add_dt	DATE 			DEFAULT(SYSDATE)
);
-- 시퀀스 생성 
CREATE SEQUENCE post_comment_seq NOCACHE;

-- 데이터 추가
INSERT INTO TB_POST_COMMENTS VALUES (post_comment_seq.NEXTVAL, 21, '좋은 정보 감사합니다.', 'A2022110', '원빈', 1, 0, 1, 'N', 'N', SYSDATE);
INSERT INTO TB_POST_COMMENTS VALUES (post_comment_seq.NEXTVAL, 21, '테스트 댓글1입니다.', 'A2022110', '원빈', 2, 0, 1, 'N', 'N', SYSDATE);
INSERT INTO TB_POST_COMMENTS VALUES (post_comment_seq.NEXTVAL, 21, '테스트 댓글2입니다.', 'A2022110', '원빈', 1, 1, 2, 'N', 'N', SYSDATE);