DROP TABLE tb_jobs;

-- 직급 테이블 생성 쿼리
CREATE TABLE tb_jobs (
	   job_id	VARCHAR2(30) CONSTRAINT PK_TB_JOBS_JOB_ID PRIMARY KEY  -- 직급 코드
	 , job_nm 	VARCHAR2(30) CONSTRAINT NN_TB_JOBS_JOB_NM NOT NULL 	   -- 직급명
);

-- TB_JOBS 테이블의 전체 데이터 조회 쿼리
SELECT * FROM tb_jobs;
