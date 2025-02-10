(function() {
	"use strict";

	document.addEventListener("DOMContentLoaded", function() {

		//for cart		
		const cart = JSON.parse(localStorage.getItem("cart")) || [];
		const finalVoucher = localStorage.getItem("finalVoucher");
		const finalSubtotal = parseFloat(localStorage.getItem("finalSubtotal"));
		const products = document.getElementById("products");
		const subtotalEle = document.getElementById("subtotal");
		const totalEle = document.getElementById("total");

		function showOrderSum() {
			products.innerHTML = "";
			subtotalEle.innerHTML = "";
			totalEle.innerHTML = "";
			let subtotal = 0;
			let total = 0;

			cart.forEach((item) => {
				const totalOfEachItem = item.price * item.quantity;
				subtotal += totalOfEachItem;
				const row = `
				<tr>
					<td class="align-middle">
					    ${item.name}
					</td>
				    <td class="align-middle">
						${item.quantity}
				    </td>
				    <td class="align-middle">$${totalOfEachItem.toFixed(2)}</td>
	
				</tr>`;
				products.innerHTML += row;
			});

			subtotalEle.innerHTML += `<h6>Subtotal</h6>
										<h6>$${subtotal.toFixed(2)}</h6>`;

			if (finalVoucher === "null" || finalVoucher === "") {
				total = subtotal + 10;
			}
			else {
				showAppliedVoucher(voucherDescribe(finalVoucher));
				showNewSubtotal(finalSubtotal);
				total = finalSubtotal + 10;
			}
			totalEle.innerHTML += `<h5>Total</h5><h5>$${total.toFixed(2)}</h5>`;
		}

		function voucherDescribe(voucher) {
			voucher = voucher.toLowerCase();
			if (voucher === "be7")
				return "BE7 -10%";
			if (voucher === "thaydzun")
				return "THAYDZUN -50%";
			if (voucher === "rienghung")
				return "RIENGHUNG -20%";
		}

		function showAppliedVoucher(message) {
			const appliedVoucher = document.getElementById("appliedVoucher");
			appliedVoucher.innerHTML = "";
			appliedVoucher.innerHTML += `<h6>Voucher Applied:</h6><h6>${message}</h6>`;
			appliedVoucher.style.display = 'flex';
		}

		function showNewSubtotal(finalSubtotal) {
			const newSubtotal = document.getElementById("newSubtotal");
			newSubtotal.innerHTML = "";
			newSubtotal.innerHTML += `<h6>New Subtotal:</h6><h6>$${finalSubtotal}</h6>`;
			newSubtotal.style.display = 'flex';
		}


		// Helper functions for validation
		function showError(inputElement, message) {
			const errorElement = inputElement.nextElementSibling; // Target <small> for errors
			errorElement.textContent = message;
		}

		function clearError(inputElement) {
			const errorElement = inputElement.nextElementSibling;
			errorElement.textContent = '';
		}

		// Event listeners for real-time validation
		document.getElementById('firstName').addEventListener('input', function() {
			const value = this.value.trim();
			const nameRegex = /^[A-Za-z\s]+$/;
			if (!nameRegex.test(value)) {
				showError(this, 'First name cannot be empty and only contain alphabets and spaces.');
			} else {
				clearError(this);
			}
		});

		document.getElementById('difFirstName').addEventListener('input', function() {
			const value = this.value.trim();
			const nameRegex = /^[A-Za-z\s]+$/;
			if (!nameRegex.test(value)) {
				showError(this, 'First name cannot be empty and only contain alphabets and spaces.');
			} else {
				clearError(this);
			}
		});

		document.getElementById('lastName').addEventListener('input', function() {
			const value = this.value.trim();
			const nameRegex = /^[A-Za-z\s]+$/;
			if (!nameRegex.test(value)) {
				showError(this, 'Last name cannot be empty and only contain alphabets and spaces.');
			} else {
				clearError(this);
			}
		});

		document.getElementById('difLastName').addEventListener('input', function() {
			const value = this.value.trim();
			const nameRegex = /^[A-Za-z\s]+$/;
			if (!nameRegex.test(value)) {
				showError(this, 'Last name cannot be empty and only contain alphabets and spaces.');
			} else {
				clearError(this);
			}
		});

		document.getElementById('email').addEventListener('blur', function() {
			const value = this.value.trim();
			const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailRegex.test(value)) {
				showError(this, 'Please enter a valid email address (e.g., email@gmail.com).');
			} else {
				clearError(this);
			}
		});

		document.getElementById('confirmEmail').addEventListener('blur', function() {
			const emailValue = document.getElementById('email').value.trim();
			const confirmEmailValue = this.value.trim();

			if (emailValue !== confirmEmailValue) {
				showError(this, 'Confirm email must match the email address.');
			} else {
				clearError(this);
			}
		});

		// Prevent copy-paste into Confirm Email
		document.getElementById('confirmEmail').addEventListener('paste', function(e) {
			e.preventDefault();
			alert('Copy-pasting into the Confirm Email field is not allowed. Please type it manually.');
		});

		document.getElementById('phone').addEventListener('blur', function() {
			const value = this.value.trim();
			const phoneRegex = /^\+?\d+$/;
			if (!phoneRegex.test(value)) {
				showError(this, 'Please enter a valid phone number (e.g., +61481567890 or +84909090909).');
			} else {
				clearError(this);
			}
		});

		document.getElementById('difPhone').addEventListener('blur', function() {
			const value = this.value.trim();
			const phoneRegex = /^\+?\d+$/;
			if (!phoneRegex.test(value)) {
				showError(this, 'Please enter a valid phone number (e.g., +61481567890 or +84909090909).');
			} else {
				clearError(this);
			}
		});

		//initialize table
		showOrderSum();
	});
})();

