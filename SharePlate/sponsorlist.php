<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
 $did = $_POST['did'];
 $sql = "SELECT * FROM sponsor,user  WHERE sponsor.receiver_id=user.id AND sponsor.sponsor_id='$did' ORDER BY sponsor.id desc;";

$res = $con->query($sql);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'name' => $row['name'],
            'amount' => $row['amount'],
            'date' => $row['date'],
            'descr' => $row['description']
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
