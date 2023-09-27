<<<<<<< HEAD



def get_sk_hotspots(request):
    area_count = 0
    for sk_areaid in sk_songpagu_areas_id:
        areainfo = sk_api_areas_congetion(sk_areaid, area_count, area)
        area_count += 1
        print("sk_area_finish")
    for sk_poiid in sk_songpagu_pois_id:
        areainfo = sk_api_pois_congetion(sk_poiid, area_count, area)
        area_count += 1
    json_data = json.dumps(areainfo,ensure_ascii=False)
        
    return JsonResponse(areainfo, json_dumps_params={'ensure_ascii': False}, safe=False, content_type='application/json')
  


@shared_task
def get_sk(request):
  area_count = 0
  for sk_areaid in sk_songpagu_areas_id:
    areainfo = sk_api_areas_congetion(sk_areaid, area_count, area)
    area_count += 1
    print("sk_area_finish")
  for sk_poiid in sk_songpagu_pois_id:
    areainfo = sk_api_pois_congetion(sk_poiid, area_count, area)
    area_count += 1
  json_data = json.dumps(areainfo,ensure_ascii=False)
  
  return JsonResponse(areainfo, json_dumps_params={'ensure_ascii': False}, safe=False, content_type='application/json')


=======
from fastapi import FastAPI, BackgroundTasks
from json_serialize import Sk_json_Serialize
import time


app = FastAPI()


sk_json = Sk_json_Serialize()
areainfo = sk_json.areainfo

def update_data_every_hour():
    while True:
        sk_json.upadate_sk_data()
        time.sleep(3600)


@app.get("/request_congestion")
def get_congestion(background_tasks: BackgroundTasks):
    background_tasks.add_task(update_data_every_hour)
    return areainfo
>>>>>>> ccf7fca58ed55ed95d1c17c35b7c3905b50e5142
