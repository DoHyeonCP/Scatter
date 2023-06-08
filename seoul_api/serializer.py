from rest_framework import serializers
from .models import Hotspot

class Serializer(serializers.ModelSerializer):
    class Meta:
        model = Hotspot
        fields = '__all__'