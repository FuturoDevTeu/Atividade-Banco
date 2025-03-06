package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ContaBancaria;
public class Dao {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/estudo";
	private String user = "root";
	private String password = "";
	private Connection con;
	
	public Connection conectar(){
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public void adicionarConta(ContaBancaria conta) {
		String sql = "INSERT INTO conta(titular, saldo) VALUES (? ,?)";
		try {
			con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, conta.getTitular());
			ps.setDouble(2, conta.getSaldo());
			ps.executeUpdate();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<ContaBancaria> listarContas() {
		ArrayList<ContaBancaria> lista = new ArrayList<>();
		String sql = "SELECT * FROM conta";
		try {
			con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ContaBancaria conta = new ContaBancaria();
				conta.setNumeroConta(rs.getInt(1));
				conta.setTitular(rs.getString(2));
				conta.setSaldo(rs.getDouble(3));
				lista.add(conta);
			}
			con.close();
			return lista;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public ContaBancaria listaConta(ContaBancaria conta) {
		String sql = "SELECT * FROM conta WHERE numeroConta=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, conta.getNumeroConta());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				conta.setNumeroConta(rs.getInt(1));
				conta.setTitular(rs.getString(2));
				conta.setSaldo(rs.getDouble(3));
			}
			return conta;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public void transacao(ContaBancaria conta) {
		String sql = "UPDATE conta SET saldo=? WHERE numeroConta=?";
		try {
			con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, conta.getSaldo());
			ps.setInt(2, conta.getNumeroConta());
			ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	 public ContaBancaria buscarContaPorTitular(String titular) {
	        String sql = "SELECT * FROM conta WHERE titular = ?";
	        
	        try (Connection con = conectar();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            
	            ps.setString(1, titular);
	            try (ResultSet rs = ps.executeQuery()) {
	                if(rs.next()) {
	                    ContaBancaria conta = new ContaBancaria();
	                    conta.setNumeroConta(rs.getInt("numeroConta"));
	                    conta.setTitular(rs.getString("titular"));
	                    conta.setSaldo(rs.getDouble("saldo"));
	                    return conta;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro na busca por titular: " + e.getMessage());
	        }
	        return null;
	    }
}
