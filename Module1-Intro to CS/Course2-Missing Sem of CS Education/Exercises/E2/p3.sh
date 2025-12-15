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