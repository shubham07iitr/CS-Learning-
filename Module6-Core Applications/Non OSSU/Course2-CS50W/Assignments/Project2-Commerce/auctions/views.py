from django.contrib.auth import authenticate, login, logout
from django.db import IntegrityError
from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.urls import reverse

from .models import User, Listing, Comment, Bid, CATEGORY_CHOICES

categories = []
for i in CATEGORY_CHOICES:
    categories.append(i[1])

def index(request):
    listings = Listing.objects.all()
    return render(request, "auctions/index.html", {
        "Listing": listings,
        "cats": CATEGORY_CHOICES
    })


def login_view(request):
    if request.method == "POST":

        # Attempt to sign user in
        username = request.POST["username"]
        password = request.POST["password"]
        user = authenticate(request, username=username, password=password)

        # Check if authentication successful
        if user is not None:
            login(request, user)
            return HttpResponseRedirect(reverse("index"))
        else:
            return render(request, "auctions/login.html", {
                "message": "Invalid username and/or password.",
                "cats": CATEGORY_CHOICES
            })
    else:
        return render(request, "auctions/login.html", {
            "cats": CATEGORY_CHOICES
        })


def logout_view(request):
    logout(request)
    return HttpResponseRedirect(reverse("index"))


def register(request):
    if request.method == "POST":
        username = request.POST["username"]
        email = request.POST["email"]

        # Ensure password matches confirmation
        password = request.POST["password"]
        confirmation = request.POST["confirmation"]
        if password != confirmation:
            return render(request, "auctions/register.html", {
                "message": "Passwords must match.",
                "cats": CATEGORY_CHOICES
            })

        # Attempt to create new user
        try:
            user = User.objects.create_user(username, email, password)
            user.save()
        except IntegrityError:
            return render(request, "auctions/register.html", {
                "message": "Username already taken.",
                "cats": CATEGORY_CHOICES
            })
        login(request, user)
        return HttpResponseRedirect(reverse("index"))
    else:
        return render(request, "auctions/register.html", {
            "cats": CATEGORY_CHOICES
        })


##Defining the create listing page
##Page should be able to share the form for creating a listing 

def create_listing(request):
    return render(request, "auctions/create.html", {
        "categories": categories,
        "cats": CATEGORY_CHOICES
    })
    

##Defining the save listing page
def save_listing(request):
    if request.method == "POST":
        title = request.POST.get("title")
        description = request.POST.get("description")
        min_bid = request.POST.get("min_bid")
        listed_by = request.user
        category = request.POST.get("category")
        print(category)
        image_url = request.POST.get("image_url")
        l = Listing(title= title, description = description, min_bid = min_bid, listed_by = listed_by, category = category, image_url = image_url)
        print(l)
        try:
            l.save()
            print(l)
            return render(request, "auctions/create.html", {
                "message": "Your listing was succesful, pls go to home page to see your listing",
                "cats": CATEGORY_CHOICES
            })
        except:
            return render(request, "auctions/create.html", {
                "message": "Your listing was not succesful, pls try again",
                "cats": CATEGORY_CHOICES
            })

##Defining the individual listing page
def listing(request, listing_id):
    count_bids = Bid.objects.filter(listing=Listing.objects.get(id=listing_id)).count()

    if request.method == "GET":
        listing_temp = Listing.objects.get(id = listing_id)
        all_comments = Comment.objects.filter(listing = listing_temp)
        print(listing_temp)
        return render(request, "auctions/listing.html" , {
            "listing": listing_temp,
            "count_bids": count_bids,
            "comments": all_comments,
            "cats": CATEGORY_CHOICES

        })
    
##Defining the bid POST action

def bid(request):
    if request.method == "POST":
        listing_temp = Listing.objects.get(id = request.POST.get("listingid"))
        count_bids = Bid.objects.filter(listing=listing_temp).count()
        all_comments = Comment.objects.filter(listing = listing_temp)
        bid_user = request.user
        bid_value = float(request.POST.get("bid_value"))
        print(bid_value)
        try:
            if bid_value < listing_temp.min_bid:
                return render(request, "auctions/listing.html", {
                    "message" : "Sorry, you can't place bid lower than min bid",
                    "listing": listing_temp,
                    "count_bids": count_bids,
                    "comments": all_comments,
                    "cats": CATEGORY_CHOICES
                })
            
            if listing_temp.current_price and bid_value <= listing_temp.current_price:
                return render(request, "auctions/listing.html", {
                    "message": "Sorry, your bid must be higher than the current bid",
                    "listing": listing_temp,
                    "count_bids": count_bids,
                    "comments": all_comments,
                    "cats": CATEGORY_CHOICES
                })

            if bid_user == listing_temp.listed_by:
                return render(request, "auctions/listing.html", {
                    "message" : "Sorry, you can't place a bid on your listing",
                    "listing": listing_temp,
                    "count_bids": count_bids,
                    "comments": all_comments,
                    "cats": CATEGORY_CHOICES
                })
            
            bid, created = Bid.objects.update_or_create(
                            listing=listing_temp,
                            bid_user=bid_user,
                            defaults={'bid_value': bid_value}
                        )
                        
            # Update listing current price
            listing_temp.current_price = bid_value
            listing_temp.save()
            
            message = "Your bid has been submitted" if created else "Your bid has been updated"
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "message": message,
                "count_bids": count_bids,
                "comments": all_comments,
                "cats": CATEGORY_CHOICES
            })
        except:
                return render(request, "auctions/listing.html", {
                    "message" : "We couldn't process your bid, pls try again",
                    "listing": listing_temp,
                    "count_bids": count_bids,
                    "comments": all_comments,
                    "cats": CATEGORY_CHOICES
                    })
        

##Defining the comment view

def comment(request):
    if request.method == "POST":
        comment_user = request.user 
        comment_details = request.POST.get("new_comment")
        listing_temp = Listing.objects.get(id = request.POST.get("listingid"))
        count_bids = Bid.objects.filter(listing=listing_temp).count()
        new_comment = Comment(listing = listing_temp, comment_user = comment_user, comment = comment_details)
        if not comment_details or not comment_details.strip():
            all_comments = Comment.objects.filter(listing=listing_temp)
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "count_bids": count_bids,
                "comments": all_comments,
                "message_comment": "Comment cannot be empty",
                "cats": CATEGORY_CHOICES
            })
        else:
            try:
                new_comment.save()
                all_comments = Comment.objects.filter(listing = listing_temp)
                return render(request, "auctions/listing.html", {
                    "listing": listing_temp,
                    "count_bids": count_bids,
                    "comments": all_comments,
                    "message_comment": "Comment succesfully submitted",
                    "cats": CATEGORY_CHOICES
                })
            
            except:
                all_comments = Comment.objects.filter(listing = listing_temp)
                return render(request, "auctions/listing.html", {
                    "listing": listing_temp,
                    "count_bids": count_bids,
                    "comments": all_comments,
                    "message_comment": "Could not submit comment, pls try again",
                    "cats": CATEGORY_CHOICES
                })



##Defining the filter for category

def category(request, category):
    listings = Listing.objects.filter(category = category)
    return render(request, "auctions/index.html", {
        "Listing": listings,
        "cats": CATEGORY_CHOICES
    })


##Defining the different watchlist views:

def addWatchlist(request):
    print(request.POST.get('listing_ID'))
    if request.method == "POST":
        print(f"Listing ID is {request.POST.get('listing_ID')}")
        listing_temp = Listing.objects.get(id = request.POST.get("listing_ID"))
        print(listing_temp)
        count_bids = Bid.objects.filter(listing=listing_temp).count()
        all_comments = Comment.objects.filter(listing=listing_temp)
        try:
            request.user.watchlist.add(listing_temp)
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "count_bids": count_bids,
                "comments": all_comments,
                "cats": CATEGORY_CHOICES,
                "message_watchlist": "You have succesfully added this listing to your watchlist"
                })
        except:
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "count_bids": count_bids,
                "comments": all_comments,
                "cats": CATEGORY_CHOICES,
                "message_watchlist": "Sorry we could not add this to your watchlist, pls try again"
                })



def removeWatchlist(request):
    if request.method == "POST":
        listing_temp = Listing.objects.get(id = request.POST.get("listing_ID"))
        count_bids = Bid.objects.filter(listing=listing_temp).count()
        all_comments = Comment.objects.filter(listing=listing_temp)
        try:
            request.user.watchlist.remove(listing_temp)
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "count_bids": count_bids,
                "comments": all_comments,
                "cats": CATEGORY_CHOICES,
                "message_watchlist": "You have succesfully removed this listing to your watchlist"
                })
        except:
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "count_bids": count_bids,
                "comments": all_comments,
                "cats": CATEGORY_CHOICES,
                "message_watchlist": "Sorry we could not remove this from your watchlist, pls try again"
                })


def watch(request):
    listings = request.user.watchlist.all()
    return render(request, "auctions/index.html", {
        "Listing": listings,
        "cats": CATEGORY_CHOICES
    })


def close(request):
     if request.method == "POST":
        listing_temp = Listing.objects.get(id = request.POST.get("listing_ID"))
        user_temp = request.user
        count_bids = Bid.objects.filter(listing=listing_temp).count()
        all_comments = Comment.objects.filter(listing=listing_temp)
        try:
            listing_temp.winner = user_temp
            listing_temp.status = "CLOSED"
            listing_temp.save()  
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "count_bids": count_bids,
                "comments": all_comments,
                "cats": CATEGORY_CHOICES,
                "message_closeAuction": "You have succesfully closed the auction"
                })
        except:
            return render(request, "auctions/listing.html", {
                "listing": listing_temp,
                "count_bids": count_bids,
                "comments": all_comments,
                "cats": CATEGORY_CHOICES,
                "message_closeAuction": "You could not close the auction, pls try again"
                })
            


    
