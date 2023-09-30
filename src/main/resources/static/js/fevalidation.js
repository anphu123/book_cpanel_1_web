
const nameformat = /^([a-zA-Z0-9_-\s]){3,25}$/;
const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
const phoneformat = /^([0-9]){10}$/;
let nameEmployee,mailEmployee,phoneEmployee,errs;

function validationForNewEmployeeRegister() {
    nameEmployee = document.getElementById('txtnameEmployee').value;
    mailEmployee = document.getElementById('txtmailEmployee').value;
    phoneEmployee = document.getElementById('txtphoneEmployee').value;
    errs = [];
    if (!nameEmployee.match(nameformat) || nameEmployee.length >= 25) {
        errs.push("Vui lòng nhập tên của bạn từ 3-25 ký tự.");
        document.getElementById("errRegisterEmployeeName").style.color = "red";
    } else {
        document.getElementById("errRegisterEmployeeName").style.color = "#f2f2f2";
    }

    if (!mailEmployee.match(mailformat) || mailEmployee.length >= 25) {
        errs.push("Vui lòng nhập chính xác địa chỉ email của bạn và trong vòng 25 ký tự. Và vui lòng kiểm tra lại định dạng một lần nữa.");
        document.getElementById("errRegisterEmployeeMail").style.color = "red";
    } else {
        document.getElementById("errRegisterEmployeeMail").style.color = "#f2f2f2";
    }

    if (!phoneEmployee.match(phoneformat)) {
        errs.push("Vui lòng nhập số điện thoại của bạn trong vòng 10 số.");
        document.getElementById("errRegisterEmployeePhone").style.color = "red";
    } else {
        document.getElementById("errRegisterEmployeePhone").style.color = "#f2f2f2";
    }

    if (errs.length === 0){
        return  true;
    }
    else{
        alert(errs[0]);
        return false;
    }

}
function validationForNewEmployeeRegister2() {
    let nameEmployee = document.getElementById('txtnameEmployee').value;
    let mailEmployee = document.getElementById('txtmailEmployee').value;
    let phoneEmployee = document.getElementById('txtphoneEmployee').value;
    let errs = [];
    if (!nameEmployee.match(nameformat)　|| nameEmployee.length >= 25) {
        errs.push("Vui lòng nhập tên của bạn từ 3-25 ký tự.");
    }
    if (!mailEmployee.match(mailformat) || mailEmployee.length >= 25) {
        errs.push("Vui lòng nhập chính xác địa chỉ email của bạn và trong vòng 25 ký tự. Và vui lòng kiểm tra lại định dạng một lần nữa.");
    }
    if (!phoneEmployee.match(phoneformat)) {
        errs.push("Vui lòng nhập số điện thoại của bạn trong vòng 10 số.");
    }

    if (errs.length === 0) {
        return true;
    } else {
        document.getElementById("txt_error_msg_gm6").innerHTML = errs[0];
        return false;
    }
}