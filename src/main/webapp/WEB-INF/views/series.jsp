<jsp:root version="2.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page contentType="text/html; charset=utf-8"
		language="java" pageEncoding="UTF-8" session="true" />
	<h2>
		<c:out value="${series.name}" />
	</h2>
	<div class="tabbable tabs-right" id="season-menu">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#season-all" data-toggle="tab">All</a></li>
			<c:forEach var="season" items="${series.seasons}">
				<c:set var="seasonNr" value="${season.number}" />
				<li><a href="#season-${seasonNr}" data-toggle="tab">${seasonNr}</a></li>
			</c:forEach>
        </ul>
	<div class="tab-content">
	<ol id="season-all">
		<c:forEach var="season" items="${series.seasons}">
			<li id="season-${season.number}" class="tab-pane">Staffel
				<ol>
					<c:forEach var="episode" items="${season.episodes}">
						<li><c:out value="${episode.title} ${season.number}/${episode.number}" /></li>
					</c:forEach>
				</ol>
			</li>
		</c:forEach>
	</ol>
	</div>
	</div>
</jsp:root>