from django.contrib.auth.models import AbstractUser
from django.db import models

##Defining different categories for listings 
CATEGORY_CHOICES = [
    ('FASHION', 'Fashion'),
    ('HOMEUSE', 'Home Use'),
    ('ELECTRONICS', 'Electronics'),
    ('TOYS', 'Toys'),
    ('SPORTS', 'Sports'),
    ('BOOKS', 'Books'),
]

STATUS = [
    ('ACTIVE', 'Active'),
    ('CLOSED', 'Closed')
]



class User(AbstractUser):
    watchlist = models.ManyToManyField('Listing', blank=True, related_name="watchers")


##Defining the model for Auction Listings
class Listing(models.Model):
    title = models.CharField(max_length=512) 
    description = models.TextField() ##textfield for inlmiited length
    min_bid = models.DecimalField(max_digits=10, decimal_places=2)
    current_price = models.DecimalField(max_digits=10, decimal_places=2, null = True, blank = True)
    listed_by = models.ForeignKey(User, on_delete=models.CASCADE, related_name="listers") ##relates to the user table/object
    category = models.CharField(max_length=20, choices= CATEGORY_CHOICES, default='FASHION') ##can only take values from the given choices
    image_url = models.URLField(max_length=2048, blank=True, null=True)
    created_at = models.DateTimeField(auto_now_add=True)  # Set once
    updated_at = models.DateTimeField(auto_now=True)      # Updates every save
    status = models.CharField(max_length=20, choices=STATUS, default='ACTIVE')
    winner = models.ForeignKey(User, on_delete=models.CASCADE, related_name="winner", blank=True, null=True)

    def __str__(self):
        return f"title: {self.title} , details: {self.description} , min_bid: {self.min_bid}, listed_by: {self.listed_by}, category: {self.category}"


##Defining the model for bids

class Bid(models.Model):
    listing =  models.ForeignKey(Listing, on_delete=models.CASCADE, related_name="bidListings")
    bid_user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="bidder")
    bid_value = models.DecimalField(max_digits=10, decimal_places=2)
    bid_created_date = models.DateTimeField(auto_now_add=True)
    bid_updated_date = models.DateTimeField(auto_now=True, null = True  )
    

    def __str__(self):
        return f"listing: {self.listing}, bid_user: {self.bid_user}, bid_value: {self.bid_value}"


##Defining the model for Comments

class Comment(models.Model):
    listing = models.ForeignKey(Listing, on_delete=models.CASCADE, related_name="commentListings")
    comment_user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="commenter")
    comment = models.TextField() ##textfield for inlmiited length
    bid_created_date = models.DateTimeField(auto_now_add=True)
    bid_updated_date = models.DateTimeField(auto_now=True, null=True)

    def __str__(self):
        return f"listing: {self.listing}, comment_user: {self.comment_user}, bid_value: {self.comment}"