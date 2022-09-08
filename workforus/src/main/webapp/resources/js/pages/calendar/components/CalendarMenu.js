const CalendarMenu = (props) => {
  const menuItems = [
    { id: 0, name: "내 캘린더" },
    { id: 1, name: "공유 캘린더" },
  ];

  const subMenuItems = [
    { id: 0, type: 0, name: "나의 일정" },
    { id: 1, type: 1, name: "전사일정" },
    { id: 2, type: 1, name: "부서일정" },
  ];

  return (
    <div id="calendar-menu" className="col-md-2 col-4">
      <div className="calendar-menu-head d-flex">
        <div className="calendar-icon">
          <i className="bi  bi-calendar-check" />
        </div>
        <span>캘린더</span>
      </div>
      <div>
        <button id="calendar-add-button" className="btn">
          일정추가
        </button>
      </div>
      <div>
        <ul className="menu">
          {menuItems.map((menu) => (
            <CalendarSubMenu
              key={menu.id}
              menuName={menu.name}
              subMenu={subMenuItems.filter(
                (subMenu) => subMenu.type === menu.id
              )}
            />
          ))}
        </ul>
      </div>
    </div>
  );
};
