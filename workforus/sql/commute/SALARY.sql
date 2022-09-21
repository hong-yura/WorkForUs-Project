DROP TABLE tb_salary_logs;
DROP SEQUENCE tb_salary_logs_seq;

-- 월급기록 테이블 생성 쿼리
CREATE TABLE tb_salary_logs (
	  salary_id			NUMBER				CONSTRAINT PK_TB_SALARY_LOGS_SALARY_ID PRIMARY KEY				-- 월급ID
	, salary_log_dt		VARCHAR2(8)																			-- 월급기록날짜
	, salary_log		NUMBER																				-- 지급월급
	, salary_tax		NUMBER																				-- 기본세금
	, income_tax		NUMBER																				-- 소득세 
	, emp_id 			VARCHAR2(30)		CONSTRAINT FK_TB_SALARY_LOGS_EMP_ID REFERENCES tb_employees(emp_id)	-- 사원ID
);

-- 월급기록 시퀀스
CREATE SEQUENCE tb_salary_logs_seq START WITH 1 NOCACHE;

-- 특정 날짜의 월급기록 삭제 쿼리
DELETE FROM TB_SALARY_LOGS WHERE SALARY_LOG_DT  = '202209';

-- 특정 달의 월급 기록을 조회 쿼리
SELECT *
  FROM tb_salary_logs
 WHERE emp_id = 'A2022100'
   AND salary_log_dt = '202209'
 ORDER BY 1;
 
 
-- 월급기록 추가 쿼리
INSERT INTO tb_salary_logs(salary_id, salary_log_dt, salary_log, salary_tax, income_tax, emp_id)
VALUES (tb_salary_logs_seq.NEXTVAL, #{yearMonth}, #{todaySalary}, #{calTax}, #{calIncome}, #{empId})

 
-- 특정달의 월급을 오늘까지의 계산값으로 수정 쿼리
UPDATE tb_salary_logs
   SET salary_log = #{todaySalary}
   	 , salary_tax = #{calTax}
   	 , income_tax = #{calIncome}
WHERE emp_id = #{empId}
  AND salary_log_dt = #{yearMonth} 
 