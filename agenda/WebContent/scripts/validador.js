/**
 * validaacao de formulario
 * @autor lucas marini
 */

function validar(){
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	if (nome === ""){
		alert('Preencha o campo Nome')
		frmContato.nome.focus()
		return false
	}else if (fone === ""){
		alert('Preencha o campo Fone')
		frmContato.fone.focus()
		return false
		}else {
			document.forms["frmContato"].submit()
		}
}

function voltar(){
	let resposta = confirm("Deseja voltar ?")
	if (resposta === true){
		//window.location faz um redirecionamento
		window.location.href = "main"
	}
}
