package aabraham.analyzer;

import java.util.Set;
import java.util.TreeSet;

public class Range implements Comparable<Range> {
  private int low, high;
  
  public Range(int low, int high) {
    this.low = low;
    this.high = high;
  }
  
  public boolean isAdjacent(Range r) {
    if (low - r.high == 1) { // [ Their range][Our range]
      return true;
    } else if (r.low - high == 1) { // [Our range][Their range]
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isOverlapping(Range r) {
    if (low <= r.low && r.low <= high) { // OL TL OH [TH]
      return true;
    } else if (low <= r.high && r.high <= high) { // [TL] OL TH OH  
      return true;
    } else if (low <= r.low && r.high <= high) { // OL TL TH OH
      return true;
    } else if (r.low <= low && high <= r.high) { // TL OL OH TH
      return true;
    } else {
      return false;
    }
  }

  public static Range merge(Range r1, Range r2) {
    if (r1.isAdjacent(r2) || r1.isOverlapping(r2)) {
      int low = Math.min(r1.low, r2.low);
      int high = Math.max(r1.high, r2.high);
      return new Range(low, high);
    } else {
      throw new RuntimeException("Can't merge ranges");
    }
  }

  @Override
  public int compareTo(Range r) {
    int c1 = new Integer(low).compareTo(new Integer(r.low));
    
    if (c1 != 0) {
      return c1;
    } else {
      int c2 = new Integer(high).compareTo(new Integer(r.high));
      return c2;
    }
  }
  
  public String toString() {
    return "" + low + ":" + high;
  }
  
  public Set<Range> difference(Range r) {
    Set<Range> set = new TreeSet<Range>();
    if (!isOverlapping(r)) {
      set.add(r);
    } else {
      if (r.low < low) { // [Their range [Our range]
        set.add(new Range(r.low, low-1));
      }
      if (r.high > high) { // [Our range] Their range ]
        set.add(new Range(high+1, r.high));
      }
    }
    return set;
  }
}
