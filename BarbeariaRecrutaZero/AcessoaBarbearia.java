package barbearia;

import static barbearia.MarcaTempo.tempo;
import static barbearia.Tainha.estado;
import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class AcessoaBarbearia extends Thread{
	Cliente suaVez;
	static Barbearia salaoUnissex;
	MarcaTempo MarcaTempo;
	static Semaphore semaforo;
	static int numTotalOficiais, numTotalSargentos, numTotalCabos, numTotalPausas;

	AcessoaBarbearia(){
		salaoUnissex = new Barbearia();
		semaforo = new Semaphore(1);
	}

	public void entrarNaBarbearia() throws InterruptedException{
		Random gerador = new Random();
		int tempoCochiloTainha, cont;
		int patente, gambi, tempoAtendimento, aux;
		ArrayList<Integer> filaNaPorta = new ArrayList<Integer>(); 

		tempoCochiloTainha = 1 + gerador.nextInt(5);
		try{
			for(cont=0;cont<1000;cont++){
				aux = gerador.nextInt(4);
				filaNaPorta.add(aux);
			}
			 gambi = 0;
			while((filaNaPorta.size()!=0) && gambi<3){
				Tainha.estado = 1; // TAINHA ESTAH ACORDADO E PRONTO PARA LIBERAR A ENTRADA NA BARBEARIA
				patente = filaNaPorta.get(0);
				filaNaPorta.remove(0);
				if(patente==0){
					gambi++;
					System.out.println("GEROU UMA PAUSA!");
					numTotalPausas++;
					if(gambi<0){
						gambi = 0;
					}
				}else{
					gambi--;
					if(salaoUnissex.retorneOcupacaoBarbearia()<20){
						semaforo.acquire();
						if(patente==1){
							tempoAtendimento = 4 + gerador.nextInt(3);

							suaVez = new Cliente(patente,tempoAtendimento, MarcaTempo.tempo);
							salaoUnissex.filaOficiais.add(suaVez);
							numTotalOficiais++;
						}else if(patente==2){
							tempoAtendimento = 2 + gerador.nextInt(3);

							suaVez = new Cliente(patente,tempoAtendimento, MarcaTempo.tempo);
							salaoUnissex.filaSargentos.add(suaVez);
							numTotalSargentos++;
						}else if(patente==3){
							tempoAtendimento = 1 + gerador.nextInt(3);
							suaVez = new Cliente(patente,tempoAtendimento, MarcaTempo.tempo);
							salaoUnissex.filaCabos.add(suaVez);
							numTotalCabos++;
						}
						semaforo.release();
					}else{
						System.out.println("O salao estah cheio, voltem mais tarde!");

						if(patente==1){
							numTotalOficiais++;
						}else if(patente==2){
							numTotalSargentos++;
						}else if(patente==3){
							numTotalCabos++;
						}else{
							numTotalPausas++;
						}
					}
					Thread.sleep(tempoCochiloTainha*10);
					Tainha.estado = 0; // TAINHA ESTAH DORMINDO
				}
				}

			Tainha.estado = 2; // TAINHA FOI PARA CASA
			System.out.println("O expediente de Tainha terminou!"); 
		}catch(Exception e){
			semaforo.release();
			e.printStackTrace();
		}
	}

	public void run(){
		try{
			entrarNaBarbearia();
		}catch(Exception e){
			semaforo.release();
			e.printStackTrace();
		}
	}
}