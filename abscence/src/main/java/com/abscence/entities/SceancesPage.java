package com.abscence.entities;

import java.util.List;

public class SceancesPage {
	public List<Sceance> sceances;
	public int totalPages;
	public SceancesPage(List<Sceance> sceances, int totalPages) {
		super();
		this.sceances = sceances;
		this.totalPages = totalPages;
	}
	public SceancesPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Sceance> getSceances() {
		return sceances;
	}
	public void setSceances(List<Sceance> Sceances) {
		this.sceances = sceances;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
