package de.mas.myyellowsnowbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class YellingActivator implements BundleActivator {

	private static BundleContext context;

	private static Yeller yeller = null;
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		YellingActivator.context = bundleContext;
		yeller = new Yeller();
		new Thread(yeller).start();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		yeller.setActive(false);
		YellingActivator.context = null;		
	}

}
