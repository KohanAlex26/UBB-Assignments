$(document).ready(function() {
    $("#get_title_for_genre").submit(function(event) {
         event.preventDefault()
 
         genre = document.getElementById("genre").value
 
         $.get("get_title_for_genre.php",{genre:genre},function(data){
             $("#maindiv2").html(data); 
         })
    })
    
 
 })
 
     
 
 