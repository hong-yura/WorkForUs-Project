<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>workforus - calendar</title>
    <link rel="stylesheet" href="static/css/pages/calendar.css" />
    <link rel="stylesheet" href="https://uicdn.toast.com/calendar/latest/toastui-calendar.min.css"/>
    <%@ include file="../module/head.jsp" %>
    <c:url var="axiosUrl" value="/webjars/axios/0.27.2" />
    <c:url var="babelUrl" value="/webjars/babel-standalone/6.26.0" />
    <c:url var="reactUrl" value="/webjars/react/18.2.0" />
    <c:url var="reactDomUrl" value="/webjars/react-dom/18.2.0" />
    <c:url var="reactQueryUrl" value="/webjars/react-query/3.39.2" />
	<script src="${axiosUrl}/dist/axios.min.js"></script>
	<script src="${babelUrl}/babel.min.js"></script>
    <script src="${reactUrl}/umd/react.development.js"></script>
    <script src="${reactDomUrl}/umd/react-dom.development.js"></script>
    <script src="${reactQueryUrl}/dist/react-query.development.js"></script>
    <script src="${reactQueryUrl}/dist/react-query-core.development.js"></script>
    <script src="${reactQueryUrl}/dist/react-query-devtools.development.js"></script>
	<!-- for deployment -->
    <%--<script src="${reactUrl}/umd/react.production.min.js"></script>--%>
    <%--<script src="${reactDomUrl}/umd/react-dom.production.min.js"></script>--%>
    <%--<script src="${reactQueryUrl}/dist/react-query.production.js"></script>--%>
    <%--<script src="${reactQueryUrl}/dist/react-query-core.production.js"></script>--%>
    <%--<script src="${reactQueryUrl}/dist/react-query-devtools.production.js"></script>--%>
  </head>

  <body>
  	<script type="text/javascript">const empId = "${empId}"</script>
    <%@ include file="../module/navigation.jsp" %>
    <div id="app">
      <div id="main">
        <div id="calendar-root"></div>
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>

    <script src="static/js/main.js"></script>

    <!-- Add ReactQuery -->
    <!-- Add toast ui react calendar -->
    <script src="https://cdn.jsdelivr.net/npm/@toast-ui/react-calendar@2.1.3/dist/toastui-react-calendar.min.js"></script>
    <!-- js files -->
    <script type="text/babel" src="static/js/pages/calendar/utils/setTime.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/setEndDate.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/formatYearMonth.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/eventConverter.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/utils/eventReverter.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/ModalPortal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddCalendarFrame.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddCalendarModal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddShareCalendarFrame.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddShareCalendarModal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddScheduleFrame.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/AddScheduleModal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/CalendarInfoFrame.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/CalendarInfoModal.js"></script>
    <script type="text/babel" src="static/js/pages/calendar/modal/ScheduleInfoFrame.js"></script>
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
