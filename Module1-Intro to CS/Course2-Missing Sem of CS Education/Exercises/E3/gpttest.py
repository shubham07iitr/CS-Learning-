def add(a,b):
	return a+b

def greet(name):
	return ("hello," + name)

for i in range(3):
	print(f"[{i+1}]  {greet("vim")}")

print("DONE")
print(f"sum={add(2,5)}")

def divide(a,b):
	if b==0:
		return None
	return a/b

def multiply(a,b):
	return a*b

def subtract(a,b):
	return a-b

def divide(a,b):
	if b==0:
		return None
	return a/b

def main():
	numbers =  [10,5,0]
	for n in numbers:
		print("add:", add(n,2))
		print("sub:", add(n,2))
		print("mul:", add(n,2))
		print("div:", add(n,2))
		print("-----")

main()



		
