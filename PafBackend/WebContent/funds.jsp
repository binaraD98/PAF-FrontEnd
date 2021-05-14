<%@ page import= "com.funds" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Product Management</title>
		<link rel="stylesheet" href="views/bootstrap.min.css">
		<script src="components/jquery-3.2.1.min.js"></script>
		<script src="components/products.js"></script>
	</head>
<body>

<div class="container">
<div class="row">
   <div class="col-8">
		
				<h1 class="m-3">Fund Details</h1>
			
				<form id="formFunds">
				
				<form id="formItem" name="formItem">
 					Proposal Id: 
 					<input id="propID" name="propID" type="text" class="form-control form-control-sm">
 					<br> Amount: 
 					<input id="amount" name="amount" type="text" class="form-control form-control-sm">
 					<br> Description: 
 					<input id="description" name="description" type="text" class="form-control form-control-sm">
 					<br>
 					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
 					

				
				</form>
				
 				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>	
				<br>
				<div id="divFundsGrid"> 
					<%
					funds fundObj = new funds();
					out.print(fundObj.readFunds());
					%>
</div>			
				
			</div>
		</div>
	</div>
	
	<br>
	
			<div class="row">
			<div class="col-12" id="colFunds">
		
		
		</div>
	</div>

</body>
</html>