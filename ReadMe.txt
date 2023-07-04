
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