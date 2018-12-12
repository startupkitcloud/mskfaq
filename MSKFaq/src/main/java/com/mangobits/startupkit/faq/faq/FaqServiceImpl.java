package com.mangobits.startupkit.faq.faq;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.New;
import javax.inject.Inject;

import com.mangobits.startupkit.core.status.SimpleStatusEnum;
import com.mangobits.startupkit.core.utils.BusinessUtils;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FaqServiceImpl implements FaqService {

	
	@Inject
	@New
	private FaqDAO faqDAO;
	
	
	
	@Override
	public List<Faq> listAll() throws Exception {
		
		List<Faq> list = null;
		
		list = faqDAO.listAll();

		
		return list;
	}
	
	
	
	@Override
	public List<Faq> listActives() throws Exception {
		
		List<Faq> list = null;
		

			Map<String, Object> params = new HashMap<>();
			params.put("status", SimpleStatusEnum.ACTIVE);
			
			list = faqDAO.search(params);
			
//			list = list.stream()
//					.sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
//					.collect(Collectors.toList());

		
		return list;
	}



	@Override
	public void save(Faq faq) throws Exception {
		

			if(faq.getId() == null){
				faq.setStatus(SimpleStatusEnum.ACTIVE);
				faq.setCreationDate(new Date());
			}
			
			new BusinessUtils<>(faqDAO).basicSave(faq);
	}



	@Override
	public void changeStatus(String id) throws Exception {
		

			Faq faq = faqDAO.retrieve(new Faq(id));
			
			if(faq.getStatus() != null && faq.getStatus().equals(SimpleStatusEnum.ACTIVE)){
				faq.setStatus(SimpleStatusEnum.BLOCKED);
			}
			else{
				faq.setStatus(SimpleStatusEnum.ACTIVE);
			}
			
			faqDAO.update(faq);

	}



	@Override
	public Faq load(String id) throws Exception {
		
		Faq faq = null;
		

			faq = faqDAO.retrieve(new Faq(id));
			

		
		return faq;
	}
}
