package barbearia;

import static barbearia.MarcaTempo.tempo;
import static barbearia.CortarCabelo.caso;
import static barbearia.Tainha.estado;
import static barbearia.AcessoaBarbearia.salaoUnissex;

import static barbearia.CortarCabelo.numAtendimentoOficiais;
import static barbearia.CortarCabelo.numAtendimentoSargentos;
import static barbearia.CortarCabelo.numAtendimentoCabos;

import static barbearia.CortarCabelo.tempoAtendimentoOficiais;
import static barbearia.CortarCabelo.tempoAtendimentoSargentos;
import static barbearia.CortarCabelo.tempoAtendimentoCabos;

import static barbearia.CortarCabelo.tempoEsperaOficiais;
import static barbearia.CortarCabelo.tempoEsperaSargentos;
import static barbearia.CortarCabelo.tempoEsperaCabos;

import static barbearia.AcessoaBarbearia.numTotalOficiais;
import static barbearia.AcessoaBarbearia.numTotalSargentos;
import static barbearia.AcessoaBarbearia.numTotalCabos;
import static barbearia.AcessoaBarbearia.numTotalPausas;

public class Escovinha extends Thread{
    public static double mediaOcupaFilaOficiais, mediaOcupaFilaSargentos, mediaOcupaFilaCabos, mediaCadeirasLivres;    
    public static double mediaTamFilaOficiais, mediaTamFilaSargentos, mediaTamFilaCabos;   
    public static double mediaTempoAtOficiais, mediaTempoAtSargentos, mediaTempoAtCabos;  
    public static double mediaTempoEspOficiais, mediaTempoEspSargentos, mediaTempoEspCabos;  
    public static int totalAtOficiais, totalAtSargentos, totalAtCabos; 
    public static int totalOficiais, totalSargentos, totalCabos, totalPausas;
     
    public double percentual(int tamanhoFila, int quantidade){
        return (((tamanhoFila/quantidade)*100)/20);
    }

    public double comprimentoMedio(int tamanhoFila, int quantidade){
    	if(quantidade==0){
        	return 0;
    	}else{
    		return (tamanhoFila/quantidade) ;
    	}
    }

    public double calculaTempo(int tempoCategoria, int qtdCategoria){
        if(qtdCategoria==0){
        	return 0;
        }else{
        	return (tempoCategoria)/(qtdCategoria);
        }
    }
    
    public void EscovinhaBabearia() throws InterruptedException{
        // já que pode ser verificado a cada 3 segundos não sabemos quantas vezes será feita a verificação
        int quantidadeVerificacao, tamanhoFilaOficiais, tamanhoFilaSargentos, tamanhoFilaCabos, quantCadeirasLivres;
        boolean verifica = true;
        
        
        quantidadeVerificacao = 0;
        tamanhoFilaOficiais = 0;
        tamanhoFilaSargentos = 0;
        tamanhoFilaCabos = 0;
        quantCadeirasLivres = 0;


        while(verifica){
        	if(Tainha.estado!=2){
               
	        	// incrementa a quantidade de verificaçãoes
	            quantidadeVerificacao++;
	            /// somando o tamanho a cada chamada de relatório
	            tamanhoFilaOficiais     += AcessoaBarbearia.salaoUnissex.filaOficiais.size();
	            tamanhoFilaSargentos    += AcessoaBarbearia.salaoUnissex.filaSargentos.size();
	            tamanhoFilaCabos        += AcessoaBarbearia.salaoUnissex.filaCabos.size();
	
	            // realiza o calculo para a quantidade de cadeiras livres
	            quantCadeirasLivres = quantCadeirasLivres + (20 - Barbearia.retorneOcupacaoBarbearia());
            }else{
            	verifica = false;
            }
     
            Thread.sleep(30);
        }
  
        //Estado de ocupação da(s) cadeira(s) (% por categoria e livre)
        Escovinha.mediaOcupaFilaOficiais    = percentual( tamanhoFilaOficiais, quantidadeVerificacao);
        Escovinha.mediaOcupaFilaSargentos   = percentual( tamanhoFilaSargentos, quantidadeVerificacao);
        Escovinha.mediaOcupaFilaCabos       = percentual( tamanhoFilaCabos, quantidadeVerificacao);
        Escovinha.mediaCadeirasLivres      = percentual( quantCadeirasLivres, quantidadeVerificacao);

        // Calcula Comprimento Medio das Filas
        Escovinha.mediaTamFilaOficiais     = comprimentoMedio(tamanhoFilaOficiais, quantidadeVerificacao);
        Escovinha.mediaTamFilaSargentos    = comprimentoMedio(tamanhoFilaSargentos, quantidadeVerificacao);
        Escovinha.mediaTamFilaCabos        = comprimentoMedio(tamanhoFilaCabos, quantidadeVerificacao);

       	//Calcula o tempo medio de Atendimento de cada Categoria
        Escovinha.mediaTempoAtOficiais    = calculaTempo(CortarCabelo.tempoAtendimentoOficiais,CortarCabelo.numAtendimentoOficiais);
        Escovinha.mediaTempoAtSargentos   = calculaTempo(CortarCabelo.tempoAtendimentoSargentos,CortarCabelo.numAtendimentoSargentos);
        Escovinha.mediaTempoAtCabos       = calculaTempo(CortarCabelo.tempoAtendimentoCabos,CortarCabelo.numAtendimentoCabos);

        // Calcula o tempo medio de Espera de cada Categoria
        Escovinha.mediaTempoEspOficiais = calculaTempo(CortarCabelo.tempoEsperaOficiais,CortarCabelo.numAtendimentoOficiais);
        Escovinha.mediaTempoEspSargentos = calculaTempo(CortarCabelo.tempoEsperaOficiais,CortarCabelo.numAtendimentoOficiais);
        Escovinha.mediaTempoEspCabos = calculaTempo(CortarCabelo.tempoEsperaOficiais,CortarCabelo.numAtendimentoOficiais);

        // Calcula o Numero Total  de Clientes por categoria
        Escovinha.totalAtOficiais  = CortarCabelo.numAtendimentoOficiais;
        Escovinha.totalAtSargentos = CortarCabelo.numAtendimentoSargentos;
        Escovinha.totalAtCabos     = CortarCabelo.numAtendimentoCabos;
        
        //Calcula o Numero Total de Clientes gerados
        Escovinha.totalOficiais = AcessoaBarbearia.numTotalOficiais;
        Escovinha.totalSargentos = AcessoaBarbearia.numTotalSargentos;
        Escovinha.totalCabos = AcessoaBarbearia.numTotalCabos;
        Escovinha.totalPausas = AcessoaBarbearia.numTotalPausas;

        imprimeEscovinha();
    }
    
    public static void imprimeEscovinha(){
	    System.out.println("Estado de ocupação da(s) cadeira(s):\n ");
	    System.out.println("Oficiais:"+           Escovinha.mediaOcupaFilaOficiais);
	    System.out.println("Sargentos:"+          Escovinha.mediaOcupaFilaSargentos);
	    System.out.println("Cabos:"+              Escovinha.mediaOcupaFilaCabos);
	    System.out.println("Cadeiras Livres: "+   Escovinha.mediaCadeirasLivres);
	    
	    System.out.println("\nComprimento Medio das Filas:\n ");
	    System.out.println("Oficiais: "+          Escovinha.mediaTamFilaOficiais);
	    System.out.println("Sargentos: "+         Escovinha.mediaTamFilaSargentos);
	    System.out.println("Cabos: "+             Escovinha.mediaTamFilaCabos);
	    
	    System.out.println("\nTempo Medio de Atendimento:\n ");
	    System.out.println("Oficiais: "+          Escovinha.mediaTempoAtOficiais);
	    System.out.println("Sargentos: "+         Escovinha.mediaTempoAtSargentos);
	    System.out.println("Cabos: "+             Escovinha.mediaTempoAtCabos);
	    
	    System.out.println("\nTempo Medio de Espera:\n ");
	    System.out.println("Oficiais: "+          Escovinha.mediaTempoEspOficiais);
	    System.out.println("Sargentos: "+         Escovinha.mediaTempoAtSargentos);
	    System.out.println("Cabos: "+             Escovinha.mediaTempoEspCabos);
	    
	    System.out.println("\nNumero de Atendimentos por Categoria:\n ");
	    System.out.println("Oficiais: "+          Escovinha.totalAtOficiais);
	    System.out.println("Sargentos: "+         Escovinha.totalAtSargentos);
	    System.out.println("Cabos: "+             Escovinha.totalAtCabos);
	    
	    System.out.println("\nNumero de Clientes por Categoria:\n ");
	    System.out.println("Oficiais: "+          Escovinha.totalOficiais);
	    System.out.println("Sargentos: "+         Escovinha.totalSargentos);
	    System.out.println("Cabos: "+             Escovinha.totalCabos);
	    System.out.println("Pausas: "+            Escovinha.totalPausas);
	    
	    
}
    
    public void run() {
        try {
            EscovinhaBabearia();
        } catch (Exception e) {
            System.out.println("Merda no relogio "+ e);
            e.printStackTrace();
        }
        
    }

}
