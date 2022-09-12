package site.workforus.forus.calendar.model;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias("calendarShareDto")
public class CalendarShareDTO {

	int calShrId; // 캘린더 공유 번호

	String empId; // 캘린더 공유 상대 사번

	int calId; // 캘린더 번호

	@JsonInclude(JsonInclude.Include.NON_NULL)
	CalendarDTO calendarDTO;

	public CalendarShareDTO(int calShrId, String empId, int calId) {
		this.calShrId = calShrId;
		this.empId = empId;
		this.calId = calId;
	}

	public CalendarShareDTO(int calShrId, String empId, int calId, CalendarDTO calendarDTO) {
		this.calShrId = calShrId;
		this.empId = empId;
		this.calId = calId;
		this.calendarDTO = calendarDTO;
	}

}
