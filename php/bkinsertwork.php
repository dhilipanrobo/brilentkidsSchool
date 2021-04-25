<?php
include 'bkdbconfig.php';
$user_sno = $_POST["sno"];
$user_date = $_POST["date"];
$user_subject = $_POST["subject"];
$user_class = $_POST["class"];
$user_work = $_POST["work"];
$user_teacher = $_POST["tech"];
$servername = "localhost";
$username = "adimnazd_dk";
$password = "TcNtS=%GS8nZ";
$database = "adimnazd_notice_board";


$sql = "insert into srkhomework values('".$user_sno."','".$user_date."','".$user_subject."','".$user_class."','".$user_work."','".$user_teacher."');";
if(mysqli_query($con,$sql)){
	
	echo "data insert .....";
}
else {
	
	echo "error insert data ....";
}
mysqli_close($con);

