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