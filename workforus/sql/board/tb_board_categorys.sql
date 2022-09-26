CREATE TABLE tb_board_categorys(
	  category_id		NUMBER 			CONSTRAINT PK_TB_BOARD_CATEGORYS_CATEGORY_ID PRIMARY KEY
	, category_nm		VARCHAR2(50)	
	, dept_type_yn 		VARCHAR2(1) 	CONSTRAINT CK_TB_BOARD_CATEGORYS_DEPT_TYPE_YN CHECK (DEPT_TYPE_YN IN ('Y', 'N', 'y', 'n'))
);
CREATE SEQUENCE board_category_seq NOCACHE;

-- 데이터 추가
INSERT INTO TB_BOARD_CATEGORYS VALUES(board_category_seq.NEXTVAL, '공지', 'N'); 		-- 전체게시판
INSERT INTO TB_BOARD_CATEGORYS VALUES(board_category_seq.NEXTVAL, '자유게시판', 'Y'); 	-- 부서게시판
INSERT INTO TB_BOARD_CATEGORYS VALUES(board_category_seq.NEXTVAL, '익명게시판', 'N'); 	-- 전체게시판
INSERT INTO TB_BOARD_CATEGORYS VALUES(board_category_seq.NEXTVAL, '동호회', 'N');
INSERT INTO TB_BOARD_CATEGORYS VALUES(board_category_seq.NEXTVAL, '이슈', 'N');
SELECT * FROM TB_BOARD_POSTS WHERE NOTICE_YN IS NULL;