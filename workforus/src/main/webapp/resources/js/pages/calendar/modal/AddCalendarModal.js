const AddCalendarModal = (props) => {
  console.log(props.addCalendar);
  return (
    <AddCalendarFrame
      setOnModal={props.setOnModal}
      addCalendar={props.addCalendar}
    />
  );
};
