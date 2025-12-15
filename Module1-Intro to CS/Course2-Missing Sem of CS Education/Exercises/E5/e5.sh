##Problem1
##From what we have seen, we can use some ps aux | grep commands to get our jobs’ pids and then kill them, but there are better ways to do it. Start a sleep 10000 job in a terminal, background it with Ctrl-Z and continue its execution with bg. Now use pgrep to find its pid and pkill to kill it without ever typing the pid itself. (Hint: use the -af flags).

##Solution
sleep 10000 & ## to send a sleep process to bg
pgrep -af sleep 10000 ## to check the pid
pkill -af sleep 10000 ## to kill the process


##Problem 2
##Say you don’t want to start a process until another completes. How would you go about it? In this exercise, our limiting process will always be sleep 60 &. One way to achieve this is to use the wait command. Try launching the sleep command and having an ls wait until the background process finishes.
##However, this strategy will fail if we start in a different bash session, since wait only works for child processes. One feature we did not discuss in the notes is that the kill command’s exit status will be zero on success and nonzero otherwise. kill -0 does not send a signal but will give a nonzero exit status if the process does not exist. Write a bash function called pidwait that takes a pid and waits until the given process completes. You should use sleep to avoid wasting CPU unnecessarily.

##Solution 2
##Function param will be passed as argument like pidwait 5033
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

##PROBLEM ALIASING
##P1:Create an alias dc that resolves to cd for when you type it wrong.
alias dc=cd


##PROBLEM DOTFILES
##Solution
##First move your dotfiles to dotfiles folder
mkdir ~/dotfiles
mv ~/.zshrc ~/.vimrc ~/.bashrc ~/dotfiles

##NOw write a script for symliking all in one go
##Writing a script which will setup symlink for each of our dotfiles from dotfiles folder
setsymlink() {
    temppath="$HOME/dotfiles"
    allfiles=$(ls -A "$temppath")
    for i in $allfiles; do
        ln -s "$temppath/$i" "$HOME/.$i"
        echo "symlink established for $i, moving on"
    done
}
##And add a simple echo in zshrc to test
echo "zsh load sucess"


##Problem REMOTE MACHINE
##Generating the ssh key
ssh-keygen -a 100 -t ed25519
##Activating the ssh agent
 eval “$(ssh-agent -s)” 
 ssh-add ~/.ssh/id_ed25519

##copying file to vm 
ssh-copy-id shubham07iitr@192.168.64.2

