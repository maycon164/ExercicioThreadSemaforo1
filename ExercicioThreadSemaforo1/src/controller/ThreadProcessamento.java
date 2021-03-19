package controller;

import java.util.concurrent.Semaphore;

public class ThreadProcessamento extends Thread {
	private int id;
	private Semaphore sema;

	public ThreadProcessamento(int id, Semaphore sema) {
		this.id = id;
		this.sema = sema;
	}

	public void run() {
		verifica();
	}

	public void verifica() {
		int resto = id % 3;

		if (resto == 1) {
			int tempo = (int) (Math.random() * 801) + 200;
			realizarOperacoes(2, tempo, 1000);
		
		} else if (resto == 2) {
			
			int tempo = (int) (Math.random() * 1001) + 500;
			realizarOperacoes(3, tempo, 1500);
		
		} else {
			
			int tempo = (int) (Math.random() * 1001) + 1000;
			realizarOperacoes(3, tempo, 1500);
			
		}
	}

	private void realizarOperacoes(int n, int tempo, int tempoTransacao) {
		for (int vezes = 0; vezes < n; vezes++) {	
			calculos(tempo);
			transacao(tempoTransacao);
		}
	}
	
	private void calculos(int tempo) {
		try {
			System.out.println("Thread #" + id + " processando....");
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void transacao(int tempo) {
		try {
			sema.acquire();
			System.out.println("Thread #" + id + " transação de BD...");
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sema.release();
		}
	}
}
