package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import entities.Accounts;
import interfaces.DataBaseInterface;

public class AccountsDAL extends ObjectHelper implements DataBaseInterface<Accounts>
{
	@Override
	public void Insert(Accounts entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Accounts (PersonelId,YetkiId,Sifre) VALUES("+entity.getPersonelId()+","
			+entity.getYetkiId()+",'"
			+entity.getSifre()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Accounts GetPersonelData(int personelId,String sifre)
	{
		Accounts contract = new Accounts();
		List<Accounts> listele = new ArrayList<Accounts>();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId="+personelId+" AND Sifre='"+sifre.trim()+"'");
			
			while(rs.next())
			{
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return contract;
	}
	
	public Accounts GetYetkiId(int personelId)
	{
		Accounts contract = new Accounts();
		List<Accounts> listele = new ArrayList<Accounts>();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId="+personelId+"");
			while(rs.next())
			{
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return contract;
	}	
	
	@Override
	public List<Accounts> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Accounts Delete(Accounts entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Accounts entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Accounts> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}