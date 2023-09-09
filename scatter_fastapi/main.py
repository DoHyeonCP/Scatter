


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


