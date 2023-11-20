import java.util.*;
public class main {
    private int[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public main() {
        heap = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, size * 2);
        }
    }

    public void insert(int value) {
        ensureCapacity();
        heap[size] = value;
        size++;
        siftUp(size - 1);
    }

    private void siftUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap[index] > heap[parentIndex]) {
            // Swap current element with its parent
            int temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return max;
    }

    private void siftDown(int index) {
        int leftChildIndex, rightChildIndex, maxIndex;
        while (2 * index + 1 < size) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;
            maxIndex = leftChildIndex;
            if (rightChildIndex < size && heap[rightChildIndex] > heap[leftChildIndex]) {
                maxIndex = rightChildIndex;
            }
            if (heap[index] < heap[maxIndex]) {
                int temp = heap[index];
                heap[index] = heap[maxIndex];
                heap[maxIndex] = temp;
                index = maxIndex;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Given list of integers
        List<Integer> elements = Arrays.asList(10, 8, 12, 85, 64, 3, 29, 76);

        // Create a NonHeapifySort object
        main maxHeap = new main();

        // Insert elements into the max heap
        for (int element : elements) {
            maxHeap.insert(element);
        }

        // Print the original list
        System.out.println("Original List: " + elements);

        // Extract elements from the max heap (sorted order) and store in a list
        List<Integer> sortedList = new ArrayList<>();
        while (maxHeap.size > 0) {
            sortedList.add(maxHeap.extractMax());
        }

        // Print the sorted list
        System.out.println("Sorted List: " + sortedList);
    }
}