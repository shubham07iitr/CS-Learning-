
from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("login", views.login_view, name="login"),
    path("logout", views.logout_view, name="logout"),
    path("register", views.register, name="register"),

    ##Adding new paths
    path("newpost", views.newP, name="newpost") , ##to let user post a new post
    path("profile/<str:user>", views.profile , name = "profilePage"), ##Open the profile of a user
    path("follow", views.follow, name = "followPage"), ##Defining the post follow method
    path("following", views.following, name="followingPage"),
    path("editPost", views.edit, name = "editPost"),
    path("like", views.like, name = "likePost")

]
