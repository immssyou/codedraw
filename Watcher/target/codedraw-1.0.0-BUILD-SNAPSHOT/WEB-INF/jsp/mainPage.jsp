<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Watcher</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--Flat UI CSS-->
    <!--<link href="css/flat-ui.css" rel="stylesheet" />-->
    <link href="css/demo.css" rel="stylesheet" />
    <!--Font-Awesome CSS-->
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Sidebar CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <!--Custom Watcher CSS-->
    <link href="css/watcher.css" rel="stylesheet" />
    <link href="css/mainPage.css" rel="stylesheet" />
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
                <li class="sidebar-brand text-center">
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
            <!--Content-->
            <div class="container-fluid" id="content-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('01', false)" id="mSelBtn01">1월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('02', false)" id="mSelBtn02">2월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('03', false)" id="mSelBtn03">3월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('04', false)" id="mSelBtn04">4월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('05', false)" id="mSelBtn05">5월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('06', false)" id="mSelBtn06">6월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('07', false)" id="mSelBtn07">7월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('08', false)" id="mSelBtn08">8월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('09', false)" id="mSelBtn09">9월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('10', false)" id="mSelBtn10">10월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('11', false)" id="mSelBtn11">11월</button>
                            <button type="button" class="btn btn-primary mSelBtn" onclick="loadCrimeRateofArea('12', false)" id="mSelBtn12">12월</button>
                        </div>
                    </div>
                    <!--Google Map-->
                    <div class="col-lg-8">
                        <div id="google-map" class="panel panel-default"></div>
                        <!--Bar Chart-->
                        <div></div>
                    </div>
                    <!--Info-->
                    <div class="col-lg-4">
                        <!--Weather-->
                        <div class="row">
                            <!--Now Date-->
                            <div class="col-lg-4">
                                <div class="panel panel-weather">
                                    <div class="panel-header panel-header-weather text-center">
                                        <strong>현재 시각</strong>
                                    </div>
                                    <div class="panel-body">
                                        <div class="container-fluid text-center" id="clock"></div>
                                        <div class="container-fluid text-center" id="date"></div>
                                    </div>
                                </div>
                            </div>
                            <!--#Now Date-->
                            <!--Now weather info-->
                            <div class="col-lg-4">
                                <div class="panel panel-weather">
                                    <div class="panel-header panel-header-weather text-center" >
                                        <strong class="area">2015년 </strong><strong class="month"></strong><strong> 기상</strong>
                                    </div>
                                    <div class="panel-body">
                                        <div class="col-md-6">
                                            <img src="img/weather/cloudy.png" class="" />
                                        </div>
                                        <div class="col-md-6" id="weatherContainer2015">
                                            <strong>23°C</strong><br>
                                            <strong>44%</strong>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--#Now weather info-->
                            <!--Month weather info-->
                            <div class="col-lg-4">
                                <div class="panel panel-weather">
                                    <div class="panel-header panel-header-weather text-center" >
                                    <strong class="area">2014년 </strong><strong class="month"></strong><strong> 기상</strong>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <img src="img/weather/clear.png" class="img-responsive" />
                                            </div>
                                            <div class="col-md-6 text-center" id="weatherContainer2014">
                                                <div><strong>26°C</strong></div>
                                                <div><strong>32%</strong></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--#Month weather info-->
                        </div>
                        <!--#Weather-->
                        <div class="row">
                            <!--Donout chart 범죄예측률-->
                            <div class="col-lg-6">
                                <div class="panel panel-chart">
                                    <div class="panel-header panel-header-chart text-center"><strong class="area">서울</strong><strong> 2015 </strong><strong class="month"></strong><strong> 범죄 예측율</strong></div>
                                    <div id="crimeChart"></div>
                                </div>
                            </div>
                            <!--#Donout chart 범죄예측률-->
                            <!--Donout chart 2015년도 범죄율-->
                            <div class="col-lg-6">
                                <div class="panel panel-chart">
                                    <div class="panel-header panel-header-chart text-center"><strong class="area">서울</strong><strong> 2014 </strong><strong class="month"></strong><strong> 범죄 예측율</strong></div>
                                    <div id="preCrimeChart"></div>
                                </div>
                            </div>
                            <!--#Donout chart 2015년도 범죄율-->
                        </div>
                        <!--info table-->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-table">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th class="month"></th>
                                                    <th>2015</th>
                                                    <th>2014</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><strong>절도</strong></td>
                                                    <td class="curCrimeData">data</td>
                                                    <td class="preCrimeData">data</td>
                                                </tr>
                                                <tr>
                                                    <td><strong>강간</strong></td>
                                                     <td class="curCrimeData">data</td>
                                                    <td class="preCrimeData">data</td>
                                                </tr>
                                                <tr>
                                                    <td><strong>강도</strong></td>
                                                     <td class="curCrimeData">data</td>
                                                    <td class="preCrimeData">data</td>
                                                </tr>
                                                <tr>
                                                    <td><strong>살인</strong></td>
                                                     <td class="curCrimeData">data</td>
                                                    <td class="preCrimeData">data</td>
                                                </tr>
                                                <tr>
                                                    <td><strong>폭력</strong></td>
                                                     <td class="curCrimeData">data</td>
                                                    <td class="preCrimeData">data</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--#info table-->
                    </div>
                    <!--#info-->
                </div>
                <!--#row-->
                    <div id="lineChart">
                    </div>
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
    <script src="js/mainPage.js"></script>
    <!--Google Map Script--> 
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATSt9Q1F0A8IxaJxA7qvqQyCS2zKHTIYI&callback=initMap" async defer></script>
    <!--Zing Chart Script-->
    <script src="js/zingchart.min.js"></script>
    <script src="js/modules/zingchart-pie.min.js"></script>
    <script src="js/chartConfig.js" charset='utf-8'></script>
</body>
</html>
