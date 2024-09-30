/*
 You are implementing a program to use as your calendar. We can add a new event if 
 adding the event will not cause a double booking.

  A double booking happens when two events have some non-empty intersection (i.e., 
  some moment is common to both events.).

  The event can be represented as a pair of integers start and end that represents 
  a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.

  Implement the MyCalendar class:

  MyCalendar() Initializes the calendar object.
  boolean book(int start, int end) Returns true if the event can be added to the calendar 
  successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
  

  Example 1:

  Input
  ["MyCalendar", "book", "book", "book"]
  [[], [10, 20], [15, 25], [20, 30]]
  Output
  [null, true, false, true]

  Explanation
  MyCalendar myCalendar = new MyCalendar();
  myCalendar.book(10, 20); // return True
  myCalendar.book(15, 25); // return False, It can not be booked because 
  time 15 is already booked by another event.
  myCalendar.book(20, 30); // return True, The event can be booked, as 
  the first event takes every time less than 20, but not including 20.
  

  Constraints:

  0 <= start < end <= 109
  At most 1000 calls will be made to book.
 */

import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

// O(n) -> iterating through all bookings
class MyCalendarBRUTE {
  List<int[]> bookedSlots;

  public MyCalendarBRUTE() {
    bookedSlots = new ArrayList<>();
  }

  public boolean book(int start, int end) {
    for (int[] slot : this.bookedSlots) {
      if (slot[1] > start && end > slot[0])
        return false;
    }
    this.bookedSlots.add(new int[] { start, end });
    return true;
  }
}

// OPTIMAL
// USING ORDERED SET / TREE SET (IN JAVA)

class MyCalendar {
  TreeSet<int[]> bookedSlots;

  public MyCalendar() {
    bookedSlots = new TreeSet<>((a, b) -> {
      if (a[0] != b[0])
        return Integer.compare(a[0], b[0]);
      return Integer.compare(a[1], b[1]);
    });
  }

  public boolean book(int start, int end) {
    int[] newSlot = new int[] { start, end };

    // find the closest slot that could overlap
    int[] floor = this.bookedSlots.floor(newSlot);
    if (floor != null && floor[1] > newSlot[0])
      return false;

    int[] ceiling = this.bookedSlots.ceiling(newSlot);
    if (ceiling != null && newSlot[1] > ceiling[0])
      return false;

    // no overlapping, so this slot can be added to the booked slots
    bookedSlots.add(newSlot);
    return true;
  }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */