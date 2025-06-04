package egovframework.rndp.admin.position.service;

import java.util.List;

public interface StaffService {

	public List staffListByPos(int positionKey) throws Exception;
	public StaffVO staffInfoForView(int staffKey) throws Exception;
	 
}
