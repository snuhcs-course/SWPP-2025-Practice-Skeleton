

from django.db import models

class Hero(models.Model):
    name = models.CharField(max_length=100)
    age = models.IntegerField(default=0)
    score = models.IntegerField(default=0)





