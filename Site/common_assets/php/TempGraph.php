<?php
/**
 * Created by PhpStorm.
 * User: Antronio Tagliente
 * Date: 24.01.17
 * Time: 13:04
 */

require ('DBConnection.php');
require ('StandardMessages.php');

echo DBConnection::getInstance()->getDataGraphFromQuery(StandardMessages::TEMP_HEADER, StandardMessages::TEMP_QUERY);

?>