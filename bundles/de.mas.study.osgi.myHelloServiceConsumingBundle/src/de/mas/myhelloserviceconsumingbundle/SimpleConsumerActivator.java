package de.mas.myhelloserviceconsumingbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import de.mas.service.MyHelloService;

public final class SimpleConsumerActivator implements BundleActivator {

	private static BundleContext context;
	private MyHelloService myHelloService;
	private ServiceTracker serviceTracker;
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		SimpleConsumerActivator.context = bundleContext;
//		simpleInitMyHelloService(bundleContext);
		serviceTracker = new ServiceTracker( context, MyHelloService.class.getName(),
		         new ServiceTrackerCustomizer()
		         {
		            @Override
		            public Object addingService( final ServiceReference reference )
		            {
		               final MyHelloService myHelloService = (MyHelloService) context.getService( reference );
		               System.out.println("[SimpleConsumerActivator] calling myHelloService: " + myHelloService.sayHello());
		               return myHelloService;
		            }
		            @Override
		            public void modifiedService( final ServiceReference reference, final Object service ) {/*ok*/}
		            @Override
		            public void removedService(  final ServiceReference reference, final Object service ) {/*ok*/}
		         });
	    serviceTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		SimpleConsumerActivator.context = null;
	}

}
