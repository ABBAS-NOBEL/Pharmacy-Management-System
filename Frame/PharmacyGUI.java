package Frame;
import Entity.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class PharmacyGUI extends JFrame implements ActionListener, MouseListener {
    // Main CardLayout for single page
    private JPanel mainPanel;
    private CardLayout cardLayout;
	
	//images
	private ImageIcon logo;
	//private ImageIcon panelbg;
    
    // Login Panel
    private JPanel loginPanel;
    private JLabel titleLabel, emailLabel, passwordLabel,logoLabel,panelbgLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn, exitBtn;
    
    // Register Panel
    private JPanel registerPanel;
    private JLabel regTitleLabel, nameLabel, regEmailLabel, regPassLabel, phoneLabel,genderLabel;
    private JTextField regNameField, regEmailField, regPhoneField;
    private JPasswordField regPassField, regConfirmPassField;
    private JButton submitBtn, backBtn, regExitBtn;
    
    // Medicine 
    private JPanel medicinePanel;
    private JLabel medTitleLabel, categoryLabel;
	private JComboBox<String> categoryCombo;
    private JList<String> medicineList; 
    private DefaultListModel<String> listModel;
    private JButton addToCartBtn, viewCartBtn, logoutBtn, deleteAccountBtn, backToLoginBtn, exitMedBtn;
    private JSpinner quantitySpinner;
    
    // Cart Panel
    private JPanel cartPanel;
    private JLabel cartTitleLabel;
    private JTextArea cartArea;
    private JLabel totalLabel;
    private JButton checkoutBtn, removeBtn, backToMedBtn, exitCartBtn;
    
    // Data
    private ArrayList<Medicine> medicines;
    private ArrayList<CartItem> cart;
    private User currentUser;
    
    private Font f1, f2;
    private Color c1 = new Color(0, 150, 136);
    
    public PharmacyGUI() {
        super("Pharmacy Management System");
        super.setBounds(250, 100, 800, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize data
        medicines = new ArrayList<>();
        cart = new ArrayList<>();
        
        //fonts
        f1 = new Font("Cambria", Font.BOLD, 24);
        f2 = new Font("Cambria", Font.PLAIN, 16);
        
        //Cardlayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // panels
        createLoginPanel();
        createRegisterPanel();
        createMedicinePanel();
        createCartPanel();
        
        //showing medicines
        initializeMedicines();
        
        // Adding panels to Cardlayout
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(registerPanel, "REGISTER");
        mainPanel.add(medicinePanel, "MEDICINE");
        mainPanel.add(cartPanel, "CART");
        
        super.add(mainPanel);
    }
    
    private void createLoginPanel() {
        loginPanel = new JPanel(null);
        loginPanel.setBackground(new Color(240, 240, 240));
		//adding logo
		logo = new ImageIcon("C:/Users/User/Downloads/Documents/2nd semmester/Gui Pharmacy (Deep 3)/Pictures/Pharmacy & Beyond (deep 3.2).png");
		logoLabel=new JLabel(logo);
		logoLabel.setBounds(5, -30, 500, 500); 
		loginPanel.add(logoLabel);
	
	  /*  //adding pnael background
		panelbg = new ImageIcon("C:/Users/User/Downloads/Documents/2nd semmester/Gui Pharmacy (Deep 3)/Pictures/pexels-shvetsa-3683045.jpg");
		panelbgLabel=new JLabel(panelbg);
		panelbgLabel.setBounds(0, 0, 800, 600); 
		panelbgLabel.add(logoLabel);*/
		
        titleLabel = new JLabel("Login");
        titleLabel.setBounds(300, 50, 400, 40);
        titleLabel.setFont(f1);
        titleLabel.setForeground(c1);
        loginPanel.add(titleLabel);
        
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 120, 100, 30);
        emailLabel.setFont(f2);
        loginPanel.add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(300, 120, 300, 30);
        emailField.setFont(f2);
        loginPanel.add(emailField);
        
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 170, 100, 30);
        passwordLabel.setFont(f2);
        loginPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 170, 300, 30);
        passwordField.setFont(f2);
        loginPanel.add(passwordField);
        
        loginBtn = new JButton("Login");
        loginBtn.setBounds(200, 230, 120, 35);
        loginBtn.setFont(f2);
        loginBtn.setBackground(c1);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(this);
        loginPanel.add(loginBtn);
        
        registerBtn = new JButton("Register");
        registerBtn.setBounds(340, 230, 120, 35);
        registerBtn.setFont(f2);
        registerBtn.setBackground(Color.GRAY);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.addActionListener(this); 
        loginPanel.add(registerBtn);
        
        exitBtn = new JButton("Exit");
        exitBtn.setBounds(590, 490, 120, 35);
        exitBtn.setFont(f2);
        exitBtn.setBackground(Color.RED);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.addActionListener(this);
        loginPanel.add(exitBtn);
    }
    
    private void createRegisterPanel() {
        registerPanel = new JPanel(null);
        registerPanel.setBackground(new Color(240, 240, 240));
       
        regTitleLabel = new JLabel("User Registration");
        regTitleLabel.setBounds(300, 30, 400, 40);
        regTitleLabel.setFont(f1);
        regTitleLabel.setForeground(c1);
        registerPanel.add(regTitleLabel);
		
		logo = new ImageIcon("C:/Users/User/Downloads/Documents/2nd semmester/Gui Pharmacy (Deep 3)/Pictures/Pharmacy & Beyond (deep 3.2).png");
		logoLabel=new JLabel(logo);
		logoLabel.setBounds(5, -30, 500, 500); 
		registerPanel.add(logoLabel);
        
        nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(100, 80, 150, 30);
        nameLabel.setFont(f2);
        registerPanel.add(nameLabel);
        
        regNameField = new JTextField();
        regNameField.setBounds(250, 80, 400, 30);
        regNameField.setFont(f2);
        registerPanel.add(regNameField);
        
        regEmailLabel = new JLabel("Email:");
        regEmailLabel.setBounds(100, 120, 150, 30);
        regEmailLabel.setFont(f2);
        registerPanel.add(regEmailLabel);
        
        regEmailField = new JTextField();
        regEmailField.setBounds(250, 120, 400, 30);
        regEmailField.setFont(f2);
        registerPanel.add(regEmailField);
        
        regPassLabel = new JLabel("Password:");
        regPassLabel.setBounds(100, 160, 150, 30);
        regPassLabel.setFont(f2);
        registerPanel.add(regPassLabel);
        
        regPassField = new JPasswordField();
        regPassField.setBounds(250, 160, 400, 30);
        regPassField.setFont(f2);
        registerPanel.add(regPassField);
        
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(100, 200, 150, 30);
        confirmLabel.setFont(f2);
        registerPanel.add(confirmLabel);
        
        regConfirmPassField = new JPasswordField();
        regConfirmPassField.setBounds(250, 200, 400, 30);
        regConfirmPassField.setFont(f2);
        registerPanel.add(regConfirmPassField);
        
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(100, 240, 150, 30);
        phoneLabel.setFont(f2);
        registerPanel.add(phoneLabel);
        
        regPhoneField = new JTextField();
        regPhoneField.setBounds(250, 240, 400, 30);
        regPhoneField.setFont(f2);
        registerPanel.add(regPhoneField);
         
		//implimenting radiobutton 
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setBounds(100, 280, 150, 30);
		genderLabel.setFont(f2);
		registerPanel.add(genderLabel);
		
		JRadioButton maleBtn = new JRadioButton("Male");
		maleBtn.setBounds(250, 280, 80, 30);
		maleBtn.setFont(f2);
		registerPanel.add(maleBtn);

		JRadioButton femaleBtn = new JRadioButton("Female");
		femaleBtn.setBounds(340, 280, 100, 30);
		femaleBtn.setFont(f2);
		registerPanel.add(femaleBtn);

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleBtn);
		genderGroup.add(femaleBtn);
 
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(200, 400, 120, 35);
        submitBtn.setFont(f2);
        submitBtn.setBackground(new Color(76, 175, 80));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.addActionListener(this);
        registerPanel.add(submitBtn);
        
		backBtn = new JButton("Back");
        backBtn.setBounds(340, 400, 120, 35); 
        backBtn.setFont(f2);
        backBtn.setBackground(c1);
        backBtn.setForeground(Color.WHITE);
		backBtn.addActionListener(this);
		registerPanel.add(backBtn);
		
		regExitBtn = new JButton("Exit");
        regExitBtn.setBounds(590, 490, 120, 35); 
        regExitBtn.setFont(f2);
        regExitBtn.setBackground(Color.RED);
        regExitBtn.setForeground(Color.WHITE);
        regExitBtn.addActionListener(this);
		regExitBtn.addActionListener(this);
        registerPanel.add(regExitBtn);
}

    
    private void createMedicinePanel() {
        medicinePanel = new JPanel(null);
        medicinePanel.setBackground(new Color(240, 240, 240));
        
        medTitleLabel = new JLabel("Medicine Selection");
        medTitleLabel.setBounds(300, 20, 400, 40);
        medTitleLabel.setFont(f1);
        medTitleLabel.setForeground(c1);
        medicinePanel.add(medTitleLabel);
		
		logo = new ImageIcon("C:/Users/User/Downloads/Documents/2nd semmester/Gui Pharmacy (Deep 3)/Pictures/Pharmacy & Beyond (deep 3.2).png");
		logoLabel=new JLabel(logo);
		logoLabel.setBounds(5, -30, 500, 500); 
		medicinePanel.add(logoLabel);
        
        categoryLabel = new JLabel("Select Category:");
        categoryLabel.setBounds(50, 100, 150, 30);
        categoryLabel.setFont(f2);
        medicinePanel.add(categoryLabel);
        
        // Category dropdown
        categoryCombo = new JComboBox<>();
        categoryCombo.setBounds(180, 100, 200, 30);
        categoryCombo.setFont(f2);
        categoryCombo.addItem("All Medicines");
        categoryCombo.addItem("Pain Relief");
        categoryCombo.addItem("Antibiotics");
        categoryCombo.addItem("Vitamins");
        categoryCombo.addItem("First Aid");
        categoryCombo.addActionListener(this);
        medicinePanel.add(categoryCombo);
        
        listModel = new DefaultListModel<>();
        medicineList = new JList<>(listModel);
        medicineList.setFont(f2);
        medicineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(medicineList);
        scrollPane.setBounds(50, 130, 700, 250);
        medicinePanel.add(scrollPane);
        
        // Quantity selection
        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setBounds(50, 390, 100, 30);
        qtyLabel.setFont(f2);
        medicinePanel.add(qtyLabel);
        
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
														//(current, min, max, step)
        quantitySpinner.setBounds(120, 400, 80, 30);
        quantitySpinner.setFont(f2);
        medicinePanel.add(quantitySpinner);
        
        addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setBounds(50, 440, 150, 35);
        addToCartBtn.setFont(f2);
        addToCartBtn.setBackground(c1);
        addToCartBtn.setForeground(Color.WHITE);
        addToCartBtn.addActionListener(this);
        medicinePanel.add(addToCartBtn);
        
        viewCartBtn = new JButton("View Cart");
        viewCartBtn.setBounds(220, 440, 150, 35);
        viewCartBtn.setFont(f2);
        viewCartBtn.setBackground(Color.ORANGE);
        viewCartBtn.setForeground(Color.BLACK);
        viewCartBtn.addActionListener(this);
        medicinePanel.add(viewCartBtn);
        
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(390, 440, 150, 35);
        logoutBtn.setFont(f2);
        logoutBtn.setBackground(Color.RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.addActionListener(this);
        medicinePanel.add(logoutBtn);
        
        deleteAccountBtn = new JButton("Delete Account");
        deleteAccountBtn.setBounds(560, 440, 150, 35);
        deleteAccountBtn.setFont(f2);
        deleteAccountBtn.setBackground(Color.DARK_GRAY);
        deleteAccountBtn.setForeground(Color.WHITE);
        deleteAccountBtn.addActionListener(this);
        medicinePanel.add(deleteAccountBtn);
        
        backToLoginBtn = new JButton("Back");
        backToLoginBtn.setBounds(50, 500, 150, 35);
        backToLoginBtn.setFont(f2);
        backToLoginBtn.setBackground(Color.GRAY);
        backToLoginBtn.setForeground(Color.WHITE);
        backToLoginBtn.addActionListener(this);
        medicinePanel.add(backToLoginBtn);
        
        exitMedBtn = new JButton("Exit");
        exitMedBtn.setBounds(590, 490, 150, 35);
        exitMedBtn.setFont(f2);
        exitMedBtn.setBackground(Color.RED);
        exitMedBtn.setForeground(Color.WHITE);
        exitMedBtn.addActionListener(this);
        medicinePanel.add(exitMedBtn);
        
        // Show all medicines initially
        showAllMedicines();
    }
    
    private void createCartPanel() {
        cartPanel = new JPanel(null);
        cartPanel.setBackground(new Color(240, 240, 240));
        
        cartTitleLabel = new JLabel("Shopping Cart");
        cartTitleLabel.setBounds(300, 00, 400, 40);
        cartTitleLabel.setFont(f1);
        cartTitleLabel.setForeground(c1);
        cartPanel.add(cartTitleLabel);
		
		logo = new ImageIcon("C:/Users/User/Downloads/Documents/2nd semmester/Gui Pharmacy (Deep 3)/Pictures/Pharmacy & Beyond (deep 3.2).png");
		logoLabel=new JLabel(logo);
		logoLabel.setBounds(5, -30, 500, 500); 
		cartPanel.add(logoLabel);
        
        cartArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(cartArea);
        scrollPane.setBounds(50, 100, 700, 270);
        cartArea.setFont(f2);
        cartArea.setEditable(false);
        cartPanel.add(scrollPane);
        
        totalLabel = new JLabel("Total: Taka 0.00");
        totalLabel.setBounds(50, 380, 200, 30);
        totalLabel.setFont(new Font("Cambria", Font.BOLD, 18));
        totalLabel.setForeground(Color.RED);
        cartPanel.add(totalLabel);
        
        removeBtn = new JButton("Remove Item");
        removeBtn.setBounds(50, 420, 150, 35);
        removeBtn.setFont(f2);
        removeBtn.setBackground(Color.RED);
        removeBtn.setForeground(Color.WHITE);
        removeBtn.addActionListener(this);
        cartPanel.add(removeBtn);
        
        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBounds(220, 420, 150, 35);
        checkoutBtn.setFont(f2);
        checkoutBtn.setBackground(new Color(76, 175, 80));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.addActionListener(this);
        cartPanel.add(checkoutBtn);
        
        backToMedBtn = new JButton("Back to Medicines");
        backToMedBtn.setBounds(390, 420, 180, 35);
        backToMedBtn.setFont(f2);
        backToMedBtn.setBackground(c1);
        backToMedBtn.setForeground(Color.WHITE);
        backToMedBtn.addActionListener(this);
        cartPanel.add(backToMedBtn);
        
        exitCartBtn = new JButton("Exit");
        exitCartBtn.setBounds(590, 490, 150, 35);
        exitCartBtn.setFont(f2);
        exitCartBtn.setBackground(Color.RED);
        exitCartBtn.setForeground(Color.WHITE);
		exitCartBtn.addActionListener(this);
        cartPanel.add(exitCartBtn);
    }
    
    private void initializeMedicines() {
        //medicines
        medicines.add(new Medicine("M001", "Paracetamol 500mg", "Pain Relief", 5.99, 100, "Square"));
        medicines.add(new Medicine("M002", "Napa 500 mg", "Pain Relief", 7.49, 75, "Beximco"));
        medicines.add(new Medicine("M003", "Ace 500 mg", "Pain Relief", 3.99, 90, "Square"));
        medicines.add(new Medicine("M004", "Fexo 500 mg", "Antibiotics", 12.99, 50, ""));
        medicines.add(new Medicine("M005", "Azithromycin 500mg", "Antibiotics", 15.99, 40, "Incepta"));
        medicines.add(new Medicine("M006", "Vitamin C 500mg", "Vitamins", 9.99, 120, "Renata"));
        medicines.add(new Medicine("M007", "Vitamin D 1000IU", "Vitamins", 11.99, 80, "Drug International"));
        medicines.add(new Medicine("M008", "Seclo 20 mg", "Gastric", 4.99, 150, "SKF"));
        medicines.add(new Medicine("M009", "Sergel 20mg", "Gastric", 6.99, 70, "Health care"));
        medicines.add(new Medicine("M010", "Multivitamin", "Vitamins", 14.99, 60, "Healthcare"));
		medicines.add(new Medicine("!!", "Stock Out!!", "First Aid", 0.0, 00, " "));
    }
    
    private void showAllMedicines() {
        listModel.clear();
        for (Medicine med : medicines) {
            listModel.addElement(String.format("%s - %s (Taka%.2f, Stock: %d)", 
                med.getId(), med.getName(), med.getPrice(), med.getQuantity()));
        }
    }
    
    private void showMedicinesByCategory() {
        String selectedCategory = (String) categoryCombo.getSelectedItem();
        listModel.clear();
        
        if ("All Medicines".equals(selectedCategory)) {
            showAllMedicines();
            return;
        }
        
        for (Medicine med : medicines) {
            if (med.getCategory().equals(selectedCategory)) {
                listModel.addElement(String.format("%s - %s (Taka%.2f, Stock: %d)", 
                    med.getId(), med.getName(), med.getPrice(), med.getQuantity()));
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginBtn) {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter email and password");
                return;
            }
            
            // Check if user exists in file
            if (User.checkUserExists(email, password)) {
                currentUser = new User("Customer", email, password, ""," ");
                cardLayout.show(mainPanel, "MEDICINE");
                emailField.setText("");
                passwordField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password!");
            }
            
        } else if (ae.getSource() == registerBtn) {
            cardLayout.show(mainPanel, "REGISTER");
            
        } 
		//12:29
		else if (ae.getSource() == backBtn) {
		cardLayout.show(mainPanel, "LOGIN");
	}

		else if (ae.getSource() == submitBtn) {
            String name = regNameField.getText().trim();
            String email = regEmailField.getText().trim();
            String password = new String(regPassField.getPassword()).trim();
            String confirmPass = new String(regConfirmPassField.getPassword()).trim();
            String phone = regPhoneField.getText().trim();
            
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || 
                phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            
            // Password error
            if (password.length() < 7) {
                JOptionPane.showMessageDialog(this, 
                    "Password must be at least 7 characters long!",
                    "Password Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Phone number condition
            if (phone.length() != 11 || !phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, 
                    "Phone number must be exactly 11 digits!",
                    "Phone Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!password.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match");
                return;
            }
            
            // Check if user already exists
            if (User.checkUserExists(email, password)) {
                JOptionPane.showMessageDialog(this, "User already exists!");
                return;
            }
            
            // Create and save user
            User newUser = new User(name, email, password, phone,"");
            newUser.saveToFile();
            
            JOptionPane.showMessageDialog(this, "Registration successful! Please login.");
            cardLayout.show(mainPanel, "LOGIN");
            
            // Clear fields
            regNameField.setText("");
            regEmailField.setText("");
            regPassField.setText("");
            regConfirmPassField.setText("");
            regPhoneField.setText("");
            
        } else if (ae.getSource() == addToCartBtn) {
            int selectedIndex = medicineList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a medicine by clicking on it");
                return;
            }
            
            String selectedText = listModel.getElementAt(selectedIndex);
            String medicineId = selectedText.substring(0, 4); 
            
            Medicine selectedMed = null;
            for (Medicine med : medicines) {
                if (med.getId().equals(medicineId)) {
                    selectedMed = med;
                    break;
                }
            }
            
            if (selectedMed == null) {
                JOptionPane.showMessageDialog(this, "Error: Medicine not found");
                return;
            }
            
            int quantity = (int) quantitySpinner.getValue();
            if (quantity > selectedMed.getQuantity()) {
                JOptionPane.showMessageDialog(this, "Not enough stock available!");
                return;
            }
            
            // Add to cart
            cart.add(new CartItem(selectedMed, quantity));
            JOptionPane.showMessageDialog(this, 
                quantity + " x " + selectedMed.getName() + " added to cart!");
            
        } else if (ae.getSource() == logoutBtn) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", 
                    "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cart.clear();
                currentUser = null;
                cardLayout.show(mainPanel, "LOGIN");
            }
            
        } else if (ae.getSource() == deleteAccountBtn) {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "You are not logged in!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete your account?\nThis action cannot be undone!", 
                    "Delete Account", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                
                JOptionPane.showMessageDialog(this, "Account deletion request recorded.");
                cart.clear();
                currentUser = null;
                cardLayout.show(mainPanel, "LOGIN");
            }
            
        } else if (ae.getSource() == removeBtn) {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cart is empty!");
                return;
            }
            
            String input = JOptionPane.showInputDialog(this, 
                    "Enter item number to remove (1, 2, 3...):");
            if (input == null) return;
            
            try {
                int itemNum = Integer.parseInt(input.trim());
                if (itemNum < 1 || itemNum > cart.size()) {
                    JOptionPane.showMessageDialog(this, "Invalid item number!");
                    return;
                }
                
                CartItem removed = cart.remove(itemNum - 1);
                JOptionPane.showMessageDialog(this, 
                    "Removed: " + removed.getMedicine().getName() + " x" + removed.getQuantity());
                updateCartDisplay();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number");
            }
            
        } else if (ae.getSource() == checkoutBtn) {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cart is empty!");
                return;
            }
            
            double total = 0;
            StringBuilder receipt = new StringBuilder();
            receipt.append("========= PHARMACY RECEIPT =========\n");
            receipt.append("Customer: " + (currentUser != null ? currentUser.getName() : " ") + "\n");
            //receipt.append("------------------------------------\n");
            
            for (CartItem item : cart) {
                double itemTotal = item.getTotalPrice();
                total += itemTotal;
			receipt.append(item.getMedicine().getName() + 
               " Taka" + item.getMedicine().getPrice() + 
               " x " + item.getQuantity() + 
               " = Taka" + itemTotal + "\n");
		}
            //receipt.append("------------------------------------\n");
			receipt.append("Total: "+total+"Taka\n");
            receipt.append("*************************************************\n");
            receipt.append("   Your trusted! Medicine & Beyond.\n");
			receipt.append("*************************************************\n");
            
            // Save receipt to file
            try {
                File file = new File("./Data/receipts.txt");
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                
                FileWriter fwriter = new FileWriter(file, true);
                fwriter.write(receipt.toString() + "\n");
                fwriter.flush();
                fwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            JOptionPane.showMessageDialog(this, receipt.toString(), "Checkout Complete", 
                    JOptionPane.INFORMATION_MESSAGE);
            cart.clear();
            updateCartDisplay();
            cardLayout.show(mainPanel, "MEDICINE");
        }
		
		else if (ae.getSource() == exitBtn) { System.exit(0);
	}

	else if (ae.getSource() == backToLoginBtn) {cardLayout.show(mainPanel, "LOGIN");
	}
	else if (ae.getSource() == exitMedBtn) {System.exit(0);
	}
	else if (ae.getSource() == exitCartBtn) {System.exit(0);
	}
	else if (ae.getSource() == backToMedBtn) {cardLayout.show(mainPanel, "MEDICINE");
	}
	else if (ae.getSource() == viewCartBtn) {updateCartDisplay();
    cardLayout.show(mainPanel, "CART");
	}
	else if (ae.getSource() == categoryCombo) {showMedicinesByCategory();
	}
	else if (ae.getSource() == regExitBtn) {System.exit(0);
}
		
    }
	
    
    private void updateCartDisplay() {
        if (cart.isEmpty()) {
            cartArea.setText("Your cart is empty.");
            totalLabel.setText("Total: Taka 0.00");
            return;
        }
        
        StringBuilder cartText = new StringBuilder();
        double total = 0;
        int itemCount = 1;
        
        cartText.append("Item# | Medicine Name        | Price   | Qty | Subtotal\n");
        cartText.append("-------------------------------------------------------\n");
        
		for (CartItem item : cart) {
		double subtotal = item.getTotalPrice();
		total += subtotal;
		String line = itemCount + ". " + 
        item.getMedicine().getName() + " | " +"Taka" + item.getMedicine().getPrice() + " | " +item.getQuantity() + " | " +"Taka" + subtotal + "\n";cartText.append(line);
		itemCount++;
}
        
        cartArea.setText(cartText.toString());
        totalLabel.setText(String.format("Total:"+ total));
    }
    
    // MouseListener methods
    public void mouseClicked(MouseEvent me) {}
    public void mousePressed(MouseEvent me) {
		/*if(me.getSource() ==logoLabel )
		{
			logoLabel.setText("Medicine & Beyond");
		}*/
	}
    public void mouseReleased(MouseEvent me) {
		/*if(me.getSource() ==logoLabel )
		{
			logoLabel.setText(" ");
		}*/
	}
    public void mouseEntered(MouseEvent me) {
		if(me.getSource() ==loginBtn )
		{
			loginBtn.setBackground(Color.GREEN);
			loginBtn.setForeground(Color.BLACK);
		}
	}
    public void mouseExited(MouseEvent me) {}
}