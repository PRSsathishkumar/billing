package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stock.stockServlet;
public class stockclass {
	public JSONObject stockadd(String cid, String pName, String tName, String thold, String bprice, String sprice,String qty) throws JSONException{
		JSONObject result=new JSONObject();
		try {                                    
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
	        Statement statement = connection.createStatement();
	        String query= "insert into productname values('"+ cid +"','"+ pName +"','"+ tName +"','"+ thold +"')";
	        String query1="insert into productdetail (bprice,sprice,qty,cid)values('"+bprice+"','"+sprice+"','"+qty+"','"+cid+"')";
	        
	        //int rows = statement.executeUpdate("INSERT INTO cproducts SELECT * FROM productname1 ,productdetail2");
	        statement.execute(query);
	       statement.execute(query1);   //SELECT  productCode, productName, textDescription FROM products T1 INNER JOIN productlines T2 ON T1.productline = T2.productline;
	       
	        result.put("Status","1");
		} catch (Exception e1) {
			JSONObject error=new JSONObject();
			error.put("Status", "0");
			error.put("Message", e1.getMessage());
			return error;
			
		}
		return result;
		
	}
	public JSONObject updatestockDetails(String bprice, String sprice,String qty,int PID) throws JSONException{
		JSONObject result=new JSONObject();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
	        Statement statement = connection.createStatement();
	        String query= "update productdetail set bprice='"+ bprice +"',sprice='"+ sprice +"',qty='"+ qty +"' where PID="+PID;
	        statement.execute(query);
	        result.put("Status","1");
		}catch (Exception e){
		JSONObject error=new JSONObject();
		error.put("Status", "0");
		error.put("Message", e.getMessage());
		return error;
			
		}
	return result;

}

public JSONObject deletestock(int PID,String cid)throws JSONException{
	JSONObject result=new JSONObject();
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
		        Statement statement = connection.createStatement();
		        String query="delete from  productdetail where PID='"+PID+"'";
		        statement.execute(query);
		        result.put("Status","1");
			
			}catch (Exception e){
				JSONObject error=new JSONObject();
				error.put("Status","0");
				error.put("Message",e.getMessage());
				return error;
				
				
			}
	return result;
  
     }
public JSONObject getstockDetails(int PID)throws JSONException{
	JSONObject result=new JSONObject();
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
        Statement statement = connection.createStatement();
        String query="select * from productdetail where PID='"+PID+"'";
        ResultSet rs = statement.executeQuery(query);
         if(rs.next()) {
        	//result.put("PID", rs.getInt("PID"));
        	result.put("bprice",rs.getString("bprice"));
        	result.put("sprice", rs.getString("sprice"));
        	result.put("qty", rs.getString("qty"));
        	result.put("cid",rs.getString("cid"));
        }
	}catch (Exception e){
		JSONObject error=new JSONObject();
	      error.put("Status","0");
		error.put("Message",e.getMessage());
		return error;
	}
	return result;
}
public JSONObject getstockname(String cid)throws JSONException{
	JSONObject result=new JSONObject();
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
        Statement statement = connection.createStatement();
        String query="select * from productname where cid='"+cid+"'";
        //String query1="select * from  productname1 where cid="+cid;
        ResultSet rs = statement.executeQuery(query);
        //ResultSet rs1 = statement.executeQuery(query1);
        if (rs.next()) {
        	
        	//result.put("cid",rs.getString("cid"));
        	result.put("pName", rs.getString("pName"));
        	result.put("tName", rs.getString("tName"));
        	result.put("thold",rs.getString("thold"));
        	//result.put("PID", rs.getInt("PID"));
        	//result.put("bprice",rs.getString("bprice"));
        	//result.put("sprice", rs.getString("sprice"));
        	//result.put("qty", rs.getString("qty"));
        }
	}catch (Exception e){
		JSONObject error=new JSONObject();
		error.put("Status", "0");
		error.put("Message", e.getMessage());
		return error;
		
	}
	return result;
}
public JSONArray getAllstock()throws JSONException{
	JSONArray result=new JSONArray();
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root" , "root");
        Statement statement = connection.createStatement();
        String query="SELECT * FROM productname INNER JOIN productdetail where productname.cid=productdetail.cid";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
        	JSONObject stock = new JSONObject();
        	stock.put("cid", rs.getInt("cid"));
        	stock.put("pName", rs.getString("pName"));
        	stock.put("tName",rs.getString("tName"));
        	stock.put("thold",rs.getString("thold"));
        	stock.put("PID",rs.getInt("PID"));
        	stock.put("bprice", rs.getString("bprice"));
        	stock.put("sprice", rs.getString("sprice"));
        	stock.put("qty", rs.getString("qty"));
        	result.put(stock);
        }
	}catch (Exception e){
		JSONArray error=new JSONArray();
		error.put("Message");
		return error;
		
	}
	return result;
}
}