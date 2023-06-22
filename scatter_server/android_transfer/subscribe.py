import paho.mqtt.client as mqtt

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

def location_subscribe():
    try:
        host_id = "115.21.135.45"
        port = "1883"
        client.connect(host_id, port)
        client.loop_forever()
    except Exception as err:
        print('error : %s'%err)