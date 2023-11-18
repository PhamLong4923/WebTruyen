<%-- 
    Document   : signin
    Created on : Jun 22, 2023, 11:13:15 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap demo</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-qTlOb+HwL6g+AeZUdbN1xgSs0/fuJIlY9IFc4P67WQvPtznGRXkpsfQHaCW7d/2hqus9Q+jvKjy3naF/ELzt8A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="mycss.css" rel="stylesheet">

    </style>
</head>
<body>
    <div class="nt_wrapper">
        <div class="header navbar-header">
            <div class="navbar nt_navbar">
                <div class="container">
                    <div class="navbar-header">
                        <a class="logo" title="Truyện tranh online" href="/">
                            <img alt="Logo NetTruyen" src="imgsrc/logo-nettruyen.png" width="150" style="aspect-ratio:5">
                        </a>
                    </div>

                    <div class="navbar-form">
                        <div class="input-group">
                            <input type="text" class="searchinput form-control" placeholder="Tìm truyện..." autocomplete="off">
                            <div class="input-group-btn">
                                <button type="submit" class="searchbutton btn btn-default">
                                    <img src="https://st.nettruyenmax.com/Data/Sites/1/skins/comic/images/btn_search.png" alt="#">
                                </button>
                            </div>
                        </div>

                    </div>

                    <ul class="nav-account">
                        <li class="dropdown">
                            <a data-toggle="dropdown" class="user-menu fn-userbox dropdown-toggle" href="javascript:void(0)">
                                <img class="fn-thumb" alt="Avatar" src="//st.ntcdntempv3.com/data/siteimages/anonymous.png">
                                <span style="list-style:none; font-size: 14px; color: black;">Cá nhân</span> 
                                <i class="fa fa-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a rel="nofollow" href="/Secure/Dashboard.aspx">
                                        <i class="fa fa-user"></i> Trang cá nhân</a>
                                </li>
                                <li>
                                    <a rel="nofollow" href="/Secure/ComicFollowed.aspx">
                                        <i class="fa fa-book"></i> Truyện theo dõi</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="user-logout" href="loginform.jsp">
                                        <i class="fa fa-sign-out"></i> Thoát</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <nav class="navbar navbar-expand-lg bg-body-tertiary main-nav">
                    <div class="container-fluid">

                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse main-nav-items" id="navbarNavDropdown">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="/WebTruyen/home">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Theo dõi</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Lich su</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Hot</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Tim truyen</a>
                                </li>
                                <li class="nav-item dropdown nav-dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        The loai
                                    </a>

                                    <ul class="dropdown-menu nt-menu">
                                        <c:forEach items="${requestScope.clist}" var="c">
                                            <li><a class="dropdown-item" href="#" onclick="loadType('${c.getCategoryId()}')">${c.getCategoryName()}</a></li>

                                        </c:forEach>

                                    </ul>

                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <div class="main">
            <div class="content row" id="chapterselectajax">

                <div class="chaptercontent">
                    <div class="controll-banner">
                        <div class="bookselectname">ten Truyen nay</div>
                        <div class="errorbutton"><a href=""><i class="fa-solid fa-triangle-exclamation" style="color: #fbd309;"></i>Báo Lỗi Chương</a></div>
                        <div class="notifycation"><i class="fa-solid fa-circle-info" style="color: #eee044;"></i>Sử dụng mũi tên trái (←) hoặc phải (→) để chuyển chapter</div>
                        <div class="transitionbutton">
                            <a class="prev control-button link-prev-chap" href="" ><i class="fa fa-chevron-left"></i></a>
                            <a class="next disable control-button" href=""><i class="fa fa-chevron-right"></i></a>
                        </div>
                    </div>

                    <div class="chapter-img">
                        <img src="src" width="100%" height="100%" alt="alt"/>                       
                    </div>
                    <div class="chapter-img">
                        <img src="src" width="100%" height="100%" alt="alt"/>                       
                    </div>
                    <div class="chapter-img">
                        <img src="src" width="100%" height="100%" alt="alt"/>                       
                    </div>
                    <div class="chapter-img">
                        <img src="src" width="100%" height="100%" alt="alt"/>                       
                    </div>

                </div>
                <div class="chapter-transition">
                    <div class="transition">
                        <a href="/" class="home"><i class="fa fa-home" aria-hidden="true"></i></a>
                        <a class="prev control-button link-prev-chap" href="" onclick="changeSelectValue(-1)"><i class="fa fa-chevron-left"></i></a>
                        <select onch id="mySelect" class="form-select" aria-label="Default select example" onchange="readselectchapter()">
                            <option  value="0">Open this select menu</option>
                            <option value="1" onclick="readChapter()">One</option>
                            <option value="2" onclick="">Two</option>
                            <option value="3" onclick="">Three</option>

                        </select>
                        <a class="next disable control-button" href="" onclick="changeSelectValue(1)"><i class="fa fa-chevron-right"></i></a>
                        <a href="" class="button is-danger is-rounded btn-subscribe subscribeBook"><i class="fa fa-heart"></i> <span style="font: 10px;">Theo dõi</span></a>
                    </div>
                </div>
                <div class="chaptercomment">
                    <div class="book-comments">
                        <div class="cm-relative">
                            <i class="fa-solid fa-comments"></i>Binh Luan(478)
                            <p>Hay donate de co nhieu truyen moi hon nhe</p>
                        </div>
                        <div class="cm-main">
                            <div class="yourcm">
                                <textarea class="comment-input" placeholder="hay binh luan van minh khong la ban acc"></textarea>
                                <button type="button" class="btn btn-success" onclick="comment()">Ðang</button>
                            </div>
                            <div class="othercm" id="commentsajax">
                                <div class="comment">
                                    <div class="avataruser"><img src="src" alt="alt" style="width: 60px; height: 60px; border-radius: 50%"></div>
                                    <div class="commentcontent"><p class="username">user name</p><p>heheheh truyen nhu cc</p></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <button class="action-button" style="background-color: #e24040"><i class="fa-sharp fa-solid fa-heart-circle-minus"></i></i>Bo Theo doi</button>
        </div>

    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/a1e04d9f7f.js" crossorigin="anonymous"></script>
    <script>
                            function loadType(cid) {
                                $.ajax({
                                    url: "/WebTruyen/load?cid=" + cid,

                                    cache: false,
                                    type: "get",

                                    success: function (response) {
                                        var row = document.getElementById("contentajax");
                                        row.innerHTML = response;

                                    },
                                    error: function (xhr) {

                                    }
                                });
                            }

                            function readbook(bid) {
                                $.ajax({
                                    url: "/WebTruyen/read?bid=" + bid,

                                    cache: false,
                                    type: "get",

                                    success: function (response) {
                                        var row = document.getElementById("contentajax");
                                        row.innerHTML = response;

                                    },
                                    error: function (xhr) {

                                    }
                                });
                            }

                            function changeSelectValue(increment) {
                                var select = document.getElementById("mySelect");
                                var selectedIndex = select.selectedIndex;

                                // Tăng hoặc giảm chỉ số của option
                                var newIndex = selectedIndex + increment;

                                // Kiểm tra giới hạn chỉ số
                                if (newIndex >= 0 && newIndex < select.options.length) {
                                    select.selectedIndex = newIndex;
                                }
                            }

                            






    </script>
</body>
</html>