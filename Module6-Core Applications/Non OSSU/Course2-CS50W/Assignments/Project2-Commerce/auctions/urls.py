from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("login", views.login_view, name="login"),
    path("logout", views.logout_view, name="logout"),
    path("register", views.register, name="register"),
    path("create", views.create_listing, name="create"),
    path("save", views.save_listing, name="save"),
    path("listing/<int:listing_id>", views.listing, name = "listingpage"),
    path("bid", views.bid, name = "bidpage"),
    path("comment", views.comment, name = "commentpage"),
    path("add_to_watchlist", views.addWatchlist, name="add_to_watchlist"),
    path("remove_from_watchlist", views.removeWatchlist, name="remove_from_watchlist"),
    path("watchlist", views.watch, name="watchlist"),
    path("closeAuction", views.close , name="closeAuction"),
    path("<str:category>", views.category, name="categorypage")


]
