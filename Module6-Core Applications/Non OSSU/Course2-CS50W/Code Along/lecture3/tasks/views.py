from django.shortcuts import render, redirect 

##tasks = ["foo" , "bar", "baz"]
# Create your views here.
def index(request):
    if "tasks" not in request.session:
        request.session["tasks"] = []

    return render(request, "tasks/index.html", {
        "tasks": request.session["tasks"]
    })

## adding a task

def add(request):
    if request.method == 'POST':
        task = request.POST["task"]
        request.session["tasks"] += [task]
        return redirect("tasks:index")
    else:
        return render(request, "tasks/add.html")
