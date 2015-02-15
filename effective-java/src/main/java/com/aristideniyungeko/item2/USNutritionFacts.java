package com.aristideniyungeko.item2;

public class USNutritionFacts extends AbstractNutritionFacts implements Label {
   protected USNutritionFacts(final Builder builder) {
      super(builder);
   }

   @Override
   public String print() {
      return "NutritionFacts{" +
                "servingSize=" + getServingSize() +
                ", servings=" + getServings() +
                ", calories=" + getCalories() +
                ", fat=" + getFat() +
                ", protein=" + getProtein() +
                ", carbs=" + getCarbs() +
                '}';
   }
}
