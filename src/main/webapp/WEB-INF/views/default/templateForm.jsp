<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/inc/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Form Template</title>
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
    <strong>Form Template</strong>
  </li>
</ol>

<h1 id="overview" class="page-header">Form Template</h1>

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

        <form role="form" class="form-horizontal form-groups-bordered" enctype="multipart/form-data">

          <div class="form-group">
            <label for="field-1" class="col-sm-3 control-label">Field 1</label>

            <div class="col-sm-5">
              <input type="text" class="form-control" id="field-1" placeholder="Placeholder">
            </div>
          </div>

          <div class="form-group">
            <label for="field-2" class="col-sm-3 control-label">Disabled</label>

            <div class="col-sm-5">
              <input type="text" class="form-control" id="field-2" placeholder="Placeholder" disabled>
            </div>
          </div>

          <div class="form-group">
            <label for="field-3" class="col-sm-3 control-label">Password</label>

            <div class="col-sm-5">
              <input type="password" class="form-control" id="field-3" placeholder="Placeholder (Password)">
            </div>
          </div>

          <div class="form-group">
            <label for="field-file" class="col-sm-3 control-label">File Field</label>

            <div class="col-sm-5">
              <input type="file" class="form-control" id="field-file" placeholder="Placeholder">
            </div>
          </div>

          <div class="form-group">
            <label for="field-ta" class="col-sm-3 control-label">Textarea</label>

            <div class="col-sm-5">
              <textarea class="form-control" id="field-ta" placeholder="Textarea"></textarea>
            </div>
          </div>

          <div class="form-group">
            <label for="field-ta-auto" class="col-sm-3 control-label">Autogrow</label>

            <div class="col-sm-5">
              <textarea class="form-control autogrow" id="field-ta-auto" placeholder="I will grow as you type new lines."></textarea>
            </div>
          </div>

          <div class="form-group has-error">
            <label for="field-4" class="col-sm-3 control-label">Error field</label>

            <div class="col-sm-5">
              <input type="text" class="form-control" id="field-4" placeholder="Placeholder">
            </div>
          </div>

          <div class="form-group has-warning">
            <label for="field-5" class="col-sm-3 control-label">Warning field</label>

            <div class="col-sm-5">
              <input type="text" class="form-control" id="field-5" placeholder="Placeholder">
            </div>
          </div>

          <div class="form-group has-success">
            <label for="field-6" class="col-sm-3 control-label">Success field</label>

            <div class="col-sm-5">
              <input type="text" class="form-control" id="field-6" placeholder="Placeholder">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">Select List</label>

            <div class="col-sm-5">
              <select class="form-control">
                <option>Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
                <option>Option 4</option>
                <option>Option 5</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-offset-3 col-sm-1">
              <button type="submit" class="btn btn-blue">submit</button>
            </div>
            <div class="col-sm-1">
              <button type="reset" class="btn btn-default">reset</button>
            </div>
          </div>
        </form>

      </div>

    </div>

    <div class="highlight">
      <pre class="brush: xml;">
        <div class="panel panel-primary" data-collapsed="0">

          <div class="panel-body">

            <form role="form" class="form-horizontal form-groups-bordered" enctype="multipart/form-data">

              <div class="form-group">
                <label for="field-tpl-1" class="col-sm-3 control-label">Field 1</label>

                <div class="col-sm-5">
                  <input type="text" class="form-control" id="field-tpl-1" placeholder="Placeholder">
                </div>
              </div>

              <div class="form-group">
                <label for="field-tpl-2" class="col-sm-3 control-label">Disabled</label>

                <div class="col-sm-5">
                  <input type="text" class="form-control" id="field-tpl-2" placeholder="Placeholder" disabled>
                </div>
              </div>

              <div class="form-group">
                <label for="field-tpl-3" class="col-sm-3 control-label">Password</label>

                <div class="col-sm-5">
                  <input type="password" class="form-control" id="field-tpl-3" placeholder="Placeholder (Password)">
                </div>
              </div>

              <div class="form-group">
                <label for="field-tpl-file" class="col-sm-3 control-label">File Field</label>

                <div class="col-sm-5">
                  <input type="file" class="form-control" id="field-tpl-file" placeholder="Placeholder">
                </div>
              </div>

              <div class="form-group">
                <label for="field-tpl-ta" class="col-sm-3 control-label">Textarea</label>

                <div class="col-sm-5">
                  <textarea class="form-control" id="field-tpl-ta" placeholder="Textarea"></textarea>
                </div>
              </div>

              <div class="form-group">
                <label for="field-tpl-ta-auto" class="col-sm-3 control-label">Autogrow</label>

                <div class="col-sm-5">
                  <textarea class="form-control autogrow" id="field-tpl-ta-auto" placeholder="I will grow as you type new lines."></textarea>
                </div>
              </div>

              <div class="form-group has-error">
                <label for="field-tpl-4" class="col-sm-3 control-label">Error field</label>

                <div class="col-sm-5">
                  <input type="text" class="form-control" id="field-tpl-4" placeholder="Placeholder">
                </div>
              </div>

              <div class="form-group has-warning">
                <label for="field-tpl-5" class="col-sm-3 control-label">Warning field</label>

                <div class="col-sm-5">
                  <input type="text" class="form-control" id="field-tpl-5" placeholder="Placeholder">
                </div>
              </div>

              <div class="form-group has-success">
                <label for="field-tpl-6" class="col-sm-3 control-label">Success field</label>

                <div class="col-sm-5">
                  <input type="text" class="form-control" id="field-tpl-6" placeholder="Placeholder">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label">Select List</label>

                <div class="col-sm-5">
                  <select class="form-control">
                    <option>Option 1</option>
                    <option>Option 2</option>
                    <option>Option 3</option>
                    <option>Option 4</option>
                    <option>Option 5</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <div class="col-sm-offset-3 col-sm-1">
                  <button type="submit" class="btn btn-blue">submit</button>
                </div>
                <div class="col-sm-1">
                  <button type="reset" class="btn btn-default">reset</button>
                </div>
              </div>
            </form>

          </div>
        </div>
      </pre>
    </div>

  </div>
</div>

</body>
</html>
