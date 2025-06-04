package egovframework.rndp.mes.contact.service;

import java.util.List;

public interface MesContactService {

	public void mesContactInsert(MesContactVO mesContactVO) throws Exception;

	public List mesContactInfoList(MesContactVO mesContactVO) throws Exception;

	public int mesContactInfoListCnt(MesContactVO mesContactVO) throws Exception;

	public MesContactVO mesContactInfo(MesContactVO mesContactVO) throws Exception;

	public void mesContactDelete(MesContactVO mesContactVO) throws Exception;

	public void mesContactUpdate(MesContactVO mesContactVO) throws Exception;

	public List mesContactInfoPopupList(MesContactVO mesContactVO) throws Exception;

	public int mesContactInfoPopupListCnt(MesContactVO mesContactVO) throws Exception;
	 
	
}
