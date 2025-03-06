package model;

public class ContaBancaria {
	private int numeroConta;
	private String titular;
	private double saldo;
	
	public ContaBancaria() {
		
	}
	public ContaBancaria(int numeroConta, String titular) {
		this.numeroConta = numeroConta;
		this.titular = titular;
		this.saldo = 0.0d;
	}
	
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getTitular() {
		return this.titular;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public int getNumeroConta() {
		return this.numeroConta;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getSaldo() {
		return this.saldo;
	}
	public boolean sacar(double valorSaque) {
		if(valorSaque > 0.0d && valorSaque <= this.saldo) {
			this.saldo -= valorSaque;
			return true;
		}else {
			return false;
		}
	}
	public boolean depositar(double valorDeposito) {
		if(valorDeposito > 0.0d) {
			this.saldo += valorDeposito;
			return true;
		}else {
			return false;
		}
	}
}
