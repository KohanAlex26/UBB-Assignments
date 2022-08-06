<!DOCTYPE html>
<html>

<head>
    <title>Insert Page page</title>
    <!-- <script type="text/javascript" src="script.js"></script> -->
</head>

<body>
    <center>
        <!-- <script>
            a();
        </script> -->
        <?php

        $conn = mysqli_connect("localhost", "root", "", "multimediaproject");

        // Check connection
        if ($conn === false) {
            die("ERROR: Could not connect. "
                . mysqli_connect_error());
        }

        $genre =  $_REQUEST['genre'];

        // Performing insert query execution
        // here our table name is college
        if ($result = mysqli_query($conn, "SELECT * FROM multimediafile WHERE genre='$genre'")) {
            echo "<table border='1'><tr><th>Title</th></tr>";
            while ($row = mysqli_fetch_array($result)) {
                echo "<tr>";
                echo "<td>" . $row['title'] . "</td>";
                echo "</tr>";
            }
            echo "</table>";
            echo "<br />";
            echo "<br />";
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