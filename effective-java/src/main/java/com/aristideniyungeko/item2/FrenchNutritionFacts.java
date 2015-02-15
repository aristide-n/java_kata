package com.aristideniyungeko.item2;

public class FrenchNutritionFacts extends AbstractNutritionFacts {
   protected FrenchNutritionFacts(final Builder builder) {
      super(builder);
   }

   @Override
   public String print() {
      return "Alimentation {" +
                "portion=" + getServingSize() +
                ", total=" + getServings() +
                ", calories=" + getCalories() +
                ", graisses=" + getFat() +
                ", proteines=" + getProtein() +
                '}';
   }
}
