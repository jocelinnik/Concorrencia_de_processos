package barbearia;

import barbearia.AcessoaBarbearia;
import barbearia.Escovinha;
import barbearia.MarcaTempo;
import java.util.concurrent.Semaphore;
import java.io.IOException;

public class Main{
	public static void main(String[] args)throws IOException {
        char caso;
        
		System.out.println("Digite qual caso deseja executar:\n1: um barbeiro \n2: dois barbeiros \n3: tres barbeiros\n");
		caso = (char)System.in.read();
		
		
		
        AcessoaBarbearia salaoUnissex = new AcessoaBarbearia();
        MarcaTempo relogio = new MarcaTempo();
        CortarCabelo teste = new CortarCabelo(caso);
        Escovinha relatorio = new Escovinha();
        
        relogio.start();
        salaoUnissex.start();
        teste.start();
        relatorio.start();
        
        //relatorio.imprimeEscovinha();
    }
}