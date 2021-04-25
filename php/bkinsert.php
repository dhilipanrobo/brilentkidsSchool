<?php
include 'bkdbconfig.php';
$user_sno = $_POST["sno"];
$user_date = $_POST["date"];
$user_title = $_POST["title"];
$user_mater = $_POST["mater"];
$user_teacher = $_POST["tech"];
/*$servername = "localhost";
$name = "root";
$password = "1234";
$database = "noticeboard";*/
/*
$servername = "localhost";
$name = "adimnazd_dk";
$password = "TcNtS=%GS8nZ";
$database = "adimnazd_notice_board"; 
$con = mysqli_connect($servername,$name,$password,$database); */
$sql = "insert into srknotice (`sno`, `date`, `title`, `mater`, `teacher`) values ('".$user_sno."','".$user_date."','".$user_title."','".$user_mater."','".$user_teacher."');";
if(mysqli_query($con,$sql)){
	echo "data insert .....";
}
else {
	echo "error insert data ....";
}
mysqli_close($con);
?>