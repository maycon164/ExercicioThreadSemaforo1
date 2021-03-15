package view;

import java.util.concurrent.Semaphore;

import controller.ThreadProcessamento;

public class Programa {
	public static void main(String[] args) {
		Semaphore sema = new Semaphore(1);
		
		for(int x = 1; x <= 21; x++) {
			Thread cc = new ThreadProcessamento(x, sema);
			cc.start();
		}
	}
}
