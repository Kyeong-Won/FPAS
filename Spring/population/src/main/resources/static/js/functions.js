// 속성에서 최대값 구하기
var a = [];
function getMaxAttr(objects, key, _default){
  return (
    objects.length > 0
      ? Math.max.apply(null, Array.from(objects).map(object=>object.getAttribute(key)))
      : _default
  )+1;
}

// 도형 element 생성
function createObject(){
  const canvas = document.querySelector("#canvas > .wrapper");
  const objects = canvas.querySelectorAll(".object");

  let classList = ["object"];

        const priority = getMaxAttr(objects, "priority", 0);
        let object;
        object = document.createElement("img");
        object.src = "/img/video-solid.svg";
        object.alt = "camera";
        object.style["width"] = "50px";
        object.style["height"] = "40px";
        object.setAttribute("priority", priority);
        object.style["opacity"] = 1;
        object.style["z-index"] = priority;
        object.style.position = "absolute";
        object.className = "object";
        var name = document.getElementById("camera_name").value;
        object.setAttribute("name", name);

        object.addEventListener("mousedown", handleObjectHold);
        object.addEventListener("dblclick", handleObjectRemove);

        return object;

}

