DROP TABLE tb_commute;
DROP SEQUENCE 

-- 출퇴근 테이블 생성 쿼리
CREATE TABLE tb_commute (
	  commute_no	NUMBER		    CONSTRAINT PK_TB_COMMUTE_COMMUTE_ID   PRIMARY KEY			-- 근태번호
	, emp_id	    VARCHAR2(30)	CONSTRAINT NN_TB_COMMUTE_EMP_ID	  NOT NULL					-- 사원ID
	, commute_dt	VARCHAR2(8)	   																-- 출근일
	, commute_time	TIMESTAMP																	-- 출근시간
	, getoff_time	TIMESTAMP																	-- 퇴근시간
	, added_time    TIMESTAMP 																	-- 추가근무시간
	, work_time		TIMESTAMP																	-- 실근로시간
	, week_addtime  TIMESTAMP																	-- 이번주 추가근무시간
	, week_worktime TIMESTAMP																	-- 이번주 실근로시간
	, CONSTRAINT FK_TB_COMMUTE_USER_ID FOREIGN KEY(emp_id) REFERENCES tb_employees(emp_id)
);


-- 출퇴근용 시퀀스
CREATE SEQUENCE tb_commute_seq START WITH 100 NOCACHE;



-- tb_commute 테이블 전체 조회 쿼리
SELECT * FROM tb_commute;

-- 특정 사원id와 날짜를 사용한 조회 쿼리
SELECT *
  FROM tb_commute
 WHERE emp_id = 'A2022100'
   AND SUBSTR(commute_dt, 0, 6) = '202209';
   
-- 주간근무시간 수정 쿼리
UPDATE tb_commute 
   SET week_worktime = #{workDate}
 WHERE emp_id = 'A2022100'
   AND commute_dt = '20220917';  
   
-- 근로테이블에 값 추가 
INSERT INTO tb_commute(commute_no, emp_id, commute_dt, commute_time)
VALUES (TB_COMMUTE_SEQ.NEXTVAL, 'A2022100', '20220917', '14:23:11');
   
-- 월 근무 리스트 조회   
SELECT *
  FROM tb_commute
 WHERE emp_id = 'A2022100'
   AND SUBSTR(commute_dt, 0, 6) = '202209';
	   
   
   