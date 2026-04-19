// Toggles password visibility (show/hide password)
function hidepassword() {

    // Get password input field by ID
    var pass = document.getElementById("password");

    // If password is hidden, show it
    if (pass.type === "password") {
        pass.type = "text";
    } else {
        // Otherwise, hide it again
        pass.type = "password";
    }
}