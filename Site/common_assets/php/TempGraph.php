<?php
/**
 * Created by PhpStorm.
 * User: Antonio Tagliente
 * Date: 24.01.17
 * Time: 13:04
 */

require ('DBConnection.php');
require ('StandardMessages.php');

$headers = array(array("Date","string"), array("Value","number"));

echo DBConnection::getInstance()->getDataGraphFromQuery($headers, StandardMessages::TEMP_QUERY);

?>