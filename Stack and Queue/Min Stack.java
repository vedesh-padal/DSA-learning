import java.util.Stack;

class MinStack {
  private Stack<Long> stk;
  private long mini;

  public MinStack() {
    this.stk = new Stack<>();
    this.mini = Long.MAX_VALUE;
  }

  public void push(int val) {
    long value = (long) val;
    if (stk.isEmpty())  {
      stk.push(value);
      mini = value;
    }
    else {
      // incoming value is less than mini
      if (value < mini) {
        stk.push(2*value - mini);
        mini = val;
      }
      else {
        stk.push(value);
      }
    }
  }

  public void pop() {
    if (stk.isEmpty())
      return;
    
    long top = stk.pop();
    if (top < mini) {
      @SuppressWarnings("unused")
      long actualTop = mini;
      // updating the min. with prev. min
      // and you get the prev. min this way
      mini = 2*mini - top;
      // return actualTop;
    }

    // return (int) top;
  }

  public int top() {
    if (stk.isEmpty())
      return -1;
    
    long top = stk.peek();
    
    // means that top is the modified one
    // so our top now is mini, so we return that
    if (top < mini) {
      return (int) mini;
    }
    return (int) top;
  }

  public int getMin() {
    if (stk.isEmpty())
      return -1;
    
    return (int) mini;
  }

}

