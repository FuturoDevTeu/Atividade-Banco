package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import model.Dao;
import model.ContaBancaria;

@WebServlet(urlPatterns = { "/controller", "/cadastrar", "/insert", "/logar", "/main", "/logado"
							,"/requestSaque", "/requestDeposito", "/saque", "/deposito"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = new Dao();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/logar")) {
			logar(request, response);
		}else if(action.equals("/cadastrar")) {
			cadastrar(request, response);
		}else if(action.equals("/requestSaque")) {
			saque(request, response);
		}else if(action.equals("/requestDeposito")) {
			deposito(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/insert")){
			verificacaoConta(request, response);
		}else if(action.equals("/logado")) {
			verificandoLogin(request, response);
		}else if(action.equals("/saque")){
			verificandoSaque(request, response);
		}else if(action.equals("/deposito")){
			verificandoDeposito(request, response);
		}
	}
	protected void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("novaConta.jsp");
		rd.forward(request, response);
	}
	
	protected void verificacaoConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titular = request.getParameter("titular");
		
		if(titular == null || titular.trim().isEmpty()) {
			request.setAttribute("erro", "Por favor informe o titular da conta!");
			RequestDispatcher rd = request.getRequestDispatcher("novaConta.jsp");
			rd.forward(request, response);
		}
		ContaBancaria novaConta = new ContaBancaria();
		novaConta.setTitular(titular.trim());
		dao.adicionarConta(novaConta);
		response.sendRedirect("index.html");
	}

	protected void logar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ContaBancaria> lista = dao.listarContas();
		request.setAttribute("contas", lista);
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	protected void verificandoLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String verificacao = request.getParameter("titular");
		
		if(verificacao == null || verificacao.trim().isEmpty()) {
			request.setAttribute("erro", "Por favor, informe o titular da conta!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		ContaBancaria conta = dao.buscarContaPorTitular(verificacao);
		
		if(conta == null) {
			request.setAttribute("erro", "conta não encontrada!!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("contaLogada", conta);
			response.sendRedirect("main.jsp");
		}
		
	}
	protected void saque(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("saque.jsp");
		rd.forward(request, response);
	}
	protected void verificandoSaque(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ContaBancaria contaLogada = (ContaBancaria) session.getAttribute("contaLogada");
		
		if(contaLogada == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		try {
			double valor = Double.parseDouble(request.getParameter("valor"));
			if(valor < 0) {
				request.setAttribute("erro", "Valor do saque deve ser positivo");
				RequestDispatcher rd = request.getRequestDispatcher("saque.jsp");
				rd.forward(request, response);
				return;
			}
			boolean sucesso = contaLogada.sacar(valor);
			
			if(sucesso){
				dao.transacao(contaLogada);
				response.sendRedirect("main.jsp");
			} else {
				request.setAttribute("erro", "saldo insuficiente");
				RequestDispatcher rd = request.getRequestDispatcher("saque.jsp");
				rd.forward(request, response);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	protected void deposito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("deposito.jsp");
		rd.forward(request, response);
	}
	protected void verificandoDeposito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ContaBancaria contaLogada = (ContaBancaria) session.getAttribute("contaLogada");
		if(contaLogada == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		try {
			double valor = Double.parseDouble(request.getParameter("valor"));
			if(valor < 0) {
				request.setAttribute("erro", "Insira um valor positivo");
				RequestDispatcher rd = request.getRequestDispatcher("deposito.jsp");
				rd.forward(request, response);
				return;
			}
			boolean sucesso = contaLogada.depositar(valor);
			
			if(sucesso) {
				dao.transacao(contaLogada);
				response.sendRedirect("main.jsp");
			}else {
				request.setAttribute("erro", "Insira um valor valido");
				RequestDispatcher rd = request.getRequestDispatcher("deposito.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
}
