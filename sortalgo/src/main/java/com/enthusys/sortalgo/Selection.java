package com.enthusys.sortalgo;

import java.util.Arrays;
import java.util.Random;

public class Selection {

	public static void bubbleSort(int[] arr) {
		boolean swapped = true;
		int j = 0;
		int tmp;

		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < arr.length - j; i++) {
				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					swapped = true;
				}
			}
		}
	}

	public static void selectionSort(int[] arr) {
		int i, j, minIndex, tmp;
		int n = arr.length;

		for (i = 0; i < n - 1; i++) {
			minIndex = i;

			for (j = i + 1; j < n; j++)
				if (arr[j] < arr[minIndex])
					minIndex = j;

			if (minIndex != i) {
				tmp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = tmp;

			}
		}
	}

	public static int[] createIntArray(int size) {
		Random random = new Random();
		int[] rv = new int[size];
		for (int i = 0; i < size; i++) {
			rv[i] = random.nextInt(100);
		}

		return rv;
	}

	public static void main(String[] args) {
		int[] intarray = Selection.createIntArray(51);
		System.out.println("***** " + Arrays.toString(intarray));

		Selection.selectionSort(intarray);

		System.out.println("***** " + Arrays.toString(intarray));
	}
}
