package br.com.italo.faculdade.SpringBoot.model;

public enum TipoTelefone {
FIXO(1),
CELULAR(2),
EMPRESARIAL(3),
RESIDENCIAL (4),
OUTROS(10);

	private final int tipo;
	
	TipoTelefone(int tipo){
		this.tipo = tipo;
	}
	
	public int getTipo() {
		return tipo;
	}
}
