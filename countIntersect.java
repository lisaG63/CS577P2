import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.Arrays;
import java.util.Comparator;

public class countIntersect {
	
	private static long merge(int arr[], int start, int mid, int end) {

		long count = 0;
		int size1 = mid - start + 1;
		int size2 = end - mid;
		
		int left[] = new int[size1];
		int right[] = new int[size2];
		
		for (int i = 0; i < size1; i ++) {
			left[i] = arr[start + i];
		}
		
		for (int i = 0; i < size2; i ++) {
			right[i] = arr[mid + 1 + i];
		}
		
		
		int i = 0;
		int j = 0;
		int cur = start;
		while (i < size1 && j < size2) {
			if (left[i] < right[j]) {
				arr[cur] = left[i];
				i ++;
			} else {
				arr[cur] = right[j];
				j ++;
				count += mid + 1 - i - start;
			}
			cur ++;
		}
		
		while (i < size1) {
			arr[cur] = left[i];
			cur ++;
			i ++;
		}
		
		while (j < size2) {
			arr[cur] = right[j];
			cur ++;
			j ++;
		}
		return count;
	}
	
	private static long sort(int arr[], int start, int end) {
		long count = 0;
		if (start < end) {
			
			int mid = (int) Math.floor((start + end) / 2);
			count += sort(arr, start, mid);
			count += sort(arr, mid + 1, end);
			count += merge(arr, start, mid, end);
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			int size = Integer.parseInt(bf.readLine());
			int[][] twoDarr = new int[size][2];
			
			
			for (int i = 0; i < size; i ++) {
				twoDarr[i][0] = Integer.parseInt(bf.readLine());
			}
			for (int i = 0; i < size; i ++) {
				twoDarr[i][1] = Integer.parseInt(bf.readLine());
			}
			
			Arrays.sort(twoDarr, Comparator.comparingDouble(o -> o[0]));
			
			int[] arr = new int[size];
			for (int i = 0; i < size; i ++) {
				arr[i] = twoDarr[i][1];
			}
			System.out.println(sort(arr, 0, size - 1));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
