class Point 
  attr_accessor :x, :y #defines methods x,y,x=,y=

  def initialize(a,b) 
    @x = a 
    @y = b 
  end
  def distFromOrigin
    Math.sqrt(@x*@x + @y*@y)  #uses instance variables 
  end
  def distFromOrigin2 
    Math.sqrt(x*x + y*y) #uses getter methods
  end
end

class ThreePoint < Point 
  attr_accessor :z 
  def initialize(x,y,z) 
    super (x,y)
    @z = z
  end
  def distFromOrigin
    d = super  #here d stores the distance of origin for x and y coordinates only using the super method of distance to origin
    Math.sqrt (d*d + @z * @z)
  end

  def distFromOrigin2 
    d = super 
    Math.sqrt(d*d + z*z)
  end
end

class PolarPoint < Point 
  #Bynot calling super constructor , no x and y instance variables
  # In Java/C#/Small talk would have just usused x,y fields 
  def initialize(r,theta)
    @r = r 
    @theta = theta 
  end
end 
def x 
  @r * Math.cos(@theta)
end 
def y 
  @r *Math.sin(@theta)
end
def x= a 
  b = y #avoids multiple calls to the y method 
  @theta = Math.atan(b/a) 
  @r = Math.sqrt(a*a + b*b) 
  self
end
def y = b 
  a = y # avoid multipple calls to y method 
  @theta = Math.atan(b/a) 
  @r = Math.sqrt(a*a + b*b) 
  self
end
def distFromOrigin #must override 
  @r 
end
#inherited distFromOrigin2 already works !
end

