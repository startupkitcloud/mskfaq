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

    public Object retrieveObject (Faq faq){

        Object object = null;

        try {

            Class<?> klass = Class.forName(faq.getTypeObj());

            object = entityManager.find(klass, faq.getIdObj());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
}
