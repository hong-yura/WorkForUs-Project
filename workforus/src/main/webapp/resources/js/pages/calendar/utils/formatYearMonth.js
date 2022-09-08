const formatHeaderMonth = (date) => {
  const year = date.getFullYear();

  const month = date.getMonth() + 1;
  const fullMonth = +month < 10 ? "0" + month : month;

  return year + "-" + fullMonth;
};
