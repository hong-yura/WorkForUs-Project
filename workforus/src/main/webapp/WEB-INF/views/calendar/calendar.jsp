<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="static/css/pages/calendar.css" />
    <link
      rel="stylesheet"
      href="https://uicdn.toast.com/calendar/latest/toastui-calendar.min.css"
    />
    <%@ include file="../module/head.jsp" %> <%@ include
    file="../module/navigation.jsp" %>
  </head>

  <body>
    <div id="app">
      <div id="main">
        <div id="calendar-root"></div>
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>

    <script src="static/js/main.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/@mui/x-date-pickers@5.0.1/node/index.min.js"></script>

    <!-- add axios.js library -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <!-- add react.js library -->
    <script crossorigin src="https://unpkg.com/react@18/umd/react.production.min.js"></script>
    <script crossorigin src="https://unpkg.com/react-dom@18/umd/react-dom.production.min.js"></script>

    <!-- Add toast ui react calendar -->
    <script src="https://cdn.jsdelivr.net/npm/@toast-ui/react-calendar@2.1.3/dist/toastui-react-calendar.min.js"></script>
    <!-- Add bebel js library -->
    <script src=" https://unpkg.com/@babel/standalone/babel.min.js"></script>
    <!-- Add toast ui datepicker, timepicker js library and css -->
    <script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.js"></script>
    <script src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.js"></script>
    <!-- js files -->
    <script type="text/babel" src="static/js/pages/calendar/utils/setTime.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/setEndDate.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/formatYearMonth.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/eventConverter.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/eventReverter.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/ModalPortal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddCalendarFrame.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddCalendarModal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddScheduleFrame.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddScheduleModal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/ScheduleInfo.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/ScheduleInfoModal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarHeader.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarMain.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarSubMenu.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarSubShareMenu.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/components/CalendarMenu.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/App.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/index.js"></script>
  </body>
</html>
