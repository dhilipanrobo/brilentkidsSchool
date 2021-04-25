<?php
include 'bkdbconfig.php';
$user_sno = $_POST["sno"];
$user_date = $_POST["date"];
$user_title = $_POST["title"];
$user_food = $_POST["food"];
$sql = "insert into srkfood values('".$user_sno."','".$user_date."','".$user_title."','".$user_food."');";
if(mysqli_query($con,$sql)){
	echo "data insert .....";
}
else {
	echo "error insert data ....";
}
mysqli_close($con);
?>