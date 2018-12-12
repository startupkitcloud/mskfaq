package com.mangobits.startupkit.faq.faq;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.SortableField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mangobits.startupkit.core.status.SimpleStatusEnum;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name= "faq")
@Indexed
public class Faq {


    @Id
    @DocumentId
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    @Field
    @SortableField
    private Date creationDate;


    @Field
    private String idGroup;

  
    @Field
    private String desc;
    
    
    @Field
	@Enumerated(EnumType.STRING)
	private SimpleStatusEnum status;
    
    
    public Faq(){
    	
    }
    
    public Faq(String id){
    	this.id = id;
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public String getIdGroup() {
		return idGroup;
	}


	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public SimpleStatusEnum getStatus() {
		return status;
	}


	public void setStatus(SimpleStatusEnum status) {
		this.status = status;
	}
   

}
