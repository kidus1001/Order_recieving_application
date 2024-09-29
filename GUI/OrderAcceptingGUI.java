package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import DAO.*;
import entity.Order;

public class OrderAcceptingGUI {	
    JFrame frameHome;
    JPanel panelHome;
    JPanel descriptionBox;

    JButton viewOrders;
    JButton newOrder;
    
    JPanel tablePanel;
    DefaultTableModel model;
    JTable orderListTable;
    
    JPanel inputPanel;
    
    JComboBox <String> foodItem; 
    JTextField quantity;
    JTextArea description;
    JLabel foodItemLabel, quantityLabel;
    
    JButton addButton;
    JButton deleteButton;

    public OrderAcceptingGUI() {
        frameHome = new JFrame();

        frameHome.setBackground(new Color(255, 51, 204));
        panelHome = new JPanel();

        viewOrders = new JButton("Orders' List");
        newOrder = new JButton("New Order");

        tablePanel = new JPanel(new BorderLayout());
        
        model = new DefaultTableModel();
        
        Object[] header = {"Food Item", "Quantity", "Description", "Order Time"};
        model.setColumnIdentifiers(header);
        orderListTable = new JTable(model);
        
        tablePanel.add(new JScrollPane(orderListTable), BorderLayout.CENTER);
        
        newOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputPanel = new JPanel();
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

                foodItemLabel = new JLabel("Food Item");
                quantityLabel = new JLabel("Quantity");
                
                String [] foodItemArray = new OrderMenuDAO ().getOrder();             
                foodItem = new JComboBox <>(foodItemArray);
                quantity = new JTextField(20);
                description = new JTextArea(5, 20);

                JPanel a = new JPanel(new FlowLayout());
                a.add(foodItemLabel);
                a.add(foodItem);
                inputPanel.add(a);

                inputPanel.add(Box.createRigidArea(new Dimension(0,5)));
                
                JPanel b = new JPanel(new FlowLayout());
                b.add(quantityLabel);
                b.add(quantity);
                inputPanel.add(b);

                
                inputPanel.add(Box.createRigidArea(new Dimension(0,5)));
                
                inputPanel.add(Box.createRigidArea(new Dimension(0,12)));
                
                description.setBorder(BorderFactory.createTitledBorder("Description"));
                descriptionBox = new JPanel(new GridBagLayout());
                inputPanel.add(description);

                addButton = new JButton("Add Order");
                //top - 0x090979
                //middle - 0x7e7E8c
                addButton.addActionListener(new ActionListener() {
                	
                    public void actionPerformed(ActionEvent e) {
                    	if (foodItem.getSelectedItem() == null || quantity.getText().isEmpty()) {
                    		JOptionPane.showMessageDialog(frameHome, "Fill the fields correctly");
                    	}
                    	else {
                    		boolean isTrue = false;
                    		Order order = null;
                    		try {                    		                    
                    			order = new Order(String.valueOf(foodItem.getSelectedItem()), Integer.parseInt(quantity.getText()), new java.util.Date().toString(), description.getText());
                    			isTrue = true;
                    		}
                    		catch (NumberFormatException ex) {
                    			JOptionPane.showMessageDialog(frameHome, "Only numeric numbers are accepted!");
                    		}
                    		finally {
                    			if (isTrue == true) {
                    				model.addRow(new Object[]{foodItem.getSelectedItem(), quantity.getText(), description.getText(), new java.util.Date().toString()});
                    			}		
                    		}
                    		
	                        new OrderDAO().addOrder(order);
	                        foodItem.setSelectedIndex(-1);
	                        quantity.setText("");
	                        description.setText("");  
                     	}
                    }
                    	
                });
                
                viewOrders.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	frameHome.remove(inputPanel);
                    	
                        frameHome.add(tablePanel, BorderLayout.CENTER);
                        frameHome.revalidate();
                        frameHome.repaint();
                    }
                });
                
                deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new ActionListener () {
                	public void actionPerformed(ActionEvent e) {
                		if (orderListTable.getSelectedRow() >= 0) {
                			model.removeRow(orderListTable.getSelectedRow());
                		}
                		else {
                			if (orderListTable.getRowCount() == 0) {
                				JOptionPane.showMessageDialog(frameHome, "The table is empty!");
                			}
                		}
                	}
                });
                
                JPanel grid = new JPanel (new GridLayout(1,2));
                grid.add(addButton);
                grid.add(deleteButton);
                grid.setPreferredSize(new Dimension(20, 50));
                inputPanel.add(grid);
                
                frameHome.add(inputPanel, BorderLayout.WEST);
                frameHome.revalidate();
                frameHome.repaint();
            }
        });

        panelHome.add(viewOrders);
        panelHome.add(newOrder);
        
        panelHome.setBackground(new Color(0x090979));

        frameHome.add(panelHome, BorderLayout.NORTH);
        frameHome.setSize(800,600);
        frameHome.setLocationRelativeTo(null);
        frameHome.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.setTitle("Work Space");
        frameHome.setVisible(true);
    }
    
	public static void main (String [] args) {
		new OrderAcceptingGUI();
	}
}