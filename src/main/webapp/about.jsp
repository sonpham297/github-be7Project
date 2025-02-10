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
<style>
h1 {
	text-align: center;
	margin-top: 30px;
	font-size: 38px;
	text-transform: uppercase;
	color: #495057;
	font-weight: 900;
	letter-spacing: 1px;
}

span {
	text-align: center;
	font-size: 16px;
	text-transform: uppercase;
	color: #ffbb05;
	font-weight: 300;
	letter-spacing: 0.5px;
}

.second-slide img {
	width: 100%;
	overflow: hidden;
}

.second-slide .left-image {
	padding-right: 0px !important;
}

.second-slide .right-image {
	padding-left: 0px !important;
}

.second-slide .right-about-text {
	text-align: left;
	margin-left: 15px;
	margin-right: 30px;
}

.second-slide .right-about-text h4 {
	font-size: 19px;
	color: #121212;
	text-transform: uppercase;
	letter-spacing: 1px;
	margin-top: 30px;
	margin-bottom: 25px;
}

.second-slide .right-about-text {
	margin-top: 25px;
}

.second-slide .left-about-text {
	text-align: left;
	margin-left: 15px;
	margin-left: 30px;
}

.second-slide .left-about-text h4 {
	font-size: 19px;
	color: #121212;
	text-transform: uppercase;
	letter-spacing: 1px;
	margin-top: 30px;
	margin-bottom: 25px;
}

.second-slide .left-about-text {
	margin-top: 25px;
}
</style>
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
						<h5>Invalid search term!</h5>
						<h5>Please use only letters, numbers, and spaces.</h5>
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
					</div>
				</nav>
			</div>
		</div>
	</div>
	<!-- Navbar End -->

	<div class="heading">
		<h1>About Us</h1>
	</div>
	<div class="cd-half-width second-slide">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="content second-content">
						<div class="row">
							<div class="col-md-7 left-image">
								<img src="img/about1.jpg">
							</div>
							<div class="col-md-5">
								<div class="right-about-text">
									<h4>Who we are?</h4>
									<p>Welcome to Chemist Sale, your trusted online service
										dedicated to helping you save more by purchasing discounted
										items from leading chemists. We bridge the gap between
										unbeatable deals and your convenience, offering a seamless
										platform where you can discover and shop for quality products
										at affordable prices. Whether you’re looking for skincare,
										health essentials, or everyday necessities, we’re here to make
										bargain hunting easy, fast, and stress-free. Let us help you
										shop smarter and save bigger—because great value should always
										be within reach.</p>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-7">
								<div class="left-about-text">
									<h4>What we do?</h4>
									<p>At Chemist Sale, we specialize in finding the best deals
										on high-quality products from trusted chemists and bringing
										them straight to your doorstep—no matter where you are in the
										world. Our team works tirelessly to scout for discounts and
										special offers, ensuring you get exceptional value for your
										money. Once you’ve selected your items, we handle the entire
										process, from purchasing to securely shipping them overseas.
										Whether you’re looking for everyday essentials or exclusive
										bargains, we make shopping and saving simple, convenient, and
										global.</p>
								</div>
							</div>
							<div class="col-md-5 right-image">
								<img src="img/about5.jpg">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>

	<!-- Search script -->		
	<script src="js/products/searchProduct.js"></script>

</body>
</html>