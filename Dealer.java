package dealer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dealer {

    public JSONObject addDealer(int dealerId, String dealerName, String companyName, String address, String accountNumber, String branch, String ifsc, String mobileNumber) throws JSONException {
        JSONObject result = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
            Statement statement = connection.createStatement();
            
            String query = "Insert into dealer values (" + dealerId + 
                    ", '" + dealerName + "','" + companyName + "','" + address + "', '" + accountNumber + "','" + branch +"','" + ifsc +"','" + mobileNumber +"')";
             
            statement.execute(query);
            result.put("Status", "1");
        } catch (Exception e1) {
            JSONObject error = new JSONObject();
            error.put("Status", "0");
            error .put("Message", e1.getMessage());
            return error;
        }
        return result;
    }
    
    public JSONObject updateDealer(int dealerId, String dealerName, String companyName, String address, String accountNumber, String branch, String ifsc, String mobileNumber) throws JSONException {
        JSONObject result = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
            Statement statement = connection.createStatement();
            
            String query = "Update dealer set dealerName ='" + dealerName + "',companyName='" + companyName + "',address='"+ address +"',accountNumber='"+ accountNumber +"',branch='"+ branch +"',ifsc='"+ ifsc +"',mobileNumber='" + mobileNumber
                    + "'  where dealerId = " + dealerId;
            statement.execute(query);
            result.put("Status", "1");
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("Status","0");
            error.put("Message", e.getMessage());
            return error;
        }
        return result;
    }
    
    public JSONObject deleteDealer(int dealerId) throws JSONException {
        JSONObject result = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
            Statement statement = connection.createStatement();
            
            String query = "delete from dealer where dealerId = " + dealerId;
            statement.execute(query);
            result.put("Status", "1");
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("Status","0");
            error.put("Message", e.getMessage());
            return error;
       }	
        return result;
    }
    
    public JSONObject getDealerDetails(int dealerId) throws JSONException {
        JSONObject result = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
            Statement statement = connection.createStatement();
            
            String query = "select * from dealer where dealerId = " + dealerId;
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                result.put("dealerId", rs.getInt("dealerId"));
                result.put("dealerName", rs.getString("dealerName"));
                result.put("companyName", rs.getString("companyName"));
                result.put("address", rs.getString("address"));
                result.put("accountNumber", rs.getString("accountNumber"));
                result.put("branch", rs.getString("branch"));
                result.put("ifsc", rs.getString("ifsc"));
                result.put("mobileNumber", rs.getString("mobileNumber"));
                
            }
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("Status","0");
            error.put("Message", e.getMessage());
            return error;
        }
        return result;
    }
    
    public JSONArray getAllDealers() {
        JSONArray result = new JSONArray();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
            Statement statement = connection.createStatement();
            
            String query = "select * from dealer";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                JSONObject dealer = new JSONObject();
                dealer.put("dealerId", rs.getInt("dealerId"));
                dealer.put("dealerName", rs.getString("dealerName"));
                dealer.put("companyName", rs.getString("companyName"));
                dealer.put("address", rs.getString("address"));
                dealer.put("accountNumber", rs.getString("accountNumber"));
                dealer.put("branch", rs.getString("branch"));
                dealer.put("ifsc", rs.getString("ifsc"));
                dealer.put("mobileNumber", rs.getString("mobileNumber"));;
                result.put(dealer);
            } 
        } catch (Exception e) {
            JSONArray error = new JSONArray();
            error.put("Status:0");
            error.put("Message: " + e.getMessage());
            return error;
        }
        return result;
    }
}
