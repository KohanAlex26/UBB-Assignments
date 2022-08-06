$(document).ready(function() {
    $("#get_files").submit(function(event) {
         event.preventDefault()
 
        //  title = document.getElementById("title").value, 
        //  format = document.getElementById("format").value, 
        //  genre = document.getElementById("genre").value, 
        //  path = document.getElementById("path").value
 
         $.get("get.php",{},function(data){
             $("#maindiv").html(data); 
            //  console.log(data)
         })
    })
    
 
 })
 
     
 
 