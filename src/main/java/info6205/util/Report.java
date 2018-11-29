package info6205.util;

import java.util.ArrayList;

public class Report {
	private ArrayList<Record> report;
	private String result;

	public Report() {
		this.report = new ArrayList<Record>();
	}

	public ArrayList<Record> getReport() {
		return report;
	}

	public void setReport(ArrayList<Record> report) {
		this.report = report;
	}

	public void addRecord(Record record) {
		report.add(record);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
