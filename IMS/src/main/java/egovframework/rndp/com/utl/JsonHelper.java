package egovframework.rndp.com.utl;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class JsonHelper {

	public void printJsonObject(HttpServletResponse res, String name, Object obj) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(name, obj);
		StringBuffer sb = new StringBuffer();
		try {

			sb.append(jsonObject.toString());
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");

			PrintWriter out = res.getWriter();
			out.print(sb.toString());
			out.flush();
			out.close();
	//		System.out.println(sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
