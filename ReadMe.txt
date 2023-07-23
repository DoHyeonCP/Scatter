마무리 남은 것
1. 안드로이드 hardcode 없애기(보안을 위해)
2. Djnago image json 파일혁식 지역이름 하위에 경로가 나오게하기
3. Android Studio 이미지가져와서 드롭다운 지역 설정시 서버에서 JsonResponse된  name/path parshing해서 ImageView 띄우도록 수정하기(congestionpredistion/downloadfile, URlconnction vs Retrofit)
4. AI 코드 Djnago의 app으로 만들기(sk api 불러오는 것이 중복됨으로 비효율)


AI 전달내용
--Python 3.7.10

실행 순서

1. main.py 실행
과정:
(Crontab-매일 0시 10분 경 1회 실행)
=> congestion3.csv 데이터 업데이트
=> train_weather.csv 데이터 업데이트
=> model1 에측
=> model2 예측
=> total_pred.csv 생성 (두 모델 예측값 병합)
=> plot.py 실행
=> 장소별 미래시간 그래프 생성
=> 장소 비교 그래프 생성
=> images 폴더에 png파일로 저장
=> html_files 폴더에 html파일로 저장


2. rltm_poi.py 실행
과정:
(Crontab-매시간 25분경 총 24회 실행)
=> rltm_data.csv 데이터 업데이트 (실시간 데이터 병합)
시간이 0시가 아닌 시간에 중도 실행할 경우 rltm_data.csv 내에 데이터 삭제(열이름 유지) 후 실행
=> 오늘 예측값 + 실제값 그래프 생성
=> images 폴더에 png파일로 저장
=> html_files 폴더에 html파일로 저장
