

let billingAutocomplete, shippingAutocomplete;

function initAutocomplete() {
	const manualCheckbox = document.getElementById('manual');

	// Billing Address Fields
	const billingAddressInput = document.getElementById("address");
	const billingCountrySelect = document.getElementById("country");

	// Shipping Address Fields
	const shippingAddressInput = document.getElementById("difAddress");
	const shippingCountrySelect = document.getElementById("difCountry");

	if (!billingAddressInput || !billingCountrySelect || !shippingAddressInput || !shippingCountrySelect) {
		console.error("One or more address input fields are missing!");
		return;
	}

	// Initialize Google Places Autocomplete for Billing Address
	billingAutocomplete = new google.maps.places.Autocomplete(billingAddressInput, {
		types: ['geocode'],
		componentRestrictions: { country: billingCountrySelect.value }
	});

	// Initialize Google Places Autocomplete for Shipping Address
	shippingAutocomplete = new google.maps.places.Autocomplete(shippingAddressInput, {
		types: ['geocode'],
		componentRestrictions: { country: shippingCountrySelect.value }
	});

	// Update billing autocomplete when country changes
	billingCountrySelect.addEventListener("change", function() {
		billingAutocomplete.setComponentRestrictions({ country: billingCountrySelect.value });
		//		billingAddressInput.value = "";
		clearBillingFields();
	});

	// Update shipping autocomplete when country changes
	shippingCountrySelect.addEventListener("change", function() {
		shippingAutocomplete.setComponentRestrictions({ country: shippingCountrySelect.value });
		//		shippingAddressInput.value = "";
		clearShippingFields();
	});

	// Handle Billing Address Selection
	billingAutocomplete.addListener("place_changed", function() {
		fillAddressFields(billingAutocomplete, "billing");
	});

	// Handle Shipping Address Selection
	shippingAutocomplete.addListener("place_changed", function() {
		fillAddressFields(shippingAutocomplete, "shipping");
	});

	// Handle "Ship to Different Address" Checkbox
	const shipToCheckbox = document.getElementById("shipto");
	const shippingSection = document.getElementById("shipping-address");

	// Toggle between Google Autocomplete & Manual Entry
	manualCheckbox.addEventListener('change', function() {
		if (manualCheckbox.checked) {
			disableAutocomplete(billingAutocomplete);
		} else {
			enableAutocomplete();
		}
	});

	//Toggle between ship to same or diferent address
	shipToCheckbox.addEventListener("change", function() {
		if (shipToCheckbox.checked) {
			shippingSection.classList.add("show"); // Show shipping section
			clearShippingFields(); // Clear previous values for new entry
		} else {
			shippingSection.classList.remove("show"); // Hide shipping section
			copyBillingToShipping(); // Copy billing address if unchecked
		}
	});
}

// Fill Address Fields from Google API
function fillAddressFields(autocomplete, type) {
	const countrySelect = document.getElementById('country');
	const addressInput = document.getElementById('address');
	const difCountrySelect = document.getElementById('difCountry');
	const difAddressInput = document.getElementById('difAddress');
	const place = autocomplete.getPlace();
	if (!place.geometry) {
		console.log("No details available for the selected place.");
		return;
	}

	if (countrySelect.value === "AU" && type === "billing" || difCountrySelect.value === "AU" && type === "shipping") {

		let addressFields;
		if (type === "billing") {
			addressFields = {
				address: document.getElementById("address"),
				city: document.getElementById("city"),
				state: document.getElementById("state"),
				postcode: document.getElementById("postcode"),
			};
		} else {
			addressFields = {
				address: document.getElementById("difAddress"),
				city: document.getElementById("difCity"),
				state: document.getElementById("difState"),
				postcode: document.getElementById("difPostcode"),
			};
		}

		let unitNumber = "";
		let street = "";
		let city = "";
		let state = "";
		let postcode = "";

		place.address_components.forEach((component) => {
			const types = component.types;
			if (types.includes("subpremise")) {
				unitNumber = component.long_name;
			}
			if (types.includes("street_number")) {
				if (!unitNumber)
					street = component.long_name + " " + street;
				else
					street = unitNumber + "/" + component.long_name + " " + street;
			}
			if (types.includes("route")) {
				street += component.long_name;
			}
			if (types.includes("locality")) {
				city = component.long_name;
			}
			if (types.includes("administrative_area_level_1")) {
				state = component.long_name;
			}
			if (types.includes("postal_code")) {
				postcode = component.long_name;
			}
		});

		// Populate fields
		addressFields.address.value = street;
		addressFields.city.value = city;
		addressFields.state.value = state;
		addressFields.postcode.value = postcode;

		console.log(`Selected ${type} Address:`, {
			street,
			city,
			state,
			postcode,
		});
	} else {
		if (type === "billing")
			console.log("Billing Address Input: ", addressInput.value);
		else
			console.log("Shipping Address Input: ", difAddressInput.value);

	}
}

//Copy Billing Address to Shipping
function copyBillingToShipping() {
	document.getElementById("difAddress").value = document.getElementById("address").value;
	document.getElementById("difCity").value = document.getElementById("city").value;
	document.getElementById("difState").value = document.getElementById("state").value;
	document.getElementById("difPostcode").value = document.getElementById("postcode").value;
}

//Clear Billing Fields
function clearBillingFields() {
	document.getElementById("address").value = "";
	document.getElementById("city").value = "";
	document.getElementById("state").value = "";
	document.getElementById("postcode").value = "";

}//Clear Shipping Fields
function clearShippingFields() {
	document.getElementById("difAddress").value = "";
	document.getElementById("difCity").value = "";
	document.getElementById("difState").value = "";
	document.getElementById("difPostcode").value = "";
}

// Disable Google Autocomplete (for manual entry)
function disableAutocomplete(autocomplete) {
	const addressInput = document.getElementById('address');
	const cityInput = document.getElementById('city');
	const stateInput = document.getElementById('state');
	const postcodeInput = document.getElementById('postcode');
	addressInput.value = ""; // Clear input field
	cityInput.value = "";
	stateInput.value = "";
	postcodeInput.value = "";
	google.maps.event.clearInstanceListeners(autocomplete); // Remove event listeners
	addressInput.setAttribute("autocomplete", "off"); // Prevent browser autofill
	addressInput.setAttribute("autocorrect", "off");
	addressInput.setAttribute("autocapitalize", "off");
	addressInput.setAttribute("spellcheck", "false");
	addressInput.placeholder = "Enter your address manually...";
	addressInput.readOnly = false; // Allow manual typing
	// Prevent Google suggestions from showing
	setTimeout(() => addressInput.blur(), 0);
}

// Enable Google Autocomplete
function enableAutocomplete() {
	document.getElementById('address').placeholder = "Start typing your address...";
	initAutocomplete();
	document.getElementById('address').readOnly = false; // Ensure input is editable
}

// Make function globally available
window.initAutocomplete = initAutocomplete;



/*
let autocomplete;
const shipTo = document.getElementById("shipTo");
const unitInput = document.getElementById('unit'); // Add unit input
const addressInput = document.getElementById('address');
const cityInput = document.getElementById('city');
const stateInput = document.getElementById('state');
const postcodeInput = document.getElementById('postcode');
const countrySelect = document.getElementById('country');

function initAutocomplete() {
	const manualCheckbox = document.getElementById('manual');
	const unitInput = document.getElementById('unit'); // Add unit input
	const addressInput = document.getElementById('address');
	const cityInput = document.getElementById('city');
	const stateInput = document.getElementById('state');
	const postcodeInput = document.getElementById('postcode');
	const countrySelect = document.getElementById('country');

	if (!addressInput || !unitInput || !countrySelect || !cityInput || !stateInput || !postcodeInput) {
		console.error("One or more input fields are missing!");
		return;
	}

	// Initialize Google Places Autocomplete
	autocomplete = new google.maps.places.Autocomplete(addressInput, {
		types: ['geocode'],
		componentRestrictions: { country: countrySelect.value }
	});

	// Update restriction when country is changed
	countrySelect.addEventListener('change', function() {
		autocomplete.setComponentRestrictions({ country: countrySelect.value });
		addressInput.value = ""; // Clear input when changing country
		unitInput.value = "";
		cityInput.value = "";
		stateInput.value = "";
		postcodeInput.value = "";
	});

	// Listen for address selection
	autocomplete.addListener('place_changed', function() {
		const place = autocomplete.getPlace();

		if (!place.geometry) {
			console.log("No details available for the selected place.");
			return;
		}

		let fullAddress = place.formatted_address; //Use name first, fallback to formatted_address
		if (countrySelect.value === "AU") {

			let unitNumber = "";
			let street = "";
			let city = "";
			let state = "";
			let postcode = "";

			// Loop through address components
			place.address_components.forEach(component => {
				const types = component.types;
				if (types.includes("subpremise")) {
					unitNumber = component.long_name; // Flat/unit number
				}
				if (types.includes("street_number")) {
					street = component.long_name + " " + street; // Street number
				}
				if (types.includes("route")) {
					street += component.long_name; // Street name
				}
				if (types.includes("locality")) {
					city = component.long_name; // City
				}
				if (types.includes("administrative_area_level_1")) {
					state = component.long_name; // State
				}
				if (types.includes("postal_code")) {
					postcode = component.long_name; // Postcode
				}
			});

			// If unit number is missing, extract it from the full address
			//			if (!unitNumber && fullAddress.includes("/")) {
			//				console.log("type.include subpremise doesn't work!")
			//				unitNumber = fullAddress.split(" ")[0]; // Takes the first part (e.g., "3/7")
			//			}

			// Populate the fields
			unitInput.value = unitNumber;
			addressInput.value = street;
			cityInput.value = city;
			stateInput.value = state;
			postcodeInput.value = postcode;

			console.log("Selected Address:", {
				unit: unitInput.value, // User-entered
				street,
				city,
				state,
				postcode,
				fullAddress
			});
		} else {
			console.log("addressInput: ", addressInput.value);
		}

	});

	// Toggle between Google Autocomplete & Manual Entry
	manualCheckbox.addEventListener('change', function() {
		if (manualCheckbox.checked) {
			disableAutocomplete();
		} else {
			enableAutocomplete();
		}
	});
}

// Disable Google Autocomplete (for manual entry)
function disableAutocomplete() {
	const unitInput = document.getElementById('unit'); // Add unit input
	const addressInput = document.getElementById('address');
	const cityInput = document.getElementById('city');
	const stateInput = document.getElementById('state');
	const postcodeInput = document.getElementById('postcode');
	addressInput.value = ""; // Clear input field
	unitInput.value = "";
	cityInput.value = "";
	stateInput.value = "";
	postcodeInput.value = "";
	google.maps.event.clearInstanceListeners(autocomplete); // Remove event listeners
	addressInput.setAttribute("autocomplete", "off"); // Prevent browser autofill
	addressInput.setAttribute("autocorrect", "off");
	addressInput.setAttribute("autocapitalize", "off");
	addressInput.setAttribute("spellcheck", "false");
	addressInput.placeholder = "Enter your address manually...";
	addressInput.readOnly = false; // Allow manual typing
	// Prevent Google suggestions from showing
	setTimeout(() => addressInput.blur(), 0);
}

// Enable Google Autocomplete
function enableAutocomplete() {
	document.getElementById('address').placeholder = "Start typing your address...";
	initAutocomplete();
	document.getElementById('address').readOnly = false; // Ensure input is editable
}

// Make function globally available
window.initAutocomplete = initAutocomplete;


document.addEventListener("DOMContentLoaded", function() {
	// Toggle between Google Autocomplete & Manual Entry
	shipTo.addEventListener('change', function() {
		if (shipTo.checked) {
			disableAutoCompleteShipTo();
		} else {
			enableAutoCompleteShipTo();
		}
	});

	function enableAutoCompleteShipTo() {
		const unitInput = document.getElementById('unit'); // Add unit input
		const addressInput = document.getElementById('address');
		const cityInput = document.getElementById('city');
		const stateInput = document.getElementById('state');
		const postcodeInput = document.getElementById('postcode');
		const countrySelect = document.getElementById('country');

		if (!addressInput || !unitInput || !countrySelect || !cityInput || !stateInput || !postcodeInput) {
			console.error("One or more input fields are missing!");
			return;
		}

		// Initialize Google Places Autocomplete
		autocomplete = new google.maps.places.Autocomplete(addressInput, {
			types: ['geocode'],
			componentRestrictions: { country: countrySelect.value }
		});

		// Update restriction when country is changed
		countrySelect.addEventListener('change', function() {
			autocomplete.setComponentRestrictions({ country: countrySelect.value });
			addressInput.value = ""; // Clear input when changing country
			unitInput.value = "";
			cityInput.value = "";
			stateInput.value = "";
			postcodeInput.value = "";
		});

		// Listen for address selection
		autocomplete.addListener('place_changed', function() {
			const place = autocomplete.getPlace();

			if (!place.geometry) {
				console.log("No details available for the selected place.");
				return;
			}

			let fullAddress = place.formatted_address; //Use name first, fallback to formatted_address
			if (countrySelect.value === "AU") {

				let unitNumber = "";
				let street = "";
				let city = "";
				let state = "";
				let postcode = "";

				// Loop through address components
				place.address_components.forEach(component => {
					const types = component.types;
					if (types.includes("subpremise")) {
						unitNumber = component.long_name; // Flat/unit number
					}
					if (types.includes("street_number")) {
						street = component.long_name + " " + street; // Street number
					}
					if (types.includes("route")) {
						street += component.long_name; // Street name
					}
					if (types.includes("locality")) {
						city = component.long_name; // City
					}
					if (types.includes("administrative_area_level_1")) {
						state = component.long_name; // State
					}
					if (types.includes("postal_code")) {
						postcode = component.long_name; // Postcode
					}
				});

				// If unit number is missing, extract it from the full address
				//			if (!unitNumber && fullAddress.includes("/")) {
				//				console.log("type.include subpremise doesn't work!")
				//				unitNumber = fullAddress.split(" ")[0]; // Takes the first part (e.g., "3/7")
				//			}

				// Populate the fields
				unitInput.value = unitNumber;
				addressInput.value = street;
				cityInput.value = city;
				stateInput.value = state;
				postcodeInput.value = postcode;

				console.log("Selected Address:", {
					unit: unitInput.value, // User-entered
					street,
					city,
					state,
					postcode,
					fullAddress
				});
			} else {
				console.log("addressInput: ", addressInput.value);
			}

		});

		// Toggle between Google Autocomplete & Manual Entry
		manualCheckbox.addEventListener('change', function() {
			if (manualCheckbox.checked) {
				disableAutocomplete();
			} else {
				enableAutocomplete();
			}
		});

	}

	function disableAutoCompleteShipTo() {

	}

})();
*/




/*
function initAutocomplete() {

	const unitInput = document.getElementById('unit'); // Add unit input
	const addressInput = document.getElementById('address');
	const cityInput = document.getElementById('city');
	const stateInput = document.getElementById('state');
	const postcodeInput = document.getElementById('postcode');
	const countrySelect = document.getElementById('country');

	if (!addressInput || !unitInput || !countrySelect || !cityInput || !stateInput || !postcodeInput) {
		console.error("One or more input fields are missing!");
		return;
	}

	// Initialize Google Places Autocomplete
	const autocomplete = new google.maps.places.Autocomplete(addressInput, {
		types: ['geocode'],
		componentRestrictions: { country: countrySelect.value }
	});

	// Update country restriction when the user selects a new country
	countrySelect.addEventListener('change', function() {
		autocomplete.setComponentRestrictions({ country: countrySelect.value });
		addressInput.value = ""; // Clear input when changing country
		unitInput.value = "";
		cityInput.value = "";
		stateInput.value = "";
		postcodeInput.value = "";
	});

	// Listen for address selection
	autocomplete.addListener('place_changed', function() {

		const place = autocomplete.getPlace();

		if (!place.geometry) {
			console.log("No details available for the selected place.");
			return;
		}

		let fullAddress = place.name || place.formatted_address; //Use name first, fallback to formatted_address
		if (countrySelect.value === "AU") {

			let unitNumber = "";
			let street = "";
			let city = "";
			let state = "";
			let postcode = "";

			// Loop through address components
			place.address_components.forEach(component => {
				const types = component.types;
				if (types.includes("subpremise")) {
					unitNumber = component.long_name; // Flat/unit number
				}
				if (types.includes("street_number")) {
					street = component.long_name + " " + street; // Street number
				}
				if (types.includes("route")) {
					street += component.long_name; // Street name
				}
				if (types.includes("locality")) {
					city = component.long_name; // City
				}
				if (types.includes("administrative_area_level_1")) {
					state = component.long_name; // State
				}
				if (types.includes("postal_code")) {
					postcode = component.long_name; // Postcode
				}
			});

			// If unit number is missing, extract it from the full address
			if (!unitNumber && fullAddress.includes("/")) {
				console.log("type.include subpremise doesn't work!")
				unitNumber = fullAddress.split(" ")[0]; // Takes the first part (e.g., "3/7")
			}

			// Populate the fields
			unitInput.value = unitNumber;
			addressInput.value = street;
			cityInput.value = city;
			stateInput.value = state;
			postcodeInput.value = postcode;

			// Notify the user to enter the unit number manually
			//			unitInput.placeholder = "Enter your unit/flat number (if applicable)";
			//			unitInput.focus();


			console.log("Selected Address:", {
				unit: unitInput.value, // User-entered
				street,
				city,
				state,
				postcode,
				fullAddress
			});
		} else {
			console.log("Full Address:", fullAddress);
		}



	});
}

//Ensure function is globally available
window.initAutocomplete = initAutocomplete;

*/