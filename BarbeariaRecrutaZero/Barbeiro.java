package barbearia;

class Barbeiro{
	int id;
	boolean status;
	String nome[] = {"NINGUEM", "RECRUTAZERO", "DENTINHO", "OTTO"};

	Barbeiro(int id){
		this.id = id;
		this.status = true;
	}

	public boolean getEstado(){
		return this.status;
	}

	public void setEstado(boolean newEstado){
		this.status = newEstado;
	}
}
