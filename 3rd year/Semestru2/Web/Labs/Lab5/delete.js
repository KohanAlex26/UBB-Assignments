$(document).ready(function() {
    $("form").submit(function(event) {
         event.preventDefault()
 
         title = document.getElementById("title").value, 
         format = document.getElementById("format").value, 
         genre = document.getElementById("genre").value, 
         path = document.getElementById("path").value
 
         $.post("delete.php",{title:title, format:format, genre:genre, path:path},function(data){
             $("#maindiv3").html(data); 
             console.log(data)
         })
    })
    
 
 })
 
     
 
 