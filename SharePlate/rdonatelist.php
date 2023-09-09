<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }
 $date = $_POST['date'];
 $loc = $_POST['loc'];
 $sql = "SELECT * FROM donate where date='$date'and remains!=0 and location like '%$loc%'order by id desc";

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
            'location' => $row['location'],
            'id' => $row['id'],
            'time' => $row['time'],
            'contact' => $row['contact'],
            'dietary' => $row['dietary']
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
