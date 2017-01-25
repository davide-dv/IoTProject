<?php
/**
 * Created by PhpStorm.
 * User: Antonio Tagliente
 * Date: 24.01.17
 * Time: 13:04
 */

require ('DBConnection.php');
require ('StandardMessages.php');

echo DBConnection::getInstance()->getJsonFromQuery(StandardMessages::EVENT_QUERY);

?>