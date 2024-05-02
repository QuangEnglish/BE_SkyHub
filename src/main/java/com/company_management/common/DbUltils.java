package com.company_management.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.math.BigDecimal;

public class DbUltils {
    public static Long getSequence(EntityManager em, String sequenceName) {
        String sql = "select " + sequenceName + ".nextval from dual";
        Query query = em.createNativeQuery(sql);
        return ((BigDecimal) query.getSingleResult()).longValue();
    }
}
