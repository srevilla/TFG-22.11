package es.ubu.inf.tfg.generador;

import es.ubu.inf.tfg.dominio.BancoPreguntas;

public interface GeneradorBancoPreguntas <T> {
	
    BancoPreguntas generarBancoPreguntas(T config);

}
