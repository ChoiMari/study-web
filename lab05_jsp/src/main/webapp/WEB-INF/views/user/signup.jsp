<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Lab 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid">
        <!-- 회원 가입 양식(form) -->
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>회원가입</h2>
                </div>
                <div class="card-body">
                    <c:url var="signUpPage" value="/user/signup" />
                    <form method="post" action="${signUpPage}">
                        <div class="mt-2">
                            <label for="userid" class="form-label">아이디</label>
                            <input type="text" id="userid" class="form-control" 
                                name="userid" required autofocus /><!-- input태그에 name속성 준것만 submit됨. -->
                        </div>
                        <div class="mt-2">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" id="password" class="form-control" 
                                name="password" required />
                        </div>
                        <div class="mt-2">
                            <label for="email" class="form-label">이메일</label>
                            <input type="email" id="email" class="form-control" 
                                name="email" required />
                        </div>
                        <div class="mt-2">
                            <input type="submit" class="form-control btn btn-outline-success" 
                                value="작성완료" /><!-- input태그에 name속성 준것만 submit됨. -->
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </div>    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>
--%>


<%-- 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>sign up</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- MATERIAL DESIGN ICONIC FONT -->
        <link rel="stylesheet" href="../signup/fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
        
        <!-- STYLE CSS -->
        <link rel="stylesheet" href="../signup/css/style.css">
    </head>

    <body>

        <div class="wrapper">
            <div class="image-holder">
                <img src="../signup/images/registration-form-8.jpg" alt="">
            </div>
            <div class="form-inner">
                <form id="signUpForm" action="">
                    <div class="form-header">
                        <h3>Sign up</h3>
                        <img src="../signup/images/sign-up.png" alt="" class="sign-up-icon">
                    </div>
                    <div class="form-group">
                        <label for="">User ID:</label>
                        <input id="userid" name="userid" type="text" class="form-control" data-validation="length alphanumeric" data-validation-length="3-12">
                    </div>
                    <div class="form-group" >
                        <label for="">Password:</label>
                        <input id="password" name="password" type="password" class="form-control" data-validation="length" data-validation-length="min8">
                    </div>
                    <div class="form-group">
                        <label for="">E-mail:</label>
                        <input id="email" name="email" type="text" class="form-control" data-validation="email">
                    </div>
                    <button id="btnCreate">create my account</button>
                    <div class="socials">
                        <p>Sign up with social platforms</p>
                        <a href="https://www.facebook.com/?locale=ko_KR" class="socials-icon">
                            <i class="zmdi zmdi-facebook"></i>
                        </a>
                        <a href="https://www.instagram.com/accounts/login/" class="socials-icon">
                            <i class="zmdi zmdi-instagram"></i>
                        </a>
                        <a href="https://x.com/i/flow/login?input_flow_data=%7B%22requested_variant%22%3A%22eyJsYW5nIjoia28ifQ%3D%3D%22%7D" class="socials-icon">
                            <i class="zmdi zmdi-twitter"></i>
                        </a>
                        <a href="https://www.tistory.com/auth/login" class="socials-icon">
                            <i class="zmdi zmdi-tumblr"></i>
                        </a>
                    </div>
                </form>
            </div>
            
        </div>
        
        <script src="../signup/js/jquery-3.3.1.min.js"></script>
        <script src="../signup/js/jquery.form-validator.min.js"></script>
        <script src="../signup/js/main.js"></script>
        <script src="../signup/js/user_signup.js"></script>
    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
--%>


<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>sign up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <!-- TODO: 회원 가입 양식(form) -->
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>
--%>

<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Lab 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid">
        <!-- 회원 가입 양식(form) -->
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>회원가입</h2>
                </div>
                <div class="card-body">
                    <c:url var="signUpPage" value="/user/signup" />
                    <form method="post" action="${signUpPage}">
                        <div class="mt-2">
                            <label for="userid" class="form-label">아이디</label>
                            <input type="text" id="userid" class="form-control" 
                                name="userid" required autofocus />
                        </div>
                        <div class="mt-2">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" id="password" class="form-control" 
                                name="password" required />
                        </div>
                        <div class="mt-2">
                            <label for="email" class="form-label">이메일</label>
                            <input type="email" id="email" class="form-control" 
                                name="email" required />
                        </div>
                        <div class="mt-2">
                            <input type="submit" class="form-control btn btn-outline-success" 
                                value="작성완료" />
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </div>    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>

--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>sign up</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- MATERIAL DESIGN ICONIC FONT -->
        <link rel="stylesheet" href="../signup/fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
        
        <!-- STYLE CSS -->
        <link rel="stylesheet" href="../signup/css/style.css">
    </head>

    <body>

        <div class="wrapper">
            <div class="image-holder">
                <img src="../signup/images/registration-form-8.jpg" alt="">
            </div>
            <div class="form-inner">
                <form id="signUpForm" action="">
                    <div class="form-header">
                        <h3>Sign up</h3>
                        <img src="../signup/images/sign-up.png" alt="" class="sign-up-icon">
                    </div>
                    <div class="form-group">
                        <label for="">User ID:</label>
                        <input id="userid" name="userid" type="text" class="form-control" data-validation="length alphanumeric" data-validation-length="3-12">
                    </div>
                    <div class="form-group" >
                        <label for="">Password:</label>
                        <input id="password" name="password" type="password" class="form-control" data-validation="length" data-validation-length="min8">
                    </div>
                    <div class="form-group">
                        <label for="">E-mail:</label>
                        <input id="email" name="email" type="text" class="form-control" data-validation="email">
                    </div>
                    <button id="btnCreate">create my account</button>
                    <div class="socials">
                        <p>Sign up with social platforms</p>
                        <a href="https://www.facebook.com/?locale=ko_KR" class="socials-icon">
                            <i class="zmdi zmdi-facebook"></i>
                        </a>
                        <a href="https://www.instagram.com/accounts/login/" class="socials-icon">
                            <i class="zmdi zmdi-instagram"></i>
                        </a>
                        <a href="https://x.com/i/flow/login?input_flow_data=%7B%22requested_variant%22%3A%22eyJsYW5nIjoia28ifQ%3D%3D%22%7D" class="socials-icon">
                            <i class="zmdi zmdi-twitter"></i>
                        </a>
                        <a href="https://www.tistory.com/auth/login" class="socials-icon">
                            <i class="zmdi zmdi-tumblr"></i>
                        </a>
                    </div>
                </form>
            </div>
            
        </div>
        
        <script src="../signup/js/jquery-3.3.1.min.js"></script>
        <script src="../signup/js/jquery.form-validator.min.js"></script>
        <script src="../signup/js/main.js"></script>
        <script src="../signup/js/user_signup.js"></script>
    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>