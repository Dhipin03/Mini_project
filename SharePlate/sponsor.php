<?php

include 'init.php';
                              
 $spid = $_POST['sp_id'];
 $rid = $_POST['rec_id'];
 $amount = $_POST['amount'];
 $desc = $_POST['desc'];
 $date = $_POST['date'];

 $Sql_Query = " INSERT INTO sponsor(sponsor_id, receiver_id, amount, description, date) VALUES ('$spid','$rid','$amount','$desc','$date')";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Thank You For Being A Part With Us...!";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
