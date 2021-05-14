// js/event_handler.js

var deleteUriAry = new Array(); //도형 삭제 uri 배열

//수정 페이지에서 도면 저장 이벤트 핸들러
function handleUpdateObject(event){
  event.preventDefault();

  // 도형 삭제 uri 전송
  if (deleteUriAry.length != 0){
      for (var i=0; i<deleteUriAry.length; ++i){
         let str = deleteUriAry.pop();
         console.log(str);
         $.ajax({
           type: 'DELETE',
           url: str,
         }).done(function() {

         }).fail(function(error) {
                 alert(JSON.stringify(error));
         });
      }
  }
  const boardId = document.getElementById("board_id").innerText;
  const objects = canvas.querySelectorAll(".object");

  const uri = '/board/'+ boardId + '/put'

  var shapes = [];
  var shapes_id = [];
  var title = document.getElementById("title").value;

  for(var i = 0; i<objects.length; i++){
       var object = objects.item(i);

          console.log(object.style["left"]);

          var tag_val = {
              "id" : object.id,
              "priority": object.style["z-index"],
              "className": object.className,
              "zIndex": object.style["z-index"],
              "width": object.style["width"],
              "height": object.style["height"],
              "fontSize": object.style["font-size"],
              "left": object.style["left"],
              "top": object.style["top"],
          };
          shapes.push(tag_val);
    }

    var put_data = {'shapes': shapes, 'title': title};
      //ajax 호출
      $.ajax({
          type: 'PUT',
          url: uri,
          dataType: 'json',
          traditional: true,
          contentType:'application/json; charset=utf-8',
          data: JSON.stringify(put_data)
      }).done(function() {
          alert('도면이 수정 되었습니다.');
          window.location.href = '/boards/list';
      }).fail(function(error) {
          alert(JSON.stringify(error));
      });


}

//도면 저장 이벤트 핸들러 최신
function handleSaveObject(event){
    event.preventDefault();
    const canvas = document.querySelector("#canvas > .wrapper");
    var title = document.getElementById("title").value;
    var form = document.getElementById("palette_form")
    var formData = new FormData(form);
    var shapes = [];
    const objects = canvas.querySelectorAll(".object");

    for(var i = 0; i<objects.length; i++){
        var object = objects.item(i);
        formData.append("shapes["+i+"].priority", object.priority);
        formData.append("shapes["+i+"].className", object.className);
        formData.append("shapes["+i+"].aria_hidden", object.getAttribute('aria-hidden'));
        formData.append("shapes["+i+"].zIndex", object.style["z-index"]);
        formData.append("shapes["+i+"].width", object.style["width"]);
        formData.append("shapes["+i+"].height", object.style["height"]);
        formData.append("shapes["+i+"].position", "absolute");
        formData.append("shapes["+i+"].left", object.style["left"]);
        formData.append("shapes["+i+"].top", object.style["top"]);
    }

    formData.append("file", jQuery("#image")[0].files[0]);
    formData.append("titles", title);
//    console.log(formData)
    $.ajax({
        url: '/board/save',
        method: "POST",
        data: formData,
//        dataType: 'json',
        processData: false,
        contentType: false,
        success: function () {
            alert('도면이 저장 되었습니다.');
            window.location.href = '/boards/list';
        },
        error: function () {
            alert("failed! ")
        }
    });

}
//도면 저장 이벤트 핸들러
//function handleSaveObject(event){
//  event.preventDefault();
////  const objects = canvas.querySelectorAll(".object");
//
//  var arr = [];
//  var title = document.getElementById("title").value;
//
//  for(var i = 0; i<objects.length; i++){
//    var object = objects.item(i);
//
//    var tag_val = {
//        "priority": object.style["z-index"],
//        "className": object.className,
//        "zIndex": object.style["z-index"],
//        "width": object.style["width"],
//        "height": object.style["height"],
//        "fontSize": object.style["font-size"],
//        "left": object.style["left"],
//        "top": object.style["top"],
//    };
//    arr.push(tag_val);
//  }
//
//  var post_data = {'shapes': arr, 'title': title};
//  //ajax 호출
//  $.ajax({
//      type: 'POST',
//      url: '/board/save',
//      dataType: 'json',
//      traditional: true,
//      contentType:'application/json; charset=utf-8',
//      data: JSON.stringify(post_data)
//  }).done(function() {
//      alert('도면이 저장 되었습니다.');
//      window.location.href = '/boards/list';
//  }).fail(function(error) {
//      alert(JSON.stringify(error));
//  });
//}

// 도형 생성 이벤트 핸들러
function handleCreateObject(event){
  event.preventDefault();

  const canvas = document.querySelector("#canvas > .wrapper");
  const palette = document.getElementById("palette_form");
//  const object_state = palette.querySelectorAll('input[type="text"], input[type="radio"]:checked');

  // 도형 설정값을 JSON형태로 치환.
//  const settings = Array.from(object_state).reduce(function(prev, crnt, idx){
//    if( idx === 1 ) {
//      const _setting = {}
//      _setting[prev.name] = prev.value;
//      _setting[crnt.name] = crnt.value;
//      return _setting;
//    }
//    prev[crnt.name] =crnt.value;
//
//    return prev;
//  });
  // 도형 생성
//  const object = createObject(settings);
    const object = createObject();

  // 도형을 도화지에 추가
  canvas.appendChild(object);

//  // 도형이 텍스트인 경우, 바로 글을 작성할 수 있도록 포커싱.
//  if( settings.shape === "text" ){
//    object.focus();
//  }
}

// 도형 삭제 이벤트 핸들러
function handleObjectRemove(event){
  event.preventDefault();
  event.target.remove();

  if (event.target.id != ""){ //db에 저장된 도형이면...
    const uri = '/board/delete/' + event.target.id;
    deleteUriAry.push(uri);
  }
}

// 도형 선택 이벤트 핸들러
function handleObjectHold(event){
  event.preventDefault();

  event.target.focus();

  const canvas = document.querySelector("#canvas > .wrapper");
  const objects = canvas.querySelectorAll(".object");
  let seledted_object = event.target;
  let classList = seledted_object.classList;

  if( !classList.contains("hold") ){
    // 마우스 커서의 위치 (왼쪽 상단 모서리 기준)
    const mouseX = event.clientX;
    const mouseY = event.clientY;

    // 선택한 도형의 위치 (왼쪽 상단 모서리 기준)
    const objectPos = seledted_object.getBoundingClientRect();
    const objectX = objectPos.x;
    const objectY = objectPos.y;

    // 도형과 마우스의 위치 차이
    const gapX = mouseX - objectX;
    const gapY = mouseY - objectY;

    // 도형과 마우스의 위치 차이를 속성에 저장
    seledted_object.setAttribute("gap-x", gapX);
    seledted_object.setAttribute("gap-y", gapY);

    // 선택한 도형을 맨 앞으로 보내기
    const priority = getMaxAttr(objects, "priority", 0);
    seledted_object.setAttribute("priority", priority);
    seledted_object.style["z-index"] = priority;

    // 선택한 도형에 'hold' class를 추가
    classList.add("hold");
  }
}

// 도형 움직임 이벤트 핸들러
function handleObjectDrag(event){
  event.preventDefault();

  const canvas = document.querySelector("#canvas > .wrapper");
  const object = canvas.querySelector(".object.hold");
  if( object ){
    // 마우스 커서의 위치. (왼쪽 상단 모서리 기준)
    const mouseX = event.clientX;
    const mouseY = event.clientY;

    // 도화지의 위치. (왼쪽 상단 모서리 기준)
    const canvasPos = canvas.getBoundingClientRect();
    const canvasX = canvasPos.x;
    const canvasY = canvasPos.y;

    // 도형과 마우스의 위치 차이
    const gapX = object.getAttribute("gap-x");
    const gapY = object.getAttribute("gap-y");

    // 도형이 이동할 위치
    const objectX = mouseX - gapX - canvasX;
    const objectY = mouseY - gapY - canvasY;

    object.style.left = objectX+"px";
    object.style.top = objectY+"px";

  }
}

// 도형 놓기 이벤트 핸들러
function handleObjectDrop(event){
  event.preventDefault();

  const canvas = document.querySelector("#canvas > .wrapper");
  const object = canvas.querySelector(".object.hold");

  if( object ){
    // 속성 및 class를 삭제
    object.removeAttribute("gap-x")
    object.removeAttribute("gap-y")

    object.classList.remove("hold");
  }
}
