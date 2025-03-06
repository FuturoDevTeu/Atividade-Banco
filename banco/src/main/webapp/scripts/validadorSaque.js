/**
 * 
 */
function validarSaque(){
	let valor = frmSaque.valor.value.trim();
	if(valor > 0){
		document.forms["frmSaque"].submit();
	}else{
		alert("Por favor insira um valor valido");
		return false;
	}
}