<%-- 
    Document   : signinform
    Created on : May 20, 2023, 9:29:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            @font-face{
                font-family: Minecratften;
                src:url("MinecraftTen-VGORe.ttf");
            }

            .loginform{
                background-color: whitesmoke;
                width: 380px;
                height: 450px;
                padding: 10px;
            }

            .form{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                background-image: url("backgroundimg.jpg");
                height: 600px
            }

            .inputbox{
                font-size: 13px;
                height: 25px;
            }

            h1{
                text-align: center;
                font-family: Minecratften;
            }

            .signup{
                text-align: center;
            }

            .forgotpass{
                margin-left: 220px;
            }

            button{
                width: 370px;
                height: 45px;
                background-color: greenyellow;
                border-left-color: black;

            }

            button:hover{
                background-color: gray;
                transition-duration: 0.5s;
            }


        </style>
    </head>
    <body>
        <div class="form">
            <div class="loginform">
                <h1>SIGN IN</h1>
                <form action="signin" method="post">
                    <p class="signin">have Google account?<a href="signinform.jsp"> Sign up by Google account</a></p>
                    <p> Account name </p>
                    <input class="inputbox" type="text" name="usern" size="50" height="30px"><br>
                    <p> Password </p>
                    <input class="inputbox" type="password" name="userp" size="50" height="30px">
                    <p> Comfirm Password </p>
                    <input class="inputbox" type="password" name="cfuserp" size="50" height="30px">
                    <a href="loginform.jsp"><p class="forgotpass">Have account? Login</p></a>
                    <button type="submit" name="log-in">Sign in</button>
                    <p color="red">${requestScope.error}</p>
                </form>
            </div>

        </div>

    </body>
</html>
