
function direction2(){
    window.location.replace("http://localhost:8080/gamen2");
}

function direction3(){
    window.location.replace("http://localhost:8080/gamen3");
}

function directionexit(){
    window.location.replace("http://localhost:8080/logout");

}
function checkRow(){
    let  check;
    let errormsg="Không có kết quả tìm kiếm.";
    check = $("tr")[1].getElementsByTagName("td")[0].innerText;
    if($('#myInput').val() && check !== "No matching records found")
    window.location.replace("/gamen6/"+check);
    else
    document.getElementById("Không tìm thấy bản ghi phù hợp").innerHTML=errormsg;
}



