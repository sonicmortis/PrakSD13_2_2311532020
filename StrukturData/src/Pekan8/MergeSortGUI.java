package Pekan8;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MergeSortGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;

    // Merge Sort specific variables
    private Queue<int[]> mergeQueue = new LinkedList<>();
    private int[] temp;
    private int left, right, mid;
    private boolean isMerging = false;
    private boolean copying = false;
    private int k = 0;
    private int stepCount = 1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MergeSortGUI frame = new MergeSortGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MergeSortGUI() {
        setTitle("Merge Sort Langkah per Langkah");
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
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka yang dipisahkan dengan koma!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Initialize merge sort
        mergeQueue.clear();
        generateMergeSteps(0, array.length - 1);
        isMerging = true;
        copying = false;
        stepCount = 1;
        stepButton.setEnabled(true);
        stepArea.setText("");
        panelArray.removeAll();

        labelArray = new JLabel[array.length];
        for (int i = 0; i < array.length; i++) {
            labelArray[i] = new JLabel(String.valueOf(array[i]));
            labelArray[i].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[i].setOpaque(true);
            labelArray[i].setBackground(Color.WHITE);
            labelArray[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelArray[i].setPreferredSize(new Dimension(50, 50));
            labelArray[i].setHorizontalAlignment(SwingConstants.CENTER);
            panelArray.add(labelArray[i]);
        }
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.append("Langkah " + stepCount++ + ": Mulai Merge Sort\n");
    }

    private void generateMergeSteps(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            generateMergeSteps(l, m);
            generateMergeSteps(m + 1, r);
            mergeQueue.add(new int[]{l, m, r});
        }
    }

    private void performStep() {
        resetHighlights();
        
        if (!isMerging) {
            return;
        }

        if (!copying && !mergeQueue.isEmpty()) {
            int[] range = mergeQueue.poll();
            left = range[0];
            mid = range[1];
            right = range[2];
            temp = new int[right - left + 1];
            k = 0;
            int i = left;
            int j = mid + 1;

            stepArea.append("Langkah " + stepCount++ + ": Merge data dari " + left + " ke " + right + "\n");
            
            // Highlight the current range being merged
            for (int x = left; x <= right; x++) {
                labelArray[x].setBackground(Color.YELLOW);
            }
            
            // Start the merging process
            while (i <= mid && j <= right) {
                if (array[i] <= array[j]) {
                    temp[k++] = array[i++];
                    labelArray[i-1].setBackground(Color.CYAN);
                    stepArea.append("Langkah " + stepCount++ + ": Salin elemen kiri " + temp[k-1] + "\n");
                    return;
                } else {
                    temp[k++] = array[j++];
                    labelArray[j-1].setBackground(Color.CYAN);
                    stepArea.append("Langkah " + stepCount++ + ": Salin elemen kanan " + temp[k-1] + "\n");
                    return;
                }
            }

            // Copy remaining elements
            while (i <= mid) {
                temp[k++] = array[i++];
                labelArray[i-1].setBackground(Color.CYAN);
                stepArea.append("Langkah " + stepCount++ + ": Salin sisa kiri " + temp[k-1] + "\n");
                return;
            }

            while (j <= right) {
                temp[k++] = array[j++];
                labelArray[j-1].setBackground(Color.CYAN);
                stepArea.append("Langkah " + stepCount++ + ": Salin sisa kanan " + temp[k-1] + "\n");
                return;
            }

            // Prepare to copy back to original array
            copying = true;
            k = 0;
            return;
        }

        if (copying && k < temp.length) {
            array[left + k] = temp[k];
            labelArray[left + k].setText(String.valueOf(temp[k]));
            labelArray[left + k].setBackground(Color.GREEN);
            k++;
            stepArea.append("Langkah " + stepCount++ + ": Tempelkan ke array utama\n");
            return;
        }

        if (copying && k == temp.length) {
            copying = false;
            stepArea.append("Langkah " + stepCount++ + ": Selesai merge dari " + left + " ke " + right + "\n\n");
        }

        if (mergeQueue.isEmpty() && !copying) {
            isMerging = false;
            stepArea.append("Langkah " + stepCount++ + ": Merge Sort selesai!\n");
            stepButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Merge Sort selesai!");
            stepArea.append("Hasil akhir: " + arrayToString(array) + "\n\n");
        }
    }

    private void resetHighlights() {
        if (labelArray == null) return;
        for (JLabel label : labelArray) {
            label.setBackground(Color.WHITE);
        }
    }

    private void updateLabels() {
        System.out.println("Muhammad Luthfi Kautsar Rizata");
        System.out.println("2311532020");

        for (int i = 0; i < array.length; i++) {
            labelArray[i].setText(String.valueOf(array[i]));
        }
    }

    private void reset() {
        inputField.setText("");
        panelArray.removeAll();
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.setText("");
        stepButton.setEnabled(false);
        mergeQueue.clear();
        isMerging = false;
        copying = false;
        stepCount = 1;
    }

    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.toString();
    }
}