window.onload = function(){
    const objects = canvas.querySelectorAll(".object");
    for(var i = 0; i<objects.length; i++){
         var object = objects.item(i);
         object.addEventListener("mousedown", handleObjectHold);
         object.addEventListener("dblclick", handleObjectRemove);
    }
}