from call_json import Call_Sk
import json

class Sk_json_Serialize():
    def __init__(self):
        self.call_sk = Call_Sk()
        self.areainfo = self.call_sk.areainfo
        self.appkey = self.call_sk.SK_APP_KEY
        self.area_n = self.call_sk.area
        self.area_id = self.call_sk.sk_songpagu_areas_id
        self.pois_id = self.call_sk.sk_songpagu_pois_id
    

    def get_sk_hotspots_areas(self, id: str, num: int):
        url = f'https://apis.openapi.sk.com/puzzle/place/congestion/rltm/areas/{id}?appkey={self.appkey}'
        jsonobject = self.call_sk.sk_open_api(url)
        congestion = jsonobject['contents']['rltm']['congestion']
        congestion = self.call_sk.rename_level(congestion)

        datetime = jsonobject['contents']['rltm']['datetime']
        y = datetime[:4]
        M = datetime[4:6]
        d = datetime[6:8]
        h = datetime[8:10]
        m = datetime[10:12]
        s = datetime[12:]
        datetime_format = f"{y}년{M}월{d}일 {h}.{m}.{s}"
    
        area  = str(self.call_sk.area[num])
        self.areainfo[area] = {
            "congestion_level": congestion,
            "datetime":  datetime_format
        }
        print(self.call_sk.areainfo[area])
        return self.areainfo
    
    def get_sk_hotspots_pois(self, id , num):
        url = f'https://apis.openapi.sk.com/puzzle/place/congestion/rltm/pois/{id}?appkey={self.appkey}'
        jsonobject = self.call_sk.sk_open_api(url)
        datetime = jsonobject['contents']['rltm'][0]['datetime']
        congestion = jsonobject['contents']['rltm'][0]['congestion']
        congestion = self.call_sk.rename_level(congestion)
        
        area = str(self.call_sk.area[num])
        y = datetime[:4]
        M = datetime[4:6]
        d = datetime[6:8]
        h = datetime[8:10]
        m = datetime[10:12]
        s = datetime[12:]
        datetime_format = f"{y}년{M}월{d}일 {h}.{m}.{s}"
        self.areainfo[area] = {
                "congestion_level": congestion,
                "datetime": datetime_format
            }
        print(self.areainfo[area])
        return self.areainfo
    
    def upadate_sk_data(self):
        area_count = 0
        for sk_areaid in self.area_id:
            self.areainfo = self.get_sk_hotspots_areas(sk_areaid, area_count)
            area_count += 1
            print("sk_area_finish")
        for sk_poiid in self.pois_id:
            self.areainfo = self.get_sk_hotspots_pois(sk_poiid, area_count)
            area_count += 1
            print("sk_pois_finish")
        return self.areainfo
