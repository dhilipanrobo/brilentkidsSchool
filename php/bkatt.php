<?php
include 'bkdbconfig.php';
$user_sno = $_POST["sno"];
$user_date = $_POST["date"];
$user_subject = $_POST["rno"];
$user_class = $_POST["states"];
$user_work = $_POST["remark"];
$user_teacher = $_POST["tech"];
$servername = "localhost";
$username = "adimnazd_dk";
$password = "TcNtS=%GS8nZ";
$database = "adimnazd_notice_board";


$sql = "insert into srkattance values('".$user_sno."','".$user_date."','".$user_subject."','".$user_class."','".$user_work."','".$user_teacher."');";
if(mysqli_query($con,$sql)){
	
	echo "data insert .....";
}
else {
	
	echo "error insert data ....";
}
mysqli_close($con);

