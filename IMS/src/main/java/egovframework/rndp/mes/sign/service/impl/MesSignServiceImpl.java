package egovframework.rndp.mes.sign.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mesSignService")
public class MesSignServiceImpl extends EgovAbstractServiceImpl implements MesSignService  {

    @Resource(name="mesSignDAO")
    private MesSignDAO mesSignDAO;
    
	@Override
	public List selectPositionList(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
		return mesSignDAO.selectPositionList(mesSignVO);
	}

	@Override
	public List selectStaffList(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
		return mesSignDAO.selectStaffList(mesSignVO);
	}

	@Override
	public List choiceStaffListSe(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
		return mesSignDAO.choiceStaffListSe(mesSignVO);
	}

	@Override
	public List selectPathList(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
		return mesSignDAO.selectPathList(mesSignVO);
	}

	@Override
	public String insertSign(String tableNum, String tableName, String kStaffKey, String kStaffName, String kStaffPosition) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO mesSignVO = new MesSignVO();
		
		mesSignVO.setsSignKey(mesSignDAO.getMaxSignKey(mesSignVO));
		
		mesSignVO.setsSignTableKey(tableNum);
		mesSignVO.setsSignTableName(tableName);
	
		mesSignDAO.insertSign(mesSignVO);
		
		for(int i=0; i<EgovStringUtil.split(kStaffKey, ",").length; i++){
			mesSignVO.setsSignStaffKey(EgovStringUtil.split(kStaffKey, ",")[i]);
			mesSignVO.setsSignStaffName(EgovStringUtil.split(kStaffName, ",")[i]);
			mesSignVO.setsSignStaffPosition(EgovStringUtil.split(kStaffPosition, ",")[i]);
			mesSignVO.setsSignSequence(Integer.toString(i+1));

			mesSignDAO.insertSignItem(mesSignVO);
		}

		mesSignVO.setsSignProgress("0");
		mesSignDAO.updateSignProgress(mesSignVO);
		
		return mesSignVO.getsSignKey();
	}
	public String insertSignTwo(String tableNum, String tableName,  String kStaffKey, String kStaffName, String sSignStaffGubun, String kStaffPosition) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO mesSignVO = new MesSignVO();
		
		mesSignVO.setsSignKey(mesSignDAO.getMaxSignKey(mesSignVO));
		
		mesSignVO.setsSignTableKey(tableNum);
		mesSignVO.setsSignTableName(tableName);
		
		mesSignDAO.insertSign(mesSignVO);
		
		for(int i=0; i<EgovStringUtil.split(kStaffKey, ",").length; i++){
			mesSignVO.setsSignStaffKey(EgovStringUtil.split(kStaffKey, ",")[i]);
			mesSignVO.setsSignStaffName(EgovStringUtil.split(kStaffName, ",")[i]);
			mesSignVO.setsSignStaffPosition(EgovStringUtil.split(kStaffPosition, ",")[i]);
			mesSignVO.setsSignStaffGubun(EgovStringUtil.split(sSignStaffGubun, ",")[i]);
			mesSignVO.setsSignSequence(Integer.toString(i+1));
			
			mesSignDAO.insertSignItem(mesSignVO);
		}
		
		mesSignVO.setsSignProgress("0");
		mesSignDAO.updateSignProgress(mesSignVO);
		
		return mesSignVO.getsSignKey();
	}

	@Override
	public String updateSign(String sSignKey, String kStaffKey, String kStaffName, String kStaffPosition) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO vo = new MesSignVO();
		
		vo.setsSignKey(sSignKey);
		MesSignVO mesSignVO = mesSignDAO.selectSignInfo(vo);

		mesSignDAO.deleteSignItem(mesSignVO);
		
		for(int i=0; i<EgovStringUtil.split(kStaffKey, ",").length; i++){
			mesSignVO.setsSignStaffKey(EgovStringUtil.split(kStaffKey, ",")[i]);
			mesSignVO.setsSignStaffName(EgovStringUtil.split(kStaffName, ",")[i]);
			mesSignVO.setsSignStaffPosition(EgovStringUtil.split(kStaffPosition, ",")[i]);
			mesSignVO.setsSignSequence(Integer.toString(i+1));

			mesSignDAO.insertSignItem(mesSignVO);
		}

		mesSignVO.setsSignProgress("0");
		mesSignDAO.updateSignProgress(mesSignVO);
		
		return mesSignVO.getsSignKey();
	}
	@Override
	public String updateSignTwo(String sSignKey, String kStaffKey, String kStaffName, String sSignStaffGubun, String kStaffPosition) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO vo = new MesSignVO();
		
		vo.setsSignKey(sSignKey);
		MesSignVO mesSignVO = mesSignDAO.selectSignInfo(vo);

		mesSignDAO.deleteSignItem(mesSignVO);
		
		for(int i=0; i<EgovStringUtil.split(kStaffKey, ",").length; i++){
			mesSignVO.setsSignStaffKey(EgovStringUtil.split(kStaffKey, ",")[i]);
			mesSignVO.setsSignStaffName(EgovStringUtil.split(kStaffName, ",")[i]);
			mesSignVO.setsSignStaffPosition(EgovStringUtil.split(kStaffPosition, ",")[i]);
			mesSignVO.setsSignStaffGubun(EgovStringUtil.split(sSignStaffGubun, ",")[i]);
			
			mesSignVO.setsSignSequence(Integer.toString(i+1));

			mesSignDAO.insertSignItem(mesSignVO);
		}

		mesSignVO.setsSignProgress("0");
		mesSignDAO.updateSignProgress(mesSignVO);
		
		return mesSignVO.getsSignKey();
	}
	@Override
	public void updateSignStatus(String sSignKey, String kStaffKey, String updateContent, String updateType) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO mesSignVO = new MesSignVO();
		
		mesSignVO.setsSignKey(sSignKey);
		mesSignVO.setsSignStaffKey(kStaffKey);
		mesSignVO.setsSignDecison(updateType);
		mesSignVO.setsSignContent(updateContent);
		
		mesSignDAO.updateSignStatus(mesSignVO);

		String count = mesSignDAO.getSignCount(mesSignVO);
		
		mesSignVO.setsSignProgress(count);
		mesSignDAO.updateSignProgress(mesSignVO);
		
		if(updateType.equals("반려")){
			mesSignDAO.resetSignStatus(mesSignVO);
		}
	}

	@Override
	public void deleteSign(String sSignKey) throws Exception {
		// TODO Auto-generated method stub

		MesSignVO mesSignVO = new MesSignVO();
		
		mesSignVO.setsSignKey(sSignKey);

		mesSignDAO.deleteSignItem(mesSignVO);
		
		mesSignDAO.deleteSign(mesSignVO);
	}

	@Override
	public List selectSignList(String sSignKey) throws Exception {
		// TODO Auto-generated method stub

		MesSignVO mesSignVO = new MesSignVO();
		
		mesSignVO.setsSignKey(sSignKey);
		
		return mesSignDAO.selectSignList(mesSignVO);
	}

	@Override
	public void kwUploadSignImgAjax(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
//		mesSignDAO.updateSignStatus(mesSignVO);
//
//		String count = mesSignDAO.getSignCount(mesSignVO);
//		
//		mesSignVO.setsSignProgress(count);
//		mesSignDAO.updateSignProgress(mesSignVO);
		
		mesSignDAO.updateSignStatusTwo(mesSignVO);
		String sSignStaffGubun =mesSignDAO.sSignStaffGubunCheck(mesSignVO);
		if ("결재".equals(sSignStaffGubun)) {
			mesSignDAO.eFinalApprovalUpdate(mesSignVO);
		}
		String count = mesSignDAO.getSignCountTwo(mesSignVO);
		
		mesSignVO.setsSignProgress(count);
		mesSignDAO.updateSignProgressTwo(mesSignVO);
	}

	@Override
	public void resetSignStatusStart(String sSignKey) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO mesSignVO = new MesSignVO();
		
		mesSignVO.setsSignKey(sSignKey);
		mesSignDAO.resetSignStatusStart(mesSignVO);
	}

	@Override
	public void signSubInsertPath(MesSignVO mesSignVO) throws Exception{
		// TODO Auto-generated method stub
		int num = mesSignDAO.selectSignSubNum(mesSignVO);
		String[] sSingPathKey 	= EgovStringUtil.split(mesSignVO.getsSingPathKey(), ",");
		String[] sSingPathGubun 			= EgovStringUtil.split(mesSignVO.getsSingPathGubun(), ",");
		
		MesSignVO vo = new MesSignVO();
		vo.setsSingPathSubject(mesSignVO.getsSingPathSubject());
		vo.setkStaffKey(mesSignVO.getkStaffKey());
		vo.setsSingPathNum(Integer.toString(num));
		for(int i = 0; i < sSingPathKey.length; i++){
		 
			vo.setsSingPathKey(sSingPathKey[i]);
			vo.setsSingPathGubun(sSingPathGubun[i]);
			vo.setsSignSubSequence(Integer.toString(i));
			
			mesSignDAO.signSubInsertPath(vo);
		}
	}

	@Override
	public List seleteSignSelectPath(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
		return mesSignDAO.seleteSignSelectPath(mesSignVO);
	}

	@Override
	public void signDeletePath(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
		mesSignDAO.signDeletePath(mesSignVO);
	}

	@Override
	public List selectSignListTwo(MesSignVO mesSignVO) throws Exception {
		// TODO Auto-generated method stub
		return mesSignDAO.selectSignListTwo(mesSignVO);
	}

	@Override
	public void deleteSignItemTwo(String tableNum, String tableName) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO vo = new MesSignVO();
				vo.setsSignTableKey(tableNum);
				vo.setsSignTableName(tableName);
		mesSignDAO.deleteSignItemTwo(vo);
		mesSignDAO.deleteSignTwo(vo);
	}

	@Override
	public void updateSignStart(String tableNum, String tableName) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO vo = new MesSignVO();
		vo.setsSignTableKey(tableNum);
		vo.setsSignTableName(tableName);
		mesSignDAO.updateSignStart(vo);
	}
	
	@Override
	public void insertSignRejectionReason(String tableNum, String tableName, String sSignStaffKey, String sSignContent) throws Exception {
		// TODO Auto-generated method stub
		MesSignVO vo = new MesSignVO();
		vo.setsSignTableKey(tableNum);
		vo.setsSignTableName(tableName);
		vo.setsSignStaffKey(sSignStaffKey);
		vo.setsSignContent(sSignContent);
		vo.setsSignProgress("반려");
		mesSignDAO.updateSignRejectionReason(vo);
		mesSignDAO.updateSignProgressTwo(vo);
	}
	
	
}