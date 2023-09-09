<?php

include 'init.php';
                              
 
 $name = $_POST['name'];
 $phone = $_POST['phone'];
 $address = $_POST['address'];
 $pincode = $_POST['pin'];
 $email = $_POST['email'];
 $id = $_POST['id'];

 $Sql_Query = " UPDATE user SET name = '$name', phone='$phone',address='$address',pincode='$pincode',email='$email' WHERE id='$id'";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Your Profile Changed Successfully...!)";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
