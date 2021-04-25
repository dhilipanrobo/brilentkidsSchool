<?php


include 'bkdbconfig.php';


$service_key="AAAAGFN61pc:APA91bFXBO-YnSlnh4S9ot9FEzDcHck4EihxRylRo2nqmiwM7v_2uSde8Ov1gpkmpJq1il2dhnA63uS536IYsPnTknfLoXGsaooZCLH7pxHKbONr1YPLOnADL30AyuwtQz7DQeusBpee";
 $query="select * from srkfcm_info ";
$result = mysqli_query ($con,$query);
while($row=mysqli_fetch_array($result)){
 		
 		$device_to[] =  $row['fcm_token'];
 		
 			
}

 echo "no = " .sizeof($device_to);

if(isset($_POST['send_notification'])){

$title=$_POST['title'];

for($i=0 ; $i< sizeof($device_to) ; $i++)
{
//echo $to[$i];

$to = $device_to[$i];

sendPush($to,$title);
//sleep(5);
}


}
	function sendPush($to,$title)
	{
// API access key from Google API's Console
// replace API
//$service_key="AAAAGFN61pc:APA91bFXBO-YnSlnh4S9ot9FEzDcHck4EihxRylRo2nqmiwM7v_2uSde8Ov1gpkmpJq1il2dhnA63uS536IYsPnTknfLoXGsaooZCLH7pxHKbONr1YPLOnADL30AyuwtQz7DQeusBpee";
define( 'API_ACCESS_KEY', 'AAAAGFN61pc:APA91bFXBO-YnSlnh4S9ot9FEzDcHck4EihxRylRo2nqmiwM7v_2uSde8Ov1gpkmpJq1il2dhnA63uS536IYsPnTknfLoXGsaooZCLH7pxHKbONr1YPLOnADL30AyuwtQz7DQeusBpee');
		$registrationIds = array($to);
		
		

	$msg = array
	(
	'body' 	=> ' ',
	'title' => $title,
	
	'sound' => 1

// you can also add images, additionalData
);


	$fields = array
	(
		'registration_ids' 	=> $registrationIds,
			'notification'	=> $msg
);
$headers = array
(
'Authorization: key='  . API_ACCESS_KEY,
'Content-Type: application/json'
);

$ch = curl_init();
curl_setopt( $ch,CURLOPT_URL, 'https://android.googleapis.com/gcm/send' );
curl_setopt( $ch,CURLOPT_POST, true );
curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
$result = curl_exec($ch );
curl_close( $ch );

echo $result;
}


?>