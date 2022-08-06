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
        $id=$_REQUEST['id'];
        $title =  $_REQUEST['title'];
        $format = $_REQUEST['format'];
        $genre =  $_REQUEST['genre'];
        $path =  $_REQUEST['path'];

        if (!preg_match("/^[a-zA-z]*$/", $title) || !preg_match("/^[a-zA-z]*$/", $format) || !preg_match("/^[a-zA-z]*$/", $genre)) {
            $ErrMsg = "Only alphabets and whitespace are allowed for title/format/genre .";
            echo $ErrMsg;
        } else {
            $regex = "((https?|ftp)\:\/\/)?";
            $regex .= "([a-z0-9+!*(),;?&=\$_.-]+(\:[a-z0-9+!*(),;?&=\$_.-]+)?@)?";
            $regex .= "([a-z0-9-.]*)\.([a-z]{2,3})";
            $regex .= "(\:[0-9]{2,5})?";
            $regex .= "(\/([a-z0-9+\$_-]\.?)+)*\/?";
            $regex .= "(\?[a-z+&\$_.-][a-z0-9;:@&%=+\/\$_.-]*)?";
            $regex .= "(#[a-z_.-][a-z0-9+\$_.-]*)?";

            if (!preg_match("/^$regex$/i", $path)) {
                $ErrMsg = "Invalid URL format";
                echo $ErrMsg;
            } else {
                // Performing insert query execution
                // here our table name is college
                $sql = "UPDATE multimediafile  SET title='$title',format='$format',genre='$genre',path='$path' WHERE id='$id'";

                if (mysqli_query($conn, $sql)) {
                    echo "<h3>Data updated in a database successfully."
                        . " Please browse your localhost php my admin"
                        . " to view the updated data</h3>";

                    echo nl2br("\n$title\n $format\n $genre\n $path\n ");
                } else {
                    echo "ERROR: Hush! Sorry $sql. "
                        . mysqli_error($conn);
                }
            }
        }

        // Close connection
        mysqli_close($conn);
        ?>
    </center>
</body>

</html>