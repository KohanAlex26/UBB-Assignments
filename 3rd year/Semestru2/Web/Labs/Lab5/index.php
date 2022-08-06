<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="cookie.js"></script>
    <script type="text/javascript" src="post.js"></script>
</head>

<body>

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

    <form action="get.php" method="get">
        <input type="submit" value="Get Files" />
    </form>
    <br />

    <!-- <form action="post.php" method="post">
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
    <br /> -->
    <form method="post">
        Title: <input type="text" id="title" name="title" placeholder="title" />
        <br />
        Format: <input type="text" id="format" name="format" placeholder="format" />
        <br />
        Genre: <input type="text" id="genre" name="genre" placeholder="genre" />
        <br />
        Path: <input type="text" id="path" name="path" placeholder="path" />
        <br />
        <input type="submit" value="Insert" />
    </form>
    <br />

    <!-- <form action="update.php" method="put">
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
    <br /> -->

    <form method="put">
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

    <!-- <form action="delete.php" method="delete">
        Title: <input type="text" name="title" />
        <br />
        Format: <input type="text" name="format" />
        <br />
        Genre: <input type="text" name="genre" />
        <br />
        Path: <input type="text" name="path" />
        <br />
        <input type="submit" value="Delete" />
    </form>
    <br /> -->

    <form method="delete">
        Title: <input type="text" name="title" />
        <br />
        Format: <input type="text" name="format" />
        <br />
        Genre: <input type="text" name="genre" />
        <br />
        Path: <input type="text" name="path" />
        <br />
        <input type="submit" value="Delete" />
    </form>
    <br />

    <form action="get_title_for_genre.php" method="get">
        Genre: <input type="text" name="genre" />
        <br />
        <input type="submit" value="Get the titles of a genre" onclick="a()" />
    </form>
    <br />

    <form action="get_path_for_format.php" method="get" onclick="b()">
        Format: <input type="text" name="format" />
        <br />
        <input type="submit" value="Get the paths of a format" />
    </form>
    <br />


    <label id="label"></label>

    <script>
        var cookie = document.cookie;
        console.log(cookie);
        document.getElementById('label').innerHTML = 'Last operation: ' + cookie;
    </script>

    <div id="maindiv"></div>
    <div id="maindiv2"></div>
    <div id="maindiv3"></div>
    <div id="maindiv4"></div>
</body>

</html>