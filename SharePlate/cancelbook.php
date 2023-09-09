<?php

include 'init.php';
                              
 
 $did = $_POST['bid'];

 $Sql_Query = "UPDATE Bookings SET bstatus='Cancelled' WHERE bid='$did' ";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Your Booking Cancelled Successfully...!";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
