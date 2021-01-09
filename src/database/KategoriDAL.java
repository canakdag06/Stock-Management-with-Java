package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import entities.Kategori;
import interfaces.DataBaseInterface;

public class KategoriDAL extends ObjectHelper implements DataBaseInterface<Kategori>{

	@Override
	public void Insert(Kategori entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Kategori (adi,parentId) VALUES ('"+entity.getAd()+"',"+entity.getParentId()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Kategori> GetAll() {
		List<Kategori> datacontract = new ArrayList<Kategori>();
		Connection connection = getConnection();
		Kategori contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
			while(resultSet.next()) {
				contract = new Kategori();
				contract.setId(resultSet.getInt("id"));
				contract.setAd(resultSet.getString("adi"));
				contract.setParentId(resultSet.getInt("parentId"));
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}
	
	public List<Kategori> GetAllParentId() {
		List<Kategori> datacontract = new ArrayList<Kategori>();
		Connection connection = getConnection();
		Kategori contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori WHERE parentId=0");
			while(resultSet.next()) {
				contract = new Kategori();
				contract.setId(resultSet.getInt("id"));
				contract.setAd(resultSet.getString("adi"));
				contract.setParentId(resultSet.getInt("parentID"));
				datacontract.add(contract);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}
	

	@Override
	public Kategori Delete(Kategori entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Kategori entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE Kategori SET Adi ='" + entity.getAd() + "', ParentId ="
					+ entity.getParentId() + "WHERE id =" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Kategori> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Kategori> GetSearchKategori(String kategoriAdi)
	{
		List<Kategori> dataContract = new ArrayList<Kategori>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT * FROM Kategori WHERE Adi Like '%"+kategoriAdi+"%'");

			while (resulset.next())
			{
				Kategori contract = new Kategori();
				contract.setAd(resulset.getString("Adi"));
				contract.setParentId(resulset.getInt("ParentId"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;
	}
}