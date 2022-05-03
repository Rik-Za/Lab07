package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Rilevamento;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Rilevamento> getRilevamentiPerNerc(int id){
		String sql ="SELECT customers_affected, date_event_began, date_event_finished, HOUR(TIMEDIFF(date_event_began, date_event_finished)) AS diff "
				+ "FROM poweroutages "
				+ "WHERE nerc_id=?";
		List<Rilevamento> rilevamenti = new LinkedList<>();
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Rilevamento r = new Rilevamento(rs.getInt("customers_affected"), rs.getDate("date_event_began"), rs.getDate("date_event_finished"), rs.getInt("diff"));
				rilevamenti.add(r);
			}
			conn.close();
			return rilevamenti;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	

}
