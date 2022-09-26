package site.workforus.forus.admin.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.workforus.forus.employee.model.EmpDTO;

@Getter
@Setter
@ToString
@Alias("jobDto")
public class JobDTO {
	private String jobId; // 직급코드
	private String jobName; // 직급명
	private EmpDTO empObj;

	public JobDTO() {
	}
	
	public JobDTO(String jobId, String jobName) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
	}

}
