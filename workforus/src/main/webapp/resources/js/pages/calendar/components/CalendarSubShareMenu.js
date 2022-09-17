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
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label class="form-check-label" for="flexCheckDefault">
              {item.calendarDTO.calName}
            </label>
          </li>
        ))}
        <li className="submenu-item">
          <i class="bi bi-plus-square"></i>
          <label
            class="form-check-label"
            for="flexCheckDefault"
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
