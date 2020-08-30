package com.inn.dms.report.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inn.dms.report.model.ReportEntry;

@Repository
public interface IReportGenerationDao extends JpaRepository<ReportEntry, Long>{

	@Query("SELECT r from ReportEntry r where r.reportStatus = :reportStatus ")
	public List<ReportEntry> getAllQueuedReportLists(String reportStatus, Pageable pageable);
}
