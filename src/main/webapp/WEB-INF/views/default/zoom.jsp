<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/inc/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>全屏预览</title>
  <link rel="stylesheet" type="text/css" href="${root}/assets/css/zoom/imageviewer.css">
  <style type="text/css">
    .htmleaf-container {
      margin: 0 auto;
      text-align: center;
      overflow: hidden;
    }

    #image-gallery-1 {
      max-width: 800px;
      margin: 50px auto;
    }

    #image-gallery-1 .gallery-items {
      float: left;
      height: 200px;
      margin-right: 10px;
      margin-bottom: 10px;
      cursor: pointer;
    }
  </style>
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
    <strong>Zoom</strong>
  </li>
</ol>

<h1 id="overview" class="page-header">Zoom</h1>

<div class="row">
  <div class="col-md-12">

    <article class="htmleaf-container">
      <p>图片全屏预览模式：点击图片可以切换到全屏查看图片的模式，全屏模式下可以缩放和平移图片。</p>
      <div id="image-gallery-1" class="cf">
        <img src="${root}/assets/img/zoom/1.jpg" data-high-res-src="${root}/assets/img/zoom/1_big.jpg" alt="" class="gallery-items">
        <img src="${root}/assets/img/zoom/2.jpg" data-high-res-src="${root}/assets/img/zoom/2_big.jpg" alt="" class="gallery-items">
        <img src="${root}/assets/img/zoom/3.jpg" data-high-res-src="${root}/assets/img/zoom/3_big.jpg" alt="" class="gallery-items">
        <img src="${root}/assets/img/zoom/4.jpg" data-high-res-src="${root}/assets/img/zoom/4_big.jpg" alt="" class="gallery-items">
      </div>
    </article>

    <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
      <p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
      <p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>
    </div>
  </div>
</div>

<script type="text/javascript" src="${root}/assets/js/zoom/imageviewer.min.js"></script>
<script type="text/javascript">
  $(function () {
    var viewer = ImageViewer();
    $('.gallery-items').click(function () {
      var imgSrc = this.src,
          highResolutionImage = $(this).data('high-res-img');

      viewer.show(imgSrc, highResolutionImage);
    });
  });
</script>
</body>
</html>
