#-*- coding: utf-8 -*-
from django.db import models

# Create your models here.

class SeoulJsonData(models.Model):
    area_nm = models.CharField(max_length = 255)
    seoul_data = models.JSONField()
    created_at = models.DateTimeField(auto_now_add=True)
    
    def __str__(self):
        return str(self.data)
    
class SkJsonPoisAreasHourData(models.Model): # 시간대별
	sk_pois_hour_data = models.JSONField()
	sk_areas_hour_data = models.JSONField()
	created_at = models.DateTimeField(auto_now_add = True)
   
   
   
# 필요 없어지면 지우기 지금은 쓰지 않음 06.15 
class SkJsonPoisData(models.Model): # 지역
    sk_pois_data = models.JSONField()
    created_at = models.DateTimeField(auto_now_add = True)
    
class SKJsonAreasData(models.Model): #상권
    sk_areas_data = models.JSONField()
    created_at = models.DateTimeField(auto_now_add = True)


    
    
    

# json분석 후 전송될 모델
# class Hotspot(models.Model):
#     AREA_NM = models.CharField(max_length= 20) # 핫스팟 장소명
#     LIVE_PPLTN_STTS = models.CharField(max_length = 255, null = True) # 실시간 인구현황
#     AREA_CONGEST_LVL = models.CharField(max_length = 10) # 장소 혼잡도 지표
#     AREA_CONGEST_MSG = models.CharField(max_length=255) # 장소 혼잡도 관련 메세지
#     AREA_PPLTN_MIN = models.IntegerField() # 실시간 인구 지표 최소값
#     AREA_PPLTN_MAX = models.IntegerField() # 실시간 인구 지표 최대값
#     MALE_PPLTN_RATE = models.FloatField() #  남성 인구 비율
#     FEMALE_PPLTN_RATE = models.FloatField() # 여성 인구 비율
#     PPLTN_RATE_0 = models.FloatField() # 0 ~ 10세 인구비율
#     PPLTN_RATE_10 = models.FloatField() # 10대 인구비율
#     PPLTN_RATE_20 = models.FloatField() # 20대 인구비율
#     PPLTN_RATE_30 = models.FloatField() # 30대 인구비율
#     PPLTN_RATE_40 = models.FloatField() # 40대 인구비율
#     PPLTN_RATE_50 = models.FloatField() # 50대 인구비율
#     PPLTN_RATE_60 = models.FloatField() # 60대 인구비율
#     PPLTN_RATE_70 = models.FloatField() # 70대 인구비율
#     RESNT_PPLTN_RATE = models.FloatField() # 상주 인구비율(거주자)
#     NON_RESNT_PPLTN_RATE = models.FloatField() #비상주 인구비율(비 거주자)
#     REPLACE_YN = models.CharField(max_length = 2) # 대체 데이터 여부
#     PPLTN_TIME = models.DateTimeField() # 실시간 인구 데이터 업데이트 시간
#     ROAD_TRAFFIC_STTS = models.CharField(max_length = 255, null = True)#도로소통현황
#     ROAD_TRAFFIC_SPD = models.IntegerField	#전체도로소통평균속도
#     ROAD_TRAFFIC_IDX = models.CharField(max_length = 10)	#전체도로소통평균현황
#     ROAD_TRAFFIC_TIME = models.DateTimeField	#도로소통현황 업데이트 시간
#     ROAD_MSG = models.CharField(max_length=255)	#전체도로소통평균현황 메세지
#     LINK_ID = models.IntegerField()	#도로구간 LINK ID
#     ROAD_NM = models.CharField(max_length = 20)	#도로명
#     START_ND_CD = models.IntegerField()	#도로노드시작지점 코드
#     START_ND_NM = models.CharField(max_length = 255)	#도로노드시작명
#     START_ND_XY = models.FloatField()	#도로노드시작지점좌표
#     END_ND_CD = models.IntegerField()	#도로노드종료지점 코드
#     END_ND_NM = models.CharField(max_length= 255)	#도로노드종료명
#     END_ND_XY = models.FloatField()	#도로노드종료지점좌표
#     DIST = models.IntegerField()	#도로구간길이
#     SPD = models.FloatField()	#도로구간평균속도
#     IDX = models.CharField(max_length = 255)	#도로구간소통지표
#     XYLIST = models.FloatField()	#링크아이디 좌표(보간점)
#     PRK_STTS = models.CharField(max_length = 255, null = True)	#주차장 현황
#     PRK_NM = models.CharField(max_length = 255)	#주차장명
#     PRK_CD = models.IntegerField()	#주차장코드
#     CPCTY = models.FloatField()	#주차장 수용 가능 면수
#     CUR_PRK_CNT = models.FloatField()	#주차장 주차 가능 면수
#     CUR_PRK_TIME = models.DateTimeField()	#현재 주차장 주차 차량 수 업데이트 시간
#     CUR_PRK_YN = models.CharField(max_length = 2)	#실시간 주차장 주차 현황 제공 여부
#     PAY_YN = models.CharField(max_length = 2)	#유무료 여부
#     RATES = models.IntegerField()	#기본주차요금
#     TIME_RATES = models.IntegerField()	#기본주차단위시간
#     ADD_RATES = models.IntegerField	#추가주차단위요금
#     ADD_TIME_RATES = models.IntegerField()	#추가주차단위시간
#     ROAD_ADDR = models.CharField(max_length = 30)	#도로명주소
#     LAT = models.FloatField()	#위도
#     LNG = models.FloatField()	#경도
#     SUB_STTS = models.CharField(max_length = 255, null = True)	#지하철 실시간 도착 현황
#     SUB_STN_NM = models.CharField(max_length = 30)	#지하철역명
#     SUB_STN_LINE = models.CharField(max_length = 10)	#지하철역 호선
#     SUB_STN_RADDR = models.CharField(max_length = 255)	#지하철역 도로명 주소
#     SUB_STN_JIBUN = models.CharField(max_length = 255)	#지하철역 구 지번주소
#     SUB_STN_X = models.FloatField()	#지하철역 X 좌표(경도)
#     SUB_STN_Y = models.FloatField()	#지하철역 Y 좌표(위도)
#     SUB_NT_STN = models.IntegerField()	#다음역 코드
#     SUB_BF_STN = models.IntegerField()	#이전역 코드
#     SUB_ROUTE_NM = models.CharField(max_length = 10)	#지하철노선명
#     SUB_LINE = models.CharField(max_length = 10)	#지하철호선
#     SUB_ORD = models.CharField(max_length = 255)	#도착예정열차순번
#     SUB_DIR = models.CharField(max_length = 5)	#지하철방향
#     SUB_TERMINAL = models.CharField(max_length = 255)#종착역
#     SUB_ARVTIME = models.CharField(max_length = 50)	#열차 도착 시간
#     SUB_ARMG1 = models.CharField(max_length = 255)	#열차 도착 메세지
#     SUB_ARMG2 = models.CharField(max_length = 255)	#열차 도착 메세지
#     SUB_ARVINFO = models.IntegerField()	#열차 도착 코드 정보
#     BUS_STN_STTS = models.CharField(max_length = 255)	#버스정류소 현황
#     BUS_STN_ID = models.IntegerField()	#정류소ID
#     BUS_ARD_ID = models.IntegerField()	#정류소 고유번호
#     BUS_STN_NM = models.CharField(max_length =255)	#정류소명
#     BUS_STN_X = models.FloatField()	#정류소 X 좌표(경도)
#     BUS_STN_Y = models.FloatField()	#정류소 Y 좌표(위도)
#     RTE_STN_NM = models.CharField(max_length = 255)	#노선 조회 기준 정류장명
#     RTE_NM = models.CharField(max_length = 255)	#노선명
#     RTE_ID = models.IntegerField()	#노선ID
#     RTE_SECT = models.CharField(max_length = 255)	#노선구간
#     RTE_CONGEST = models.CharField(max_length = 255)	#노선혼잡도
#     RTE_ARRV_TM	 = models.TimeField()#노선예상도착시간
#     RTE_ARRV_STN = models.CharField(max_length = 255)	#노선최근도착정류장
#     ACDNT_CNTRL_STTS = models.CharField(max_length = 255, null = True)	#사고통제현황
#     ACDNT_OCCR_DT = models.DateTimeField()	#사고발생일시
#     EXP_CLR_DT = models.DateTimeField()	#통제종료예정일시
#     ACDNT_TYPE = models.CharField(max_length = 20)	#사고발생유형
#     ACDNT_DTYPE = models.CharField(max_length =20)	#사고발생세부유형
#     ACDNT_INFO = models.CharField(max_length = 50)	#사고통제내용
#     ACDNT_X = models.FloatField()	#사고통제지점 X 좌표
#     ACDNT_Y	 = models.FloatField()#사고통제지점 Y 좌표
#     ACDNT_TIME = models.TimeField()	#사고통제현황 업데이트 시간
#     SBIKE_STTS = models.CharField(max_length = 255, null = True)	#따릉이 현황
#     SBIKE_SPOT_NM = models.CharField(max_length = 50)	#따릉이대여소명
#     SBIKE_SPOT_ID = models.IntegerField()	#따릉이대여소ID
#     SBIKE_SHARED = models.FloatField()	#따릉이거치율
#     SBIKE_PARKING_CNT = models.IntegerField()	#따릉이 주차 건수
#     SBIKE_RACK_CNT = models.IntegerField()	#따릉이거치대 개수
#     SBIKE_X = models.FloatField()	#따릉이대여소 좌표 경도
#     SBIKE_Y = models.FloatField()	#따릉이대여소 좌표 위도
#     WEATHER_STTS = models.CharField(max_length= 1, null = True)	#날씨 현황
#     TEMP = models.FloatField()	#기온
#     SENSIBLE_TEMP = models.FloatField()	#체감온도
#     MAX_TEMP = models.FloatField()	#일 최저온도/최고온도
#     MIN_TEMP = models.FloatField()	#일 최저온도/최고온도
#     HUMIDITY = models.FloatField()	#습도
#     WIND_DIRCT = models.CharField(max_length = 10)	#풍향
#     WIND_SPD = models.FloatField()	#풍속
#     PRECIPITATION = models.FloatField()	#강수량
#     PRECPT_TYPE = models.CharField(max_length = 50)	#강수형태
#     PCP_MSG = models.CharField(max_length = 255)	#강수관련 메세지
#     SUNRISE = models.TimeField()	#일출시각
#     SUNSET = models.TimeField()	#일몰시각
#     UV_INDEX_LVL = models.IntegerField()	#자외선지수 단계
#     UV_INDEX = models.CharField(max_length = 20)	#자외선지수
#     UV_MSG = models.CharField(max_length = 50)	#자외선메세지
#     PM25_INDEX = models.CharField(max_length = 50)	#초미세먼지지표
#     PM25 = models.FloatField()	#초미세먼지농도
#     PM10_INDEX = models.CharField(max_length = 20)	#미세먼지지표
#     PM10 = models.FloatField()	#미세먼지농도
#     AIR_IDX = models.CharField(max_length = 20)	#통합대기환경등급
#     AIR_IDX_MVL = models.FloatField()	#통합대기환경지수
#     AIR_IDX_MAIN = models.CharField(max_length = 10)	#통합대기환경지수 결정물질
#     AIR_MSG = models.CharField(max_length = 50)	#통합대기환경등급별 메세지
#     WEATHER_TIME = models.DateTimeField()	#날씨 데이터 업데이트 시간
#     NEWS_LIST = models.CharField(max_length = 50, null = True)	#기상관련특보
#     WARN_VAL = models.CharField(max_length = 50, null = True)	#기상특보종류
#     WARN_STRESS	 = models.CharField(max_length = 50, null = True)#기상특보강도
#     ANNOUNCE_TIME = models.DateTimeField(null = True)	#기상특보발효시각
#     COMMAND = models.IntegerField()	#기상특보발표코드
#     CANCEL_YN = models.CharField(max_length = 10, null = True)	#기상특보취소구분
#     WARN_MSG = models.CharField(max_length = 255, null = True)	#기상특보별 행동강령
#     FCST24HOURS = models.CharField(max_length = 1, null = True)	#24시간 예보
#     FCST_DT = models.DateTimeField()	#예보시간
#     TEMP = models.FloatField()	#기온
#     PRECIPITATION = models.IntegerField()	#강수량
#     PRECPT_TYPE = models.CharField(max_length = 255)	#강수형태
#     RAIN_CHANCE = models.FloatField()	#강수확률
#     SKY_STTS = models.CharField(max_length =255)	#하늘상태
#     COVID_19_STTS = models.CharField(max_length = 255, null = True)	#코로나19 현황
#     STRD_DT = models.DateTimeField()	#기준일
#     GU_NM = models.CharField(max_length= 50)	#소재한 자치구명
#     GU_CONFIRMED = models.IntegerField()	#(자치구)확진자 수
#     GU_ADDED = models.IntegerField()	#(자치구)확진자 추가
#     CONFIRMED = models.IntegerField()	#서울 전체 확진자 수
#     ADDED = models.IntegerField()	#서울 전체 확진자 추가 수
#     DIED = models.IntegerField()	#서울시 전체 사망자 수
#     T_DIED = models.IntegerField()	#서울시 당일 사망자 수
 
  # 지우지마세요 지우면 저 죽어요
	# ROAD_TRAFFIC_STTS	
    # ROAD_TRAFFIC_SPD	
	# ROAD_TRAFFIC_IDX	
	# ROAD_TRAFFIC_TIME	
	# ROAD_MSG	
	# LINK_ID	
	# ROAD_NM	
	# START_ND_CD	
	# START_ND_NM	
	# START_ND_XY	
	# END_ND_CD
	# END_ND_NM	
	# END_ND_XY	
	# DIST	
	# SPD	
	# IDX	
	# XYLIST	
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
	# TEMP	#기온
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