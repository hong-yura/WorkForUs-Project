package site.workforus.forus.calendar.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("scheduleDto")
public class ScheduleDTO {

	private int scheId; // 스케쥴 ID

	private String empId; // 사번

	private int calId; // 캘린더 ID

	private Date scheRegDt; // 일정 등록 시간

	private String scheName; // 일정 이름

	private String scheContent; // 일정 내용

	private String scheAlltime; // 일정 종일 여부 'Y','N'

	private Date scheTimeStart; // 일정 시작 시간

	private Date scheTimeEnd; // 일정 종료 시간

	private Date scheDateStart; // 일정 시작 시간

	private Date scheDateEnd; // 일정 종료 시간

}
