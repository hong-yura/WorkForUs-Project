const AddShareCalendarFrame = (props) => {
  const [userList, setUserList] = React.useState([]);

  const [searchOption, setSearchOption] = React.useState("0");

  const [user, setUser] = React.useState();

  const [calendarList, setCalendarList] = React.useState([]);

  React.useEffect(() => {
    axios
      .get("http://localhost/calendar/share/emp-list")
      .then((res) => {
        setUserList(res.data.data);
      })
      .catch();
  });

  const onSelect = (event) => {
    setSearchOption(event.target.value);
  };

  const onChangeUser = (event) => {
    setUser(event.target.value);
  };

  const onSearchUser = (event) => {
    event.preventDefault();
    let empNo, empName, isExist;
    if (searchOption === "0") {
      empNo = Object.keys(userList).find((key) => userList[key] === user);
      if (empNo) {
        isExist = true;
        empName = user;
      }
    } else {
      isExist = Object.keys(userList).includes(user);
      if (isExist) {
        empNo = user;
        empName = userList[empNo];
      }
    }
    axios.get(`http://localhost/calendar/list?empId=${empNo}`).then((res) => {
      const filteredList = res.data.data.filter(
        (calendar) => calendar.calAccess !== "0"
      );
      setCalendarList(filteredList);
    });
    if (!isExist) alert("존재하지 않는 직원입니다.");
  };

  const onAddCalendar = (event) => {
    event.preventDefault();
    console.log(event.target.value);
    axios
      .post("http://localhost/calendar/share", {
        empId: empId,
        calId: event.target.value,
      })
      .then((res) => {
        if (res.data.result === "SUCCESS")
          alert("캘린더 추가에 성공하셨습니다.");
        else alert("캘린더 추가에 실패하셨습니다.");
      });
  };

  return (
    <ModalPortal>
      <div
        className="modal fade text-left show"
        id="inlineForm"
        tabIndex="-1"
        aria-modal="true"
        role="dialog"
        style={{ display: "block" }}
      >
        <div className="modal-dialog modal-dialog-centered modal-dialog-scrollable">
          <div className="modal-content">
            <div className="modal-header">
              <h4 className="modal-title">공유 캘린더 추가</h4>
              <button
                type="button"
                className="close"
                onClick={props.onClose}
              >
                <i data-feather="x" className="bi bi-x fs-5"></i>
              </button>
            </div>
            <div className="modal-body">
              <form action="#">
                <label>캘린더 생성자 검색: </label>
                <div className="form-group">
                  <div className="row g-3">
                    <div className="col-auto">
                      <select
                        className="form-select"
                        value={searchOption}
                        onChange={onSelect}
                      >
                        <option value="0">이름</option>
                        <option value="1">사번</option>
                      </select>
                    </div>
                    <div className="col-auto">
                      <input
                        type="text"
                        className="form-control"
                        onChange={onChangeUser}
                      />
                    </div>
                    <div className="col-auto">
                      <button
                        className="btn btn-outline-secondary"
                        onClick={onSearchUser}
                      >
                        검색하기
                      </button>
                    </div>
                  </div>
                </div>
                <div>
                  {calendarList.length !== 0 &&
                    calendarList.map((calendar) => {
                      return (
                        <div key={calendar.calId} className="mt-3">
                          <div className="row g-3">
                            <div className="col-5">
                              <div className="mt-2">{calendar.calName}</div>
                            </div>
                            <div className="col-5">
                              <button
                                className="btn btn-light-secondary"
                                value={calendar.calId}
                                onClick={onAddCalendar}
                              >
                                추가하기
                              </button>
                            </div>
                          </div>
                        </div>
                      );
                    })}
                </div>
              </form>
            </div>
            <div className="modal-footer mt-3">
              <button
                type="button"
                className="btn btn-light-secondary"
                onClick={props.onClose}
              >
                <i className="bx bx-x d-block d-sm-none"></i>
                <span className="d-none d-sm-block">닫기</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </ModalPortal>
  );
};
