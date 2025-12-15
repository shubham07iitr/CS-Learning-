from django.http import HttpResponse
from django.shortcuts import render
import markdown2
from . import util
import random

def index(request):
    return render(request, "encyclopedia/index.html", {
        "entries": util.list_entries()
    })



##Defining the entry method which will render the markdown pages we have available
def entry(request, entryname):
    if util.get_entry(entryname) is not None:
        return render(request, "encyclopedia/entry.html", {
            "title": entryname,
            "entryname": markdown2.markdown(util.get_entry(entryname))
        })
    else:
        return render(request, "encyclopedia/entry.html", {
            "title": entryname,
            "entryname": "Requested page does not exist"
        })
    
##Defining the create page method which will render the new webpage to enter our new CSS

def create(request):
    return render(request, "encyclopedia/create.html", {
        "title" : None, 
        "content" : None
    })


##Defining the savepage method

def save(request):
    
    if request.method == "POST":
        title = request.POST.get("title")
        content = request.POST.get("content")
        print(title)
        print(content)
        if title in util.list_entries():
            return render(request, "encyclopedia/index.html", {
                "entries": ["This page already exists"]
            })
        else:
            util.save_entry(title, content)
            return render(request, "encyclopedia/entry.html", {
                "title": title,
                "entryname": markdown2.markdown(content)
            })

##Defining the random page method

def random_request(request):
    entryname = random.choice(util.list_entries())
    print(entryname)
    return render(request, "encyclopedia/entry.html", {
        "title": entryname,
        "entryname": markdown2.markdown(util.get_entry(entryname))
        })

##Defining the search method

def search(request):
    if request.method == "POST":
        query = request.POST.get("q")
        if query in util.list_entries():
            return render(request, "encyclopedia/entry.html", {
                "title": query,
                "entryname": markdown2.markdown(util.get_entry(query))
            })
        
        else:
            matches = []
            entries = util.list_entries()             
            for i in entries:
                if query in i:
                    matches.append(i)
            if len(matches) == 0:        
                return render(request, "encyclopedia/entry.html", {
                    "title": query,
                    "entryname": "Requested page does not exist"
                    })
            else:
                return render(request, "encyclopedia/index.html", {
                    "entries": matches
                    })
            
##Defining the edit page/shoud be the same as create page, with values pre-filled:
def edit(request):
    title = request.GET.get("title")
    content = util.get_entry(title=title)
    return render(request, "encyclopedia/create.html", {
        "title": title ,
        "content": content
    })
    
##Defining a new function for saving through edit operation
def saveedit(request):
    if request.method == "POST":
        title = request.POST.get("title")
        content = request.POST.get("content")
        util.save_entry(title, content)
        return render(request, "encyclopedia/entry.html", {
            "title": title,
            "entryname": markdown2.markdown(content)
            })