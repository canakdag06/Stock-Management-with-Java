package entities;

public class Musteri
{
	private int id;
	private String adSoyad;
	private String telefon;
	private String adres;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdSoyad() {
		return adSoyad;
	}
	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	@Override
	public String toString()
	{
		return adSoyad;
	}
}