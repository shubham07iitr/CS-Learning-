##Problem 1
##Most makefiles provide a target called clean. This isn’t intended to produce a file called clean, but instead to clean up any files that can be re-built by make. Think of it as a way to “undo” all of the build steps. Implement a clean target for the paper.pdf Makefile above. You will have to make the target phony. You may find the git ls-files subcommand useful. A number of other very common make targets are listed here.
##Solution
##The Makefile would look like
paper.pdf: paper.tex plot-data.png
	pdflatex paper.tex

plot-%.png: %.dat plot.py
	./plot.py -i $*.dat -o $@

.PHONY: clean
clean:
	rm -f paper.pdf plot-data.png

##If we want to get all untracked files by git, we could add
.PHONY: clean
clean:
	rm -f $(git ls-files --others --exclude-standard)

##git ls-files --others will get all files which are not tracked by git, such as paper.pdf or plot-data.png in our case - because these canbe gitignored, as these can be recreated

##Problem 3

##Solution3
##First we rename the pre-commit sample file to actual pre-commit
mv .git/hooks/pre-commit.sample .git/hooks/pre-commit
##Then we update this so that if make paper.pdf fails then it will have exit status of 1
make paper.pdf || exit 1



##Problem4
##Set up a simple auto-published page using GitHub Pages. Add a GitHub Action to the repository to run shellcheck on any shell files in that repository (here is one way to do it). Check that it works!
##Solution
##First setup a simple repo with a simple index.html and then push it htrough Github pages online
##then add the following in your repo in local , then we add a github action file as below:
mkdir -p .github/workflows
code .github/workflows/shellcheck.yml
##The folloiing code in shellcheck.yml
name: ShellCheck
on: [push, pull_request]

jobs:
  shellcheck:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Run ShellCheck
        uses: ludeeus/action-shellcheck@v2



##then add a simple bash script
code test.sh
#!/bin/sh
echo $UNQUOTED_VAR

##And once we push it to github, we can see it actually adds a wf in github actions tab in our worklow

