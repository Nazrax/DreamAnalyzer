package aabraham.analyzer;

import static org.junit.Assert.*;

import org.junit.Test;

public class RangeTest {

  @Test
  public void testIsAdjacent() {
    Range r1 = new Range(5, 10);
    Range r2 = new Range(11, 15);
    
    assertTrue(r1.isAdjacent(r2));
    assertTrue(r2.isAdjacent(r1));
  }

  @Test
  public void testIsNotAdjacent() {
    Range r1 = new Range(5, 9);
    Range r2 = new Range(11, 15);
    
    assertFalse(r1.isAdjacent(r2));
    assertFalse(r2.isAdjacent(r1));
  }

  @Test
  public void testIsOverlapping() {
    Range r1 = new Range(5, 11);
    Range r2 = new Range(11, 15);
    Range r3 = new Range(1, 20);
    
    assertTrue(r1.isOverlapping(r2));
    assertTrue(r2.isOverlapping(r1));
    assertTrue(r1.isOverlapping(r3));
  }

  @Test
  public void testIsNotOverlapping() {
    Range r1 = new Range(5, 10);
    Range r2 = new Range(11, 15);

    assertFalse(r1.isOverlapping(r2));
    assertFalse(r2.isOverlapping(r1));
  }
}
