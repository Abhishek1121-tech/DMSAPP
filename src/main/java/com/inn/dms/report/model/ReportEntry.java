package com.inn.dms.report.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "REPORTENTRY")
public class ReportEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="report_id_pk")
	private long id;	
	
	@Column(name="reportname")
	private String reportName; 
	
	@Column(name="reporttype")
	private String reportType;
	
	@Column(name="reportselectiontype")
	private String reportSelectionType;
	
	@Column(name="failedreason")
	private String failedReason;
	
	@Column(name="reportstatus")
	private String reportStatus;
	
	@Column(name="reportstartrangetimestampinms")
	private Timestamp reportStartRangeTimestampInMs;
	
	@Column(name="reportendrangetimestampinms")
	private Timestamp reportEndRangeTimestampInMs;
	
	@Column(name= "reportdownloadurl")
	private String reportDownloadURL;
	
	@CreationTimestamp
	@Column(name = "reportcreationdate")
	private Timestamp reportCreationDate;
	
	@UpdateTimestamp
	@Column(name = "modifiedreportcreationdate")
	private Timestamp modifiedReportCreationDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportSelectionType() {
		return reportSelectionType;
	}

	public void setReportSelectionType(String reportSelectionType) {
		this.reportSelectionType = reportSelectionType;
	}

	public String getFailedReason() {
		return failedReason;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Timestamp getReportStartRangeTimestampInMs() {
		return reportStartRangeTimestampInMs;
	}

	public void setReportStartRangeTimestampInMs(Timestamp reportStartRangeTimestampInMs) {
		this.reportStartRangeTimestampInMs = reportStartRangeTimestampInMs;
	}

	public Timestamp getReportEndRangeTimestampInMs() {
		return reportEndRangeTimestampInMs;
	}

	public void setReportEndRangeTimestampInMs(Timestamp reportEndRangeTimestampInMs) {
		this.reportEndRangeTimestampInMs = reportEndRangeTimestampInMs;
	}

	public Timestamp getReportCreationDate() {
		return reportCreationDate;
	}

	public void setReportCreationDate(Timestamp reportCreationDate) {
		this.reportCreationDate = reportCreationDate;
	}

	public Timestamp getModifiedReportCreationDate() {
		return modifiedReportCreationDate;
	}

	public void setModifiedReportCreationDate(Timestamp modifiedReportCreationDate) {
		this.modifiedReportCreationDate = modifiedReportCreationDate;
	}
	
	public String getReportDownloadURL() {
		return reportDownloadURL;
	}

	public void setReportDownloadURL(String reportDownloadURL) {
		this.reportDownloadURL = reportDownloadURL;
	}

	@Override
	public String toString() {
		return "ReportEntry [id=" + id + ", reportName=" + reportName + ", reportType=" + reportType
				+ ", reportSelectionType=" + reportSelectionType + ", failedReason=" + failedReason + ", reportStatus="
				+ reportStatus + ", reportStartRangeTimestampInMs=" + reportStartRangeTimestampInMs
				+ ", reportEndRangeTimestampInMs=" + reportEndRangeTimestampInMs + ", reportDownloadURL="
				+ reportDownloadURL + ", reportCreationDate=" + reportCreationDate + ", modifiedReportCreationDate="
				+ modifiedReportCreationDate + "]";
	}
}
