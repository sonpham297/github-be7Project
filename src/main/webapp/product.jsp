<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Chemist Sale - Online Shop</title>
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
					<h5 class="text-dark m-0">
						<i class="fa fa-bars mr-2"></i>Categories
					</h5> <i class="fa fa-angle-down text-dark"></i>
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
								<a href="shop.html?category=menFragrances" class="dropdown-item">Men's
									Fragrances</a> <a href="shop.html?category=womenFragrances"
									class="dropdown-item">Women's Fragrances</a>
							</div>
						</div>
						<a href="shop.html?category=Vitamins" class="nav-item nav-link">Vitamins</a>
						<a href="shop.html?category=Beauty" class="nav-item nav-link">Beauty</a>
						<a href="shop.html?category=Cosmetics" class="nav-item nav-link">Cosmetics</a>
						<a href="shop.html?category=Clearance" class="nav-item nav-link">CLEARANCE</a>
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
				<nav class="breadcrumb bg-light mb-30" id="breadcrumb">
					<a class="breadcrumb-item text-dark" href="index.html">Home</a> <a
						class="breadcrumb-item text-dark" href="shop.html">Shop</a>
				</nav>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->


	<!-- Shop Detail Start -->
	<div class="container-fluid pb-5">
		<div class="row px-xl-5">
			<div class="col-lg-5 mb-30">
				<div id="product-carousel" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner bg-light" id="image-list">
						<!-- Image list will be show here -->
					</div>
					<a class="carousel-control-prev" href="#product-carousel"
						data-slide="prev"> <i class="fa fa-2x fa-angle-left text-dark"></i>
					</a> <a class="carousel-control-next" href="#product-carousel"
						data-slide="next"> <i
						class="fa fa-2x fa-angle-right text-dark"></i>
					</a>
				</div>
			</div>
			<!--Will implement the product details here, name, price, discount price, muted-text retail price, available qty, add to cart
            , and share on social media (optional)-->
			<div class="col-lg-7 h-auto mb-30">
				<div class="h-100 bg-light p-30">
					<h3 id="product_name"></h3>
					<div class="d-flex mb-3">
						<div class="text-primary mr-2" id="product_rating">
							<!-- Rating starts will be here -->
						</div>
						<small class="pt-1" id="rate_count"></small>
					</div>
					<h1 class="font-weight-semi-bold mb-4" id="product_price"></h1>
					<h5 class="font-weight-semi-bold mb-4" id="product_discount"
						style="color: red;"></h5>
					<p class="mb-4">Product ID: will implement later :D</p>
					<div class="d-flex align-items-center mb-4 pt-2">
						<div style="width: 130px;">
							<div class="input-group">
								<span class="input-group-text">Qty:</span> <select
									class="form-select form-select-sm" id="quantitySelect"
									style="-webkit-appearance: menulist-button; border: 1px solid #888; width: auto; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; width: 55px !important; height: 39px !important">
								</select>
							</div>
						</div>

						<button class="btn btn-primary px-3" id="addToCart_button">
							<i class="fa fa-shopping-cart mr-1"></i> Add To Cart
						</button>
					</div>
					<div id="cartPopup" class="cart-popup">
						<div class="cart-popup-content">
							<h5>Product added to cart!</h5>
							<div class="cart-popup-buttons">
								<button id="goToCartButton" class="btn btn-primary">Go
									to Cart</button>
								<button id="continueShoppingButton" class="btn btn-secondary">Continue
									Shopping</button>
							</div>
						</div>
					</div>
					<div id="limitReachPopup" class="cart-popup">
						<div class="cart-popup-content">
							<h5>Maximum Limit Reached!</h5>
							<div class="cart-popup-buttons">
								<button id="viewCartButton" class="btn btn-primary">View
									Cart</button>
							</div>
						</div>
					</div>
					<div class="d-flex pt-2">
						<strong class="text-dark mr-2">Share on:</strong>
						<div class="d-inline-flex">
							<a class="text-dark px-2" href=""> <i
								class="fab fa-facebook-f"></i>
							</a> <a class="text-dark px-2" href=""> <i class="fab fa-twitter"></i>
							</a> <a class="text-dark px-2" href=""> <i
								class="fab fa-linkedin-in"></i>
							</a> <a class="text-dark px-2" href=""> <i
								class="fab fa-pinterest"></i>
							</a>
						</div>
					</div>

				</div>
			</div>
		</div>

		<!--Will implement the product general information here, and review (optional)-->
		<div class="row px-xl-5">
			<div class="col">
				<div class="bg-light p-30">
					<div class="nav nav-tabs mb-4">
						<a class="nav-item nav-link text-dark active" data-toggle="tab"
							href="#tab-pane-1">General Information</a> <a
							class="nav-item nav-link text-dark" data-toggle="tab"
							href="#tab-pane-3">Reviews (0)</a>
					</div>
					<div class="tab-content">
						<div class="tab-pane fade show active" id="tab-pane-1"></div>
						<div class="tab-pane fade" id="tab-pane-3">
							<div class="row">
								<div class="col-md-6">
									<h4 class="mb-4">1 review for "Product Name"</h4>
									<div class="media mb-4">
										<img src="img/user.jpg" alt="Image"
											class="img-fluid mr-3 mt-1" style="width: 45px;">
										<div class="media-body">
											<h6>
												John Doe<small> - <i>01 Jan 2045</i></small>
											</h6>
											<div class="text-primary mb-2">
												<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
													class="fas fa-star"></i> <i class="fas fa-star-half-alt"></i>
												<i class="far fa-star"></i>
											</div>
											<p>Diam amet duo labore stet elitr ea clita ipsum, tempor
												labore accusam ipsum et no at. Kasd diam tempor rebum magna
												dolores sed sed eirmod ipsum.</p>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<h4 class="mb-4">Leave a review</h4>
									<small>Your email address will not be published.
										Required fields are marked *</small>
									<div class="d-flex my-3">
										<p class="mb-0 mr-2">Your Rating * :</p>
										<div class="text-primary">
											<i class="far fa-star"></i> <i class="far fa-star"></i> <i
												class="far fa-star"></i> <i class="far fa-star"></i> <i
												class="far fa-star"></i>
										</div>
									</div>
									<form>
										<div class="form-group">
											<label for="message">Your Review *</label>
											<textarea id="message" cols="30" rows="5"
												class="form-control"></textarea>
										</div>
										<div class="form-group">
											<label for="name">Your Name *</label> <input type="text"
												class="form-control" id="name">
										</div>
										<div class="form-group">
											<label for="email">Your Email *</label> <input type="email"
												class="form-control" id="email">
										</div>
										<div class="form-group mb-0">
											<input type="submit" value="Leave Your Review"
												class="btn btn-primary px-3">
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Shop Detail End -->


	<!--Will implement same category products customer may also like here -->
	<div class="container-fluid py-5">
		<h2
			class="section-title position-relative text-uppercase mx-xl-5 mb-4">
			<span class="bg-secondary pr-3">You May Also Like</span>
		</h2>
		<div class="row px-xl-5">
			<div class="col">
				<div class="owl-carousel related-carousel" id="related_products">
					<!-- Start from here -->
				</div>
			</div>
		</div>
	</div>
	<!-- Products End -->

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

	<!-- Show product details Javascript -->
	<script src="js/products/productDetails.js"></script>
	<script src="js/products/searchProduct.js"></script>

</body>

</html>