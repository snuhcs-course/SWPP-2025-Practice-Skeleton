

from django.contrib import admin
from django.urls import path
from django.urls import include
from hero import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('hero/', include('hero.urls'))
]




