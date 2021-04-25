<?php
/* $connection=mysqli_connect('localhost','adimnazd_dk','TcNtS=%GS8nZ','adimnazd_notice_board');

if(! $connection ) {
die('Could not connect: ' . mysql_error());
}
*/
include 'bkdbconfig.php';

$query="select srkclasss from srkclass ORDER BY sno asc;";
$result=mysqli_query($con,$query);
$respones=array();
$respones1=array();
while($row=mysqli_fetch_array($result))
{
	
	
	array_push($respones,array("class"=>$row[0]));
	
	
}


echo json_encode($respones);

?>

