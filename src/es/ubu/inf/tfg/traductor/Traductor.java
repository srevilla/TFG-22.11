package es.ubu.inf.tfg.traductor;

import java.io.IOException;

import es.ubu.inf.tfg.dominio.BancoPreguntas;

public interface Traductor {
	
	void traducir(BancoPreguntas bancoPreguntas) throws IOException;

}
