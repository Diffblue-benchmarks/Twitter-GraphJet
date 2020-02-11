package com.twitter.graphjet.algorithms.counting.moment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.twitter.graphjet.algorithms.NodeInfo;
import com.twitter.graphjet.algorithms.RecommendationType;
import com.twitter.graphjet.algorithms.filters.ResultFilterChain;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.LongSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

/**
 * Unit tests for com.twitter.graphjet.algorithms.counting.moment.TopSecondDegreeByCountMomentRecsGenerator
 *
 * @author Diffblue JCover
 */

public class TopSecondDegreeByCountMomentRecsGeneratorTest {

    @Test(timeout=10000)
    public void generateMomentRecs() {
        Long2DoubleMap leftSeedNodesWithWeight = mock(Long2DoubleMap.class);
        LongSet toBeFiltered = mock(LongSet.class);
        byte[] socialProofTypes = new byte[] { 1 };
        List<NodeInfo> candidateNodes = new ArrayList<NodeInfo>();
        ((ArrayList<NodeInfo>)candidateNodes).add(new NodeInfo(1L, 1.0, 1));
        assertThat(TopSecondDegreeByCountMomentRecsGenerator.generateMomentRecs(new TopSecondDegreeByCountRequestForMoment(1L, leftSeedNodesWithWeight, toBeFiltered, 1, 1, 1, new HashMap<Byte, Integer>(), socialProofTypes, 1L, 1L, new ResultFilterChain(new ArrayList<com.twitter.graphjet.algorithms.filters.ResultFilter>())), candidateNodes).size(), is(1));
        assertThat(TopSecondDegreeByCountMomentRecsGenerator.generateMomentRecs(new TopSecondDegreeByCountRequestForMoment(1L, leftSeedNodesWithWeight, toBeFiltered, 1, 1, 1, new HashMap<Byte, Integer>(), socialProofTypes, 1L, 1L, new ResultFilterChain(new ArrayList<com.twitter.graphjet.algorithms.filters.ResultFilter>())), candidateNodes).get(0).getRecommendationType(), is(RecommendationType.MOMENT));
        assertEquals(1.0, TopSecondDegreeByCountMomentRecsGenerator.generateMomentRecs(new TopSecondDegreeByCountRequestForMoment(1L, leftSeedNodesWithWeight, toBeFiltered, 1, 1, 1, new HashMap<Byte, Integer>(), socialProofTypes, 1L, 1L, new ResultFilterChain(new ArrayList<com.twitter.graphjet.algorithms.filters.ResultFilter>())), candidateNodes).get(0).getWeight(), 0);
    }

    @Test(timeout=10000)
    public void generateMomentRecsCandidateNodesIsEmptyReturnsEmpty() {
        Long2DoubleMap leftSeedNodesWithWeight = mock(Long2DoubleMap.class);
        LongSet toBeFiltered = mock(LongSet.class);
        byte[] socialProofTypes = new byte[] { 1 };
        assertTrue(TopSecondDegreeByCountMomentRecsGenerator.generateMomentRecs(new TopSecondDegreeByCountRequestForMoment(0L, leftSeedNodesWithWeight, toBeFiltered, 1, 1, 1, new HashMap<Byte, Integer>(), socialProofTypes, 1L, 1L, new ResultFilterChain(new ArrayList<com.twitter.graphjet.algorithms.filters.ResultFilter>())), new ArrayList<NodeInfo>()).isEmpty());
    }
}
