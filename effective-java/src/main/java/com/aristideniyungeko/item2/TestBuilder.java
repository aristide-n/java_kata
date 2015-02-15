package com.aristideniyungeko.item2;

/**
 * Use a builder that creates different objects depending on the value of the language field, and validates it.
 */
public class TestBuilder {
   public static void main(String[] args) {
      FrenchNutritionFacts frenchFacts =
         (FrenchNutritionFacts) new AbstractNutritionFacts.Builder(10,12).calories(16).language("french").build();
      System.out.println(frenchFacts.print());
      USNutritionFacts USFacts =
         (USNutritionFacts) new AbstractNutritionFacts.Builder(10,12).calories(16).language("english").build();
      System.out.println(USFacts.print());
      USNutritionFacts defaultFacts =
         (USNutritionFacts) new AbstractNutritionFacts.Builder(10,12).calories(16).build();
      defaultFacts.print();
      // illegalLanguage
      new AbstractNutritionFacts.Builder(10,12).language("illegal").build();
   }
}
