<!DOCTYPE html>
<html>

<head>
    <title>Insert Page page</title>
</head>

<body>
    <center>
        <?php

        $conn = mysqli_connect("localhost", "root", "", "multimediaproject");

        // Check connection
        if ($conn === false) {
            die("ERROR: Could not connect. "
                . mysqli_connect_error());
        }

        // Taking all 5 values from the form data(input)
        $title =  $_REQUEST['title'];
        $format = $_REQUEST['format'];
        $genre =  $_REQUEST['genre'];
        $path =  $_REQUEST['path'];

        // Performing insert query execution
        // here our table name is college
        $sql = "DELETE FROM multimediafile WHERE title='$title'";

        if (mysqli_query($conn, $sql)) {
            echo "<h3>Data deleted in a database successfully."
                . " Please browse your localhost php my admin"
                . " to view the updated data</h3>";

            echo nl2br("\n$title\n $format\n $genre\n $path\n ");
        } else {
            echo "ERROR: Hush! Sorry $sql. "
                . mysqli_error($conn);
        }

        // Close connection
        mysqli_close($conn);
        ?>
    </center>
</body>

</html>