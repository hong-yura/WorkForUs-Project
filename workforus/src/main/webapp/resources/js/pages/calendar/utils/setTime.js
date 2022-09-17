const setTime = (date, time) => {
	const newDate = new Date(date);
	const splitTime = time.split(':');
	const hour = splitTime[0];
	const minute = splitTime[1];
	newDate.setHours(hour);
	newDate.setMinutes(minute);
	
	return newDate;
}