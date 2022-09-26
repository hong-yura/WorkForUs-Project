-- 테이블 생성
CREATE TABLE tb_boards(
	  board_id 			NUMBER 			CONSTRAINT PK_TB_BOARDS_BOARD_ID PRIMARY KEY
	, category_id		NUMBER 			CONSTRAINT FK_TB_BOARDS_CATEGORY_ID REFERENCES TB_BOARD_CATEGORYS(CATEGORY_ID)
	, board_nm			VARCHAR2(50)
	, deleted_yn		VARCHAR2(1)		DEFAULT('N')
	, public_yn 		VARCHAR2(1)		DEFAULT('Y') -- 부서테이블은 public인 경우 해당 부서 사람들이 모두 볼 수 있는 걸로 
	, anonymity_yn		VARCHAR2(1)		DEFAULT('N')
	, inv_link			VARCHAR2(100)
	, dept_no 			NUMBER 			CONSTRAINT FK_TB_BOARDS_DEPT_NO REFERENCES TB_DEPARTMENTS(DEPT_NO) -- null이면 전체게시판
	, board_manager		VARCHAR2(30)	CONSTRAINT FK_TB_BOARDS_BOARD_MANAGER REFERENCES TB_EMPLOYEES(EMP_ID)
	);
-- 시퀀스 생성
CREATE SEQUENCE board_seq NOCACHE;

--게시판 데이터 추가						 
INSERT INTO TB_BOARDS VALUES(board_seq.NEXTVAL, '', '일정공지', 		'N', 'Y', 'N', 'http://workforus/board?bid=1',  140,  'A2022100'); -- 공지 카테고리/부서게시판/150(IT부서)
INSERT INTO TB_BOARDS VALUES(board_seq.NEXTVAL, 1,  '주간식단표', 		'N', 'Y', 'N', 'http://workforus/board?bid=2', '',  'A2022100'); -- no카테고리/전체게시판/
INSERT INTO TB_BOARDS VALUES(board_seq.NEXTVAL, 1,  '전체공지', 		'N', 'Y', 'N', 'http://workforus/board?bid=3', '',  'A2022100'); -- NO카테고리/전체게시판
INSERT INTO TB_BOARDS VALUES(board_seq.NEXTVAL, 5, '이달의뉴스', 		'N', 'Y', 'N', 'http://workforus/board?bid=6', '',  'A2022100'); -- 소식 카테고리/전체게시판
INSERT INTO TB_BOARDS VALUES(board_seq.NEXTVAL, 2, 	'토론게시판', 		'N', 'Y', 'N', 'http://workforus/board?bid=7', 140, 'A2022100'); -- 자유 게시판 카테고리/부서게시판
INSERT INTO TB_BOARDS VALUES(board_seq.NEXTVAL, '', '인공지능개발팀', 	'N', 'N', 'N', 'http://workforus/board?bid=8', 140, 'A2022100'); -- NO게시판 카테고리/부서게시판
INSERT INTO TB_BOARDS VALUES(board_seq.NEXTVAL, '', 'IT관리부', 		'N', 'Y', 'N', 'http://workforus/board?bid=9', 140, 'A2022100'); -- NO게시판 카테고리/부서게시판