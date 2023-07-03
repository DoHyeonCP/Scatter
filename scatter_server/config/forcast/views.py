from django.shortcuts import render

# Create your views here.

from django.shortcuts import render
import os

def lotteworld_forcast(request):
    template_dir = 'forecast/templates/lotteworld'  # 템플릿 디렉토리 경로
    locations = os.listdir(template_dir)  # 폴더 목록 가져오기

    context = {'locations': locations}
    return render(request, 'lotteworld/forecast.html', context)

