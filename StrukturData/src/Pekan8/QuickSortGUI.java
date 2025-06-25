package Pekan8;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuickSortGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;

    private Stack<int[]> stack = new Stack<>();  //Nama: M. Luthfi Kautsar
    private int pivotIndex = -1;                 //NIM: 2311532020
    private int i = -1, j = -1;
    private boolean partitioning = false;
    private boolean sorting = false;      
    private int stepCount = 1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuickSortGUI frame = new QuickSortGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public QuickSortGUI() {
        setTitle("Quick Sort Langkah per Langkah");
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

        stack.clear();
        stack.push(new int[]{0, array.length - 1});
        sorting = true;
        partitioning = false;
        stepCount = 1;
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
        stepArea.append("Langkah " + stepCount++ + ": Mulai Quick Sort\n");
    }

    private void performStep() {
        resetHighlights();
        
        if (!sorting || stack.isEmpty()) {
            if (stack.isEmpty() && !partitioning) {
                sorting = false;
                stepButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Quick Sort selesai!");
                stepArea.append("Hasil akhir: " + arrayToString(array) + "\n\n");
            }
            return;
        }
        if (!partitioning) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];
            
            if (low < high) {
                pivotIndex = high;
                i = low - 1;
                j = low;
                partitioning = true;
                
                highlightPivot(pivotIndex);
                stepArea.append("Langkah " + stepCount++ + ": Partisi dari " + low + " ke " + high + 
                              " dengan pivot " + array[pivotIndex] + "\n");
                return;
            }
        } else {
            if (j < pivotIndex) {
                highlightCompare(j, pivotIndex);
                
                if (array[j] <= array[pivotIndex]) {
                    i++;
                    swap(i, j);
                    labelArray[i].setBackground(Color.GREEN);
                    labelArray[j].setBackground(Color.CYAN);
                    stepArea.append("Langkah " + stepCount++ + ": Tukar " + array[i] + " dengan " + array[j] + "\n");
                } else {
                    stepArea.append("Langkah " + stepCount++ + ": " + array[j] + " <= " + array[pivotIndex] + 
                                  "? Tidak, lanjutkan\n");
                }
                j++;
                return;
            } else {
                swap(i + 1, pivotIndex);
                pivotIndex = i + 1;
                labelArray[pivotIndex].setBackground(Color.YELLOW);
                stepArea.append("Langkah " + stepCount++ + ": Letakkan pivot di posisi " + pivotIndex + "\n");
                
                stack.push(new int[]{pivotIndex + 1, pivotIndex == -1 ? pivotIndex : array.length - 1});
                stack.push(new int[]{stack.peek()[0] - pivotIndex - 1, pivotIndex - 1});
                partitioning = false;
                
                stepArea.append("Langkah " + stepCount++ + ": Partisi selesai. Pivot sekarang di " + pivotIndex + "\n\n");
                return;
            }
        }
    }

    private void highlightPivot(int index) {
        labelArray[index].setBackground(Color.YELLOW);
    }

    private void highlightCompare(int jIndex, int pivotIndex) {
        labelArray[jIndex].setBackground(Color.CYAN);
        labelArray[pivotIndex].setBackground(Color.YELLOW);
    }

    private void resetHighlights() {
        for (JLabel label : labelArray) {
            label.setBackground(Color.WHITE);
        }
    }

    private void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        updateLabels();
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
        stack.clear();
        sorting = false;
        partitioning = false;
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