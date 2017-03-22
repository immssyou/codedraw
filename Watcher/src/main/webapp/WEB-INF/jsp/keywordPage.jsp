<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Watcher</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!--Font-Awesome CSS-->
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Sidebar CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <!--Custom Watcher CSS-->
    <link href="css/watcher.css" rel="stylesheet" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
    <div id="wrapper">
        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="/mainPage">WATCHER</a>
                </li>
                <li><a href="/mainPage">범죄예측</a></li>
                <li><a href="/simulationPage">시뮬레이션</a></li>
                <li><a href="/keywordPage">키워드</a></li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <!--navgation-->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li><a href="#menu-toggle" id="menu-toggle">☰</a></li>
                        <li><a href="#">Watcher 소개</a></li>
                        <li><a href="#">사용가이드</a></li>
                        <li><a href="/developerInfo">개발진</a></li>
                    </ul>
                </div>
            </nav>

            <div class="keword-nav">
                <ul class="nav navbar-nav" id="keword-navbar">
                    <li><a href="keywordPage">키워드</a></li>
                    <li><a href="wordcloudPage">워드클라우드</a></li>
                </ul>
            </div>

            <div class="container-fluid">
                <div class="col-lg-12" id="keword-info">각 나라별 트위터를 크로롤링 하고  범죄와 관련된 단어들을 분석 하여  많이 나온 단어 순서대로 보여줍니다.</div>
            </div>
            
            <!--Content-->
            <div class="container-fluid" id="keyword-content-wrapper">
                
                <div class="row">
                    <div class="col-lg-4">
                        <section id="korea">
                        <h3 class="title keword alignLeft"><img src="img/country/korea.png" alt="korea" />대한민국</h3>
                            <div class="box keword">
                                <ol class="kewordRank">
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">1</em><span class="koreaData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">2</em><span class="koreaData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">3</em><span class="koreaData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">4</em><span class="koreaData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">5</em><span class="koreaData"></span>
                                        </div>
                                    </li>
                                </ol>
                            </div>
                        </section>
                    </div>
                    <div class="col-lg-4">
                        <section id="germany">
                        <h3 class="title keword alignLeft"><img src="img/country/germany.png" alt="germany" />독일</h3>
                            <div class="box keword">
                                <ol class="kewordRank">
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">1</em><span class="germanyData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">2</em><span class="germanyData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">3</em><span class="germanyData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">4</em><span class="germanyData"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">5</em><span class="germanyData"></span>
                                        </div>
                                    </li>
                                </ol>
                            </div>
                        </section>
                    </div>
                    <div class="col-lg-4">
                        <section id="usa">
                            <h3 class="title keword alignLeft"><img src="img/country/usa.png" alt="usa" />미국</h3>
                            <div class="box keword">
                                <ol class="kewordRank">
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">1</em><span class="usa"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">2</em><span class="usa"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">3</em><span class="usa"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">4</em><span class="usa"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">5</em><span class="usa"></span>
                                        </div>
                                    </li>
                                </ol>
                            </div>
                        </section>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-4">
                        <section id="japan">
                            <h3 class="title keword alignLeft"><img src="img/country/japan.png" alt="japan" />일본</h3>
                            <div class="box keword">
                                <ol class="kewordRank">
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">1</em><span class="japan"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">2</em><span class="japan"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">3</em><span class="japan"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">4</em><span class="japan"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">5</em><span class="japan"></span>
                                        </div>
                                    </li>
                                </ol>
                            </div>
                        </section>
                    </div>
                    <div class="col-lg-4">
                        <section id="france">
                            <h3 class="title keword alignLeft"><img src="img/country/france.png" alt="france" />프랑스</h3>
                            <div class="box keword">
                                <ol class="kewordRank">
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">1</em><span class="france"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">2</em><span class="france"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">3</em><span class="france"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">4</em><span class="france"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">5</em><span class="france"></span>
                                        </div>
                                    </li>
                                </ol>
                            </div>
                        </section>
                    </div>
                    <div class="col-lg-4">
                        <section id="russia">
                            <h3 class="title keword alignLeft"><img src="img/country/russia.png" alt="russia" />러시아</h3>
                            <div class="box keword">
                                <ol class="kewordRank">
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">1</em><span class="russia"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">2</em><span class="russia"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">3</em><span class="russia"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">4</em><span class="russia"></span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="kewordRankData">
                                            <em class="num">5</em><span class="russia"></span>
                                        </div>
                                    </li>
                                </ol>
                            </div>
                        </section>
                    </div>
                </div>
                <div class="control-page"></div>
            </div>
        </div>
        
        
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Menu Toggle Script -->
    <script>
        $("#menu-toggle").click(function (e) {
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");
        });
    </script>
    <script src="js/keyword.js"></script>
</body>
</html>
