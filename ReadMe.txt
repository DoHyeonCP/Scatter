-- 완료:
1. android UI/UX 틀
2. 위치 권한요청
3. 위치 정보 서버로 보내기
4. 위도경도기반 지오팬스 알고리즘
5.  push알림 전송기능


-- 가능한 것(시연)
1. scattle 공유된 어플 다운
2. mqtt 사용을 위한 mosquitto 다운로드
  -- 방화벽 설정 꼭 해야함
1) C:\Program Files\mosquitto 경로의 mosquitto.conf 파일을 관리자 권한 메모장으로 연다.
2) 맨 하단
listener 1883
allow_anonymous true
bind_address 0.0.0.0 //broker server pc ip
입력 후저장 
3) 이후 과정은 https://velog.io/@foxiq/MQTT-%EC%82%AC%EC%9A%A9
3.서버 Run
scatter_server/location_sub run



-- 해야할 것: 
1. menu_burger 드래그가 아닌 클릭 시 toolbar 열림
- 툴바를 닫을 대는 X 클릭

2. toolbar에서 지역 선택 시 textview에 업데이트 되어 있는 정보 출력
- aws or django 서버에서 api를 통해 json으로 정보 가져오기
- 파싱된 정보 item에 저장하기
- toolbar에서 지역 선택 시 item에 저장된 데이터 texview에 출력
- 예측 그래프 WebView연결(아닐 수도 있음)

3. push 알림
- 서버에서 받은 위치 정보가 혼잡 지역 근처에 갈경우 push 전송
- 스마트폰 push 알림과 함께 진동 3번, 소리 3번
- wearable에서는 표정과 진동 소리 띄우기

4. 지역에 이미지 드래그 앤 드롭
- 지역별로 이미지 분할하여 consantraint layout으로 연결
- 이미지 드래그엔 드롭 








------------------- 지역 좌표 중앙
- 쇼핑
롯데백화점 잠실점, 37.512160 127.099437
롯데마트제타플렉스점 37.511612, 127.096311
롯데백화점에비뉴엘월드타워점, 37.512739, 127.102087
롯데월드몰, 37.513714, 127.104169

- 상권(원을 그려야함)
잠실역: 37.515358, 127.101563
잠실역 롯데월드, 37.511526, 127.098350
방이동먹자골목 37.514503, 127.108618

: 1시간( 

기능


2. 위험 신호 스마트폰으로 받기 - 2순위
3. push알림 - 3순위

