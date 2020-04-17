package com.mangobits.startupkit.faq.faq;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangobits.startupkit.admin.util.AdminBaseRestService;
import com.mangobits.startupkit.core.configuration.ConfigurationService;
import com.mangobits.startupkit.core.exception.BusinessException;
import com.mangobits.startupkit.notification.email.EmailService;
import com.mangobits.startupkit.ws.JsonContainer;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;


@Stateless
@Path("/faq")
public class FaqRestService extends AdminBaseRestService {


	@EJB
	private FaqService faqService;


	@EJB
	private EmailService emailService;


	@EJB
	private ConfigurationService configurationService;



	@GET
	@Path("/listActivesByIdObj/{idObj}/{status}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listActivesByIdObj(@PathParam("idObj") String idObj, @PathParam("status") FaqStatusEnum status) throws Exception {

		String resultStr = null;
		JsonContainer cont = new JsonContainer();

		try {

			List<Faq> list = faqService.listActivesByIdObj(idObj, status);

			if(CollectionUtils.isNotEmpty(list)){
				//Collections.sort(user.getListPhotoUpload(), (s1, s2) -> Integer.compare(s1.getIndex(), s2.getIndex()));
				list.sort(Comparator.comparing(Faq::getId));
			}

			cont.setData(list);

		} catch (Exception e) {

			if(!(e instanceof BusinessException)){
				e.printStackTrace();
			}

			cont.setSuccess(false);
			cont.setDesc(e.getMessage());

			emailService.sendEmailError(e);
		}

		ObjectMapper mapper = new ObjectMapper();
		resultStr = mapper.writeValueAsString(cont);

		return resultStr;
	}


	@GET
	@Path("/listActivesByIdObj/{idObj}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listActivesByIdObj(@PathParam("idObj") String idObj) throws Exception {

		String resultStr = null;
		JsonContainer cont = new JsonContainer();

		try {

			List<Faq> list = faqService.listActivesByIdObj(idObj, null);

			cont.setData(list);

		} catch (Exception e) {

			if(!(e instanceof BusinessException)){
				e.printStackTrace();
			}

			cont.setSuccess(false);
			cont.setDesc(e.getMessage());

			emailService.sendEmailError(e);
		}

		ObjectMapper mapper = new ObjectMapper();
		resultStr = mapper.writeValueAsString(cont);

		return resultStr;
	}



	@GET
	@Path("/listActives")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listActives() throws Exception {

		String resultStr = null;
		JsonContainer cont = new JsonContainer();

		try {

			List<Faq> list = faqService.listActives();
			cont.setData(list);

		} catch (Exception e) {

			if(!(e instanceof BusinessException)){
				e.printStackTrace();
			}

			cont.setSuccess(false);
			cont.setDesc(e.getMessage());

			emailService.sendEmailError(e);
		}

		ObjectMapper mapper = new ObjectMapper();
		resultStr = mapper.writeValueAsString(cont);

		return resultStr;
	}




	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/save")
	public String save(Faq faq)  throws Exception{

		String resultStr = null;
		JsonContainer cont = new JsonContainer();

		try {

			faqService.save(faq);
			cont.setData(faq);

		} catch (Exception e) {

			if(!(e instanceof BusinessException)){
				e.printStackTrace();
			}

			cont.setSuccess(false);
			cont.setDesc(e.getMessage());

			emailService.sendEmailError(e);
		}


		ObjectMapper mapper = new ObjectMapper();
		resultStr = mapper.writeValueAsString(cont);

		return resultStr;
	}



	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/changeStatus")
	public String changeStatus(Faq faq)  throws Exception{

		String resultStr = null;
		JsonContainer cont = new JsonContainer();

		try {

			faqService.changeStatus(faq.getId());
			cont.setData("OK");

		} catch (Exception e) {

			if(!(e instanceof BusinessException)){
				e.printStackTrace();
			}

			cont.setSuccess(false);
			cont.setDesc(e.getMessage());

			emailService.sendEmailError(e);
		}


		ObjectMapper mapper = new ObjectMapper();
		resultStr = mapper.writeValueAsString(cont);

		return resultStr;
	}



	@GET
	@Path("/load/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String load(@PathParam("id") String id) throws Exception {

		String resultStr = null;
		JsonContainer cont = new JsonContainer();

		try {

			Faq faq = faqService.load(id);
			cont.setData(faq);

		} catch (Exception e) {

			if(!(e instanceof BusinessException)){
				e.printStackTrace();
			}

			cont.setSuccess(false);
			cont.setDesc(e.getMessage());

			emailService.sendEmailError(e);
		}

		ObjectMapper mapper = new ObjectMapper();
		resultStr = mapper.writeValueAsString(cont);

		return resultStr;
	}

}
