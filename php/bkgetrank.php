<?php
$term = $_GET["term"];
$Rno = $_GET["rno"];
include 'bkdbconfig.php';
$query="select  * from srkrankcard where term ="."'".$term."'"."AND rno="."'".$Rno."'ORDER BY sno DESC";
$result=mysqli_query($con,$query);
$respones=array();
$respones1=array();
while($row=mysqli_fetch_array($result))
{
	array_push($respones,array("tamil"=>$row[2],"english"=>$row[3],"maths"=>$row[4],"science"=>$row[5],"social"=>$row[6],"total"=>$row[7],"rank"=>$row[8]));
}
echo json_encode($respones);
?>
