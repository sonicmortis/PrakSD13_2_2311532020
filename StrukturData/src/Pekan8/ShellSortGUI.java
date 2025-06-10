package Pekan8;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShellSortGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;

    private int i = 0, j = 0, gap = 0;
    private boolean sorting = false;
    private boolean isMapping = false;
    private int temp;
    private int stepCount = 1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ShellSortGUI frame = new ShellSortGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ShellSortGUI() {
        setTitle("Shell Sort Langkah per Langkah");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(30);
        setButton = new JButton("Set Array");
        inputPanel.add(new JLabel("Masukkan angka (pisahkan dengan koma): "));
        inputPanel.add(inputField);
        inputPanel.add(setButton);

        panelArray = new JPanel();
        panelArray.setLayout(new FlowLayout());

        JPanel controlPanel = new JPanel();
        stepButton = new JButton("Langkah Selanjutnya");
        resetButton = new JButton("Reset");
        controlPanel.add(stepButton);
        controlPanel.add(resetButton);

        stepArea = new JTextArea(8, 60);
        stepArea.setEditable(false);
        stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(stepArea);

        add(inputPanel, BorderLayout.NORTH);
        add(panelArray, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        setButton.addActionListener(e -> setArrayFromInput());
        stepButton.addActionListener(e -> performStep());
        resetButton.addActionListener(e -> reset());
    }

    private void setArrayFromInput() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;

        String[] parts = text.split(",");
        array = new int[parts.length];
        try {
            for (int k = 0; k < parts.length; k++) {
                array[k] = Integer.parseInt(parts[k].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka yang dipisahkan dengan koma!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        i = 0;
        j = 0;
        gap = array.length / 2;
        stepCount = 1;
        sorting = true;
        isMapping = false;
        stepButton.setEnabled(true);
        stepArea.setText("");
        panelArray.removeAll();

        labelArray = new JLabel[array.length];
        for (int k = 0; k < array.length; k++) {
            labelArray[k] = new JLabel(String.valueOf(array[k]));
            labelArray[k].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[k].setOpaque(true);
            labelArray[k].setBackground(Color.WHITE);
            labelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelArray[k].setPreferredSize(new Dimension(50, 50));
            labelArray[k].setHorizontalAlignment(SwingConstants.CENTER);
            panelArray.add(labelArray[k]);
        }
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.append("Langkah " + stepCount++ + ": Mulai Shell Sort dengan gap awal " + gap + "\n\n");
    }

    private void performStep() {
        resetHighlights();
        if (!sorting || gap == 0) {
            stepArea.append("Sorting selesai!\n");
            stepButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Sorting selesai!");
            stepArea.append("Hasil akhir: " + arrayToString(array) + "\n\n");
            return;
        }

        if (i < array.length) {
            if (!isMapping) {
                temp = array[i];
                j = i;
                isMapping = true;
                labelArray[i].setBackground(Color.YELLOW);
                stepArea.append("Langkah " + stepCount++ + ": Memilih elemen " + temp + " pada posisi " + i + "\n");
                return;
            }

            if (j >= gap && array[j - gap] > temp) {
                array[j] = array[j - gap];
                labelArray[j].setBackground(Color.GREEN);
                labelArray[j - gap].setBackground(Color.CYAN);
                updateLabels();
                stepArea.append("Langkah " + stepCount++ + ": Geser elemen " + array[j] + " ke kanan\n");
                j -= gap;
                return;
            } else {
                array[j] = temp;
                updateLabels();
                stepArea.append("Langkah " + stepCount++ + ": Tempatkan " + temp + " di posisi " + j + "\n");
                i++;
                isMapping = false;
            }
        } else {
            gap /= 2;
            i = gap;
            isMapping = false;
            stepArea.append("Langkah " + stepCount++ + ": Kurangi gap menjadi " + gap + "\n\n");
        }
    }

    private void resetHighlights() {
        for (JLabel label : labelArray) {
            label.setBackground(Color.WHITE);
        }
    }

    private void updateLabels() {
        System.out.println("Muhammad Luthfi Kautsar Rizata");
        System.out.println("2311532020");

        for (int k = 0; k < array.length; k++) {
            labelArray[k].setText(String.valueOf(array[k]));
        }
    }

    private void reset() {
        inputField.setText("");
        panelArray.removeAll();
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.setText("");
        stepButton.setEnabled(false);
        sorting = false;
        i = 0;
        j = 0;
        gap = 0;
        isMapping = false;
        stepCount = 1;
    }

    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < arr.length; k++) {
            sb.append(arr[k]);
            if (k < arr.length - 1) sb.append(", ");
        }
        return sb.toString();
    }
}