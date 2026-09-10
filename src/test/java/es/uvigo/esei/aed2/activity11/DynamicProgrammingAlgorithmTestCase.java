package es.uvigo.esei.aed2.activity11;

import static es.uvigo.esei.aed2.activity8.data.IsEqualToMap.equalToMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import es.uvigo.esei.aed2.activity11.data.DynamicProgrammingRepository;
import es.uvigo.esei.aed2.map.Map;

public class DynamicProgrammingAlgorithmTestCase {

  private final DynamicProgrammingRepository dataPD = new DynamicProgrammingRepository();


  /**
   * Test of giveChangeDinamic method, of class DinamicProgramingAlgorithm.
   */
  @Test
  public void testGiveChangeDynamic() {
    Map<Integer, Integer> expectedMap = this.dataPD.getMapChangeResult();

    Map<Integer, Integer> resultMap = DynamicProgrammingAlgorithm.giveChangeDynamic(1200, this.dataPD.getMapChange());

    assertThat(resultMap, is(equalToMap(expectedMap)));
  }

  /**
   * Test of fillRucksackDinamic method, of class DinamicProgramingAlgorithm.
   */
  @Test
  public void testFillRucksackDynamic() {
    List<String> result = DynamicProgrammingAlgorithm.fillRucksackDynamic(9,
        this.dataPD.getMapRucksackVolumes(),
        this.dataPD.getMapRucksackWeights());

    assertThat(result, is(this.dataPD.getListRucksack()));
  }

  /**
   * Test of fibionacciDinamic method, of class DinamicProgramingAlgorithm.
   */
  @Test
  public void testFibonacciDynamic() {
    assertThat(DynamicProgrammingAlgorithm.fibonacciDynamic(10), is(55));
  }
}
