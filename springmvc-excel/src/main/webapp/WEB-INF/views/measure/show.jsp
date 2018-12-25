<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<style>
body {
	margin: 10;
	font-size: 62.5%;
	line-height: 1.5;
}

.blue-button {
	background: #25A6E1;
	padding: 3px 20px;
	color: #fff;
	font-size: 12px;
	border-radius: 2px;
	-moz-border-radius: 2px;
	-webkit-border-radius: 4px;
	border: 1px solid #1A87B9
}

table {
	width: 70%;
}

th {
	background: SteelBlue;
	color: white;
}

td, th {
	border: 1px solid gray;
	font-size: 12px;
	text-align: left;
	padding: 5px 10px;
	overflow:hidden; 
	white-space:nowrap; 
	text-overflow:ellipsis;
	max-width: 200px;
}
</style>
</head>
<script type="text/javascript">
	function formReset() {
		window.top.location.href = "${pageContext.request.contextPath}/measure/";
	}
</script>
<body>
	<jsp:include page="/" />
	<form:form method="post" modelAttribute="item" action="${pageContext.request.contextPath}/measure/add">
		<table>
			<tr>
				<th colspan="3">Add or Edit Item</th>
				<form:hidden path="id" />
			</tr>
			<tr>
				<td><form:label path="no">no:</form:label></td>
				<td><form:input path="no" size="30" maxlength="30"></form:input></td>
				<td>String <form:errors path="no" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="as">as:</form:label></td>
				<td><form:input path="as" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="as" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="cr">cr:</form:label></td>
				<td><form:input path="cr" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="cr" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="cd">cd:</form:label></td>
				<td><form:input path="cd" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="cd" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="cu">cu:</form:label></td>
				<td><form:input path="cu" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="cu" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="pb">pb:</form:label></td>
				<td><form:input path="pb" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="pb" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="zn">zn:</form:label></td>
				<td><form:input path="zn" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="zn" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="ni">ni:</form:label></td>
				<td><form:input path="ni" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="ni" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="hg">hg:</form:label></td>
				<td><form:input path="hg" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="hg" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="sn">sn:</form:label></td>
				<td><form:input path="sn" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="sn" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="co">co:</form:label></td>
				<td><form:input path="co" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="co" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="ag">ag:</form:label></td>
				<td><form:input path="ag" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="ag" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="sb">sb:</form:label></td>
				<td><form:input path="sb" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="sb" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="ba">ba:</form:label></td>
				<td><form:input path="ba" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="ba" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="mg">mg:</form:label></td>
				<td><form:input path="mg" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="mg" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="ti">ti:</form:label></td>
				<td><form:input path="ti" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="ti" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="w">w:</form:label></td>
				<td><form:input path="w" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="w" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="al">al:</form:label></td>
				<td><form:input path="al" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="al" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="th">th:</form:label></td>
				<td><form:input path="th" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="th" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="sr">sr:</form:label></td>
				<td><form:input path="sr" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="sr" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="cs">cs:</form:label></td>
				<td><form:input path="cs" size="30" maxlength="30"></form:input></td>
				<td>Double <form:errors path="cs" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center;"><input type="submit" class="blue-button" /> <input type="reset" class="blue-button" onclick="formReset()" /></td>
			</tr>
		</table>
	</form:form>
	<h3>Data List</h3>
	<c:if test="${!empty page.items}">
		<table>
			<tr>
				<td align="left">&nbsp;共 <b><font color="red">${page.total}</font> </b>条记录- 分 <b><font color="red">${page.totalPage }</font> </b>页 - 这是第 <b> <font color="red">${page.pageNo}</font>
				</b>页&nbsp; | &nbsp; <a href="list?pageNo=1">首页</a>&nbsp; <a href="list?pageNo=${page.pageNo-1}">前一页</a>&nbsp; <a href="list?pageNo=${page.pageNo+1 gt page.totalPage?page.totalPage:page.pageNo+1}">后一页</a>&nbsp;
					<a href="list?pageNo=${page.totalPage}">末页</a>&nbsp;
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<th width="5">序号</th>
				<th width="5">Edit</th>
				<th width="5">Delete</th>
				<th width="100">id</th>
				<th width="150">no</th>
				<th width="150">as</th>
				<th width="150">cr</th>
				<th width="150">cd</th>
				<th width="150">cu</th>
				<th width="150">pb</th>
				<th width="150">zn</th>
				<th width="150">ni</th>
				<th width="150">hg</th>
				<th width="150">sn</th>
				<th width="150">co</th>
				<th width="150">ag</th>
				<th width="150">sb</th>
				<th width="150">ba</th>
				<th width="150">mg</th>
				<th width="150">ti</th>
				<th width="150">w</th>
				<th width="150">al</th>
				<th width="150">th</th>
				<th width="150">sr</th>
				<th width="150">cs</th>
			</tr>
			<c:forEach items="${page.items}" var="it" varStatus="vs">
				<tr>
					<td>${(page.pageNo-1)*page.pageSize+vs.count}</td>
					<td><a href="<c:url value='/measure/update/${it.id}' />">Edit</a></td>
					<td><a href="<c:url value='/measure/delete/${it.id}' />">Delete</a></td>
					<td>${it.id}</td>
					<td>${it.no}</td>
					<td>${it.as}</td>
					<td>${it.cr}</td>
					<td>${it.cd}</td>
					<td>${it.cu}</td>
					<td>${it.pb}</td>
					<td>${it.zn}</td>
					<td>${it.ni}</td>
					<td>${it.hg}</td>
					<td>${it.sn}</td>
					<td>${it.co}</td>
					<td>${it.ag}</td>
					<td>${it.sb}</td>
					<td>${it.ba}</td>
					<td>${it.mg}</td>
					<td>${it.ti}</td>
					<td>${it.w}</td>
					<td>${it.al}</td>
					<td>${it.th}</td>
					<td>${it.sr}</td>
					<td>${it.cs}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
