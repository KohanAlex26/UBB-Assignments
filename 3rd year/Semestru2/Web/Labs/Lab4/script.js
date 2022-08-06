var a=1;
var b=1;
function changeWallpaper() {
    a++;
    if(a==6)
    a=1;
    document.getElementById("banner").style.backgroundImage="url(wallpaper"+a+".jpg)";
}

function changeButtons() {
    var links = document.getElementsByTagName("a");
    for(var i=0;i<links.length;i++)
    {
        if(links[i].href)
        {
            if(b==1)
            {
                links[i].style.color = "red";  
                links[i].style.border= "1px solid red"; 
                links[i].style.borderRadius = "25px";
            }
            else
            {
                links[i].style.color = "black";  
                links[i].style.border= "1px solid rgb(9, 112, 40)"; 
                links[i].style.borderRadius = "0px";
            } 
        }
    }
    if(b==1)
    b=2;
    else
    b=1;  
}
