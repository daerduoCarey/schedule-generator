package htmlInterfaces;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dummy dum;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() 
    {
        super();
        dum = new Dummy("Dumdum",128);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.print(HTMLUtils.toHTMLTable(HTMLInterfaceTool.fetchTableContent(dum)).write());    
	}



}
