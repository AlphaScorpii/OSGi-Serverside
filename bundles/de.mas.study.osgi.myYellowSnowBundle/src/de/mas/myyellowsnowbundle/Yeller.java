package de.mas.myyellowsnowbundle;

public final class Yeller implements Runnable {

	private boolean active = true;
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void run() {
		while (active)
		{
			System.out.println("Look ma, yellow snow.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Okay.");
	}

}
