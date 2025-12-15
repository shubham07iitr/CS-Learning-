from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("wiki/<str:entryname>", views.entry, name = "wikiEntry"),
    path("createpage", views.create, name = "createpage"),
    path("savepage", views.save, name="savepage"),
    path("randompage", views.random_request, name="randompage"),
    path("searchpage", views.search, name = "searchpage"),
    path("editpage", views.edit, name = "editpage"),
    path("saveeditpage", views.saveedit, name = "saveeditpage"),
]
