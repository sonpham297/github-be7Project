<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Chemist Sale - Online Shop Website</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="css/style.css" rel="stylesheet">
<link href="css/popup-style/popup.css" rel="stylesheet">
</head>

<body>
	<!-- Topbar Start -->
	<div class="container-fluid">
		<div class="row bg-secondary py-1 px-xl-5">
			<div class="col-lg-6 d-none d-lg-block">
				<div class="d-inline-flex align-items-center h-100">
					<a class="text-body mr-3" href="about.html">About</a> <a
						class="text-body mr-3" href="contact.html">Contact</a> <a
						class="text-body mr-3" href="">Help</a> <a class="text-body mr-3"
						href="">FAQs</a>
				</div>
			</div>
			<div class="col-lg-6 text-center text-lg-right">
				<div class="d-inline-flex align-items-center">
					<div class="btn-group">
						<button type="button" class="btn btn-sm btn-light dropdown-toggle"
							data-toggle="dropdown">My Account</button>
						<div class="dropdown-menu dropdown-menu-right">
							<a href="login.jsp" class="dropdown-item">Sign in/ Sign up</a>
						</div>
					</div>
				</div>
				<div class="d-inline-flex align-items-center d-block d-lg-none">
					<a href="" class="btn px-0 ml-2"> <i
						class="fas fa-heart text-dark"></i> <span
						class="badge text-dark border border-dark rounded-circle"
						style="padding-bottom: 2px;">0</span>
					</a> <a href="" class="btn px-0 ml-2"> <i
						class="fas fa-shopping-cart text-dark"></i> <span
						class="badge text-dark border border-dark rounded-circle"
						style="padding-bottom: 2px;">0</span>
					</a>
				</div>
			</div>
		</div>
		<div
			class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
			<div class="col-lg-4">
				<a href="index.html" class="text-decoration-none"> <span
					class="h1 text-uppercase text-primary bg-dark px-2">Chemist</span>
					<span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Sale</span>
				</a>
			</div>
			<div class="col-lg-4 col-6 text-left">
				<form id="searchForm" action="shop.html" method="GET">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for products" id="searchInput" name="search"
							required>
						<div class="input-group-append">
							<button type="submit"
								class="input-group-text bg-transparent text-primary">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</form>
				<div id="invalidSearchPopup" class="cart-popup">
					<div class="cart-popup-content">
						<h5>"Invalid search term.</h5>
						<h5>Please use only letters, numbers, and spaces."</h5>
						<div class="cart-popup-buttons">
							<button id="invalidSearchButton" class="btn btn-primary">Ok</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Topbar End -->


	<!-- Navbar Start -->
	<div class="container-fluid bg-dark mb-30">
		<div class="row px-xl-5">
			<div class="col-lg-3 d-none d-lg-block">
				<a
					class="btn d-flex align-items-center justify-content-between bg-primary w-100"
					data-toggle="collapse" href="#navbar-vertical"
					style="height: 65px; padding: 0 30px;">
					<h6 class="text-dark m-0">
						<i class="fa fa-bars mr-2"></i>Categories
					</h6> <i class="fa fa-angle-down text-dark"></i>
				</a>
				<nav
					class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 bg-light"
					id="navbar-vertical"
					style="width: calc(100% - 30px); z-index: 999;">
					<div class="navbar-nav w-100">
						<div class="nav-item dropdown dropright">
							<a href="#" class="nav-link dropdown-toggle"
								data-toggle="dropdown">Fragrances<i
								class="fa fa-angle-right float-right mt-1"></i></a>
							<div
								class="dropdown-menu position-absolute rounded-0 border-0 m-0">
								<a href="shop.html" class="dropdown-item">Men's Fragrances</a> <a
									href="shop.html" class="dropdown-item">Women's Fragrances</a>
							</div>
						</div>
						<a href="shop.html" class="nav-item nav-link">Vitamins</a> <a
							href="shop.html" class="nav-item nav-link">Beauty</a> <a
							href="shop.html" class="nav-item nav-link">Cosmetics</a> <a
							href="shop.html" class="nav-item nav-link">CLEARANCE</a>
					</div>
				</nav>
			</div>
			<div class="col-lg-9">
				<nav
					class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
					<a href="" class="text-decoration-none d-block d-lg-none"> <span
						class="h1 text-uppercase text-dark bg-light px-2">Chemist</span> <span
						class="h1 text-uppercase text-light bg-primary px-2 ml-n1">Sale</span>
					</a>
					<button type="button" class="navbar-toggler" data-toggle="collapse"
						data-target="#navbarCollapse">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-between"
						id="navbarCollapse">
						<div class="navbar-nav mr-auto py-0">
							<a href="index.html" class="nav-item nav-link active">Home</a> <a
								href="shop.html" class="nav-item nav-link">Shop</a>
						</div>
						<div class="navbar-nav ml-auto py-0 d-none d-lg-block">
							<a href="" class="btn px-0"> <i
								class="fas fa-heart text-primary"></i> <span
								class="badge text-secondary border border-secondary rounded-circle"
								style="padding-bottom: 2px;">0</span>
							</a> <a href="cart.html" class="btn px-0 ml-3"> <i
								class="fas fa-shopping-cart text-primary"></i> <span
								id="prodInCart"
								class="badge text-secondary border border-secondary rounded-circle"
								style="padding-bottom: 2px;">0</span>
							</a>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</div>
	<!-- Navbar End -->


	<!-- Breadcrumb Start -->
	<div class="container-fluid">
		<div class="row px-xl-5">
			<div class="col-12">
				<nav class="breadcrumb bg-light mb-30">
					<a class="breadcrumb-item text-dark" href="#">Home</a> <a
						class="breadcrumb-item text-dark" href="#">Shop</a> <span
						class="breadcrumb-item active">Shopping Cart</span>
				</nav>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->


	<!-- Cart Start -->
	<div class="container-fluid">
		<div class="row px-xl-5">
			<div class="col-lg-8 table-responsive mb-5">
				<div id="clearCartPopup" class="cart-popup">
					<div class="cart-popup-content">
						<h5>Cart has been cleared!</h5>
						<button id="backToHomepage" class="btn btn-primary">BACK
							TO HOMEPAGE</button>
					</div>
				</div>
				<div id="emptyCartPopup" class="cart-popup">
					<div class="cart-popup-content">
						<h5>There are no items in your shopping cart</h5>
						<button id="continueShoping" class="btn btn-primary">CONTINUE
							SHOPPING</button>
					</div>
				</div>
				<table
					class="table table-light table-borderless table-hover text-center mb-0">
					<thead class="thead-dark">
						<tr>
							<th></th>
							<th>Products</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Total</th>
							<th><button id="clearCartButton" class="btn btn-primary">Clear</button></th>
						</tr>
					</thead>

					<tbody class="align-middle" id="tableProduct">
						<!-- products information will be here -->
					</tbody>
				</table>
				<div class="mt-3">
					<button id="keepShoping" class="btn btn-primary">CONTINUE
						SHOPPING</button>
				</div>
			</div>





			<div class="col-lg-4">
				<form class="mb-30" id="voucherForm">
					<div class="input-group">
						<input type="text" class="form-control border-0 p-4"
							placeholder="Coupon Code" id="voucherCode">
						<div class="input-group-append">
							<button type="submit" class="btn btn-primary">Apply
								Voucher</button>
						</div>
					</div>
				</form>
				<div id="voucherMessage" class="p-10 mb-3"
					style="color: red; display: none; text-align: center;"></div>
				<h5 class="section-title position-relative text-uppercase mb-3">
					<span class="bg-secondary pr-3">Cart Summary</span>
				</h5>
				<div class="bg-light p-30 mb-5">
					<div class="border-bottom pb-2">
						<div class="d-flex justify-content-between mb-3">
							<h6>Subtotal:</h6>
							<h6 id="subtotal"></h6>
						</div>
						<div class="d-flex justify-content-between">
							<h6 class="font-weight-medium">Shipping:</h6>
							<h6 class="font-weight-medium">Calculated at next step</h6>
						</div>
						<div class="justify-content-between mt-2 " id="appliedVoucher"
							style="display: none;"></div>
					</div>
					<div class="pt-2">
						<div class="justify-content-between mt-2" id="newSubtotal"
							style="display: none;"></div>
						<button
							class="btn btn-block btn-primary font-weight-bold my-3 py-3"
							id="checkout">Proceed To Checkout</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Cart End -->

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>

	<!-- Back to Top -->
	<a href="#" class="btn btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Contact Javascript File -->
	<script src="mail/jqBootstrapValidation.min.js"></script>
	<script src="mail/contact.js"></script>

	<!-- Template Javascript -->
	<script src="js/main.js"></script>
	<script src="js/cart.js"></script>
	<script src="js/products/searchProduct.js"></script>

</body>

</html>