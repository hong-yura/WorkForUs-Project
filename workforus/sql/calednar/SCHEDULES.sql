DROP TABLE tb_schedules;
DROP SEQUENCE SCHEDULES_SEQ;

-- 일정 테이블 생성 쿼리
CREATE TABLE tb_schedules (
          sche_id   NUMBER          -- PK
        , emp_id    VARCHAR2(30)    -- FK, NN
        , cal_id    NUMBER          -- FK, NN
        , sche_reg_dt   DATE    DEFAULT sysdate -- DF, NN
        , sche_name     VARCHAR2(50)    -- NN
        , sche_content  VARCHAR2(500)   -- NN
        , sche_alltime  VARCHAR2(1)
        , sche_date_start   DATE
        , sche_date_end     DATE
        , sche_time_start VARCHAR2(20)
        , sche_time_end VARCHAR2(20)
);

COMMENT ON TABLE TB_SCHEDULES IS '일정';

COMMENT ON COLUMN   tb_schedules.sche_id   IS '일정 ID';
ALTER TABLE TB_SCHEDULES ADD CONSTRAINT PK_TB_SCHEDULES_SCHE_ID PRIMARY KEY (sche_id);

COMMENT ON COLUMN   tb_schedules.emp_id     IS '일정 생성자 ID';
ALTER TABLE TB_SCHEDULES ADD CONSTRAINT FK_TB_SCHEDULES_EMP_ID FOREIGN KEY(emp_id) REFERENCES TB_EMPLOYEES(emp_id) ON DELETE CASCADE;
ALTER TABLE TB_SCHEDULES  MODIFY emp_id  CONSTRAINT NN_TB_SCHEDULES_EMP_ID NOT NULL;

COMMENT ON COLUMN   tb_schedules.cal_id     IS '캘린더 ID';
ALTER TABLE TB_SCHEDULES ADD CONSTRAINT FK_TB_SCHEDULES_CAL_ID FOREIGN KEY(cal_id) REFERENCES TB_CALENDARS(CAL_ID) ON DELETE CASCADE;
ALTER TABLE TB_SCHEDULES  MODIFY cal_id  CONSTRAINT NN_TB_SCHEDULES_CAL_ID NOT NULL;

COMMENT ON COLUMN   tb_schedules.sche_reg_dt     IS '일정 생성 시간';
ALTER TABLE TB_SCHEDULES  MODIFY sche_reg_dt  CONSTRAINT NN_TB_SCHEDULES_SCHE_REG_DT NOT NULL;

COMMENT ON COLUMN   tb_schedules.sche_name    IS '일정 이름';
ALTER TABLE TB_SCHEDULES  MODIFY sche_name  CONSTRAINT NN_TB_SCHEDULES_SCHE_NAME NOT NULL;

COMMENT ON COLUMN   tb_schedules.sche_content    IS '일정 내용';
ALTER TABLE TB_SCHEDULES  MODIFY sche_content  CONSTRAINT NN_TB_SCHEDULES_SCHE_CONTENT NOT NULL;

COMMENT ON COLUMN   tb_schedules.sche_alltime    IS '일정 종일 여부';
ALTER TABLE TB_SCHEDULES  MODIFY sche_alltime  CONSTRAINT NN_TB_SCHEDULES_SCHE_ALLTIME NOT NULL;
ALTER TABLE TB_SCHEDULES  ADD CONSTRAINT CK_TB_SCHEDULES_SCHE_ALLTIME CHECK(sche_alltime IN ('Y', 'N'));

COMMENT ON COLUMN   tb_schedules.sche_time_start    IS '일정 시작 시간';
COMMENT ON COLUMN   tb_schedules.sche_time_end    IS '일정 종료 시간';

COMMENT ON COLUMN   tb_schedules.sche_date_start    IS '일정 시작 날짜';
ALTER TABLE TB_SCHEDULES  MODIFY sche_date_start  CONSTRAINT NN_TB_SCHEDULES_SCHE_DATE_START NOT NULL;

COMMENT ON COLUMN   tb_schedules.sche_date_end    IS '일정 종료 날짜';
ALTER TABLE TB_SCHEDULES  MODIFY sche_date_end  CONSTRAINT NN_TB_SCHEDULES_SCHE_DATE_END NOT NULL;

-- 일정 시퀀스 생성 쿼리
CREATE SEQUENCE SCHEDULES_SEQ START WITH 10 INCREMENT BY 1;

-- 전체 조회 쿼리
SELECT * FROM TB_SCHEDULES;

-- 특정 데이터 조회 쿼리
SELECT * FROM TB_SCHEDULES WHERE EMP_ID = #{empId};

SELECT * FROM TB_SCHEDULES WHERE CAL_ID = #{calId};

SELECT * FROM TB_SCHEDULES WHERE SCHE_ID = #{scheId};

-- 추가 쿼리
INSERT INTO TB_SCHEDULES (
          sche_id
        , emp_id
        , cal_id
        , sche_reg_dt
        , sche_name
        , sche_content
        , sche_alltime
        , sche_date_start
        , sche_date_end
        , sche_time_start
        , sche_time_end
) VALUES (
          SCHEDULES_SEQ.NEXTVAL
        , #{empId}
        , #{calId}
        , CURRENT_DATE
        , #{scheName}
        , #{scheContent}
        , #{scheAlltime}
        , #{scheDateStart}
        , #{scheDateEnd}
        , #{scheTimeStart}
        , #{scheTimeEnd}
);

-- 특정 데이터 수정 쿼리
UPDATE TB_SCHEDULES
	SET 
          sche_name = #{scheName}
        , sche_content = #{scheContent}
        , sche_alltime = #{scheAlltime}
        , sche_date_start = #{scheDateStart}
        , sche_date_end = #{scheDateEnd}
	WHERE SCHE_ID = #{scheId};
	
-- 특정 데이터 삭제 쿼리
DELETE FROM TB_SCHEDULES WHERE SCHE_ID = #{scheId};

DELETE FROM TB_SCHEDULES WHERE CAL_ID = #{calId};

