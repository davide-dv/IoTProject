<?php

require ('Login.php');

$login = new Login();

$login->sec_session_start();


if(isset($_POST['username'], $_POST['password'])) {
    $username = $_POST['username'];
    $password = $_POST['password'];

    if($login->login($username, $password) == true) {
        // Login eseguito
        $login->writeDebugLog("Login Effettuato");
        header('Location: ../../Home/index.html');
    } else {
        // Login fallito
        $login->sec_session_start();
        // Elimina tutti i valori della sessione.
        $_SESSION = array();
        // Recupera i parametri di sessione.
        $params = session_get_cookie_params();
        // Cancella i cookie attuali.
        setcookie(session_name(), '', time() - 42000, $params["path"], $params["domain"], $params["secure"], $params["httponly"]);
        // Cancella la sessione.
        session_destroy();
        $login->writeDebugLog("Login Fallito");
        header('Location: ../../Login/index.html');
    }
} else {
    // Le variabili corrette non sono state inviate a questa pagina dal metodo POST.
    echo 'Invalid Request';
}


?>
