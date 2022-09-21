const AddCalendarFrame = (props) => {
  const [inputs, setInputs] = React.useState({
    empId: "A2022100",
    calName: null,
    calAccess: null,
  });

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
        props.setOnModal(false);
        return res.data.data;
      }).then((cal) =>{
    	props.addCalendar(cal);
      }).catch();
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
              <h4 className="modal-title">캘린더 추가</h4>
              <button
                type="button"
                className="close"
                onClick={() => props.setOnModal(false)}
              >
                <i data-feather="x" className="bi bi-x fs-5"></i>
              </button>
            </div>
            <form action="#">
              <div className="modal-body">
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
                  onClick={onClickSubmit}
                >
                  <i className="bx bx-check d-block d-sm-none"></i>
                  <span className="d-none d-sm-block">등록</span>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </ModalPortal>
  );
};
