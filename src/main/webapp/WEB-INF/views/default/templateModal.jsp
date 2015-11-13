<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/inc/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Modal Template</title>
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
    <strong>Modal Template</strong>
  </li>
</ol>

<h1 id="overview" class="page-header">Modal Template</h1>

<div class="col-md-12">
  <div class="row">
    <div class=" col-md-offset-4 col-md-4">
      <a href="javascript:$('#modal-template').modal('show');" class="btn btn-blue">Show Modal</a>
    </div>
  </div>

  <div class="highlight">
      <pre class="brush: xml;">
        <a href="javascript:$('#modal-tpl').modal('show');" class="btn btn-default">Show Modal</a>
        <div class="modal fade" id="modal-tpl">
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
      </pre>
  </div>
</div>

</body>
</html>
