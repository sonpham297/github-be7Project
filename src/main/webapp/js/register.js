function registerUser(event) {
	event.preventDefault(); // Prevent form submission

	let firstname = document.getElementById("firstName").value;
	let lastname = document.getElementById("lastName").value;
	let emailRegister = document.getElementById("emailRegister").value;
	let passReg = document.getElementById("passReg").value;
//	let passConfirmReg = document.getElementById("passConfirmReg").value;

	// Hash password using SHA256 before sending
	const hashedPassword = CryptoJS.SHA256(passReg).toString();

	// Create form data and submit
	let form = document.createElement("form");
	form.method = "POST";
	form.action = "RegisterServlet";

	let firstnameField = document.createElement("input");
	firstnameField.type = "hidden";
	firstnameField.name = "firstName";
	firstnameField.value = firstname;

	let lastnameField = document.createElement("input");
	lastnameField.type = "hidden";
	lastnameField.name = "lastName";
	lastnameField.value = lastname; 

	let emailRegisterField = document.createElement("input");
	emailRegisterField.type = "hidden";
	emailRegisterField.name = "emailRegister";
	emailRegisterField.value = emailRegister;

	let passRegField = document.createElement("input");
	passRegField.type = "hidden";
	passRegField.name = "passReg";
	passRegField.value = hashedPassword;// Send hashed password


	form.appendChild(firstnameField);
	form.appendChild(lastnameField);
	form.appendChild(emailRegisterField);
	form.appendChild(passRegField);
	document.body.appendChild(form);
	form.submit();
}