package arayuz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import database.KategoriDAL;
import database.UrunlerDAL;
import entities.Kategori;
import entities.Urunler;
import interfaces.ArayuzInterface;

public class UrunEkleFE extends JDialog implements ArayuzInterface{

	public UrunEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayýt"));
		add(panel);
		setTitle("Ürün Ekleyin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(4,2));
		JLabel adLabel = new JLabel("Ürün Adý:",JLabel.RIGHT);
		panel.add(adLabel);
		JTextField adField = new JTextField(10);
		panel.add(adField);
		JLabel kategoriLabel = new JLabel("Kategori:",JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
		panel.add(kategoriBox);
		JLabel fiyatLabel = new JLabel("Fiyat:",JLabel.RIGHT);
		panel.add(fiyatLabel);
		JTextField fiyatField = new JTextField(10);
		panel.add(fiyatField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Urunler contract = new Urunler();
				Kategori casContract = (Kategori) kategoriBox.getSelectedItem();
				
				contract.setAd(adField.getText());
				contract.setKategoriId(casContract.getId());
				//contract.setTarih(date);
				contract.setFiyat(Float.parseFloat(fiyatField.getText()));
				new UrunlerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null,contract.getAd()+" Adlý Ürün Eklendi");
			}
		});
		
		iptalButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}
}