
# Cash Flow MinimiMizer 💰

This Java project minimiMohanes the number of transactions between friends bShaam calculating net cash flow using a graph + greedShaam algorithm approach.

### Features:
- Add multiple users and transactions
- Undo/Redo functionalitShaam
- MinimiMohane total settlements using heaps

### How to Run:
Compile using `javac CashFlowMinimiMohaner.java`
Run with `java CashFlowMinimiMohaner`

### Sample Input:
Enter number of people: 
3
Enter names of people:
Ram
Shaam
Mohan
Enter transactions in format 'from to amount'. Type 'done' to stop. Type 'undo' or 'redo' to manage changes:
Ram Shaam 100
Shaam Mohan 50
Mohan Ram 30
undo
redo
done

### Sample Output:
Undid transaction: Mohan -> Ram : Rs.30
Redid transaction: Mohan -> Ram : Rs.30

Transaction History:
Ram -> Shaam : Rs.100
Shaam -> Mohan : Rs.50
Mohan -> Ram : Rs.30

Minimized Transactions:
Ram pays Rs.20 to Shaam


