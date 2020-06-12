<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <%-- jQuery --%>
        <script src="<c:url value="/docroot/scripts/jquery-3.2.1.min.js"/>"></script>
        <script src="<c:url value="/docroot/scripts/jquery.loadTemplate.min.js"/>"></script>
        <%-- Anime.js --%>
        <script language="javascript" type="text/javascript" src="<c:url value="/docroot/scripts/anime.min.js"/>"></script>
        <%-- AnimeSign --%>
        <%--<link rel="shortcut icon" href="<c:url value="/docroot/images/icon.ico"/>" />--%>
        <link rel="stylesheet" type="text/css" href="<c:url value="/docroot/styles/animesign.css"/>" />
        <%--<script language="javascript" type="text/javascript" src="<c:url value="/docroot/scripts/animesign.lib.js"/>"></script> webpack ⇒ vue --%>
        <script language="javascript" type="text/javascript" src="<c:url value="/docroot/scripts/animesign.core.js"/>"></script>
        <title>AnimeSign</title>
    </head>
    <body>
        <main>
            <tiles:insertAttribute name="content" />
        </main>
    </body>
</html>