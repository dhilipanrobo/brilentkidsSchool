<?php
$sno = $_GET["datee"];
$sub = $_GET["subj"];
$clase = $_GET["clas"];
include 'bkdbconfig.php';
if($sub =='All'){$query="select * from srkhomework where date ="."'".$sno."'"."AND class="."'".$clase."'";}
else{$query="select * from srkhomework where date ="."'".$sno."'"."AND subject="."'".$sub."'"."AND class="."'".$clase."'";}
//$query="select * from devihomework where date ="."'".$sno."'"."AND class="."'".$clase."'";
$result=mysqli_query($con,$query);
$respones=array();
$respones1=array();
while($row=mysqli_fetch_array($result))
{
array_push($respones,array("title"=>$row[2],"work"=>$row[4],"sno"=>$row[0]));
}
echo json_encode($respones);
?>
