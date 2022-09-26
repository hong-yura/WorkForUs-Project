-- 테이블 생성
CREATE TABLE tb_post_files(
	  file_id			NUMBER			CONSTRAINT TB_BOARD_ATTACHS_ATTACH_ID PRIMARY KEY
	, post_id 			NUMBER			CONSTRAINT TB_BOARD_ATTACHS_POST_ID REFERENCES TB_BOARD_POSTS(POST_ID)
	, file_nm 			VARCHAR2(100)
	, upload_location	VARCHAR2(300)	-- 실제 파일 위치(local에서)
	, upload_url		VARCHAR2(300)	-- 파일 url 
	, file_type			VARCHAR2(20)	-- 파일 확장자
	, file_summ_yn		VARCHAR2(1)		-- summernote인지 아닌지 -> 나올 위치가 다름
);

-- 시퀀스 생성
CREATE SEQUENCE post_file_seq NOCACHE;