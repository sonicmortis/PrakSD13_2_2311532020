package Tugas;

import java.util.*;

public class TugasSearchingGraf {

    static Map<String, List<String>> graph = new HashMap<>();
    static List<String> visited = new ArrayList<>();

    public static void main(String[] args) {
    
        // Identitas Mahasiswa
        String nama = "Muhammad Luthfi Kautsar Rizata";
        String nim = "2311532020"; 
        char digitTerakhir = nim.charAt(nim.length() - 1);
        boolean isGenap = Character.getNumericValue(digitTerakhir) % 2 == 0;

        // Setup graph (undirected)
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D"));
        graph.put("C", Arrays.asList("A", "D", "E"));
        graph.put("D", Arrays.asList("B", "C", "F"));
        graph.put("E", Arrays.asList("C", "F", "H"));
        graph.put("F", Arrays.asList("D", "E", "G"));
        graph.put("G", Arrays.asList("F", "H"));
        graph.put("H", Arrays.asList("E", "G"));

        String startNode = "A";
        String goalNode = "G";

        // Output identitas
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Node awal: " + startNode);
        System.out.println("Node tujuan: " + goalNode);
        System.out.println("Algoritma: " + (isGenap ? "BFS" : "DFS"));

        if (isGenap) {
            bfs(startNode, goalNode);
        } else {
            dfs(startNode, goalNode);
        }
    }

    // Implementasi DFS
    public static void dfs(String current, String goal) {
        visited.clear();
        List<String> path = new ArrayList<>();
        if (dfsRecursive(current, goal, path)) {
            printPath(path);
        } else {
            System.out.println("Tujuan tidak ditemukan.");
        }
    }

    private static boolean dfsRecursive(String node, String goal, List<String> path) {
        visited.add(node);
        path.add(node);
        System.out.println("Langkah " + visited.size() + ": Kunjungi " + node);
        if (node.equals(goal)) return true;

        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfsRecursive(neighbor, goal, path)) return true;
            }
        }
        path.remove(path.size() - 1); 
        return false;
    }

    // Implementasi BFS
    public static void bfs(String start, String goal) {
        visited.clear();
        Map<String, String> parent = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);
        parent.put(start, null);
        int step = 1;

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.println("Langkah " + step + ": Kunjungi " + current);
            step++;

            if (current.equals(goal)) {
                break;
            }

            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }

        if (!parent.containsKey(goal)) {
            System.out.println("Tujuan tidak ditemukan.");
            return;
        }

        List<String> path = new ArrayList<>();
        for (String at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        printPath(path);
    }

    // Cetak rute akhir
    public static void printPath(List<String> path) {
        System.out.println("Tujuan " + path.get(path.size() - 1) + " ditemukan");
        System.out.print("Rute: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i != path.size() - 1) System.out.print(" → ");
        }
        System.out.println();
    }
}
