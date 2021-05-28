<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>새 핫플레이스</title>
    
</head>
<body>
<h1>핫플레이스 등록2</h1>
<div id="map" style="width:700px;height:700px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=321d4ad60c277c79886760c525a516fe&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(36.382998928428584, 128.11241397383552), // 지도의 중심좌표
        level: 13 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    
    document.getElementById("latitude").value = latlng.getLat(); 
    document.getElementById("longitude").value = latlng.getLng(); 
    
});
</script>

<form action="add" method="post" enctype="multipart/form-data">
제목: <input type='text' name='title'><br>
내용: <textarea name='content' rows='10' cols='60'></textarea><br>
사진: <input type='file' name='photo'><br>
위도: <input type='text' id='latitude' name='latitude' value='' readonly>
경도: <input type='text' id='longitude' name='longitude' value='' readonly><br>
<input type='submit' value='등록'>
<p><a href='list'>목록</a></p>
</form>
</body>
</html>