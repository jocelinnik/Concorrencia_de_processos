package barbearia;
import java.util.ArrayList;

public class Barbearia{
	static ArrayList<Cliente> filaOficiais;
	static ArrayList<Cliente> filaSargentos;
	static ArrayList<Cliente> filaCabos;
	static int ocupacao;

	Barbearia(){
		filaOficiais = new ArrayList<Cliente>();
		filaSargentos = new ArrayList<Cliente>();
		filaCabos = new ArrayList<Cliente>();
	}

	public static int retorneOcupacaoBarbearia(){
		ocupacao = (filaOficiais.size() + filaSargentos.size()) + filaCabos.size();

		return ocupacao;
	}
}