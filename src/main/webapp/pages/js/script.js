function hidepassword(){
    var pass = document.getElementById("password");
    if (pass.type === "password") {
        pass.type = "text";
    }else {
        pass.type = "password"
    }
}


function validateLoginForm() {
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    const emailError = document.getElementById("emailError");
    const passwordError = document.getElementById("passwordError");
    const loginError = document.getElementById("loginError");

    emailError.textContent = "";
    passwordError.textContent = "";
    loginError.textContent = "";

    let isValid = true;

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (email === "") {
        emailError.textContent = "Please enter your email.";
        isValid = false;
    } else if (!emailPattern.test(email)) {
        emailError.textContent = "Please enter a valid email address.";
        isValid = false;
    }

    if (password === "") {
        passwordError.textContent = "Please enter your password.";
        isValid = false;
    } else if (password.length < 6) {
        passwordError.textContent = "Password must be at least 6 characters long.";
        isValid = false;
    }

    if (!isValid) {
        return false;
    }

    // Demo login check only
    // Replace this later with real backend/database validation
    const validEmail = "test@example.com";
    const validPassword = "password123";

    if (email !== validEmail || password !== validPassword) {
        loginError.textContent = "Incorrect email or password.";
        return false;
    }

    return true;
}