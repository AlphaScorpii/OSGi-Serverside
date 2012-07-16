Two libraries and an OSGi-service interface are mandatory for building this projekt:
	1) org.osgi.core-4.2.0.jar
	2) org.osgi.enterprise-4.2.0.jar
	3) OSGi-bundle 'MyHelloServiceInterface'
	
The jars above should be copied or linked from the target applicationserver.

In case of jboss-as-7.1.1.Final these jars can bo found here:
	<JBoss installation path>\modules\org\osgi\core\main\org.osgi.core-4.2.0.jar
	<JBoss installation path>\bundles\org\osgi\enterprise\main\org.osgi.enterprise-4.2.0.jar
	
The bundle 'MyHelloServiceInterface' must be referenced from the app-server to.
Open the OSGi-bundle-project 'de.mas.study.osgi.myHelloServiceInterface' in the Eclipse-RCP-IDE
and export the bundle into the app-server (deployment maybe appserver-specific).