// js/event_handler.js

var deleteUriAry = new Array(); //도형 삭제 uri 배열
//수정 페이지에서 도면 저장 이벤트 핸들러
function handleUpdateObject(event){
    event.preventDefault();
    const canvas = document.querySelector("#canvas > .wrapper");
    const objects = canvas.querySelectorAll(".object");
    const boardId = document.getElementById("board_id").innerText;
    const imageId = document.getElementById("image_id").innerText;

    // 도형 삭제 uri 전송
    if (deleteUriAry.length != 0){
      for (var i=0; i<deleteUriAry.length; ++i){
         let str = deleteUriAry.pop();
         $.ajax({
           type: 'DELETE',
           url: str,
         }).done(function() {

         }).fail(function(error) {
            alert(JSON.stringify(error));
         });
      }
    }

    var title = document.getElementById("title").value;

    if (jQuery("#image")[0].files[0] !== undefined){
        let file_delete_url = '/board/file/delete/' + imageId;
        $.ajax({
            type: 'DELETE',
            url: file_delete_url
        }).done(function() {

        }).fail(function(error) {
            alert(JSON.stringify(error));
        });

        var form = document.getElementById("palette_form")
        var formData = new FormData(form);

        for(var i = 0; i<objects.length; i++){
            var object = objects.item(i);
            formData.append("shapes["+i+"].id", object.id);
            formData.append("shapes["+i+"].src", "/img/video-solid.svg");
            formData.append("shapes["+i+"].priority", object.priority);
            formData.append("shapes["+i+"].className", object.className);
            formData.append("shapes["+i+"].aria_hidden", object.getAttribute('aria-hidden'));
            formData.append("shapes["+i+"].zIndex", object.style["z-index"]);
            formData.append("shapes["+i+"].width", object.style["width"]);
            formData.append("shapes["+i+"].height", object.style["height"]);
            formData.append("shapes["+i+"].position", "absolute");
            formData.append("shapes["+i+"].left", object.style["left"]);
            formData.append("shapes["+i+"].top", object.style["top"]);
            formData.append("shapes["+i+"].name", object.name);
        }
        formData.append("file", jQuery("#image")[0].files[0]);
        formData.append("titles", title);

        var uri = '/board/change-image/'+ boardId + '/put'
        $.ajax({
            url: uri,
            method: "PUT",
            data: formData,
            processData: false,
            contentType: false,
            success: function () {
              alert('도면이 수정 되었습니다.');
              window.location.href = '/boards/list';
            },
            error: function (request, status, error) {
              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
              alert("failed! ")
            }
        });
    }
    else{
        var uri = '/board/'+ boardId + '/put'
        var shapes = [];

        for(var i = 0; i<objects.length; i++){
          var object = objects.item(i);
          var tag_val = {
              "id" : object.id,
              "src": "/img/video-solid.svg",
              "priority": object.priority,
              "className": object.className,
              "aria_hidden": object.getAttribute('aria-hidden'),
              "zIndex": object.style["z-index"],
              "width": object.style["width"],
              "height": object.style["height"],
              "position": "absolute",
              "left": object.style["left"],
              "top": object.style["top"],
              "name":object.name
          };
          shapes.push(tag_val);
        }

        var put_data = {'shapes': shapes, 'titles': title};
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
}

//도면 저장 이벤트 핸들러 최신
function handleSaveObject(event){
    var check_upload = document.getElementById("board_img").getAttribute("src");
    if(check_upload == null){
        alert("도면 이미지를 업로드 해주세요.");
        return;
    }
    event.preventDefault();
    const canvas = document.querySelector("#canvas > .wrapper");
    var title = prompt("도면 이름을 입력해주세요.");
    if(title == null)
        return;
//    var title = document.getElementById("title").value;
    var form = document.getElementById("palette_form")
    var formData = new FormData(form);
    var shapes = [];
    const objects = canvas.querySelectorAll(".object");
    if (objects.length == 0){
        alert("카메라를 생성해주세요.");
        return;
    }
    for(var i = 0; i<objects.length; i++){
        var object = objects.item(i);
        formData.append("shapes["+i+"].src", "/img/video-solid.svg");
        formData.append("shapes["+i+"].priority", object.priority);
        formData.append("shapes["+i+"].className", object.className);
        formData.append("shapes["+i+"].aria_hidden", object.getAttribute('aria-hidden'));
        formData.append("shapes["+i+"].zIndex", object.style["z-index"]);
        formData.append("shapes["+i+"].width", object.style["width"]);
        formData.append("shapes["+i+"].height", object.style["height"]);
        formData.append("shapes["+i+"].position", "absolute");
        formData.append("shapes["+i+"].left", object.style["left"]);
        formData.append("shapes["+i+"].top", object.style["top"]);
        formData.append("shapes["+i+"].name", object.name);

    }

    formData.append("file", jQuery("#image")[0].files[0]);
    formData.append("titles", title);

    $.ajax({
        url: '/board/save',
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function () {
            alert('도면이 저장 되었습니다.');
            window.location.href = '/boards/list';
        },
        error: function (request, status, error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            alert("failed! ")
        }
    });

}

// reset버튼 이벤트 핸들러
function handleReset(event){
    event.preventDefault();
    var result = confirm("페이지를 초기화 하시겠습니까?");
    if(result){
        window.location.reload();
    }
    else
        return;
}

// 도형 생성 이벤트 핸들러
function handleCreateObject(event){
    event.preventDefault();
    var check_upload = document.getElementById("board_img").getAttribute("src");
    if(check_upload == null){
        alert("도면 이미지를 업로드 해주세요.");
        return;
    }
    const canvas = document.querySelector("#canvas > .wrapper");
    const palette = document.getElementById("palette_form");

    const object = createObject();


    // 도형을 도화지에 추가
    canvas.appendChild(object);


    $( ".object" ).resizable();
    $('.ui-wrapper').draggable();

}

// 도형 삭제 이벤트 핸들러
function handleObjectRemove(event){
  event.preventDefault();
  event.target.remove();

  if (event.target.id != ""){ //db에 저장된 도형이면...
    const uri = '/board/shape/delete/' + event.target.id;
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

// 카메라 선택 이벤트 핸들러
function handleSelectObject(event){
    var camera_name = document.getElementById("cameras").value;

    $.ajax({
        type: 'GET',
        url: ('/board/camera/' + camera_name),
        success: successCallBack
    });
}

function successCallBack(return_val){
    console.log(return_val);
    const dashboards = document.querySelectorAll(".dashboard");

    for(var i = 0; i<dashboards.length; i++)
    {
        var dashboard = dashboards.item(i);
        if (dashboard.id == return_val){
            dashboard.style.display = "";
        }
        else
            dashboard.style.display="none"; 
    }
}

window.onload= function(){
    var heatmapInstance = h337.create({
        container: document.getElementById('boardPaint2')
    });

    var max = 0;
    var points = [];
    var heatmap = document.querySelectorAll("#shapes > img");
    var divid = document.querySelectorAll("#heatmap_info > div");
    console.log(divid);

    for(var i = 0; i < heatmap.length; ++i){
        var h = heatmap.item(i);
        var info = divid.item(i);
        console.log(document.getElementById(info.id));

        var sum_value = document.getElementById(info.id).innerText;
        console.log(sum_value);
        let val = parseInt(sum_value);
        if(info.id == "heatmap_hyungnam") sum_value += 0;
        var point = {
            x: parseInt(h.style.left.replace('px', ''))+190,
            y: parseInt(h.style.top.replace('px', ''))+22,
            value: parseInt(sum_value)
        };
        max = Math.max(max, val);
        points.push(point);
    }

    console.log(points);
    console.log(max);
    // heatmap data format
    var data = {
        max: max,
        data: points
    };
    heatmapInstance.setData(data);
}