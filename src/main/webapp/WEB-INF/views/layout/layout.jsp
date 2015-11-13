<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/inc/common.jsp" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="zh-en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta name="description" content=""/>
  <meta name="author" content=""/>
  <title><decorator:title default="java-learning"/></title>
  <link rel="icon" href="${root}/favicon.ico" type="image/x-icon" />
  <link rel="shortcut icon" href="${root}/favicon.ico" type="image/x-icon" />

  <link rel="stylesheet" href="${root}/neon/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
  <link rel="stylesheet" href="${root}/neon/css/font-icons/entypo/css/entypo.css">
  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">
  <link rel="stylesheet" href="${root}/neon/css/bootstrap.css">
  <link rel="stylesheet" href="${root}/neon/css/neon-core.css">
  <link rel="stylesheet" href="${root}/neon/css/neon-theme.css">
  <link rel="stylesheet" href="${root}/neon/css/neon-forms.css">
  <link rel="stylesheet" href="${root}/neon/css/custom.css">
  <link rel="stylesheet" href="${root}/neon/css/skins/white.css">

  <script type="text/javascript" src="${root}/neon/js/jquery-1.11.0.min.js"></script>

  <decorator:head/>
</head>
<body class="page-body skin-white">
<div class="page-container horizontal-menu with-sidebar">
  <header class="navbar navbar-fixed-top"><!-- set fixed position by adding class "navbar-fixed-top" -->

    <div class="navbar-inner">
      <!-- logo -->
      <div class="navbar-brand">
        <a href="${root}/index.html">
          <img src="${root}/neon/images/logo-light@2x.png" width="88" alt=""/>
        </a>
      </div>

      <!-- main menu -->
      <ul class="navbar-nav">
        <li class="active">
          <a href="#">
            <i class="entypo-menu"></i>
            <span>Template</span>
          </a>
          <ul>
            <li class="active">
              <a href="${root}/default/templateTable.html">
                <span>Table Template</span>
              </a>
            </li>
            <li>
              <a href="${root}/default/templateForm.html">
                <span>Form Template</span>
              </a>
            </li>
            <li>
              <a href="${root}/default/templateTab.html">
                <span>Tab Template</span>
              </a>
            </li>
            <li>
              <a href="${root}/default/templateModal.html">
                <span>Modal Template</span>
              </a>
            </li>
            <li>
              <a href="${root}/default/templateSearch.html">
                <span>Search Template</span>
              </a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#">
            <i class="entypo-globe"></i>
            <span>Other</span>
          </a>
          <ul>
            <li>
              <a href="${root}/default/slider.html">
                <span>Slider</span>
              </a>
            </li>
          </ul>
        </li>
        <!-- Search Bar -->
        <li id="search" class="search-input-collapsed"><!-- add class "search-input-collapsed" to auto collapse search input -->
          <form method="get" action="">
            <input type="text" name="q" class="search-input" placeholder="Search something..."/>
            <button type="submit">
              <i class="entypo-search"></i>
            </button>
          </form>
        </li>
      </ul>

      <!-- notifications and other links -->
      <ul class="nav navbar-right pull-right">

        <!-- Profile Info -->
        <li class="profile-info dropdown"><!-- add class "pull-right" if you want to place this from right -->
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <img src="${root}/neon/images/thumb-1@2x.png" alt="" class="img-circle" width="25" />
            Art Ramadani
          </a>
          <ul class="dropdown-menu">
            <!-- Reverse Caret -->
            <li class="caret"></li>
            <!-- Profile sub-links -->
            <li>
              <a href="#">
                <i class="entypo-user"></i>
                Edit Profile
              </a>
            </li>
            <li>
              <a href="mailbox.html">
                <i class="entypo-mail"></i>
                Inbox
              </a>
            </li>
            <li>
              <a href="extra-calendar.html">
                <i class="entypo-calendar"></i>
                Calendar
              </a>
            </li>
            <li>
              <a href="#">
                <i class="entypo-clipboard"></i>
                Tasks
              </a>
            </li>
          </ul>
        </li>

        <li class="sep"></li>

        <!-- raw links -->
        <li class="dropdown profile-info pull-right">
          <li>
            <a href="#">Live Site</a>
          </li>
        </li>

        <li class="sep"></li>

        <li>
          <a href="extra-login.html">
            Log Out <i class="entypo-logout right"></i>
          </a>
        </li>

        <!-- mobile only -->
        <li class="visible-xs"><!-- open/close menu icon (do not remove if you want to enable menu on mobile devices) -->
          <div class="horizontal-mobile-menu visible-xs">
            <a href="#" class="with-animation"><!-- add class "with-animation" to support animation -->
              <i class="entypo-menu"></i>
            </a>
          </div>
        </li>
      </ul>
    </div>
  </header>

  <div class="sidebar-menu">

    <ul id="main-menu" class="multiple-expanded auto-inherit-active-class">
      <!-- add class "multiple-expanded" to allow multiple submenus to open -->
      <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
      <li class="opened active">
        <a href="#">
          <i class="entypo-flow-tree"></i>
          <span>Menu Levels</span>
        </a>
        <ul>
          <li class="active">
            <a href="#">
              <i class="entypo-flow-line"></i>
              <span>Menu Level 1.1</span>
            </a>
          </li>
          <li>
            <a href="#">
              <i class="entypo-flow-line"></i>
              <span>Menu Level 1.2</span>
            </a>
          </li>
          <li>
            <a href="#">
              <i class="entypo-flow-line"></i>
              <span>Menu Level 1.3</span>
            </a>
            <ul>
              <li>
                <a href="#">
                  <i class="entypo-flow-parallel"></i>
                  <span>Menu Level 2.1</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="entypo-flow-parallel"></i>
                  <span>Menu Level 2.2</span>
                </a>
                <ul>
                  <li>
                    <a href="#">
                      <i class="entypo-flow-cascade"></i>
                      <span>Menu Level 3.1</span>
                    </a>
                    <ul>
                      <li>
                        <a href="#">
                          <i class="entypo-flow-branch"></i>
                          <span>Menu Level 4.1</span>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li>
                    <a href="#">
                      <i class="entypo-flow-cascade"></i>
                      <span>Menu Level 3.2</span>
                    </a>
                  </li>
                </ul>
              </li>
              <li>
                <a href="#">
                  <i class="entypo-flow-parallel"></i>
                  <span>Menu Level 2.3</span>
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </li>
    </ul>

  </div>

  <div class="main-content">

    <decorator:body/>

    <footer class="main">
      &copy; 2015 <strong>java-learning</strong>
    </footer>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal-template">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Modal Template</h4>
      </div>

      <div class="modal-body">

        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="field-1" class="control-label">Name</label>
              <input type="text" class="form-control" id="field-1" placeholder="John">
            </div>
          </div>

          <div class="col-md-6">
            <div class="form-group">
              <label for="field-2" class="control-label">Surname</label>
              <input type="text" class="form-control" id="field-2" placeholder="Doe">
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label for="field-3" class="control-label">Address</label>
              <input type="text" class="form-control" id="field-3" placeholder="Address">
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-4">
            <div class="form-group">
              <label for="field-4" class="control-label">City</label>
              <input type="text" class="form-control" id="field-4" placeholder="Boston">
            </div>
          </div>

          <div class="col-md-4">
            <div class="form-group">
              <label for="field-5" class="control-label">Country</label>
              <input type="text" class="form-control" id="field-5" placeholder="United States">
            </div>
          </div>

          <div class="col-md-4">
            <div class="form-group">
              <label for="field-6" class="control-label">Zip</label>
              <input type="text" class="form-control" id="field-6" placeholder="123456">
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group no-margin">
              <label for="field-7" class="control-label">Personal Info</label>
              <textarea class="form-control autogrow" id="field-7" placeholder="Write something about yourself"></textarea>
            </div>
          </div>
        </div>

      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-info">Save changes</button>
      </div>
    </div>
  </div>
</div>

<link rel="stylesheet" href="${root}/neon/js/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="${root}/neon/js/rickshaw/rickshaw.min.css">
<link rel="stylesheet" href="${root}/neon/js/zurb-responsive-tables/responsive-tables.css">

<!-- Bottom Scripts -->
<script type="text/javascript" src="${root}/neon/js/gsap/main-gsap.js"></script>
<script type="text/javascript" src="${root}/neon/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
<script type="text/javascript" src="${root}/neon/js/bootstrap.js"></script>
<script type="text/javascript" src="${root}/neon/js/joinable.js"></script>
<script type="text/javascript" src="${root}/neon/js/resizeable.js"></script>
<script type="text/javascript" src="${root}/neon/js/neon-api.js"></script>
<script type="text/javascript" src="${root}/neon/js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script type="text/javascript" src="${root}/neon/js/jvectormap/jquery-jvectormap-europe-merc-en.js"></script>
<script type="text/javascript" src="${root}/neon/js/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="${root}/neon/js/rickshaw/vendor/d3.v3.js"></script>
<script type="text/javascript" src="${root}/neon/js/rickshaw/rickshaw.min.js"></script>
<script type="text/javascript" src="${root}/neon/js/raphael-min.js"></script>
<script type="text/javascript" src="${root}/neon/js/morris.min.js"></script>
<script type="text/javascript" src="${root}/neon/js/toastr.js"></script>
<script type="text/javascript" src="${root}/neon/js/zurb-responsive-tables/responsive-tables.js"></script>
<script type="text/javascript" src="${root}/neon/js/neon-custom.js"></script>
<script type="text/javascript" src="${root}/neon/js/neon-demo.js"></script>

<!-- syntaxhighlighter -->
<link type="text/css" rel="stylesheet" href="${root}/assets/syntaxhighlighter/css/shCoreEclipse.css"/>
<script type="text/javascript" src="${root}/assets/syntaxhighlighter/js/shCore.js"></script>
<script type="text/javascript" src="${root}/assets/syntaxhighlighter/js/shBrushXml.js"></script>
<script type="text/javascript">SyntaxHighlighter.all();</script>

<script type="text/javascript" src="${root}/assets/js/fire.js"></script>
<script type="text/javascript" src="${root}/assets/js/ajaxupload.js"></script>
<script type="text/javascript" src="${root}/assets/js/ajaxloading.js"></script>

</body>
</html>