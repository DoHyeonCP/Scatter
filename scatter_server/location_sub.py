import paho.mqtt.client as mqtt
from .geo_distance import coordinate, radius, geo_distance

geo_distance.coordiante = coordinate
geo_distance.radius = radius

def on_connect(client, userdata, falgs, rc):
    print("Connected with result code", str(rc))
    if rc == 0:
        client.subscribe("android/location")
    else:
        print('fail connect: ', rc)
        
        
def on_message(client,userdata,msg):
    value = float(msg.payload.docode())
    print(f"{msg.topic} {value}")
    
client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message

# 일단 위치를받아야함.
# dohyeon_home ip 115.21.135.45
def location_subscribe():
    try:
        host_id = "115.21.135.45"
        port = "1883"
        client.connect(host_id, port)
        client.loop_forever()
    except Exception as err:
        print('error : %s'%err)

