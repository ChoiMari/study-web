<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
     <!--initial-scale=1는 브라우저 기본 비율 이용 -->
    <title>Lab 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid"> <!-- 바디전체를 반응형 웹으로 만드려고 속성 추가함 -->
        <c:set var="pageTitle" value="Home" scope="page" />
        <%-- pageContext.setAttribute("pageTitle", "Home");와 같음 --%>
     
        <%@ include file="./fragments/header.jspf" %>
    
    </div>
    <div>
        <CENTER><img src="https://news.nateimg.co.kr/orgImg/hm/2021/09/05/202109050559572427275_20210905060033_01.jpg"  alt="오잉" /></CENTER>
    </div>
    <br/>
    <div>
        <center>
            <iframe width="560" height="315" src="https://www.youtube.com/embed/YW5DCpi-t4U?si=NBhrY8C-OkMBy8eX&amp;controls=0&amp;autoplay=1&amp;mute=1&loop=1" title="YouTube video player" frameborder="0" allow="autoplay"></iframe>
        </center>
        <center>    
            <iframe width="560" height="315" src="https://www.youtube.com/embed/YO0BOLV9hxo?si=c9zOaq3yrDkyIrOc&amp;controls=0&amp;autoplay=1&amp;mute=1&loop=1"
                title="YouTube video player" frameborder="0" allow="autoplay" >
            </iframe>  
            <iframe width="560" height="315" src="https://www.youtube.com/embed/RPMb7LDAmP0?si=pDNw0FdiXg0JZpoG&amp;controls=0&amp;autoplay=1&amp;mute=1&loop=1"        title="YouTube video player" frameborder="0" allow="autoplay">
            </iframe>
        </center>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>