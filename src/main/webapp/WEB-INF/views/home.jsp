<?xml version="1.0" encoding="utf-8"?>
<jsp:root version="2.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page contentType="text/html; charset=utf-8"
		language="java" pageEncoding="UTF-8" session="true" />
	<![CDATA[<!DOCTYPE html>]]>
	<html>
		<head>
			<meta http-equiv="content-type" content="text/html; charset=utf-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<title>Epiguider</title>
			<c:set var="assetsPath" value="${pageContext.request.contextPath}/assets" />
			<link rel="stylesheet" type="text/css" href="${assetsPath}/libraries/bootstrap/css/bootstrap.min.css" />
			<link rel="stylesheet" type="text/css" href="${assetsPath}/css/main.css" />
			<script src="${assetsPath}/libraries/jquery/jquery.js" type="text/javascript"><!--keep me --></script>
			<script src="${assetsPath}/libraries/bootstrap/js/bootstrap.min.js" type="text/javascript"><!--keep me --></script>
			<script src="${assetsPath}/js/less.js" type="text/javascript"><!--keep me --></script>
			<script src="${assetsPath}/js/main.js" type="text/javascript"><!--keep me --></script>
		</head>
		<body>
			<div class="container">
				<div class="page-header">
					<form class="pull-right" role="search">
						<div class="form-group">
							<input type="text" id="showSearch" class="form-control" placeholder="Search" />
						</div>
					</form>
					<h1 id="header" class="text-muted">Epiguider</h1>
					<span id="info-label" class="label label-info"><!-- keep me --></span>
					<a href="#back" id="back">
						<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"><!-- keep me --></span>
						back to tv show list
					</a>
				</div>
				<div class="row" id="list">
					<div class="col-md-10 col-xs-9">
					<ul id="shows">
						<c:forEach var="store" items="${stores}">
							<li data-name="${store.name}">
								<c:forEach var="parserInfo" items="${store.parsers}" varStatus="status">
									<div class="row">
										<c:url var="link" value="/${store.name}">
											<c:param name="parserId" value="${parserInfo.parserId}" />
											<c:param name="seriesId" value="${parserInfo.seriesId}" />
										</c:url>
										<div class="col-md-11 col-xs-9 ${not status.first ? 'alternative-parser' : ''}">
											<c:choose>
												<c:when test="${status.first}">
													<a href="${link}" class="seriesDetails"><c:out value="${store.name}" /></a>
												</c:when>
												<c:otherwise>
													<span class="">using <a href="${link}" class="seriesDetails">${parserInfo.parserId}</a></span>
												</c:otherwise>
											</c:choose>
										</div>
										
										<div class="col-md-1 col-xs-1">
											<a href="#delete" class="deleteButton btn btn-xs"><span class="glyphicon glyphicon-trash" aria-hidden="true"><!-- keep me --></span></a>
										</div>
									</div>
								</c:forEach>
							</li>
						</c:forEach>
						</ul>
					</div>
					<div class="col-md-2 col-xs-1">
						<button class="btn btn-default btn-xs" href="#parserModal" data-toggle="modal" data-target="#addParser">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"><!-- keep me --></span> <span class="hidden-xs">add new show</span>
						</button>
					</div>
				</div>
				
				<div id="details"><!-- keep me --></div>
				
				<div class="modal fade" id="addParser" tabindex="-1" role="dialog" aria-labelledby="addParserLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&amp;times;</span></button>
								<h4 class="modal-title" id="addParserLabel">Add parser</h4>
							</div>
							<div class="modal-body">
								<c:url var="addParser" value="/add" />
								<form:form modelAttribute="store" cssClass="form-horizontal" action="${addParser}">
									<div class="control-group">
										<form:label path="name" cssClass="control-label col-sm-2">Name</form:label>
										<div class="col-sm-10">
											<form:input path="name" cssClass="form-control" />
										</div>
									</div>
									<div class="control-group">
										<label for="parsers[0].parserId" class="control-label col-sm-2">Parser ID</label>
										<div class="col-sm-10">
											<select id="parsers[0].parserId" name="parsers[0].parserId" class="form-control">
											<c:forEach var="parser" items="${parsers}">
												<option value="${parser}">${parser}</option>
											</c:forEach>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label for="parsers[0].seriesId" class="control-label col-sm-2">Parser</label>
										<div class="col-sm-10">
											<input id="parsers[0].seriesId" name="parsers[0].seriesId" class="form-control" />
										</div>
									</div>
								</form:form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary modal-form-submit">Add parser</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</body>
	</html>
</jsp:root>