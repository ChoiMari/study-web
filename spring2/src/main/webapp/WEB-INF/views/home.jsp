<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" 
    %>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1"><!--initial-scale=1는 브라우저 기본 비율 이용 -->
<title>Spring Legeacy 2</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
 rel="stylesheet" 
 integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
 crossorigin="anonymous">

 <!-- integrity 보안 -->
 

</head>

<body>
    <div class="container-fluid">
        <%--header.jspf 의 <h1>${pageTitle}</h1> 부분 추가함 var="pageTitle" EL과 동일해야(대소문자 구분함) value는 EL 자리에 들어갈 값--%>
        <c:set var="pageTitle" value="Home" scope="page" />
        <%@ include file="./fragments/header.jspf" %> <%--header라는 jspf(jsp조각파일)추가 --%>
    </div>
    
    
    <body>
    
    <%-- 카카오 지도 API --%>
    <div id="map" style="width:500px;height:400px; margin-left: auto; margin-right: auto; margin-top:60px;"></div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ca0462f8dc9c9b542e58c9fabbf962e1"></script>
    <script>
    //아이티 윌 위도,경도 : 37.5005494, 127.0312363
//        var container = document.getElementById('map');
//        var options = {
//            center: new kakao.maps.LatLng(37.5005494, 127.0312363),
//            level: 3
//        };

//        var map = new kakao.maps.Map(container, options);//지도 생성
        
     // 마커가 표시될 위치입니다 
//        var markerPosition  = new kakao.maps.LatLng(37.5005494, 127.0312363); 

        // 마커를 생성합니다
//        var marker = new kakao.maps.Marker({
 //           position: markerPosition
              // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
 //           });
     // 마커가 지도 위에 표시되도록 설정합니다
 //       marker.setMap(map);

        // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
        // marker.setMap(null);

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.5005494, 127.0312363), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
  
// 마커를 표시할 위치입니다 
var position =  new kakao.maps.LatLng(37.5005494, 127.0312363);

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
  position: position,
  clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
});

// 아래 코드는 위의 마커를 생성하는 코드에서 clickable: true 와 같이
// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
// marker.setClickable(true);

// 마커를 지도에 표시합니다.
marker.setMap(map);

// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
var iwContent = '<div style="padding:5px;">itwill</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    content : iwContent,
    removable : iwRemoveable
});

// 마커에 클릭이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'click', function() {
      // 마커 위에 인포윈도우를 표시합니다
      infowindow.open(map, marker);  
});

        
    </script>
     <%--카카오 지도 API 끝 --%>
     
</body>
    
   
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
</body>
</html>