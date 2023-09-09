<?php

include 'init.php';
                              
 
 $did = $_POST['did'];
 $remains = $_POST['remains'];
 $stat = $_POST['stat'];

 $Sql_Query = "UPDATE donate SET remains='$remains',status='$stat' WHERE id='$did' ";
 
 if(mysqli_query($con,$Sql_Query)){
 
//     echo "Your Food Successfully Reserved...!";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
