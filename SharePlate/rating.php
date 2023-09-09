<?php

include 'init.php';
                              
 
 $uid = $_POST['uid'];
 $rate = $_POST['rate'];
 $feedback = $_POST['feed'];
 

 $Sql_Query = "INSERT INTO rating( uid,rate,feedback) VALUES ('$uid','$rate','$feedback')";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Your Rating Submitted Successfully...!)";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
