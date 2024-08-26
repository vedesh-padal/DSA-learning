import java.util.*;

class Main {
  public static void main(String[] args) {
    // Array Initialization.
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, -4, -5));
    RearrangeBySign(A);

    for (int i = 0; i < A.size(); i++) {
      System.out.print(A.get(i) + " ");
    }
  }

  public static void RearrangeBySign(ArrayList<Integer> A) {
    int n = A.size();
    int posIndex = 0; // Pointer for positive elements
    int negIndex = 1; // Pointer for negative elements

    while (posIndex < n && negIndex < n) {
      // Find the next positive element
      while (posIndex < n && A.get(posIndex) >= 0) {
        posIndex++; // Move to the next index to find a positive number
      }
      // Find the next negative element
      while (negIndex < n && A.get(negIndex) < 0) {
        negIndex++; // Move to the next index to find a negative number
      }

      // Swap the positive and negative elements if both indices are valid
      if (posIndex < n && negIndex < n) {
        int temp = A.get(posIndex);
        A.set(posIndex, A.get(negIndex));
        A.set(negIndex, temp);
      }

      // Move both pointers to the next intended positions
      posIndex += 2; // Move to the next even index for the next positive
      negIndex += 2; // Move to the next odd index for the next negative
    }
  }

  // -----------------------------------------
  // if num of positives and negatives are not equal
  public static ArrayList<Integer> RearrangebySign(ArrayList<Integer> A, int n) {
    // Define 2 ArrayLists, one for storing positive
    // and other for negative elements of the array.
    ArrayList<Integer> pos = new ArrayList<>();
    ArrayList<Integer> neg = new ArrayList<>();

    // Segregate the array into positives and negatives.
    for (int i = 0; i < n; i++) {
      if (A.get(i) > 0)
        pos.add(A.get(i));
      else
        neg.add(A.get(i));
    }

    // If positives are lesser than the negatives.
    if (pos.size() < neg.size()) {

      // First, fill array alternatively till the point
      // where positives and negatives are equal in number.
      for (int i = 0; i < pos.size(); i++) {
        A.set(2 * i, pos.get(i));
        A.set(2 * i + 1, neg.get(i));
      }

      // Fill the remaining negatives at the end of the array.
      int index = pos.size() * 2;
      for (int i = pos.size(); i < neg.size(); i++) {
        A.set(index, neg.get(i));
        index++;
      }
    }

    // If negatives are lesser than the positives.
    else {
      // First, fill array alternatively till the point
      // where positives and negatives are equal in number.
      for (int i = 0; i < neg.size(); i++) {
        A.set(2 * i, pos.get(i));
        A.set(2 * i + 1, neg.get(i));
      }

      // Fill the remaining positives at the end of the array.
      int index = neg.size() * 2;
      for (int i = neg.size(); i < pos.size(); i++) {
        A.set(index, pos.get(i));
        index++;
      }
    }
    return A;
  }
}