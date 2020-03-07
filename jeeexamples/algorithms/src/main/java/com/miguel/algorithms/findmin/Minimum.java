package com.miguel.algorithms.findmin;

public class Minimum {

    private int tree[];

    private int findMin(int startIndex, int endIndex, int from, int to, int index)
    {

        // found
        if (from <= startIndex && to >= endIndex)
            return tree[index];

        // out of scope
        if (endIndex < from || startIndex > to)
            return Integer.MAX_VALUE;

        int mid = splitIndex(startIndex, endIndex);
        return Math.min(findMin(startIndex, mid, from, to, 2 * index + 1),
            findMin(mid + 1, endIndex, from, to, 2 * index + 2));
    }

    int findMin(int n, int from, int to)
    {
        if (from < 0 || to > n - 1 || from > to) {
            throw new RuntimeException("Invalid");
        }

        return findMin(0, n - 1, from, to, 0);
    }

    public void initialize(int arr[])
    {
        int size = arr.length;
        initializeTree(size);
        initialize(arr, 0, size - 1, 0);
    }

    private void initializeTree(int size) {
        int heightOfTree = (int) (Math.ceil(Math.log(size) / Math.log(2)));
        int maxTreeSize = 2 * (int) Math.pow(2, heightOfTree) - 1;
        tree = new int[maxTreeSize];
    }

    private int initialize(int[] array, int start, int end, int index)
    {
        if (start == end) {
            tree[index] = array[start];
            return array[start];
        }

        int mid = splitIndex(start, end);
        tree[index] = Math.min(
            initialize(array, start, mid, index * 2 + 1),
            initialize(array, mid + 1, end, index * 2 + 2));
        return tree[index];
    }

    private int splitIndex(int start, int end) {
        return start + (end - start) / 2;
    }
}
