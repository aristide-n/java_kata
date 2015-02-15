package com.aristideniyungeko.item2;

public abstract class AbstractNutritionFacts implements Label {
   private final int servingSize;
   private final int servings;
   private final int calories;
   private final int fat;
   private final int protein;
   private final int carbs;

   public static class Builder implements com.aristideniyungeko.item2.Builder<AbstractNutritionFacts> {
      // required fields
      private final int servingSize;
      private final int servings;

      // optional fields with defaults
      private int calories = 0;
      private int fat = 0;
      private int protein = 0;
      private int carbs = 0;
      private String language = "english";

      public Builder(int servingSize, int servings) {
         this.servingSize = servingSize;
         this.servings = servings;
      }

      public Builder calories(final int calories) {
         this.calories = calories;
         return this;
      }

      public Builder fat(final int fat) {
         this.fat = fat;
         return this;
      }

      public Builder protein(final int protein) {
         this.protein = protein;
         return this;
      }

      public Builder carbs(final int carbs) {
         this.carbs = carbs;
         return this;
      }

      public Builder language(final String language) {
         if (!(language.equals("french") || language.equals("english"))) {
            throw new IllegalArgumentException(language + " is not a supported language");
         }
         this.language = language;
         return this;
      }

      @Override
      public AbstractNutritionFacts build() {
         if(language.equals("english")) {
            return new USNutritionFacts(this);
         } else if (language.equals("french")) {
            return new FrenchNutritionFacts(this);
         }
         return null;
      }
   }

   protected int getServingSize() {
      return servingSize;
   }

   protected int getServings() {
      return servings;
   }

   protected int getCalories() {
      return calories;
   }

   protected int getFat() {
      return fat;
   }

   protected int getProtein() {
      return protein;
   }

   protected int getCarbs() {
      return carbs;
   }

   protected AbstractNutritionFacts(Builder builder) {
      servings = builder.servings;
      servingSize = builder.servingSize;
      fat = builder.fat;
      calories = builder.calories;
      carbs = builder.carbs;
      protein =builder.protein;
   }

   @Override
   public String print() {
      return "override me";
   }
}
