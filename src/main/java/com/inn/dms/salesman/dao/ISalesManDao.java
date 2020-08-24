package com.inn.dms.salesman.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inn.dms.salesman.model.Salesman;
import com.inn.dms.salesman.wrapper.SalesManMobileWrapper;
@Repository
public interface ISalesManDao extends JpaRepository<Salesman, Long> {
	
	@Query("SELECT count(*) FROM Salesman p WHERE p.mobile = :mobile")
	public Integer verifyUserByMobileNumber(@Param("mobile")Long mobile);

	@Query("SELECT p FROM Salesman p WHERE p.mobile = :mobile")
	public Salesman getSalesmanIdByMobile(@Param("mobile")Long mobile);
	
	@Query("SELECT new com.inn.dms.salesman.wrapper.SalesManMobileWrapper(name,mobile)   FROM Salesman ")
	public List<SalesManMobileWrapper> getSalesmanIdByMobile();
	
	@Query("SELECT p.mobile FROM Salesman p where str(p.mobile) like CONCAT(:mobile, '%')")
	public List<Long> searchSalesmanIdByMobile(@Param("mobile")Long mobile);
	
}
