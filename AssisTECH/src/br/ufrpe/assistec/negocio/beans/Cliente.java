package br.ufrpe.assistec.negocio.beans;

import java.io.Serializable;

public class Cliente extends Usuario implements Comparable<Cliente>, Serializable{

	private String cpf;


	public Cliente(String usrName, String psswd, String cpf, String primeiroNome,String segundoNome, String endereco, String telefone, String email, int numOrdens) {
		super(usrName, psswd, primeiroNome, segundoNome, email, telefone, endereco, numOrdens);
		this.cpf = cpf;
	}

	public Cliente(String cpf) {
		this.cpf = cpf;
	}

	public Cliente () {

	}

	public String getCpf() {
		return this.cpf;
	}
	
	public String getNomeCompleto() {
		return this.primeiroNome + " " + this.segundoNome;
	}
	
	/*public boolean equals(Cliente cli) {
		if(this.cpf.equals(anObject))
	}*/

	public String toString() {
		String resultado = String.format("%5s %5s\n", "cpf: ", this.cpf);
		resultado += super.toString();
		resultado += String.format("%5s %5s\n", "Endere�o: ", this.endereco);

		return resultado;
	}

	public static void main(String[] args) {
		Cliente c1 = new Cliente("032890098-78", "Jo�o", "da Silva J�nior", "Rua Le�o Coroado, 109, Jardim Brasil, Olinda - PE", "3729-0452", "joao_da_silva_37@yahoo.com.br", 12);

		System.out.println(c1);

	}

	@Override
	public int compareTo(Cliente cli) {
		
		return this.getNomeCompleto().compareTo(cli.getNomeCompleto());
	}

}
