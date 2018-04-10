package barbearia;

public class MarcaTempo extends Thread{
	static int tempo;

	public void run(){
		try{
			while(true && Tainha.estado!= 2){
				tempo = tempo + 1;
				Thread.sleep(1000);
			}
		}catch(Exception atraso){
			System.out.println("Relogio atrasado" + atraso);
		}
	}
}