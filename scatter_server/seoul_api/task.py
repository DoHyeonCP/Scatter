import requests
import xml.etree.ElementTree as ET
from django.conf import settings
from celery import shared_task # 비동기 작업처리를 위한 라이브러리
# from .models import Hotspot

# Use, later(auto callback function)
# @shared_task
# def update_seoul_hotspots():
#     startpoint = 1 # 스타트 포인트, 임의로 지정했음 수정해야함
#     endpoint = 6 # 스타트 포인트, 임의로 지정했음 수정해야함
#     api_key = settings.SEOUL_API_KEY
#     url =  f'https://api.seoul.go.kr:8088/{api_key}/json/citydata/{startpoint}/{endpoint}'

#     response = requests.get(url)
#     hotspots_data = response.content
    
#     root = ET.fromstring(hotspots_data)
    
#     # for hotspot_data in root.iter('hotspot'):
#     AREA_NM = root.find('CITYDATA/AREA_NM').text
#     LIVE_PPLTN_STTS = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS').text
#     AREA_CONGEST_LVL = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_CONGEST_LVL').text
#     AREA_CONGEST_MSG = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_CONGEST_MSG').text
#     AREA_PPLTN_MIN = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_PPLTN_MIN').text
#     AREA_PPLTN_MAX = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_PPLTN_MAX').text
#     MALE_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/MALE_PPLTN_RATE').text
#     FEMALE_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/FEMALE_PPLTN_RATE').text
#     PPLTN_RATE_0 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_0').text
#     PPLTN_RATE_10 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_10').text
#     PPLTN_RATE_20 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_20').text
#     PPLTN_RATE_30 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_30').text
#     PPLTN_RATE_40 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_40').text
#     PPLTN_RATE_50 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_50').text
#     PPLTN_RATE_60 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_60').text
#     PPLTN_RATE_70 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_70').text
#     RESNT_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/RESNT_PPLTN_RATE').text
#     NON_RESNT_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/NON_RESNT_PPLTN_RATE').text
#     REPLACE_YN = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/REPLACE_YN').text
#     PPLTN_TIME = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_TIME').text
    
#     hotspot = Hotspot.objects.create(
#     AREA_NM = AREA_NM,
#     LIVE_PPLTN_STTS = LIVE_PPLTN_STTS,
#     AREA_CONGEST_LVL = AREA_CONGEST_LVL,
#     AREA_CONGEST_MSG = AREA_CONGEST_MSG,
#     AREA_PPLTN_MIN = AREA_PPLTN_MIN,
#     AREA_PPLTN_MAX = AREA_PPLTN_MAX,
#     MALE_PPLTN_RATE = MALE_PPLTN_RATE,
#     FEMALE_PPLTN_RATE = FEMALE_PPLTN_RATE,
#     PPLTN_RATE_0 = PPLTN_RATE_0,
#     PPLTN_RATE_10 = PPLTN_RATE_10,
#     PPLTN_RATE_20 = PPLTN_RATE_20,
#     PPLTN_RATE_30 = PPLTN_RATE_30,
#     PPLTN_RATE_40 = PPLTN_RATE_40,
#     PPLTN_RATE_50 = PPLTN_RATE_50,
#     PPLTN_RATE_60 = PPLTN_RATE_60,
#     PPLTN_RATE_70 = PPLTN_RATE_70,
#     RESNT_PPLTN_RATE = RESNT_PPLTN_RATE,
#     NON_RESNT_PPLTN_RATE = NON_RESNT_PPLTN_RATE,
#     REPLACE_YN = REPLACE_YN,
#     PPLTN_TIME = PPLTN_TIME 
#     )    
#     hotspot.save()





# RabbitMQ: RabbitMQ는 대용량 데이터 처리와 분산 작업 처리에 많이 사용되는 
# 메시지 브로커입니다. RabbitMQ는 안정성과 확장성이 뛰어나며, 다양한 클라이언트
# 라이브러리를 지원합니다.

# Apache Kafka: Apache Kafka는 고성능 분산 스트리밍 플랫폼으로, 대용량 데이터
# 스트림 처리에 적합합니다. Kafka는 데이터를 신속하게 처리하고 저장하기 위한 
# 메시지 시스템으로 사용될 수 있습니다.

# Redis: Redis는 인메모리 데이터 저장소로, 속도가 빠르고 간편한 설정으로
# 대용량 데이터 처리에 유용합니다. Redis는 Pub/Sub 메커니즘을 제공하여 
# 데이터의 발행 및 구독을 처리할 수 있습니다. Celery와 함께 사용

# mqtt 는 부적절 경량이기 때문