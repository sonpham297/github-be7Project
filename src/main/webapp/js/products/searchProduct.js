(function() {
	"use strict";


	document.addEventListener("DOMContentLoaded", function() {
		const searchForm = document.querySelector("#searchForm");
		const searchInput = document.querySelector("#searchInput");
		const invalidSearchPopup = document.querySelector("#invalidSearchPopup");
		const invalidSearchButton = document.querySelector("#invalidSearchButton");
		
		if (searchForm && searchInput) {
			searchForm.addEventListener("submit", function(e) {
				e.preventDefault(); // Prevent form submission default behavior

				const searchQuery = searchInput.value.trim();
				if (!isValidSearchTerm(searchQuery)) {
					invalidSearchPopup.style.display = "flex";
					return; // Stop the search if the input is invalid
				}

				// Manually construct the URL
				const baseUrl = searchForm.action; // Get the form's action URL (search.html)
				const url = `${baseUrl}?search=${encodeURIComponent(searchQuery)}`; // Correctly encode

				// Navigate to the constructed URL
				window.location.href = url;

			});

			function isValidSearchTerm(searchQuery) {
				if (searchQuery === "") return true; // Allow empty search

				// Regular expression to allow letters (a-z, A-Z), numbers (0-9), and spaces
				const regex = /^[a-zA-Z0-9\s]*$/;

				return regex.test(searchQuery);
			}
		}

		invalidSearchButton.addEventListener("click", function() {
			invalidSearchPopup.style.display = "none"; // Hide popup
		});

		window.addEventListener("click", function(event) {
			if (event.target === invalidSearchPopup) {
				invalidSearchPopup.style.display = "none";
			}
		});

	});
})();

