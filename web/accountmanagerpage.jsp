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
            <div class="dashboard row">
                <div class="right-controll col-md-4">
                    <div class="summaryinfo"><a href="#" onclick="">Thong tin chung</a></div>
                    <div class="authorasign"><a href="#" onclick="">Truyen da dang</a></div>
                    <div class="changeinfo"><a href="#" onclick="">Thay doi mat khau</a></div>
                </div>
                <div class="left-content col-md-8">
                    <div class="left-inner-content summary" style="display: none">
                        <div class="avata"><img src="src" width="85px" height="85px" alt="alt"/><p>Username</p></div>
                        <div class="otherinfo">
                            <p style="margin-left: 10px;">Email</p>
                            <textarea disabled="" style="width: 300px; height: 30px; margin-left: 10px;">usergmail</textarea>
                            <p style="margin-left: 10px;">Role</p>
                            <textarea disabled="" style="width: 100px; height: 30px; margin-left: 10px;">doc gia</textarea>
                        </div>

                    </div>

                    <!-- ADMIN  -->
                    <div class="left-inner-content author" style="display: block">
                        <div class="managerscreen">
                            <div class="managerbox">
                                <div class="box"><a href="#" onclick="request()"><buttoon>yeu cau</buttoon></a></div>
                                <div class="box"><a href="#" onclick="uploadedbook()"><buttoon>Truyen dang dang</buttoon></a></div>
                                <div class="box"><a href="#" onclick="typemanager()"><buttoon>The loai</buttoon></a></div>
                                <div class="box"><a href="#" onclick="allauthor()"><buttoon>Dich gia</buttoon></a></div>
                            </div>
                            <div class="manageritem">
                                <div class="managerajax" style="width: 98%; height: auto; min-height: 400px; margin: auto; background-color: gainsboro">
                                    <div class="authormanager" style="width: 98%; height: auto; min-height: 395px; margin: auto; background-color: white">
                                       
                                            </div>
                                            <div class="disinherit" style="display: flex; flex-direction: column; margin: auto; width: 95%; height: 350px;justify-content: center; align-items: center">
                                                <i class="fa-solid fa-triangle-exclamation fa-2xl" style="color: #f92424; font-size: 100px"></i>
                                                <p style="font-size: 17px; margin-top: 40px">!Bạn đã bị hủy quyền đăng truyện!</p>
                                                <p style="font-size: 12px"><i class="fa-regular fa-hand-point-up fa-rotate-90" style="color: #2c6ae8;"></i>hãy liên hệ với admin để biết chi tiết</p>
                                            </div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <!--Author -->
                    <div class="left-inner-content author" style="display: none">
                        <div class="managerscreen">

                            <div class="manageritem">
                                <div class="managerajax">
                                    <div class="authorinf">
                                        <p class="pseudonym">Chuyen cua ban</p>
                                    </div>
                                    <div class="sign">
                                        <p>Ten dang ki: Mariaoza</p>
                                        <p>Tai Khoang dang ki: LongPham</p>

                                    </div>
                                    <div class="uploaded">
                                        <table border="1">
                                            <tr>
                                                <th>Bìa</th>
                                                <th>Tên</th>
                                                <th>Edit</th>
                                            </tr>

                                            <tr>
                                                <td><img src="src" alt="alt" width="100px" height="120px"/></td>
                                                <td>${b.getBookName()}</td>
                                                <td><a href="edittor?bid="><i class="fa-solid fa-pen"></i></a></td>
                                            </tr>



                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>



                    <div class="left-inner-content author" style="display: none">
                        <div class="intro-author">
                            <textarea style="width: 90%; height: 150px;" placeholder="gioi thieu ve team dich cua ban" id="intro"></textarea>
                            <div class="updateinfo" style="display: flex; flex-direction: column;">
                                <label>Email</label>
                                <input type="text" name="email" id="email">
                                <label>Tên dịch giả (ký danh)</label>
                                <input type="text" name="authorsign" id="authorsign">
                            </div>
                            <button onclick="beAuthor()">Dang kí tac gia</button>
                        </div>

                    </div>

                    <div class="changepass" style="display: none">
                        <div class="authorinf">
                            <p class="pseudonym">change your password</p>
                        </div>

                        <div class="form-row">
                            <label class="form-label">Old pass:</label>
                            <input type="text" name="oldpass" id="oldpass" class="form-input">
                        </div>

                        <div class="form-row">
                            <label class="form-label">New pass:</label>
                            <input type="text" name="newpass" id="newpass" class="form-input">
                        </div>

                        <div class="form-row">
                            <label class="form-label">Confirm new pass:</label>
                            <input type="text" name="cfnewpass" id="cfnewpass" class="form-input">
                        </div>
                        <button onclick="changepass()">Change</button>

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
