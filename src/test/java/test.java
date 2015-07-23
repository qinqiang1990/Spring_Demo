import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String txt="Hello Wrold";
		JSONObject data=new JSONObject();
		try {
			data.getString("key");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(txt);
	}

}
