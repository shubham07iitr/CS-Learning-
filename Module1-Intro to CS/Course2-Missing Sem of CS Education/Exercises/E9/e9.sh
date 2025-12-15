##Problem ENTROPY
##Problem 1
##Suppose a password is chosen as a concatenation of four lower-case dictionary words, where each word is selected uniformly at random from a dictionary of size 100,000. An example of such a password is correcthorsebatterystaple. How many bits of entropy does this have?
#Solution
log2(100000^4) ~ 66

##Proble 2
## Consider an alternative scheme where a password is chosen as a sequence of 8 random alphanumeric characters (including both lower-case and upper-case letters). An example is rg8Ql34g. How many bits of entropy does this have?

##Solution
#Tottal possitibilites = (26+26+10)^8 = a
#Entropy = log2(a)

##Problem3 
##1 is a strongger password

##Problem4

##Solution
# nums1 = 100000**4
# nums2 = 62**8
# time1 = nums1/10000
# time2 = nums2/10000
# print(time1)
# print(time2)

##PROBLEM Cryptographic hash functions
##Download a Debian image from a mirror (e.g. from this Argentinean mirror). Cross-check the hash (e.g. using the sha256sum command) with the hash retrieved from the official Debian site (e.g. this file hosted at debian.org, if you’ve downloaded the linked file from the Argentinean mirror).
##Solution
##Could not download the files but could have used the command
<filename> | sha256sum ##t generage and match the hash


##Problem4
##  
##Solution
##First we encrypt
openssl aes-256-cbc -salt -in ./p3.txt -out p3encrypted.txt

##Then we decrypt
openssl aes-256-cbc -salt -in ./p3.txt -out p3encrypted.txt

##Problem ASYMMETRIC
##Already generated SSH keys earlier for remote machines so lite
