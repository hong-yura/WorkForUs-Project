const AddScheduleModal = (props) => {
  console.log(props);
  return (
    <AddScheduleFrame
      setOnModal={props.setOnModal}
      myCal={props.myCal}
      addSchedule={props.addSchedule}
    />
  );
};
