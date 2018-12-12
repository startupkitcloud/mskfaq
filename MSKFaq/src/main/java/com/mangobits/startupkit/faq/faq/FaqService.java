package com.mangobits.startupkit.faq.faq;

import java.util.List;

import javax.ejb.Local;

@Local
public interface FaqService {

	
	List<Faq> listAll() throws Exception;
	
	
	List<Faq> listActives() throws Exception;
	
	
	void save(Faq category) throws Exception;
	
	
	void changeStatus(String id) throws Exception;
	
	
	Faq load(String id) throws Exception;
}
