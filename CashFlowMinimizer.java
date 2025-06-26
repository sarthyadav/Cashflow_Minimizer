import java.util.*;

public class CashFlowMinimizer {

    static class Edge {
        String from;
        String to;
        int amount;

        Edge(String from, String to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }
    }

    static Map<String, List<Edge>> graph = new HashMap<>();
    static Map<String, Integer> netAmount = new HashMap<>();
    static List<String> people = new ArrayList<>();
    static Stack<Edge> transactionHistory = new Stack<>();
    static Stack<Edge> undoStack = new Stack<>();
    static Stack<Edge> redoStack = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of people: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter names of people:");
        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            graph.put(name, new ArrayList<>());
            netAmount.put(name, 0);
            people.add(name);
        }

        System.out.println("Enter transactions in format 'from to amount'. Type 'done' to stop. Type 'undo' or 'redo' to manage changes:");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("done")){
                break;

            } 

            if (input.equalsIgnoreCase("undo")) {
                if (!undoStack.isEmpty()) {
                    Edge last = undoStack.pop();
                    graph.get(last.from).removeIf(e -> e.to.equals(last.to) && e.amount == last.amount);
                    netAmount.put(last.from, netAmount.get(last.from) + last.amount);
                    netAmount.put(last.to, netAmount.get(last.to) - last.amount);
                    redoStack.push(last);
                    transactionHistory.pop();
                    System.out.println("Undid transaction: " + last.from + " -> " + last.to + " : Rs." + last.amount);
                } else {
                    System.out.println("Nothing to undo.");
                }
                continue;
            }

            if (input.equalsIgnoreCase("redo")) {
                if (!redoStack.isEmpty()) {
                    Edge last = redoStack.pop();
                    graph.get(last.from).add(last);
                    netAmount.put(last.from, netAmount.get(last.from) - last.amount);
                    netAmount.put(last.to, netAmount.get(last.to) + last.amount);
                    undoStack.push(last);
                    transactionHistory.push(last);
                    System.out.println("Redid transaction: " + last.from + " -> " + last.to + " : Rs." + last.amount);
                } else {
                    System.out.println("Nothing to redo.");
                }
                continue;
            }

            String[] parts = input.split(" ");
            if (parts.length != 3 || !graph.containsKey(parts[0]) || !graph.containsKey(parts[1])) {
                System.out.println("Invalid input format or unknown name. Try again.");
                continue;
            }

            String from = parts[0];
            String to = parts[1];
            int amount = Integer.parseInt(parts[2]);

            Edge e = new Edge(from, to, amount);
            graph.get(from).add(e);
            netAmount.put(from, netAmount.get(from) - amount);
            netAmount.put(to, netAmount.get(to) + amount);
            transactionHistory.push(e);
            undoStack.push(e);
            redoStack.clear();
        }

        System.out.println("\nTransaction History:");
        for (Edge e : transactionHistory) {
            System.out.println(e.from + " -> " + e.to + " : Rs." + e.amount);
        }

        minimizeCashFlow();
        sc.close();
    }

    static void minimizeCashFlow() {
        PriorityQueue<String> creditors = new PriorityQueue<>((a, b) -> netAmount.get(b) - netAmount.get(a));
        PriorityQueue<String> debtors = new PriorityQueue<>((a, b) -> netAmount.get(a) - netAmount.get(b));

        for (String person : people) {
            int amt = netAmount.get(person);
            if (amt > 0) creditors.offer(person);
            else if (amt < 0) debtors.offer(person);
        }

        System.out.println("\nMinimized Transactions:");

        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            String creditor = creditors.poll();
            String debtor = debtors.poll();

            int settleAmount = Math.min(netAmount.get(creditor), -netAmount.get(debtor));
            netAmount.put(creditor, netAmount.get(creditor) - settleAmount);
            netAmount.put(debtor, netAmount.get(debtor) + settleAmount);

            System.out.println(debtor + " pays Rs." + settleAmount + " to " + creditor);

            if (netAmount.get(creditor) > 0) creditors.offer(creditor);
            if (netAmount.get(debtor) < 0) debtors.offer(debtor);
        }
    }
}
