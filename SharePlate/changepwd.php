<?php

include 'init.php';
                              
 
 $pwd = $_POST['pwd'];
 $id = $_POST['id'];

 $Sql_Query = " UPDATE user SET pwd = '$pwd' WHERE id='$id'";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Your Password Changed Successfully...!)";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
