
# Cash Flow Minimizer 💰

This Java project minimizes the number of transactions between friends by calculating net cash flow using a graph + greedy algorithm approach.

### Features:
- Add multiple users and transactions
- Undo/Redo functionality
- Minimize total settlements using heaps

### How to Run:
Compile using `javac CashFlowMinimizer.java`
Run with `java CashFlowMinimizer`

### Sample Input:
Enter number of people: 
3
Enter names of people:
Alice
Bob
Charlie
Enter transactions in format 'from to amount'. Type 'done' to stop. Type 'undo' or 'redo' to manage changes:
Alice Bob 100
Bob Charlie 50
Charlie Alice 30
undo
redo
done

### Sample Output:
Undid transaction: Charlie -> Alice : Rs.30
Redid transaction: Charlie -> Alice : Rs.30

Transaction History:
Alice -> Bob : Rs.100
Bob -> Charlie : Rs.50
Charlie -> Alice : Rs.30

Minimized Transactions:
Alice pays Rs.20 to Bob

