package de.mas.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import de.mas.ejb.osgiUtil.MyHelloServiceTrackerCustomizer;
import de.mas.service.MyHelloService;

/**
 * Session Bean implementation class OSGiUsingEJB
 */
@Stateless(name="OSGiUsingEJB")
@LocalBean
public class OSGiUsingEJB implements OSGiUsingEJBLocal {

	@Resource
	private BundleContext bundleContext;
	
	private MyHelloServiceTrackerCustomizer myCustomizer = null;
	 
	@PostConstruct
	public void init() {
		final OSGiUsingEJB bean = this;
		myCustomizer = new MyHelloServiceTrackerCustomizer(bundleContext);
		ServiceTracker tracker = new ServiceTracker(bundleContext, MyHelloService.class.getName(), myCustomizer);
		tracker.open();
	}
    /**
     * Default constructor. 
     */
    public OSGiUsingEJB() {    }
    
    public String sayHi() {
		return "Hi. I'm here.";
	}

    public String repetitiveCallOSGiService() {
    	while (true) {
    		callOSGiService();
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public String callOSGiService() {
    	if (myCustomizer.myHelloServiceAvailable()) {
    		MyHelloService service = myCustomizer.getService();
    		System.out.println("OSGiUsingEJB.callOSGiService: " + service.sayHello());
    		return myCustomizer.getServicename() + "-" + myCustomizer.getServiceVersion() + ":  " + service.sayHello();
    	}
    	return "MyHelloService not available.";
	}
}
