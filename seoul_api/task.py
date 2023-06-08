import requests
from django.conf import settings
from celery import shared_task # 비동기 작업처리를 위한 라이브러리
from .models import Hotspot

@shared_task
def update_seoul_hotspots():
    startpoint = 1 # 스타트 포인트, 임의로 지정했음 수정해야함
    endpoint = 6 # 스타트 포인트, 임의로 지정했음 수정해야함
    api_key = settings.SEOUL_API_KEY
    url =  f'https://api.seoul.go.kr:8088/{api_key}/json/citydata/{startpoint}/{endpoint}'

    response = requests.get(url)
    data = response.json()
    
    hotspots_data = data['hotspots']
    
    for hotspot_data in hotspots_data:
        AREA_NM = hotspot_data['AREA_NM'] 
        LIVE_PPLTN_STTS = hotspot_data['LIVE_PPLTN_STTS']
        AREA_CONGEST_LVL = hotspot_data['AREA_CONGEST_LVL']
        AREA_CONGEST_MSG = hotspot_data['AREA_CONGET_MSG']
        AREA_PPLTN_MIN = hotspot_data['AREA_PPLTN_MIN']
        AREA_PPLTN_MAX = hotspot_data['AREA_PPLTN']
        
        hotspot, created = Hotspot.objects.get_or_create(AREA_NM = AREA_NM)
        hotspot.LIVE_PPLTN_STTS = LIVE_PPLTN_STTS
        hotspot.AREA_CONGEST_LVL = AREA_CONGEST_LVL
        hotspot.AREA_CONGEST_MSG = AREA_CONGEST_MSG
        hotspot.AREA_PPLTN_MIN = AREA_PPLTN_MIN
        hotspot.AREA_PPLTN_MAX = AREA_PPLTN_MAX
        hotspot.save()





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