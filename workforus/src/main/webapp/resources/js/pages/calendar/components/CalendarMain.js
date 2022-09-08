const CalendarMain = (props) => {
  let cal;

  const Calendar = tui.ReactCalendar;

  const calendars = [{ id: "cal1", name: "Personal" }];

  const initialEvents = [
    {
      id: "1",
      calendarId: "cal1",
      title: "Lunch",
      category: "time",
      start: "2022-06-28T12:00:00",
      end: "2022-06-28T13:30:00",
    },
    {
      id: "2",
      calendarId: "cal1",
      title: "Coffee Break",
      category: "time",
      start: "2022-06-28T15:00:00",
      end: "2022-06-28T15:30:00",
    },
  ];

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
        month={{
          dayNames: ["일", "월", "화", "수", "목", "금", "토"],
        }}
        useFormPopup={true}
        calendars={calendars}
        events={initialEvents}
        usageStatistics={false}
      />
    </div>
  );
};
