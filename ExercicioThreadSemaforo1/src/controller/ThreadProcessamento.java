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
			for (int vezes = 0; vezes < 2; vezes++) {
				int tempo = (int) (Math.random() * 801) + 200;
				calculos(tempo);
				transacao(1000);
			}
		} else if (resto == 2) {

			for (int vezes = 0; vezes < 3; vezes++) {
				int tempo = (int) (Math.random() * 1001) + 500;
				calculos(tempo);
				transacao(1500);
			}

		} else {
			for (int vezes = 0; vezes < 3; vezes++) {
				int tempo = (int) (Math.random() * 1001) + 1000;
				calculos(tempo);
				transacao(1500);
			}
		}
	}

	private void calculos(int tempo) {
		try {
			System.out.println("Thread #" + id + " processando....");
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void transacao(int tempo) {
		try {
			sema.acquire();
			System.out.println("Thread #" + id + " transação de BD...");
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sema.release();
		}
	}
}
