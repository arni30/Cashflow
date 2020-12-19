/**
 * shows field for email
 */
$(function() {
    $(".forgot-button").click(function() {
        $(".forgot-form").toggleClass("hidden");
    });
});

/**
 * shows eye animation in passwords fields
 */
$(".toggle-password").click(function() {
    $(this).toggleClass("fa-eye fa-eye-slash");
    let input = $($(this).attr("toggle"));
    if (input.attr("type") === "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});

/**
 * shows email confirmation window
 */
// function showConfWindow() {
    // document.querySelector('.nav').setAttribute('class', 'nav nav-up');
    // document.querySelector('.form-signup-left')
    //     .setAttribute('class', 'form-signup-left form-signup-down');
    // document.querySelector('.success').setAttribute('class', 'success success-left');
    // document.querySelector('.frame').setAttribute('class', 'frame frame-short');
    // document.querySelector('#check').setAttribute('class', 'checked');
// }

/**
 * check passwords match and prevents from submit if they are different
 */
let match = false;
function checkPass() {
    let password = $("#password").val();
    let confirmPassword = $("#confirmpassword").val();
    match = (password === confirmPassword);
}
$(document).ready(function () {
    $("#confirmpassword").keyup(checkPass);
});
function checkPasswordMatch() {
    if (match) {
        // showConfWindow();
        return true;
    } else {
        $("#confirmpassword").val('Passwords do not match!');
        // alert('Passwords do not match!');
        return false;
    }
}
