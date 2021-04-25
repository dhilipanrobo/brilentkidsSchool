<?php

$term = $_GET["term"];
include 'bkdbconfig.php';

$query="select password from srktecilog where username ="."'".$term."'";
$result=mysqli_query($con,$query);
$respones=array();
$respones1=array();
while($row=mysqli_fetch_array($result))
{
	
	
	array_push($respones,array("pass"=>$row[0]));
	
	
}


echo json_encode($respones);

?>
