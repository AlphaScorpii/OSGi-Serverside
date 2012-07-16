package de.mas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.mas.ejb.OSGiUsingEJBLocal;

/**
 * Servlet implementation class RepetitiveEjbCallingServlet
 */
@WebServlet("/RepetitiveEjbCallingServlet")
public class RepetitiveEjbCallingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	static String PAGE_HEADER ="<html><head/><body>";
	static String PAGE_FOOTER ="</body></html>";
	
	private OSGiUsingEJBLocal osgiBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepetitiveEjbCallingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @EJB(name="OSGiUsingEJB")
    public void setEJB(OSGiUsingEJBLocal localBean) {
    	osgiBean = localBean;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("[Servlet] Hi, this is RepetitiveEjbCallingServlet.");
		writer.println("<p>");
		writer.println("[Servlet] Hello ejb ... ");
		writer.println("<p>");
		writer.println("[EJB] " + osgiBean.sayHi());
		writer.println("<p>");
		writer.println("[Servlet] Now invoking service on ejb ... ");
		writer.println("<p>");
		writer.println("[EJB] " + osgiBean.repetitiveCallOSGiService());
		writer.println("<p>");
		writer.println("[Servlet] demo finished.");
		writer.println(PAGE_FOOTER);
	}

}
