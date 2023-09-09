<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
 
 $sql = "SELECT * FROM user Where utype='donor' ";

$res = $con->query($sql);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'name' => $row['name'],
            'email' => $row['email'],
            'phone' => $row['phone']
          
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
