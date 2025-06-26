
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
X
Y
Z
Enter transactions in format 'from to amount'. Type 'done' to stop. Type 'undo' or 'redo' to manage changes:
X Y 100
Y Z 50
Z X 30
undo
redo
done
### Sample Output:
Undid transaction: Z -> X : Rs.30
Redid transaction: Z -> X : Rs.30
Transaction History:
X -> Y : Rs.100
Y -> Charlie : Rs.50
Z -> X : Rs.30
Minimized Transactions:
X pays Rs.20 to Bob

