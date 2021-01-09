package arayuz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import database.UrunlerDAL;
import entities.Urunler;
import interfaces.ArayuzInterface;

public class UrunDuzenleFE extends JDialog implements ArayuzInterface
{
	public UrunDuzenleFE()
	{
		initPencere();
	}

	@Override
	public void initPencere()
	{
		JPanel panel = initPanel();
		add(panel);
		setTitle("Ürün Düzenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel()
	{
		JPanel panel = new JPanel(new GridLayout(4, 3));
		panel.setBorder(BorderFactory.createTitledBorder("Ürün Düzenle"));
		
		JLabel urunIdLabel = new JLabel("Ürün Id:", JLabel.RIGHT);
		panel.add(urunIdLabel);
		JTextField urunIdField = new JTextField(15);
		panel.add(urunIdField);
		
		JLabel urunAdiLabel = new JLabel("Ürün Adý: ", JLabel.RIGHT);
		panel.add(urunAdiLabel);
		JTextField urunAdiField = new JTextField(15);
		panel.add(urunAdiField);
		
		JLabel urunFiyatLabel = new JLabel("Fiyat: ", JLabel.RIGHT);
		panel.add(urunFiyatLabel);
		JTextField urunFiyatField = new JTextField(15);
		panel.add(urunFiyatField);
		
		JButton urunGuncelleButton = new JButton("Güncelle");
		panel.add(urunGuncelleButton);
		
		urunGuncelleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Urunler contract = new Urunler();
				contract.setAd(urunAdiField.getText());
				contract.setId(Integer.parseInt(urunIdField.getText()));
				contract.setFiyat(Float.parseFloat(urunFiyatField.getText()));
				new UrunlerDAL().Update(contract);
				JOptionPane.showMessageDialog(null, "Ürün Bilgileri Güncellendi");
			}
		});
		
		JButton urunSilButton = new JButton("Sil");
		panel.add(urunSilButton);
		
		urunSilButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Urunler contract = new Urunler();
				contract.setId(Integer.parseInt(urunIdField.getText()));
				new UrunlerDAL().Delete(contract);
				JOptionPane.showMessageDialog(null,"Ürün Silindi");
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