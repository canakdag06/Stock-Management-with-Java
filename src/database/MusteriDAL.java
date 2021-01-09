package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import entities.Kategori;
import entities.Musteri;
import interfaces.DataBaseInterface;

public class MusteriDAL extends ObjectHelper implements DataBaseInterface<Musteri> {

	@Override
	public void Insert(Musteri entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Musteri (AdiSoyadi, Telefon, Adres) VALUES('" + entity.getAdSoyad() + "','"
					+ entity.getTelefon() + "','" + entity.getAdres() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Musteri> GetAll() {
		List<Musteri> datacontract = new ArrayList<Musteri>();
		Connection connection = getConnection();
		Musteri contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Musteri");
			
			while(resultSet.next()) {
				contract = new Musteri();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdSoyad(resultSet.getString("AdiSoyadi"));
				contract.setAdres(resultSet.getString("Adres"));
				contract.setTelefon(resultSet.getString("Telefon"));
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public Musteri Delete(Musteri entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			String sorgu = "Delete from musteri where Id =" + entity.getId();
			statement.executeUpdate(sorgu);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void Update(Musteri entity) {
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();

			String sorgu = "Update musteri set AdiSoyadi ='" + entity.getAdSoyad() + "',Telefon ='"
					+ entity.getTelefon() + "',Adres ='" + entity.getAdres() + "' where Id =" + entity.getId();
			statement.executeUpdate(sorgu);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Musteri> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}