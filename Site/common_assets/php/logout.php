<?php

    require ('Login.php');

    $login = new Login();
    header("location: ../../Login/login.html?msg=".$login->logout());

?>