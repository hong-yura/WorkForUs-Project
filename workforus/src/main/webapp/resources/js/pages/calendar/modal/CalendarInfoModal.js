const CalendarInfoModal = (props) => {
  return (
    <CalendarInfoFrame
      	setCalendarInfoModal={props.setCalendarInfoModal}
      	calendarInfo={props.calendarInfo}
        addCalendar={props.addCalendar}
    />
  );
};
