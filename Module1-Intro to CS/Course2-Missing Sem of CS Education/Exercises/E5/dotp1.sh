##Writing a script which will setup symlink for each of our dotfiles from dotfiles folder
setsymlink() {
    temppath="$HOME/dotfiles"
    allfiles=$(ls -A "$temppath")
    for i in $allfiles; do
        ln -s "$temppath/$i" "$HOME/.$i"
        echo "symlink established for $i, moving on"
    done
}