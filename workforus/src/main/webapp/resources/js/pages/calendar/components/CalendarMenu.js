const CalendarMenu = (props) => {
  const [onModal, setOnModal] = React.useState(false);

  return (
    <div id="calendar-menu" className="col-md-2 col-4">
      <div className="calendar-menu-head d-flex">
        <div className="calendar-icon">
          <i className="bi  bi-calendar-check" />
        </div>
        <span>캘린더</span>
      </div>
      <div>
        <button
          id="calendar-add-button"
          className="btn"
          onClick={props.setOnModal}
        >
          일정추가
        </button>
      </div>
      <div>
        <ul className="menu">
          <CalendarSubMenu
            menuName="내 캘린더"
            subMenu={props.myCal}
            addCalendar={props.addCalendar}
            checkCalendarVisibilitiy={props.checkCalendarVisibilitiy}
          />
          <CalendarSubShareMenu
            menuName="공유 캘린더"
            subMenu={props.shareCal}
          />
        </ul>
      </div>
    </div>
  );
};
