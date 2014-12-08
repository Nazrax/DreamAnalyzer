package aabraham.analyzer;

import java.util.Set;
import java.util.TreeSet;

public class RangeSet extends TreeSet<Range> {
  private static final long serialVersionUID = 1L;

  public boolean add(int low, int high) {
    return add(new Range(low, high));
  }
  
  @Override
  public boolean add(Range r) {
    boolean rv = super.add(r);
    if (!rv) {
      return rv;
    }
    
    boolean needsMerge = true;
    while (needsMerge) {
      needsMerge = mergeRanges();
    }
    
    return true;
  }

  private boolean mergeRanges() {
    Range last = null;
    Range toMerge = null;
    
    for(Range r : this) {
      if (last != null && (last.isAdjacent(r) || last.isOverlapping(r))) {
        toMerge = r;
        break;
      }
      last = r;
    }
    if (toMerge != null) {
      remove(last);
      remove(toMerge);
      add(Range.merge(last, toMerge));
      return true;
    } else {
      return false;
    }
  }
  
  public RangeSet difference(Range toDiff) {
    RangeSet differences = new RangeSet();
    differences.add(toDiff);
    for(Range r : this) {
      if (r.isOverlapping(differences.last())) {
        Range last = differences.last();
        differences.remove(last);
        Set<Range> newRanges = r.difference(last);
        differences.addAll(newRanges);
        if (differences.isEmpty()) {
          break;
        }
      }
    }
    return differences;
  }

  public void dump() {
    for(Range r : this) {
      System.out.println(r);
    }
  }
}
