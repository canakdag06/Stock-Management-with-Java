package arayuz;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.MusteriDAL;
import database.PersonelDAL;
import entities.Musteri;
import entities.Personel;
import interfaces.ArayuzInterface;

public class MusteriEkleFE extends JDialog implements ArayuzInterface
{

	public MusteriEkleFE() {
		initPencere();
		
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müþteri Ekle"));
		
		add(panel);
		setTitle("Müþteri Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(5,2));
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		
		JLabel adSoyadLabel = new JLabel("Ad Soyad:",JLabel.RIGHT);
		fieldPanel.add(adSoyadLabel);
		JTextField adSoyadField = new JTextField(15);
		fieldPanel.add(adSoyadField);
		
		JLabel telefonLabel = new JLabel("Telefon:",JLabel.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldPanel.add(telefonField);
		
		JTextArea adresArea = new JTextArea(7,1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		buttonPanel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Musteri contract = new Musteri();
				contract.setAdSoyad(adSoyadField.getText());
				contract.setTelefon(telefonField.getText());
				contract.setAdres(adresArea.getText());
				
				new MusteriDAL().Insert(contract);
				JOptionPane.showMessageDialog(null,contract.getAdSoyad()+" Adlý Müþteri Eklendi");
			}
		});
		
		panel.add(fieldPanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		
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
