package com.domain.java.bean;

import org.junit.Test;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-14
 */
public class NutritionFactsTest {

    @Test
    public void testNew() {

        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).
                calories(100).sodium(35).carbohydrate(27).build();

        System.out.println(cocaCola);
    }
}
