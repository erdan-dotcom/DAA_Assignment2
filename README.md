# DAA Assignment 2 - Data Structures & Workloads

## Project Overview
This project implements three fundamental data structures from scratch (without using `java.util` collections) in Java:
1. **DynamicArray**: Custom array-based structure with $2\times$ resizing and index-based operations.
2. **MyLinkedList**: Custom singly linked list with pointer-based node management.
3. **MinHeap**: Array-based binary min-heap supporting priority queue operations.

The project also includes a benchmarking framework to measure time, steps, moves, and comparisons across four distinct workloads (W1–W4) and exports results to CSV.

---

## Project Structure
- `src/main/java/com/daa/assignment2/` - Source code for data structures, metrics, and benchmark runner.
- `src/test/java/com/daa/assignment2/` - JUnit 5 test suites.
- `results/` - Contains `results.csv` with performance measurements.
- `REPORT.md` - Comprehensive report including asymptotic analysis, loop invariant proofs, and performance discussion.

---

## How to Build and Run

### Prerequisites
- Java Development Kit (JDK) 17 or higher.
- Maven.

### 1. Run Unit Tests (JUnit 5)
You can run tests through your IDE (IntelliJ IDEA) by right-clicking the `test/java` directory and selecting **Run Tests**, or via command line:
```bash
mvn test