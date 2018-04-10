package barbearia;
import java.util.concurrent.Semaphore;
import static barbearia.MarcaTempo.tempo;
import static barbearia.AcessoaBarbearia.semaforo;
import static barbearia.Tainha.estado;


class CortarCabelo extends Thread{
	static char caso;
	static int numAtendimentoOficiais, numAtendimentoSargentos, numAtendimentoCabos;
	static int tempoAtendimentoOficiais, tempoAtendimentoSargentos, tempoAtendimentoCabos;
	static int tempoEsperaOficiais, tempoEsperaSargentos, tempoEsperaCabos;

	CortarCabelo(char caso){
		CortarCabelo.caso = caso;
	}

	public static void caso1()throws InterruptedException{
		Barbeiro recrutaZero = new Barbeiro(1);
		Semaphore semaforo = new Semaphore(1);
		int tempoServico;

		while(Barbearia.retorneOcupacaoBarbearia()!=0 || Tainha.estado!=2){
			semaforo.acquire();
			tempoServico = 0;

			if(Barbearia.filaOficiais.size()!=0){
				Cliente naVez = Barbearia.filaOficiais.get(0);
				tempoServico = naVez.tempoAtendimento;
				tempoAtendimentoOficiais = tempoAtendimentoOficiais + naVez.tempoAtendimento;
				tempoEsperaOficiais = tempoEsperaOficiais + naVez.tempoEspera;
				numAtendimentoOficiais++;
				System.out.println("O barbeiro " + recrutaZero.nome[recrutaZero.id] + " estah atendendo um oficial !");
				Barbearia.filaOficiais.remove(0);
			}else if(Barbearia.filaSargentos.size()!=0){
				Cliente naVez = Barbearia.filaSargentos.get(0);
				tempoServico = naVez.tempoAtendimento;
				tempoAtendimentoSargentos = tempoAtendimentoSargentos + naVez.tempoAtendimento;
				tempoEsperaSargentos = tempoEsperaSargentos + naVez.tempoEspera;
				numAtendimentoSargentos++;
				System.out.println("O barbeiro " + recrutaZero.nome[recrutaZero.id] + " estah atendendo um sargento !");
				Barbearia.filaSargentos.remove(0);
			}else if(Barbearia.filaCabos.size()!=0){
				Cliente naVez = Barbearia.filaCabos.get(0);
				tempoServico = naVez.tempoAtendimento;
				tempoAtendimentoCabos = tempoAtendimentoCabos + naVez.tempoAtendimento;
				tempoEsperaCabos = tempoEsperaCabos = naVez.tempoEspera;
				numAtendimentoCabos++;
				System.out.println("O barbeiro " + recrutaZero.nome[recrutaZero.id] + " estah atendendo um cabo !");
				Barbearia.filaCabos.remove(0);
			}
			semaforo.release();
			Thread.sleep(tempoServico*10);
		}
	}

	public static void caso2()throws InterruptedException{
		Barbeiro recrutaZero = new Barbeiro(1);
		Barbeiro dentinho = new Barbeiro(2);
		Semaphore semaforo = new Semaphore(2);
		int tempoServicoZero, tempoServicoDentinho;

		while(Barbearia.retorneOcupacaoBarbearia()!=0 || Tainha.estado!=2){
			semaforo.acquire();
			tempoServicoZero = 0;
			tempoServicoDentinho = 0;
					
			if(Barbearia.filaOficiais.size()!=0){
				Cliente naVez = Barbearia.filaOficiais.get(0);
				tempoServicoZero = naVez.tempoAtendimento;
				tempoAtendimentoOficiais = tempoAtendimentoOficiais + naVez.tempoAtendimento;
				tempoEsperaCabos = tempoEsperaCabos = naVez.tempoEspera;
				numAtendimentoOficiais++;
				System.out.println("O barbeiro " + recrutaZero.nome[recrutaZero.id] + " estah atendendo um oficial !");
				recrutaZero.setEstado(true);
				
				if(naVez.prox!=null){
					Cliente naVez2 = Barbearia.filaOficiais.get(1);
					tempoServicoDentinho = naVez2.tempoAtendimento;
					tempoAtendimentoOficiais = tempoAtendimentoOficiais + naVez2.tempoAtendimento;
					tempoEsperaOficiais = tempoEsperaOficiais = naVez2.tempoEspera;
					numAtendimentoOficiais++;
					System.out.println("O barbeiro " + dentinho.nome[dentinho.id] + " estah atendendo um oficial !");
					dentinho.setEstado(true);
					Barbearia.filaOficiais.remove(1);
				}else{
					dentinho.setEstado(false);
				}
				Barbearia.filaOficiais.remove(0);
			}else if(Barbearia.filaSargentos.size()!=0){
				Cliente naVez = Barbearia.filaSargentos.get(0);
				tempoServicoZero = naVez.tempoAtendimento;
				tempoAtendimentoSargentos = tempoAtendimentoSargentos + naVez.tempoAtendimento;
				tempoEsperaSargentos = tempoEsperaSargentos + naVez.tempoEspera;
				numAtendimentoSargentos++;
				System.out.println("O barbeiro " + recrutaZero.nome[recrutaZero.id] + " estah atendendo um sargento !");
				recrutaZero.setEstado(true);
				
				if(naVez.prox!=null){
					Cliente naVez2 = Barbearia.filaSargentos.get(1);
					tempoServicoDentinho = naVez2.tempoAtendimento;
					tempoAtendimentoSargentos = tempoAtendimentoSargentos + naVez2.tempoAtendimento;
					tempoEsperaSargentos = tempoEsperaSargentos + naVez2.tempoEspera;
					numAtendimentoCabos++;
					System.out.println("O barbeiro " + dentinho.nome[dentinho.id] + " estah atendendo um sargento !");
					dentinho.setEstado(true);
					Barbearia.filaSargentos.remove(1);
				}else{
					dentinho.setEstado(false);
				}
				Barbearia.filaSargentos.remove(0);
			}else if(Barbearia.filaCabos.size()!=0){
				Cliente naVez = Barbearia.filaCabos.get(0);
				tempoServicoZero = naVez.tempoAtendimento;
				tempoAtendimentoCabos = tempoAtendimentoCabos + naVez.tempoAtendimento;
				tempoEsperaCabos = tempoEsperaCabos = naVez.tempoEspera;
				numAtendimentoCabos++;
				System.out.println("O barbeiro " + recrutaZero.nome[recrutaZero.id] + " estah atendendo um cabo !");
				
				if(naVez.prox!=null){
					Cliente naVez2 = Barbearia.filaCabos.get(1);
					tempoServicoDentinho = naVez2.tempoAtendimento;
					tempoAtendimentoCabos = tempoAtendimentoCabos + naVez2.tempoAtendimento;
					tempoEsperaCabos = tempoEsperaCabos = naVez2.tempoEspera;
					numAtendimentoCabos++;
					System.out.println("O barbeiro " + dentinho.nome[dentinho.id] + " estah atendendo um cabo !");
					Barbearia.filaCabos.remove(1);
				}
				Barbearia.filaCabos.remove(0);
			}
			
			semaforo.release();
			Thread.sleep(tempoServicoZero*10);
			Thread.sleep(tempoServicoDentinho*10);
		}
	}

	public static void caso3() throws InterruptedException{
		Barbeiro recrutaZero, dentinho, otto;
		Semaphore semaforo = new Semaphore(3);
		int tempoServicoZero, tempoServicoDentinho, tempoServicoOtto;

		recrutaZero = new Barbeiro(1);
		dentinho = new Barbeiro(2);
		otto = new Barbeiro(3);

		while(Barbearia.retorneOcupacaoBarbearia()!=0 || Tainha.estado!=2){
			semaforo.acquire();
			tempoServicoZero = 0;
			tempoServicoDentinho = 0;
			tempoServicoOtto = 0;
						
			if(Barbearia.filaOficiais.size()!=0){
				Cliente naVez = Barbearia.filaOficiais.get(0);
				tempoServicoZero = naVez.tempoAtendimento;
				tempoAtendimentoOficiais = tempoAtendimentoOficiais + naVez.tempoAtendimento;
				tempoEsperaOficiais = tempoEsperaOficiais + naVez.tempoEspera;
				numAtendimentoOficiais++;
				System.out.println("O barbeiro " + recrutaZero.nome[recrutaZero.id] + " estah atendendo um oficial !");
				Barbearia.filaOficiais.remove(0);
				recrutaZero.setEstado(true);
			}else{
				recrutaZero.setEstado(false);
			}

			if(Barbearia.filaSargentos.size()!=0){
				Cliente naVez2 = Barbearia.filaSargentos.get(0);
				tempoServicoDentinho = naVez2.tempoAtendimento;
				tempoAtendimentoOficiais = tempoAtendimentoOficiais + naVez2.tempoAtendimento;
				tempoEsperaSargentos = tempoEsperaSargentos + naVez2.tempoEspera;
				numAtendimentoSargentos++;
				System.out.println("O barbeiro " + dentinho.nome[dentinho.id] + " estah atendendo um sargento !");
				Barbearia.filaSargentos.remove(0);
				dentinho.setEstado(true);
			}else{
				dentinho.setEstado(false);
			}

			if(Barbearia.filaCabos.size()!=0){
				Cliente naVez3 = Barbearia.filaCabos.get(0);
				tempoServicoOtto = naVez3.tempoAtendimento;
				tempoAtendimentoCabos = tempoAtendimentoCabos + naVez3.tempoAtendimento;
				tempoEsperaCabos = tempoEsperaCabos = naVez3.tempoEspera;
				numAtendimentoCabos++;
				System.out.println("O barbeiro " + otto.nome[otto.id] + " estah atendendo um cabo !");
				Barbearia.filaCabos.remove(0);
				otto.setEstado(true);
			}else{
				otto.setEstado(false);
			}
			semaforo.release();
			Thread.sleep(tempoServicoZero*10);
			Thread.sleep(tempoServicoDentinho*10);
			Thread.sleep(tempoServicoOtto*10);
		}
	}

	public void run(){
		try{
			switch(caso){
			case '1': caso1();

			case '2': caso2();

			case '3': caso3();
			}
		}catch (Exception e){
			AcessoaBarbearia.semaforo.release();
			e.printStackTrace();
		}
	}
}