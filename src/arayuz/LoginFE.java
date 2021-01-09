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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import database.AccountsDAL;
import database.PersonelDAL;
import entities.Personel;
import interfaces.ArayuzInterface;

public class LoginFE extends JDialog implements ArayuzInterface
{
	public static JComboBox emailBox;

	public LoginFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Satýþ/Stok Takip Sistemine Hoþgeldiniz"));
		add(panel);
		setTitle("Giriþ");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel()
	{
		JPanel panel = new JPanel(new GridLayout(3,2));
		JLabel emailLabel = new JLabel("Kullanýcý: ",JLabel.RIGHT);
		panel.add(emailLabel);
		emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		JLabel passwordLabel = new JLabel("Þifreniz: ",JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(15);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Giriþ Yap");
		panel.add(loginButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Personel contract = (Personel) emailBox.getSelectedItem();
				String sifre = passwordField.getText();
				if(new AccountsDAL().GetPersonelData(contract.getId(), sifre).getId()!=0)
				{
					new AnaPencere();
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Giriþ Baþarýsýz");
				}
			}
		});
		
		
		iptalButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
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
