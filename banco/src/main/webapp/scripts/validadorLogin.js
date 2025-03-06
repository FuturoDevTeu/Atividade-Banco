/**
 * 
 */

function validarLogin(){
	let titularDigitada = frmLogin.titular.value;
	let contaEncontrada = contas.find(conta => conta.titular === titularDigitada);
	if(contaEncontrada){
		document.forms["frmLogin"].submit();	
	}else{
		alert("Conta não encontrada");
		return false;
	}	
}
