<?php

include 'init.php';
                              
 
 $d_id = $_POST['d_id'];
 $r_id = $_POST['r_id'];
 $qty = $_POST['qty'];
 $stat = $_POST['stat'];

 $Sql_Query = "INSERT INTO Bookings(donation_id, receiver_id, qty, bstatus) VALUES ('$d_id','$r_id','$qty','$stat') ";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Your Food Successfully Reserved...!";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
