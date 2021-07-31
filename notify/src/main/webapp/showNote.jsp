<!DOCTYPE html>

<%@page import="java.sql.Date" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

</head>
<body>

	<div class="container">
		<h2>Note</h2>
		<table class="table table-striped">
			<tbody>
				<tr>
					<th>Name</th>
					<td><% String nname= (String)(request.getAttribute("nname")); out.print(nname);%></td>
				</tr>
				<tr>
					<th>StartDate</th>
					<td><% String sdate= (String)(request.getAttribute("sdate")); out.print(sdate);%></td>
				</tr>
				<tr>
					<th>EndDate</th>
					<td><% String edate= (String)(request.getAttribute("edate")); out.print(edate);%></td>
				</tr>
				<tr>
					<th>RemainderDate</th>
					<td><% String rdate= (String)(request.getAttribute("rdate")); out.print(rdate);%></td>
				</tr>
				<tr>
					<th>Status</th>
					<td><% String stat= (String)(request.getAttribute("stat")); out.print(stat);%></td>
				</tr>
				
				<tr>
					<th>Description</th>
					<td height="100px"><% String des= (String)(request.getAttribute("des")); out.print(des);%></td>
				</tr>
				
			</tbody>
		</table>
			<a id="noteViews" href="notename">Back
												
												</a>
	</div>
    <script>
        function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
        }
    </script>
</body>
</html>
