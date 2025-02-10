<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chemist Sale - Online Shop Website</title>
    <!-- Link to CSS file -->
    <link rel="stylesheet" href="css/login-style/styles.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- script for calling SHA256 to hash the password -->
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
	<script async defer src="js/login.js"></script>
	<script async defer src="js/register.js"></script>
</head>
<body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row bg-secondary py-1 px-xl-5">
                <div class="col-lg-6 d-none d-lg-block">
                    <div class="d-inline-flex align-items-center h-100">
                        <a class="text-body mr-3" href="about.html">About</a>
                        <a class="text-body mr-3" href="contact.html">Contact</a>
                        <a class="text-body mr-3" href="">Help</a>
                        <a class="text-body mr-3" href="">FAQs</a>
                    </div>
                </div>
            </div>
            <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
                <div class="center-content">
                    <a href="index.html" class="text-decoration-none">
                        <span class="h1 text-uppercase text-primary bg-dark px-2">Chemist</span>
                        <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Sale</span>
                    </a>
                </div>
             </div>
        </div>
        <!-- Topbar End -->
    
    
        <!-- Navbar Start -->
        <div class="container-fluid bg-dark mb-30">
            <div class="row px-xl-5">
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <span class="h1 text-uppercase text-dark bg-light px-2">Chemist</span>
                            <span class="h1 text-uppercase text-light bg-primary px-2 ml-n1">Sale</span>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="index.html" class="nav-item nav-link active">Home</a>
                                <a href="shop.html" class="nav-item nav-link">Shop</a>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar End -->
    
    <div class="cont">
        <div class="form-login sign-in">
            <h2>Welcome to Chemist Sale</h2>
            <label class="login-label">
                <span class="login-span">Email</span>
                <input type="email" id="emailLogin" required>
            </label>
            <label class="login-label">
                <span class="login-span">Password</span>
                <input type="password" id="password" required>
            </label>
            <p class="forgot-pass" id="forgotPassword">Forgot password?</p>
            <button class="login-button login-submit " onclick="loginUser(event)">Sign In</button>
         	<c:if test="${not empty loginMessage}">
         		<div class="p-10 mb-3"
					style="color: red; text-align: center;">${loginMessage}</div>
         	</c:if>
        </div>
        <div class="sub-cont">
            <div class="img">
                <div class="img__text m--up">
                 
                    <h3>Don't have an account? Please Sign up!<h3>
                </div>
                <div class="img__text m--in">
                
                    <h3>If you already has an account, just sign in.<h3>
                </div>
                <div class="img__btn">
                    <span class="m--up">Sign Up</span>
                    <span class="m--in">Sign In</span>
                </div>
            </div>
            <div class="form sign-up">
                <h2>Create your Account</h2>
                <label class="login-label">
                    <span class="login-span">First Name</span>
                    <input type="text" id="firstName" required>
                </label>
                <label class="login-label">
                    <span class="login-span">Last Name</span>
                    <input type="text" id="lastName" required>
                </label>
                <label class="login-label">
                    <span class="login-span">Email</span>
                    <input type="email" id="emailRegister" required>
                </label>
                <label class="login-label">
                    <span class="login-span">Password</span>
                    <input type="password" id="passReg" required>
                </label>
               <label class="login-label">
                    <span class="login-span">Confirm Password</span>
                    <input type="password" id="passConfirmReg" required>
                </label>
                <button class="login-button login-submit " onclick="registerUser(event)" >Sign Up</button>
   
            </div>
        </div>
    </div>
    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-secondary mt-5 pt-5">
        <div class="row px-xl-5 pt-5">
            <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                <h5 class="text-secondary text-uppercase mb-4">Get In Touch</h5>
                <p class="mb-4">We’d love to hear from you! Whether you have questions, feedback, or want to learn more about what we do, feel free to reach out. Our team is here to help and will respond to your inquiry as soon as possible. Contact us via email, phone, or by filling out the form—we look forward to connecting with you!</p>
            </div>
            <div class="col-lg-8 col-md-12">
                <div class="row">
                    <div class="col-md-4 mb-5">
                        <h5 class="text-secondary text-uppercase mb-4">Quick Shop</h5>
                        <div class="d-flex flex-column justify-content-start">
                            <a class="text-secondary mb-2" href="index.html"><i class="fa fa-angle-right mr-2"></i>Home</a>
                            <a class="text-secondary mb-2" href="shop.html"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                            <a class="text-secondary" href="contact.html"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                        </div>
                    </div>                    
                    <div class="col-md-4 mb-5">
                        <h5 class="text-secondary text-uppercase mb-4">My Account</h5>
                        <div class="d-flex flex-column justify-content-start">
                            <a class="text-secondary mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                            <a class="text-secondary mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <h5 class="text-secondary text-uppercase mb-4">Newsletter</h5>
                        <p>Never miss BIG SALES! Subscribe to our newsletter for the latest SALES, exclusive offers, and insider tips. Sign up today and be part of our growing community—it’s quick, easy, and totally free!
</p>
                        <form action="">
                            <div class="input-group">
                                <div class="input-group-append">
                                    <button class="btn btn-primary">
                                        <a href="login.jsp" class="dropdown-item">Sign in</a>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row border-top mx-xl-5 py-4" style="border-color: rgba(256, 256, 256, .1) !important;">
            <div class="col-md-6 px-xl-0">
                <p class="mb-md-0 text-center text-md-left text-secondary">
                    &copy; <a class="text-primary" href="#">Domain</a>. All Rights Reserved. Designed
                    by
                    <a class="text-primary" href="https://htmlcodex.com">HTML Codex</a>
                </p>
            </div>
            <div class="col-md-6 px-xl-0 text-center text-md-right">
                <img class="img-fluid" src="img/payments.png" alt="">
            </div>
        </div>
    </div>
    <!-- Footer End -->

    <script>
        document.querySelector('.img__btn').addEventListener('click', function() {
            document.querySelector('.cont').classList.toggle('s--signup');
        });
    </script>
</body>
</html>