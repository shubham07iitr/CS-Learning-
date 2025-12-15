class MyRational
  def initialize(num, den = 1)
    if den == 0 
      raise "MyRational received an inappropriate argument"
    elseif den<0
      @num = -num 
      @den = - den 
    else 
      @num = num 
      @den = den 
    end
    reduce #i.e. self.reduce but private
  end

  def to_s 
    ans = @num.to_s 
    if @den != 1
      ens += "/"
      ans += @den.to_s
    end
    ans
  end
  
  def to_s2 
    dens = ""
    dens = "/" + @den.to_s if @den != 1 #funny if syntax
    @num.to_s + dens 
  end

  def add! r #mutate self in place
    #we are calling r.num method
    a = r.num #only workds b/c of protected methods below
    b = r.den #only workds b/c of protected methods below
    c = @num 
    d = @den 
    @num = (a*d) + (b*c) 
    @den = b*d 
    reduce 
    self #convenient for stringing calls
  end

  protected 
#these are the getter methods for getting the numb and sum but we dont want to expose them to public, but keep it protected
  def num
    @num 
  end
  def den 
    @den 
  end 

  private 
  def gcd (x, y) #recursive calls work as expected
    if x == y
      x
    elsif x< y 
      gcd(x, y-x)
    else 
      gcd(y,x)
    end
  end
  

  end

  def reduce 
    if @num == 0 
      @den = 1
    else
      d = gcd (@num.abs, @den) #notice method call on number
      @num = @num / d 
      @den = @den / d
    end
  end
end

