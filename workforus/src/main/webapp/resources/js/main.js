function slideToggle(t, e, o) {
  0 === t.clientHeight ? j(t, e, o, !0) : j(t, e, o);
}
function slideUp(t, e, o) {
  j(t, e, o);
}
function slideDown(t, e, o) {
  j(t, e, o, !0);
}
function j(t, e, o, i) {
  void 0 === e && (e = 400),
    void 0 === i && (i = !1),
    (t.style.overflow = "hidden"),
    i && (t.style.display = "block");
  var p,
    l = window.getComputedStyle(t),
    n = parseFloat(l.getPropertyValue("height")),
    a = parseFloat(l.getPropertyValue("padding-top")),
    s = parseFloat(l.getPropertyValue("padding-bottom")),
    r = parseFloat(l.getPropertyValue("margin-top")),
    d = parseFloat(l.getPropertyValue("margin-bottom")),
    g = n / e,
    y = a / e,
    m = s / e,
    u = r / e,
    h = d / e;
  window.requestAnimationFrame(function l(x) {
    void 0 === p && (p = x);
    var f = x - p;
    i
      ? ((t.style.height = g * f + "px"),
        (t.style.paddingTop = y * f + "px"),
        (t.style.paddingBottom = m * f + "px"),
        (t.style.marginTop = u * f + "px"),
        (t.style.marginBottom = h * f + "px"))
      : ((t.style.height = n - g * f + "px"),
        (t.style.paddingTop = a - y * f + "px"),
        (t.style.paddingBottom = s - m * f + "px"),
        (t.style.marginTop = r - u * f + "px"),
        (t.style.marginBottom = d - h * f + "px")),
      f >= e
        ? ((t.style.height = ""),
          (t.style.paddingTop = ""),
          (t.style.paddingBottom = ""),
          (t.style.marginTop = ""),
          (t.style.marginBottom = ""),
          (t.style.overflow = ""),
          i || (t.style.display = "none"),
          "function" == typeof o && o())
        : window.requestAnimationFrame(l);
  });
}

let sidebarItems = document.querySelectorAll(".sidebar-item.has-sub");
for (var i = 0; i < sidebarItems.length; i++) {
  let sidebarItem = sidebarItems[i];
  sidebarItems[i]
    .querySelector(".sidebar-link")
    .addEventListener("click", function (e) {
      e.preventDefault();

      let submenu = sidebarItem.querySelector(".submenu");
      if (submenu.classList.contains("active")) submenu.style.display = "block";

      if (submenu.style.display == "none") submenu.classList.add("active");
      else submenu.classList.remove("active");
      slideToggle(submenu, 300);
    });
}

window.addEventListener("DOMContentLoaded", (event) => {
  var w = window.innerWidth;
  if (w < 1200) {
    document.getElementById("sidebar").classList.remove("active");
    document.getElementById("sidebar2").classList.add("active");
  }
});
window.addEventListener("resize", (event) => {
  var w = window.innerWidth;
  if (w < 1200) {
    document.getElementById("sidebar").classList.remove("active");
    document.getElementById("sidebar2").classList.add("active");
    document.getElementById("main").classList.add("wide");
  } else {
    document.getElementById("sidebar").classList.add("active");
    document.getElementById("sidebar2").classList.remove("active");
    document.getElementById("main").classList.remove("wide");
  }
});

document.querySelector(".burger-btn").addEventListener("click", () => {
  document.getElementById("sidebar").classList.toggle("active");
  document.getElementById("sidebar2").classList.toggle("active");
    document.getElementById("main").classList.toggle("wide");
});
document.querySelector(".sidebar-hide").addEventListener("click", () => {
  document.getElementById("sidebar").classList.toggle("active");
  document.getElementById("sidebar2").classList.toggle("active");
  document.getElementById("main").classList.toggle("wide");
});

// Perfect Scrollbar Init
if (typeof PerfectScrollbar == "function") {
  const container = document.querySelector(".sidebar-wrapper");
  const container2 = document.querySelector(".sidebar2-wrapper");
  const ps = new PerfectScrollbar(container, {
    wheelPropagation: false,
  });
  const ps2 = new PerfectScrollbar(container2, {
    wheelPropagation: false,
  });
}

// Scroll into active sidebar
document.querySelector(".sidebar-item").scrollIntoView(false);

document.querySelector(".sidebar2-item").scrollIntoView(false);
