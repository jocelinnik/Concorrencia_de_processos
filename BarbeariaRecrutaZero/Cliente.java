package barbearia;

class Cliente implements Runnable{
	int patente;
	int tempoAtendimento;
	int tempoEspera;
	Cliente prox;

	Cliente(int patente, int tempoAtendimento, int tempoEspera){
		this.patente = patente;
		this.tempoAtendimento = tempoAtendimento;
		this.tempoEspera = tempoEspera;
	}

	public void run(){
		
	}
}