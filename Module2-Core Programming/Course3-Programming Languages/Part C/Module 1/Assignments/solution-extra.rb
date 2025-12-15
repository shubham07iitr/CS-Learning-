## Solution template for Guess The Word practice problem (section 7)

require_relative './section-7-provided'

class ExtendedGuessTheWordGame < GuessTheWordGame
  ## YOUR CODE HERE
end

class ExtendedSecretWord < SecretWord
  ## YOUR CODE HERE

  ##We want to change the pattern to include , and spaces visible to the second user
  ##We will call a new function to create pattern
  def initialize word 
    self.word = word 
    self.pattern  = self.createPattern 
    @guessedTillNow = []
  end

  def createPattern 
    loopCounter = 0
    returnPattern = ""
    charsToInclude = [",", " ", "?", ".", "!"]
    while loopCounter < self.word.length 
      char = self.word[loopCounter]
      if charsToInclude.include?(char)
        returnPattern += char
      else
        returnPattern += "-"
      end
      loopCounter += 1 
    end
    puts returnPattern
    returnPattern
  end

  ##Now we woudl want to override the game , so that capital and small letters guesses are case insesitive
  ##So if the word is Myname, and we guess m, then both M and m will be displayed accurately
  ##we do it by first trying for lowercase and then trying for uppercase
  ##we do this by using a helper method
    def guess_letter! letter
      self.guess_letter_helper(letter.upcase)
      self.guess_letter_helper(letter.downcase)
  end

  def guess_letter_helper letter 
    if @guessedTillNow.include?(letter)
      return true
    end
    @guessedTillNow.push(letter)
    found = self.word.index letter
    if found
      start = 0
      while ix = self.word.index(letter, start)
        self.pattern[ix] = self.word[ix]
        start = ix + 1
      end
    end
    found
  end



end

## Change to `false` to run the original game
if true
  ExtendedGuessTheWordGame.new(ExtendedSecretWord).play
else
  GuessTheWordGame.new(SecretWord).play
end
