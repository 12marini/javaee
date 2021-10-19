/**
 * confirmar de exclusao de um contato
 * @author lucas marini
 */

function confirmar(idcon){
	let resposta = confirm("confirma a exclusão deste contato ?")
	if (resposta === true){
		// teste de recebimento 
		//alert(idcon)
		//window.location faz um redirecionamento
		window.location.href = "delete?idcon=" + idcon
	}
}