const App = () => {
  const calendarRef = React.useRef();

  const [onModal, setOnModal] = React.useState(false);

  const [onInfoModal, setOnInfoModal] = React.useState(false);

  const [calendarInfoModal, setCalendarInfoModal] = React.useState(false);

  const [eventInfo, setEventInfo] = React.useState();

  const [calendarInfo, setCalendarInfo] = React.useState();

  const [myCal, setMyCal] = React.useState([]);

  const [shareCal, setShareCal] = React.useState([]);

  const [mySche, setMySche] = React.useState([]);

  const [shareSche, setShareSche] = React.useState([]);

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
    shareCal.forEach((cal) => {
      axios
        .get(`http://localhost/schedule?calId=${cal.calId}`)
        .then((res) => {
          const schedules = res.data.data;
          setShareSche(schedules);

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
    });
  }, [shareCal]);

  React.useEffect(() => {
    const myCalendar = myCal.map((item) => {
      return { id: item.calId, name: item.calName };
    });

    const shareCalendar = shareCal.map((item) => {
      return { id: item.calId, name: item.calendarDTO.calName };
    });

    calendarList = [...myCalendar, ...shareCalendar];

    calendarRef.current.getInstance().setCalendars([]);
    calendarRef.current.getInstance().setCalendars(calendarList);
  }, [myCal, shareCal, addCalendar]);

  const addSchedule = (sche) => {
    const newEvent = eventConverter(sche);
    calendarRef.current.getInstance().createEvents([newEvent]);
  };

  const addCalendar = () => {
    axios
      .get(`http://localhost/calendar/list?empId=${empId}`)
      .then((res) => {
        setMyCal(res.data.data);
      })
      .catch();
  };

  const addShareCalendar = () => {
    axios
      .get(`http://localhost/calendar/share?empId=${empId}`)
      .then((res) => {
        setShareCal(res.data.data.calendarDTO);
      })
      .catch();
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

  const onUpdateEvent = (event) => {
    calendarRef.current.getInstance().updateEvent(event.id, event.calendarId, {
      title: event.title,
      body: event.body,
      start: event.start,
      end: event.end,
      isAllday: event.isAllday,
    });
  };

  const onDeleteEvent = (event) => {
    calendarRef.current.getInstance().deleteEvent(event.id, event.calendarId);
  };

  return (
    <div id="calendar-wrapper" className="row match-height container">
      <CalendarMenu
        myCal={myCal}
        shareCal={shareCal}
        addCalendar={addCalendar}
        addShareCalendar={addShareCalendar}
        checkCalendarVisibilitiy={checkCalendarVisibilitiy}
        setCalendarInfoModal={(bool) => setCalendarInfoModal(bool)}
        setCalendarInfo={(cal) => setCalendarInfo(cal)}
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
          onUpdateEvent={(event) => onUpdateEvent(event)}
          onDeleteEvent={(event) => onDeleteEvent(event)}
          myCal={myCal}
        />
      )}
      {calendarInfoModal && (
        <CalendarInfoModal
          calendarInfo={calendarInfo}
          setCalendarInfoModal={(bool) => setCalendarInfoModal(bool)}
          addCalendar={(cal) => addCalendar(cal)}
        />
      )}
    </div>
  );
};
