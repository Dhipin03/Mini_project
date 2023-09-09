<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
 $did = $_POST['did'];
 $sql = "SELECT * FROM donate where donor_id='$did' order by id desc";

$res = $con->query($sql);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'name' => $row['item_name'],
            'type' => $row['food_type'],
            'status' => $row['status'],
            'count' => $row['qty'],
            'date' => $row['date'],
            'remains' => $row['remains'],
            'id' => $row['id']


                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
