<%-- 
    Document   : footer
    Created on : May 20, 2023, 9:28:54 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .footer{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                background-color: black;
                color: whitesmoke;
            }
            
            .logomojang_xbox{
                display: flex;
                flex-direction: row;
                justify-content: space-between
            }
            
            .logobrand{
                width: 150px;
                height: 150px;
                padding: 50px;
            }
            
            .logoXbox{
                width: 200px;
                height: 150px
            }
            
        </style>
    </head>
    <body>
        <div class="footer">
            <h2>FOLLOW MINECRAFT</h2>
            <div class="media">
                
            </div>
            
            <div class="logomojang_xbox">
                <img class="logobrand" src="footericon\mojang_img400x400.jpg">
                <img class="logobrand logoXbox" src="footericon\xboxlogo.png">
            </div>
        </div>
    </body>
</html>
