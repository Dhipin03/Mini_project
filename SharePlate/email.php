<?php

include 'init.php';
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $email = $_POST['email'];

 ///echo $email." ".$password;
 $sql = "select * from user where email='$email'";

 $check = mysqli_fetch_array(mysqli_query($con,$sql));

 if(isset($check)){
 	//echo "1";
	$data[]=$check;
	echo json_encode($data);
 }
 else{
 	echo "1";
 }

 }
 else{
 echo "error try again";
 }
 
?>
