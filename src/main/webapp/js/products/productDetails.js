
(function() {
	"use strict";

	document.addEventListener("DOMContentLoaded", function() {

		let params = new URLSearchParams(window.location.search);
		let searchQuery = params.get("product");

		console.log("product name: " + searchQuery);

		let limitQty;
		let category;
		const cartCountElement = document.getElementById("prodInCart");
		let cart = JSON.parse(localStorage.getItem("cart")) || [];
		const addToCartButton = document.getElementById("addToCart_button");
		const cartPopup = document.getElementById("cartPopup");
		const goToCartButton = document.getElementById("goToCartButton");
		const continueShoppingButton = document.getElementById("continueShoppingButton");
		const limitReachPopup = document.getElementById("limitReachPopup");
		const viewCartButton = document.getElementById("viewCartButton");



		function displayImages(product) {
			const imageList = document.getElementById("image-list");
			imageList.innerHTML = "";
			const encodedImagePath = encodeURIComponent(product.category);

			fetch(`ImageList?path=${encodedImagePath}`)
				.then(response => response.json())
				.then(files => {
					// Filter files containing product name
					const filteredFiles = files.filter(file => file.includes(product.name));

					// Display the filtered files
					let ind = 0;
					filteredFiles.forEach(file => {
						if (ind === 0) {
							imageList.innerHTML += `<div class="carousel-item active">
														<img class="w-100 h-100" src="img/${product.category}/${file}" alt="${product.name}">
													</div>`;
						}
						else {
							imageList.innerHTML += `<div class="carousel-item">
													<img class="w-100 h-100" src="img/${product.category}/${file}" alt="${product.name}">
													</div>`;
						}

						ind++;
					});
				})
				.catch(error => console.error('Error fetching files:' + error));
		}


		function displayProduct(product) {
			const productName = document.getElementById("product_name");
			productName.innerHTML = "";
			productName.innerText = product.name;

			const productRating = document.getElementById("product_rating");
			productRating.innerHTML = "";
			const fullStars = Math.floor(product.ratingStarBar / 20);
			const hasHalfStar = product.ratingStarBar % 20 >= 5;
			const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

			let starsHTML = "";

			for (let i = 0; i < fullStars; i++) {
				starsHTML += "<small class='fa fa-star text-primary mr-1'></small>";
			}

			if (hasHalfStar) {
				starsHTML += "<small class='fa fa-star-half-alt text-primary mr-1'></small>";
			}

			for (let i = 0; i < emptyStars; i++) {
				starsHTML += "<small class='far fa-star text-primary mr-1'></small>";
			}

			productRating.innerHTML += starsHTML;

			const rateCount = document.getElementById("rate_count");
			rateCount.innerText += `(${product.rateCount} Reviews)`;

			const price = document.getElementById("product_price");
			price.innerText = `$${product.price}`;

			const productDiscount = document.getElementById("product_discount");
			productDiscount.innerHTML = "";
			productDiscount.innerHTML += `$${product.priceDiscount} Off RRP`;

			const productInfo = document.getElementById("tab-pane-1");
			productInfo.innerHTML = "";
			productInfo.innerHTML += `<h4 class="mb-3">Product Description</h4>`;
			const words = product.generalInfo.split("\n");
			let formattedString = "<p>";

			for (let i = 0; i < words.length; i++) {
				formattedString += words[i] + "</p><p>";
				if (i === words.length - 2) {
					formattedString += words[i + 1] + "</p>";
					break;
				}
			}
			productInfo.innerHTML += `<div style="white-space: pre-wrap;">${formattedString}</div>`;
		}

		function displayRelativeProds(products) {
			const productList = document.getElementById("related_products");
			productList.innerHTML = "";
			//	productList.innerHTML += `<div class="owl-carousel related-carousel" >`;
			console.log("get relative id");
			const startIndex = 0;
			const endIndex = Math.min(products.length, 8);
			console.log(" end index: " + endIndex);

			let productHTML = "";

			for (let i = startIndex; i < endIndex; i++) {
				const product = products[i];
				const fullStars = Math.floor(product.ratingStarBar / 20);
				const hasHalfStar = product.ratingStarBar % 20 >= 5;
				const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);
				const rating = (product.ratingStarBar / 20).toFixed(1);
				let starsHTML = "";

				for (let i = 0; i < fullStars; i++) {
					starsHTML += "<small class='fa fa-star text-primary mr-1'></small>";
				}

				if (hasHalfStar) {
					starsHTML += "<small class='fa fa-star-half-alt text-primary mr-1'></small>";
				}

				for (let i = 0; i < emptyStars; i++) {
					starsHTML += "<small class='far fa-star text-primary mr-1'></small>";
				}

				console.log("i'm here");
				const encodedProductName = encodeURIComponent(product.name);

				const productItem = `
				<a href="product.html?product=${encodedProductName}" >
				    <div class="product-item bg-light mb-4">
				        <div class="product-img position-relative overflow-hidden">
				            <img class="img-fluid w-100" src="img/${product.category}/${product.name}.jpg" alt="${product.name}">
				        </div>
				        <div class="text-center py-4">
				            <p class="h6 text-decoration-none text-truncate">${product.name}</p>
				            <div class="d-flex align-items-center justify-content-center">
				                <h4>$${product.price}</h4>
							</div>
							<div class="d-flex align-items-center justify-content-center">
				                <small class="ml-2" style="color:red;">$${product.priceDiscount} Off RRP</small>
				            </div>
				            <div class="d-flex align-items-center justify-content-center mb-1">
				                ${starsHTML}
								<small>${rating}</small>
				                <small>(${product.rateCount})</small>
				            </div>
				        </div>
				    </div>
				</a>
			        `;
				productHTML += productItem;
			}
			//				productList.innerHTML += `<div class="owl-carousel related-carousel" >${productHTML}</div>`;
			productList.innerHTML += `${productHTML}`;
			//		console.log("productList.innerHTML :" + productList.innerHTML);
			//			initializeOwlCarousel();

			//		window.onload = function() {
			//			initializeOwlCarousel();
			//			};

		}
		/*
				function initializeOwlCarousel() {
					const relatedCarousel = document.querySelector('.related-carousel');
					if (relatedCarousel) { // Check if the element exists
						if (typeof OwlCarousel === 'function') { // Check if OwlCarousel is loaded
							new OwlCarousel(relatedCarousel, {
								loop: true,
								margin: 29,
								nav: false,
								autoplay: true,
								smartSpeed: 1000,
								responsive: {
									0: { items: 1 },
									576: { items: 2 },
									768: { items: 3 },
									992: { items: 4 }
								}
							});
						} else {
							console.error("Owl Carousel library not loaded.");
						}
					} else {
						console.error("Owl Carousel element (.related-carousel) not found in the DOM.");
					}
				}
		*/

		function showBreadcumb(product) {
			const breadcrumb = document.getElementById("breadcrumb");
			if (product.category.match("Fragrances")) {
				breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=Fragrances">Fragrances</a>`;
				if (product.gender.match("W"))
					breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=womenFragrances">Fragrances - Women</a>`;
				if (product.gender.match("M"))
					breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=menFragrances">Fragrances - Men</a>`;
			} else
				breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=${product.category}">${product.category}</a>`;

		}

		function updateQuantityOptions(product) {
			const quantitySelect = document.getElementById('quantitySelect');
			quantitySelect.innerHTML = '';

			const defaultOption = document.createElement('option');
			defaultOption.value = '1';
			defaultOption.text = "1";
			quantitySelect.appendChild(defaultOption);

			if (product) {
				for (let i = 2; i <= product.availQty; i++) {
					const option = document.createElement('option');
					option.value = i;
					option.text = i;
					quantitySelect.appendChild(option);
				}
				limitQty = product.availQty;
			}

		}

		quantitySelect.addEventListener('change', (event) => {
			const orderQty = event.target.value;
			quantitySelect.value = orderQty;
		});

		addToCartButton.addEventListener("click", function() {
			const product = {
				name: document.getElementById("product_name").innerText,
				category: category,
				price: parseFloat(document.getElementById("product_price").innerText.replace("$", "")),
				quantity: parseInt(document.getElementById("quantitySelect").value),
				limit: limitQty
			};

			// Check if product already exists in cart
			const existingProduct = cart.find((item) => item.name === product.name);
			const reachLimit = false;
			if (existingProduct) {
				if ((existingProduct.quantity + product.quantity) > product.limit) {
					limitReachPopup.style.display = "flex";
					reachLimit = true;
				}
				else
					existingProduct.quantity += product.quantity;
			} else {
				cart.push(product);
			}

			if (!reachLimit) {
				localStorage.setItem("cart", JSON.stringify(cart));
				updateCartCount();
				displayCartCount();
				cartPopup.style.display = "flex";
			}
		});

		function updateCartCount() {
			const cart = JSON.parse(localStorage.getItem("cart")) || [];
			const totalProducts = cart.reduce((sum, product) => sum + product.quantity, 0);
			localStorage.setItem("cartCount", totalProducts);
			console.log("Total cart count: " + totalProducts);
		}

		goToCartButton.addEventListener("click", function() {
			window.location.href = "cart.html";
		});

		continueShoppingButton.addEventListener("click", function() {
			window.location.href = "index.html";
		});

		viewCartButton.addEventListener("click", function() {
			window.location.href = "cart.html";
		});

		window.addEventListener("click", function(event) {
			if (event.target === cartPopup) {
				cartPopup.style.display = "none";
			}

			if (event.target === limitReachPopup) {
				limitReachPopup.style.display = "none";
			}
		});


		function loadProduct(searchQuery = "") {
			const encodedSearchQuery = encodeURIComponent(searchQuery);
			fetch(`SearchModule?product=${encodedSearchQuery}`)
				.then(response => response.json())
				.then(data => {
					const product = data;
					console.log("Product data loaded successfully:", product);
					showBreadcumb(product);
					displayImages(product);
					updateQuantityOptions(product);
					displayProduct(product);
					setCategory(product);
					loadRelativeProds(product);
				})
				.catch(() => {
					console.error("Failed to load product data");
				});
		}

		function loadRelativeProds(product) {
			const encodedSearchQuery = encodeURIComponent(product.category);
			fetch(`SearchModule?searchTerm=${encodedSearchQuery}`)
				.then(response => response.json())
				.then(data => {
					const products = data;
					console.log("Product data loaded successfully:", products);
					displayRelativeProds(products);
				})
				.catch(() => {
					console.error("Failed to load product data");
				});
		}


		function displayCartCount() {
			const cartCount = parseInt(localStorage.getItem("cartCount")) || 0;
			cartCountElement.textContent = cartCount;
		}

		function setCategory(product) {
			category = product.category;
		}

		//initialize the page
		displayCartCount();
		loadProduct(searchQuery);


	});
})();
