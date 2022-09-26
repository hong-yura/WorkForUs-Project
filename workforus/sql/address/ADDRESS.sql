/*
 * 무결성 제약조건에 따라 테이블 생성 시 순서대로 생성해야 함
 */

-- 개인 주소록 테이블 및 시퀀스 삭제
DROP TABLE tb_indv_address;
DROP SEQUENCE INDV_ADDRESS_SEQ;

-- 개인 주소록 테이블 생성 쿼리
CREATE TABLE tb_indv_address (
			 addr_indv_id	 NUMBER CONSTRAINT PK_TB_INDV_ADDRESS_ADDR_INDV_ID PRIMARY KEY -- 개인주소록ID
		   , addr_nm 	  	 VARCHAR2(30) CONSTRAINT NN_TB_INDV_ADDRESS_ADDR_NM NOT NULL   -- 이름(표시명)
		   , addr_phone		 VARCHAR2(20)  -- 전화번호
		   , addr_dept		 VARCHAR2(30)  -- 부서
		   , addr_email		 VARCHAR2(50)  -- 이메일
		   , addr_detail 	 VARCHAR2(100) -- 주소(회사명, 혹은 거래처 주소 등)
);

-- 개인 주소록 시퀀스
CREATE SEQUENCE INDV_ADDRESS_SEQ NOCACHE;



-- 주소록 테이블 및 시퀀스 삭제
DROP TABLE tb_address;
DROP SEQUENCE ADDRESS_SEQ;

-- 주소록 테이블 생성 쿼리
CREATE TABLE tb_address (
	   addr_id		 NUMBER 	  CONSTRAINT PK_TB_ADDRESS_ADDR_ID 		PRIMARY KEY 							 -- 주소록ID
     , addr_indv_id  NUMBER		  CONSTRAINT FK_TB_ADDRESS_ADDR_INDV_ID REFERENCES tb_indv_address(addr_indv_id) -- 개인주소록ID
	 , emp_id		 VARCHAR2(30) CONSTRAINT FK_TB_ADDRESS_EMP_ID 		REFERENCES tb_employees(emp_id) 		 -- 사번
);

-- 주소록 시퀀스
CREATE SEQUENCE ADDRESS_SEQ NOCACHE;