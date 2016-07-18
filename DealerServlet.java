package dealer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject; 

import dealer.Dealer;

public class DealerServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        Dealer dealObject = new Dealer();
        if (operation.equals("add")) {
            int dealerId = Integer.parseInt(req.getParameter("dealerId"));
            String dealerName = req.getParameter("dealerName");
            String companyName = req.getParameter("companyName");
            String address = req.getParameter("address");
            String accountNumber = req.getParameter("accountNumber");
            String branch = req.getParameter("branch");
            String ifsc = req.getParameter("ifsc");
            String mobileNumber = req.getParameter("mobileNumber");

            JSONObject result;
			try {
				result = dealObject.addDealer(dealerId, dealerName, companyName, address, accountNumber, branch, ifsc, mobileNumber);
				resp.getWriter().print(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        } else if (operation.equals("update")) {
        	 int dealerId = Integer.parseInt(req.getParameter("dealerId"));
             String dealerName = req.getParameter("dealerName");
             String companyName = req.getParameter("companyName");
             String address = req.getParameter("address");
             String accountNumber = req.getParameter("accountNumber");
             String branch = req.getParameter("branch");
             String ifsc = req.getParameter("ifsc");
             String mobileNumber = req.getParameter("mobileNumber");

            JSONObject result;
			try {
				result = dealObject.updateDealer(dealerId, dealerName, companyName, address, accountNumber, branch, ifsc, mobileNumber);
				resp.getWriter().print(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        } else if (operation.equals("delete")) {
            int dealerId = Integer.parseInt(req.getParameter("dealerId"));

            JSONObject result;
			try {
				result = dealObject.deleteDealer(dealerId);
				 resp.getWriter().print(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        } else if (operation.equals("getDealer")) {
            int dealerId = Integer.parseInt(req.getParameter("dealerId"));

            JSONObject result;
			try {
				result = dealObject.getDealerDetails(dealerId);
				 resp.getWriter().print(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        } else if (operation.equals("getAllDealers")) {
            JSONArray result = dealObject.getAllDealers();
            resp.getWriter().print(result);
        }
    }
}
