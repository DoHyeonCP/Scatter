from django.db import models

# Create your models here.
class getGps(models.Model):
    id = models.CharField(max_length = 15, primary_key=True)
    latitude = models.FloatField()
    longitude = models.FloatField()
    created_at = models.DateTimeField(auto_now_add = True)