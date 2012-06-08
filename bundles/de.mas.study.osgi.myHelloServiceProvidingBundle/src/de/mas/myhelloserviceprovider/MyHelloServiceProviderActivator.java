package de.mas.myhelloserviceprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.mas.service.MyHelloService;
import de.mas.service.impl.MyHelloServiceImpl;

public class MyHelloServiceProviderActivator implements BundleActivator {

	private static BundleContext bundleContext;
//	private ServiceRegistration<MyHelloService> registration;
	private ServiceRegistration registration;
	
	static BundleContext getContext() {
		return bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext aBundleContext) throws Exception {
		MyHelloServiceProviderActivator.bundleContext = aBundleContext;
		System.out.println("[HelloService-Provider] MyHelloService-Bundle started.");
		registerMyHelloService(bundleContext);
		System.out.println("[HelloService-Provider] MyHelloService registered.");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext aBundleContext) throws Exception {
		this.registration.unregister(); //automatic
		MyHelloServiceProviderActivator.bundleContext = null;
		System.out.println("MyHelloService: Bundle stopped.");
	}

	private void registerMyHelloService(BundleContext aBundleContext) throws InterruptedException {
		System.out.println("[HelloService-Provider] Registering service ... ");
		MyHelloService service = new MyHelloServiceImpl();
		this.registration = bundleContext.registerService(MyHelloService.class.getName(), service, null);
		System.out.println("[HelloService-Provider] ready." + registration);
		
	}
}
