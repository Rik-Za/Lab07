package it.polito.tdp.poweroutages.model;

import java.sql.Date;

public class Rilevamento {
	private int utenti;
	private Date dataInizio;
	private Date dataFine;
	private int diff;
	
	public Rilevamento(int utenti, Date dataInizio, Date dataFine, int diff) {
		super();
		this.utenti = utenti;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.diff=diff;
	}

	public int getUtenti() {
		return utenti;
	}

	public void setUtenti(int utenti) {
		this.utenti = utenti;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	

	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	@Override
	public String toString() {
		return Integer.toString(dataInizio.toLocalDate().getYear())+" "+dataInizio.toLocalDate().toString()+" "+dataFine.toLocalDate().toString()+ " "+diff+" "+utenti;
	}
	
	
	
	
}
