<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="animesign">
    <img id="default-background" class="background" src="${defaultBackground}" />
    <div id="panel" class="controls">
        <button class="build">Build</button>
        <button class="play">Play</button>
    </div>
    <div class="resources">
        <c:if test="${empty resourceList}">
            <div>no resourceList.</div>
        </c:if>
        <%-- リソースをHTMLに埋め込む必要がある --%>
        <c:if test="${not empty resourceList}">
            <c:forEach var="resource" items="${resourceList}" varStatus="status">
                <<c:out value="${resource.elem}" /> id='<c:out value="${resource.attrId}" />' class='<c:out value="${resource.attrClass}" />' src='<c:out value="${resource.attrSrc}" />'></<c:out value="${resource.elem}" />>
            </c:forEach>
        </c:if>
    </div>
</div>
<!--<div id="vue-app"></div>-->