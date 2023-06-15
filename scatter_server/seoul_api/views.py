#-*- coding: utf-8 -*-

import xml.etree.ElementTree as ET
import time
from rest_framework import status
from rest_framework.parsers import JSONParser
from django.shortcuts import render
from django.http import JsonResponse,HttpResponse
from .serializer import SkAreasserializer, SkPoisSerializer
from .callapi import sk_api_areas_congetion,sk_api_pois_congetion,sk_api_pois_areas_congetion,seoul_api_space

# Create your views here.

# save request
seoul_area_list = ['강남 MICE 관광특구', '동대문 관광특구', '명동 관광특구', '이태원 관광특구', '잠실 관광특구', '종로·청계 관광특구', '홍대 관광특구', '경복궁·서촌마을', '광화문·덕수궁', '창덕궁·종묘', '가산디지털단지역', '강남역', '건대입구역', '고속터미널역', '교대역', '구로디지털단지역', '서울역', '선릉역', '신도림역', '신림역', '신촌·이대역', '역삼역', '연신내역', '용산역', '왕십리역', 'DMC(디지털미디어시티)', '창동 신경제 중심지', '노량진', '낙산공원·이화마을', '북촌한옥마을', '가로수길', '성수카페거리', '수유리 먹자골목', '쌍문동 맛집거리', '압구정로데오거리', '여의도', '영등포 타임스퀘어', '인사동·익선동', '국립중앙박물관·용산가족공원', '남산공원', '뚝섬한강공원', '망원한강공원', '반포한강공원', '북서울꿈의숲', '서울대공원', '서울숲공원', '월드컵공원', '이촌한강공원', '잠실종합운동장', '잠실한강공원']
def get_seoul_hotspots(request):
	print("success")
	while True:
		for seoul_area in seoul_area_list:
			seoul_api_space(seoul_area)
		print("finish")
		time.sleep(60*30) # 30minutes

# 잠실역, 잠실역 롯데월드, 방이동 먹자골목
sk_songpagu_areas_id = ["9270", "9272", "9273"]
# 롯데월드어드벤처, 롯데월드잠실점, 롯데백화점잠실점, 롯데마트제타플레
sk_songpagu_pois_id = ["6967166","187961","188485","188592","5783805","5799875","384515","188633"]
def get_sk_hotspots(request):
	print("success")
	while True:
		# for sk_areaid in sk_songpagu_areas_id:
		# 	sk_api_areas_congetion(sk_areaid)
		# print("sk_area_finish")
		# for sk_poiid in sk_songpagu_pois_id:
		# 	sk_api_pois_congetion(sk_poiid)
		# print("sk_poi_finish")
		for sk_areaid in sk_songpagu_areas_id:
			sk_api_pois_areas_congetion(areaid = sk_areaid)
		for sk_poiid in sk_songpagu_pois_id:
			sk_api_pois_areas_congetion(poiid = sk_poiid)
		print("sk_pois_areas_hourly_finish")
		
		time.sleep(60*60*24) # by one day

	return HttpResponse('success')



	
 
 
 
 
 
 
 
 
 
 
 
# Don't erase, please    
# for hotspot_data in root.iter('hotspot'):
    # AREA_NM = root.find('CITYDATA/AREA_NM').text
    # LIVE_PPLTN_STTS = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS').text
    # AREA_CONGEST_LVL = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_CONGEST_LVL').text
    # AREA_CONGEST_MSG = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_CONGEST_MSG').text
    # AREA_PPLTN_MIN = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_PPLTN_MIN').text
    # AREA_PPLTN_MAX = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/AREA_PPLTN_MAX').text
    # MALE_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/MALE_PPLTN_RATE').text
    # FEMALE_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/FEMALE_PPLTN_RATE').text
    # PPLTN_RATE_0 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_0').text
    # PPLTN_RATE_10 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_10').text
    # PPLTN_RATE_20 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_20').text
    # PPLTN_RATE_30 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_30').text
    # PPLTN_RATE_40 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_40').text
    # PPLTN_RATE_50 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_50').text
    # PPLTN_RATE_60 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_60').text
    # PPLTN_RATE_70 = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_RATE_70').text
    # RESNT_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/RESNT_PPLTN_RATE').text
    # NON_RESNT_PPLTN_RATE = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/NON_RESNT_PPLTN_RATE').text
    # REPLACE_YN = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/REPLACE_YN').text
    # PPLTN_TIME = root.find('CITYDATA/LIVE_PPLTN_STTS/LIVE_PPLTN_STTS/PPLTN_TIME').text
    # # will modify
    # ROAD_TRAFFIC_STTS  = root.find('CITYDATA/Road_TRAFFIC_STTS').text
    # ROAD_TRAFFIC_SPD = root.find('CITYDATA/Road_TRAFFIC_STTS/AVG_ROADDATA/ROAD_TRAFFIC_SPD').text
	# ROAD_TRAFFIC_IDX = root.find('CITYDATA/Road_TRAFFIC_STTS/AVG_ROADDATA/ROAD_TRAFFIC_IDX').text	
	# ROAD_TRAFFIC_TIME = root.find('CITYDATA/Road_TRAFFIC_STTS/AVG_ROADDATA/ROAD_TRAFFIC_TIME').text
	# ROAD_MSG = root.find('CITYDATA/Road_TRAFFIC_STTS/AVG_ROADDATA/ROAD_MSG').text	
	# LINK_ID	= root.find('CITYDATA/Road_TRAFFIC_STTS/LINK_ID').text
	# ROAD_NM	= root.find('CITYDATA/Road_TRAFFIC_STTS/ROAD_NM').text
	# START_ND_CD	= root.find('CITYDATA/Road_TRAFFIC_STTS/START_ND_CD').text
	# START_ND_NM	= root.find('CITYDATA/Road_TRAFFIC_STTS/START_ND_NM').text
	# START_ND_XY	= root.find('CITYDATA/Road_TRAFFIC_STTS/START_ND_XY').text
	# END_ND_CD= root.find('CITYDATA/Road_TRAFFIC_STTS/END_ND_CD').text
	# END_ND_NM	= root.find('CITYDATA/Road_TRAFFIC_STTS/END_ND_NM').text
	# END_ND_XY= root.find('CITYDATA/Road_TRAFFIC_STTS/LINK_ID').text	
	# DIST= root.find('CITYDATA/Road_TRAFFIC_STTS/LINK_ID').text	
	# SPD	= root.find('CITYDATA/Road_TRAFFIC_STTS/LINK_ID').text
	# IDX	= root.find('CITYDATA/Road_TRAFFIC_STTS/LINK_ID').text
	# XYLIST	= root.find('CITYDATA/Road_TRAFFIC_STTS/LINK_ID').text
	# PRK_STTS	
	# PRK_NM	
	# PRK_CD	
	# CPCTY	
	# CUR_PRK_CNT	
	# CUR_PRK_TIME	
	# CUR_PRK_YN	
	# PAY_YN	
	# RATES	
	# TIME_RATES	
	# ADD_RATES	
	# ADD_TIME_RATES	
	# ROAD_ADDR	
	# LAT	
	# LNG	
	# SUB_STTS	
	# SUB_STN_NM	
	# SUB_STN_LINE	
	# SUB_STN_RADDR	
	# SUB_STN_JIBUN	
	# SUB_STN_X	
	# SUB_STN_Y	
	# SUB_NT_STN	
	# SUB_BF_STN	
	# SUB_ROUTE_NM	
	# SUB_LINE	
	# SUB_ORD	
	# SUB_DIR	
	# SUB_TERMINAL	
	# SUB_ARVTIME	
	# SUB_ARMG1	
	# SUB_ARMG2	
	# SUB_ARVINFO	
	# BUS_STN_STTS	
	# BUS_STN_ID	
	# BUS_ARD_ID	
	# BUS_STN_NM	
	# BUS_STN_X	
	# BUS_STN_Y	
	# RTE_STN_NM	
	# RTE_NM	
	# RTE_ID	
	# RTE_SECT	
	# RTE_CONGEST	
	# RTE_ARRV_TM	
	# RTE_ARRV_STN	
	# ACDNT_CNTRL_STTS	
	# ACDNT_OCCR_DT	
	# EXP_CLR_DT	
	# ACDNT_TYPE	
	# ACDNT_DTYPE	
	# ACDNT_INFO	
	# ACDNT_X	
	# ACDNT_Y	
	# ACDNT_TIME	
	# SBIKE_STTS	
	# SBIKE_SPOT_NM	
	# SBIKE_SPOT_ID	
	# SBIKE_SHARED	
	# SBIKE_PARKING_CNT
	# SBIKE_RACK_CNT	
	# SBIKE_X
	# SBIKE_Y
	# WEATHER_STTS
	# TEMP
	# SENSIBLE_TEMP
	# MAX_TEMP
	# MIN_TEMP
	# HUMIDITY
	# WIND_DIRCT	
	# WIND_SPD	
	# PRECIPITATION	
	# PRECPT_TYPE	
	# PCP_MSG	
	# SUNRISE	
	# SUNSET	
	# UV_INDEX_LVL	
	# UV_INDEX	
	# UV_MSG	
	# PM25_INDEX	
	# PM25	
	# PM10_INDEX	
	# PM10	
	# AIR_IDX	
	# AIR_IDX_MVL	
	# AIR_IDX_MAIN	
	# AIR_MSG	
	# WEATHER_TIME	
	# NEWS_LIST	
	# WARN_VAL	
	# WARN_STRESS	
	# ANNOUNCE_TIME	
	# COMMAND	
	# CANCEL_YN	
	# WARN_MSG	
	# FCST24HOURS	
	# FCST_DT	
	# TEMP	
	# PRECIPITATION	
	# PRECPT_TYPE	
	# RAIN_CHANCE	
	# SKY_STTS	
	# COVID_19_STTS	
	# STRD_DT	
	# GU_NM	
	# GU_CONFIRMED
	# GU_ADDED
	# CONFIRMED
	# ADDED
	# DIED
	# T_DIED
 
    # hotspot = Hotspot.objects.create(
    # AREA_NM = AREA_NM,
    # LIVE_PPLTN_STTS = LIVE_PPLTN_STTS,
    # AREA_CONGEST_LVL = AREA_CONGEST_LVL,
    # AREA_CONGEST_MSG = AREA_CONGEST_MSG,
    # AREA_PPLTN_MIN = AREA_PPLTN_MIN,
    # AREA_PPLTN_MAX = AREA_PPLTN_MAX,
    # MALE_PPLTN_RATE = MALE_PPLTN_RATE,
    # FEMALE_PPLTN_RATE = FEMALE_PPLTN_RATE,
    # PPLTN_RATE_0 = PPLTN_RATE_0,
    # PPLTN_RATE_10 = PPLTN_RATE_10,
    # PPLTN_RATE_20 = PPLTN_RATE_20,
    # PPLTN_RATE_30 = PPLTN_RATE_30,
    # PPLTN_RATE_40 = PPLTN_RATE_40,
    # PPLTN_RATE_50 = PPLTN_RATE_50,
    # PPLTN_RATE_60 = PPLTN_RATE_60,
    # PPLTN_RATE_70 = PPLTN_RATE_70,
    # RESNT_PPLTN_RATE = RESNT_PPLTN_RATE,
    # NON_RESNT_PPLTN_RATE = NON_RESNT_PPLTN_RATE,
    # REPLACE_YN = REPLACE_YN,
    # PPLTN_TIME = PPLTN_TIME 
