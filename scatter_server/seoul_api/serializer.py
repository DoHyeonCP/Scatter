from rest_framework import serializers, viewsets
from .models import SKJsonAreasData, SkJsonPoisData
# from .models import Hotspot

# Use, later(maybe after use Jsondatas?)
class SkPoisSerializer(serializers.Serializer):
    class Meta:
        model = SkJsonPoisData
        fields = '__all__'

class SkAreasserializer(serializers.Serializer):
    class Meta:
        model = SKJsonAreasData
        fields = '__all__'
        
# class SeoulViewSet(viewsets.ModelViewSet):
#     queryset = Hotspot.objects.all()
#     serializer_class = SeoulSerializer
