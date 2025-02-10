
(function() {
	"use strict";

	document.addEventListener("DOMContentLoaded", function() {


		let params = new URLSearchParams(window.location.search);
		let searchQuery = "";
		if (params.get("category"))
			searchQuery = params.get("category");
		if (params.get("search"))
			searchQuery = params.get("search");
		if (params.get("sale"))
			searchQuery = params.get("sale");

		let productsPerPage = 16;
		let currentPage = 1;
		let allProducts = [];
		let filteredProducts = [];
		let currentSort = null;
		const cartCountElement = document.getElementById("prodInCart");

		function displayProducts(products) {
			const productList = document.getElementById("product-list");
			productList.innerHTML = "";

			const startIndex = (currentPage - 1) * productsPerPage;
			const endIndex = Math.min(startIndex + productsPerPage, products.length);

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

				const encodedProductName = encodeURIComponent(product.name);
				const productItem = `
						
		                <a href="product.html?product=${encodedProductName}" class="col-lg-3 col-md-4 col-sm-6 pb-1">
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
				productList.innerHTML += productItem;
			}
		}

		function createPagination(totalProducts) {
			const totalPages = Math.ceil(totalProducts / productsPerPage);
			const pagination = document.getElementById("pagination");
			pagination.innerHTML = "";

			function createPageLink(pageNumber, text, isDisabled = false, isCurrent = false) {
				const li = document.createElement("li");
				li.className = "page-item";

				if (isDisabled) {
					li.classList.add("disabled");
				} else if (isCurrent) {
					li.classList.add("active");
				} else {
					li.addEventListener("click", function(e) {
						e.preventDefault();
						currentPage = pageNumber;
						displayProducts(filteredProducts);
						createPagination(filteredProducts.length);
					});
				}

				const a = document.createElement("a");
				a.className = "page-link";
				a.href = "#";
				a.textContent = text;

				li.appendChild(a);
				return li;
			}

			pagination.appendChild(createPageLink(1, "First", currentPage === 1));
			pagination.appendChild(createPageLink(currentPage - 1, "Previous", currentPage === 1));

			let startPage = Math.max(1, currentPage - 2);
			let endPage = Math.min(totalPages, currentPage + 2);

			if (totalPages <= 5) {
				startPage = 1;
				endPage = totalPages;
			} else {
				if (currentPage <= 3) {
					endPage = Math.min(5, totalPages);
				}

				if (currentPage >= totalPages - 2) {
					startPage = Math.max(1, totalPages - 4);
				}
			}

			for (let i = startPage; i <= endPage; i++) {
				pagination.appendChild(createPageLink(i, i, false, i === currentPage));
			}

			pagination.appendChild(createPageLink(currentPage + 1, "Next", currentPage === totalPages));
			pagination.appendChild(createPageLink(totalPages, "Last", currentPage === totalPages));
		}

		function sortProducts() {
			if (currentSort === "price-asc") {
				filteredProducts.sort((a, b) => a.price - b.price);
			} else if (currentSort === "price-desc") {
				filteredProducts.sort((a, b) => b.price - a.price);
			} else if (currentSort === "rating-desc") {
				filteredProducts.sort((a, b) => b.ratingStarBar - a.ratingStarBar);
			}
		}

		function filterProducts() {
			const noPriceFilterChecked = !document.getElementById("price-all").checked &&
				!document.getElementById("price-1").checked &&
				!document.getElementById("price-2").checked &&
				!document.getElementById("price-3").checked &&
				!document.getElementById("price-4").checked &&
				!document.getElementById("price-5").checked;

			filteredProducts = allProducts.filter(product => {
				if (document.getElementById("price-all").checked || noPriceFilterChecked) {
					return true;
				}

				const price = product.price;
				if (document.getElementById("price-1").checked && price >= 0 && price < 20) return true;
				if (document.getElementById("price-2").checked && price >= 20 && price < 30) return true;
				if (document.getElementById("price-3").checked && price >= 30 && price < 40) return true;
				if (document.getElementById("price-4").checked && price >= 40 && price < 50) return true;
				if (document.getElementById("price-5").checked && price >= 50) return true;

				return false;
			});
			currentPage = 1;
			displayProducts(filteredProducts);
			createPagination(filteredProducts.length);
		}

		function updatePriceCounts() {
			const counts = {
				all: allProducts.length,
				1: allProducts.filter(p => p.price >= 0 && p.price < 20).length,
				2: allProducts.filter(p => p.price >= 20 && p.price < 30).length,
				3: allProducts.filter(p => p.price >= 30 && p.price < 40).length,
				4: allProducts.filter(p => p.price >= 40 && p.price < 50).length,
				5: allProducts.filter(p => p.price >= 50).length,
			};

			document.getElementById("all-price-count").textContent = counts.all;
			document.getElementById("price-1-count").textContent = counts[1];
			document.getElementById("price-2-count").textContent = counts[2];
			document.getElementById("price-3-count").textContent = counts[3];
			document.getElementById("price-4-count").textContent = counts[4];
			document.getElementById("price-5-count").textContent = counts[5];
		}

		function showBreadcumb(products) {
			const breadcrumb = document.getElementById("breadcrumb");
			if (params.has("search")) {
				breadcrumb.innerHTML += `<span class="breadcrumb-item active">Search Results for ${searchQuery}</span>`;
				return;
			}
			if (!searchQuery)
				return;
			if (searchQuery === "BetterThanHalf") {
				breadcrumb.innerHTML += `<span class="breadcrumb-item active">More Than Half Price Sale</span>`;
				return;
			}
			if (searchQuery.match("Fragrances") && searchQuery.match("women")) {
				breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=Fragrances">Fragrances</a>`;
				breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=womenFragrances">Fragrances - Women</a>`;
			}
			else if (searchQuery.match("Fragrances") && searchQuery.match("men")) {
				breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=Fragrances">Fragrances</a>`;
				breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=menFragrances">Fragrances - Men</a>`;

			} else
				breadcrumb.innerHTML += `<a class="breadcrumb-item text-dark" href="shop.html?category=${products[0].category}">${products[0].category}</a>`;

		}

		function loadProducts(searchQuery = "") {
			fetch(`SearchModule?searchTerm=${encodeURIComponent(searchQuery)}`)
				.then(response => response.json())
				.then(data => {
					if (data.length === 0) {
						console.log("data is null");
						document.getElementById("noMatchingProduct").style.display = "flex";
						return;
					}
					allProducts = data;
					console.log("Product data loaded successfully:", allProducts);
					showBreadcumb(allProducts);
					updatePriceCounts();
					sortProducts();
					filterProducts();
				})
				.catch(() => {
					console.error("Failed to load product data");
				});
		}

		function displayCartCount() {
			const cartCount = parseInt(localStorage.getItem("cartCount")) || 0;
			cartCountElement.textContent = cartCount;
		}

		document.getElementById("productsPerPageDropdown").addEventListener("click", function(e) {
			if (e.target.classList.contains("dropdown-item")) {
				e.preventDefault();
				productsPerPage = parseInt(e.target.dataset.value, 10);
				displayProducts(filteredProducts);
				createPagination(filteredProducts.length);
			}
		});

		document.getElementById("productSortingDropdown").addEventListener("click", function(e) {
			if (e.target.classList.contains("dropdown-item")) {
				e.preventDefault();
				currentSort = e.target.dataset.sort;
				sortProducts();
				currentPage = 1;
				displayProducts(filteredProducts);
				createPagination(filteredProducts.length);
			}
		});

		document.getElementById("filterButton").addEventListener("click", function(e) {
			e.preventDefault();
			filterProducts();
		});

		document.getElementById("price-all").addEventListener("change", function() {
			if (this.checked) {
				document.querySelectorAll("[id^='price-']:not(#price-all)").forEach(el => el.checked = false);
			}
		});

		document.querySelectorAll("[id^='price-']:not(#price-all)").forEach(el => {
			el.addEventListener("change", function() {
				if (this.checked) {
					document.getElementById("price-all").checked = false;
				}
			});
		});
		
		document.getElementById("continueShoping").addEventListener("click", () => {
			window.location.href = "index.html";
		});

		loadProducts(searchQuery);

		displayCartCount();
	});
})();
