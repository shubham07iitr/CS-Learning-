
//Ancestor tree is one of 
/*
 * -- Unknown - if the ancestor is unknown for the person
 * -- Type Person which is compound data with fields mom (AT) and dad (AT)
 */
interface IAT {
	
}


// Unknown is an empty class interp. as of type ancestor tree
// interp. as the case when the ancestor of a person is not known
class Unknown implements IAT {
	Unknown () {}
}

//Person is of type (mom-AT, dad-AT)
// interp. as any normal person with a mom and dad
class People implements IAT{
	IAT mom;
	IAT dad;
	
	People (IAT mom, IAT dad) {
		this.mom = mom;
		this.dad = dad;
	}
	
}

class ExamplesIAT {
	ExamplesIAT () {}
	IAT dadi = new People (new Unknown(), new Unknown());
	IAT dada = new People (new Unknown(), new Unknown());
	IAT nani = new People (new Unknown(), new Unknown());
	IAT nana = new People (new Unknown(), new Unknown());
	IAT mummy = new People (nani, nana);
	IAT dad = new People (dadi, dada);
	IAT shubham = new People (mummy, dad);
}
