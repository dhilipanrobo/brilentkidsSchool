<?php
include 'bkdbconfig.php';
$fcm_token = $_POST["fcm_token"];
$fcm_imi = $_POST["imi"];
/*$servername = "localhost";
$name = "root";
$password = "1234";
$database = "noticeboard";
*/
// $servername = "localhost";
// $name = "adimnazd_dk";
// $password = "TcNtS=%GS8nZ";
// $database = "adimnazd_notice_board";
// $con = mysqli_connect($servername,$name,$password,$database);
$sql = "insert into srkfcm_info values('".$fcm_token."','','".$fcm_imi."');";
if(mysqli_query($con,$sql)){
	echo "data insert .....";
}
else {
	echo "error insert data ....";
}
mysqli_close($con);