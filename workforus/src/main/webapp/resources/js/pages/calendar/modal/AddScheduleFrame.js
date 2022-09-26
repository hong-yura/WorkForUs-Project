const AddScheduleFrame = (props) => {
  const [inputs, setInputs] = React.useState({
    empId: empId,
    calId: null,
    scheName: null,
    scheContent: null,
    scheAlltime: "N",
    scheDateStart: null,
    scheDateEnd: null,
    scheTimeStart: null,
    scheTimeEnd: null,
  });

  const onInputChange = (event) => {
    console.log(event.target.name);
    console.log(event.target.value);
    setInputs({ ...inputs, [event.target.name]: event.target.value });
  };

  const onInputBlur = (event) => {
    setInputs({ ...inputs, [event.target.name]: event.target.value });
    console.log(inputs);
  };

  const onCheckBoxChange = (event) => {
    const value = event.target.checked ? "Y" : "N";
    setInputs({ ...inputs, [event.target.name]: value });
  };

  const onDateChange = (event) => {
    setInputs({
      ...inputs,
      [event.target.name]: new Date(event.target.value + "T00:00"),
    });
  };

  const onCheckBoxBlur = (event) => {
    const value = event.target.checked ? "Y" : "N";
    setInputs({ ...inputs, [event.target.name]: value });
    if (value == "N") {
      setInputs({ ...inputs, [scheTimeStart]: null, [scheTimeEnd]: null });
    }
  };

  const onSubmit = () => {
    axios
      .post("http://localhost/schedule", inputs)
      .then((res) => {
        console.log(res);
        if (res.data.result === "FAIL") {
          alert("일정 생성에 실패하였습니다.");
        } else {
          alert("일정 생성에 성공하였습니다.");
        }
        props.setOnModal(false);
      })
      .catch((e) => console.log(e));
    props.addSchedule(inputs);
    props.setOnModal(false);
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
              <h4 className="modal-title">일정 추가</h4>
              <button
                type="button"
                className="close"
                onClick={() => props.setOnModal(false)}
              >
                <i data-feather="x" className="bi bi-x fs-5"></i>
              </button>
            </div>
            <div className="modal-body overflow-scroll">
              <form action="#">
                <label>캘린더 선택</label>
                <select
                  className="form-select"
                  aria-label="Default select example"
                  name="calId"
                  value={inputs.calId}
                  onChange={onInputChange}
                  onBlur={onInputBlur}
                >
                  <option>캘린더를 선택해주세요</option>
                  {props.myCal.map((cal) => {
                    return (
                      <option key={cal.calId} id={cal.calId} value={cal.calId}>
                        {cal.calName}
                      </option>
                    );
                  })}
                </select>
                <label>일정 제목: </label>
                <div className="form-group">
                  <input
                    type="text"
                    name="scheName"
                    className="form-control"
                    onChange={onInputChange}
                    onBlur={onInputBlur}
                  />
                </div>
                <label>일정 내용: </label>
                <div className="form-group">
                  <input
                    type="text"
                    name="scheContent"
                    className="form-control"
                    onChange={onInputChange}
                    onBlur={onInputBlur}
                  />
                </div>

                <label>일정 시작 날짜: </label>
                <div className="form-group">
                  <input
                    type="date"
                    name="scheDateStart"
                    className="form-control"
                    onChange={onDateChange}
                    onBlur={onDateChange}
                  />
                </div>

                <label>일정 종료 날짜: </label>
                <div className="form-group">
                  <input
                    type="date"
                    name="scheDateEnd"
                    className="form-control"
                    onChange={onDateChange}
                    onBlur={onDateChange}
                  />
                </div>

                <div className="form-check">
                  <input
                    className="form-check-input"
                    name="scheAlltime"
                    type="checkbox"
                    onChange={onCheckBoxChange}
                    onBlur={onCheckBoxBlur}
                  />
                  <label className="form-check-label">종일 여부</label>
                </div>
                {inputs.scheAlltime === "N" && (
                  <React.Fragment>
                    <label>일정 시작 시간: </label>
                    <div className="form-group">
                      <input
                        type="time"
                        name="scheTimeStart"
                        className="form-control"
                        onChange={onInputChange}
                        onBlur={onInputBlur}
                      />
                    </div>

                    <label>일정 종료 시간: </label>
                    <div className="form-group">
                      <input
                        type="time"
                        name="scheTimeEnd"
                        className="form-control"
                        onChange={onInputChange}
                        onBlur={onInputBlur}
                      />
                    </div>
                  </React.Fragment>
                )}
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-light-secondary"
                onClick={() => props.setOnModal(false)}
              >
                <i className="bx bx-x d-block d-sm-none"></i>
                <span className="d-none d-sm-block">닫기</span>
              </button>
              <button
                type="button"
                className="btn btn-primary ml-1"
                onClick={onSubmit}
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
