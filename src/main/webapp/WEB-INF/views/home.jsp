<?xml version="1.0" encoding="utf-8"?>
<jsp:root version="2.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page contentType="text/html; charset=utf-8"
		language="java" pageEncoding="UTF-8" session="true" />
	<![CDATA[<!DOCTYPE html>]]>
	<html>
		<head>
			<meta http-equiv="content-type" content="text/html; charset=utf-8" />
			<title>Epiguider</title>
			<c:url var="assetsPath" value="/assets" />
			<link rel="stylesheet/less" type="text/css" href="${assetsPath}/libraries/bootstrap/less/bootstrap.less" />
			<link rel="stylesheet/css" type="text/css" href="${assetsPath}/css/main.css" />
			<script src="${assetsPath}/libraries/jquery/jquery.js" type="text/javascript"><!--keep me --></script>
			<script src="${assetsPath}/libraries/bootstrap/js/bootstrap-tab.js" type="text/javascript"><!--keep me --></script>
			<script src="${assetsPath}/libraries/bootstrap/js/bootstrap-modal.js" type="text/javascript"><!--keep me --></script>
			<script src="${assetsPath}/js/less.js" type="text/javascript"><!--keep me --></script>
			<script src="${assetsPath}/js/main.js" type="text/javascript"><!--keep me --></script>
		</head>
		<body>
			<div class="navbar">
				<div class="navbar-inner">
					<ul class="nav">
	                    <li><a href="#" id="back"><i class="icon-arrow-left-white"><!-- keep me --></i></a></li>
	                    <li><a href="#addParser" data-toggle="modal"><i class="icon-plus"><!-- keep me --></i></a></li>
                  	</ul>
					<form class="navbar-search">
						<input type="text" class="search-query" placeholder="Search" />
					</form>
					<!-- 
					<a class="brand pull-right" href="#">Epiguider</a>
					 -->
					<span class="label label-info pull-left" id="info-label" style="margin:10px;"><!-- keep me --></span>
				</div>
			</div>
			<div class="container" id="list">
				<ul>
					<c:forEach var="store" items="${stores}">
						<li>
							<c:forEach var="parserInfo" items="${store.parsers}" varStatus="status">
								<c:url var="link" value="/${store.name}">
									<c:param name="parserId" value="${parserInfo.parserId}" />
									<c:param name="seriesId" value="${parserInfo.seriesId}" />
								</c:url>
								<c:choose>
									<c:when test="${status.first}">		
										<a href="${link}" class="seriesDetails"><c:out value="${store.name}" /></a>
									</c:when>
									<c:otherwise>
										<a href="${link}" class="seriesDetails">${parserInfo.parserId}</a>	
									</c:otherwise>
								</c:choose>
								<a href="#delete" class="btn btn-mini">Delete</a>
							</c:forEach>
						</li>
					</c:forEach>
				</ul>
			</div>
			
			<div class="modal hide fade" id="addParser">
  				<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
    				<h3>Add parser</h3>
  				</div>
  				<div class="modal-body">
  					<c:url var="addParser" value="/add" />
  					<form:form modelAttribute="store" cssClass="form-horizontal" action="${addParser}">
  						<div class="control-group">
  							<form:label path="name" cssClass="control-label">Name</form:label>
    						<div class="controls">
      							<form:input path="name" />
    						</div>
  						</div>
  						<div class="control-group">
  							<label for="parsers[0].parserId" class="control-label">Parser</label>
  							<div class="controls">
  								<select id="parsers[0].parserId" name="parsers[0].parserId">
  								<c:forEach var="parser" items="${parsers}">
  									<option value="${parser}">${parser}</option>
  								</c:forEach>
  								</select>
  							</div>
  						</div>
  						<div class="control-group">
  							<label for="parsers[0].seriesId" class="control-label">Parser</label>
  							<div class="controls">
  								<input id="parsers[0].seriesId" name="parsers[0].seriesId" />
  							</div>
  						</div>
  					</form:form>
  				</div>
  				<div class="modal-footer">
    				<a href="#" class="btn" data-dismiss="modal">Close</a>
    				<a href="#" class="btn btn-primary modal-form-submit">Add parser</a>
  				</div>
  			</div>
			
			<div class="container" id="details">
				<!-- keep me -->
			</div>
		</body>
	</html>
</jsp:root>