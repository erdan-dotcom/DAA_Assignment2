# Assignment 2: Data Structures & Workloads - Report

## 1. Asymptotic Complexity Table

| Structure & Operation | Best Case ($\Omega$) | Average Case ($\Theta$) | Worst Case ($O$) | Auxiliary Space | Justification |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **DynamicArray.get(i)** | $\Omega(1)$ | $\Theta(1)$ | $O(1)$ | $O(1)$ | Direct array indexing via memory address offset. |
| **DynamicArray.add(x)** | $\Omega(1)$ | $\Theta(1)$ | $O(N)$ | $O(N)$ | Amortized $O(1)$ due to $2\times$ geometric resizing. |
| **DynamicArray.add(i, x)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(N)$ | Requires shifting elements to the right. |
| **DynamicArray.remove(i)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(N)$ | Requires shifting elements to the left. |
| **DynamicArray.contains(x)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(1)$ | Linear scan through the array elements. |
| **MyLinkedList.get(i)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(1)$ | Pointer chasing from head to index $i$. |
| **MyLinkedList.add(x)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(N)$ | Traversal to tail required for singly linked list. |
| **MyLinkedList.add(i, x)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(N)$ | Traversal to position $i$, then pointer update. |
| **MyLinkedList.remove(i)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(N)$ | Traversal to position $i-1$, then pointer update. |
| **MyLinkedList.contains(x)** | $\Omega(1)$ | $\Theta(N)$ | $O(N)$ | $O(1)$ | Linear traversal of nodes. |
| **MinHeap.peekMin()** | $\Omega(1)$ | $\Theta(1)$ | $O(1)$ | $O(1)$ | Root element is always the minimum. |
| **MinHeap.insert(x)** | $\Omega(1)$ | $\Theta(\log N)$ | $O(\log N)$ | $O(N)$ | Appends at end, followed by bubble-up. |
| **MinHeap.extractMin()** | $\Omega(1)$ | $\Theta(\log N)$ | $O(\log N)$ | $O(N)$ | Swaps root with last element, followed by bubble-down. |

---

## 2. Loop Invariant Proofs

### Proof 1: `DynamicArray.contains(int element)`
- **Invariant:** At the start of every iteration $i$, the target `element` has not been found in the sub-array from index `0` to `i - 1`.
- **Initialization:** Before the first iteration ($i = 0$), the sub-array is empty, so the target trivially has not been found.
- **Maintenance:** Assume the invariant holds before iteration $i$. If `data[i] == element`, the method immediately returns `true` (correct). If not, the element is confirmed absent in index $i$ as well, so before iteration $i + 1$, the target has not been found in `0` through `i`.
- **Termination:** The loop terminates when $i = \text{size}$. If no match was found, the invariant ensures the element is absent from земля/entire array, so returning `false` is correct.
- **Conclusion:** By induction, the loop correctly determines presence or absence.

### Proof 2: `MinHeap.bubbleUp(int index)`
- **Invariant:** At the start of every iteration, the heap property might be violated only between `currentIndex` and its parent; all other parent-child relationships in the subtree rooted above remain valid.
- **Initialization:** Before the first iteration, an element was appended at the very end of the heap, potentially violating the heap property solely with its direct parent.
- **Maintenance:** If `heap[currentIndex] < heap[parentIndex]`, the elements are swapped, restoring order locally at this level and moving `currentIndex` up to the parent position, preserving the invariant for the next iteration.
- **Termination:** The loop terminates when `currentIndex == 0` or `heap[currentIndex] >= heap[parentIndex]`, meaning local heap order is fully restored.
- **Conclusion:** The proof guarantees that the min-heap property is successfully re-established across the structure.

---

## 3. Discussion & Performance Analysis

1. **CPU Cache Locality & Spatial Locality (`DynamicArray` vs `MyLinkedList`):**
   `DynamicArray` stores primitive `int` values contiguously in memory (`int[]`). When the CPU reads an element, it fetches an entire cache line containing adjacent elements (spatial locality). This makes random access (`get(i)`) and linear scans (`contains`) extremely fast.

2. **Pointer Chasing & Overhead:**
   `MyLinkedList` allocates individual `Node` objects scattered across the heap. Each access (`get(i)`) requires traversing pointer references (*pointer chasing*), causing frequent CPU cache misses. Additionally, object headers and references introduce significant memory overhead and garbage collection pressure.

3. **When to Choose Each Structure:**
    - Use `DynamicArray` when frequent random access (`get(i)`) and iteration speed are required.
    - Use `MyLinkedList` when frequent insertions and removals occur specifically at known boundaries (like a queue/stack implementation where node references are already held).
    - Use `MinHeap` when priority-based scheduling or continuous access to the minimum element is needed.