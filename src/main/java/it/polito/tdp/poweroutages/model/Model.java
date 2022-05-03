package it.polito.tdp.poweroutages.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<Rilevamento> migliore;
	private List<Rilevamento> rilevamenti;
	private int anni;
	private int maxOre;
	private int contaOre;
	private int utentiOttimo;
	private int cont;
	
	public Model() {
		podao = new PowerOutageDAO();
		rilevamenti = new LinkedList<>();
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public String trovaSequenza(int id, int anni, int maxOre){
		rilevamenti=podao.getRilevamentiPerNerc(id);
		this.anni=anni;
		this.maxOre=maxOre;
		LinkedList<Rilevamento> parziale = new LinkedList<>();
		migliore = new LinkedList<>();
		cont=0;
		cerca(parziale,0);
		String s="";
		for(Rilevamento r: migliore)
		{
			if(s!="")
				s+="\n";
			s+=r.toString();
		}
		return s;
		
	}

	private void cerca(LinkedList<Rilevamento> parziale, int L) {
		//caso terminale
		if(L>maxOre) {
		int utenti = calcolaUtenti(parziale);
			if(utenti>utentiOttimo)
			{
				utentiOttimo=utenti;
				migliore.clear();
				for(int i=0;i<parziale.size()-1;i++)
					migliore.add(parziale.get(i));
			}
			return;
		}
		else if(cont==rilevamenti.size()) {
			int utenti = calcolaUtenti2(parziale);
			if(utenti>utentiOttimo)
			{
				utentiOttimo=utenti;
				migliore.clear();
				for(int i=0;i<parziale.size();i++)
					migliore.add(parziale.get(i));
			}
			return;
		}
		for(Rilevamento r: rilevamenti) {
			cont++;
			if(r.getDiff()<=maxOre && !parziale.contains(r)) {
				if(parziale.isEmpty()) 
					parziale.add(r);
				else
				{
					int annoInizio = parziale.get(0).getDataInizio().getYear();
					int annoFine = r.getDataInizio().getYear();
					if(annoInizio-annoFine<=anni)
					{
						parziale.add(r);
					}
				}
				L+=r.getDiff();
				cerca(parziale,L);
				L-=parziale.get(parziale.size()-1).getDiff();
				parziale.remove(parziale.size()-1);
				cont--;
			}
			
			
		}
				
		
	}

	private int calcolaUtenti2(LinkedList<Rilevamento> parziale) {
		int utenti=0;
		for(int i=0; i<parziale.size();i++)
			utenti+=parziale.get(i).getUtenti();
		return utenti;
	}

	private int calcolaUtenti(LinkedList<Rilevamento> parziale) {
		int utenti=0;
		for(int i=0; i<parziale.size()-1;i++)
			utenti+=parziale.get(i).getUtenti();
		return utenti;
	}

}
