package egovframework.rndp.com.utl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFileUtil {
	public static final int BUFF_SIZE = 2048;
	private String filePath;
	private String fileName;
	private String serverFilename;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getServerFilename() {
		return serverFilename;
	}
	public void setServerFilename(String serverFilename) {
		this.serverFilename = serverFilename;
	}
	public void download(HttpServletRequest request
			, HttpServletResponse response) throws Exception{
		
		File file = new File(filePath+"\\"+serverFilename);
			if(!file.exists()){
					throw new FileNotFoundException(serverFilename);
			}
			
			if(!file.isFile()){
					throw new FileNotFoundException(serverFilename);
			}
			
			byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
			
			String userAgent = request.getHeader("User-Agent");
	
			response.setContentType("application/x-x-download");
			if(request.getHeader("User-Agent").contains("Firefox")) {
		        response.setHeader("Content-Disposition",
		                "attachment;filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\";");
		    } else {
		        response.setHeader("Content-Disposition",
		                "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\";");
		    }
			
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			
			BufferedInputStream fin = null;
			BufferedOutputStream outs = null;
			
			try {
			    fin = new BufferedInputStream(new FileInputStream(file));
			    outs = new BufferedOutputStream(response.getOutputStream());
			    int read = 0;
	
			    while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			    }
			} finally {
			    if (outs != null) {
				try {
				    outs.close();
				} catch (Exception ignore) {
				    System.out.println("IGNORED: " + ignore.getMessage());
				}
			    }
			    if (fin != null) {
				try {
				    fin.close();
				} catch (Exception ignore) {
				    System.out.println("IGNORED: " + ignore.getMessage());
				}
			    }
			}
	}
	

}
