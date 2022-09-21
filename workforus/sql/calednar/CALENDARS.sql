DROP TABLE tb_calendars;
DROP SEQUENCE CALENDAR_SEQ;

-- 캘린더 테이블 생성 쿼리
CREATE TABLE tb_calendars (
      cal_id    	NUMBER                -- PK
    , emp_id    	VARCHAR2(30)          -- FK, NN
    , cal_name    	VARCHAR2(50)          -- NN
    , cal_access VARCHAR2(1) DEFAULT '0'  -- CK, NN
);

COMMENT ON TABLE    tb_calendars          	IS '캘린더';

COMMENT ON COLUMN   tb_calendars.cal_id   	IS '캘린더 ID';
ALTER TABLE TB_CALENDARS  ADD CONSTRAINT PK_TB_CALENDARS_CAL_ID PRIMARY KEY (cal_id);

COMMENT ON COLUMN   tb_calendars.emp_id   	IS '캘린더 생성자 ID';
ALTER TABLE TB_CALENDARS  MODIFY emp_id  CONSTRAINT NN_TB_CALENDARS_EMP_ID NOT NULL;
ALTER TABLE TB_CALENDARS ADD CONSTRAINT FK_TB_CALENDARS_EMP_ID FOREIGN KEY (emp_id) REFERENCES TB_EMPLOYEES(emp_id) ON DELETE CASCADE;

COMMENT ON COLUMN   tb_calendars.cal_nm   	IS '캘린더 이름';
ALTER TABLE TB_CALENDARS  MODIFY cal_nm  CONSTRAINT NN_TB_CALENDARS_CAL_NM NOT NULL;

COMMENT ON COLUMN   tb_calendars.cal_access IS '캘린더 공개속성';
ALTER TABLE TB_CALENDARS  MODIFY cal_access  CONSTRAINT NN_TB_CALENDARS_CAL_ACCESS NOT NULL;
ALTER TABLE TB_CALENDARS  ADD CONSTRAINT CK_TB_CALENDARS_CAL_ACCESS CHECK(cal_access IN ('0', '1', '2', '3'));

-- 캘린더 시퀀스
CREATE SEQUENCE CALENDARS_SEQ START WITH 10 INCREMENT BY 1;

-- 전체 조회 쿼리
SELECT * FROM TB_CALENDARS;

-- 특정 데이터 조회 쿼리
SELECT * FROM TB_CALENDARS WHERE CAL_ID = #{calId};

SELECT * FROM TB_CALENDARS WHERE EMP_ID = #{empId};

-- 추가 쿼리
INSERT INTO 
		    TB_CALENDARS(
		          CAL_ID
				, EMP_ID
				, CAL_NAME
		        , CAL_ACCESS)
		    VALUES(
		          CALENDARS_SEQ.NEXTVAL
				, #{empId}
				, #{calName}
		        , #{calAccess}
		    );

-- 특정 데이터 수정 쿼리
UPDATE TB_CALENDARS
			SET 
				  CAL_NAME = #{calName}
				, CAL_ACCESS = #{calAccess}
			WHERE CAL_ID = #{calId}
				AND EMP_ID = #{empId};
				
-- 특정 데이터 삭제 쿼리
DELETE FROM TB_CALENDARS
		 WHERE CAL_ID = #{calId};
