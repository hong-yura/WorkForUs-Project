function eventConverter(sche){

  let startTime;
  let endTime;
  let allday;

  if (sche.scheAlltime === "Y") {
    startTime = new Date(sche.scheDateStart);
    endTime = setEndDate(sche.scheDateEnd);
    allday = true;
  } else {
    startTime = setTime(sche.scheDateStart, sche.scheTimeStart);
    endTime = setTime(sche.scheDateEnd, sche.scheTimeEnd);
    allday = false;
  }
  
  const newEvent = {
    id: sche.scheId,
    calendarId: String(sche.calId),
    title: sche.scheName,
    body: sche.scheContent,
    isAllday: allday,
    start: startTime,
    end: endTime,
    backgroundColor: '#003a70',
    color: '#FFF',
  };

  return newEvent;
};
