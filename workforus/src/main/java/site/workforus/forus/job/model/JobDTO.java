package site.workforus.forus.job.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobDTO {
	private String jobId;   // 직급코드
	private String jobName; // 직급명
	
	public JobDTO() {}
	
	public JobDTO(String jobId, String jobName) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
	}
	
}
