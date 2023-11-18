<%-- 
    Document   : editor
    Created on : Jul 16, 2023, 10:57:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi" translate="no">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-qTlOb+HwL6g+AeZUdbN1xgSs0/fuJIlY9IFc4P67WQvPtznGRXkpsfQHaCW7d/2hqus9Q+jvKjy3naF/ELzt8A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </head>
    <style>
        .editbox{
            display: flex;
        }

        .oldinfo{
            width: 46%;
            height: auto;
            background-color: gainsboro;
            border-radius: 10px;
            margin: auto;
        }

        .newinfo{
            width: 46%;
            height: auto;
            background-color: gainsboro;
            border-radius: 10px;
            margin: auto;
            margin-top: 25px;
        }

        .newinfobox{
            width: 98%;
            height: auto;
            margin: auto;
        }

        .oldinfobox{
            display: flex;

            width: 98%;
            height: auto;
            margin: auto;
        }

        .oldimg{
            width: 35%;
            height: auto;
            display: flex;
            margin-top: 10px;
            margin-left: 10px;

        }

        .type{
            width: 98%;
            margin: auto;
        }

        .checkbox-container {
            width: 98%;
            display: grid;
            grid-template-columns: repeat(4, 1fr); /* Thay đổi số cột thành 4 */
            gap: 0px;
        }

        input[type="checkbox"] {
            transform: scale(0.5); /* Tăng giá trị scale để checkbox gần với chữ cái */
        }

        .submitbutton{
            width: 98%;
            margin-left: 5px;
            border-radius: 5px;
        }

        .oldname h4 {
            word-wrap: break-word;
        }

        .summary{
            width: 95%;
            height: auto;
            min-height: 50px;
            margin: auto;
            margin-bottom: 5px;
            background-color: white;
            border-radius: 5px;
            word-wrap: break-word;
        }

        .category{
            margin: auto;
            margin-left: 10px;
            font-size: 11px;
        }

        .chaptereditbox{
            width: 90%;
            height: auto;
            min-height: 100px;
            background-color: gainsboro;
            border-radius: 10px;
            margin-top: 30px;
            margin-left: 60px;
        }

        .chapteritemajax{
            width: 98%;
            margin-top: 20px;
            margin: auto;
            height: 98%;
            min-height: 500px;
            background-color: white;
            border-radius: 5px;
        }

        .chapters{

            width: 89%; /* Độ rộng của thẻ */
            overflow: scroll; /* Thêm thanh cuộn */
            border: 1px solid black; /* Viền của thẻ */
            padding: 10px; /* Khoảng cách bên trong của thẻ */
            margin: auto;
            overflow-y: auto; /* Hiển thị thanh cuộn khi nội dung vượt quá chiều cao */
            max-height: 500px; /* Chiều cao tối đa của khung thanh cuộn */
            min-height: 100px; /* Chiều cao tối thiểu của khung thanh cuộn */

        }

        .inner-component {
            height: 28px;
            width: 100%;
            border-bottom: 1px grey solid;
            display: flex;
            justify-content: space-between;
            font-size: 15px;
        }

    </style>
    <body>
        <div class="editbox">
            

            <div class="newinfo">
                <div class="newinfobox">
                    <form action="createbook" method="post">
                        <div class="mb-3 choosefile">
                            <label for="formFile" class="form-label"> Anh bia truyen </label>
                            <input class="form-control" type="text" id="formFile" name="avatanew" placeholder="Nguon anh">

                        </div>
                        <div class="newname">

                            Tên truyen:<input type="text" name="nname" placeholder="tên moi">
                        </div>
                        
                        <div>
                            <p>Noi dung truyen</p>
                            <textarea name="nsummary" placeholder="Nd truyen" wrap="" style="width: 98%; height: 100px; margin: auto">
                            </textarea>

                        </div>
                        
                        <button type="submit" class="submitbutton" value="${requestScope.book.getBookId()}" name="button">Submit</button>

                        <p style="color: greenyellow; background-color: white;border-radius: 5px">${requestScope.notify}</p>
                    </form>

                </div>
            </div>

        </div>
        
        <script src="https://kit.fontawesome.com/a1e04d9f7f.js" crossorigin="anonymous"></script>
        <script>
                                        function limitCheckboxes(checkbox) {
                                            var checkboxes = document.getElementsByName('myCheckbox');
                                            var checkedCount = 0;

                                            for (var i = 0; i < checkboxes.length; i++) {
                                                if (checkboxes[i].checked) {
                                                    checkedCount++;
                                                }
                                            }

                                            if (checkedCount > 5) {
                                                checkbox.checked = false;
                                            }
                                        }
        </script>
    </body>
</html>
