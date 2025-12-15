# University of Washington, Programming Languages, Homework 7, hw7.rb 
# (See also ML code)

# a little language for 2D geometry objects

# each subclass of GeometryExpression, including subclasses of GeometryValue,
#  needs to respond to messages preprocess_prog and eval_prog
#
# each subclass of GeometryValue additionally needs:
#   * shift
#   * intersect, which uses the double-dispatch pattern
#   * intersectPoint, intersectLine, and intersectVerticalLine for 
#       for being called by intersect of appropriate clases and doing
#       the correct intersection calculuation
#   * (We would need intersectNoPoints and intersectLineSegment, but these
#      are provided by GeometryValue and should not be overridden.)
#   *  intersectWithSegmentAsLineResult, which is used by 
#      intersectLineSegment as described in the assignment
#
# you can define other helper methods, but will not find much need to

# Note: geometry objects should be immutable: assign to fields only during
#       object construction

# Note: For eval_prog, represent environments as arrays of 2-element arrays
# as described in the assignment

#########################################SUPER CLASSES#################################################

##GeometryExpression is one of 
##--Intersect - is of of type (GeoExp e1, GeoExp e2) and represents the value generated when two GeoExp intersect
##--Let - is of of type (string s, GeoExp e1, GeoExp e2)- represents the let expression (* let s = e1 in e2 *)
##--Var - is of type (string s)- represents a variable s which is bound to some GeoExp in the env
##--Shift - is of type (Real dx, Real dy, GeoExp e) - represents shifting a geo exp by dx and dy values to the right

class GeometryExpression  
  # do *not* change this class definition
  Epsilon = 0.00001

  ##TEMPLATE
  ##FIELDS
  ##.....NO FIELDS....
  ##METHODS PUBLIC
  ##...self.eval_prog(env) ....Geometry Value 
  ##...self.preprocess_prog....Geometry Value

  ##METHODS DEFINTION

  ##METHOD PREPROCESS_PROG
  ##Signature > SELF > SELF
  ##Interp. as a geometry exp being evaluated to a geometry object, after evaluating each individual expression

  def preprocess_prog 
    self ##this is just rudimentary, will have to be done insubclasses properly
  end

  ##METHOD EVAL_PROG
  ##Signature>Self, ArrayList<ArrayList<String, GeoExp>> > GeometryValue
  ##Will evaluate the given expression in the environment it is being evaluated 

  def eval_prog env
    self  ##this is just rudimentary, will have to be done insubclasses properly
  end
end

##GeometryValue is one of 
##-- No Points - which represents a null value in our MUPL 
##-- Point is of type (Real x, Real y) representing a point in our interpreter where x and y represent x and y coordinate of the point
##-- Line is of type (Real - m, Real-b) where it represents and infinite line with slope m and y coordinate as b
##-- Vertical Line is of type (Real x) - which represents an infinite vertical line with x cordinate as x
##-- LineSegmnet is of type (Real x1, Real y1, Real x2, Real y2) which represents a finite line segmnet b/w 2 points

class GeometryValue 
  # do *not* change methods in this class definition
  # you can add methods if you wish

  ##TEMPLATE
  ##FIELDS
  #.....No Fields 
  #METHODS PRIVATE
  ##...self.real_close(real r1, real r2) .....boolean
  ##...self.real_close_point(real x1, real y1, real x2, real y2) .....boolean
  ##...self.two_points_to_line(real x1, real y1, real x2, real y2)....Geometry Value>Vertical Line or Line
  ##...self.eval_prog(env) ....Geometry Value 
  ##...self.preprocess_prog....Geometry Value
  ##...self.shift(Real dx, Real dy)....Geometry Value
  ##...self.intersect(GeometryExp other) ..... GeometryValue

  
  # some helper methods that may be generally useful
  def real_close(r1,r2) 
    (r1 - r2).abs < GeometryExpression::Epsilon
  end
  def real_close_point(x1,y1,x2,y2) 
    real_close(x1,x2) && real_close(y1,y2)
  end
  # two_points_to_line could return a Line or a VerticalLine
  def two_points_to_line(x1,y1,x2,y2) 
    if real_close(x1,x2)
      VerticalLine.new x1
    else
      m = (y2 - y1).to_f / (x2 - x1)
      b = y1 - m * x1
      Line.new(m,b)
    end
  end

  public
  # we put this in this class so all subclasses can inherit it:
  # the intersection of self with a NoPoints is a NoPoints object
  def intersectNoPoints np
    np # could also have NoPoints.new here instead
  end

  # we put this in this class so all subclasses can inhert it:
  # the intersection of self with a LineSegment is computed by
  # first intersecting with the line containing the segment and then
  # calling the result's intersectWithSegmentAsLineResult with the segment
  def intersectLineSegment seg
    line_result = intersect(two_points_to_line(seg.x1,seg.y1,seg.x2,seg.y2)) ##this could be point, or line or nopoint
    line_result.intersectWithSegmentAsLineResult seg ## we will call the right method
  end

  ##METHOD PREPROCESS_PROG
  ##Signature > SELF > SELF
  ##Interp. as a geometry exp being evaluated to a geometry object, after evaluating each individual expression

  def preprocess_prog 
    self ## values other than line segment will always return self 
  end

  ##METHOD EVAL_PROG
  ##Signature>Self, ArrayList<ArrayList<String, GeoExp>> > GeometryValue
  ##Will evaluate the given expression in the environment it is being evaluated 

  def eval_prog env
    self  ##This will be inhertied for all subclasses other than LineSegment
  end

  ##METHOD SHIFT
  ##Signature> Self, Real, Real > GeometryValue
  ##Will return self for the base class, and will be overridden across classes

  def shift(dx, dy)
    self
  end




end

#########################################SUBCLASS NO POINT#################################################

##No Points - which represents a null value in our MUPL 

class NoPoints < GeometryValue
  # do *not* change this class definition: everything is done for you
  # (although this is the easiest class, it shows what methods every subclass
  # of geometry values needs)
  # However, you *may* move methods from here to a superclass if you wish to
  # Note: no initialize method only because there is nothing it needs to do

  ##TEMPLATE
  ##FIELDS
  #.....No Fields 
  #METHODS PRIVATE
  ##...self.real_close(real r1, real r2) .....boolean
  ##...self.real_close_point(real x1, real y1, real x2, real y2) .....boolean
  ##...self.two_points_to_line(real x1, real y1, real x2, real y2)....Geometry Value>Vertical Line or Line
  ##...self.eval_prog(env) ....Geometry Value 
  ##...self.preprocess_prog....Geometry Value
  ##...self.shift(Real dx, Real dy)....Geometry Value...overridden
  ##...self.intersect(GeometryExp other) ..... GeometryValue....overridden
  


  ##def eval and preprocess will be inherited

  def shift(dx,dy)
    self # shifting no-points is no-points
  end
  def intersect other
    other.intersectNoPoints(self) # will be NoPoints but follow double-dispatch
  end
  def intersectPoint p
    self # intersection with point and no-points is no-points
  end
  def intersectLine line
    self # intersection with line and no-points is no-points
  end
  def intersectVerticalLine vline
    self # intersection with line and no-points is no-points
  end

  # if self is the intersection of (1) some shape s and (2) 
  # the line containing seg, then we return the intersection of the 
  # shape s and the seg.  seg is an instance of LineSegment
  def intersectWithSegmentAsLineResult seg
    self ##if the result of line of LS and NP is NP, then result of NP and LS will also be NP
  end

  def intersectNoPoints np
    self # could also have NoPoints.new here instead  
  end
end

#########################################SUBCLASS POINT#################################################

##Point is of type (Real x, Real y) representing a point in our interpreter where x and y represent x and y coordinate of the point

class Point < GeometryValue
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods

  # Note: You may want a private helper method like the local
  # helper function inbetween in the ML code

  ##TEMPLATE
  ##FIELDS
  #.....No Fields 
  #METHODS PRIVATE
  ##...self.real_close(real r1, real r2) .....boolean...inherited
  ##...self.real_close_point(real x1, real y1, real x2, real y2) .....boolean...inherited
  ##...self.two_points_to_line(real x1, real y1, real x2, real y2)....Geometry Value>Vertical Line or Line...inherited
  ##...self.eval_prog(env) ....Geometry Value ...inherited
  ##...self.preprocess_prog....Geometry Value ...inherited
  ##...self.shift(Real dx, Real dy)....Geometry Value...overridden
  ##...self.intersect(GeometryExp other) ..... GeometryValue....overridden

  attr_reader :x, :y
  def initialize(x,y)
    @x = x
    @y = y
  end

  ##def eval and preprocess will be inherited

  ##METHOD SHIFT
  ##Signature> Self, Real, Real > GeometryValue
  ##Will return a new point shifted by dx and dy values

  def shift(dx, dy)
    Point.new(@x+dx, @y+dy)
  end

  ##Defining intersect methods and submethods
  def intersect other
    other.intersectPoint(self) # will be NoPoints but follow double-dispatch
  end
  
  ##Logic here - if p is very close to self, then p, else no
  def intersectPoint p
    if self.real_close_point(self.x, self.y, p.x, p.y)
      p 
    else
      NoPoints.new 
    end
  end

  ##Logic - if y coordinate of point, is same as m*x+b coordinate 
  def intersectLine line
    if real_close(@y, line.m * @x + line.b)
      self 
    else
      NoPoints.new
    end  
  end

  ##logic, if x coordinate of point is same as x value of vartical line then return self else no points
  def intersectVerticalLine vline
    if real_close(self.x, vline.x)
      self
    else
      NoPoints.new 
    end  
  end

  ##def intersectLineSegment is inhertied

  # if self is the intersection of (1) some shape s and (2) 
  # the line containing seg, then we return the intersection of the 
  # shape s and the seg.  seg is an instance of LineSegment
  def intersectWithSegmentAsLineResult seg
    if inbetween(@x, seg.x1, seg.x2) && inbetween(@y, seg.y1, seg.y2)  ## if the 
      self 
    else
      NoPoints.new
    end
  end

  def intersectNoPoints np
    np # we return a nopoints, could also have NoPoints.new here instead  
  end

  ##Helper method to check if point is in bounds of line segment
  def inbetween(v, end1, end2)
    epsilon = 0.00001
    (end1 - epsilon <= v && v <= end2 + epsilon) ||
    (end2 - epsilon <= v && v <= end1 + epsilon)
  end

end

#########################################SUBCLASS LINE #################################################
##Line is of type (Real - m, Real-b) where it represents and infinite line with slope m and y coordinate as b

class Line < GeometryValue
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods
  
  ##TEMPLATE
  ##FIELDS
  #.....No Fields 
  #METHODS PRIVATE
  ##...self.real_close(real r1, real r2) .....boolean...inherited
  ##...self.real_close_point(real x1, real y1, real x2, real y2) .....boolean...inherited
  ##...self.two_points_to_line(real x1, real y1, real x2, real y2)....Geometry Value>Vertical Line or Line...inherited
  ##...self.eval_prog(env) ....Geometry Value ...inherited
  ##...self.preprocess_prog....Geometry Value ...inherited
  ##...self.shift(Real dx, Real dy)....Geometry Value...overridden
  ##...self.intersect(GeometryExp other) ..... GeometryValue....overridden
  
  ##def eval and preprocess will be inherited to return self
  
  attr_reader :m, :b 
  def initialize(m,b)
    @m = m
    @b = b
  end

  ##METHOD SHIFT
  ##Signature> Self, Real, Real > GeometryValue
  ##Will return a new line where slope is unchanged but slope will be changed to b + deltaY− m· deltaX

  def shift(dx, dy)
    Line.new(@m, @b+dy-@m*dx)
  end

  ##Defining intersect methods and submethods
  def intersect other
    other.intersectLine(self) # will be NoPoints but follow double-dispatch
  end
  
  def intersectPoint p
    p.intersectLine(self) ##we run the intersect on point 
  end

  ##Logic - if only m is same, then parallel then nopoints, it m and b same, then line, if intersecting, then point where they intersect
  def intersectLine line
    if self.real_close(self.m , line.m) && self.real_close(self.b , line.b)
      return self ##we have same lines
    elsif self.real_close(self.m , line.m) 
      return NoPoints.new ##parall lines dont intersect
    else
      x = (line.b - self.b) / (self.m - line.m)
		  y = self.m * x + self.b 
      Point.new(x,y)
    end  
  end

  ##A line will always intersect with a vertical line
  def intersectVerticalLine vline
    Point.new(vline.x, self.m*vline.x + self.b) 
  end
  # if self is the intersection of (1) some shape s and (2) 
  # the line containing seg, then we return the intersection of the 
  # shape s and the seg.  seg is an instance of LineSegment
  
  def intersectWithSegmentAsLineResult seg
    seg ##whenever intersection of line and line is another line, it means both lines coincide, so line segment will be a small part of the line
  end

  def intersectNoPoints np
    np # we return nopints, could also have NoPoints.new here instead  
  end

end

#########################################SUBCLASS VERTICAL LINE #################################################

##Vertical Line is of type (Real x) - which represents an infinite vertical line with x cordinate as x

class VerticalLine < GeometryValue
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods
  
  ##TEMPLATE
  ##FIELDS
  #.....No Fields 
  #METHODS PRIVATE
  ##...self.real_close(real r1, real r2) .....boolean...inherited
  ##...self.real_close_point(real x1, real y1, real x2, real y2) .....boolean...inherited
  ##...self.two_points_to_line(real x1, real y1, real x2, real y2)....Geometry Value>Vertical Line or Line...inherited
  ##...self.eval_prog(env) ....Geometry Value ...inherited
  ##...self.preprocess_prog....Geometry Value ...inherited
  ##...self.shift(Real dx, Real dy)....Geometry Value...overridden
  ##...self.intersect(GeometryExp other) ..... GeometryValue....overridden
    
  attr_reader :x
  def initialize x
    @x = x
  end

  ##def eval and preprocess will be inherited to return self
  
  ##METHOD SHIFT
  ##Signature> Self, Real, Real > GeometryValue
  ##Will return a new vertical line where x is shifted by x+dx

  def shift(dx, dy)
    VerticalLine.new(@x+dx)
  end

  ##Defining intersect methods and submethods
  def intersect other
    other.intersectVerticalLine(self) # will be NoPoints but follow double-dispatch
  end
  def intersectPoint p
    p.intersectVerticalLine(self) ##we play uno reverse
  end

  def intersectLine line
    line.intersectVerticalLine(self) ##we play uno reverse
  end

  ##if x cordinates of both lines are real close then same line, else noPoint
  def intersectVerticalLine vline
    if self.real_close(self.x, vline.x) 
      self 
    else 
      NoPoints.new 
    end
  end
  # if self is the intersection of (1) some shape s and (2) 
  # the line containing seg, then we return the intersection of the 
  # shape s and the seg.  seg is an instance of LineSegment
  def intersectWithSegmentAsLineResult seg
    seg ##whenever intersection of line and line is another line, it means both lines coincide, so line segment will be a small part of the line
  end

  def intersectNoPoints np
    np #we return nopoints,  could also have NoPoints.new here instead  
  end

end

#########################################SUBCLASS LINE SEGMENT #################################################
##LineSegmnet is of type (Real x1, Real y1, Real x2, Real y2) which represents a finite line segmnet b/w 2 points
class LineSegment < GeometryValue
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods
  # Note: This is the most difficult class.  In the sample solution,
  #  preprocess_prog is about 15 lines long and 
  # intersectWithSegmentAsLineResult is about 40 lines long
  
  ##TEMPLATE
  ##FIELDS
  #.....No Fields 
  #METHODS PRIVATE
  ##...self.real_close(real r1, real r2) .....boolean...inherited
  ##...self.real_close_point(real x1, real y1, real x2, real y2) .....boolean...inherited
  ##...self.two_points_to_line(real x1, real y1, real x2, real y2)....Geometry Value>Vertical Line or Line...inherited
  ##...self.eval_prog(env) ....Geometry Value ...overridden
  ##...self.preprocess_prog....Geometry Value ...overridden
  ##...self.shift(Real dx, Real dy)....Geometry Value...overridden
  ##...self.intersect(GeometryExp other) ..... GeometryValue....overridden
  
  
  attr_reader :x1, :y1, :x2, :y2
  def initialize (x1,y1,x2,y2)
    @x1 = x1
    @y1 = y1
    @x2 = x2
    @y2 = y2
  end

  ##METHOD PREPROCESS_PROG
  ##Signature > SELF > SELF
  ##Two key rules to be followed
  ##--If both x and y end points real close then give a point - done
  ##--If x1 > x2 reverse the end points both x and y
  ##--If x1 ~= x2, and y2 < y1 , then reverse the end points both x and y
  def preprocess_prog 
    if self.real_close_point(@x1, @y1, @x2, @y2) 
      Point.new(@x1, @y1)
    elsif @x2 < @x1 || (self.real_close(@x1, @x2) && @y2 < @y1)
      LineSegment.new(@x2, @y2, @x1, @y1)
    else
      self
    end
  end

  ##to be overriddent
  ##METHOD EVAL_PROG
  ##Signature>Self, ArrayList<ArrayList<String, GeoExp>> > GeometryValue
  ##Will evaluate the given expression in the environment it is being evaluated - return self 

  def eval_prog env
    self
  end

  ##METHOD SHIFT
  ##Signature> Self, Real, Real > GeometryValue
  ##Will return a new line segment where end points will be shifted by respective values

  def shift(dx, dy)
    LineSegment.new(@x1 + dx, @y1 + dy, @x2+dx, @y2+dy) 
  end

    ##Defining intersect methods and submethods
  def intersect other
    other.intersectLineSegment(self) # We follow double dispatch
  end

  def intersectPoint p
    p.intersectLineSegment(self) ##we play uno reverse
  end

  def intersectLine line
    line.intersectLineSegment(self) ## we play uno reverse
  end

  def intersectVerticalLine vline
    vline.intersectLineSegment(self) ##we play uno reverse
  end


  ##we will inherit the method for intersectlinesegment


  # if self is the intersection of (1) some shape s and (2) 
  # the line containing seg, then we return the intersection of the 
  # shape s and the seg.  seg is an instance of LineSegment
  def intersectWithSegmentAsLineResult seg
  # seg  -> original segment (the one whose supporting line we intersected)
  # self -> the other collinear segment (result of previous step)

    x1start, y1start, x1end, y1end = seg.x1, seg.y1, seg.x2, seg.y2
    x2start, y2start, x2end, y2end = @x1,   @y1,   @x2,   @y2

    if real_close(x1start, x1end)
      # Vertical case: order by starting y
      a = [x1start, y1start, x1end, y1end]
      b = [x2start, y2start, x2end, y2end]
      (a, b) = (y1start < y2start) ? [a, b] : [b, a]

      aXstart, aYstart, aXend, aYend = a
      bXstart, bYstart, bXend, bYend = b

      if real_close(aYend, bYstart)
        Point.new(aXend, aYend)                         # touching
      elsif aYend < bYstart
        NoPoints.new                                     # disjoint
      elsif aYend > bYend
        LineSegment.new(bXstart, bYstart, bXend, bYend)  # b inside a
      else
        LineSegment.new(bXstart, bYstart, aXend, aYend)  # overlap
      end
    else
      # Non-vertical case: order by starting x
      a = [x1start, y1start, x1end, y1end]
      b = [x2start, y2start, x2end, y2end]
      (a, b) = (x1start < x2start) ? [a, b] : [b, a]

      aXstart, aYstart, aXend, aYend = a
      bXstart, bYstart, bXend, bYend = b

      if real_close(aXend, bXstart)
        Point.new(aXend, aYend)                         # touching
      elsif aXend < bXstart
        NoPoints.new                                     # disjoint
      elsif aXend > bXend
        LineSegment.new(bXstart, bYstart, bXend, bYend)  # b inside a
      else
        LineSegment.new(bXstart, bYstart, aXend, aYend)  # overlap
      end
    end
  end
    
  

  def intersectNoPoints np
    np #we return no points could also have NoPoints.new here instead  
  end
end

# Note: there is no need for getter methods for the non-value classes

#########################################SUBCLASS INTERSECT < GEOEXP #################################################
##Intersect - is of of type (GeoExp e1, GeoExp e2) and represents the value generated when two GeoExp intersect

class Intersect < GeometryExpression
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods
  def initialize(e1,e2)
    @e1 = e1
    @e2 = e2
  end

  ##TEMPLATE
  ##FIELDS
  ##.....NO FIELDS....
  ##METHODS PUBLIC
  ##...self.eval_prog(env) ....Geometry Value  ...overidden
  ##...self.preprocess_prog....Geometry Value  ...overidden

  ##METHOD PREPROCESS_PROG
  ##Signature > SELF > SELF
  ##25 sub-cases to be worked on based on SML readings
  ##And we will use double dispatch by adding 5 methods each 5 sub-classes of values

  ## to be overidden
  def preprocess_prog 
    @e1.preprocess_prog.intersect(@e2.preprocess_prog)
  end

  ## to be overidden
  ##METHOD EVAL_PROG
  ##Signature>Self, ArrayList<ArrayList<String, GeoExp>> > GeometryValue
  ##Will evaluate the given expression in the environment it is being evaluated 

  def eval_prog env
    @e1.eval_prog(env).intersect(@e2.eval_prog(env))
  end

end

#########################################SUBCLASS LET < GEOEXP #################################################
##Let - is of of type (string s, GeoExp e1, GeoExp e2)- represents the let expression (* let s = e1 in e2 *)

class Let < GeometryExpression
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods
  # Note: Look at Var to guide how you implement Let
  def initialize(s,e1,e2)
    @s = s
    @e1 = e1
    @e2 = e2
  end

  ##TEMPLATE
  ##FIELDS
  ##.....NO FIELDS....
  ##METHODS PUBLIC
  ##...self.eval_prog(env) ....Geometry Value  ...overidden
  ##...self.preprocess_prog....Geometry Value  ...overidden

  ##METHOD PREPROCESS_PROG
  ##Signature > SELF > Geometry Value
  ##It will return e1, eval_prog would for let would actually add e1 bound to s in the environment passed

  ## to be overidden
  def preprocess_prog 
    Let.new(@s, @e1.preprocess_prog, @e2.preprocess_prog) ##will return the value of e1 processed to its value
  end

  ## to be overidden
  ##METHOD EVAL_PROG
  ##Signature>Self, ArrayList<ArrayList<String, GeoExp>> > GeometryValue
  ##It will evaluate e2 in an environment extended where s is bound to e1

  def eval_prog env
    v1 = @e1.eval_prog(env)
    @e2.eval_prog(env + [[@s, v1]]) ##we evaluate e1 in an environment extended by variable s bound to eval e1
  end

end

#########################################SUBCLASS VAR < GEOEXP #################################################

##--Var - is of type (string s)- represents a variable s which is bound to some GeoExp in the env

class Var < GeometryExpression
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods
  def initialize s
    @s = s
  end

  ##TEMPLATE
  ##FIELDS
  ##.....NO FIELDS....
  ##METHODS PUBLIC
  ##...self.eval_prog(env) ....Geometry Value  ...overidden
  ##...self.preprocess_prog....Geometry Value  ...inherited

  def eval_prog env # remember: do not change this method - inhertied
    pr = env.assoc @s
    raise "undefined variable" if pr.nil?
    pr[1]
  end
end

#########################################SUBCLASS SHIFT < GEOEXP #################################################
##--Shift - is of type (Real dx, Real dy, GeoExp e) - represents shifting a geo exp by dx and dy values to the right
class Shift < GeometryExpression
  # *add* methods to this class -- do *not* change given code and do not
  # override any methods
  def initialize(dx,dy,e)
    @dx = dx
    @dy = dy
    @e = e
  end

  ##TEMPLATE
  ##FIELDS
  ##.....NO FIELDS....
  ##METHODS PUBLIC
  ##...self.eval_prog(env) ....Geometry Value  ...overidden
  ##...self.preprocess_prog....Geometry Value  ...overidden

  ##METHOD PREPROCESS_PROG
  ##Signature > SELF > GeometryValue
  ##Interp. as a shift in the object by dx and dy coordinates

  ## We let dynamic dispatch figure out which method for shift is to be called
  def preprocess_prog 
    @e.preprocess_prog.shift(@dx, @dy)
  end

  ## to be overidden
  ##METHOD EVAL_PROG
  ##Signature>Self, ArrayList<ArrayList<String, GeoExp>> > GeometryValue
  ##Will evaluate the given expression in the environment it is being evaluated - which is inconsequential here because Ruby doesnt hv closures

  def eval_prog env
    @e.eval_prog(env).shift(@dx,@dy)
  end

end



