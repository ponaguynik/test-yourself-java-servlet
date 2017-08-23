<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/${param.css}">
    <c:if test="${param.js == 'highlighter'}">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/highlighter/default.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/highlighter/github-gist.css">
        <script src="${pageContext.request.contextPath}/resources/js/highlight.pack.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    </c:if>
    <title>${param.title}</title>
</head>
<body>
<div id="content">
    <header class="flex-container">
        <a href="<c:url value="/home"/>"><img src="${pageContext.request.contextPath}/resources/images/testyourself-logo.png" alt="TestYourself-logo"></a>
    </header>
    <nav class="flex-container">
        <a href="<c:url value="/home"/>">Home</a>
        <a href="<%= response.encodeURL("test.jsp") %>">Test</a>
        <a href="<%= response.encodeURL("results.jsp") %>">My Results</a>
        <a href="<%= response.encodeURL("about.jsp") %>">About</a>
        <c:if test="${sessionScope.user.username == 'admin'}">
            <a href="<%= response.encodeURL("addQuestion.jsp") %>">Add Question</a>
        </c:if>
        <c:choose>
            <c:when test="${cookie != null && cookie.TOKEN != null}">
                <form class="login-menu-item" action="<c:url value="/logout"/> " method="post">
                    <input type="image" src="${pageContext.request.contextPath}/resources/images/logout.png" alt="Logout">
                </form>
            </c:when>
            <c:otherwise>
                <a href="<c:url value="/login"/>" class="login-menu-item"><img src="${pageContext.request.contextPath}/resources/images/login.png" alt="Login"></a>
            </c:otherwise>
        </c:choose>
    </nav>
    <section class="flex-container">
