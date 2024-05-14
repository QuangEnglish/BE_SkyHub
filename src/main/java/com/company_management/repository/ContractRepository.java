package com.company_management.repository;

import com.company_management.model.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>, ContractRepositoryCustom {

    String sqlSearch = "select\n" +
            "    ctr.id as contractId,\n" +
            "    ctr.contract_code as contractCode,\n" +
            "    ctr.contract_type as contractType,\n" +
            "    ctr.attachfile as attachfile,\n" +
            "    ctr.is_active as isActive\n" +
            "from contract ctr\n" +
            "where 1 = 1\n" +
            "and ctr.is_active = 1\n" +
            "and (:contractType IS NULL OR LOWER(ctr.contract_type) LIKE LOWER(CONCAT('%', :contractType, '%')))\n" +
            "and (:contractCode IS NULL OR LOWER(ctr.contract_code) LIKE LOWER(CONCAT('%', :contractCode, '%'))) ";
    @Query(nativeQuery = true, value = sqlSearch, countQuery = "select count(*) from ( " + sqlSearch + " ) tmp" )
    Page<Object[]> findAllWithPagination(
            @Param("contractCode") String contractCode,
            @Param("contractType") String contractType,
            Pageable pageable);

    @Modifying
    @Query(value = "update Contract c set c.isActive = 0, c.updatedDate = now(), c.updatedUser = :user where c.id = :id and c.isActive = 1")
    int updateById(Long id, Long user);

    @Query(value = """
            select udct.contract_type as name, count(ctr.id) as value
            from contract ctr
            left join (
                select contract_id, c.contract_type
                from
                    contract c left join user_detail_contract udc
                                         on c.id = udc.contract_id
                where user_detail_id is not null
                  and udc.active_date in (select udca.aDate from (select MAX(udc.active_date) as aDate
                                                                  from
                                                                      contract c left join user_detail_contract udc
                                                                                           on c.id = udc.contract_id
                                                                  where user_detail_id is not null
                                                                    and udc.is_active =1
                                                                    and c.is_active = 1
                                                                  group by  user_detail_id
                                                                  order by user_detail_id desc) udca)
                group by  user_detail_id
            ) as udct
            on ctr.id = udct.contract_id
            where udct.contract_type is not null
            group by udct.contract_type;
                        """, nativeQuery = true)
    List<Object[]> getStatisticalContract();

}
