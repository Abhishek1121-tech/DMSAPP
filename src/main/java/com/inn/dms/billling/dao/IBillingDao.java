package com.inn.dms.billling.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.report.wrapper.SalesmansalesWrapper;

@Repository
public interface IBillingDao extends JpaRepository<Billing, Long> {

	@Query("select new com.inn.dms.report.wrapper.SalesmansalesWrapper(item,cust.salesman) from Billing item left join item.customer cust join cust.salesman ")
	public List<SalesmansalesWrapper> findAllByCustomerNSalesman();
	@Query("select new com.inn.dms.report.wrapper.SalesmansalesWrapper(bill,cust.salesman) from Billing bill  left join bill.customer cust join cust.salesman where date(bill.transactionDate) BETWEEN :starTimestamp AND :endTimestamp")
	public List<SalesmansalesWrapper> findAllByCustomerNSalesman(@Param("starTimestamp") Date startDate, @Param("endTimestamp") Date endDate);
}
