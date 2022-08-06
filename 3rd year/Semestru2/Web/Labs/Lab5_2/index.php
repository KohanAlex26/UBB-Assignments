<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="get.js"></script>
    <script type="text/javascript" src="get_title_for_genre.js"></script>
    <script type="text/javascript" src="get_path_for_format.js"></script>
    <link rel="stylesheet" href="index.css">
</head>

<body>

    <div class="myDiv">
        <?php
        $serverName = "localhost";
        $userName = "root";
        $password = "";
        $dbName = "multimediaproject";
        $db = new mysqli($serverName, $userName, $password, $dbName) or die("Unable to connect");
        if (mysqli_connect_errno()) {
            echo 'Failed to connect!';
            exit();
        }
        echo 'Connection succes!';
        ?>
        <br />


        <form id="get_files" method="get">
            <input type="submit" value="Get Files" />
        </form>
        <br />
        <div id="maindiv"></div>
        <hr>

        <form action="post.php" method="post">
            Title: <input type="text" name="title" />
            <br />
            Format: <input type="text" name="format" />
            <br />
            Genre: <input type="text" name="genre" />
            <br />
            Path: <input type="text" name="path" />
            <br />
            <input type="submit" value="Insert" />
        </form>
        <br />

        <form action="put.php" method="put">
            Id: <input type="text" name="id" />
            <br />
            Title: <input type="text" name="title" />
            <br />
            Format: <input type="text" name="format" />
            <br />
            Genre: <input type="text" name="genre" />
            <br />
            Path: <input type="text" name="path" />
            <br />
            <input type="submit" value="Update" />
        </form>
        <br />

        <form action="delete.php" method="delete">
            Id: <input type="text" name="id" />
            <br />
            <input type="submit" value="Delete" onClick="return confirm('Are you sure you want to delete?')" />
        </form>
        <hr>

        <form id="get_title_for_genre" method="get">
            Genre: <input type="text" id="genre" name="genre" />
            <br />
            <input type="submit" value="Get the titles of a genre" onclick="a()" />
        </form>
        <br />
        <div id="maindiv2"></div>
        <hr>

        <form id="get_path_for_format" method="get">
            Format: <input type="text" id="format" name="format" />
            <br />
            <input type="submit" value="Get the paths of a format" onclick="b()" />
        </form>
        <br />
        <div id="maindiv3"></div>
        <hr>

        <label id="label"></label>
        <script>
            function a() {
                document.getElementById('label').innerHTML = 'Last operation:  get title for genre';
            }

            function b() {
                document.getElementById('label').innerHTML = 'Last operation: get path for format';
            }
        </script>
    </div>

</body>

</html>