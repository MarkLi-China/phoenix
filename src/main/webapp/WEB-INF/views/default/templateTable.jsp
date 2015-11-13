<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/inc/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Table Template</title>
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
    <strong>Table Template</strong>
  </li>
</ol>

<h1 id="overview" class="page-header">Table Template</h1>

<div class="row">
  <div class="col-md-6">

    <h3>Simple Table</h3>

    <table class="table responsive">
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

    <div class="highlight">
      <pre class="brush: xml;">
        <table class="table responsive">
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
      </pre>
    </div>
  </div>

  <div class="col-md-6">

    <h3>Striped Rows</h3>

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

    <div class="highlight">
      <pre class="brush: xml;">
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
      </pre>
    </div>
  </div>
</div>

<div class="row">
  <div class="col-md-6">

    <h3>Hover Rows</h3>

    <table class="table table-hover">
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

    <div class="highlight">
      <pre class="brush: xml;">
        <table class="table table-hover">
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
      </pre>
    </div>

  </div>

  <div class="col-md-6">

    <h3>Bordered Table</h3>

    <table class="table table-bordered">
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

    <div class="highlight">
      <pre class="brush: xml;">
        <table class="table table-bordered">
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
      </pre>
    </div>

  </div>
</div>

<div class="row">

  <div class="col-md-6">

    <h3>Condensed Table</h3>

    <table class="table table-condensed">
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

    <div class="highlight">
      <pre class="brush: xml;">
        <table class="table table-condensed">
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
      </pre>
    </div>
  </div>

  <div class="col-md-6">

    <h3>Mix of all these classes</h3>

    <table class="table table-condensed table-bordered table-hover table-striped">
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

    <div class="highlight">
      <pre class="brush: xml;">
        <table class="table table-condensed table-bordered table-hover table-striped">
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
      </pre>
    </div>
  </div>

</div>

</body>
</html>
