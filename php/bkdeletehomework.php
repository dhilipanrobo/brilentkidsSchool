<?php
include 'bkdbconfig.php';
//$connection=mysqli_connect('localhost','root','1234','noticeboard');
$techh = $_GET["tech"];
if(! $con ) {
die('Could not connect: ' . mysql_error());
}
$query="select * from srkhomework where teacher = "."'".$techh."'"."ORDER BY sno desc;";
$result=mysqli_query($con,$query);
$respones=array();
$respones1=array();
while($row=mysqli_fetch_array($result))
{
	array_push($respones,array("title"=>$row[2],"datee"=>$row[1],"sno"=>$row[0],"cl"=>$row[3]));
	}
echo json_encode($respones);
?>

