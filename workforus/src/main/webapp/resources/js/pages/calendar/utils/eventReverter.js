const eventReverter = (event) => {
  let schedule;
  if (event.isAllday === true) {
    schedule = {
      id: event.scheId,
      calId: String(event.calId),
      title: event.title,
      body: event.body,
      scheAllday: "Y",
      scheDateStart: event.start.d.d,
      scheDateEnd: event.end.d.d,
      scheTimeStart: null,
      scheTimeEnd: null,
    };
  } else {
    schedule = {
      id: event.scheId,
      calendarId: String(event.calId),
      title: event.scheName,
      body: event.scheContent,
      scheAllday: "N",
      scheDateStart: new Date(event.start.d.d).setHours(0, 0, 0),
      scheDateEnd: new Date(event.end.d.d).setHours(0, 0, 0),
      scheTimeStart:
        event.start.d.d.getHours() + "-" + event.start.d.d.getMinutes(),
      scheTimeEnd: event.end.d.d.getHours() + "-" + event.end.d.d.getMinutes(),
    };
  }
  return schedule;
};
