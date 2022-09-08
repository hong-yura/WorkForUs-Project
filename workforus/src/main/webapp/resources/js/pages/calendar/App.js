const App = () => {
  const calendarRef = React.useRef();

  const [headerMonth, setHeaderMonth] = React.useState(
    formatHeaderMonth(new Date())
  );

  const onClickNavi = (event) => {
    return (event) => {
      if (event.target.type === "button") {
        const { target } = event;

        const action = target.dataset
          ? target.dataset.action
          : target.getAttribute("data-action");

        const moveAction = action.replace("move-", "");

        calendarRef.current.getInstance()[moveAction]();

        setHeaderMonth(
          formatHeaderMonth(calendarRef.current.getInstance().getDate())
        );
      }
    };
  };

  const onClickViewOption = (event) => {
    return (event) => {
      console.log(event.target);
      if (event.target.type === "button") {
        const { target } = event;

        const action = target.dataset
          ? target.dataset.action
          : target.getAttribute("data-action");

        const changeAction = action.replace("change-", "");

        calendarRef.current.getInstance().changeView(changeAction);
      }
    };
  };

  return (
    <div id="calendar-wrapper" className="row match-height container">
      <CalendarMenu
        onClickNavi={onClickNavi}
        onClickViewOption={onClickViewOption}
      />
      <CalendarMain
        forwardRef={calendarRef}
        onClickNavi={onClickNavi}
        onClickViewOption={onClickViewOption}
        headerMonth={headerMonth}
      />
    </div>
  );
};
