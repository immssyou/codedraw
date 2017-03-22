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
    <link href="css/simulation.css" rel="stylesheet" />
    <!--Slider-->
    <link href="css/bootstrap-slider.css" rel="stylesheet"/>

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
            <div class="container-fluid" id="simulation-content-wrapper">
                <div class="row section1 container-fluid">
                    <div class="col-md-3">
                        <input id="tempSlider" data-slider-id='ex1Slider' type="text" data-slider-min="-20" data-slider-max="35" data-slider-step="1" data-slider-value="0" />
                        &nbsp;<strong class="slider-label">기온</strong>
                    </div>
                    <div class="col-md-3">
                        <input id="humiditySlider" data-slider-id='ex1Slider' type="text" data-slider-min="40" data-slider-max="80" data-slider-step="5" data-slider-value="0" />
                        &nbsp;<strong class="slider-label">습도</strong>
                    </div>
                    <!-- <div class="col-md-4">
                        <form>
                            <label class="radio-inline">
                                <input type="radio" name="optradio"><strong class="slider-label">살인</strong>
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optradio"><strong class="slider-label">강간</strong>
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optradio"><strong class="slider-label">강도</strong>
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optradio"><strong class="slider-label">폭력</strong>
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optradio"><strong class="slider-label">절도</strong>
                            </label>
                        </form>
                    </div> -->
                    <div class="col-md-2">
                        <button type="button" class="btn btn-primary pull-left"  onClick="loadSimul()">시뮬레이션</button>
                    </div>
                </div>
                <div class="row container-fluid">
                    <div class="col-md-12 section2">
                        <div id="google-map" class="panel panel-default"></div>
                    </div>
                </div>
                <div class="row container-fluid">
                    <div class="col-md-12 section2">
                        <div id="simulation-chart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <!--Slider Script-->
    <script src="js/bootstrap-slider.js"></script>
    <script>
        // With JQuery
        $("#tempSlider").slider({
            tooltip: 'always'
        });

        $("#humiditySlider").slider({
            tooltip: 'always'
        });

    </script>
    <!-- Menu Toggle Script -->
    <script>
        $("#menu-toggle").click(function (e) {
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");
        });
    </script>
    <!--Google Map Script-->
    <script>
        var map;
        function initMap() {
            map = new google.maps.Map(document.getElementById('google-map'), {
                center: { lat: 36.5, lng: 127 },
                zoom: 7
            });
        }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATSt9Q1F0A8IxaJxA7qvqQyCS2zKHTIYI&callback=initMap" async defer></script>
    <!--Zing Chart Script-->
    <script src="js/zingchart.min.js"></script>
    <script src="js/modules/zingchart-line.min.js"></script>
    <script src="js/simulationPage.js" charset='euc-kr'></script>
    <script src="js/chartConfig.js" charset='utf-8'></script>
</body>
</html>
