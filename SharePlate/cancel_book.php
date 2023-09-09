<?php

include 'init.php';
                              
 
 $bid = $_POST['bid'];

 $Sql_Query = "Update Bookings set bstatus='Cancelled' where bid='$bid'";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Your Booking Cancelled Successfully..!";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
