const CalendarSubMenu = (props) => {
  const [isOpen, setIsOpen] = React.useState(false);

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
          <li className="submenu-item" key={item.id}>
            <span>{item.name}</span>
          </li>
        ))}
      </ul>
    </li>
  );
};
