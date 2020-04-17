package com.mangobits.startupkit.faq.faq;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.SortableField;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Embeddable
@Indexed
public class FaqMessage {

    private String idUser;

    private String nameUser;

    @Field
    @SortableField
    private Date creationDate;

    private String message;

    @Field
    @Enumerated(EnumType.STRING)
    private FaqTypeEnum typeEnum;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FaqTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(FaqTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
