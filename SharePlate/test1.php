<?php

include 'init.php';
                              
 
 $name = $_POST['name'];
 $phone = $_POST['phone'];

 $education = $_POST['education'];
 $email = $_POST['email'];


 $Sql_Query = " INSERT INTO Test(name, phone, eduction, email) VALUES ('$name','$phone','$education','$email')";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo " Successfully Registered...!)";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
