const ScheduleInfoFrame = (props) => {
  const [inputs, setInputs] = React.useState(props.eventInfo);

  React.useEffect(() => {
    if (props.eventInfo !== null) setInputs(eventReverter(props.eventInfo));
  }, [props.eventInfo]);

  const onInputChange = (event) => {
    setInputs({ ...inputs, [event.target.name]: event.target.value });
  };

  const onInputBlur = (event) => {
    setInputs({ ...inputs, [event.target.name]: event.target.value });
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

  const onClickUpdate = () => {
    axios
      .put("https://workforus.site/schedule", inputs)
      .then((res) => {
        if (res.data.result === "FAIL") {
          alert("일정 수정에 실패하였습니다.");
        } else {
          alert("일정 수정에 성공하였습니다.");
        }
      })
      .catch((e) => console.error(e));
    props.onUpdateEvent(eventConverter(inputs));
    props.setOnInfoModal(false);
  };

  const onClickDelete = () => {
    axios
      .delete(`https://workforus.site/schedule/${inputs.scheId}`)
      .then((res) => {
        if (res.data.result === "FAIL") {
          alert("일정 삭제에 실패하였습니다.");
        } else {
          alert("일정 삭제에 성공하였습니다.");
        }
      })
      .catch((e) => console.error(e));
    props.onDeleteEvent(eventConverter(inputs));
    props.setOnInfoModal(false);
  };

  return (
    <ModalPortal onClick={() => props.setOnInfoModal(false)}>
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
              <h4 className="modal-title">일정 정보</h4>
              <button
                type="button"
                className="close"
                onClick={() => props.setOnInfoModal(false)}
              >
                <i data-feather="x" className="bi bi-x fs-5"></i>
              </button>
            </div>
            <div className="modal-body">
              <form action="#">
                <label>캘린더</label>
                <select
                  value={inputs.calId}
                  className="form-select"
                  aria-label="Default select example"
                  name="calId"
                >
                  {props.myCal.map((cal) => {
                    return (
                      <option key={cal.calId} value={cal.calId}>
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
                    value={inputs.scheName}
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
                    value={inputs.scheContent}
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
                    defaultValue={dayjs(inputs.scheDateStart).format(
                      "YYYY-MM-DD"
                    )}
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
                    defaultValue={inputs.scheDateEnd}
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
                    checked={inputs.scheAlltime === "Y" ? true : false}
                  />
                  <label className="form-check-label">종일 여부</label>
                </div>
                {inputs.scheAlltime !== "Y" && (
                  <React.Fragment>
                    <label>일정 시작 시간: </label>
                    <div className="form-group">
                      <input
                        type="time"
                        name="scheTimeStart"
                        className="form-control"
                        defaultValue={inputs.scheTimeStart}
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
                        defaultValue={inputs.scheTimeEnd}
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
                onClick={() => props.setOnInfoModal(false)}
              >
                <span className="d-none d-sm-block">닫기</span>
              </button>
              <button
                type="button"
                className="btn btn-alert ml-1"
                onClick={onClickDelete}
              >
                <span className="d-none d-sm-block">삭제하기</span>
              </button>
              <button
                type="button"
                className="btn btn-primary ml-1"
                onClick={onClickUpdate}
              >
                <span className="d-none d-sm-block">수정하기</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </ModalPortal>
  );
};
