import java.util.Arrays;
import java.util.Random; 
public class Sorts {
  
  public static void main(String[] args) {
   int num = args.length == 1 ? Integer.parseInt(args[0]) : 10000000;
		Integer[] array = new Integer[num];
		Integer[] array2 = new Integer[num];
		Random rand;
		long start, stop;
		rand = new Random(1);
		for (int i = 0; i < array.length; ++i) {
		array[i] = rand.nextInt(num);
		}
		for (int i = 0; i < array.length; ++i) {
		array2[i] = array[i];
		}
		start = System.currentTimeMillis();
		Sorts.heapSort(array);
		stop = System.currentTimeMillis();
		for (int i = 0; i < array.length - 1; ++i) {
		if (array[i] > array[i+1]) {
		System.out.println("insert fail");
		System.exit(0);
		}
		}
		System.out.println("Heap Sort: " + (stop - start));
		start = System.currentTimeMillis();
		Sorts.heapSortI(array2);
		stop = System.currentTimeMillis();
		for (int i = 0; i < array2.length - 1; ++i) {
		if (array2[i] > array2[i+1]) {
		System.out.println("insert fail");
		System.exit(0);
		}
		}
		System.out.println("Heap Sort: " + (stop - start));
  }
  
  public static <T extends Comparable<? super T>> void heapSort(T[] array) {
    int unsorted = array.length; 
    for (int i = (unsorted / 2) - 1; i >= 0 ; i--) {
      heapify(array, unsorted, i); 
    }
    for (int i = unsorted - 1 ; i >= 0; i--) {
      T temp = array[0]; 
      array[0] = array[i]; 
      array[i] = temp; 
      heapify(array, i, 0);      
    }  
  }  
  public static <T extends Comparable<? super T>> void heapSortI(T[] array) {
    int unsorted = array.length; 
    for (int i = (unsorted / 2) - 1; i >= 0 ; i--) {
      heapify(array, unsorted, i); 
    }
    for (int i = unsorted - 1 ; i >= 0; i--) {
      T temp = array[0]; 
      array[0] = array[i]; 
      array[i] = temp; 
      heapifyI(array, i, 0);      
    }  
  }  
  public static <T extends Comparable<? super T>> void heapify(T[] array, int unsorted, int root) {
    int lc = (root*2) + 1; 
    int rc = (root*2) + 2; 
    int largest = root;     
    if(rc < unsorted && array[rc].compareTo(array[largest]) > 0) {
      largest = rc; 
    }
    if(lc < unsorted && array[lc].compareTo(array[largest]) > 0) {
      largest = lc; 
    }
    if(largest != root){
      T temp  = array[largest]; 
      array[largest] = array[root]; 
      array[root] = temp; 
      heapify(array, unsorted, largest); 
    }
  }

    public static <T extends Comparable<? super T>> void heapifyI(T[] array, int n, int root) {
        int largest = root;
        while (true) {
            int left = 2 * root + 1;
            int right = 2 * root + 2;            
            if (left < n && array[left].compareTo(array[largest]) > 0) {
                largest = left;
            }

            if (right < n && array[right].compareTo(array[largest]) > 0) {
                largest = right;
            }
            if (largest != root) {
                T temp = array[root];
                array[root] = array[largest];
                array[largest] = temp;
                root = largest;  
            } else {
                break;  
            }
        }
    }

    public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {

        T temp;
        boolean sorted;

        for (int i = array.length - 1; i > 0; --i) {

            sorted = true;

            for (int j = 0; j < i; ++j) {

                if (array[j].compareTo(array[j + 1]) > 0) {

                    sorted = false;

                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

            if (sorted) {
                break;
            }
        }
    }

    public static <T extends Comparable<? super T>> void insertSort(T[] array) {

        T temp;
        int j;

        for (int i = 1; i < array.length; ++i) {

            temp = array[i];

            for (j = i; j > 0; --j) {

                if (array[j - 1].compareTo(temp) > 0) {
                    array[j] = array[j - 1];
                }
                else {
                    break;
                }
            }

            if (j < i) {
                array[j] = temp;
            }
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] array) {

        if (array.length > 1) {

            T[] left  = Arrays.copyOfRange(array, 0, array.length / 2);
            T[] right = Arrays.copyOfRange(array, array.length / 2, array.length);

            mergeSort(left);
            mergeSort(right);

            int i, l = 0, r = 0;

            for (i = 0; i < array.length && l < left.length && r < right.length; ++i) {
                if (left[l].compareTo(right[r]) <= 0) {
                    array[i] = left[l++];
                }
                else {
                    array[i] = right[r++];
                }
            }

            if (i < array.length) {
                if (l < left.length) {
                    while (i < array.length) {
                        array[i++] = left[l++];
                    }
                }
                else {
                    while (i < array.length) {
                        array[i++] = right[r++];
                    }
                }
            }
        }
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] array) {

        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] array, int left, int right) {

        int pivot = right;

        int l = left, r = right;

        if (left < right) {
            while (l < r) {

                while (l < r && array[l].compareTo(array[pivot]) <= 0) {
                    ++l;
                }

                while (l < r && array[pivot].compareTo(array[r]) <= 0) {
                    --r;
                }

                if (l < r) {
                    T temp = array[l];
                    array[l] = array[r];
                    array[r] = temp;
                }
            }

            if (r != pivot) {
                T temp = array[pivot];
                array[pivot] = array[r];
                array[r] = temp;
            }

            quickSort(array, left, r - 1);
            quickSort(array, r + 1, right);
        }
    }

    public static <T extends Comparable<? super T>> void selectSort(T[] array) {

        T temp;
        int mini;

        for (int i = 0; i < array.length - 1; ++i) {

            mini = i;

            for (int j = i + 1; j < array.length; ++j) {

                if (array[j].compareTo(array[mini]) < 0) {
                    mini = j;
                }
            }

            if (i != mini) {

                temp = array[i];
                array[i] = array[mini];
                array[mini] = temp;
            }
        }
    }
}