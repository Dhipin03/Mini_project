<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
 $did = $_POST['did'];
 $sql = "SELECT * FROM Bookings,user  WHERE Bookings.receiver_id=user.id AND Bookings.donation_id='$did' AND bstatus='Booked' ORDER BY Bookings.bid;";

$res = $con->query($sql);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'name' => $row['name'],
            'stat' => $row['bstatus'],
            'qty' => $row['qty'],
            'id' => $row['bid']
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
