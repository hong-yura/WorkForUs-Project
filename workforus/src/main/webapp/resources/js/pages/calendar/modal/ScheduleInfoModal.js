const ScheduleInfoModal = (props) => {
  return (
    <ScheduleInfoFrame
      eventInfo={props.eventInfo}
      setOnInfoModal={(bool) => props.setOnInfoModal(bool)}
      onUpdateEvent={props.onUpdateEvent}
      onDeleteEvent={props.onDeleteEvent}
      myCal={props.myCal}
    />
  );
};
