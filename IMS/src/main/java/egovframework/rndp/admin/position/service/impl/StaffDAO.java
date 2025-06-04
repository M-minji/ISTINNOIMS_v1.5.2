package egovframework.rndp.admin.position.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.position.service.StaffVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("staffDAO")
public class StaffDAO extends EgovAbstractDAO{

	public List staffListByPos(int positionKey) throws Exception{
		if(positionKey == 0){
			return list("staffDAO.staffListByPos", null);
		}else{
			return list("staffDAO.staffListByPos1", positionKey);
		}
		
	}
	
	public StaffVO staffInfoForView(int staffKey) throws Exception{
		return (StaffVO)selectByPk("staffDAO.staffInfoForView", staffKey);
	}
}
