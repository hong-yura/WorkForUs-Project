const App = () => {
  const calendarRef = React.useRef();

  const [onModal, setOnModal] = React.useState(false);

  const [onInfoModal, setOnInfoModal] = React.useState(false);

  const [eventInfo, setEventInfo] = React.useState();

  const [myCal, setMyCal] = React.useState([]);

  const [shareCal, setShareCal] = React.useState([]);

  const [mySche, setMySche] = React.useState([]);

  const [shareSche, setShareSche] = React.useState([]);

  const empId = "A2022100";

  let calendarList = [];

  React.useEffect(() => {
    axios
      .get(`http://localhost/calendar/list?empId=${empId}`)
      .then((res) => {
        setMyCal(res.data.data);
      })
      .catch();
  }, []);

  React.useEffect(() => {
    axios
      .get(`http://localhost/calendar/share?empId=${empId}`)
      .then((res) => {
        setShareCal(res.data.data);
      })
      .catch();
  }, []);

  React.useEffect(() => {
    calendarRef.current.getInstance().on("selectDateTime", () => {
      setOnModal(true);
    });
    calendarRef.current.getInstance().on("clickEvent", (event) => {
      setEventInfo(event.event);
      setOnInfoModal(true);
    });
  }, []);

  const [headerMonth, setHeaderMonth] = React.useState(
    formatHeaderMonth(new Date())
  );

  React.useEffect(() => {
    axios
      .get(`http://localhost/schedule?empId=${empId}`)
      .then((res) => {
        const schedules = res.data.data;
        setMySche(schedules);

        return schedules;
      })
      .then((schedules) => {
        schedules.forEach((sche) => {
          const newEvent = eventConverter(sche);
          calendarRef.current.getInstance().createEvents([newEvent]);
        });
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  React.useEffect(() => {
    const myCalendar = myCal.map((item) => {
      return { id: item.calId, name: item.calName };
    });

    const shareCalendar = shareCal.map((item) => {
      return { id: item.calId, name: item.calName };
    });

    calendarList = [...myCalendar, ...shareCalendar];

    calendarRef.current.getInstance().setCalendars([]);
    calendarRef.current.getInstance().setCalendars(calendarList);
  }, [myCal, shareCal]);

  const addSchedule = (sche) => {
    const newEvent = eventConverter(sche);
    calendarRef.current.getInstance().createEvents([newEvent]);
  };

  const addCalendar = (cal) => {
    setMyCal([...myCal, cal]);
  };

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

  const checkCalendarVisibilitiy = (event) => {
    calendarRef.current
      .getInstance()
      .setCalendarVisibility(String(event.target.name), event.target.checked);
  };

  return (
    <div id="calendar-wrapper" className="row match-height container">
      <CalendarMenu
        myCal={myCal}
        shareCal={shareCal}
        addCalendar={(cal) => addCalendar(cal)}
        checkCalendarVisibilitiy={checkCalendarVisibilitiy}
        setOnModal={(bool) => setOnModal(bool)}
      />
      <CalendarMain
        forwardRef={calendarRef}
        onClickNavi={onClickNavi}
        onClickViewOption={onClickViewOption}
        headerMonth={headerMonth}
        myCal={myCal}
        shareCal={shareCal}
      />
      {onModal && (
        <AddScheduleModal
          addSchedule={(sche) => addSchedule(sche)}
          setOnModal={(bool) => setOnModal(bool)}
          myCal={myCal}
        />
      )}
      {onInfoModal && (
        <ScheduleInfoModal
          eventInfo={eventInfo}
          setOnInfoModal={(bool) => setOnInfoModal(bool)}
          myCal={myCal}
        />
      )}
    </div>
  );
};
