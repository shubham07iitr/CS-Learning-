##Function which will wait for a given process to finish before running the ls command
pidwait() {
    pid="$1"
    for i in {1..10000}; do
        kill -0 "$pid" 2>/dev/null
        if [[ $? -ne 0 ]]; then
            ls
            break
        else
            sleep 5
        fi
    done
}