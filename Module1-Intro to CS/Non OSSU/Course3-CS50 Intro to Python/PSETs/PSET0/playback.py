def playback():
    text = input("Please enter something: ")
    return "...".join(text.split())

text = playback()
print(text)
    