package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import complex_types.SatisComplex;
import core.ObjectHelper;
import entities.Satis;
import interfaces.DataBaseInterface;

public class SatisDAL extends ObjectHelper implements DataBaseInterface<Satis> {

	@Override
	public void Insert(Satis entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Satis (UrunId, MusteriId, Adet, PersonelId) VALUES ("
					+ entity.getUrunId() + "," + entity.getMusteriId() + ","+entity.getAdet()+","+entity.getPersonelId()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<SatisComplex> GetAllSatis() {
		List<SatisComplex> dataContract = new ArrayList<SatisComplex>();
		Connection conn = getConnection();
		SatisComplex contract;
		
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT satis.Id, personel.AdSoyad, musteri.AdiSoyadi, Adi, Adet FROM satis "
					+ "LEFT JOIN musteri on satis.MusteriId = musteri.Id "
					+ "LEFT JOIN urunler on satis.UrunId = urunler.Id "
					+ "LEFT JOIN personel on satis.PersonelId = personel.Id ORDER BY satis.Id DESC");
			while(rs.next())
			{
				contract = new SatisComplex();
				
				contract.setId(rs.getInt("satis.Id"));
				contract.setMusteriAdi(rs.getString("musteri.AdiSoyadi"));
				contract.setPersonelAdi(rs.getString("personel.AdSoyad"));
				contract.setUrunAdi(rs.getString("Adi"));
				contract.setAdet(rs.getInt("Adet"));
				
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;
	}
	
	@Override
	public List<Satis> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Satis Delete(Satis entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Satis entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Satis> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}