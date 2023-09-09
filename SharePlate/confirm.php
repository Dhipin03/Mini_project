<?php

include 'init.php';
                              
 
 $bid = $_POST['bid'];

 $Sql_Query = "Update Bookings set bstatus='Picked' where bid='$bid'";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Item Picked..!";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
