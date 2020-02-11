package com.twitter.graphjet.algorithms.intersection;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InverseDegreeUpdateNormalizationDiffblueTest {
  @Test(timeout=10000)
  public void computeScoreNormalizationTest() {
    // Arrange, Act and Assert
    assertEquals(1.0, (new InverseDegreeUpdateNormalization()).computeScoreNormalization(10.0, 3, 3), 0.0);
  }

  @Test(timeout=10000)
  public void computeLeftNeighborContributionTest() {
    // Arrange, Act and Assert
    assertEquals(1.0, (new InverseDegreeUpdateNormalization()).computeLeftNeighborContribution(1), 0.0);
  }
}

