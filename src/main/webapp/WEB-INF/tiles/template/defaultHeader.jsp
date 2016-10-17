<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" type="date" pattern="dd/MM/yyyy" var="fmtNow" />

<spring:url value="/" var="urlHome" />
<spring:url value="/rentals?rentalDate=${fmtNow}" var="urlRentals" />
<spring:url value="/customers" var="urlCustomers" />
<spring:url value="/vehicles" var="urlVehicles" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Rent-a-car</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${urlHome}">Home</a></li>
				<li><a href="${urlCustomers}">Customers</a></li>
				<li><a href="${urlRentals}">Rentals</a></li>
				<li><a href="${urlVehicles}">Vehicles</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</div>
</nav>