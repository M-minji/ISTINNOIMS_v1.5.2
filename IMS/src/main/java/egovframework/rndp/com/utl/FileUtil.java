package egovframework.rndp.com.utl;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

	private String filePath;
	private String fileName;
	
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
	
	/**
	 * 파일 삭제
	 * @throws Exception
	 */
	public void delete() throws Exception{
		File delFile = new File(filePath+"/"+fileName);
		
		if(delFile.exists() && delFile.isFile()){
			delFile.delete();
		}
	}
	
	
	public  boolean fileExt(String fileName, String strPat) throws Exception{
		boolean result = false;
		try{
			Pattern p = Pattern.compile(strPat);
			Matcher m = p.matcher(fileName);
			result = m.matches();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
}
