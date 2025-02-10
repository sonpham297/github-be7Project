<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<!-- Topbar Start -->
	<div class="container-fluid">
		<div class="row bg-secondary py-1 px-xl-5">
			<div class="col-lg-6 d-none d-lg-block">
				<div class="d-inline-flex align-items-center h-100">
					<a class="text-body mr-3" href="about.jsp">About</a> <a
						class="text-body mr-3" href="contact.jsp">Contact</a> <a
						class="text-body mr-3" href="">Help</a> <a class="text-body mr-3"
						href="">FAQs</a>
				</div>
			</div>
			<div class="col-lg-6 text-center text-lg-right">
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<div class="p-10 mb-3">
							<div class="p-10 mt-3"> Welcome,
								${sessionScope.user.lastName.toUpperCase()} 
							
								<button class="btn btn-primary" onclick="window.location.href='LogoutServlet'">Logout</button>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="d-inline-flex align-items-center">
							<div class="btn-group">
								<button type="button"
									class="btn btn-sm btn-light dropdown-toggle"
									data-toggle="dropdown">My Account</button>
								<div class="dropdown-menu dropdown-menu-right">
									<a href="login.jsp" class="dropdown-item">Sign in/ Sign up</a>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div
			class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
			<div class="col-lg-4">
				<a href="home.jsp" class="text-decoration-none"> <span
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
				<nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
					<div class="collapse navbar-collapse justify-content-between"
						id="navbarCollapse">
						<div class="navbar-nav mr-auto py-0">
							<a href="home.jsp" class="nav-item nav-link active">Home</a> <a
								href="shop.jsp" class="nav-item nav-link">Shop</a>
						</div>
						<div class="navbar-nav ml-auto py-0 d-none d-lg-block">
							<a href="" class="btn px-0"> <i
								class="fas fa-heart text-primary"></i> <span
								class="badge text-secondary border border-secondary rounded-circle"
								style="padding-bottom: 2px;">0</span>
							</a> <a href="cart.jsp" class="btn px-0 ml-3"> <i
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
