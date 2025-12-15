##Problem1:
##Clone the repository for the class website.
##SOlution: 
git clone https://github.com/missing-semester/missing-semester
##Explore the version history by visualizing it as a graph.
##Solution
git log --graph --decorate --all 
##Who was the last person to modify README.md? (Hint: use git log with an argument).
 git log -p -1 README.md ##-p stands for patch and will show the difference in last vs latest commit
##What was the commit message associated with the last modification to the collections: line of _config.yml? (Hint: use git blame and git show).
##Solution
##First we check the commit hash for the modifictioation using
git blame _config.yml | grep collections: 
##Then we use the commit hash with git show
git show <hash>

##Problem 2
##One common mistake when learning Git is to commit large files that should not be managed by Git or adding sensitive information. Try adding a file to a repository, making some commits and then deleting that file from history (you may want to look at this).
##after making wrong commits we could use the below command
git filter-repo \
  --force \
  --path data/Sensex/UTM.dmg \
  --path data/Sensex/ubuntu-24.04.3-live-server-amd64.iso \
  --invert-paths
## -- force wll forcibly rewrite history, filter-repo is the command, we are removing two large files which were added to commit history UTM and ubuntu softwares
## - invert paths removes or rewrites history
##Once you do this, your hashes will change b/w local and remote machine, so you ll need to resync your local and remote like below:
git remote add origin https://github.com/shubham07iittr/Backtests.git
git push --force origin main    