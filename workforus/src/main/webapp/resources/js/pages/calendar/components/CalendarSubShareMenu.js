const CalendarSubShareMenu = (props) => {
  const [isOpen, setIsOpen] = React.useState(false);
  const [onModal, setOnModal] = React.useState(false);

  const toggleSubMenu = () => {
    setIsOpen((isOpen) => !isOpen);
  };

  return (
    <li className="sidebar-item has-sub">
      <div className="sidebar-link" onClick={toggleSubMenu}>
        <span>{props.menuName}</span>
      </div>
      <ul className={isOpen ? "submenu" : "submenu hide-menu"}>
        {props.subMenu.map((item) => (
          <li className="submenu-item" key={item.calShrId}>
            <input
              className="form-check-input"
              type="checkbox"
              value=""
            />
            <label className="form-check-label">
              {item.calendarDTO.calName}
            </label>
          </li>
        ))}
        <li className="submenu-item">
          <i className="bi bi-plus-square"></i>
          <label
            className="form-check-label"
            onClick={() => setOnModal(true)}
          >
            추가하기
          </label>
          {onModal && (
            <AddCalendarModal setOnModal={(bool) => setOnModal(bool)} />
          )}
        </li>
      </ul>
    </li>
  );
};
