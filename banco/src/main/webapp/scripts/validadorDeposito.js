/**
 * 
 */

function validarDeposito(){
	let valor = frmDeposito.deposito.value;
	if(valor >= 0){
		document.forms["frmDeposito"].submit();
	}else{
		alert("Informe um valor valido");
	 	return false;
	}
}