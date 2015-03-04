package dsa_project;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Iterator;

public class RealEstate {

	private JFrame frame;
	private JTextField txtLotNumber;
	private JTextField txtFirsName;
	private JTextField txtLastName;
	private JTextField txtPrice;
	private JTextField txtSquareFeet;
	private JTextField txtNoOfBedRooms;
	private DoublyLinkList<House> houselist;
	private House house;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RealEstate window = new RealEstate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	void addHouse() {
		
		if(txtLotNumber.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter a Lot Number", "Lot Number Is Empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(txtFirsName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter your First Name", "First Name Is Empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(txtLastName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter your Last Name", "Last Name Is Empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(txtPrice.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter the Price", "Price Is Empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(txtSquareFeet.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter the Square Feet", "Square Feet Is Empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(txtNoOfBedRooms.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter the Number Of Bed Rooms", "Number Of Bed Rooms Is Empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		house = new House(Long.parseLong(txtLotNumber.getText().trim()),
				new Owner(txtFirsName.getText().trim(), txtLastName.getText()),
				Integer.parseInt(txtNoOfBedRooms.getText().trim()),
				Double.parseDouble(txtSquareFeet.getText().trim()),
				new BigDecimal(txtPrice.getText().trim()));
		if(houselist.contains(house)) {
			JOptionPane.showMessageDialog(null, "This details already exists", "Duplicate Data", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		houselist.add(house);
		JOptionPane.showMessageDialog(null, "The details were saved successfully", "Successfully Saved", JOptionPane.INFORMATION_MESSAGE);
		clearFields();
	}
	
	void clear() {
		clearFields();
		JOptionPane.showMessageDialog(null, "All fields were cleared successfully!", "Successfully Cleared", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void clearFields() {
		for (Component C : frame.getContentPane().getComponents()) {
		    if (C instanceof JTextField){
		        ((JTextField)C).setText("");
		    }
		}
		house = new House();
		txtLotNumber.requestFocus();
	}
	
	void deleteHouse() {
		findHouse();
		if(house != null) {
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want delete details realted to lot number : " + txtLotNumber.getText(), "Confirm Before Delete", JOptionPane.YES_NO_OPTION) == 0) {
				houselist.remove(house);
				clearFields();
			}
		}
	}
	
	void findHouse() {
		if(txtLotNumber.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter a Lot Number", "Lot Number Is Empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		house = houselist.binarySearch(Integer.parseInt(txtLotNumber.getText().trim()));
		if(house == null) {
			JOptionPane.showMessageDialog(null, "No match was found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			txtLotNumber.setText(house.getLotnumber()+"");
			txtFirsName.setText(house.getOwner().getFirstname());
			txtLastName.setText(house.getOwner().getLastname());
			txtPrice.setText(house.getPrice()+"");
			txtSquareFeet.setText(house.getSquareFeet()+"");
			txtNoOfBedRooms.setText(house.getNobedRooms()+"");
		}
	}
	
	void getHouseListFromFile(){
		houselist = new HouseFile().getDataset() == null ? new DoublyLinkList<House>() : new HouseFile().getDataset();
	}
	
	void getNextHouse() {
		
		findHouse();
		if(house == null) {
			return;
		}
		
		if(!houselist.contains(house)) {
			JOptionPane.showMessageDialog(null, "The details you entered does not exist!", "Cannot Obtain Next Record", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		try {
			house = houselist.get(houselist.indexOf(house) + 1);
			txtLotNumber.setText(house.getLotnumber()+"");
			txtFirsName.setText(house.getOwner().getFirstname());
			txtLastName.setText(house.getOwner().getLastname());
			txtPrice.setText(house.getPrice()+"");
			txtSquareFeet.setText(house.getSquareFeet()+"");
			txtNoOfBedRooms.setText(house.getNobedRooms()+"");
		}
		catch(IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "There is no next record to show!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	void resetHouses() {
		new HouseFile().deleteDataset();
		clearFields();
		houselist = new DoublyLinkList<House>();
		txtLotNumber.requestFocus();
		JOptionPane.showMessageDialog(null, "All the data was reset successfully!", "Successfully Reset", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void writeHouseListToFile(){
		new HouseFile().writeDataset(houselist);
	}
	
	

	/**
	 * Create the application.
	 */
	public RealEstate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				writeHouseListToFile();
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				getHouseListFromFile();
			}
		});
		frame.setBounds(100, 100, 478, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtLotNumber = new JTextField();
		txtLotNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vChar = arg0.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                	arg0.consume();
                }
			}
		});
		txtLotNumber.setBounds(183, 21, 253, 20);
		frame.getContentPane().add(txtLotNumber);
		txtLotNumber.setColumns(10);
		
		txtFirsName = new JTextField();
		txtFirsName.setColumns(10);
		txtFirsName.setBounds(183, 52, 253, 20);
		frame.getContentPane().add(txtFirsName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(183, 83, 253, 20);
		frame.getContentPane().add(txtLastName);
		
		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vChar = arg0.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE
                        || (vChar == KeyEvent.VK_PERIOD)))) {
                	arg0.consume();
                }
			}
		});
		txtPrice.setColumns(10);
		txtPrice.setBounds(183, 114, 253, 20);
		frame.getContentPane().add(txtPrice);
		
		txtSquareFeet = new JTextField();
		txtSquareFeet.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSquareFeet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vChar = arg0.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE
                        || (vChar == KeyEvent.VK_PERIOD)))) {
                	arg0.consume();
                }
			}
		});
		txtSquareFeet.setColumns(10);
		txtSquareFeet.setBounds(183, 145, 253, 20);
		frame.getContentPane().add(txtSquareFeet);
		
		txtNoOfBedRooms = new JTextField();
		txtNoOfBedRooms.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNoOfBedRooms.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vChar = arg0.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                	arg0.consume();
                }
			}
		});
		txtNoOfBedRooms.setColumns(10);
		txtNoOfBedRooms.setBounds(183, 176, 253, 20);
		frame.getContentPane().add(txtNoOfBedRooms);
		
		JLabel lblNewLabel = new JLabel("Lot Number");
		lblNewLabel.setBounds(30, 27, 151, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(30, 58, 151, 14);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(30, 89, 151, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(30, 120, 151, 14);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblSquareFeet = new JLabel("Square Feet");
		lblSquareFeet.setBounds(30, 151, 151, 14);
		frame.getContentPane().add(lblSquareFeet);
		
		JLabel lblNumberOfBedrooms = new JLabel("Number Of Bedrooms");
		lblNumberOfBedrooms.setBounds(30, 182, 151, 14);
		frame.getContentPane().add(lblNumberOfBedrooms);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetHouses();
			}
		});
		btnReset.setBounds(30, 226, 175, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getNextHouse();
			}
		});
		btnNext.setBounds(261, 226, 175, 23);
		frame.getContentPane().add(btnNext);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addHouse();
			}
		});
		btnAdd.setBounds(30, 260, 175, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteHouse();
			}
		});
		btnDelete.setBounds(261, 260, 175, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnClear.setBounds(30, 294, 175, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findHouse();
			}
		});
		btnFind.setBounds(261, 294, 175, 23);
		frame.getContentPane().add(btnFind);
	}
}
