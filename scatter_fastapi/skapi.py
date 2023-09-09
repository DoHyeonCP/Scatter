import json
import requests
from django.http import JsonResponse

SK_APP_KEY = 'BFE8BDtYZK553WvHLrnHxagtLvBEypDq9ClJQpAs'
# SK_APP_KEY = 'RNM43SFreC8YwWjFIAGHY4VIpOi6jDHG98AHf7rN'
# SK_APP_KEY = 'e8wHh2tya84M88aReEpXCa5XTQf3xgo01aZG39k5'
# SK_APP_KEY = '6nPa8A9ij41zGV7x7QAeR9x9y3MuLPEgjiHjbhhc'
# SK_APP_KEY = 'W9DXjZKgAg4bVZwjN90m14SxPppyMoRzcYiBAB72'
# SK_APP_KEY = 'Tt3yyROHTM8op2hEyv1Z34AXC2x8KPbn1iuD5Hlc'
basic_url = "https://apis.openapi.sk.com/puzzle/place/congestion/rltm/{pois_or_area}/{poiid}?appkey={app_key}"

areainfo = {"롯데월드": {
	"datetime" : "",
     "congestion_level" : 0
		}, 
	    "방이동먹자골목":{
              "datetime" : "",
     		"congestion_level" : 0
              
		},
		"에비뉴엘월드타워점":{
               "datetime" : "",
     "congestion_level" : 0
               
		}, 
		"롯데월드몰":{
               "datetime" : "",
     "congestion_level" : 0
		}, 
		"올림픽공원":{
               "datetime" : "",
     "congestion_level" : 0
		}
    }

class callapi:
    def SkOpenApi(url): # json serialize
        url = url
        response = requests.get(url)
        hotspots_data = response.content.decode('utf-8')
        json_data = json.loads(hotspots_data)
        return json_data


    def sk_api_areas_congetion(areaid, num, area):
        app_key = SK_APP_KEY
        jsonobject = callapi().SkOpenApi(f'https://apis.openapi.sk.com/puzzle/place/congestion/rltm/areas/{areaid}?appkey={app_key}')
        congestion = jsonobject['contents']['rltm']['congestion']
        congestion = callapi().renamelevel(congestion)

        datetime = jsonobject['contents']['rltm']['datetime']
        y = datetime[:4]
        M = datetime[4:6]
        d = datetime[6:8]
        h = datetime[8:10]
        m = datetime[10:12]
        s = datetime[12:]
        datetime_format = f"{y}년{M}월{d}일 {h}.{m}.{s}"
    
        area  = str(area[num])
        areainfo[area] = {
            "congestion_level": congestion,
            "datetime":  datetime_format
        }
        print(areainfo[area])
        return areainfo
        
    def sk_api_pois_congetion(poiid,num,area):
        app_key = SK_APP_KEY
        jsonobject = callapi().SkOpenApi(f'https://apis.openapi.sk.com/puzzle/place/congestion/rltm/pois/{poiid}?appkey={app_key}')
        datetime = jsonobject['contents']['rltm'][0]['datetime']
        congestion = jsonobject['contents']['rltm'][0]['congestion']
        congestion = callapi().renamelevel(congestion)
        
        area = str(area[num])
        y = datetime[:4]
        M = datetime[4:6]
        d = datetime[6:8]
        h = datetime[8:10]
        m = datetime[10:12]
        s = datetime[12:]
        datetime_format = f"{y}년{M}월{d}일 {h}.{m}.{s}"
        areainfo[area] = {
                "congestion_level": congestion,
                "datetime": datetime_format
            }
        print(areainfo[area])
        return areainfo
    
    def renamelevel(congestion):
        if congestion < 0.0175:
            return "여유"
        elif congestion <= 0.035:
            return "보통"
        elif congestion <= 0.21:
            return "조금혼잡"
        elif congestion <= 0.4:
            return "혼잡"
        elif congestion > 0.4:
            return "매우혼잡"




# 아래 부터는 샘플

def get_sk_hotspots(request):
    area_count = 0
    for sk_areaid in callapi().sk_songpagu_areas_id:
        areainfo = callapi().sk_api_areas_congetion(sk_areaid, area_count, area)
        area_count += 1
        print("sk_area_finish")
    for sk_poiid in callapi.sk_songpagu_pois_id:
        areainfo = callapi().sk_api_pois_congetion(sk_poiid, area_count, area)
        area_count += 1
    json_data = json.dumps(areainfo,ensure_ascii=False)
        
    return JsonResponse(areainfo, json_dumps_params={'ensure_ascii': False}, safe=False, content_type='application/json')
  



롯데백화점잠실점 = {
    "status": {
      "code": "00",
      "message": "success",
      "totalCount": 1
    },
    "contents": {
      "poiId": "188485",
      "poiName": "롯데백화점잠실점",
      "rltm": [
        {
          "datetime": "20230704012000",
          "congestion": 0.00004,
          "congestionLevel": 1,
          "type": 1
        }
      ]
    }
  }


    

롯데월드 ={"status": {"code": "00","message": "success","totalCount": 1},"contents": {"areaId": "9273","areaName": "롯데월드","rltm": {"congestion": 0.01541,"congestionLevel": 1,"datetime": "20230701002000"}}}
