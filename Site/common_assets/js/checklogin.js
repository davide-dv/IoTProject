window.addEventListener('load', function () {
    $.get( "../common_assets/php/LoginCheck.php", function(data) {
        if (data != "accept") location.replace(data);
    });
})
