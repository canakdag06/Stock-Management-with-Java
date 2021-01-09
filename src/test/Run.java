package test;

import javax.swing.SwingUtilities;
import arayuz.LoginFE;
import database.UrunlerDAL;

public class Run
{
	public static void main(String[] args)
	{
		Calisan kisi = new Calisan();			//POLYMORPHISM
		YetkiliSatici yetkiliSatici = new YetkiliSatici();
		Satici satici = new Satici();
		isYap(kisi);
		isYap(yetkiliSatici);
		isYap(satici);
		
		somut s = new somut(); //ABSTRACT CLASS
		s.somutMetot();
		s.soyutMetot();
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new LoginFE();
				new UrunlerDAL().GetAll();
			}
		});;
	}
	
	public static void isYap(Calisan kisi)
	{
		kisi.calis();
	}
}

class Calisan
{
	public void calis()
	{
		System.out.println("Bu iþ yerinde farklý insanlar çalýþýr");
	}
}

class YetkiliSatici extends Calisan
{
	public void calis()
	{
		System.out.println("Yetkili Satýcý bu çalýþanlardan biridir");
	}
}

class Satici extends Calisan
{
	public void calis()
	{
		System.out.println("Satýcý da bu çalýþanlardan biridir");
	}
}
//----------------------------------------------------------------------
abstract class soyut
{
	abstract void soyutMetot();
	
	void somutMetot()
	{
		System.out.println("Somut metot");
	}	
}

class somut extends soyut
{
	@Override
	void soyutMetot() {
		System.out.println("Soyut metot");
	}
}