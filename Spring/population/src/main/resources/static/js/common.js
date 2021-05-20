//// '생성' 버튼에 클릭 이벤트 바인딩
const btn_create = document.getElementById("create_object");
if (btn_create != null){
    btn_create.addEventListener("click", handleCreateObject);
}


//'저장' 버튼에 클릭 이벤트 바인딩
const btn_save = document.getElementById("save_board");
if (btn_save != null){
    btn_save.addEventListener("click", handleSaveObject);
}

//수정 페이지에서 '저장하기' 버튼에 클릭 이벤트 바인딩
const btn_load = document.getElementById("update_board");
if (btn_load != null){
    btn_load.addEventListener("click", handleUpdateObject);
}

//
const camera_select = document.getElementById("cameras");
if (camera_select != null){
    console.log("common.js", camera_select);
    camera_select.addEventListener("change", handleSelectObject);
}

//// 마우스 이벤트 추가
document.addEventListener('mousemove', handleObjectDrag);
document.addEventListener('mouseup', handleObjectDrop);
