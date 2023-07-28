<?php

// Connect to the database
$db = new PDO("mysql:host=localhost;dbname=my_database", "root", "");

// Get the form data
$subsc_id = $_POST["subsc_id"];
$subsc_name = $_POST["subsc_name"];
$subsc_surname = $_POST["subsc_surname"];
$msisdn = $_POST["msisdn"];
$tariff_id = $_POST["tariff_id"];
$start_date = $_POST["start_date"];

// Insert the data into the database
$sql = "INSERT INTO subscriber (subsc_id, subsc_name, subsc_surname, msisdn, tariff_id, start_date) VALUES (?, ?, ?, ?, ?, ?)";
$stmt = $db->prepare($sql);
$stmt->execute(array($subsc_id, $subsc_name, $subsc_surname, $msisdn, $tariff_id, $start_date));

// Redirect the user to the home page
header("Location: subscriber.php");

?>