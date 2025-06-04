package egovframework.rndp.Log.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;
import com.sun.mail.iap.ProtocolException;

import egovframework.rndp.Log.service.LogService;
import egovframework.rndp.Log.service.LogVO;

@Service("LogService")
public class LogServiceImpl implements LogService{

	@Resource(name="LogDAO")
	private LogDAO logDAO;
	
	@Override
	public void insertLog(LogVO VO) throws Exception {
		//rndp id에 따른 이름값 변경
		if(!"rndp".equals(VO.getStaffId().toLowerCase())){
			// 30분 이상 업데이트
			//중간에 endDate 있는지 없는지 체크해야됨
			logDAO.updateLogMinutes(VO);
			
			String str = "";
			String pageGubun = "";
			String urlList[] = VO.getUrl().split("/");
			for(int m = 0; m < urlList.length; m++){
				pageGubun = urlList[1];
			}
			Runtime.getRuntime().gc();
			long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			
			VO.setPageGubun(pageGubun);
			if(!VO.getUrl().equals("/mes/webMenu.do")&& !VO.getUrl().equals("/intra/webMenu.do")){
				//전 시간 업데이트
				LogVO vo = new LogVO();
				List<LogVO> numList = logDAO.selectLogNum(VO);
				for(int i = 0; i < numList.size(); i++){
					vo.setLogNum(numList.get(i).getLogNum());
					vo.setStartDate(numList.get(i).getStartDate());
					logDAO.updateLog(vo);
				}
				
				logDAO.updateLogMinutesSub(VO);
				
				VO.setkMenuProgSrc(VO.getUrl());
				List<LogVO> menuList = logDAO.selectMenu(VO);
				if(menuList.size() != 0){
					if(menuList.size() == 1){
						for(int i = 0; i < menuList.size(); i++){
							VO.setPage(menuList.get(i).getkMenuName());
						}
					}else if(menuList.size() > 1){
						for(int m = 0; m < urlList.length; m++){
							str = urlList[1] + "/" + urlList[2] + "/" + urlList[3]; 
						}
						VO.setkMenuProgSrc(str);
						List<LogVO> menuSubList = logDAO.selectMenu(VO);
						for(int i = 0; i < menuSubList.size(); i++){
							VO.setPage(menuSubList.get(i).getkMenuName());
						}
					}
				}else{
					for(int m = 0; m < urlList.length; m++){
						str = urlList[1] + "/" + urlList[2]; 
					}
					VO.setkMenuProgSrc(str);
					List<LogVO> menuSubList2 = logDAO.selectMenuZero(VO);
					if(menuSubList2.size() != 0){
						for(int i = 0; i < menuSubList2.size(); i++){
							VO.setPage(menuSubList2.get(i).getkMenuName());
						}
					}
				}
				if(!VO.getLogAPI().equals("")){
					Date nowDate = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
					String formatDate = dateFormat.format(nowDate); 
					
					try {
						// Log데이터 보내기
						URL url = new URL("https://log.smart-factory.kr/apisvc/sendLogDataJSON.do");
		
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("POST"); // 보내는 타입
						conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
						// 데이터
						String param = "logData={\"crtfcKey\": \""+VO.getLogAPI()+"\", \"logDt\" : \""+formatDate+"\" , \"useSe\" : \""+VO.getGubun()+"\" , \"sysUser\" : \""+VO.getStaffName()+"\", \"conectIp\" : \""+VO.getLogIp()+"\", \"dataUsgqty\" : \""+usedMemory+"\"}";
						// 전송
						OutputStreamWriter osw = new OutputStreamWriter(
						conn.getOutputStream());
						osw.write(param);
						osw.flush();
						// 닫기
						BufferedReader br = null;
						br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
						String Text = Read(br);
						String text2 = "{\"result\":";
						String text3 = "}}";
						String result2 = Text.replace(text2, "").replace(text3, "}");
						JSONObject json = new JSONObject(result2);
						VO.setRecptnDt((String) json.get("recptnDt"));
						VO.setRecptnRsltCd((String) json.get("recptnRsltCd"));
						VO.setRecptnRslt((String) json.get("recptnRslt"));
						osw.close();
						br.close();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					VO.setRecptnRslt("연동안됨");
				}
				
				logDAO.insertLog(VO);
			}
		}
	}

	@Override
	public List selectLogList(LogVO logVO) throws Exception {
		return logDAO.selectLogList(logVO);
	}
	
	@Override
	public int selectLogListCnt(LogVO logVO) throws Exception {
		return logDAO.selectLogListCnt(logVO);
	}
	public String Read(Reader re) throws IOException {
	    StringBuilder str = new StringBuilder();
	    int temp;
	    do {
	      temp = re.read();
	      str.append((char) temp);
	    } while (temp != -1);        

	    return str.toString();
	}

	@Override
	public List selectLogExcelList(LogVO logVO) throws Exception {
		return logDAO.selectLogExcelList(logVO);
	}
}
