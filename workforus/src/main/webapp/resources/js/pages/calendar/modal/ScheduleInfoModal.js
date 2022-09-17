const ScheduleInfoModal = (props) => {
  return (
    <ScheduleInfo
      eventInfo={props.eventInfo}
      setOnInfoModal={(bool) => props.setOnInfoModal(bool)}
      myCal={props.myCal}
    />
  );
};
