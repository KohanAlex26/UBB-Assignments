$(document).ready(function() {
    $("#get_path_for_format").submit(function(event) {
         event.preventDefault()
 
         format = document.getElementById("format").value 
 
         $.get("get_path_for_format.php",{format:format},function(data){
             $("#maindiv3").html(data); 
         })
    })
    
 
 })
 
     
 
 