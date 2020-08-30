package com.inn.dms.report.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.dms.report.model.ReportEntry;

@Repository
public interface IReportDao extends JpaRepository<ReportEntry, Long> {

	

}
