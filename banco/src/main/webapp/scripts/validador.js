/**
 * 
 */
function validar(){
	let titular = frmConta.titular.value;
	if(titular == ""){
		alert("Informe o titular da conta!!");
		frmConta.titular.value.focus();
		return false;
	}
	else{
		document.forms["frmConta"].submit();
	}
}