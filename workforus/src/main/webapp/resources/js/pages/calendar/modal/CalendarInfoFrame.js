const CalendarInfoFrame = (props) => {
  const [checks, setChecks] = React.useState([false, false, false]);
  const [inputs, setInputs] = React.useState(props.calendarInfo);

  React.useEffect(() => {
    const newChecks = Array.from(checks);
    newChecks[+props.calendarInfo.calAccess] = true;
    setChecks(newChecks);
  }, [props.calendarInfo]);

  const onInputChange = (event) => {
    setInputs({ ...inputs, [event.target.name]: event.target.value });
  };

  const onClickSubmit = (event) => {
    const { empId, calName, calAccess } = inputs;
    axios
      .post("https://workforus.site/calendar/list", {
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
        props.setCalendarInfoModal(false);
      })
      .then((res) => {
        props.addCalendar(inputs);
      })
      .catch();
  };

  const onClickDelete = (event) => {
    axios
      .delete(`https://workforus.site/calendar/list/${inputs.calId}`)
      .then((res) => {
        if (res.data.result === "FAIL") alert("캘린더 삭제에 실패하였습니다.");
        else alert("캘린더 삭제에 성공하였습니다.");
        return res;
      })
      .then((res) => {
        props.setCalendarInfoModal(false);
        props.addCalendar(inputs);
      })
      .catch();
  };

  return (
    <ModalPortal onClick={() => props.setOnModal(false)}>
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
              <h4 className="modal-title">내 캘린더 정보</h4>
              <button
                type="button"
                className="close"
                onClick={() => props.setCalendarInfoModal(false)}
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
                    value={inputs.calName}
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
                    checked={checks[0]}
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
                    checked={checks[1]}
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
                    checked={checks[2]}
                  />
                  <label className="form-check-label" htmlFor="calAccess3">
                    전체 공개
                  </label>
                </div>
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-alert ml-1"
                onClick={onClickDelete}
              >
                <span className="d-none d-sm-block">삭제하기</span>
              </button>
              <button
                type="button"
                className="btn btn-light-secondary"
                onClick={() => props.setCalendarInfoModal(false)}
              >
                <span className="d-none d-sm-block">닫기</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </ModalPortal>
  );
};
