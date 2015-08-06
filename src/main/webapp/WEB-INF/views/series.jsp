<jsp:root version="2.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page contentType="text/html; charset=utf-8"
		language="java" pageEncoding="UTF-8" session="true" />
	<div class="row">
		<div class="col-lg-9">
			<ol id="season-all">
				<c:forEach var="season" items="${series.seasons}">
					<li id="season_${season.number}" class="tab-pane"><h4>Season</h4>
						<ol>
							<c:forEach var="episode" items="${season.episodes}">
								<li><c:out value="${episode.title} ${season.number}/${episode.number}" /></li>
							</c:forEach>
						</ol>
					</li>
				</c:forEach>
			</ol>
		</div>
		<div class="col-lg-3">
			<nav id="nav-seasons">
				<ul class="nav">
					<c:forEach var="season" items="${series.seasons}" varStatus="status">
						<!-- class="${status.first ? 'active' : '' }" -->
						<li><a href="#season_${season.number}">${season.number}. Season</a></li>
					</c:forEach>
					<li><a class="back-to-top" href="#top">Back to top</a></li>
				</ul>
			</nav>
		</div>
	</div>
</jsp:root>