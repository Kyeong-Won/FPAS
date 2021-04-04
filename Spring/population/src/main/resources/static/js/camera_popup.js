window.onload = function(){
    const objects = canvas.querySelectorAll(".object");
    var camera_icon = 'fa-video';

    for(var i = 0; i<objects.length; i++){
         var object = objects.item(i);
         var object_className = object.className;
         if (object_className.includes(camera_icon)){
            object.addEventListener("click", handleCameraPopup);
         }
    }
}

function handleCameraPopup(event){
      event.preventDefault();
      window.open("/popup.html","test",  "width=400, height=300, left=100, top=50");
}