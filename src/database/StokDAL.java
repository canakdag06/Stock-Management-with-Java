package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import complex_types.StokComplex;
import complex_types.StokTotalComplex;
import core.ObjectHelper;
import entities.Stok;
import interfaces.DataBaseInterface;

public class StokDAL extends ObjectHelper implements DataBaseInterface<Stok>
{

	@Override
	public void Insert(Stok entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Stock (PersonelId, UrunId, Adet) VALUES ("+entity.getPersonelId()
			+","+entity.getUrunId()+","+entity.getAdet()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<StokComplex> GetAllStok()
	{
		List<StokComplex> datacontract = new ArrayList<StokComplex>();
		Connection connection = getConnection();
		StokComplex contract;
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stock.Id, AdSoyad, Adi, Adet FROM stock"
					+ " LEFT JOIN urunler on stock.UrunId = urunler.Id "
					+ " LEFT JOIN personel on stock.PersonelId = personel.Id ORDER BY stock.Id DESC");
			while(resultSet.next())
			{
				contract = new StokComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdSoyad"));
				contract.setUrunAdi(resultSet.getString("urunler.Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				
				datacontract.add(contract);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return datacontract;
	}
	
	public List<StokTotalComplex> GetTotalStok()
	{
		List<StokTotalComplex> datacontract = new ArrayList<StokTotalComplex>();
		Connection connection = getConnection();
		StokTotalComplex contract;
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(Adet) as toplam, stock.Id, AdSoyad, Adi, Adet FROM stock LEFT JOIN"
					+ " urunler on stock.UrunId = urunler.Id LEFT JOIN personel on stock.PersonelId = personel.Id GROUP BY UrunId"
					+ " ORDER BY toplam desc");
			while(resultSet.next())
			{
				contract = new StokTotalComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdSoyad"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("toplam"));
				
				datacontract.add(contract);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return datacontract;
	}
	
	
	@Override
	public List<Stok> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stok Delete(Stok entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Stok entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stok> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}