const CalendarHeader = (props) => {
  return (
    <div
      type="button"
      className="d-flex justify-content-between calendar-header"
    >
      <div
        type="button"
        className="d-flex justify-content-between calendar-header-left"
      >
        <button
          type="button"
          className="calendar-header-day btn btn-light"
          onClick={props.onClickViewOption(event)}
          data-action="change-day"
        >
          일간
        </button>
        <button
          type="button"
          className="calendar-header-week btn btn-light"
          onClick={props.onClickViewOption(event)}
          data-action="change-week"
        >
          주간
        </button>
        <button
          type="button"
          className="calendar-header-month btn btn-light"
          onClick={props.onClickViewOption(event)}
          data-action="change-month"
        >
          월간
        </button>
      </div>
      <div className="d-flex justify-content-between calendar-header-mid">
        <button
          type="button"
          className="calendar-arrow-left btn btn-light"
          onClick={props.onClickNavi(event)}
          data-action="move-prev"
        >
          {"<"}
        </button>
        <div className="calendar-header-date">{props.headerMonth}</div>
        <button
          type="button"
          className="calendar-arrow-right btn btn-light"
          onClick={props.onClickNavi(event)}
          data-action="move-next"
        >
          {">"}
        </button>
      </div>
      <button
        type="button"
        className="calendar-header-right btn btn-light"
        onClick={props.onClickNavi(event)}
        data-action="move-today"
      >
        오늘
      </button>
    </div>
  );
};
