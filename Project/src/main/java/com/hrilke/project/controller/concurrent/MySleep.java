package com.hrilke.project.controller.concurrent;

public class MySleep {

	public static void mySleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
