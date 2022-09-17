const CalendarSubMenu = (props) => {
  const [isOpen, setIsOpen] = React.useState(false);
  const [onModal, setOnModal] = React.useState(false);
  const [isChecked, setIsChecked] = React.useState(true);

  const toggleSubMenu = () => {
    setIsOpen((isOpen) => !isOpen);
  };

  return (
    <li className="sidebar-item has-sub">
      <div className="sidebar-link" onClick={toggleSubMenu}>
        <span>{props.menuName}</span>
      </div>
      <ul className={isOpen ? "submenu" : "submenu hide-menu"}>
        {props.subMenu &&
          props.subMenu.map((item) => (
            <li className="submenu-item" key={item.calId}>
              <input
                class="form-check-input"
                type="checkbox"
                id="flexCheckDefault"
                name={item.calId}
                onClick={(event)=>{props.checkCalendarVisibilitiy(event)}}
                defaultChecked
              />
              <label class="form-check-label" for="flexCheckDefault">
                {item.calName}
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
            <AddCalendarModal
              setOnModal={(bool) => setOnModal(bool)}
              addCalendar={props.addCalendar}
            />
          )}
        </li>
      </ul>
    </li>
  );
};
