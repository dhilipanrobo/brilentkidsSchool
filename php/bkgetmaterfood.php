<?php
include 'bkdbconfig.php';
$sno = $_GET["datee"];
//$connection=mysqli_connect('localhost','root','1234','noticeboard');
// $connection=mysqli_connect('localhost','adimnazd_dk','TcNtS=%GS8nZ','adimnazd_notice_board');
$xxx=5;
if(! $con ) {
die('Could not connect: ' . mysql_error());
}
$query="select food from srkfood where sno =".$sno;
$result=mysqli_query($con,$query);
$respones=array();
$respones1=array();
while($row=mysqli_fetch_array($result))
{
	array_push($respones,array($row[0]));
}
echo json_encode($respones);
?>

