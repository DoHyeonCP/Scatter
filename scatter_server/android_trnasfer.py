import paho.mqtt.client as mqtt
from location_sub import on_connect, on_message




client = mqtt.Client()

client.on_connect = on_connect
client.on_message = on_message

def location_subscribe():
    try:
        host_id = "115.21.135.45"
        port = 1883  # 수정: 포트 번호를 정수로 전달
        client.connect(host_id, port, 60)
        client.loop_forever()
    except Exception as err:
        print('error:', err)