const setEndDate = (date) => {
	const newDate =  new Date(date);
	newDate.setHours(23,59,59);
	return newDate;
}