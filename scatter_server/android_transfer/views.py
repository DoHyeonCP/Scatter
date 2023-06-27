# Create your views here.
from django.shortcuts import render
import requests
from ..subscribe import location_subscribe
from django.http import HttpResponse
# Create your views here.

def get_gps(request):
    location_subscribe()
    
    return HttpResponse("success")