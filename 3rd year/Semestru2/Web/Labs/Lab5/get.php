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


        // Performing insert query execution
        // here our table name is college
        if ($result = mysqli_query($conn, "SELECT * FROM multimediafile")) {
            echo "<table border='1'><tr><th>Title</th><th>Format</th><th>Genre</th><th>Path</th></tr>";
            while ($row = mysqli_fetch_array($result)) {
                echo "<tr>";
                echo "<td>" . $row['title'] . "</td>";
                echo "<td>" . $row['format'] . "</td>";
                echo "<td>" . $row['genre'] . "</td>";
                echo "<td>" . $row['path'] . "</td>";
                echo "</tr>";
            }
            echo "</table>";
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