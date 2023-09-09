<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
$did = $_POST['did'];

$sql = "SELECT donate.item_name, donate.food_type, donate.date, Bookings.bid,Bookings.bstatus, donate.location,Bookings.qty as bqty FROM Bookings,donate WHERE donate.id =Bookings.donation_id AND Bookings.receiver_id = '$did' ORDER BY Bookings.bid desc;";

$res = $con->query($sql);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'name' => $row['item_name'],
            'type' => $row['food_type'],
            'qty' => $row['bqty'],
            'id' => $row['bid'],
           'stat' => $row['bstatus'],
            'date' => $row['date'],
            'loc' => $row['location']
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
