package stock;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import stock.stockclass;

/**
 * Servlet implementation class stockServlet
 */
@WebServlet("/stockServlet")
public class stockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public stockServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 String operation = request.getParameter("operation");
	        stockclass stockObject = new stockclass();
	        if (operation.equals("stockadd")) {
	        	 //int cid = Integer.parseInt(request.getParameter("cid"));
	            String cid= request.getParameter("cid");
	            String pName = request.getParameter("pName");
	            String tName = request.getParameter("tname");
	            String thold = request.getParameter("thold");
	           String bprice= request.getParameter("bprice");
	            String sprice = request.getParameter("sprice");
	           String qty = request.getParameter("qty");
	            

	            JSONObject result;
				try {
					result = stockObject.stockadd(cid, pName, tName, thold, bprice, sprice, qty);
					response.getWriter().print(result);
				} catch (JSONException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        } else if (operation.equals("updatestock")) {
	            int PID = Integer.parseInt(request.getParameter("PID"));
	           // String cid=request.getParameter("cid");
	            String bprice = request.getParameter("bprice");
	            String sprice = request.getParameter("sprice");
	            String qty = request.getParameter("qty");
	            //String emailId = request.getParameter("emailId");
	            //String gender = request.getParameter("gender");

	            JSONObject result;
				try {
					result = stockObject.updatestockDetails(bprice, sprice, qty, PID);
					response.getWriter().print(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        } else if (operation.equals("deletestock")) {
	            int PID = Integer.parseInt(request.getParameter("PID"));
	        	//int PID= Integer.parseInt("PID");
	        	String cid=request.getParameter("cid");
	        	 //String pName = request.getParameter("pName");
	            JSONObject result;
				try {
					result = stockObject.deletestock(PID,cid);
					 response.getWriter().print(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	        } else if (operation.equals("getstock")) {
	           int PID = Integer.parseInt(request.getParameter("PID"));
               // String cid=request.getParameter("cid");
	            JSONObject result;
				try {
					result = stockObject.getstockDetails(PID);
					 response.getWriter().print(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				} else if (operation.equals("getstockname")) {
			        //int pid = Integer.parseInt(request.getParameter("PID"));
	                String cid=request.getParameter("cid");
		            JSONObject result;
					try {
						result = stockObject.getstockname(cid);
						 response.getWriter().print(result);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	           
	        } else if (operation.equals("getAllstock")) {
	            JSONArray result;
				try {
					result = stockObject.getAllstock();
					   response.getWriter().print(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         
	        }
	    }

}
