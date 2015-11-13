<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/inc/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Search Template</title>
</head>
<body>

<ol class="breadcrumb bc-3">
  <li>
    <a href="index.html"><i class="entypo-home"></i>Home</a>
  </li>
  <li>
    <a href="#">Template</a>
  </li>
  <li class="active">
    <strong>Search Template</strong>
  </li>
</ol>

<h1 id="overview" class="page-header">Search Template</h1>

<div class="row">
  <div class="col-md-12">

    <div class="panel panel-primary" data-collapsed="0">

      <div class="panel-heading">
        <div class="panel-title">
          Default Form Inputs Template
        </div>

        <div class="panel-options">
          <a href="#" data-rel="collapse"><i class="entypo-down-open"></i></a>
        </div>
      </div>

      <div class="panel-body">

        <form role="form" class="form-horizontal form-groups-bordered" enctype="multipart/form-data" id="search-form" onsubmit="return false;">
          <div class="row form-group">

            <div>
              <label for="field-1" class="col-sm-1 control-label">Field 1</label>

              <div class="col-sm-2">
                <input type="text" class="form-control" id="field-1" name="field1" placeholder="Placeholder">
              </div>
            </div>

            <div class="has-error">
              <label for="field-4" class="col-sm-1 control-label">Error field</label>

              <div class="col-sm-2">
                <input type="text" class="form-control" id="field-4" name="field4" placeholder="Placeholder">
              </div>
            </div>

            <div>
              <label class="col-sm-1 control-label">Select List</label>

              <div class="col-sm-2">
                <select class="form-control" name="field3">
                  <option value="2">Option 1</option>
                  <option value="4">Option 2</option>
                  <option value="6">Option 3</option>
                  <option value="8">Option 4</option>
                  <option value="10">Option 5</option>
                </select>
              </div>
            </div>

            <div class="has-warning">
              <label for="field-5" class="col-sm-1 control-label">Warning field</label>

              <div class="col-sm-2">
                <input type="text" class="form-control" id="field-5" id="field5" placeholder="Placeholder">
              </div>
            </div>
          </div>

          <div class="row form-group">

            <div>
              <label for="field-7" class="col-sm-1 control-label">Field 7</label>

              <div class="col-sm-2">
                <input type="text" class="form-control" id="field-7" name="field7" placeholder="Placeholder">
              </div>
            </div>

            <div class="has-success">
              <label for="field-6" class="col-sm-1 control-label">Success field</label>

              <div class="col-sm-2">
                <input type="text" class="form-control" id="field-6" name="field6" placeholder="Placeholder">
              </div>
            </div>

            <div>
              <label class="col-sm-1 control-label">Select List</label>

              <div class="col-sm-2">
                <select class="form-control" name="field2">
                  <option value="1">Option 1</option>
                  <option value="3">Option 2</option>
                  <option value="5">Option 3</option>
                  <option value="7">Option 4</option>
                  <option value="9">Option 5</option>
                </select>
              </div>
            </div>

            <div>
              <div class="col-sm-offset-1 col-sm-1">
                <button type="submit" class="btn btn-blue" id="btn-search">search</button>
              </div>
              <div class="col-sm-1">
                <button type="reset" class="btn btn-default">reset</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>

    <div id="search-result-table">
      <table class="table table-striped">
        <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>1</td>
          <td>Arlind</td>
          <td>Nushi</td>
        </tr>
        <tr>
          <td>2</td>
          <td>Art</td>
          <td>Ramadani</td>
        </tr>
        <tr>
          <td>3</td>
          <td>Filan</td>
          <td>Fisteku</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script type="application/javascript">
  $(function () {
    $("#btn-search").on("click", function() {

      var url = '${root}/default/templateSearchTiles.html';
      var params = $('#search-form').serialize();
      ajaxHtml(url, params, function(ret) {
        $("#search-result-table").html(ret);
      }, false);
    });
  });
</script>

</body>
</html>
