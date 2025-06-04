package egovframework.rndp.mes.gubun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO; 


@Service("mesGubunService")
public class MesGubunServiceImpl implements MesGubunService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesGubunServiceImpl.class);

	/*공통사용 DAO common 및  Egov 공용 */
	@Resource(name = "mesCommonDAO")
	private MesCommonDAO mesCommonDAO; 
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	/*공통사용 DAO common 및  Egov 공용 */ 
	
	@Resource(name = "mesGubunDAO")
	private MesGubunDAO mesGubunDAO;
	
	// 안상현
	@Override
	public List selectMesGubunCateList() throws Exception{
		return mesGubunDAO.selectMesGubunCateList();
	}
	
	@Override
	public void insertMesGubun(MesGubunVO mesGubunVO) throws Exception{
		
		if(mesGubunVO.getsGubunCateKey() != null && !mesGubunVO.getsGubunCateKey().equals("")){
			
			String[] sGubunCateKeyList 		= EgovStringUtil.split(mesGubunVO.getsGubunCateKey(), ",");
			String[] sGubunNameList 		= EgovStringUtil.split(mesGubunVO.getsGubunName(), ",");
			String[] sGubunEtcList 			= EgovStringUtil.split(mesGubunVO.getsGubunEtc(), ",");
			
			MesGubunVO vo = new MesGubunVO();
			vo.setkStaffKey(mesGubunVO.getkStaffKey());
			if(sGubunCateKeyList.length > 0){
				for(int i = 0; i < sGubunCateKeyList.length; i++){
					vo.setsGubunCateKey(sGubunCateKeyList[i]);
					vo.setsGubunName(sGubunNameList[i]);
					vo.setsGubunEtc(sGubunEtcList[i]);

					mesGubunDAO.insertMesGubun(vo);
				}
			}
		}
	}
	
	@Override
	public List selectMesGubunList(MesGubunVO mesGubunVO) throws Exception{
		return mesGubunDAO.selectMesGubunList(mesGubunVO);
	}
	
	@Override
	public int selectMesGubunListCnt(MesGubunVO mesGubunVO) throws Exception{
		return mesGubunDAO.selectMesGubunListCnt(mesGubunVO);
	}
	
	@Override
	public MesGubunVO selectMesGubunInfo(MesGubunVO mesGubunVO) throws Exception{
		return mesGubunDAO.selectMesGubunInfo(mesGubunVO);
	}
	
	@Override
	public void deleteMesGubun(MesGubunVO mesGubunVO) throws Exception{
		mesGubunDAO.deleteMesGubun(mesGubunVO);
	}
	
	@Override
	public void updateMesSGubun(MesGubunVO mesGubunVO) throws Exception{
		mesGubunDAO.updateMesSGubun(mesGubunVO);
	}
	
	@Override
	public List selectMesGubunCodeList(MesGubunVO mesGubunVO) throws Exception{
		return mesGubunDAO.selectMesGubunCodeList(mesGubunVO);
	}
}
