function loginUser(event) {
	event.preventDefault();

	let emailLogin = document.getElementById("emailLogin").value;
	let password = document.getElementById("password").value;

	// Hash password using SHA256 before sending
	const hashedPassword = CryptoJS.SHA256(password).toString();

	// Create form data and submit
	let form = document.createElement("form");
	form.method = "POST";
	form.action = "LoginServlet";

	let emailField = document.createElement("input");
	emailField.type = "hidden";
	emailField.name = "email";
	emailField.value = emailLogin;

	let passField = document.createElement("input");
	passField.type = "hidden";
	passField.name = "password";
	passField.value = hashedPassword;

	form.appendChild(emailField);
	form.appendChild(passField);
	document.body.appendChild(form);
	form.submit();
}
