<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.rclass.notice.model.vo.Notice"%>
<%@ include file= "/views/common/header.jsp" %>

<%
	Notice n = (Notice) request.getAttribute("notice");
	String originFilePath = "";
	if (n != null) {
		originFilePath = n.getFilePath();
	}
%>

<style>
	
    section#notice-container{ width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {position: relative; border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    span#fname {
    	position: absolute;
    	left: 86px;
    	top: 9px;
    	width: 285px;
    	background-color: #f5f5f5;
    	/* background-color: yellow; */
    }
</style>
	<section id="notice-container">
		<form action="<%=request.getContextPath()%>/notice/updateEnd" method="post" enctype="multipart/form-data">
			<table id="tbl-notice">
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="<%=n.getNoticeTitle() %>"/></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="<%=n.getNoticeWriter() %>" readonly/></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><%if(n.getFilePath()!=null) { %>
							<input type="file" name="up_file" value="<%=n.getFilePath()%>"/>
							<input type="hidden" name="old_file" value="<%=n.getFilePath()%>"/>
							<span id="fname"><%=n.getFilePath() %></span>
						<%} else {%>
						<input type="file" name="up_file"/>
						<%} %>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea cols="50" rows="5" name="content"><%=n.getNoticeContent() %></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="no" value="<%=n.getNoticeNo()%>"/>
						<input type="submit" value="등록하기" onclick="return validate();"/>
					</td>
				</tr>
			</table>
		</form>
	</section>
	
	<script>
		$(function() {
			
			$('[type=file]').change(function() {
				if ($(this).val() == "") {
					$('#fname').show();
				} else {
					$('#fname').hide();
				}
			})
		});
	
		function validate(){
			var content = $("[name=content]").val();
			if (content.trim().length == 0) {
				alert("내용을 입력하세요!");
				return false;
			}
			return true;
		}
	</script>

<%@include file= "/views/common/footer.jsp"%>