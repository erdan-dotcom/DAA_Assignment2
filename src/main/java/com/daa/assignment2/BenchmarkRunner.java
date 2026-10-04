package com.daa.assignment2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        int runs = 5;
        Random random = new Random(42);

        File resultsDir = new File("results");
        if (!resultsDir.exists()) {
            resultsDir.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("results/results.csv"))) {
            writer.println("workload,variant,structure,n,time_ms,steps,moves,comparisons");

            for (int n : sizes) {
                runW1(n, runs, random, writer);
                runW2(n, runs, random, writer);
                runW3(n, runs, random, writer);
                runW4(n, runs, random, writer);
            }
            System.out.println("Benchmark completed successfully! Results saved to results/results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runW1(int n, int runs, Random random, PrintWriter writer) {
        for (String structType : new String[]{"DynamicArray", "MyLinkedList"}) {
            long[] times = new long[runs];
            long steps = 0, moves = 0, comparisons = 0;

            for (int r = 0; r < runs; r++) {
                int[] data = generateData(n, 42);
                DynamicArray da = null;
                MyLinkedList ll = null;

                if (structType.equals("DynamicArray")) {
                    da = new DynamicArray(n);
                    for (int val : data) da.add(val);
                    da.metrics.reset();
                } else {
                    ll = new MyLinkedList();
                    for (int val : data) ll.add(val);
                    ll.metrics.reset();
                }

                int[] indices = new int[10000];
                for (int i = 0; i < 10000; i++) {
                    indices[i] = random.nextInt(n);
                }

                long start = System.nanoTime();
                if (structType.equals("DynamicArray")) {
                    for (int idx : indices) da.get(idx);
                    steps = da.metrics.steps;
                    moves = da.metrics.moves;
                    comparisons = da.metrics.comparisons;
                } else {
                    for (int idx : indices) ll.get(idx);
                    steps = ll.metrics.steps;
                    moves = ll.metrics.moves;
                    comparisons = ll.metrics.comparisons;
                }
                long end = System.nanoTime();
                times[r] = (end - start) / 1_000_000;
            }
            long medianTime = getMedian(times);
            writer.printf("W1,none,%s,%d,%d,%d,%d,%d\n", structType, n, medianTime, steps, moves, comparisons);
        }
    }

    private static void runW2(int n, int runs, Random random, PrintWriter writer) {
        for (String structType : new String[]{"DynamicArray", "MyLinkedList"}) {
            long[] times = new long[runs];
            long steps = 0, moves = 0, comparisons = 0;

            for (int r = 0; r < runs; r++) {
                int[] data = generateData(n, 42);
                int[] queries = new int[1000];
                for (int i = 0; i < 500; i++) queries[i] = data[random.nextInt(n)];
                for (int i = 500; i < 1000; i++) queries[i] = random.nextInt();

                DynamicArray da = null;
                MyLinkedList ll = null;

                if (structType.equals("DynamicArray")) {
                    da = new DynamicArray(n);
                    for (int val : data) da.add(val);
                    da.metrics.reset();
                } else {
                    ll = new MyLinkedList();
                    for (int val : data) ll.add(val);
                    ll.metrics.reset();
                }

                long start = System.nanoTime();
                if (structType.equals("DynamicArray")) {
                    for (int q : queries) da.contains(q);
                    steps = da.metrics.steps;
                    moves = da.metrics.moves;
                    comparisons = da.metrics.comparisons;
                } else {
                    for (int q : queries) ll.contains(q);
                    steps = ll.metrics.steps;
                    moves = ll.metrics.moves;
                    comparisons = ll.metrics.comparisons;
                }
                long end = System.nanoTime();
                times[r] = (end - start) / 1_000_000;
            }
            long medianTime = getMedian(times);
            writer.printf("W2,none,%s,%d,%d,%d,%d,%d\n", structType, n, medianTime, steps, moves, comparisons);
        }
    }

    private static void runW3(int n, int runs, Random random, PrintWriter writer) {
        for (String variant : new String[]{"head", "middle"}) {
            for (String structType : new String[]{"DynamicArray", "MyLinkedList"}) {
                long[] times = new long[runs];
                long steps = 0, moves = 0, comparisons = 0;

                for (int r = 0; r < runs; r++) {
                    int[] data = generateData(n, 42);
                    DynamicArray da = null;
                    MyLinkedList ll = null;

                    if (structType.equals("DynamicArray")) {
                        da = new DynamicArray(n + 1000);
                        for (int val : data) da.add(val);
                        da.metrics.reset();
                    } else {
                        ll = new MyLinkedList();
                        for (int val : data) ll.add(val);
                        ll.metrics.reset();
                    }

                    int targetIndex = variant.equals("head") ? 0 : n / 2;

                    long start = System.nanoTime();
                    if (structType.equals("DynamicArray")) {
                        for (int i = 0; i < 1000; i++) da.add(targetIndex, i);
                        for (int i = 0; i < 1000; i++) da.remove(targetIndex);
                        steps = da.metrics.steps;
                        moves = da.metrics.moves;
                        comparisons = da.metrics.comparisons;
                    } else {
                        for (int i = 0; i < 1000; i++) ll.add(targetIndex, i);
                        for (int i = 0; i < 1000; i++) ll.remove(targetIndex);
                        steps = ll.metrics.steps;
                        moves = ll.metrics.moves;
                        comparisons = ll.metrics.comparisons;
                    }
                    long end = System.nanoTime();
                    times[r] = (end - start) / 1_000_000;
                }
                long medianTime = getMedian(times);
                writer.printf("W3,%s,%s,%d,%d,%d,%d,%d\n", variant, structType, n, medianTime, steps, moves, comparisons);
            }
        }
    }

    private static void runW4(int n, int runs, Random random, PrintWriter writer) {
        long[] times = new long[runs];
        long steps = 0, moves = 0, comparisons = 0;

        for (int r = 0; r < runs; r++) {
            int[] data = generateData(n, 42);
            MinHeap heap = new MinHeap(n);
            for (int val : data) heap.insert(val);
            heap.metrics.reset();

            long start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                heap.extractMin();
            }
            long end = System.nanoTime();
            steps = heap.metrics.steps;
            moves = heap.metrics.moves;
            comparisons = heap.metrics.comparisons;
            times[r] = (end - start) / 1_000_000;
        }
        long medianTime = getMedian(times);
        writer.printf("W4,none,MinHeap,%d,%d,%d,%d,%d\n", n, medianTime, steps, moves, comparisons);
    }

    private static int[] generateData(int n, long seed) {
        Random r = new Random(seed);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(1000000);
        }
        return arr;
    }

    private static long getMedian(long[] arr) {
        java.util.Arrays.sort(arr);
        return arr[arr.length / 2];
    }
}