import tester.*;

//BagelRecipe is of type (int flour, int water, int yeast, int salt, int malt)

class BagelRecipe {
	double flour;
	double water;
	double yeast;
	double salt;
	double malt;

	BagelRecipe (double flour, double water, double yeast, double salt, double malt) {
		if (water == flour && yeast == malt && (salt+yeast) == (1/20.0)*(flour)) {
			this.flour = flour;
			this.water = water;
			this.yeast = yeast;
			this.salt = salt;
			this.malt = malt;
		} else {throw new IllegalArgumentException ("Recipe mixture incorrect");}
	}

	BagelRecipe(double flour, double yeast) {
		this(flour, flour, yeast, (flour/20.0) - yeast, yeast);
	}

	BagelRecipe (double flour, double yeast, double salt) { //taking the ints as the volumes
		double flourWeight = flour * 4.25;          // cups to ounces
		double waterWeight = flourWeight;            // water weight = flour weight
		double yeastWeight = (yeast / 48.0) * 5.0;  // tsp to cups to ounces
		double saltWeight = (salt / 48.0) * 10.0;   // tsp to cups to ounces
		double maltWeight = yeastWeight;             // malt weight = yeast weight

		// Check perfect recipe constraints:
		// 1. flour weight = water weight (already ensured above)
		// 2. yeast weight = malt weight (already ensured above)  
		// 3. salt weight + yeast weight = (1/20) * flour weight
		if (saltWeight + yeastWeight == (1.0/20.0) * flourWeight) {
			this.flour = flourWeight;
			this.water = waterWeight;
			this.yeast = yeastWeight;
			this.malt = maltWeight;
			this.salt = saltWeight;
		}
		else {
			throw new IllegalArgumentException("Recipe mixture incorrect");
		}

	}
	
	//TEMPLATE
	/*
	 * FIELDS
	 *....this.flour ... double
	 *...this.yeast ... double
	 *...this.water ...double
	 *...this.malt .... double
	 *...this.salt ... double
	 * METHODS
	 * ...this.sameRecipe(BagelRecipe other)... boolean
	 * METHODS from ARGS
	 * ...other.sameRecipe(BagelRecipe someOther) ... boolean
	 */

	boolean sameRecipe(BagelRecipe that) {
		return this.flour - that.flour <= 0.0001
				&& this.water - that.water <= 0.0001
				&& this.yeast - that.yeast <= 0.0001
				&& this.malt - that.malt <= 0.0001
				&& this.salt - that.malt <= 0.0001;
	}
	
}

//=====================

class Assignment4Examples {
	Assignment4Examples() {}
	
	BagelRecipe valid1 = new BagelRecipe(40.0, 40.0, 1.0 ,1.0, 1.0);
	//BagelRecipe invalid1 = new BagelRecipe(40.0, 20.0, 1.0 ,1.0, 1.0);
	
	BagelRecipe valid2 = new BagelRecipe(40.0, 1.0);
//BagelRecipe invalid3 = new BagelRecipe(40, 1, 2);	
	BagelRecipe valid3 = new BagelRecipe(0.5, 0.1, 0.46);

	//Tesint the exceptions
	boolean testCons1 (Tester t) {
		return  t.checkConstructorException(new IllegalArgumentException("Recipe mixture incorrect"), "BagelRecipe", 40.0, 20.0, 1.0, 1.0, 1.0) &&
				t.checkConstructorException(new IllegalArgumentException("Recipe mixture incorrect"), "BagelRecipe", 1.0, 1.0, 1.0) ;
	}
	
	boolean testSameRecipe(Tester t) {
		return t.checkExpect(valid1.sameRecipe(valid2), true ) && 
				t.checkExpect(valid1.sameRecipe(new BagelRecipe (20.0, 0.5)), false);
		
	}
}





