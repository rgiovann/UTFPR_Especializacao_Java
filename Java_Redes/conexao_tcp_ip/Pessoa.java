package local.redes;

import java.io.Serializable;
/**
 * Classe que representa uma pessoa com atributos de nome e idade, implementando serialização para comunicação em rede.
 * 
 * @author Giovanni L. Rozza
 * @version 1.0
 */
public class Pessoa implements Serializable {
 
	private static final long serialVersionUID = -8872789790808516176L;
	
	private String nome;
	private Integer idade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Pessoa(String nome, Integer idade) {
		super();
		this.nome = nome;
		this.idade = idade;
	}
	
	

}
