package com.twitter.graphjet.bipartite.edgepool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.twitter.graphjet.hashing.BigIntArray;
import com.twitter.graphjet.hashing.IntToIntPairArrayIndexBasedMap;
import com.twitter.graphjet.hashing.IntToIntPairHashMap;
import com.twitter.graphjet.hashing.ShardedBigIntArray;
import com.twitter.graphjet.stats.NullStatsReceiver;
import com.twitter.graphjet.stats.StatsReceiver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RegularDegreeEdgePoolDiffblueTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test(timeout=10000)
  public void getMetadataTest() {
    // Arrange
    ShardedBigIntArray edges = new ShardedBigIntArray(10, 1, 1, new NullStatsReceiver());

    // Act and Assert
    thrown.expect(UnsupportedOperationException.class);
    (new RegularDegreeEdgePool.ReaderAccessibleInfo(edges,
        new IntToIntPairArrayIndexBasedMap(10, 42, new NullStatsReceiver()))).getMetadata();
  }

  @Test(timeout=10000)
  public void constructorTest3() {
    // Arrange
    ShardedBigIntArray shardedBigIntArray = new ShardedBigIntArray(10, 1, 1, new NullStatsReceiver());
    IntToIntPairArrayIndexBasedMap intToIntPairArrayIndexBasedMap = new IntToIntPairArrayIndexBasedMap(10, 42,
        new NullStatsReceiver());

    // Act
    RegularDegreeEdgePool.ReaderAccessibleInfo actualReaderAccessibleInfo = new RegularDegreeEdgePool.ReaderAccessibleInfo(
        shardedBigIntArray, intToIntPairArrayIndexBasedMap);

    // Assert
    BigIntArray actualEdges = actualReaderAccessibleInfo.getEdges();
    assertSame(shardedBigIntArray, actualEdges);
    assertSame(intToIntPairArrayIndexBasedMap, actualReaderAccessibleInfo.getNodeInfo());
  }

  @Test(timeout=10000)
  public void getNodeInfoTest() {
    // Arrange
    ShardedBigIntArray edges = new ShardedBigIntArray(10, 1, 1, new NullStatsReceiver());
    RegularDegreeEdgePool.ReaderAccessibleInfo readerAccessibleInfo = new RegularDegreeEdgePool.ReaderAccessibleInfo(
        edges, new IntToIntPairArrayIndexBasedMap(10, 42, new NullStatsReceiver()));

    // Act and Assert
    assertSame(readerAccessibleInfo.nodeInfo, readerAccessibleInfo.getNodeInfo());
  }

  @Test(timeout=10000)
  public void getEdgesTest() {
    // Arrange
    ShardedBigIntArray shardedBigIntArray = new ShardedBigIntArray(10, 1, 1, new NullStatsReceiver());

    // Act and Assert
    assertSame(shardedBigIntArray, (new RegularDegreeEdgePool.ReaderAccessibleInfo(shardedBigIntArray,
        new IntToIntPairArrayIndexBasedMap(10, 42, new NullStatsReceiver()))).getEdges());
  }

  @Test(timeout=10000)
  public void addEdgeTest2() {
    // Arrange
    RegularDegreeEdgePool regularDegreeEdgePool = new RegularDegreeEdgePool(10, 3, new NullStatsReceiver());

    // Act
    regularDegreeEdgePool.addEdge(2, 2);

    // Assert
    int actualResultInt = regularDegreeEdgePool.currentPositionOffset;
    assertEquals(1, regularDegreeEdgePool.currentNumNodes);
    assertEquals(1, regularDegreeEdgePool.currentNumEdgesStored);
    assertEquals(3, actualResultInt);
    assertEquals(7.62939453125E-4, regularDegreeEdgePool.readerAccessibleInfo.getEdges().getFillPercentage(), 0.0);
  }

  @Test(timeout=10000)
  public void getMetadataShardTest() {
    // Arrange, Act and Assert
    assertEquals(4, (new RegularDegreeEdgePool(10, 3, new NullStatsReceiver())).getMetadataShard(1).length);
  }

  @Test(timeout=10000)
  public void addEdgeTest() {
    // Arrange, Act and Assert
    thrown.expect(UnsupportedOperationException.class);
    (new RegularDegreeEdgePool(10, 3, new NullStatsReceiver())).addEdge(2, 2, 1L);
  }

  @Test(timeout=10000)
  public void getNumberedEdgeMetadataTest() {
    // Arrange, Act and Assert
    assertEquals(0L, (new RegularDegreeEdgePool(10, 3, new NullStatsReceiver())).getNumberedEdgeMetadata(1, 10));
  }

  @Test(timeout=10000)
  public void constructorTest2() {
    // Arrange and Act
    RegularDegreeEdgePool actualRegularDegreeEdgePool = new RegularDegreeEdgePool(10, 3, new NullStatsReceiver());

    // Assert
    double actualFillPercentage = actualRegularDegreeEdgePool.getFillPercentage();
    StatsReceiver statsReceiver = actualRegularDegreeEdgePool.scopedStatsReceiver;
    int actualResultInt = actualRegularDegreeEdgePool.currentPositionOffset;
    EdgePoolReaderAccessibleInfo edgePoolReaderAccessibleInfo = actualRegularDegreeEdgePool.readerAccessibleInfo;
    assertEquals(0, actualRegularDegreeEdgePool.currentShardId);
    assertEquals(0, actualRegularDegreeEdgePool.currentNumNodes);
    assertEquals(3, actualRegularDegreeEdgePool.maxDegree);
    assertSame(actualRegularDegreeEdgePool.numNodesCounter, actualRegularDegreeEdgePool.numEdgesCounter);
    BigIntArray edges = edgePoolReaderAccessibleInfo.getEdges();
    assertEquals(0, actualResultInt);
    assertEquals(0.0, actualFillPercentage, 0.0);
    assertTrue(statsReceiver instanceof NullStatsReceiver);
    assertTrue(edgePoolReaderAccessibleInfo
        .getNodeInfo() instanceof com.twitter.graphjet.hashing.IntToIntPairConcurrentHashMap);
    assertEquals(0.0, edges.getFillPercentage(), 0.0);
  }

  @Test(timeout=10000)
  public void constructorTest() {
    // Arrange and Act
    RegularDegreeEdgePool actualRegularDegreeEdgePool = new RegularDegreeEdgePool(10, 2, new NullStatsReceiver());

    // Assert
    double actualFillPercentage = actualRegularDegreeEdgePool.getFillPercentage();
    StatsReceiver statsReceiver = actualRegularDegreeEdgePool.scopedStatsReceiver;
    int actualResultInt = actualRegularDegreeEdgePool.currentPositionOffset;
    EdgePoolReaderAccessibleInfo edgePoolReaderAccessibleInfo = actualRegularDegreeEdgePool.readerAccessibleInfo;
    assertEquals(0, actualRegularDegreeEdgePool.currentShardId);
    assertEquals(0, actualRegularDegreeEdgePool.currentNumNodes);
    assertEquals(2, actualRegularDegreeEdgePool.maxDegree);
    assertSame(actualRegularDegreeEdgePool.numNodesCounter, actualRegularDegreeEdgePool.numEdgesCounter);
    BigIntArray edges = edgePoolReaderAccessibleInfo.getEdges();
    IntToIntPairHashMap nodeInfo = edgePoolReaderAccessibleInfo.getNodeInfo();
    assertEquals(0, actualResultInt);
    assertEquals(0.0, actualFillPercentage, 0.0);
    assertTrue(statsReceiver instanceof NullStatsReceiver);
    assertTrue(nodeInfo instanceof IntToIntPairArrayIndexBasedMap);
    BigIntArray bigIntArray = ((IntToIntPairArrayIndexBasedMap) nodeInfo).array;
    assertEquals(0.0, edges.getFillPercentage(), 0.0);
    assertTrue(bigIntArray instanceof com.twitter.graphjet.hashing.ShardedBigIntArray);
    assertEquals(0.0, bigIntArray.getFillPercentage(), 0.0);
  }
}

