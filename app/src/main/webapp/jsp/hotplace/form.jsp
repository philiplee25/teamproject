<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 할인정보</title>
</head>
<h1>새 할인정보</h1>
<body>
<div id="map" style="width:700px;height:700px;margin:auto;"></div>
<div id="clickLatlng"></div>

<form action="add" method="post" enctype="multipart/form-data">
제목: <input type='text' name='title'><br>
내용: <textarea name='content' rows='10' cols='60'></textarea><br>
사진1: <input type='file' name='photo1'><br>
위도: <input type='text' id='lng' readonly>
경도: <input type='text' id='lat' readonly>
<input type='submit' value='등록'>
<p><a href='list'>목록</a></p>
</form>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=321d4ad60c277c79886760c525a516fe&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(36.382998928428584, 128.11241397383552), // 지도의 중심좌표
        level: 13 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({ 
 // 지도 중심좌표에 마커를 생성합니다 
 position: map.getCenter() 
}); 
//지도에 마커를 표시합니다
marker.setMap(map);

//지도에 클릭 이벤트를 등록합니다
//지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
 
 // 클릭한 위도, 경도 정보를 가져옵니다 
 var latlng = mouseEvent.latLng; 
 
 // 마커 위치를 클릭한 위치로 옮깁니다
 marker.setPosition(latlng);
 
 console.log(mouseEvent.latlng());
});
</script>


</body>
</html>