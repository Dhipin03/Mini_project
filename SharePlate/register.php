<?php

include 'init.php';
                              
 
 $name = $_POST['name'];
 $phone = $_POST['phone'];
 $address = $_POST['address'];
 $pincode = $_POST['pin'];
 $email = $_POST['email'];
 $pwd = $_POST['pwd'];
 $utype = $_POST['utype'];
 $rtype = $_POST['type'];
 $upi = $_POST['upi'];

 $Sql_Query = " INSERT INTO user( name, phone, address, pincode, email, pwd, utype, r_type, upi) VALUES ('$name','$phone','$address','$pincode','$email','$pwd','$utype','$rtype','$upi')";
 
 if(mysqli_query($con,$Sql_Query)){
 
     echo "Your Account Successfully Registered...!)";
 
 }
 else{
 
     echo 'Sorry, Try Again';
 
 }
 mysqli_close($con);
?>
