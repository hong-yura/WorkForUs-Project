package site.workforus.forus.admin.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("deptVo")
public class DeptVO {
	private int deptNo;
	private String deptName;
	private String deptMngId;
	private Date deptModDt;
	
}
