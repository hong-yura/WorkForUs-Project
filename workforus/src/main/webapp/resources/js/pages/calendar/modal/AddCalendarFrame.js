const AddCalendarFrame = (props) => {
  const [inputs, setInputs] = React.useState({
    empId: empId,
    calName: null,
    calAccess: null,
  });

  const [userList, setUserList] = React.useState([]);

  const [searchOption, setSearchOption] = React.useState("0");

  const [user, setUser] = React.useState();

  const [shareList, setShareList] = React.useState(new Map());

  React.useEffect(() => {
    axios
      .get("https://workforus.site/calendar/share/emp-list")
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

  const onInputChange = (event) => {
    setInputs({ ...inputs, [event.target.name]: event.target.value });
  };

  const onClickSubmit = (event) => {
    event.preventDefault();
    const { empId, calName, calAccess } = inputs;
    axios
      .post("http://localhost/calendar/list", {
        empId,
        calName,
        calAccess,
      })
      .then((res) => {
        if (res.data.result === "FAIL") {
          alert("캘린더 생성에 실패하였습니다.");
        } else {
          alert("캘린더 생성에 성공하였습니다.");
        }
        axios
          .get(`https://workforus.site/calendar/recent?empId=${inputs.empId}`)
          .then((res) => {
            return res.data.data[0].calId;
          })
          .then((res) => {
            Array.from(shareList.keys()).forEach((user) => {
              axios.post("https://workforus.site/calendar/share", {
                empId: user,
                calId: res,
              });
            });
          });
        return res.data.data;
      })
      .then((cal) => {
        props.addCalendar(cal);
        props.onClickClose();
      })
      .catch();
  };

  const onAddUser = (event) => {
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
    setShareList((prev) => new Map(prev).set(empNo, empName));
    if (isExist) alert(`${empName}님이 공유 대상에 포함되셨습니다.`);
    else alert("잘못된 값을 입력하셨습니다.");
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
              <h4 className="modal-title">캘린더 추가</h4>
              <button
                type="button"
                className="close"
                onClick={props.onClickClose}
              >
                <i data-feather="x" className="bi bi-x fs-5"></i>
              </button>
            </div>
            <div className="modal-body">
              <form action="#">
                <label>캘린더 제목: </label>
                <div className="form-group">
                  <input
                    name="calName"
                    type="text"
                    className="form-control"
                    onChange={onInputChange}
                  />
                </div>
                <div className="form-check">
                  <input
                    className="form-check-input"
                    type="radio"
                    name="calAccess"
                    value="0"
                    id="calAccess1"
                    onChange={onInputChange}
                  />
                  <label className="form-check-label" htmlFor="calAccess1">
                    비공개
                  </label>
                </div>
                <div className="form-check">
                  <input
                    className="form-check-input"
                    type="radio"
                    name="calAccess"
                    value="1"
                    id="calAccess2"
                    onChange={onInputChange}
                  />
                  <label className="form-check-label" htmlFor="calAccess2">
                    수락 후 공개
                  </label>
                </div>
                <div className="form-check">
                  <input
                    className="form-check-input"
                    type="radio"
                    name="calAccess"
                    value="2"
                    id="calAccess3"
                    onChange={onInputChange}
                  />
                  <label className="form-check-label" htmlFor="calAccess3">
                    전체 공개
                  </label>
                </div>
                {(inputs.calAccess === "1" || inputs.calAccess === "2") && (
                  <React.Fragment>
                    <div className="row g-3">
                      <label className="col-auto">공유 대상</label>
                      <div className="col-auto">
                        {shareList.size !== 0 && (
                          <React.Fragment>
                            <span>: </span>
                            {Array.from(shareList.values()).map((item) => {
                              return <span>{item} </span>;
                            })}
                          </React.Fragment>
                        )}
                      </div>
                    </div>
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
                          onClick={onAddUser}
                        >
                          추가하기
                        </button>
                      </div>
                    </div>
                  </React.Fragment>
                )}
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-light-secondary"
                onClick={props.onClickClose}
              >
                <i className="bx bx-x d-block d-sm-none"></i>
                <span className="d-none d-sm-block">닫기</span>
              </button>
              <button
                type="button"
                className="btn btn-primary ml-1"
                onClick={onClickSubmit}
              >
                <i className="bx bx-check d-block d-sm-none"></i>
                <span className="d-none d-sm-block">등록</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </ModalPortal>
  );
};
