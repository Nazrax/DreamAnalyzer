package aabraham.analyzer;

import static org.junit.Assert.*;

import org.junit.Test;

public class RangeSetTest {

  @Test
  public void testAdd() {
    RangeSet set = new RangeSet();
    set.add(1, 5);
    set.add(7, 15);
    set.add(20, 25);
    set.add(6, 10);
    assertEquals(set.size(), 2);
    assertTrue(set.contains(new Range(1, 15)));
    assertTrue(set.contains(new Range(20, 25)));
  }
  
  @Test
  public void testDifferences() {
    RangeSet set = new RangeSet();
    set.add(5, 10);
    set.add(15, 20);
    
    RangeSet differences = set.difference(new Range(1, 25));
    assertEquals(differences.size(), 3);
    assertTrue(differences.contains(new Range(1, 4)));
    assertTrue(differences.contains(new Range(11, 14)));
    assertTrue(differences.contains(new Range(21, 25)));
  }
}
