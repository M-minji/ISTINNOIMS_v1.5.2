package egovframework.rndp.mes.gubun.gubunCate.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
//import egovframework.rndp.intra.sales.gubun.service.S_GubunVO;//
import egovframework.rndp.mes.gubun.gubunCate.service.MesGubunCateService;
import egovframework.rndp.mes.gubun.gubunCate.service.MesGubunCateVO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.common.service.MesCommonGubunVO;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;



@Service("mesGubunCateService")
public class MesGubunCateServiceImpl implements MesGubunCateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesGubunCateServiceImpl.class);		//로거가 무슨용도일까

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
	@Resource(name = "mesGubunCateDAO")
	private MesGubunCateDAO mesGubunCateDAO;
	
	// 안상현
	public int selectGubunCheck(MesGubunCateVO mesGubunCateVO) throws Exception{
		int cnt = 0;
		
		if(mesGubunCateVO.getsGubunCateName() != null && !mesGubunCateVO.getsGubunCateName().equals("")){
			String[] gubunList = EgovStringUtil.split(mesGubunCateVO.getsGubunCateName(), ",");
			
			for(int i = 0; i < gubunList.length; i++){
				mesGubunCateVO.setsGubunCateName(gubunList[i]);
				cnt += mesGubunCateDAO.selectGubunCheck(mesGubunCateVO);
			}
		}
		return cnt;
	}
	
	@Override
	public void insertMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception{
		
		if(mesGubunCateVO.getsGubunCateName() != null && !mesGubunCateVO.getsGubunCateName().equals("")){
			
			String[] sGubunCateNameList 		= EgovStringUtil.split(mesGubunCateVO.getsGubunCateName(), ",");
			String[] sGubunCateEtcList 			= EgovStringUtil.split(mesGubunCateVO.getsGubunCateEtc(), ",");
			
			MesGubunCateVO vo = new MesGubunCateVO();
			vo.setkStaffKey(mesGubunCateVO.getkStaffKey());
			for(int i = 0; i < sGubunCateNameList.length; i++){
				
				vo.setsGubunCateName(sGubunCateNameList[i]);
				vo.setsGubunCateEtc(sGubunCateEtcList[i]);

				mesGubunCateDAO.insertMesGubunCate(vo);
			}
		}
	}
	
	@Override
	public List selectMesGubunCateList(MesGubunCateVO mesGubunCateVO) throws Exception{
		return mesGubunCateDAO.selectMesGubunCateList(mesGubunCateVO);
	}
	
	@Override
	public int selectMesGubunCateListCnt(MesGubunCateVO mesGubunCateVO) throws Exception{
		return mesGubunCateDAO.selectMesGubunCateListCnt(mesGubunCateVO);
	}
	
	@Override
	public MesGubunCateVO selectMesGubunCateInfo(MesGubunCateVO mesGubunCateVO) throws Exception{
		return mesGubunCateDAO.selectMesGubunCateInfo(mesGubunCateVO);
	}
	
	@Override
	public void deleteMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception{
		mesGubunCateDAO.deleteMesGubunCate(mesGubunCateVO);
	}
	
	@Override
	public void updateMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception{
		mesGubunCateDAO.updateMesGubunCate(mesGubunCateVO);
	}
	
}
