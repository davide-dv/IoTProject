<?php

    require ('Login.php');

    $login = new Login();
    header("location: ../../Login/index.html?msg=".$login->logout());

?>