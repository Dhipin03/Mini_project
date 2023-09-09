<?php

include 'init.php';
    if ($con->connect_error) {
 
        die("Connection failed: " . $conn->connect_error);
    }

 $sql = "SELECT * FROM rating,user  WHERE rating.uid=user.id order by rating.id desc;";

$res = $con->query($sql);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'name' => $row['name'],
            'rate' => $row['rate'],
            'feed' => $row['feedback']
                              )
    );

    }echo json_encode( $result);

}
else
    echo "1";

mysqli_close($con);

 
?>
