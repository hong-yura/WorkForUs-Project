<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="static/css/bootstrap.css" />
    <link rel="stylesheet" href="static/vendors/iconly/bold.css" />
    <link rel="stylesheet" href="static/vendors/perfect-scrollbar/perfect-scrollbar.css"/>
    <link rel="stylesheet" href="static/vendors/bootstrap-icons/bootstrap-icons.css"/>
    <link rel="stylesheet" href="static/css/app.css" />
    <link rel="stylesheet" href="static/css/pages/calendar.css" />
    <link rel="icon" href="static/images/logo/favicon.ico" />
  </head>
  
  <body>
    <%@ include file="../module/navigation.jsp" %>
    <div id="app">
      <div id="main">
        <div id="calendar-root"></div>
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    
    <script src="static/js/main.js"></script>

    <!-- add react.js library -->
    <script crossorigin src="https://unpkg.com/react@18/umd/react.production.min.js"></script>
	<script crossorigin src="https://unpkg.com/react-dom@18/umd/react-dom.production.min.js"></script>

    <!-- Add toast ui react calendar -->
    <link rel="stylesheet" href="https://uicdn.toast.com/calendar/latest/toastui-calendar.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/@toast-ui/react-calendar@2.1.3/dist/toastui-react-calendar.min.js"></script>
    <!-- Add bebel js library -->
    <script src=" https://unpkg.com/@babel/standalone/babel.min.js"></script>
    <!-- Add toast ui datepicker, timepicker js library and css -->
    <link rel="stylesheet" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css"/>
    <script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.js"></script>
    <link rel="stylesheet" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css" />
    <script src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.js"></script>
    
    <!-- js files -->
    <script type="text/babel" src="static/js/pages/calendar/utils/formatYearMonth.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarHeader.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarMain.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarSubMenu.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarMenu.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/App.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/index.js"></script>
  </body>
</html>
