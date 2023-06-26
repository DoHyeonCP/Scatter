import paho.mqtt.client as mqtt
from geo_distance import geo_distance, radius, coordinate
from pushalarm_pub import pushalarm


coordinate = coordinate
radius = radius
area = ""

def on_connect(client, userdata, flags, rc):
    print("Connected with result code", str(rc))
    client.subscribe("location")


def on_message(client, userdata, msg):
    payload = msg.payload.decode()
    latitude, longitude = map(float, payload.split(','))
    area = geo_distance(latitude , longitude)

    # 지역 내에 들어오면 알림 보내기 && 혼잡도
    if area != area:
        pushalarm(area)

    print("Received location:", latitude, longitude)

