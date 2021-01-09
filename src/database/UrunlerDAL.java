package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import entities.Kategori;
import entities.Urunler;
import interfaces.DataBaseInterface;

public class UrunlerDAL extends ObjectHelper implements DataBaseInterface<Urunler>
{

	@Override
	public void Insert(Urunler entity) {
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Urunler(Adi,KategoriId,Fiyat)"
			+ "VALUES('"+entity.getAd()+"',"+entity.getKategoriId()+","+entity.getFiyat()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Urunler> GetAll() {
		List<Urunler> datacontract = new ArrayList<Urunler>();
		Connection connection = getConnection();
		Urunler contract;
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
			while(resultSet.next())
			{
				contract = new Urunler();
				contract.setId(resultSet.getInt("Id"));
				contract.setAd(resultSet.getString("Adi"));
				contract.setFiyat(resultSet.getFloat("Fiyat"));
				contract.setKategoriId(resultSet.getInt("KategoriId"));
				datacontract.add(contract);
			}
		} catch (SQLException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return datacontract;
	}

	@Override
	public Urunler Delete(Urunler entity)
	{
		Connection connection = getConnection();
		try
		{
			Statement statement = connection.createStatement();
			System.out.println(entity.getId());
			String sorgu = "Delete from urunler where Id=" + entity.getId();
			statement.executeUpdate(sorgu);
			statement.close();
			connection.close();
		} catch (SQLException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
	}

	@Override
	public void Update(Urunler entity)
	{
		Connection connection = getConnection();
		try
		{
			Statement statement = connection.createStatement();
			String sorgu = "Update urunler set Fiyat='" + entity.getFiyat() + "',Adi='" + entity.getAd() + "' where Id=" + entity.getId();
			statement.executeUpdate(sorgu);
			statement.close();
			connection.close();
		} catch (SQLException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

	@Override
	public List<Urunler> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}