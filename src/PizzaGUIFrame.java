import javax.swing.*;
import java.awt.*;

public class PizzaGUIFrame extends JFrame {



    JRadioButton thinCrust, regularCrust, deepDishCrust;
    ButtonGroup crustGroup;
    JPanel crustPanel = new JPanel();

    public PizzaGUIFrame() {

        //title stuff
        setTitle("Pizza Order Form");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //crust
        thinCrust = new JRadioButton("Thin");
        regularCrust = new JRadioButton("Regular");
        deepDishCrust = new JRadioButton("Deep-dish");

        crustGroup = new ButtonGroup();
        crustGroup.add(thinCrust);
        crustGroup.add(regularCrust);
        crustGroup.add(deepDishCrust);

        crustPanel.setBorder(BorderFactory.createTitledBorder("Crust"));
        crustPanel.add(thinCrust);
        crustPanel.add(regularCrust);
        crustPanel.add(deepDishCrust);
      //size
        JPanel orderPanel = new JPanel();
String[] sizes = {"Small ($8)", "Medium ($12)", "Large ($16)", "Super ($20)"};
        JComboBox<String> sizeCombo = new JComboBox<>(sizes);
        // JRadioButton smallSize, mediumSize, largeSize;
        //ButtonGroup sizeGroup;
        JPanel sizePanel = new JPanel();
        sizePanel.setBorder(BorderFactory.createTitledBorder("Pizza Size"));
        sizePanel.add(sizeCombo);


        //toppings
        JCheckBox pepperoni = new JCheckBox("Pepperoni");
        JCheckBox mushrooms = new JCheckBox("Mushrooms");
        JCheckBox onions = new JCheckBox("Onions");
        JCheckBox sausage = new JCheckBox("Sausage");
        JCheckBox bacon = new JCheckBox("Bacon");
        JCheckBox extraCheese = new JCheckBox("Extra Cheese");
        JCheckBox blackOlives = new JCheckBox("Black Olives");
        JCheckBox despair = new JCheckBox("Despair");

        JPanel toppingsPanel = new JPanel();
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        toppingsPanel.setLayout(new GridLayout(3, 2)); // Two columns
        toppingsPanel.add(pepperoni);
        toppingsPanel.add(mushrooms);
        toppingsPanel.add(onions);
        toppingsPanel.add(sausage);
        toppingsPanel.add(bacon);
        toppingsPanel.add(extraCheese);
        toppingsPanel.add(blackOlives);
        toppingsPanel.add(despair);

        //order button

        JButton orderButton = new JButton("Order");
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);
        // quit Button Functionality
        quitButton.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                });
        //topping button functionality
        clearButton.addActionListener(e -> {
            thinCrust.setSelected(false);
            regularCrust.setSelected(false);
            deepDishCrust.setSelected(false);
            sizeCombo.setSelectedIndex(0);
            pepperoni.setSelected(false);
            mushrooms.setSelected(false);
            onions.setSelected(false);
            sausage.setSelected(false);
            bacon.setSelected(false);
            extraCheese.setSelected(false);
            blackOlives.setSelected(false);
            despair.setSelected(false);
        });
        JTextArea orderSummaryArea = new JTextArea(20, 40);
        orderSummaryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);

        orderPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        orderPanel.add(scrollPane);
        //order button functionality
        orderButton.addActionListener(e -> {
            String crust = "";
            if (thinCrust.isSelected()) crust = "Thin";
            else if (regularCrust.isSelected()) crust = "Regular";
            else if (deepDishCrust.isSelected()) crust = "Deep-dish";

            if (crust.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a crust type.");
                return;
            }
            int sizeIndex = sizeCombo.getSelectedIndex();
            //sizes already declared earlier
           // String[] sizes = {"Small ($8)", "Medium ($12)", "Large ($16)", "Super ($20)"};
            double[] prices = {8, 12, 16, 20};
            String sizeLabel = sizes[sizeIndex];
            double basePrice = prices[sizeIndex];

        //topping functionality
        StringBuilder toppings = new StringBuilder();
        int toppingCount = 0;
        JCheckBox[] toppingBoxes = {
                pepperoni, mushrooms, onions, sausage, bacon, extraCheese, blackOlives, despair
        };
        for (JCheckBox toppingBox : toppingBoxes) {
            if (toppingBox.isSelected()) {
                toppings.append(toppingBox.getText()).append("\t\t$1.00\n");
                toppingCount++;
            }
        }
        //must get on topping or else
       /* if (toppingCount == 0) {
            JOptionPane.showMessageDialog(null, "Please select at least one topping.");
            return;
        }*/
        //calculate total
        //double total = prices[sizeIndex] + toppingCount;
        //StringBuilder orderSummaryText = new StringBuilder();
        //orderSummaryText.append("Crust: ").append(crust).append("\n");

        //already declared earlier
        // final double[] prices = {8, 12, 16, 20};
        double subTotal = prices[sizeCombo.getSelectedIndex()] + toppingCount;
        double tax = subTotal * 0.07;
        double total = subTotal + tax;

        String receipt = "=========================================\n";
        receipt += crust + " Crust, " + sizeLabel + " Size\t\t$" + String.format("%.2f", basePrice) + "\n";
        receipt += toppings.toString();
        receipt += "\nSub-total:\t\t$" + String.format("%.2f", subTotal) + "\n";
        receipt += "Tax:\t\t\t$" + String.format("%.2f", tax) + "\n";
        receipt += "------------------------------------------------------\n";
        receipt += "Total:\t\t\t$" + String.format("%.2f", total) + "\n";
        receipt += "=========================================";

        orderSummaryArea.setText(receipt);
        });
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 3));
        optionsPanel.add(crustPanel);
        optionsPanel.add(sizePanel);
        optionsPanel.add(toppingsPanel);

        add(optionsPanel, BorderLayout.NORTH);
        add(orderPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //order summary
        /*
        JTextArea orderSummaryArea = new JTextArea(20, 40);
        orderSummaryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);

        orderPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        orderPanel.add(scrollPane);
*/
        setVisible(true);

    }
}
