package com.mangobits.startupkit.faq.faq;

import com.mangobits.startupkit.core.configuration.ConfigurationService;
import com.mangobits.startupkit.core.exception.BusinessException;
import com.mangobits.startupkit.core.utils.BusinessUtils;
import com.mangobits.startupkit.notification.NotificationService;
import com.mangobits.startupkit.user.User;
import com.mangobits.startupkit.user.UserService;
import com.mangobits.startupkit.user.UserStatusEnum;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FaqServiceImpl implements FaqService {


	@Inject
	@New
	private FaqDAO faqDAO;

	@EJB
	private UserService userService;

	@EJB
	private NotificationService notificationService;

	@EJB
	private ConfigurationService configurationService;

	@Override
	public List<Faq> listActives() throws Exception {

		List<Faq> list = null;


			Map<String, Object> params = new HashMap<>();
			params.put("not:status", FaqStatusEnum.BLOCKED.toString());

			list = faqDAO.search(params);

//			list = list.stream()
//					.sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
//					.collect(Collectors.toList());

		return list;
	}


	@Override
	public List<Faq> listActivesByIdObj(String idObj, FaqStatusEnum status) throws Exception {

		List<Faq> listFaq = null;

		Map<String, Object> params = new HashMap<>();

		if(status == null){
			params.put("not:status", FaqStatusEnum.BLOCKED.toString());
		}else{
			params.put("status", status.toString());
		}

		listFaq = faqDAO.search(params);
		return listFaq;
	}



	@Override
	public void save(Faq faq) throws Exception {

		if(faq == null || faq.getIdObj() == null){
			throw new BusinessException("message_id_obj_required");
		}else if(faq.getId() == null && (faq.getMessageQuestion() == null || faq.getMessageQuestion().getMessage() == null || faq.getMessageQuestion().getIdUser() == null)){
			throw new BusinessException("message_question_required");
		}else if(faq.getId() != null && (faq.getMessageReply() == null || faq.getMessageReply().getMessage() == null || faq.getMessageReply().getIdUser() == null)){
			throw new BusinessException("message_question_required");
		}

		boolean flagInsert = true;

		if(faq.getId() == null){

			faq.setStatus(FaqStatusEnum.QUESTION);
			faq.setCreationDate(new Date());

			if(faq.getMessageQuestion().getCreationDate() == null){
				faq.setMessageQuestion(createFaqMessage(faq.getMessageQuestion(), FaqTypeEnum.QUESTION));
			}

		}else{

			flagInsert =false;

			if(faq.getMessageReply().getCreationDate() == null){

				faq.setStatus(FaqStatusEnum.REPLY);
				faq.setMessageReply(createFaqMessage(faq.getMessageReply(), FaqTypeEnum.REPLY));
			}
		}

		new BusinessUtils<>(faqDAO).basicSave(faq);

//		if(flagInsert && faq.getTypeObj() != null){
//
//			Object object = faqDAO.retrieveObject(faq);
//
//			if(object != null && object.getClass().getName().equals()){
//
//			}
//		}
	}


	private FaqMessage createFaqMessage(FaqMessage faqMessage, FaqTypeEnum type) throws Exception{

		User user = userService.retrieve(faqMessage.getIdUser());

		if(user == null){
			throw new BusinessException("id_user_required");
		}else if(user.getStatus().equals(UserStatusEnum.BLOCKED)){
			throw new BusinessException("user_blocked");
		}

		faqMessage.setNameUser(user.getName());
		faqMessage.setTypeEnum(type);
		faqMessage.setCreationDate(new Date());

		return faqMessage;
	}



	@Override
	public void changeStatus(String id) throws Exception {

			Faq faq = faqDAO.retrieve(new Faq(id));

			faq.setStatus(FaqStatusEnum.BLOCKED);

			faqDAO.update(faq);
	}



	@Override
	public Faq load(String id) throws Exception {

		Faq faq = null;

		faq = faqDAO.retrieve(new Faq(id));

		return faq;
	}

}
