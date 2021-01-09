package arayuz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import database.KategoriDAL;
import interfaces.ArayuzInterface;

public class KategoriDuzenleFE extends JDialog implements ArayuzInterface
{

	public KategoriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere()
	{
		JPanel panel = initPanel();
		add(panel);
		setTitle("Kategori Listele");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3));
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Arama"));
		
		JLabel kategoriAdiLabel = new JLabel("Kategori Adý: ", JLabel.LEFT);
		panel.add(kategoriAdiLabel);
		JTextField kategoriAdiField = new JTextField(15);
		panel.add(kategoriAdiField);
		
		JList kategoriList = new JList();
		kategoriList.setListData(new KategoriDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(kategoriList);
		pane.setBorder(BorderFactory.createTitledBorder("Sistemde Bulunan Kategoriler"));
		kategoriList.setSelectedIndex(0);
		
		kategoriAdiField.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				kategoriList.setListData(new KategoriDAL().GetSearchKategori(kategoriAdiField.getText()).toArray());
				kategoriList.setSelectedIndex(0);
			}
		});
		panel.add(pane, BorderLayout.CENTER);
		
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
