def main():
    filename = input("File name: ")
    extension(filename)


def extension(filename):
    if filename[-4:].strip().lower() == ".gif":
        print("image/gif")
    elif filename[-4:].strip().lower() in [".jpg", ".jpeg"]:
        print("image/jpeg")
    elif filename[-4:].strip().lower() == ".png":
        print("image/png")
    elif filename[-4:].strip().lower() == ".pdf":
        print("doc/pdf")
    elif filename[-4:].strip().lower() == ".txt":
        print("doc/txt")
    elif filename[-4:].strip().lower() == ".zip":
        print("folder/zip")
    else:
        print("application/octet-stream")

main()