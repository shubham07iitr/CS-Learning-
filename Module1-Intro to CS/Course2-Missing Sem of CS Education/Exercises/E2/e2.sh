##Problem: 
##Read man ls and write an ls command that lists files in the following manner
## Includes all files, including hidden files
##Sizes are listed in human readable format (e.g. 454M instead of 454279954)
##Files are ordered by recency
##Output is colorized

##Solution
ls -laht ## l for long, a for all, h for human readable, t for time sort

##Problem:
##Write bash functions marco and polo that do the following. Whenever you execute marco the current working directory should be saved in some manner, then when you execute polo, no matter what directory you are in, polo should cd you back to the directory where you executed marco. For ease of debugging you can write the code in a file marco.sh and (re)load the definitions to your shell by executing source marco.sh.
##Solutio
##Code for marco.sh
##Defining the code for marco
marco() {
    tempPath=$(pwd)
    echo "tempPath variable created"
}

##Defining the code for polo
polo() {
    cd $tempPath
    echo "Moved to other directory"
}

##Problem:
##Say you have a command that fails rarely. In order to debug it you need to capture its output but it can be time consuming to get a failure run. Write a bash script that runs the following script until it fails and captures its standard output and error streams to files and prints everything at the end. Bonus points if you can also report how many runs it took for the script to fail.
##The script is :
 n=$(( RANDOM % 100 ))

 if [[ n -eq 42 ]]; then
    echo "Something went wrong"
    >&2 echo "The error was using magic numbers"
    exit 1
 fi

 echo "Everything went according to plan"

##Solution:
##We creae a new script p3.sh with the following code
counter=1
for i in {1..100000}; do
     n=$(( RANDOM % 100 ))
    if [[ n -eq 42 ]]; then
        echo "Something went wrong" 
        echo "The error was using magic numbers" 1>&2
        echo "It took $counter attempts to fail" 1>&2
        exit 1
    else
        echo "All good, number was $n"
        ((counter+=1))
    fi
done

##and run it using the syntax 
bash p3.sh 1>correctp3.txt 2>errorp3.txt
##this way the stdout will go to correctp3.txt and error will go to error3.txt

##Problem:
##As we covered in the lecture find’s -exec can be very powerful for performing operations over the files we are searching for. However, what if we want to do something with all the files, like creating a zip file? As you have seen so far commands will take input from both arguments and STDIN. When piping commands, we are connecting STDOUT to STDIN, but some commands like tar take inputs from arguments. To bridge this disconnect there’s the xargs command which will execute a command using STDIN as arguments. For example ls | xargs rm will delete the files in the current directory.
##Your task is to write a command that recursively finds all HTML files in the folder and makes a zip with them. Note that your command should work even if the files have spaces (hint: check -d flag for xargs).

##Solution:
##We will use the below command:
find . -type f -name "*.html" -print0 | xargs -0 tar -czf archive.tar.gz

##Here -printf will print the output separated by NUL values like below, and xargs -0 will separate based on NULL values
##-czf means create zip file