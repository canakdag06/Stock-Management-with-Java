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

public class PersonelEkleFE extends JDialog implements ArayuzInterface
{
	public PersonelEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Personel Ekle"));
		add(panel);
		setTitle("Personel Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel adSoyadLabel = new JLabel("Ad Soyad:",JLabel.RIGHT);
		panel.add(adSoyadLabel);
		JTextField adSoyadField = new JTextField(10);
		panel.add(adSoyadField);
		JLabel eMailLabel = new JLabel("E-Mail:",JLabel.RIGHT);
		panel.add(eMailLabel);
		JTextField eMailField = new JTextField(10);
		panel.add(eMailField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Personel contract = new Personel();
				contract.setAdSoyad(adSoyadField.getText());
				contract.setEmail(eMailField.getText());
				
				new PersonelDAL().Insert(contract);
				JOptionPane.showMessageDialog(null,contract.getAdSoyad()+" Adlý Personel Eklendi");
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