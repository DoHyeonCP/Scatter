#-*- coding: utf-8 -*-
import requests
from django.shortcuts import render
from django.conf import settings
import xmltodict
import json
from django.http import JsonResponse,HttpResponse
from .serializer import Serializer
from .models import Hotspot

# Create your views here.
def get_seoul_hotspots(request): 
    # API resquest
    startpoint = 1 
    endpoint = 6 
    api_key = settings.SEOUL_API_KEY
    url = f'http://openapi.seoul.go.kr:8088/{api_key}/xml/citydata/{startpoint}/{endpoint}/강남 MICE 관광특구'
    
    response = requests.get(url)

    hotspots_data = response.content
    data_dict = xmltodict.parse(hotspots_data)  # convert xml to dict
    json_data = json.dumps(data_dict) #convert to json
    
    return HttpResponse(json_data, content_type = 'application/json')

    # hotspot_data_list = []
    # for hotspot_data in hotspots_data:
    #     AREA_NM = hotspot_data.find('AREA_NM').text
    #     LIVE_PPLTN_STTS = hotspot_data.find('LIVE_PPLTN_STTS').text
    #     AREA_CONGEST_LVL = hotspot_data.find('AREA_CONGEST_LVL').text
    #     AREA_CONGEST_MSG = hotspot_data.find('AREA_CONGEST_MSG').text
    #     AREA_PPLTN_MIN = hotspot_data.find('AREA_PPLTN_MIN').text
    #     AREA_PPLTN_MAX = hotspot_data.find('AREA_PPLTN_MAX').text
    
    #     hotspot = Hotspot.objects.create(
    #         AREA_NM = AREA_NM,
    #         LIVE_PPLTN_STTS = LIVE_PPLTN_STTS,
    #         AREA_CONGEST_LVL = AREA_CONGEST_LVL,
    #         AREA_CONGEST_MSG = AREA_CONGEST_MSG,
    #         AREA_PPLTN_MIN = AREA_PPLTN_MIN,
    #         AREA_PPLTN_MAX = AREA_PPLTN_MAX
    #     ) # it will delete, and I will Use class Serializer
    #     hotspot_data_list.append(hotspot)
    
    # response_data = {
    #     'status': 'success',
    #     'hotspots': hotspot_data_list
    # }