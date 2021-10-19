package br.com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.model.DAO;
import br.com.model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report"})
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	// instanciando o objeto dao
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath(); // objeto action armazena o caminho da requisição
		//System.out.println(action);
		if (action.equals("/main")) { // main vem do href do botão do index.html
			contatos(request, response); // aqui se for /main chama o metodo contatos
		} else if (action.equals("/insert")) {
			adicionarContato(request, response);
		}else if (action.equals("/select")) {
			listarContato(request, response);
		}else if (action.equals("/update")) {
			editarContato(request, response);
		}else if (action.equals("/delete")) {
			removerContato(request, response);
		}else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		}else {
			response.sendRedirect("index.html");
		}
		// teste de conexão
		// dao.testeConexao();
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		//encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
		//teste de recebimento da lista - mostra no console do eclipse
		/*for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getIdcon());
			System.out.println(lista.get(i).getNome());
			System.out.println(lista.get(i).getFone());
			System.out.println(lista.get(i).getEmail());
		}*/
	}

	/**
	 * Adicionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// novo contato
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//teste de recebimento dos dados do formulario -- aqui ele mostra no console do eclipse após clicar em adicionar na tela
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("fone"));
		//System.out.println(request.getParameter("email"));
		
		//setar as variaveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//invocar o metodo inserirContato passando o objeto contato
		dao.inserirContato(contato);
		// redirecionar para o documento agenda.jsp (pq é aqui q vamos listar os contatos)
		response.sendRedirect("main");
		
	}
	
	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//editar contatos
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//idcon é uma variavel interna do metodo editar pra receber o idcon do contato que será editado
		//String idcon = request.getParameter("idcon"); 
		//System.out.println(idcon);
		//Setar a variavel JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		//executar o metodo selecionarContato da (DAO)
		dao.selecionarContato(contato);
		//teste de recebimento
		//System.out.println(contato.getIdcon());
		//setar os atributos do formulario (editar.jsp) com o conteudo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		//encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//teste de recebimento
		//System.out.println(request.getParameter("idcon"));
		
		//setar as variaveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//executar o metodo alterarContato
		dao.alterarContato(contato);
		//redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
	
	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//remover um contato 
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String idcon = request.getParameter("idcon");
		//teste de recebimento do id do contato a ser excluido
		//System.out.println(idcon);
		//setar a variavel idcon JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		//executar o metodo deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);
		//redirecionar pra agenda.jsp com a lista atualizada
		response.sendRedirect("main");
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Gerar relatorio em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		try {
			//tipo de conteudo
			response.setContentType("apllication/pdf");
			//nome do documento
			response.addHeader("Content-Disposition","inline; filename=" + "contatos.pdf");
			//criar o document
			PdfWriter.getInstance(documento, response.getOutputStream());
			//abrir o documento -> conteudo
			documento.open();
			documento.add(new Paragraph("Lista de Contato: "));
			documento.add(new Paragraph(" "));
			//criar uma tabela
			PdfPTable tabela = new PdfPTable(3); // 3 significa q a tabela vai ter 3 colunas
			//cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			//popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for(int i=0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
