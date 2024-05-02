package com.company_management.repository.impl;


import com.company_management.model.entity.EntBase;
import com.company_management.model.response.DataPage;
import com.company_management.repository.CustomBaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Component
public class CustomBaseRepositoryImpl<ENT extends EntBase> implements CustomBaseRepository<ENT> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<ENT> persistentClass;

    @Override
    public DataPage<Object[]> queryHasParam(String query, Map<String, Object> params, PageRequest paging) {
        DataPage<Object[]> data = new DataPage();

        String queryCount = "SELECT COUNT(*) FROM (" + query + ")" + " as X";
        Query searchQuery = getEntityManager().createNativeQuery(query);
        Query countQuery = getEntityManager().createNativeQuery(queryCount);

        if (!params.isEmpty()) {
            params.keySet().forEach((String key) -> {
                searchQuery.setParameter(key, params.get(key));
                countQuery.setParameter(key, params.get(key));
            });
        }
        BigInteger count = (BigInteger) countQuery.getSingleResult();
        searchQuery.setFirstResult(paging.getPageNumber() * paging.getPageSize());
        searchQuery.setMaxResults(paging.getPageSize());
        List<Object[]> list = searchQuery.getResultList();

        data.setPageSize(paging.getPageSize());
        data.setPageIndex(paging.getPageNumber());
        data.setData(list);
        data.setDataCount(count.longValue());
        data.setPageCount(data.getDataCount() / data.getPageSize());
        if (data.getDataCount() % data.getPageSize() != 0) {
            data.setPageCount(data.getPageCount() + 1);
        }
        return data;
    }

    public DataPage<Object[]> queryHasParam1(String query, Map<String, Object> params, PageRequest paging) {
        DataPage<Object[]> data = new DataPage<>();
        query = query.trim();
        String queryCount = query;
        if (query.startsWith("SELECT")) {
            int indexFrom = query.indexOf("FROM");
            if (indexFrom != -1) {
                queryCount = queryCount.substring(indexFrom);
            }
        }
        queryCount = "SELECT COUNT(*) " + queryCount;
        Query sreachQuery = getEntityManager().createNativeQuery(query);
        Query countQuery = getEntityManager().createNativeQuery(queryCount);
        // set parameters
        if (!params.isEmpty()) {
            params.keySet().forEach((String key) -> {
                sreachQuery.setParameter(key, params.get(key));
                countQuery.setParameter(key, params.get(key));
            });
        }
        long count = sreachQuery.getResultList().size();
        sreachQuery.setFirstResult(paging.getPageNumber() * paging.getPageSize());
        sreachQuery.setMaxResults(paging.getPageSize());
        List<Object[]> list = sreachQuery.getResultList();

        data.setPageSize(paging.getPageSize());
        data.setPageIndex(paging.getPageNumber());
        data.setData(list);
        data.setDataCount(count);
        data.setPageCount(data.getDataCount() / data.getPageSize());
        if (data.getDataCount() % data.getPageSize() != 0) {
            data.setPageCount(data.getPageCount() + 1);
        }
        return data;
    }

    public List<Object[]> queryHasParam(String query, Map<String, Object> params) {

        String queryCount = "SELECT COUNT(*) FROM (" + query + ")" + " as X";
        Query searchQuery = getEntityManager().createNativeQuery(query);
        Query countQuery = getEntityManager().createNativeQuery(queryCount);

        if (!params.isEmpty()) {
            params.keySet().forEach((String key) -> {
                searchQuery.setParameter(key, params.get(key));
                countQuery.setParameter(key, params.get(key));
            });
        }
        return (List<Object[]>) searchQuery.getResultList();
    }

    @Override
    public DataPage<Object[]> queryHasParam2(String query, Map<String, Object> params) {
        DataPage<Object[]> data = new DataPage<>();

        String queryCount = "SELECT COUNT(*) FROM (" + query + ")" + " as X";
        Query searchQuery = getEntityManager().createNativeQuery(query);
        Query countQuery = getEntityManager().createNativeQuery(queryCount);

        if (!params.isEmpty()) {
            params.keySet().forEach((String key) -> {
                searchQuery.setParameter(key, params.get(key));
                countQuery.setParameter(key, params.get(key));
            });
        }
        List<Object[]> list = searchQuery.getResultList();
        data.setData(list);
        return data;
    }

    @Override
    public Long getCount(String query, Map<String, Object> params) {
        String queryCount = "SELECT COUNT(*) FROM (" + query + ")" + " as X";
        Query countQuery = getEntityManager().createNativeQuery(queryCount);
        if (!params.isEmpty()) {
            params.keySet().forEach((String key) -> countQuery.setParameter(key, params.get(key)));
        }
        return ((BigInteger) countQuery.getSingleResult()).longValue();
    }

    @Transactional("transactionManager")
    @Override
    public ENT insert(ENT ent) {
        ent.setIsActive(1);
//    ent.setCreateDate(new Date());
//    ent.setCreateUser(1L);
//    ent.setUserModifyId(1L);
        getEntityManager().persist(ent);
        return ent;
    }


    @Transactional("transactionManager")
    @Override
    public List<ENT> insert(List<ENT> listEntity, int batchSize) {
        if (listEntity != null && !listEntity.isEmpty()) {
            for (int i = 0; i < listEntity.size(); i++) {
                insert(listEntity.get(i));
                if (i % batchSize == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
        }
        return listEntity;
    }

    @Transactional("transactionManager")
    @Override
    public List<ENT> insert(List<ENT> listEntity) {
        return insert(listEntity, 50);
    }


    @Transactional("transactionManager")
    @Override
    public void deleteById(Long id, Class<ENT> entClass) {
        ENT ent = getEntityManager().find(entClass, id);
        ent.setIsActive(0);
    }

}
