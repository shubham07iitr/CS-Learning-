def word_count(string):
    count_words = 0
    for i in string:
        if i == " ":
            count_words+=1
    return (count_words + 1)

def sentence_count(string):
    sentence_count = 0
    for i in string:
        if i in ["!", "?", "."]:
            sentence_count += 1
    return sentence_count 


sentence = input("Please enter a string for which you want to get the readability score: ")

word_count_actual = word_count(sentence)
sentence_count_actual = sentence_count(sentence)
letter_count_actual = len(sentence) - (word_count_actual -1) - sentence_count_actual

L = (letter_count_actual/word_count_actual)*100 ## Avg no of letters per 100 words
S  = (sentence_count_actual/word_count_actual)*100 ## Avg no of sentences per 100 words

read_index = 0.0588 * L - 0.296 * S - 15.8

if int(read_index) >= 16:
    print(f" Grade is 16+")
elif int(read_index) < 1:
    print("Before grade 1")
else:
    print(f"Grade is {int(read_index)}")

#print(f"Word Count: {word_count_actual}, Sentence Count: {sentence_count_actual}, Letter Coun: {letter_count_actual}")