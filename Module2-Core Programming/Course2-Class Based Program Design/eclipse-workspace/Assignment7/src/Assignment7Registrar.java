import tester.*;

//=====================================================================
//CLASS COURSE
//Course is of type (name String, Instructor prof, IList<Student> los)
//Throw argument exception if no prof is provided
//interp. as a course taught by a single instructr and taken by a list of students

class Course {
	String name;
	Instructor prof;
	IList<Student> los;
	//Defining the constructor
	Course(String name, Instructor prof, IList<Student> los) {
		if (prof == null) {throw new IllegalArgumentException ("Can't have a course with a null value of Prof");}
		else {
			this.name = name;
			this.prof = prof;
			this.los = los;
		}
	}
	
	//TEMPLATE
	/* FIELDS
	 * ...this.name ...String
	 * ...this.prof ....Instructor
	 * ...this.los ....IList<Student>
	 * METHODS
	 */
	
}


//=====================================================================
//CLASS INSTRUCTOR
//Instructor is of type (name String, IList<Courses> loc)
//Constructed w/o taking any Course to teach
//Instructor for any Course should have that Course appear in the Instructor’s list of Courses.
//interp. as an instructor teaching multiple courses in a	collete

class Instructor {
	String name;
	IList<Course> loc;
	
	//Defining the constructor
	Instructor(String name, IList<Course> loc) {
		this.name = name;
		this.loc = loc;
	}
	//TEMPLATE
	/* FIELDS
	 * ...this.name ...String
	 * ...this.loc ....IList<Course>
	 * METHODS
	 * ...this.updateCourseList(IList<Course> loc) ...void
	 * ...this.dejavu(Student s) ....boolean
	 */
	
	//Updates the list of courses which are taken by a Prof
	void updateCourseList(IList<Course> loc) {this.loc = loc;}
	
	//Signature > Self, Student > boolean
	//Checks whether the given student is enrolled in more than 1 courses offered by the instructor
	boolean dejavu(Student s) {
		if (s.countEnrolled(this.loc) >= 2) {return true;} else {return false;}} // we ask for a wishlist for a helper function 
	
	
}


//=====================================================================
//CLASS STUDENT
//Student is of type (name String, id int, IList<Courses> loc)
//any Student who is enrolled in a Course should appear in the list of Students for that Course, 
//and the Course should likewise appear in the Student’s list of Courses.
//Interp.. as a student taking multiple courses in a college


class Student {
	String name;
	int id;
	IList<Course> loc ;
	
	//Defining the constructor
	Student (String name, int id, IList<Course> loc) {
		this.name = name;
		this.id = id;
		this.loc = loc;
	}
	
	//TEMPLATE
	/* FIELDS
	 * ...this.name ...String
	 * ...this.id ....int
	 * ...this.loc ....IList<Course	>
	 * METHODS
	 * ...this.updateCourseList(IList<Course> loc)  ... void
	 * ...this.enroll(Course c) ...void
	 * ...this.classmates(Student s) .... boolean
	 * ...this.isEnrolled(IList<Course> loc) ... boolean
	 * ...this.countEnrolled(IList <Course>) ... int
	 * METHODS from FIELDS
	 * 
	 */
	
	//Signature > Self, IList<Course> > void
	//Updates the list of courses which are taken by a student
	void updateCourseList(IList<Course> loc) {this.loc = loc;}
	
	//Signature > Self, Course > void
	//Updates the list of courses which are taken by a student
	void enroll(Course c)  {
		if (this.loc.contains(c)) {throw new IllegalArgumentException ("Student already enrolled");} 
		else {
			this.loc = this.loc.add(c); 
			c.los = c.los.add(this);}} // we add course to student list of course, and we add student to list of students for the course
	
	//Signature > Self, Student > boolean
	//Checks whether the given student is in any of the same class as this student
	//we create a helper funciton to check if the argument student is enrolled in any of the courses which the current student is enrolled
	boolean classmates(Student s) {return s.isEnrolled(this.loc);}
	
	//Signature > Self, IList<Course> > boolean
	//Checks whether the given student is enrolled in any of the given course list
	boolean isEnrolled(IList<Course> loc) {
		if (loc.isEmpty()) {return false;}
		else {return (loc.getFirst().los.contains(this)) || this.isEnrolled(loc.getRest());} // if not we call recursively with the rest of the list of courses
	}
	
	//Signature > Self, IList<Course> > int
	//Coounts the number of courses from the given course list in which the student is enrolled
	int countEnrolled(IList<Course> loc) {
		if (loc.isEmpty()) {return 0;}
		else {
			boolean checkifenrolled = this.loc.contains(loc.getFirst());
			if (checkifenrolled) {return 1+this.countEnrolled(loc.getRest());}
			else {return this.countEnrolled(loc.getRest());}
		}
	}
	
	
}


//=====================================================================
//CLASS EXAMPLE


class ExamplesRegistrar {
	ExamplesRegistrar () {}
	
	Course c1;
	Course c2;
	Course c3;
	Course c4; 
	Student s1;
	Student s2;
	Student s3;
	Student s4;
	Student s5;
	Instructor i1;
	Instructor i2;
	
	void initConditions() {
		i1 = new Instructor ("Gilbert Strang", null);
		i2 = new Instructor("David Malan", null);
		s1 = new Student ("Shubham", 1, null);
		s2 = new Student ("Sharvil", 2, null);
		s3 = new Student ("Survi", 3, null);
		s4 = new Student ("Mummy", 4, null);
		s5 = new Student ("Dady", 5, null);
		c1 = new Course ("Calculus", i1 , new ConsList<Student> (s1, new ConsList<Student>(s2, new ConsList<Student>(s3, new MtList<Student>() ))));
		c2 = new Course ("Probability", i1, new ConsList<Student> (s2, new ConsList<Student>(s3, new ConsList<Student>(s4, new MtList<Student>() ))));
		c3 = new Course ("CS50P", i2, new ConsList<Student> (s1, new ConsList<Student>(s5, new MtList<Student>() )));
		c4 = new Course ("CS50W", i2,new ConsList<Student> (s4, new ConsList<Student>(s5, new MtList<Student>() )));
		s1.updateCourseList(new ConsList<Course>(c1, new ConsList<Course>(c3, new MtList<Course>()))); 
		s2.updateCourseList(new ConsList<Course>(c1, new ConsList<Course>(c2, new MtList<Course>())))  ;
		s3.updateCourseList(new ConsList<Course>(c1, new ConsList<Course>(c2, new MtList<Course>()))) ;
		s4.updateCourseList( new ConsList<Course>(c2, new ConsList<Course>(c4, new MtList<Course>())));
		s5.updateCourseList( new ConsList<Course>(c3, new ConsList<Course>(c4, new MtList<Course>())));
		i1.updateCourseList( new ConsList<Course> (c1, new ConsList<Course>(c2, new MtList<Course>())));
		i2.updateCourseList ( new ConsList<Course> (c3, new ConsList<Course>(c4, new MtList<Course>())));
	}
	
	void testInitConditions(Tester t) {
		this.initConditions();
		t.checkExpect(i1.loc, new ConsList<Course> (c1, new ConsList<Course>(c2, new MtList<Course>()))) ;
		t.checkExpect(i2.loc, new ConsList<Course> (c3, new ConsList<Course>(c4, new MtList<Course>())));
		t.checkExpect(s1.loc, new ConsList<Course>(c1, new ConsList<Course>(c3, new MtList<Course>())));
		t.checkExpect(c1.los, new ConsList<Student> (s1, new ConsList<Student>(s2, new ConsList<Student>(s3, new MtList<Student>() ))));
	}
	
	//Testinf for enroll method
	void testEnroll(Tester t) {
		this.initConditions();
		
		t.checkException(new IllegalArgumentException("Student already enrolled"), s1, "enroll", c1);
		s1.enroll(c2);
		t.checkExpect(c2.los, new ConsList<Student> (s2, new ConsList<Student>(s3, new ConsList<Student>(s4, new ConsList<Student>(s1, new MtList<Student>() )))));
	}
	
	//Testing for Classmates method
	void testClassMates (Tester t) {
		this.initConditions();
		t.checkExpect(s1.classmates(s2), true);
		t.checkExpect(s2.classmates(s5), false);
		
	}
	
	//Testing for dejavu
	void testDejavu(Tester t) {
		this.initConditions();
		t.checkExpect(i1.dejavu(s1), false) ;
		t.checkExpect(i1.dejavu(s2), true) ;
	}
}



















