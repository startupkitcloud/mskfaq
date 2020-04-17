package com.mangobits.startupkit.faq.faq;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;

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
    private String idObj;


	private String typeObj;


	private String descObj;

  
    private String desc;


    @IndexedEmbedded
    private FaqMessage messageQuestion;


    @IndexedEmbedded
    private FaqMessage messageReply;

    
    @Field
	@Enumerated(EnumType.STRING)
	private FaqStatusEnum status;


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

	public String getIdObj() {
		return idObj;
	}

	public void setIdObj(String idObj) {
		this.idObj = idObj;
	}

	public String getTypeObj() {
		return typeObj;
	}

	public void setTypeObj(String typeObj) {
		this.typeObj = typeObj;
	}

	public String getDescObj() {
		return descObj;
	}

	public void setDescObj(String descObj) {
		this.descObj = descObj;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public FaqMessage getMessageQuestion() {
		return messageQuestion;
	}

	public void setMessageQuestion(FaqMessage messageQuestion) {
		this.messageQuestion = messageQuestion;
	}

	public FaqMessage getMessageReply() {
		return messageReply;
	}

	public void setMessageReply(FaqMessage messageReply) {
		this.messageReply = messageReply;
	}

	public FaqStatusEnum getStatus() {
		return status;
	}

	public void setStatus(FaqStatusEnum status) {
		this.status = status;
	}
}
