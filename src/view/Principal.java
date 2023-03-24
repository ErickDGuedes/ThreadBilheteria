package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBilheteria;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 100;
		Semaphore semaforo = new Semaphore(permissoes);
		//Pessoas Comprando
		for(int i=1; i<301 ; i++) {
			ThreadBilheteria bilh = new ThreadBilheteria(semaforo, "Pessoa", i);
			bilh.start();
		}
	}
}
