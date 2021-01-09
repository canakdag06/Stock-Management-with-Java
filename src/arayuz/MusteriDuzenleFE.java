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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.MusteriDAL;
import entities.Musteri;
import interfaces.ArayuzInterface;

public class MusteriDuzenleFE extends JDialog implements ArayuzInterface
{

	public MusteriDuzenleFE()
	{
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		add(panel);
		setTitle("Müþteri Düzenle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(5, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		JLabel musteriIdLabel = new JLabel("Müþteri Id: ", JLabel.RIGHT);
		fieldPanel.add(musteriIdLabel);
		JTextField musteriIdField = new JTextField(15);
		fieldPanel.add(musteriIdField);

		JLabel musteriAdiSoyadiLabel = new JLabel("Adý Soyadý: ", JLabel.RIGHT);
		fieldPanel.add(musteriAdiSoyadiLabel);
		JTextField musteriAdiSoyadiField = new JTextField(15);
		fieldPanel.add(musteriAdiSoyadiField);

		JLabel musteriTelefonLabel = new JLabel("Telefon:", JLabel.RIGHT);
		fieldPanel.add(musteriTelefonLabel);
		JTextField musteriTelefonField = new JTextField(15);
		fieldPanel.add(musteriTelefonField);
		
		JTextArea musteriAdresArea = new JTextArea(4, 1);
		JScrollPane pane = new JScrollPane(musteriAdresArea);

		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));

		JButton guncelleButton = new JButton("Güncelle");
		buttonPanel.add(guncelleButton);
		
		guncelleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Musteri contract = new Musteri();
				contract.setId(Integer.parseInt(musteriIdField.getText()));
				contract.setAdSoyad(musteriAdiSoyadiField.getText());
				contract.setTelefon(musteriTelefonField.getText());
				contract.setAdres(musteriAdresArea.getText());
				
				new MusteriDAL().Update(contract);
				JOptionPane.showMessageDialog(null, contract.getAdSoyad() + " Adlý Müþterinin Bilgileri Güncellendi");
			}
		});

		JButton musteriSilButton = new JButton("Müþteri Sil");
		buttonPanel.add(musteriSilButton);
		musteriSilButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Musteri contract = new Musteri();
				contract.setId(Integer.parseInt(musteriIdField.getText()));
				new MusteriDAL().Delete(contract);
				JOptionPane.showMessageDialog(null, "Müþteri Silindi");
			}
		});
		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
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
