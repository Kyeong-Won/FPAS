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

  let object;
  object = document.createElement("i");
  classList.push("fas fa-video fa-3x");
  const priority = getMaxAttr(objects, "priority", 0);
  object.setAttribute("priority", priority);
  object.style["z-index"] = priority;
  object.style.width = "30px";
  object.style.height = "30px";
  object.style.position = "relative";

//  if(setting.shape == "fas fa-video"){
//    setting.shape = "fas fa-video fa-3x"
//    object = document.createElement("i");
//  }
//  else{
//    object = document.createElement("div");
//  }


//
//  if( setting.shape === "text" ){
//    object = document.createElement("input");
//    object.type = "text";
//  }
//
//  // 도형 모양 설정
//  if( setting.shape ){
//    classList.push(setting.shape);
//  }
//
//  // 배경 색상 설정
//  if( setting.bg_color ){
//    classList.push(setting.bg_color);
//  }
//
//  // 폰트 색상 설정
//  if( setting.font_color ){
//    classList.push(setting.font_color);
//  }
//
//  // 우선순위 설정
//  const priority = getMaxAttr(objects, "priority", 0);
//  object.setAttribute("priority", priority);
//  object.style["z-index"] = priority;
//
//
//
//  // 도형 사이즈 설정
//  if(setting.shape == "fas fa-video fa-3x"){
//    object.style.width = "30px";
//    object.style.height = "30px";
//  }
//  else if( setting.width && setting.height ){
//    object.style.width = setting.width+"px";
//    object.style.height = setting.height+"px";
//  }
//
//
//
//  // 폰트 사이즈 설정
//  if( setting.font_size ){
//    object.style["font-size"] = setting.font_size+"px";
//  }
//
//  // 투명도 설정
//  if( setting.opacity ){
//    object.style["opacity"] = setting.opacity+"%";
//  }
//
  // class 설정
  object.className = classList.join(" ");

  // 이벤트 바인딩
  object.addEventListener("mousedown", handleObjectHold);
  object.addEventListener("dblclick", handleObjectRemove);


  return object;
}

