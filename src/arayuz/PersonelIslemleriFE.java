package arayuz;

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

import database.PersonelDAL;
import entities.Personel;
import interfaces.ArayuzInterface;

public class PersonelIslemleriFE extends JDialog implements ArayuzInterface
{
	public PersonelIslemleriFE()
	{
		initPencere();
	}

	@Override
	public void initPencere()
	{
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Personel Ýþlemleri"));
		add(panel);
		setTitle("Personel Düzenleme");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(4, 2));

		JLabel personelIdLabel = new JLabel("Personel Id: ", JLabel.RIGHT);
		panel.add(personelIdLabel);
		JTextField personelIdField = new JTextField(15);
		panel.add(personelIdField);

		JLabel personelAdiLabel = new JLabel("Personel Ad Soyad: ", JLabel.RIGHT);
		panel.add(personelAdiLabel);
		JTextField personelAdiField = new JTextField(15);
		panel.add(personelAdiField);

		JLabel personelEmailLabel = new JLabel("Personel Email :", JLabel.RIGHT);
		panel.add(personelEmailLabel);
		JTextField personelEmailField = new JTextField(15);
		panel.add(personelEmailField);
		
		JButton personelKaydetButton = new JButton("Personel Güncelle ");
		panel.add(personelKaydetButton);
		personelKaydetButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Personel contract = new Personel();
				contract.setId(Integer.parseInt(personelIdField.getText()));
				contract.setAdSoyad(personelAdiField.getText());
				contract.setEmail(personelEmailField.getText());
				new PersonelDAL().Update(contract);
				JOptionPane.showMessageDialog(null, "Personel Bilgileri Güncellendi");
			}
		});
		
		JButton personelIptalButton = new JButton("Personel Sil");
		panel.add(personelIptalButton);
		personelIptalButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Personel contract = new Personel();
				contract.setId(Integer.parseInt(personelIdField.getText()));
				new PersonelDAL().Delete(contract);
				JOptionPane.showMessageDialog(null, "Personel Bilgileri Silindi");	
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
