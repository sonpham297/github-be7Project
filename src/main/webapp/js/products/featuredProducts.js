(function() {
	"use strict";

	document.addEventListener("DOMContentLoaded", function() {

		const productsPerPage = 8;
		let currentPage = 1;
		let allProducts = []; // Store all fetched products
		const cartCountElement = document.getElementById("prodInCart");

		function displayProducts(products) {
			const productList = document.getElementById('product-list');
			productList.innerHTML = ''; // Clear existing products

			const startIndex = (currentPage - 1) * productsPerPage;
			const endIndex = Math.min(startIndex + productsPerPage, products.length);

			for (let i = startIndex; i < endIndex; i++) {
				const product = products[i];

				const fullStars = Math.floor(product.ratingStarBar / 20);
				const hasHalfStar = product.ratingStarBar % 20 >= 5;
				const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);
				const rating = (product.ratingStarBar / 20).toFixed(1);

				let starsHTML = "";

				for (let j = 0; j < fullStars; j++) {
					starsHTML += "<small class='fa fa-star text-primary mr-1'></small>";
				}

				if (hasHalfStar) {
					starsHTML += "<small class='fa fa-star-half-alt text-primary mr-1'></small>";
				}

				for (let j = 0; j < emptyStars; j++) {
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
				productList.insertAdjacentHTML('beforeend', productItem);
			}
		}

		function createPagination(totalProducts) {
			const totalPages = Math.ceil(totalProducts / productsPerPage);
			const pagination = document.getElementById('pagination');
			pagination.innerHTML = '';

			// Previous Page Link
			const prevLink = document.createElement('li');
			prevLink.className = 'page-item';
			prevLink.innerHTML = '<a class="page-link" href="#" aria-label="Previous">Previous</a>';

			if (currentPage === 1) {
				prevLink.classList.add('disabled');
			} else {
				prevLink.addEventListener('click', function(e) {
					e.preventDefault();
					currentPage--;
					displayProducts(allProducts);
					createPagination(allProducts.length);
				});
			}
			pagination.appendChild(prevLink);

			// Page Number Links
			for (let i = 1; i <= totalPages; i++) {
				const pageLink = document.createElement('li');
				pageLink.className = 'page-item';
				pageLink.innerHTML = `<a class="page-link" href="#">${i}</a>`;

				if (i === currentPage) {
					pageLink.classList.add('active');
				}

				pageLink.addEventListener('click', function(e) {
					e.preventDefault();
					currentPage = i;
					displayProducts(allProducts);
					createPagination(allProducts.length); // Update active class
				});

				pagination.appendChild(pageLink);
			}

			// Next Page Link
			const nextLink = document.createElement('li');
			nextLink.className = 'page-item';
			nextLink.innerHTML = '<a class="page-link" href="#" aria-label="Next">Next</a>';

			if (currentPage === totalPages) {
				nextLink.classList.add('disabled');
			} else {
				nextLink.addEventListener('click', function(e) {
					e.preventDefault();
					currentPage++;
					displayProducts(allProducts);
					createPagination(allProducts.length);
				});
			}
			pagination.appendChild(nextLink);
		}

		fetch('FeaturedProducts')
			.then(response => response.json())
			.then(data => {
				allProducts = data; // Store all products
				console.log(data);
				displayProducts(allProducts);
				createPagination(allProducts.length);
			})
			.catch(error => {
				console.error('Failed to load product data', error);
			});


		function displayCartCount() {
			const cartCount = parseInt(localStorage.getItem("cartCount")) || 0;
			cartCountElement.textContent = cartCount;
		}

		displayCartCount();
	});

})();
