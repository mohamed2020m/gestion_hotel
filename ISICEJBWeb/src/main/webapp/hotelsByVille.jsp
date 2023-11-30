<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Ville" %>

<!DOCTYPE html>
<html>
<head>
    <title>Hotels by Ville</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap CRUD Data Table for Database with Modal Form</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	color: #566787;
	background: #f5f5f5;
	font-family: 'Varela Round', sans-serif;
	font-size: 13px;
}

.table-wrapper {
	background: #fff;
	padding: 20px 25px;
	margin: 30px 0;
	border-radius: 1px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.247);
}

.table-title {
	padding-bottom: 15px;
	background: linear-gradient(40deg, #2096ff, #05ffa3) !important;
	color: #fff;
	padding: 16px 30px;
	margin: -20px -25px 10px;
	border-radius: 1px 1px 0 0;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.247);
}

.table-title h2 {
	margin: 5px 0 0;
	font-size: 24px;
}

.table-title .btn-group {
	float: right;
}

.table-title .btn {
	color: #fff;
	float: right;
	font-size: 13px;
	border: none;
	min-width: 50px;
	border-radius: 1px;
	border: none;
	outline: none !important;
	margin-left: 10px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.247);
}

.table-title .btn i {
	float: left;
	font-size: 21px;
	margin-right: 5px;
}

.table-title .btn span {
	float: left;
	margin-top: 2px;
}

table.table tr th, table.table tr td {
	border-color: #e9e9e9;
	padding: 12px 15px;
	vertical-align: middle;
}

table.table tr th:first-child {
	width: 60px;
}

table.table tr th:last-child {
	width: 100px;
}

table.table-striped tbody tr:nth-of-type(odd) {
	background-color: #fcfcfc;
}

table.table-striped.table-hover tbody tr:hover {
	background: #f5f5f5;
}

table.table th i {
	font-size: 13px;
	margin: 0 5px;
	cursor: pointer;
}

table.table td:last-child i {
	opacity: 0.9;
	font-size: 22px;
	margin: 0 5px;
}

table.table td a {
	font-weight: bold;
	color: #566787;
	display: inline-block;
	text-decoration: none;
	outline: none !important;
}

table.table td a:hover {
	color: #2196F3;
}

table.table td a.edit {
	color: #FFC107;
}

table.table td a.delete {
	color: #F44336;
}

table.table td i {
	font-size: 19px;
}

table.table .avatar {
	border-radius: 1px;
	vertical-align: middle;
	margin-right: 10px;
}


.navbar {
     padding-top:10px;
     padding-right: 15px;
     text-align: right;
 }
 
 .navbar-btn {
     background: white !important;
     border-radius: 5px;
     margin-right:10px;
     
 }
 
 .navbar-btn:hover{
     background: #eee  !important
 }
 
 
 #content {
     padding: 20px;
 }
        
.pagination {
	float: right;
	margin: 0 0 5px;
}

.pagination li a {
	border: none;
	font-size: 13px;
	min-width: 30px;
	min-height: 30px;
	color: #999;
	margin: 0 2px;
	line-height: 30px;
	border-radius: 1px !important;
	text-align: center;
	padding: 0 6px;
}

.pagination li a:hover {
	color: #666;
}

.pagination li.active a, .pagination li.active a.page-link {
	background: #03A9F4;
}

.pagination li.active a:hover {
	background: #0397d6;
}

.pagination li.disabled i {
	color: #ccc;
}

.pagination li i {
	font-size: 16px;
	padding-top: 6px
}

.hint-text {
	float: left;
	margin-top: 10px;
	font-size: 13px;
}
/* Custom checkbox */
.custom-checkbox {
	position: relative;
}

.custom-checkbox input[type="checkbox"] {
	opacity: 0;
	position: absolute;
	margin: 5px 0 0 3px;
	z-index: 9;
}

.custom-checkbox label:before {
	width: 18px;
	height: 18px;
}

.custom-checkbox label:before {
	content: '';
	margin-right: 10px;
	display: inline-block;
	vertical-align: text-top;
	background: white;
	border: 1px solid #bbb;
	border-radius: 1px;
	box-sizing: border-box;
	z-index: 2;
}

.custom-checkbox input[type="checkbox"]:checked+label:after {
	content: '';
	position: absolute;
	left: 6px;
	top: 3px;
	width: 6px;
	height: 11px;
	border: solid #000;
	border-width: 0 3px 3px 0;
	transform: inherit;
	z-index: 3;
	transform: rotateZ(45deg);
}

.custom-checkbox input[type="checkbox"]:checked+label:before {
	border-color: #03A9F4;
	background: #03A9F4;
}

.custom-checkbox input[type="checkbox"]:checked+label:after {
	border-color: #fff;
}

.custom-checkbox input[type="checkbox"]:disabled+label:before {
	color: #b8b8b8;
	cursor: auto;
	box-shadow: none;
	background: #ddd;
}
/* Modal styles */
.modal .modal-dialog {
	max-width: 400px;
}

.modal .modal-header, .modal .modal-body, .modal .modal-footer {
	padding: 20px 30px;
}

.modal .modal-content {
	border-radius: 1px;
}

.modal .modal-footer {
	background: #ecf0f1;
	border-radius: 0 0 1px 1px;
}

.modal .modal-title {
	display: inline-block;
}

.modal .form-control {
	border-radius: 1px;
	box-shadow: none;
	border-color: #dddddd;
}

.modal textarea.form-control {
	resize: vertical;
}

.modal .btn {
	border-radius: 1px;
	min-width: 100px;
}

.modal form label {
	font-weight: normal;
}

.update-form {
	display: none;
}
.dropdwon_container{
	display: flex;
	align-items:center;
	justify-content:space-between;
	margin-top: 25px;
}
#form_hoteByVille{
	display:flex;
}


#form_hoteByVille button{
	margin-left: 10px;
}
 .icons_text{
 	display: flex !important;
 	align-items:center;
 }
</style>
<script type="text/javascript">
		$(document).ready(function()
		{
		 // Activate tooltip
		 $('[data-toggle="tooltip"]').tooltip();
		 
		 // Select/Deselect checkboxes
		 var checkbox = $('table tbody input[type="checkbox"]');
		 $("#selectAll").click(function()
		 {
		  if(this.checked){
		   checkbox.each(function()
		   {
		    this.checked = true;                        
		   });
		  }
		  else
		  {
		   checkbox.each(function()
		   {
		    this.checked = false;                        
		   });
		  } 
		 });
		 checkbox.click(function()
		 {
		  if(!this.checked)
		  {
		   $("#selectAll").prop("checked", false);
		  }
		 });
		});
	
		function toggleUpdateForm(id) {
	        var updateForm = document.getElementById('update-form-' + id);
	        updateForm.style.display = (updateForm.style.display === 'none') ? 'block' : 'none';
	    }
		function setVilleId(id) {
	        document.getElementById('deleteVilleId').value = id;
	    }
		function setEditVilleData(id, nom) {
	        document.getElementById('editVilleId').value = id;
	        document.getElementById('editVille').value = nom;
	    }
	</script>
	<script>
        function selectVille(id, nom) {
            document.getElementById("selectedVille").value = id;
            document.getElementById('villeDropdown').innerHTML = nom + ' <span class="caret"></span>';
            
        }
    </script>
</head>
<body>
	<nav class="navbar">
        <div class="container-fluid">
            <!-- Add your navbar content here, e.g., user profile, settings, etc. -->
            <ul class="nav navbar-nav navbar-right">
				<li>
                	<a href="/ISICEJBWeb/VilleController" class="navbar-btn icons_text">
                		<span class="material-symbols-outlined">
							add_task
						</span>
						<div style="margin-left: 5px">Gestion des Villes</div>
                	</a>
                </li>
				<li >                	
                	<a href="/ISICEJBWeb/HotelController" class="navbar-btn icons_text" >
		                <span class="material-symbols-outlined">
							add_task
						</span>
						<div style="margin-left: 5px">Gestion des Hotels</div>
					</a>
				</li>
            </ul>
        </div>
    </nav>
    
	<div class="container" id="content">
		<div class="dropdwon_container">
			<h3 for="villeId">Select a Ville:</h3>
	        <form id="form_hoteByVille" action="HotelsByVilleController" method="get">
	            <div class="dropdown">
	                <button class="btn btn-default dropdown-toggle" type="button" id="villeDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
	                    Select a Ville
	                    <span class="caret"></span>
	                </button>
	                <ul class="dropdown-menu" aria-labelledby="villeDropdown">
	                    <c:forEach items="${villes}" var="ville">
	                    	<li><a href="javascript:void(0);" onclick="selectVille(${ville.id}, '${ville.nom}')">${ville.nom}</a></li>
	                        
	                    </c:forEach>
	                </ul>
	            </div>
	            <input type="text" name="villeId" id="selectedVille" hidden />
	            <button  type="submit" class="btn btn-primary">Get Hotels</button>
        	</form>
	    </div>
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>
							Hotels by <b> Villes</b>
						</h2>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nom de l'Hotel</th>
						<th>Adresse</th>
						<th>Telephone</th>
						<th>Ville</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty hotels}">
						<c:forEach items="${hotels}" var="hotel">
							<tr>
								<td>${hotel.id}</td>
								<td>${hotel.nom}</td>
								<td>${hotel.adresse}</td>
								<td>${hotel.telephone}</td>
								<td>${hotel.ville.nom}</td>
							</tr>
						</c:forEach>
					</c:if>
				    <c:if test="${empty hotels}">
				    	<td colspan="5" style="text-align:center">No Hotels</td>
				    </c:if>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>

