const CalendarMain = (props) => {
  const Calendar = tui.ReactCalendar;

  return (
    <div id="calendar-main" className="col-md-12">
      <CalendarHeader
        onClickNavi={props.onClickNavi}
        onClickViewOption={props.onClickViewOption}
        headerMonth={props.headerMonth}
      />
      <Calendar
        ref={props.forwardRef}
        className="calendar"
        height="85%"
        view="month"
        day={{
          dayNames: ["일", "월", "화", "수", "목", "금", "토"],
        }}
        week={{
          dayNames: ["일", "월", "화", "수", "목", "금", "토"],
          workweek: true,
        }}
        month={{
          dayNames: ["일", "월", "화", "수", "목", "금", "토"],
          workweek: true,
        }}
        usageStatistics={false}
      />
    </div>
  );
};
