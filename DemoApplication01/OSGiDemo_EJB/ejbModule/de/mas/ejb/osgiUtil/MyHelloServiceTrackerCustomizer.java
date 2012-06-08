package de.mas.ejb.osgiUtil;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import de.mas.service.MyHelloService;

public class MyHelloServiceTrackerCustomizer implements ServiceTrackerCustomizer {

	private final BundleContext context;

	private MyHelloService service = null;
	private Version serviceVersion = null;
	private String servicename = null;
	
	public MyHelloServiceTrackerCustomizer(BundleContext context) {
		super();
		this.context = context;
	}

	public boolean myHelloServiceAvailable() {
		return (this.service != null);
	}
	
	public MyHelloService getService() {
		return service;
	}
	
	public Version getServiceVersion() {
		return serviceVersion;
	}

	public String getServicename() {
		return servicename;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println(this.getClass().getName() + ": addingService called for " + reference);
		
		final MyHelloService service = (MyHelloService)this.context.getService(reference);
		this.servicename = reference.getBundle().getSymbolicName();
		this.serviceVersion = reference.getBundle().getVersion();
		this.service = service;
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println(this.getClass().getName() + ": modifiedService called for " + reference  + " in version " + reference.getBundle().getVersion());
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		System.out.println(this.getClass().getName() + ": removedService called for " + reference + " in version " + reference.getBundle().getVersion());
		this.service = null;
		this.servicename = null;
		this.serviceVersion = null;
	}

}
