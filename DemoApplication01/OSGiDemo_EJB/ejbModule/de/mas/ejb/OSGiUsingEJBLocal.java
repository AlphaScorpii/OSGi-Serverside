package de.mas.ejb;

import javax.ejb.Local;

@Local
public interface OSGiUsingEJBLocal {
	
	public String sayHi();
	
	public String callOSGiService();
	
	public String repetitiveCallOSGiService();
	
}
