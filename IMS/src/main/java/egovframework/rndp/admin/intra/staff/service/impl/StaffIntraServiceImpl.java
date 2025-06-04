package egovframework.rndp.admin.intra.staff.service.impl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.net.telnet.TelnetClient;
import org.springframework.stereotype.Service;

import egovframework.rndp.admin.intra.staff.service.StaffIntraService;
import egovframework.rndp.admin.intra.staff.service.StaffIntraVO;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("staffIntraService")
public class StaffIntraServiceImpl extends EgovAbstractServiceImpl implements StaffIntraService {

	@Resource(name="staffIntraDAO")
	private StaffIntraDAO staffIntraDAO;
	/*
	@Resource(name="intraMailDAO")
    private IntraMailDAO intraMailDAO;*/
	
	public List selectStaffReuestList(StaffIntraVO vo) throws Exception{
		return staffIntraDAO.selectStaffReuestList(vo);
	}
	
	public int selectCount() throws Exception{
		return staffIntraDAO.selectCount();
	}
	
	public StaffIntraVO selectStaffInfoForView(int kStaffRequestKe) throws Exception {
		return staffIntraDAO.selectStaffInfoForView(kStaffRequestKe);
	}

	public void updateStaffNum(StaffIntraVO vo) throws Exception{
		staffIntraDAO.updateStaffNum(vo);
		staffIntraDAO.insertStaffApp(vo);
		staffIntraDAO.deleteStaffReuest(vo);
		/*
		MailInfoVO mailInfoBean = new MailInfoVO();
		// 메일의 user , host , password 세팅
		mailInfoBean = (MailInfoVO)intraMailDAO.getMailInfo(vo.getkStaffId());
		
		mailUserInsert(mailInfoBean.getUser(), mailInfoBean.getPassword());*/
	}
	
	public void deleteStaffReuest(StaffIntraVO vo) throws Exception{
		staffIntraDAO.deleteStaffReuest(vo);
	}
	
	public List selectStaffList(StaffIntraVO vo) throws Exception{
		return staffIntraDAO.selectStaffList(vo);
	}
	
	public List selectSectorList() throws Exception{
		return staffIntraDAO.selectSectorList();
	}
	
	public List selectClassList() throws Exception{
		return staffIntraDAO.selectClassList();
	}
	
	public List selectPositionList() throws Exception{
		return staffIntraDAO.selectPositionList();
	}
	
	public StaffIntraVO selectStaffInfo(StaffIntraVO vo) throws Exception{
		return staffIntraDAO.selectStaffInfo(vo);
	}
	
	public void insertStaff(StaffIntraVO vo) throws Exception{
		staffIntraDAO.insertStaff(vo);
		
		mailUserInsert(vo.getkStaffId(), vo.getkStaffPassword());
	}
	
	public void updateStaff(StaffIntraVO vo) throws Exception{
		staffIntraDAO.updateStaff(vo);
	}

	public String deleteStaff(StaffIntraVO vo) throws Exception{
		int count = staffIntraDAO.selectCountBusinessNote(vo);
		
		if(count >= 1){
			return "1";
		}else {
			staffIntraDAO.deleteStaff(vo);
			return "2";
		}
	}

	public StaffIntraVO selectStaffView2(StaffIntraVO vo) throws Exception{
		return staffIntraDAO.selectStaffView2(vo);
	}
	
	// 메일 계정 추가
	public void mailUserInsert ( String mailUser , String mailPass) {
		
		if(mailPass.length() > 10){
			mailPass = mailPass.substring(0,10);
		}
	    
		String CMD_ADDID    = mailUser; // 입력 id 
	    String CMD_SETPW    = mailPass; // 수정 pw 

        TelnetClient client = null;
        PrintStream out = null;
        InputStream in = null;
        String line = null;
        String msg = null;
        byte[] b = new byte[8192]; //최대가 8192
        int cnt = 0;
        int off = 0;
        int len = b.length;
        
        String 	SERVER_IP          = EgovProperties.getProperty("mailServerIp"); // 텔넷 접속 ip
        int		SERVER_PORT        = 4555; // 텔넷 접속 포트
        String 	SUFFIX_LOGIN       = "Login id:"; // 아이디 입력 표시
        String 	SUFFIX_PASSWORD    = "Password:"; // 패스워드 입력 표시
        String 	SUFFIX_OK          = "Welcome root. HELP for a list of commands"; //로그인 성공
        String 	ID                 = EgovProperties.getProperty("mailAdminId");
        String 	PASSWORD           = EgovProperties.getProperty("mailAdminPw");
        String 	SUFFIX_ADDUSER     = "added"; // 유저 추가 성공메세지
        String 	CMD_ADDUSER        = "adduser"; // 유저 추가 adduser [username] [password]
        
        try {
        	client = new TelnetClient();
        	client.connect(SERVER_IP , SERVER_PORT);
            // 타임아웃 설정 : 1초
            client.setSoTimeout(1000);

            in = client.getInputStream();
            out = new PrintStream(client.getOutputStream());

            cnt = 0;
            off = 0;
            len = b.length;

            while ((cnt = in.read(b, off, len)) != -1)
            {
                line = new String(b, 0, off + cnt);
                ////System.out.println("[" + line + "]");

                // 아이디 입력 표시
                if (line.trim().endsWith(SUFFIX_LOGIN))
                {
                    //System.out.println(" mailUserInsert line = "+line);
                    break;
                }

                off = off + cnt;
                len = len - cnt;
            }

            msg = ID; // 아이디
            out.println(msg);
            out.flush();
            //System.out.println(msg);

            cnt = 0;
            off = 0;
            len = b.length;

            while ((cnt = in.read(b, off, len)) != -1)
            {
                line = new String(b, 0, off + cnt);
                ////System.out.println("[" + line + "]");

                // 패스워드 입력 표시
                if (line.trim().endsWith(SUFFIX_PASSWORD))
                {
                	//System.out.println(" mailUserInsert line = "+line);
                    break;
                }

                off = off + cnt;
                len = len - cnt;
            }

            msg = PASSWORD; // 패스워드
            out.println(msg);
            out.flush();
            //System.out.println(" mailUserInsert msg = "+msg);

            cnt = 0;
            off = 0;
            len = b.length;

            while ((cnt = in.read(b, off, len)) != -1)
            {
                line = new String(b, 0, off + cnt);
                ////System.out.println("[" + line + "]");

                // 정상 로그인 후
                if (line.trim().endsWith(SUFFIX_OK))
                {
                    //System.out.println(line);
                    break;
                }

                off = off + cnt;
                len = len - cnt;
            }
            
            /**
             * 여기까지 id , pw 로 로그인 
             * */

            msg = CMD_ADDUSER+" "+CMD_ADDID+" "+CMD_SETPW ; // 유저 추가 
            out.println(msg);
            out.flush();
            //System.out.println(" mailUserInsert msg = "+msg);

            cnt = 0;
            off = 0;
            len = b.length;
            while ((cnt = in.read(b, off, len)) != -1)
            {
                line = new String(b, 0, off + cnt);
                ////System.out.println("[" + line + "]");
                // adduser 명령어 
                if (line.trim().endsWith(SUFFIX_ADDUSER))
                {
                    //System.out.println(line);
                    break;
                }
                
                off = off + cnt;
                len = len - cnt;
            }
            

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//무조건 close.
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (client != null) {
				try {
					client.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//메뉴리스트
	public List selectStaffMenuList(StaffMenuAuthVO vo) throws Exception{
		return staffIntraDAO.selectStaffMenuList(vo);
	}
	
	public List selectStaffTopMenuList2(StaffMenuAuthVO vo) throws Exception{
		return staffIntraDAO.selectStaffTopMenuList2(vo);
	}
	
	public List selectStaffTopMenuList(StaffMenuAuthVO vo) throws Exception{
		return staffIntraDAO.selectStaffTopMenuList(vo);
	}
	
	public StaffMenuAuthVO selectStaffMenu(StaffMenuAuthVO vo) throws Exception{
		return staffIntraDAO.selectStaffMenu(vo);
	}
	
	public void deleteStaffMenuAuth(StaffMenuAuthVO vo)  throws Exception{
		staffIntraDAO.deleteStaffMenuAuth(vo);
	}
	
	public int staffMenuAuthMaxCnt() throws Exception{
		return staffIntraDAO.staffMenuAuthMaxCnt();
	}
	
	public void staffMenuAuthUpdate(StaffMenuAuthVO vo) throws Exception{
		staffIntraDAO.staffMenuAuthUpdate(vo);
	}
	

	@Override
	public int updateStaffImageFile(StaffIntraVO vo) throws Exception {
		// TODO Auto-generated method stub
		return staffIntraDAO.updateStaffImageFile(vo);
	}


}
