from django.contrib.auth.models import AbstractUser
from django.db import models


class User(AbstractUser):
    pass



class Post(models.Model):
    posted_by = models.ForeignKey(User, on_delete=models.CASCADE, related_name="poster") ##relates to the user table/object
    post_details = models.TextField() ##textfield for inlmiited length
    created_at = models.DateTimeField(auto_now_add=True)  # Set once
    updated_at = models.DateTimeField(auto_now=True)      # Updates every save
    is_edited = models.BooleanField(default=False)

    def __str__(self):
        return f"Post made by {self.posted_by} on {self.created_at} and it says {self.post_details}"
    
class Like(models.Model):
    post = models.ForeignKey(Post, on_delete=models.CASCADE, related_name="likes")
    liked_by = models.ForeignKey(User, on_delete=models.CASCADE, related_name="user_likes")
    like_datetime = models.DateTimeField(auto_now_add=True)  # Set once

    def __str__(self):
        return f"On {self.like_datetime} , {self.liked_by} liked the post {self.post}"

class Follower(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="original_user")
    follower = models.ForeignKey(User, on_delete=models.CASCADE, related_name="followers")
    follow_datetime = models.DateTimeField(auto_now_add=True)  # Set once


    def __str__(self):
        return f"On {self.follow_datetime} , {self.follower} started following {self.user}"

