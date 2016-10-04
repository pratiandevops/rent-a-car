<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Customer Details</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${customer.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${customer.firstName}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Email</label>
		<div class="col-sm-10">${customer.lastName}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Address</label>
		<div class="col-sm-10">${customer.phone}</div>
	</div>

    <hr>

    <spring:url value="/customers/edit/${customer.id}" var="updateCustomerUrl" />
    <button class="btn btn-primary" onclick="location.href='${updateCustomerUrl}'">Edit customer</button>

    <spring:url value="/rentals/new" var="urlAddRental" />
    <button class="btn btn-primary" onclick="location.href='${urlAddRental}'">Add new rental</button>

    <br/>
    <br/>
    <br/>
    <h2>Rentals</h2>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Date</th>
                <th>Customer</th>
                <th>Vehicle</th>
                <th>Days rented</th>
                <th>Price</th>
                <th>Total</th>
                <th>Note</th>
            </tr>
        </thead>

        <c:forEach var="rental" items="${customer.rentals}">
            <tr>
                <td>${rental.rentalDate}</td>
                <td>${rental.customer.lastName}</td>
                <td>${rental.vehicle.carBrand} ${rental.vehicle.carModel}</td>
                <td>${rental.days}</td>
                <td>${rental.vehicle.pricePerDay}</td>
                <td>${rental.days * rental.vehicle.pricePerDay}</td>
                <td>${rental.note}</td>

            </tr>
        </c:forEach>
    </table>

</div>