package arayuz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import complex_types.SatisComplex;
import complex_types.StokComplex;
import complex_types.StokTotalComplex;
import database.MusteriDAL;
import database.SatisDAL;
import database.StokDAL;
import database.UrunlerDAL;
import entities.Musteri;
import entities.Personel;
import entities.Satis;
import entities.Stok;
import entities.Urunler;
import interfaces.ArayuzInterface;
import utilities.Menuler;

public class AnaPencere extends JFrame implements ArayuzInterface
{
	public AnaPencere()
	{
		initPencere();
	}

	@Override
	public void initPencere() {
	JPanel panel = initPanel();
	JMenuBar bar = initBar();
	
	add(panel);
	setJMenuBar(bar);
	final String baslik = "Stok Takip Programý";
	setTitle(baslik);
	setSize(1000,400);
	setVisible(true);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JTabbedPane pane = initTabs();
		panel.add(pane,BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = Menuler.initBar();
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		
		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());
		
		
		/*STOK ÝÞLEMLERÝ*/
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5,2));
		JPanel stokSolAltPanel = new JPanel();
		
		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok Ýþlemleri"));
		Object [] stokKolonlar = {"Id","Ürün Adý","Personel Adý","Adet"};
		DefaultTableModel model = new DefaultTableModel(stokKolonlar,0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);
		
		
		for(StokComplex contract : new StokDAL().GetAllStok())
		{
			model.addRow(contract.getVeriler());
		}
		
		JLabel stokUrunAdiLabel = new JLabel("Ürün Adý:",JLabel.LEFT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);
		JLabel stokAdetLabel = new JLabel("Adet:",JLabel.LEFT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);
		
		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Listeyi Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokTotalButton = new JButton("Stok");
		stokSolUstPanel.add(stokTotalButton);
		
		stokTotalButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int satir = model.getRowCount();
					for(int i=0;i<satir;i++)
					{
					model.removeRow(0);
					}
					for(StokTotalComplex total : new StokDAL().GetTotalStok())
					{
						model.addRow(total.getVeriler());
					}
			}
		});
		
		
		stokYenileButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int satir = model.getRowCount();
				for(int i=0;i<satir;i++)
				{
					model.removeRow(0);
				}
				JOptionPane.showMessageDialog(null,"Liste Yenilendi");	
			}
		});
		
		for (StokComplex compContract : new StokDAL().GetAllStok()) {
			model.addRow(compContract.getVeriler());
		}
		
		
		stokEkleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Stok contract = new Stok();
				Urunler uContract = (Urunler) stokUrunAdiBox.getSelectedItem();
				Personel pContract = (Personel) LoginFE.emailBox.getSelectedItem();
				
				
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(stokAdetField.getText()));
				new StokDAL().Insert(contract);
				
				int satir = model.getRowCount();
				for(int i=0;i<satir;i++)
				{
					model.removeRow(0);
				}
				JOptionPane.showMessageDialog(null,"Liste Güncellendi");
				for(StokComplex compContract : new StokDAL().GetAllStok())
				{
					model.addRow(compContract.getVeriler());
				}
			}
		});
		
		
		/*SATIÞ ÝÞLEMLERÝ*/
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(5,2));
		JPanel satisSagAltPanel = new JPanel();
		
		satisSagPanel.setBorder(BorderFactory.createTitledBorder("Satýþ Ýþlemleri"));
		Object [] satisKolonlar = {"Id","Müþteri Adý","Personel Adý","Ürün Adý","Adet"};
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar,0);
		JTable satisTable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satisTable);
		
		JLabel musteriLabel = new JLabel("Müþteri Adý:",JLabel.LEFT);
		satisSagUstPanel.add(musteriLabel);
		JComboBox musteriAdiBox = new JComboBox(new MusteriDAL().GetAll().toArray());
		satisSagUstPanel.add(musteriAdiBox);
		JLabel satisUrunAdiLabel = new JLabel("Ürün Adý:",JLabel.LEFT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);
		JLabel satisAdetLabel = new JLabel("Adet:",JLabel.LEFT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField = new JTextField(10);
		satisSagUstPanel.add(satisAdetField);
		
		
		JButton satisEkleButton = new JButton("Satýþ Yap");
		satisSagUstPanel.add(satisEkleButton);
		for(SatisComplex yenileContract : new SatisDAL().GetAllSatis())
		{
			satisModel.addRow(yenileContract.getVeriler());
		}
		satisEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Personel pContract = (Personel) LoginFE.emailBox.getSelectedItem();
				Urunler uContract = (Urunler) satisUrunAdiBox.getSelectedItem();
				Musteri mContract = (Musteri) musteriAdiBox.getSelectedItem();
				Satis contract = new Satis();
				
				contract.setMusteriId(mContract.getId());
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(satisAdetField.getText()));
				
				new SatisDAL().Insert(contract);
				Stok stokContract = new Stok();
				stokContract.setPersonelId(pContract.getId());
				stokContract.setUrunId(uContract.getId());
				stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
				new StokDAL().Insert(stokContract);
				
				
				JOptionPane.showMessageDialog(null,mContract.getAdSoyad()+" Adlý Müþteriye "+contract.getAdet()+" Adet Satýþ Yapýldý");
				int satir = satisModel.getRowCount();
				for(int i=0;i<satir;i++)
				{
					satisModel.removeRow(0);
				}
				for(SatisComplex yenileContract : new SatisDAL().GetAllSatis())
				{
					satisModel.addRow(yenileContract.getVeriler());
				}
			}
		});
		
		
		JButton satisYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satisYenileButton);
		satisYenileButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int satir = satisModel.getRowCount();
				for(int i=0;i<satir;i++)
				{
					satisModel.removeRow(0);
				}
				for(SatisComplex contract : new SatisDAL().GetAllSatis())
				{
					satisModel.addRow(contract.getVeriler());
				}
			}
		});
		
		
		stokPanel.add(stokSolPanel,BorderLayout.WEST);
		stokPanel.add(stokTablePane,BorderLayout.CENTER);
		
		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);
		satisSagPanel.add(satisSagUstPanel,BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel,BorderLayout.SOUTH);
		
		stokSolPanel.add(stokSolUstPanel,BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel,BorderLayout.SOUTH);
		
		pane.addTab("Stoklar",null,stokPanel,"Does nothing");
		pane.addTab("Satýþlar",null,satisPanel,"yazý");
		return pane;
	}
}