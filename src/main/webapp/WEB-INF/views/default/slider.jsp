<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/inc/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <title>漂亮的轮显</title>
  <link rel="stylesheet" href="${root}/assets/css/slider/default.min.css"/>
  <link rel="stylesheet" href="${root}/assets/css/slider/slider.css"/>
  <script type="text/javascript" src="${root}/assets/js/slider/default.min.js"></script>
</head>
<body>

<ol class="breadcrumb bc-3">
  <li>
    <a href="index.html"><i class="entypo-home"></i>Home</a>
  </li>
  <li>
    <a href="#">Other</a>
  </li>
  <li class="active">
    <strong>Slider</strong>
  </li>
</ol>

<h1 id="overview" class="page-header">Slider</h1>

<div class="row">
  <div class="col-md-12">
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span12">
          <div id="homeBanner" style="padding: 0;"></div>
        </div>
      </div>

      <div class="row-fluid">
        <div class="span12">
          <div class="alert alert-info shake shake-constant shake-slow">
            <p>.......... (((　ﾟдﾟ))) ,我自己都为我自己颤抖啊 |∀` )</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="${root}/assets/js/slider/slider.js"></script>

<script type="text/javascript">
  var slider = $("#homeBanner").slider({
    id    : "homeBanner",
    imgs  : [
      "${root}/assets/img/slider/1.png",
      "${root}/assets/img/slider/2.png",
      "${root}/assets/img/slider/3.png",
      "${root}/assets/img/slider/4.png",
      "${root}/assets/img/slider/5.png",
      "${root}/assets/img/slider/6.png"
    ],
    scale : 5 / 2,
    border: true,
    x     : 4,
    y     : 3
  });
</script>
</body>
</html>