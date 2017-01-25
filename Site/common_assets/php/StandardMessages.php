<?php

abstract class StandardMessages
{
    const USER_WRONG = "Lo user non esiste";
    const PW_WRONG = "La password non corrisponde";
    const MAX_ATTEPS = "Tentativi massimi raggiunti, l'evento sarà notificato all'amministratore";
    const SUCCESS_REDIRECT = "../../../Home/index.html";
    const ALERT_MAIL = "Sono stati superati 5 tentativi d'accesso al account: ";
    const INSUCCESS_REDIRECT = '../Login/index.html';
    const SESSION_ID = "sec_session_id";
    const PASS_KEY = "0p1a2n3t4o5m6i7m8a9";
    const TEMP_QUERY = "SELECT * FROM `temperature` WHERE 1";
    const EVENT_QUERY = "SELECT date, typology, note FROM `events` WHERE 1";
}