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
        <title>Đọc Truyện Tranh Online-Website chính thức của NetTrom</title>
        <link rel="icon" type="image/png" href="//st.nettruyenmax.com/data/logos/favicon-nettruyen.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-qTlOb+HwL6g+AeZUdbN1xgSs0/fuJIlY9IFc4P67WQvPtznGRXkpsfQHaCW7d/2hqus9Q+jvKjy3naF/ELzt8A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="mycss.css" rel="stylesheet">

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
                                <input type="text" class="searchinput form-control" id="yourinput" placeholder="Tìm truyện..." autocomplete="off">
                                <div class="input-group-btn">
                                    <button type="submit" class="searchbutton btn btn-default" onclick="searchBook($('#yourinput').val())">
                                        <img src="https://st.nettruyenmax.com/Data/Sites/1/skins/comic/images/btn_search.png" alt="#">
                                    </button>
                                </div>
                            </div>

                        </div>
                        <c:choose>
                            <c:when test="${empty sessionScope.loginacc}">
                                <p><a href="loginform.jsp">login</a>/<a href="signinform.jsp">sign in</a></p>
                            </c:when>
                            <c:otherwise>
                                <ul class="nav-account">
                                    <li class="dropdown">
                                        <a data-toggle="dropdown" class="user-menu fn-userbox dropdown-toggle" href="javascript:void(0)">
                                            <img class="fn-thumb" alt="Avatar" src="${sessionScope.loginacc.getUserAvataImgSrc()}" style="width: 40px; height: 40px">
                                            <span style="list-style:none; font-size: 14px; color: black;">${sessionScope.loginacc.getUserName()}</span> 

                                        </a>
                                        <ul class="dropdown-menu">
                                            <li>
                                                <a rel="nofollow" href="#" onclick="personalinfo()">
                                                    <i class="fa fa-user"></i> Trang cá nhân</a>
                                            </li>
                                            <li>
                                                <a rel="nofollow" href="#" onclick="myfollowbook()">
                                                    <i class="fa fa-book"></i> Truyện theo dõi</a>
                                            </li>
                                            <li>
                                                <a rel="nofollow" class="user-logout" href="logout">
                                                    <i class="fa fa-sign-out"></i> Thoát</a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </c:otherwise>
                        </c:choose>

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
                                        <a class="nav-link" href="#" onclick="myfollowbook()">Theo dõi</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#" onclick="uhistory()">Lịch sử</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Hot</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" onclick="toggleSearchBlock()" href="#">Tìm truyện</a>
                                    </li>
                                    <li class="nav-item dropdown nav-dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            Thể loại
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
                <div class="content row">

                    <div class="advancesearch" style="display: none">
                        <h5>Thể loại truyện</h5>
                        <div class="type">
                            <div class="checkbox-container">
                                <c:forEach items="${requestScope.clist}" var="c">

                                    <input type="checkbox" name="myCheckbox" onclick="limitCheckboxes(this)" value="${c.getCategoryId()}" }><p class="category">${c.getCategoryName()}</p>
                                </c:forEach>

                            </div>
                        </div>
                        <div class="filter" style="display: flex; justify-content: space-evenly">

                            <h5>Tình Trạng</h5>
                            <select name="status">
                                <option value="2" >All</option> 
                                <option value="1">Đang tiến hành</option>
                                <option value="0">Đã hoàn thành</option>
                            </select>
                            <h5>Số lượng chương</h5>
                            <select name="numofchap">
                                <option value="0">>0</option>
                                <option value="10">>=10</option>
                                <option value="30">>=30</option>
                                <option value="50">>=50</option>
                                <option value="100">>=100</option>
                            </select>
                            <h5>Sắp xếp</h5>
                            <select name="sort">
                                <option value="0">Ngày đang giảm dần</option>
                                <option value="1">Ngày đang tăng dần</option>
                                <option value="2">Số chương giảm dần</option>
                                <option value="3">Số chương tăng dần</option>
                                <option value="4">Lượt thích cao</option>
                            </select>
                        </div>
                        <button onclick="advanceSearch()" style="margin-left: 450px; background-color: greenyellow;border-radius: 5px;">Tìm kiếm</button>
                    </div>

                    <div class="nt_content">
                        <!--  -->
                        <div id="contentajax" class="center center-content">
                            <div class="relative relative-content">
                                <span class="page-title">Truyện mới cập nhật</span>
                            </div>
                            <div class="items">
                                <c:forEach items="${requestScope.blist}" var="b">
                                    <div class="item item1">
                                        <div class="ct-image ct-top"><a href="#" onclick="readBook('${b.getBookId()}')"><img src="${b.getBookImg()}" width="100%" height="100%" alt="alt"/><a></div>
                                                    <div class="ct-info ct-foot">
                                                        <span class="nt-name">${b.getBookName()}</span>

                                                        <div class="new-chapter">
                                                            <span class="chapter-latest"><i class="fa-solid fa-thumbs-up"></i>:${b.getLike()}</span>
                                                            <span class="chapter-latest-time"><i class="fa-solid fa-eye"></i>:${b.getView()}</span>
                                                        </div>


                                                    </div>
                                                    </div>

                                                </c:forEach>                                                       
                                                </div>
                                                <div class="pagination-outter" id="paging">
                                                    <nav aria-label="Page navigation example paging">
                                                        <ul class="pagination">
                                                            <li class="page-item">
                                                                <a class="page-link" href="#" aria-label="Previous">
                                                                    <span aria-hidden="true">&laquo;</span>
                                                                </a>
                                                            </li>
                                                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">4</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">5</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">6</a></li>

                                                            <li class="page-item">
                                                                <a class="page-link" href="#" aria-label="Next">
                                                                    <span aria-hidden="true">&raquo;</span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </nav>
                                                </div>
                                                </div>
                                                </div>

                                                </div>
                                                </div>
                                                <div class="footer"></div>

                                                </div>
                                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                                                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
                                                <script src="https://kit.fontawesome.com/a1e04d9f7f.js" crossorigin="anonymous"></script>
                                                <script>
                                            function toggleSearchBlock() {
                                                var searchBlock = document.querySelector('.advancesearch');
                                                if (searchBlock.style.display === 'none') {
                                                    searchBlock.style.display = 'block';
                                                } else {
                                                    searchBlock.style.display = 'none';
                                                }
                                            }

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

                                            function limit(checkbox) {
                                                var checkboxes = document.getElementsByName('managercheckbox');
                                                var checkedCount = 0;

                                                for (var i = 0; i < checkboxes.length; i++) {
                                                    if (checkboxes[i].checked) {
                                                        checkedCount++;
                                                    }
                                                }

                                                if (checkedCount > 1) {
                                                    checkbox.checked = false;
                                                }
                                            }

                                            function comment(bid) {
                                                var commentValue = document.getElementById("comment-input").value;
                                                $.ajax({
                                                    url: "/WebTruyen/comment?yourcm=" + commentValue + "&bid=" + bid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {
                                                        var row = document.getElementById("commentsajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function changeSelectValue(increment) {
                                                var select = document.getElementById("mySelect");
                                                var selectedIndex = select.selectedIndex;
                                                var newIndex = selectedIndex + increment;

                                                if (newIndex >= 0 && newIndex < select.options.length) {
                                                    select.selectedIndex = newIndex;

                                                    readselectchapter();
                                                }
                                            }
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

                                            function advanceSearch() {
                                                var checkboxes = document.getElementsByName("myCheckbox");
                                                var selectedCheckboxes = [];
                                                for (var i = 0; i < checkboxes.length; i++) {
                                                    if (checkboxes[i].checked) {
                                                        selectedCheckboxes.push(checkboxes[i].value);
                                                    }
                                                }
                                                var status = document.getElementsByName("status")[0].value;
                                                var numofchap = document.getElementsByName("numofchap")[0].value;
                                                var sort = document.getElementsByName("sort")[0].value;
                                                var params =
                                                        "type=" +
                                                        encodeURIComponent(selectedCheckboxes.join(",")) +
                                                        "&status=" +
                                                        encodeURIComponent(status) +
                                                        "&numofchap=" +
                                                        encodeURIComponent(numofchap) +
                                                        "&sort=" +
                                                        encodeURIComponent(sort);
                                                $.ajax({
                                                    url: "/WebTruyen/advancesearch?" + params,

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

                                            function myfollowbook() {
                                                $.ajax({
                                                    url: "/WebTruyen/myfollow",

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

                                            function uhistory() {
                                                $.ajax({
                                                    url: "/WebTruyen/history",

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

                                            function readBook(bid) {
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

                                            function searchBook(keyword) {
                                                var string = document.getElementById("yourinput");
                                                $.ajax({
                                                    url: "/WebTruyen/search?keyname=" + keyword,

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

                                            function readchapter(chid) {

                                                $.ajax({
                                                    url: "/WebTruyen/readchapter?chid=" + chid,

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


                                            function personalinfo() {

                                                $.ajax({
                                                    url: "/WebTruyen/dashboard",

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

                                            function follow(bid) {

                                                $.ajax({
                                                    url: "/WebTruyen/follow?bid=" + bid,

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

                                            function like(bid) {

                                                $.ajax({
                                                    url: "/WebTruyen/like?bid=" + bid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var rp = response.toString();

                                                        window.alert(rp);

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function deletebook(bid) {

                                                $.ajax({
                                                    url: "/WebTruyen/deletebook?bid=" + bid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var rp = response.toString();

                                                        window.alert(rp);

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function follow(bid) {

                                                $.ajax({
                                                    url: "/WebTruyen/follow?bid=" + bid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var row = document.getElementById("followajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function addnewcategory() {
                                                var n = document.getElementById("cname").value;
                                                var t = document.getElementById("ctitile").value;
                                                $.ajax({
                                                    url: "/WebTruyen/addcategory?n=" + n + "&t=" + t,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var row = document.getElementById("categoryajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }





                                            function removecategory() {
                                                var selectedOption = document.querySelector('input[name="managercheckbox"]:checked');
                                                var cid = selectedOption ? selectedOption.value : null;
                                                $.ajax({
                                                    url: "/WebTruyen/removecategory?cid=" + cid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var row = document.getElementById("categoryajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function typemanager() {

                                                $.ajax({
                                                    url: "/WebTruyen/categorymanager",

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var row = document.getElementById("managerajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }



                                            function notification() {
                                                alert("Bạn phải đăng nhập để thực hiện hành động này.");
                                            }

                                            function summaryinfo() {
                                                var summaryDiv = document.querySelector('.summary');
                                                var authorDiv = document.querySelector('.author');
                                                var changepassDiv = document.querySelector('.changepass');

                                                summaryDiv.style.display = 'block';
                                                authorDiv.style.display = 'none';
                                                changepassDiv.style.display = 'none';
                                            }

                                            function uploaded() {
                                                var summaryDiv = document.querySelector('.summary');
                                                var authorDiv = document.querySelector('.author');
                                                var changepassDiv = document.querySelector('.changepass');

                                                summaryDiv.style.display = 'none';
                                                authorDiv.style.display = 'block';
                                                changepassDiv.style.display = 'none';
                                            }

                                            function manage() {
                                                var summaryDiv = document.querySelector('.summary');
                                                var authorDiv = document.querySelector('.author');
                                                var changepassDiv = document.querySelector('.changepass');

                                                summaryDiv.style.display = 'none';
                                                authorDiv.style.display = 'block';
                                                changepassDiv.style.display = 'none';
                                            }

                                            function changeinfo() {
                                                var summaryDiv = document.querySelector('.summary');
                                                var authorDiv = document.querySelector('.author');
                                                var changepassDiv = document.querySelector('.changepass');

                                                summaryDiv.style.display = 'none';
                                                authorDiv.style.display = 'none';
                                                changepassDiv.style.display = 'block';
                                            }

                                            function changepass() {
                                                var oldpass = document.getElementById("oldpass").value;
                                                var newpass = document.getElementById("newpass").value;
                                                var cfnewpass = document.getElementById("cfnewpass").value;
                                                $.ajax({
                                                    url: "/WebTruyen/changepass?oldpass=" + oldpass + "&newpass=" + newpass + "&cfnewpass=" + cfnewpass,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var rp = response.toString();

                                                        window.alert(rp);

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function beAuthor() {
                                                var intro = document.getElementById("intro").value;
                                                var email = document.getElementById("email").value;
                                                var authorsign = document.getElementById("authorsign").value;
                                                $.ajax({
                                                    url: "/WebTruyen/beauthor?intro=" + intro + "&email=" + email + "&authorsign=" + authorsign,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var rp = response.toString();

                                                        window.alert(rp);

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function request() {

                                                $.ajax({
                                                    url: "/WebTruyen/requestmanager",

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var row = document.getElementById("managerajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function uploadedbook() {
                                                $.ajax({
                                                    url: "/WebTruyen/uploadmanager",

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {
                                                        var row = document.getElementById("managerajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function removebook(bid) {
                                                $.ajax({
                                                    url: "/WebTruyen/removebook?bid=" + bid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {
                                                        var row = document.getElementById("bookboxajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function quicksearch() {
                                                var text = document.getElementById("quicksearch").value;
                                                $.ajax({
                                                    url: "/WebTruyen/quicksearch?text=" + text,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {
                                                        var row = document.getElementById("bookboxajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }


                                            function accept(uid) {

                                                $.ajax({
                                                    url: "/WebTruyen/accept?uid=" + uid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var row = document.getElementById("managerajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }

                                            function denife(uid) {

                                                $.ajax({
                                                    url: "/WebTruyen/denife?uid=" + uid,

                                                    cache: false,
                                                    type: "get",

                                                    success: function (response) {

                                                        var row = document.getElementById("managerajax");

                                                        row.innerHTML = response;

                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }
                                            function readselectchapter() {
                                                var selectElement = document.getElementById("mySelect");
                                                var selectedValue = selectElement.value;
                                                $.ajax({
                                                    url: "/WebTruyen/readchapter?chid=" + selectedValue,

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
                                            
                                            function allauthor(){
                                                $.ajax({
                                                    url: "/WebTruyen/authoraccmg",
                                                    cache:false,
                                                    type:"get",
                                                    success: function (response){
                                                        var row = document.getElementById("managerajax");
                                                        row.innerHTML = response;
                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }
                                            
                                            function disinherit(uid, role){
                                                $.ajax({
                                                    url: "/WebTruyen/disinherit?uid="+uid+"&role="+role,
                                                    cache: false,
                                                    type: 'get',
                                                    success: function (response) {
                                                        
                                                        var row = document.getElementById("authortableajax");
                                                        row.innerHTML = response;
                                                    },
                                                    error: function (xhr) {

                                                    }
                                                });
                                            }
                                            
                                            
                                            
                                            
                                        











                                                </script>
                                                </body>
                                                </html>