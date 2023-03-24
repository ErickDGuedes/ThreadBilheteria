package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadBilheteria extends Thread {
	
	Semaphore semaforo;
	String pessoa;
	int numPessoa;
	//Construtor
	public ThreadBilheteria(Semaphore semaforo, String pessoa, int numPessoa) {
		this.semaforo = semaforo;
		this.pessoa = pessoa;
		this.numPessoa = numPessoa;
	}
	//Método Run()
	public void run() {
		Login();
		Comprar();
		try {
			semaforo.acquire();
			Validacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
	//Ingressos
	static int qtdeIngressos = 100;
	//Login
	public void Login() {
		Random random = new Random();
		double tempoLogin = random.nextDouble(0, 3);
		if(tempoLogin > 1) {
			FimLogin();
		}else if(tempoLogin < 1){
			System.out.println(pessoa+" "+numPessoa+" Login Efetuado");
			
		}
	}
    //Comprando Ingresso
	public void Comprar() {
		Random random = new Random();
		int tempoCompra = random.nextInt(1, 4);
		if(qtdeIngressos > 1) {
			System.out.println(pessoa+" "+numPessoa+" Comprando Ingresso(s)...");
		}else {
			System.out.println(pessoa+" "+numPessoa+" Ingressos Indisponíveis");
		}
	}
	//Validando Qtde de Ingressos 
	public void Validacao() {
		Random random = new Random();
		int ingresso = random.nextInt(1, 5);
		if (qtdeIngressos > 1){
			System.out.println(pessoa+" "+numPessoa+" Comprou ==> "+ingresso+" Ingresso(s)");
			qtdeIngressos -= ingresso;
		}
		if(qtdeIngressos == 0) {
			System.out.println(pessoa+" "+numPessoa+" Ingressos Indisponíveis");
		}
	}
	//Caso a pessoa não conseguiu fazer o Login
	public void FimLogin() {
		System.out.println(pessoa+" "+numPessoa+" Não foi possível efetuar o login. Tente Novamente");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			Login();
		}
	}
	
}
