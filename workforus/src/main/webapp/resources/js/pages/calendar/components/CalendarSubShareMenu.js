const CalendarSubShareMenu = (props) => {
  const [isOpen, setIsOpen] = React.useState(false);
  const [onModal, setOnModal] = React.useState(false);

  const toggleSubMenu = () => {
    setIsOpen((isOpen) => !isOpen);
  };

  const onClickCalendarName = (item) => {
    props.setCalendarInfo(item);
    props.setCalendarInfoModal(true);
  };

  const onSetModal = () => {
    setOnModal((prev) => !prev);
  };

  return (
    <li className="sidebar-item has-sub">
      <div className="sidebar-link" onClick={toggleSubMenu}>
        <span>{props.menuName}</span>
      </div>
      <ul className={isOpen ? "submenu" : "submenu hide-menu"}>
        {props.subMenu.map((item) => (
          <li className="submenu-item row" key={item.calShrId}>
            <label
              className="form-check-label col"
              tagname={item.calendarDTO.calId}
              value={item.calendarDTO.calId}
              onClick={() => onClickCalendarName(item)}
            >
              {item.calendarDTO.calName}
            </label>
            <div className="col-4">
              <input
                className="form-check-input"
                type="checkbox"
                id="flexCheckDefault"
                name={item.calId}
                onClick={(event) => {
                  props.checkCalendarVisibilitiy(event);
                }}
                defaultChecked
              />
            </div>
          </li>
        ))}
        <li className="submenu-item row" onClick={onSetModal}>
          <label className="form-check-label col">추가하기</label>
          <div className="col-4 mt-1">
            <i className="bi bi-plus-square cal-add-icon"></i>
          </div>
        </li>
        {onModal && (
          <AddShareCalendarModal
            onClose={onSetModal}
            addShareCalendar={props.addShareCalendar}
          />
        )}
      </ul>
    </li>
  );
};
