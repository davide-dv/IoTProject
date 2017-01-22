<?php

if(!defined("DATABASE_VALUE")) {
  define("SERVER_NAME", "localhost");
  define("USER_NAME","root");
  define("PASSWORD","");
  define("LOGIN_TABLE", "login");
  define("ATTEMPS_TABLE","logattemps");
  define("WRONG_ACCESS", "INSERT INTO " . ATTEMPS_TABLE . " (username, time) VALUES (?, ?)");
  define("GET_USER_QUERY","SELECT * FROM ". LOGIN_TABLE ." WHERE (username=?)");
  define("GET_ATTEMPS_QUERY","SELECT time FROM  WHERE (username=?) AND time > ?");
  define("DATABASE","jarvis_db");
  define("EMAIL","antoniotagliente@aol.com");
  define("DATABASE_VALUE","DB_OK");
}

class DBConnection
{
    private $_connection = null;
    private static $_instance = null;

    private function __construct()
    {
        echo '<script>console.log("Your stuff here")</script>';
        $string = "";
        // Create connection
        $mysqli = new mysqli(SERVER_NAME, USER_NAME, PASSWORD, DATABASE);
        $this->_connection = $mysqli;
        // Check connection
        if ($mysqli ->connect_error) {
            $string = "Connection failed: " . $mysqli->connect_error;
            die($string);
            throw new Exception($string);
        }
        if (!syslog( LOG_DEBUG, $string)) {
            throw  new Exception("Error - Not save into syslog: ".$string);
        }
    }

    public function getConnection(){
      return $this->_connection;
    }

    public static function getInstance() {
        if(self::$_instance == null) {
            self::$_instance = new dbConnection();
        }
        return self::$_instance;
    }

    public function destructInstance() {
        $this->_connection->close();
        $this->_connection = null;
    }

    /*
      Return the current user with mail and cripted password
    */
    public function getUser($username) {
        $mysqli = $this->_connection;
        if (!$stmt = $mysqli->prepare(GET_USER_QUERY)) {
            $string = "Prepare failed: (" . $mysqli->errno . ") " . $mysqli->error;
            error_log($string, 0);
            throw new Exception($string);
        } else {
            if (!$stmt->bind_param("s", $username)) {
                $string = "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
                error_log($string, 0);
                throw new Exception($string);
            } else {
                if (!$stmt->execute()) {
                    $string = "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
                    error_log($string, 0);
                    throw new Exception($string);
                }
            }
        }
        if (!($result = $stmt->get_result())) {
            $string = "Getting result set failed: (" . $stmt->errno . ") " . $stmt->error;
            error_log($string, 0);
            throw new Exception($string);
        }
        return $result;
    }

    /**
     * Private clone method to prevent cloning of the instance of the
     * *Singleton* instance.
     *
     * @return void
     */
    private function __clone()
    {
    }

    /**
     * Private unserialize method to prevent unserializing of the *Singleton*
     * instance.
     *
     * @return void
     */
    private function __wakeup()
    {
    }

    public function sendEmail($email) {
        $headers = "From: ". EMAIL . "\r\n" .
            "Reply-To: " . EMAIL . "\r\n" .
            'X-Mailer: PHP/' . phpversion();
        mail($email, 'Security message', StandardMessages::ALERT_MAIL, $headers);
    }

    public function insertWrongAccess($username)
    {
        $now = time();
        if($stmt = $this->_connection->prepare(WRONG_ACCESS)) {
            $stmt->bind_param('ss', $username, $now);
            $stmt->execute();
        }

    }

    public function checkbrute($username) {
        $now = time();
        $valid_attempts = $now - (2 * 60 * 60); //last 2 hours
        if ($stmt = $this->_connection->prepare("SELECT time FROM " . ATTEMPS_TABLE ." WHERE (username=?) AND time > ".$valid_attempts)) {
            $stmt->bind_param('s', $username);
            $stmt->execute();
            $stmt->store_result();
            if($stmt->num_rows > 5) {
                $this->sendEmail(EMAIL);
                return true;
            } else {
                return false;
            }
        }
    }
}
