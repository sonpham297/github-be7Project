document.addEventListener("DOMContentLoaded", function () {
    const contactForm = document.querySelector("#contactForm");
    const sendMessageButton = document.querySelector("#sendMessageButton");

    if (contactForm) {
        contactForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const name = document.querySelector("input#name").value;
            const email = document.querySelector("input#email").value;
            const subject = document.querySelector("input#subject").value;
            const message = document.querySelector("textarea#message").value;

            if (sendMessageButton) sendMessageButton.disabled = true;

            fetch("contact.php", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: new URLSearchParams({
                    name: name,
                    email: email,
                    subject: subject,
                    message: message,
                }),
            })
                .then((response) => {
                    if (response.ok) {
                        const successDiv = document.querySelector("#success");
                        if (successDiv) {
                            successDiv.innerHTML =
                                "<div class='alert alert-success'>" +
                                "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                "<strong>Your message has been sent.</strong>" +
                                "</div>";
                        }
                        contactForm.reset();
                    } else {
                        throw new Error("Network response was not ok.");
                    }
                })
                .catch((error) => {
                    const errorDiv = document.querySelector("#success");
                    if (errorDiv) {
                        errorDiv.innerHTML =
                            "<div class='alert alert-danger'>" +
                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                            `<strong>Sorry ${name}, it seems that our mail server is not responding. Please try again later!</strong>` +
                            "</div>";
                    }
                    contactForm.reset();
                })
                .finally(() => {
                    setTimeout(() => {
                        if (sendMessageButton) sendMessageButton.disabled = false;
                    }, 1000);
                });
        });

        const nameInput = document.querySelector("#name");
        const successDiv = document.querySelector("#success");

        if (nameInput && successDiv) {
            nameInput.addEventListener("focus", () => {
                successDiv.innerHTML = "";
            });
        }

        document.querySelectorAll("a[data-toggle=\"tab\"]").forEach((tab) => {
            tab.addEventListener("click", (e) => {
                e.preventDefault();
                const targetTab = document.querySelector(tab.getAttribute("href"));
                if (targetTab) {
                    targetTab.classList.add("active");
                }
            });
        });
    }
});
