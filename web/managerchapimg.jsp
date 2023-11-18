<%-- 
    Document   : managerchapimg
    Created on : Jul 17, 2023, 10:44:22 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-qTlOb+HwL6g+AeZUdbN1xgSs0/fuJIlY9IFc4P67WQvPtznGRXkpsfQHaCW7d/2hqus9Q+jvKjy3naF/ELzt8A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .editbox{
                width: 95%;
                height: auto;
                background-color: gainsboro;
                border-radius: 10px;
                margin: auto;
            }

            .edittable{
                width: 95%;
                min-height: 600px;
                height: auto;
                border-radius: 10px;
                margin: auto;
                background-color: ghostwhite;

            }

            .edititem{
                width: 98%;
                height: 300px;
                display: flex;
                margin-top: 5px;
                margin: auto;
                border: 1px #01020e solid;
            }

            .chapimg{
                width: 220px;
                height: 240px;
                border: 1px #4caf50 solid;
                margin: auto;
            }

            .editbanner{
                width: 700px;
                height: 240px;
                border: 1px #4caf50 solid;
                margin: auto;
                display: flex;
                justify-content: space-around;
                align-items: center;
            }

            .addchapimgbox{
                width: 95%;
                height: 150px;
                border: 1px #01020e solid;
                margin: auto;
                border-radius: 10px;
                background-color: #ffffff;
            }
        </style>
    </head>
    <body>
        <a href="/WebTruyen/edittor?bid=${requestScope.bid}" ><i class="fa-solid fa-backward fa-2xl" style="margin: auto"></i></a>
        <a href="/WebTruyen/home" ><i class="fa-solid fa-house fa-2xl" style="margin: auto"></i></a>
        <div class="editbox">
            
            <div class="addchapimgbox">
                <div class="book_chap">${requestScope.content}</div>
                <form action="chapedittor" method="post">
                    <div class="append">

                        Nội dung chap&nbsp;&nbsp;
                        <input type="text" placeholder="Thay đổi nội dung" name="ncontent"><button type="submit" name="button" value="changecontent">Gửi</button><br>
                        <hr>
                        BookId:
                        <input type="text" name="bid" value="${requestScope.bid}" readonly="" placeholder="${requestScope.bid}" style="width: 50px;">
                        ChapterID:
                        <input type="text" name="chid" value="${requestScope.chid}" readonly="" placeholder="${requestScope.chid}" style="width: 50px;">
                        Thêm một trang
                        <input type="text" placeholder="Ảnh trang" name="img">
                        <button type="submit" name="button" value="appendimg"><i class="fa-solid fa-plus"></i></button>


                    </div>
                </form>
            </div>
            <div class="edittable">
                <c:forEach items="${requestScope.cilist}" var="ci">
                    <form action="chapedittor" method="post">
                        <div class="edititem">
                            <div class="chapimg"><img src="${ci.getChapterImgSrc()}" width="220px" height="240px" alt="alt"/></div>
                            <div class="editbanner">

                                ID:<input type="text" name="contentId" value="${ci.getContentId()}" placeholder="${ci.getContentId()}" style="width: 50px;">
                                <input type="text" name="chid" value="${requestScope.chid}" readonly="" placeholder="${requestScope.chid}" style="width: 50px;">
                                <input type="text" name="bid" value="${requestScope.bid}" readonly="" placeholder="${requestScope.bid}" style="width: 50px;">
                                <button name="button" type="submit" value="delete"><i class="fa-solid fa-trash"></i>Delete</button>
                                <button name="button" type="submit" value="replace"><i class="fa-solid fa-arrow-up-from-bracket"></i>Replace</button>
                                <input type="text" name="reimg" placeholder="Ảnh thay thế">
                                <p>${ci.getImgPage()}</p>

                            </div>

                        </div>
                    </form>
                </c:forEach>
            </div>

        </div>
        <script src="https://kit.fontawesome.com/a1e04d9f7f.js" crossorigin="anonymous"></script>
    </body>
</html>
