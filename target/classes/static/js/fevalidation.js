
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
        errs.push("お名前は25文字以内で入力して下さい。");
        document.getElementById("errRegisterEmployeeName").style.color = "red";
    } else {
        document.getElementById("errRegisterEmployeeName").style.color = "#f2f2f2";
    }

    if (!mailEmployee.match(mailformat) || mailEmployee.length >= 25) {
        errs.push("メールアドレスは、25文字以内で正しく入力して下さい。そして　format　もう　1回　チェックしてください。");
        document.getElementById("errRegisterEmployeeMail").style.color = "red";
    } else {
        document.getElementById("errRegisterEmployeeMail").style.color = "#f2f2f2";
    }

    if (!phoneEmployee.match(phoneformat)) {
        errs.push("電話番号は、数字10文字以内で入力して下さい。");
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
        errs.push("お名前は25文字以内で入力して下さい。");
    }
    if (!mailEmployee.match(mailformat) || mailEmployee.length >= 25) {
        errs.push("メールアドレスは、25文字以内で正しく入力して下さい。そして　format　もう　1回　チェックしてください。");
    }
    if (!phoneEmployee.match(phoneformat)) {
        errs.push("電話番号は、数字10文字以内で入力して下さい。");
    }

    if (errs.length === 0) {
        return true;
    } else {
        document.getElementById("txt_error_msg_gm6").innerHTML = errs[0];
        return false;
    }
}