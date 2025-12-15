##Problem1:
##Find the number of words (in /usr/share/dict/words) that contain at least three as and don’t have a 's ending. What are the three most common last two letters of those words? sed’s y command, or the tr program, may help you with case insensitivity. How many of those two-letter combinations are there? And for a challenge: which combinations do not occur?

##Solution 1.1 - 3 most common last 2 chars
cat /usr/share/dict/words | sed -E 's/S/s/g' |  grep -E ".*s+.*s+.*s+[^s]$" | sed -E "s/.*([a-zA-Z][a-zA-Z])$/\1/" | sort | uniq -c | sort -nk1,1 | tail -n3
##Solution 1.2 - how many these 2 letter combinations 
cat /usr/share/dict/words | sed -E 's/S/s/g' |  grep -E ".*s+.*s+.*s+[^s]$" | sed -E "s/.*([a-zA-Z][a-zA-Z])$/\1/" | sort | uniq -c | sort -nk1,1 | wc -l   

##Problem2:
##To do in-place substitution it is quite tempting to do something like sed s/REGEX/SUBSTITUTION/ input.txt > input.txt. However this is a bad idea, why? Is this particular to sed? Use man sed to find out how to accomplish this.
##Solution2:
##We can't use sed directly because if we do that, anything which does not match will be eliminated
##To do this we need to use -i flag

##Problem 3
## Find your average, median, and max system boot time over the last ten boots. Use journalctl on Linux and log show on macOS, and look for log timestamps near the beginning and end of each boot



##Solution - Coultn get boot end time properly on macOS even after struggling

##Problem 4
##Find an online data set like this one, this one, or maybe one from here. Fetch it using curl and extract out just two columns of numerical data. If you’re fetching HTML data, pup might be helpful. For JSON data, try jq. Find the min and max of one column in a single command, and the difference of the sum of each column in another.
curl -s https://raw.githubusercontent.com/thousandoaks/Python4DS201/main/data/iris.json \
  | jq -r '.[] | "\(.sepalLength) \(.sepalWidth)"' \
  | awk '{s1+=$1; s2+=$2} END{print s1-s2}'