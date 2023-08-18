import java.io.*;
import java.util.*;

public class Toy {
    private int id;
    private String name;
    private int frequency;
    private static int total_frequency = 0;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
        total_frequency += frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getTotal_frequency() {
        return total_frequency;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("toys.txt"));
        List<Integer> ids = new ArrayList();
        List<String> names = new ArrayList();
        List<Integer> frequencies = new ArrayList();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(";");
            ids.add(Integer.parseInt(tokens[0]));
            names.add(tokens[1]);
            frequencies.add(Integer.parseInt(tokens[2]));
        }

        PriorityQueue<Toy> toysQueue = new PriorityQueue(frequencies.size(),
                Comparator.comparingDouble(Toy::getFrequency));
        for (int i = 0; i < frequencies.size(); i++) {
            Toy toy = new Toy(ids.get(i), names.get(i), frequencies.get(i));
            toysQueue.add(toy);
        }

        Queue<Toy> queue = new LinkedList();
        queue.addAll(toysQueue);

        writeToFile("result.txt", queue);
    }

    public static void writeToFile(String filename, Queue<Toy> queue) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rnd = random.nextInt(Toy.total_frequency) + 1;
            System.out.println(rnd);
            int temp = 0;
            for (Toy t : queue) {
                temp += t.getFrequency();
                if (rnd <= temp) {
                    bw.write(t.getId() + " " + t.getName() + " " + t.getFrequency() + "\n");
                    break;
                }

            }

        }
        bw.close();
    }

}
