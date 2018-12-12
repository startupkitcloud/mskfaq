package com.mangobits.startupkit.faq.faq;

import com.mangobits.startupkit.core.dao.AbstractDAO;

public class FaqDAO extends AbstractDAO<Faq> {

    public FaqDAO(){
        super(Faq.class);
    }


    @Override
    public Object getId(Faq obj) {
        return obj.getId();
    }


}
