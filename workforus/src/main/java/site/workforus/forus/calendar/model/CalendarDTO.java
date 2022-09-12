package site.workforus.forus.calendar.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias("calendarDto")
public class CalendarDTO {

	private int calId; // 캘린더 id

	private String empId; // 캘린더 소유자 id

	private String calName; // 캘린더 이름

	private String calAccess; // 캘린더 공유 상태

	public CalendarDTO(int calId, String empId, String calName, String calAccess) {
		super();
		this.calId = calId;
		this.empId = empId;
		this.calName = calName;
		this.calAccess = calAccess;
	}

}
