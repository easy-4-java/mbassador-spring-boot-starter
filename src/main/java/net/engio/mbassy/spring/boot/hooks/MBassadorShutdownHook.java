package net.engio.mbassy.spring.boot.hooks;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.spring.boot.event.MBassadorEvent;

/**\n * Shutdown hook for MBassador event bus cleanup.\n *\n * @author <a href="https://github.com/loong10k">Loong Wan</a>\n * @since 1.0.0\n */
public class MBassadorShutdownHook extends Thread{
	
	private MBassador<MBassadorEvent> mbassador;
	
	public MBassadorShutdownHook(MBassador<MBassadorEvent> mbassador) {
		this.mbassador = mbassador;
	}
	/**
	 * <p>Run.</p>
	 */
	
	@Override
	public void run() {
		mbassador.shutdown();
	}
	
}
