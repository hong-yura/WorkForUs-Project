-- 역할 테이블 생성 쿼리
DROP TABLE tb_role;
CREATE TABLE tb_role (
	   role_id NUMBER CONSTRAINT PK_TB_ROLE_ROLE_ID PRIMARY KEY    -- 역할ID
	 , role_nm VARCHAR2(30) CONSTRAINT NN_TB_ROLE_ROLE_NM NOT NULL -- 역할명
);


-- 역할그룹 테이블 생성 쿼리
DROP TABLE tb_role_group;
CREATE TABLE tb_role_group (
	   role_group_id  NUMBER 	   CONSTRAINT PK_TB_ROLE_GROUP_ROLE_GROUP_ID PRIMARY KEY -- 역할그룹ID
	 , emp_id 		  VARCHAR2(30) CONSTRAINT FK_TB_ROLE_GROUP_EMP_ID REFERENCES tb_employees(emp_id) -- 사번
	 , role_id		  NUMBER   	   CONSTRAINT FK_TB_ROLE_GROUP_ROLE_ID REFERENCES tb_role(role_id)    -- 역할ID
);


-- 권한 테이블 생성 쿼리
DROP TABLE tb_permissions;
CREATE TABLE tb_permissions (
	   role_id  NUMBER CONSTRAINT FK_TB_PERMISSIONS_ROLE_ID REFERENCES tb_role(role_id) -- 역할ID
	 , perm_nm	VARCHAR2(50) CONSTRAINT	NN_TB_PERMISSIONS_PERM_NM NOT NULL              -- 권한명(기능)
	 , p_read	VARCHAR2(1)	DEFAULT('Y') CONSTRAINT CK_TB_PERMISSIONS_P_READ	CHECK(p_read IN ('Y', 'N'))   -- 조회권한
	 , p_add	VARCHAR2(1)	DEFAULT('N') CONSTRAINT CK_TB_PERMISSIONS_P_ADD		CHECK(p_add IN ('Y', 'N'))    -- 추가권한
	 , p_modify	VARCHAR2(1)	DEFAULT('N') CONSTRAINT CK_TB_PERMISSIONS_P_MODIFY	CHECK(p_modify IN ('Y', 'N')) -- 수정권한
	 , p_remove	VARCHAR2(1)	DEFAULT('N') CONSTRAINT CK_TB_PERMISSIONS_P_REMOVE	CHECK(p_remove IN ('Y', 'N')) -- 삭제권한
);


-- 역할이력 테이블 생성 쿼리
CREATE TABLE tb_role_history (
	   role_history_id NUMBER CONSTRAINT PK_TB_ROLE_HISTORY_ROLE_HISTORY_ID PRIMARY KEY            -- 역할이력ID
	 , emp_id 		   VARCHAR2(30) CONSTRAINT FK_TB_ROLE_HISTORY_EMP_ID REFERENCES tb_employees(emp_id) -- 사번
	 , role_id  	   NUMBER CONSTRAINT FK_TB_ROLE_HISTORY_ROLE_ID REFERENCES tb_role(role_id)    -- 역할ID
	 , role_add_dt	   DATE DEFAULT(SYSDATE) CONSTRAINT	NN_TB_ROLE_HISTORY_ROLE_ADD_DT NOT NULL    -- 역할등록일
	 , role_modify_dt  DATE DEFAULT(SYSDATE) CONSTRAINT	NN_TB_ROLE_HISTORY_ROLE_MODIFY_DT NOT NULL -- 역할최종수정일
	 , role_del_fl     VARCHAR2(1) DEFAULT('N') CONSTRAINT CK_TB_ROLE_HISTORY_ROLE_DEL_FL CHECK(role_del_fl IN ('Y', 'N')) -- 역할삭제여부
);




