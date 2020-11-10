import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 */

/**
 * @author ankita_navandar
 *
 */
public class jsonParsing {
	public static Properties properties = null;
	public static JSONObject jsonObject = null;
	static{
		properties = new Properties();
	}
	static String result = "";
	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		try {
			JSONParser jsonParser = new JSONParser();
			File file = new File("users.json");
			Object object = jsonParser.parse(new FileReader(file));
			jsonObject = (JSONObject)object;
			parseJson(jsonObject);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String content = result;
		String path = "D:\\Ankita\\Ass\\result.txt";
		
		try(FileWriter writer = new FileWriter(path);
				BufferedWriter bw = new BufferedWriter(writer)){
			bw.write(content);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static String parseJson(JSONObject jsonObject)throws ParseException {
		// TODO Auto-generated method stub
		Set<Object> set = jsonObject.keySet();
		//String result = "";
		Iterator<Object> iterator = set.iterator();
		while(iterator.hasNext())
		{
			Object obj = iterator.next();
			if(jsonObject.get(obj) instanceof JSONArray)
			{
				//System.out.println(obj.toString());
				getArray(jsonObject.get(obj));
			}else{
				if(jsonObject.get(obj) instanceof JSONObject)
				{
					parseJson((JSONObject)jsonObject.get(obj));
				}else{
					//System.out.print(obj.toString()+"="+jsonObject.get(obj)+" ");
					result = result + obj.toString()+"="+jsonObject.get(obj)+" ";
				}
			}
		}
		result = result + "\n";
		
		return result;
	}
	public static void getArray(Object object2)throws ParseException {
		// TODO Auto-generated method stub
		JSONArray jsonArr = (JSONArray)object2;
		for(int k=0;k<jsonArr.size();k++)
		{
			if(jsonArr.get(k) instanceof JSONObject){
				parseJson((JSONObject) jsonArr.get(k));
				}
			else{
				System.out.println(jsonArr.get(k));
			}
		}
	}

}
