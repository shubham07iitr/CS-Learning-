from django.urls import path 
from . import views

urlpatterns = [
    path("", views.index, name = "index"),
    path("shubham", views.shubham, name = "shubham"),
    path("<str:name>", views.greet, name = "greet")
]
