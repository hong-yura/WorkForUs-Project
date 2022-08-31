<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<nav>
<div class="mb-3 d-block d-xl">
    <div id="sidebar2" class="">
      <div class="sidebar2-wrapper">
        <div class="sidebar2-header">
          <a href="#" class="burger-btn d-block d-xl">
            <i class="bi bi-justify fs-3"></i>
          </a>
        </div>
        <div class="sidebar2-menu">
          <ul class="menu">
            <li class="sidebar2-item">
              <a href="index.html"
                ><img
                  src="static/images/logo/logo1.png"
                  alt="Logo"
                  srcset=""
                  style="width: 2%; height: auto; margin-left: -0.2rem"
              /></a>
            </li>

            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-person-square"></i>
              </a>
            </li>

            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-envelope"></i>
              </a>
            </li>

            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-journals"></i>
              </a>
            </li>

            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-calendar-check"></i>
              </a>
            </li>

            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-chat-dots"></i>
              </a>
            </li>
            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-file-text"></i>
              </a>
            </li>

            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-chat-left-text"></i>
              </a>
            </li>

            <li class="sidebar2-item">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-clipboard-data"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div id="sidebar" class="active">
    <div class="sidebar-wrapper active">
      <div class="sidebar-header">
        <div class="d-flex justify-content-between">
          <div class="logo">
            <a href="index.html"
              ><img
                src="static/images/logo/logo-main.png"
                alt="Logo"
                srcset=""
                style="max-width: 70%; height: auto"
            /></a>
          </div>
          <div class="toggler">
            <a href="#" class="sidebar-hide"
              ><i class="bi bi-x bi-middle"></i
            ></a>
          </div>
        </div>
      </div>
      <div class="sidebar-menu">
        <ul class="menu">
          <li class="sidebar-title">Menu</li>

          <li class="sidebar-item active">
            <a href="index.html" class="sidebar-link">
              <i class="bi bi-grid-fill"></i>
              <span>Home</span>
            </a>
          </li>
          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-person-square"></i>
              <span>My Page</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="form-element-input.html">개인정보수정</a>
              </li>
              <li class="submenu-item">
                <a href="form-element-input-group.html">근태관리</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-envelope"></i>
              <span>Mail</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#">SubMenu #1</a>
              </li>
              <li class="submenu-item">
                <a href="#">SubMenu #2</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-journals"></i>
              <span>Address</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#l">SubMenu #1</a>
              </li>
              <li class="submenu-item">
                <a href="#">SubMenu #2</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-calendar-check"></i>
              <span>Calendar</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#">SubMenu #1</a>
              </li>
              <li class="submenu-item">
                <a href="#">SubMenu #2</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-chat-dots"></i>
              <span>Chat</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#">SubMenu #1</a>
              </li>
              <li class="submenu-item">
                <a href="#">SubMenu #2</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-file-text"></i>
              <span>Documents</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#l">SubMenu #1</a>
              </li>
              <li class="submenu-item">
                <a href="#">SubMenu #2</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-chat-left-text"></i>
              <span>Community</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#">SubMenu #1</a>
              </li>
              <li class="submenu-item">
                <a href="#">SubMenu #2</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-clipboard-data"></i>
              <span>Survey</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#">SubMenu #1</a>
              </li>
              <li class="submenu-item">
                <a href="#">SubMenu #2</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <button class="sidebar-toggler btn x">
        <i data-feather="x"></i>
      </button>
    </div>
  </div>
  <!-- sidebar2 -->
  
</nav>