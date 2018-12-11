package com.mangobits.startupkit.faq.faq;

import com.mangobits.startupkit.core.dao.AbstractDAO;
import com.mangobits.startupkit.core.exception.DAOException;
import com.mongodb.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FaqDAO extends AbstractDAO<Faq> {

    public FaqDAO(){
        super(Faq.class);
    }


    @Override
    public Object getId(Faq obj) {
        return obj.getId();
    }


}
