<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
$did = $_POST['did'];
$date = $_POST['date'];

$sql = "SELECT donate.item_name, donate.food_type, donate.contact, Bookings.bid,Bookings.bstatus, donate.location,Bookings.qty as bqty, Bookings.donation_id, donate.remains FROM Bookings,donate WHERE donate.id =Bookings.donation_id  AND Bookings.receiver_id = '$did' AND date='$date' AND bstatus='Booked' ORDER BY Bookings.bid;";

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
                              'contact' => $row['contact'],
                              'loc' => $row['location'],
                              'did' => $row['donation_id'],
                              'remains' =>$row['remains']
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
