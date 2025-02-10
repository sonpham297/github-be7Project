(function() {
	"use strict";

	document.addEventListener("DOMContentLoaded", function() {
		let cart = JSON.parse(localStorage.getItem("cart")) || [];
		const cartTableBody = document.getElementById("tableProduct");
		const cartCountElement = document.getElementById("prodInCart");
		let subtotal = 0;
		let discountedSubtotal = 0;

		//initial for voucer form
		const form = document.getElementById('voucherForm');
		const messageDiv = document.getElementById('voucherMessage');
		const voucherInput = document.getElementById('voucherCode');
		const validVouchers = ["be7", "thaydzun", "rienghung"]; // Example valid vouchers, need to improve to file
		const appliedVoucher = document.getElementById('appliedVoucher');
		const newSubtotal = document.getElementById("newSubtotal");
		let enteredVoucher = null;
		const proceedCheckout = document.getElementById("checkout");

		// Render Cart Items
		function renderCartItems() {
			console.log("show cart when initialize:");
			showCart();
			const cartCount = parseInt(localStorage.getItem("cartCount")) || 0;
			cartTableBody.innerHTML = "";
			subtotal = 0;
			//return if cart is empty
			if (!cartCount) {
				document.getElementById("emptyCartPopup").style.display = "flex";
			}

			console.log("show cart:" + cart);

			cart.forEach((item, index) => {
				const total = item.price * item.quantity;
				subtotal += total;
				const encodedProductName = encodeURIComponent(item.name);
				const row = `
                <tr>
					<td class="align-middle">
						<img src="img/${item.category}/${item.name}.jpg" alt="${item.name}" style="width: 80px;">
	    			</td>
					<td class="align-middle">
					    <a href="product.html?product=${encodedProductName}">
					        <small style="color: black">${item.name}<small>
					    </a>
					</td>
                    <td class="align-middle">$${item.price.toFixed(2)}</td>
                    <td class="align-middle">
                        <div style="width: 100px;">
							<select class="form-select form-select-sm" id="quantitySelect${index}" data-index="${index}"
								style="-webkit-appearance: menulist-button; border: 1px solid #888; width: auto; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; width: 55px !important; height: 39px !important">
								</select>
                        </div>
                    </td>
                    <td class="align-middle">$${total.toFixed(2)}</td>
                    <td class="align-middle">
                        <button class="btn btn-sm btn-danger btn-remove" data-index="${index}">
                            <i class="fa fa-times"></i>
                        </button>
                    </td>
                </tr>`;
				cartTableBody.innerHTML += row;
				setQuantityOptions(item, index);
			});

			displayCartCount();
			displaySubtotal();

			//still apply voucher even user change quantity or remove items
			//unless they remove voucher code
			if (validVouchers.includes(localStorage.getItem("finalVoucher"))) {
				applyVoucher(enteredVoucher);
				messageDiv.style.display = 'none';
				voucherInput.value = "";
			}
		}

		//set selection quantity for appropriate item
		function setQuantityOptions(item, index) {
			const quantitySelect = document.getElementById("quantitySelect" + index);
			quantitySelect.innerHTML = '';

			const defaultOption = document.createElement('option');
			defaultOption.value = item.quantity;
			defaultOption.text = item.quantity;
			quantitySelect.appendChild(defaultOption);

			for (let i = 1; i <= item.limit; i++) {
				const option = document.createElement('option');
				option.value = i;
				option.text = i;
				quantitySelect.appendChild(option);
			}
		}

		//update local variable cart and cartCount in localStorage
		function updateCart() {
			cart = JSON.parse(localStorage.getItem("cart")) || [];
			const totalProducts = cart.reduce((sum, product) => sum + product.quantity, 0);
			localStorage.setItem("cartCount", totalProducts);
		}

		function displayCartCount() {
			const cartCount = parseInt(localStorage.getItem("cartCount")) || 0;
			cartCountElement.textContent = cartCount;
		}

		function displaySubtotal() {
			document.getElementById("subtotal").textContent = "$" + subtotal.toFixed(2);
		}

		//apply voucher
		function applyVoucher(enteredVoucher) {
			if (enteredVoucher === "be7") {
				discountedSubtotal = (subtotal * 0.9).toFixed(2);
				showAppliedVoucher("BE7 -10%");
			} else if (enteredVoucher === "thaydzun") {
				discountedSubtotal = (subtotal * 0.5).toFixed(2);
				showAppliedVoucher("THAYDZUN -50%");
			} else if (enteredVoucher === "rienghung") {
				discountedSubtotal = (subtotal * 0.8).toFixed(2);
				showAppliedVoucher("RIENGHUNG -20%");
			}
			showNewSubtotal(discountedSubtotal);
		}

		//show message with appropriate voucher
		function showMessage(message) {
			messageDiv.textContent = message;
			messageDiv.style.display = 'flex';
		}

		//show applied voucher
		function showAppliedVoucher(message) {
			appliedVoucher.innerHTML = "";
			appliedVoucher.innerHTML += `<h6>Voucher Applied:</h6><h6>${message}</h6>`;
			appliedVoucher.innerHTML += `<button class="btn btn-sm" id="removeVoucher">
						                     <i class="fa fa-times"></i>
						                  </button>`;
			appliedVoucher.style.display = 'flex';
		}

		//show new subtotal
		function showNewSubtotal(subtotal) {
			newSubtotal.innerHTML = "";
			newSubtotal.innerHTML += `<h5>New Subtotal:</h5><h5>$${subtotal}</h5>`;
			newSubtotal.style.display = 'flex';
		}

		//Update product quantity
		cartTableBody.addEventListener("change", function(event) {
			if (event.target.classList.contains("form-select")) {
				const index = event.target.dataset.index;
				const selectedQuantity = parseInt(event.target.value);

				if (index !== undefined && cart[index]) {
					cart[index].quantity = selectedQuantity;

					//update cart in localStorage
					localStorage.setItem("cart", JSON.stringify(cart));
					console.log("show cart when update quantity:");
					showCart();
					updateCart();
					renderCartItems();
				}
			}
		});


		//Remove Item
		cartTableBody.addEventListener("click", function(event) {
			if (event.target.closest(".btn-remove")) {
				const index = event.target.closest(".btn-remove").dataset.index;
				cart.splice(index, 1);

				//update cart in localStorage
				localStorage.setItem("cart", JSON.stringify(cart));
				console.log("show cart when remove:");
				showCart();

				updateCart();
				renderCartItems();
			}
		});

		//clear all products in cart
		document.getElementById("clearCartButton").addEventListener("click", () => {
			localStorage.removeItem("cart");
			localStorage.removeItem("cartCount");
			document.getElementById("prodInCart").textContent = "0"; // Reset cart count display
			document.getElementById("clearCartPopup").style.display = "flex";
			cart = null;
		});

		document.getElementById("backToHomepage").addEventListener("click", () => {
			window.location.href = "index.html";
		});

		document.getElementById("continueShoping").addEventListener("click", () => {
			window.location.href = "index.html";
		});

		document.getElementById("keepShoping").addEventListener("click", () => {
			window.location.href = "index.html";
		});

		//validate voucher than apply if applicable 
		form.addEventListener('submit', function(event) {
			event.preventDefault(); // Prevent form submission
			enteredVoucher = voucherInput.value.trim().toLowerCase();
			if (!enteredVoucher) {
				showMessage("Please enter a voucher code.");
				return;
			}

			if (validVouchers.includes(enteredVoucher)) {
				applyVoucher(enteredVoucher);
				messageDiv.style.display = 'none'; // Hide the message if it was previously shown
				voucherInput.value = ""; // Clear the input field
			} else {
				showMessage("Invalid voucher code.");
			}
		});

		// Hide message on click outside the voucher form
		document.addEventListener('click', function(event) {
			if (!form.contains(event.target)) {
				messageDiv.style.display = 'none';
			}
		});

		// Hide message if user's inputing voucher
		voucherInput.addEventListener('focus', function() {
			messageDiv.style.display = 'none';
		})

		//remove voucher
		appliedVoucher.addEventListener('click', function(event) {
			if (event.target && event.target.id === 'removeVoucher') {
				console.log("in the event listener");
				discountedSubtotal = subtotal;
				enteredVoucher = "";
				appliedVoucher.style.display = "none";
				newSubtotal.style.display = "none";
			}
		});

		proceedCheckout.addEventListener('click', () => {
			if (discountedSubtotal < subtotal)
				localStorage.setItem("finalSubtotal", discountedSubtotal);
			else
				localStorage.setItem("finalSubtotal", subtotal);
			if (enteredVoucher === null)
				localStorage.setItem("finalVoucher", null);
			else
				localStorage.setItem("finalVoucher", enteredVoucher);
			
			window.location.href = "checkout.jsp";
		});
		//initialize cart page
		renderCartItems();

		function showCart() {
			const cartTest = JSON.parse(localStorage.getItem("cart")) || [];

			cartTest.forEach((item) => {
				console.log(item);
			});
		}


	});
})();