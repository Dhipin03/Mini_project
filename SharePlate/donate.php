<?php

include 'init.php';
                              
 
 $type = $_POST['type'];
 $dietary = $_POST['dietary'];
 $itemname = $_POST['itemname'];
 $location = $_POST['location'];
 $contact = $_POST['contact'];
 $qty = $_POST['qty'];
 $did = $_POST['did'];
 $stat = $_POST['status'];
 $date = $_POST['date'];
 $time = $_POST['time'];
 $remains = $_POST['remains'];

 $Sql_Query = " INSERT INTO donate(food_type, dietary, item_name, qty, location, contact, donor_id,status,date,time,remains) VALUES ('$type','$dietary','$itemname','$qty','$location','$contact','$did','$stat','$date','$time','$remains')";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Thank You For Being A Part With Us...!";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
