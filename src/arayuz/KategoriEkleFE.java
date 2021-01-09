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
import entities.Kategori;
import interfaces.ArayuzInterface;

public class KategoriEkleFE extends JDialog implements ArayuzInterface
{

	public KategoriEkleFE()
	{
		initPencere();
		
	}

	@Override
	public void initPencere()
	{
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekle"));
		add(panel);
		
		setTitle("Kategori Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		
		JLabel adLabel = new JLabel("Kategori Adý:",JLabel.RIGHT);
		panel.add(adLabel);
		JTextField adField = new JTextField(15);
		panel.add(adField);
		
		JLabel kategoriLabel = new JLabel("Kategori Seç:",JLabel.RIGHT);
		panel.add(kategoriLabel);
		
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("--Kategori Seçiniz--",0);
		kategoriBox.setSelectedIndex(0);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kategori contract = new Kategori();
				
				if(kategoriBox.getSelectedIndex()!=0)
				{
					Kategori casContract = (Kategori) kategoriBox.getSelectedItem();
					contract.setAd(adField.getText());
					contract.setParentId(casContract.getId());
					
					new KategoriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null,contract.getAd()+" Kategorisi Eklendi");
				}
				else
				{
					contract.setAd(adField.getText());
					contract.setParentId(kategoriBox.getSelectedIndex());
					
					new KategoriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null,contract.getAd()+" Kategorisi Eklendi");
					kategoriBox.addItem(new KategoriDAL().GetAllParentId());
				}
				
			}
		});
		
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		
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
