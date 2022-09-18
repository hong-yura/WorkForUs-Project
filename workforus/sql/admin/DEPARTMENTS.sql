DROP TABLE tb_departments;
DROP SEQUENCE DEPARTMENTS_SEQ;

-- 부서 테이블 생성 쿼리
CREATE TABLE tb_departments (
	   dept_no		    NUMBER  	 CONSTRAINT PK_TB_DEPARTMENTS_DEPT_NO PRIMARY KEY      -- 부서 코드
	 , dept_nm 		    VARCHAR2(50) CONSTRAINT NN_TB_DEPARTMENTS_DEPT_NM NOT NULL 	 	   -- 부서명
	 , dept_manager_id  VARCHAR2(30)													   -- 부서책임자(부장)
	 , dept_add_dt	    DATE DEFAULT(SYSDATE) CONSTRAINT NN_TB_DEPARTMENTS_DEPT_ADD_DT NOT NULL -- 부서 생성일
	 , dept_mod_dt	    DATE DEFAULT(SYSDATE) CONSTRAINT NN_TB_DEPARTMENTS_DEPT_MOD_DT NOT NULL -- 부서 최종 수정일
);

-- 부서 시퀀스
CREATE SEQUENCE DEPARTMENTS_SEQ START WITH 270 INCREMENT BY 10;

-- TB_DEPARTMENTS 테이블의 전체 데이터 조회 쿼리
SELECT * FROM TB_DEPARTMENTS

-- 특정 부서의 상세 정보 조회 쿼리
SELECT DEPT_NO
	 , DEPT_NM
	 , DEPT_MANAGER_ID
	 , DEPT_ADD_DT
	 , DEPT_MOD_DT
  FROM TB_DEPARTMENTS
 WHERE DEPT_NO = #{no};

-- 부서 추가 쿼리


-- 특정 부서 수정 쿼리


-- 특정 부서 삭제 쿼리