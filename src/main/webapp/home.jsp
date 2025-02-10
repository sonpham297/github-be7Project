<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<jsp:include page="header.jsp"></jsp:include>
	<!-- Carousel Start -->
	<div class="container-fluid mb-3">
		<div class="row px-xl-5">
			<div class="col-lg-8">
				<div id="header-carousel"
					class="carousel slide carousel-fade mb-30 mb-lg-0"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#header-carousel" data-slide-to="0"
							class="active"></li>
						<li data-target="#header-carousel" data-slide-to="1"></li>
						<li data-target="#header-carousel" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner">
						<div class="carousel-item position-relative active"
							style="height: 430px;">
							<img class="position-absolute w-100 h-100" src="img/central1.jpg"
								style="object-fit: cover;">
							<div
								class="carousel-caption d-flex flex-column align-items-center justify-content-center">
								<div class="p-3" style="max-width: 700px;">
									<h1
										class="display-4 text-white mb-3 animate__animated animate__fadeInDown">Fragrances</h1>
									<a
										class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp"
										href="shop.html?category=Fragrances">Shop Now</a>
								</div>
							</div>
						</div>
						<div class="carousel-item position-relative"
							style="height: 430px;">
							<img class="position-absolute w-100 h-100" src="img/central2.jpg"
								style="object-fit: cover;">
							<div
								class="carousel-caption d-flex flex-column align-items-center justify-content-center">
								<div class="p-3" style="max-width: 700px;">
									<h1
										class="display-4 text-white mb-3 animate__animated animate__fadeInDown">Beauty</h1>
									<a
										class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp"
										href="shop.html?category=Beauty">Shop Now</a>
								</div>
							</div>
						</div>
						<div class="carousel-item position-relative"
							style="height: 430px;">
							<img class="position-absolute w-100 h-100" src="img/central3.jpg"
								style="object-fit: cover;">
							<div
								class="carousel-caption d-flex flex-column align-items-center justify-content-center">
								<div class="p-3" style="max-width: 700px;">
									<h1
										class="display-4 text-white mb-3 animate__animated animate__fadeInDown">Baby
										Care</h1>
									<a
										class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp"
										href="shop.html?category=Baby">Shop Now</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="product-offer mb-30" style="height: 200px;">
					<a class="text-decoration-none"
						href="shop.html?sale=BetterThanHalf"> <img class="img-fluid"
						src="img/special-offer-ad-banner.jpg" alt="">
					</a>
				</div>
				<div class="product-offer mb-30" style="height: 200px;">
					<a class="text-decoration-none" href="shop.html?category=Clearance">
						<img class="img-fluid" src="img/clearance.avif" alt="">
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Carousel End -->


	<!-- Featured Start -->
	<div class="container-fluid pt-5">
		<div class="row px-xl-5 pb-3">
			<div class="col-lg-3 col-md-6 col-sm-12 pb-1">
				<div class="d-flex align-items-center bg-light mb-4"
					style="padding: 30px;">
					<h1 class="fa fa-check text-primary m-0 mr-3"></h1>
					<h5 class="font-weight-semi-bold m-0">Quality Product</h5>
				</div>
			</div>
			<div class="col-lg-3 col-md-6 col-sm-12 pb-1">
				<div class="d-flex align-items-center bg-light mb-4"
					style="padding: 30px;">
					<h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
					<h5 class="font-weight-semi-bold m-0">Oversea Shipping</h5>
				</div>
			</div>
			<div class="col-lg-3 col-md-6 col-sm-12 pb-1">
				<div class="d-flex align-items-center bg-light mb-4"
					style="padding: 30px;">
					<h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
					<h5 class="font-weight-semi-bold m-0">14-Day Return</h5>
				</div>
			</div>
			<div class="col-lg-3 col-md-6 col-sm-12 pb-1">
				<div class="d-flex align-items-center bg-light mb-4"
					style="padding: 30px;">
					<h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
					<h5 class="font-weight-semi-bold m-0">24/7 Support</h5>
				</div>
			</div>
		</div>
	</div>
	<!-- Featured End -->


	<!-- Categories Start -->
	<div class="container-fluid pt-5">
		<h2
			class="section-title position-relative text-uppercase mx-xl-5 mb-4">
			<span class="bg-secondary pr-3">Save on</span>
		</h2>
		<div class="row px-xl-5 pb-3">
			<div class="col-lg-3 col-md-4 col-sm-6 pb-1">
				<a class="text-decoration-none" href="shop.html?category=Fragrances">
					<div class="cat-item d-flex align-items-center mb-4">
						<div class="overflow-hidden" style="width: 100px; height: 100px;">
							<img class="img-fluid" src="img/fragrance1.jpg" alt="">
						</div>
						<div class="flex-fill pl-3">
							<h6>Fragrances</h6>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 pb-1">
				<a class="text-decoration-none" href="shop.html?category=Vitamins">
					<div class="cat-item img-zoom d-flex align-items-center mb-4">
						<div class="overflow-hidden" style="width: 100px; height: 100px;">
							<img class="img-fluid" src="img/vitamins.png" alt="">
						</div>
						<div class="flex-fill pl-3">
							<h6>Vitamins</h6>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 pb-1">
				<a class="text-decoration-none" href="shop.html?category=Beauty">
					<div class="cat-item img-zoom d-flex align-items-center mb-4">
						<div class="overflow-hidden" style="width: 100px; height: 100px;">
							<img class="img-fluid" src="img/beauty.png" alt="">
						</div>
						<div class="flex-fill pl-3">
							<h6>Beauty</h6>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 pb-1">
				<a class="text-decoration-none" href="shop.html?category=Baby">
					<div class="cat-item img-zoom d-flex align-items-center mb-4">
						<div class="overflow-hidden" style="width: 100px; height: 100px;">
							<img class="img-fluid" src="img/baby-care.png" alt="">
						</div>
						<div class="flex-fill pl-3">
							<h6>Baby Care</h6>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<!-- Categories End -->


	<!-- Products Start -->
	<div class="container-fluid pt-5 pb-3">
		<h2
			class="section-title position-relative text-uppercase mx-xl-5 mb-4">
			<span class="bg-secondary pr-3">Featured Products</span>
		</h2>
		<div id="product-list" class="row px-xl-5">
			<!-- Products will be loaded here dynamically -->
		</div>
		<div class="col-12">
			<nav>
				<ul class="pagination justify-content-center" id="pagination">
				</ul>
			</nav>
		</div>
	</div>
	<!-- Products End -->


	<!-- Vendor Start -->
	<div class="container-fluid py-5">
		<div class="row px-xl-5">
			<div class="col">
				<div class="owl-carousel vendor-carousel">
					<div class="bg-light p-4">
						<img src="img/vendor-1.jpg" alt="">
					</div>
					<div class="bg-light p-4">
						<img src="img/vendor-2.jpg" alt="">
					</div>
					<div class="bg-light p-4">
						<img src="img/vendor-3.jpg" alt="">
					</div>
					<div class="bg-light p-4">
						<img src="img/vendor-4.jpg" alt="">
					</div>
					<div class="bg-light p-4">
						<img src="img/vendor-5.jpg" alt="">
					</div>
					<div class="bg-light p-4">
						<img src="img/vendor-6.jpg" alt="">
					</div>
					<div class="bg-light p-4">
						<img src="img/vendor-7.jpg" alt="">
					</div>
					<div class="bg-light p-4">
						<img src="img/vendor-8.jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Vendor End -->

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

	<!-- Display Products Javascript -->
	<script src="js/products/featuredProducts.js"></script>
	<script src="js/products/searchProduct.js"></script>


</body>

</html>