from django.db import models

# Create your models here.



class Hotspot(models.Model):
    AREA_NM = models.CharField(max_length= 20)
    LIVE_PPLTN_STTS = models.IntegerField()
    AREA_CONGEST_LVL = models.CharField(max_length = 10)
    AREA_CONGEST_MSG = models.CharField(max_length=255)
    AREA_PPLTN_MIN = models.IntegerField()
    AREA_PPLTN_MAX = models.IntegerField()
    
    