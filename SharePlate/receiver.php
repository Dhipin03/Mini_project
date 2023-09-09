<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
 $rtype = $_POST['type'];
 $sql = "SELECT * FROM user where r_type='$rtype'";

$res = $con->query($sql);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'id' => $row['id'],
            'name' => $row['name'],
            'address' => $row['address'],
            'pincode' => $row['pincode'],
            'email' => $row['email'],
            'phone' => $row['phone'],
            'upi' => $row['upi']
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
