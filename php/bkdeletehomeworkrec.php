<?php
include 'bkdbconfig.php';
$id = $_POST["id"];
// Create connection
// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
} 
// sql to delete a record
$sql = "DELETE FROM srkhomework WHERE sno=".$id;
if ($con->query($sql) === TRUE) {
    echo "Record deleted successfully";
} else {
    echo "Error deleting record: " . $con->error;
}
$con->close();
?>