

from django.urls import path
from . import views

urlpatterns = [
    path('create/', views.create_hero, name='create_hero'),
    path('count/', views.hero_count, name='hero_count'),
]






