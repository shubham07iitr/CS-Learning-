import json
from django.views.decorators.csrf import csrf_exempt
from django.contrib import messages
from django.contrib.auth import authenticate, login, logout
from django.db import IntegrityError
from django.http import HttpResponse, HttpResponseRedirect, JsonResponse
from django.shortcuts import render, redirect
from django.urls import reverse
from django.contrib.auth.decorators import login_required

from .models import User, Post, Like, Follower


def index(request):
    all_Posts = Post.objects.all().order_by('-updated_at')
    print(all_Posts)
    return render(request, "network/index.html", {
        "posts": all_Posts
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
            return render(request, "network/login.html", {
                "message": "Invalid username and/or password."
            })
    else:
        return render(request, "network/login.html")


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
            return render(request, "network/register.html", {
                "message": "Passwords must match."
            })

        # Attempt to create new user
        try:
            user = User.objects.create_user(username, email, password)
            user.save()
        except IntegrityError:
            return render(request, "network/register.html", {
                "message": "Username already taken."
            })
        login(request, user)
        return HttpResponseRedirect(reverse("index"))
    else:
        return render(request, "network/register.html")
    


##Defining the new post function 
@login_required
def newP(request):
    if request.method == "POST":
        try:
            user = request.user
            details = request.POST.get("description")
            if details and user:
                new_post = Post(posted_by = user, post_details = details)
                new_post.save()
                messages.success(request, "Successfully created a new post")
                return redirect('index')

            else:
                messages.error(request, "Sorry cannot leave your description empty, pls try again")
                return redirect('index')
        except:
            messages.error(request, "Sorry, due to unforeseen issues, your post could not be submitted, pls try again")
            return redirect('index')
        
    else:
        return redirect('index')
    

##Defining the profile page function
@login_required
def profile(request, user):
    if request.method == "GET":
        profileUser = User.objects.get(username = user)
        follower_count = Follower.objects.filter(user = profileUser).count()
        following_count = Follower.objects.filter(follower = profileUser).count()
        all_Posts = Post.objects.filter(posted_by=profileUser).order_by('-updated_at')
        if Follower.objects.filter(user = profileUser , follower = request.user).count() == 0:
            return render(request, "network/profile.html",  {
                "profile": profileUser,
                "following_count": following_count,
                "follower_count": follower_count,
                "posts": all_Posts,
                "following": "Follow"
            })
        else:
            return render(request, "network/profile.html",  {
                "profile": profileUser,
                "following_count": following_count,
                "follower_count": follower_count,
                "posts": all_Posts,
                "following": "Unfollow"
            })

    
##Defining the follow function
def follow(request):
    if request.method == "POST":
        action = request.POST.get("action") ##should be either "Follow" or "Unfollow"
        profile = User.objects.get(username = request.POST.get("follower"))
        follower = request.user
        if action == "Follow":
            follow_entry = Follower(user = profile, follower = follower)
            try:
                follow_entry.save()
                return redirect('profilePage',user= profile.username)
            except:
                messages.error(request, "Sorry could not complete your action, pls try again")
                return redirect('profilePage', user = profile)
        else:
            follow_entry = Follower.objects.get(user = profile, follower = follower)
            try:
                follow_entry.delete()
                return redirect('profilePage', user = profile.username)
            except:
                messages.error(request, "Sorry could not complete your action, pls try again")
                return redirect('profilePage', user = profile.username)
    



##Defining the folliwng function which renders the posts made by the users which the logged in user follows
@login_required
def following(request):
    # Get all users that the current user follows
    following_user_ids = Follower.objects.filter(follower=request.user).values_list('user', flat=True)
    
    # Get all posts from those users
    posts = Post.objects.filter(posted_by__in=following_user_ids).order_by('-created_at')
    
    return render(request, "network/index.html", {
        "posts": posts
    })

##Defining the function to edit a post
@login_required
@csrf_exempt
def edit(request):
    if request.method == "POST":
        try:
            # Parse JSON from request body
            data = json.loads(request.body)
            post_id = data.get('postID')
            new_content = data.get('newPost')
            # Get and update post
            post = Post.objects.get(id=post_id, posted_by=request.user)  # Security: verify ownership
            post.post_details = new_content
            post.is_edited = True
            post.save()
            return JsonResponse({'success': True, 'message': 'Post updated successfully'})
        
        except Post.DoesNotExist:
            return JsonResponse({'success': False, 'message': 'Post not found'}, status=404)
        
        except Exception as e:
            return JsonResponse({'success': False, 'message': 'Could not update post'}, status=400)
        

##Defining the likes function
@login_required
@csrf_exempt
def like(request):
    if request.method == "POST":
        try:
            data = json.loads(request.body)  # Fixed: parse JSON
            post = Post.objects.get(id=data.get("postID"))
            liked_by = User.objects.get(username=data.get("username"))
            action = data.get("action")
            
            if action == "Like":
                Like.objects.create(post=post, liked_by=liked_by)  # Use create()
                return JsonResponse({'success': True, 'message': 'You liked this post'})
            else:
                Like.objects.filter(post=post, liked_by=liked_by).delete()
                return JsonResponse({'success': True, 'message': 'You unliked this post'})
        except Exception as e:
            return JsonResponse({'success': False, 'message': "Could not complete the Like/Unlike action"}, status=400)