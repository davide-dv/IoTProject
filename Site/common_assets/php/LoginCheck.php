<?php

require ('Login.php');

$login = new Login();

if($login->loginCheck() == true) {
    $login->writeDebugLog("Login ok");
    echo "accept";
} else {
    $login->writeDebugLog("Login wrong");
    $login->logout();
    echo "../Login/index.html";
}

?>