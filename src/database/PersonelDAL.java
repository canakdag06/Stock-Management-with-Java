package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import entities.Kategori;
import entities.Personel;
import interfaces.DataBaseInterface;

public class PersonelDAL extends ObjectHelper implements DataBaseInterface<Personel>
{

	@Override
	public void Insert(Personel entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Personel (AdSoyad,Email) VALUES ('"+entity.getAdSoyad()+"','"+entity.getEmail()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Personel> GetAll() {
		List<Personel> datacontract = new ArrayList<Personel>();
		Connection connection = getConnection();
		Personel contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Personel");
			while(resultSet.next()) {
				contract = new Personel();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdSoyad(resultSet.getString("AdSoyad"));
				contract.setEmail(resultSet.getString("Email"));
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public Personel Delete(Personel entity)
	{
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			System.out.println(entity.getId());
			String sorgu = "Delete from personel where Id =" + entity.getId();
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
	public void Update(Personel entity)
	{
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			String sorgu = "Update personel set AdSoyad='" + entity.getAdSoyad() + "',Email ='" + entity.getEmail()
					+ "' where Id =" + entity.getId();
			statement.executeUpdate(sorgu);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Personel> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}