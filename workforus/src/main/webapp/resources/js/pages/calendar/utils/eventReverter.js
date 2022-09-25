const eventReverter = (event) => {
  let schedule;
  if (event.isAllday === true) {
    schedule = {
      scheId: event.id,
      calId: String(event.calendarId),
      scheName: event.title,
      scheContent: event.body,
      scheAlltime: "Y",
      scheDateStart: dayjs(event.start.d.d).format("YYYY-MM-DD"),
      scheDateEnd: dayjs(event.end.d.d).format("YYYY-MM-DD"),
      scheTimeStart: null,
      scheTimeEnd: null,
    };
  } else {
    schedule = {
      scheId: event.id,
      calId: String(event.calendarId),
      scheName: event.title,
      scheContent: event.body,
      scheAlltime: "N",
      scheDateStart: dayjs(event.start.d.d).format("YYYY-MM-DD"),
      scheDateEnd: dayjs(event.end.d.d).format("YYYY-MM-DD"),
      scheTimeStart:
        new String(event.start.d.d.getHours()).padStart(2, 0) +
        ":" +
        new String(event.start.d.d.getMinutes()).padStart(2, 0),
      scheTimeEnd:
        new String(event.end.d.d.getHours()).padStart(2, 0) +
        ":" +
        new String(event.end.d.d.getMinutes()).padStart(2, 0),
    };
  }
  console.log(schedule);
  console.log(event.start.d.d.getHours() + ":" + event.start.d.d.getMinutes());
  return schedule;
};
