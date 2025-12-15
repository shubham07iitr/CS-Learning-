from django.http import HttpResponse
from django.shortcuts import render

# Create your views here.
def index(request): 
    return render(request, "hello/index.html")

##Creating a new view
def shubham(request):
    return HttpResponse("Hello Shubham")

##Creating a greet function which is flexible to the name provided
def greet(request, name):
    return render(request, "hello/greet.html", {
        "name": name.capitalize()
    })


