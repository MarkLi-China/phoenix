function showLoading() {

  $("#maskLayer").show();
}
function hideLoading() {

  $("#maskLayer").hide();
}

function getOs() {

  var OsObject = "";
  if (navigator.userAgent.indexOf("MSIE") > 0) {
    return "MSIE";
  }
  if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {
    return "Firefox";
  }
  if (isSafari = navigator.userAgent.indexOf("Safari") > 0) {
    return "Safari";
  }
  if (isCamino = navigator.userAgent.indexOf("Camino") > 0) {
    return "Camino";
  }
  if (isMozilla = navigator.userAgent.indexOf("Gecko/") > 0) {
    return "Gecko";
  }
}

/**
 * 封装jquery ajax方法，主要控制显示载入图标
 * @param url
 * @param params 请求参数，支付两种格式：json格式和url参数格式a=2&b=1
 * @param callback 请求后回调函数
 * @param dataType 返回数据类型，支持json, html, xml
 * @param notShowLoading true不显示载入图标，false显示载入图标，默认显示
 * @param requestType
 */
function ajaxRequest(url, params, callback, dataType, notShowLoading, requestType) {

  requestType = requestType || "post";
  $.ajax({
    type      : requestType,
    url       : url,
    data      : params,
    dataType  : dataType,
    beforeSend: function (XMLHttpRequest) {
      if (!notShowLoading)
        showLoading();
    },
    success   : function (data, textStatus) {
      try {
        if (!notShowLoading)
          hideLoading();

        callback.call(this, data, textStatus);

      } catch (e) {
        if (!notShowLoading)
          hideLoading();
        throw e;
      }
    },

    complete: function (XMLHttpRequest, textStatus) {
      if (!notShowLoading)
        hideLoading();
    },
    error   : function () {
      if (!notShowLoading)
        hideLoading();
      //请求出错处理
    }
  });
}

/**
 * 返回html, 封装jquery ajax方法，主要控制显示载入图标
 * @param url
 * @param params 请求参数，支付两种格式：json格式和url参数格式a=2&b=1
 * @param callback 请求后回调函数
 * @param notShowLoading true不显示载入图标，false显示载入图标，默认显示
 */
function ajaxHtml(url, params, callback, notShowLoading) {

  ajaxRequest(url, params, callback, "html", notShowLoading, "post");
}

/**
 * 返回json格式， 封装jquery ajax方法，主要控制显示载入图标
 * @param url
 * @param params 请求参数，支付两种格式：json格式和url参数格式a=2&b=1
 * @param callback 请求后回调函数
 * @param notShowLoading true不显示载入图标，false显示载入图标，默认显示
 */
function ajaxPost(url, params, callback, notShowLoading) {

  ajaxRequest(url, params, callback, "json", notShowLoading);
}


/**
 * 有错误显示错误信息和载入图标
 * @param url
 * @param params 请求参数，支付两种格式：json格式和url参数格式a=2&b=1
 * @param callback 请求后回调函数
 * @param notShowLoading true不显示载入图标，false显示载入图标，默认显示
 */
function ajaxPostParams(url, params, callback, notShowLoading) {

  ajaxPost(url, params, function (retData) {
    if (retData.ret.code == 0) {
      callback.call(this, retData);
    } else if (retData.ret.code == 996) {
      var errMsg = retData.ret.msg;
      for (var i = 0; i < retData.fieldErrors.length; i++) {
        var fieldError = retData.fieldErrors[i];
        errMsg += "\r\n" + fieldError.name + ": " + fieldError.msg;
      }
      alert(errMsg);
    } else {
      alert(retData.ret.msg);
    }
  }, notShowLoading);
}

/**
 * 提交表单请求调用，并支持显示错误信息在每个表单项上
 * @param url
 * @param form 表单对象或表单Id
 * @param callback 请求后回调函数
 * @param notShowLoading true不显示载入图标，false显示载入图标，默认显示
 */
function ajaxPostForm(url, form, callback, notShowLoading) {

  if (typeof form === 'string') {
    if (form.indexOf("#") != -1) {
      form = $(form);
    } else {
      form = $("#" + form);
    }
  }
  //判断是否为文件上传表单
  var enctype = $(form).attr("enctype");
  if (enctype && enctype == 'multipart/form-data') {
    form = $(form).get(0);
    ajaxPostFileForm(url, form, callback, notShowLoading);
    return;
  }

  clearFieldError();
  var params = $(form).serialize();
  ajaxPost(url, params, function (retData) {
    if (retData.ret.code == 0) {
      callback.call(this, retData);
    } else if (retData.ret.code == 996) {
      alert(retData.ret.msg);
      for (var i = 0; i < retData.fieldErrors.length; i++) {
        var fieldError = retData.fieldErrors[i];
        tagFieldError(fieldError.name, fieldError.msg);
      }
    } else {
      alert(retData.ret.msg);
    }
  }, notShowLoading);
}

function ajaxPostFileForm(url, form, callback, notShowLoading) {

  clearFieldError();
  var ajaxUpload = new AjaxUpload(url, form,
    {
      start   : function () {
        if (!notShowLoading)
          showLoading();
      },
      success : function (data) {
        try {
          if (!notShowLoading)
            hideLoading();

          var retData = data.data;
          if (retData.ret.code == 0) {
            callback.call(this, retData);
          } else if (retData.ret.code == 996) {
            alert(retData.ret.msg);
            for (var i = 0; i < retData.fieldErrors.length; i++) {
              var fieldError = retData.fieldErrors[i];
              tagFieldError(fieldError.name, fieldError.msg);
            }
          } else {
            alert(retData.ret.msg);
          }
        } catch (e) {
          if (!notShowLoading)
            hideLoading();
          throw e;
        }
      },
      complete: function (retData) {
        if (!notShowLoading)
          hideLoading();
      },
      error   : function () {
        if (!notShowLoading)
          hideLoading();
        //请求出错处理
      }
    }, "json");

  ajaxUpload.start();
}


function tagFieldError(id, msg) {

  var formControl = $("[name=" + id + "]");
  var parent = formControl;
  var hasFind = false;
  var count = 0;
  while (count < 10) {
    count++;
    var t_parent = parent.parent(".form-group");
    if (t_parent.size() >= 1) {
      parent = t_parent;
      hasFind = true;
      break;
    } else {
      parent = parent.parent();
    }
  }
  if (hasFind) {
    var p = parent;
    var spanErr = p.find(".form-field-error");
    if (spanErr.size() == 0) {
      p.append("<span class=\"col-sm-2 form-field-error\">" + msg + "</span>");
      p.addClass("has-error");
    } else {
      spanErr.append("; " + msg);
    }

  } else {
    var p = formControl.parent();
    var spanErr = p.find(".form-field-error");
    if (spanErr.size() == 0) {
      p.append("<span class=\"form-field-error\">" + msg + "</span>");
      p.addClass("has-error");
    } else {
      spanErr.append("; " + msg);
    }
  }
}

function clearFieldError() {

  $(".form-group").removeClass("has-error");
  $(".form-field-error").remove();
}

/**
 * 返回到上一页
 * @param url url存在，则返回到url，如果url不存在，则退加到浏览器上一页
 */
function backPage(url) {

  if (url) {
    location.href = url;
  } else {
    history.back();
  }
  return false;
}

function returnPage(url) {

  return backPage(url);
}

function onLink(url) {

  location.replace(url);
}

/**
 *
 * @param id
 * @param title dialog title
 * @param html dialog body html
 * @param okCallback
 * @param closeCallback
 * @param isNotShow default: show dialog, true not show
 * @param options {closeTitle, okTitle}, default: 关闭，确定
 * @param completeCallback 弹出层加载完成后回调函数 add by xiongfang
 */
function bossDialog(id, title, html, okCallback, closeCallback, isNotShow, options, completeCallback) {

  options = options || {};
  var modalBtnClose = options.closeTitle || "关闭"
    , modalBtnOk = options.okTitle || "确定"
    , modalClickNotHide = options.modalClickNotHide
    , modalWidth = options.width;

  var dialogJ = $("#modal_" + id);
  if (dialogJ.size() == 0) {
    var dialogHtml = $("#modalHtml").text();
    dialogHtml = dialogHtml.replace(/\{modalId\}/g, id);
    dialogHtml = dialogHtml.replace(/\{modalBtnClose\}/g, modalBtnClose);
    dialogHtml = dialogHtml.replace(/\{modalBtnOk\}/g, modalBtnOk);
    $(document.body).append(dialogHtml);
    dialogJ = $("#modal_" + id);
    if (modalWidth) {
      dialogJ.addClass("custom-width");
      $("#modal_" + id + " .modal-dialog").css({width: modalWidth});
    }
  }

  if (options.okTitle == 'hidden') {
    $("#modal_btn_updateDialog_ok").hide();
  }

  if (closeCallback) {
    $("#modal_btn_" + id + "_close").unbind("click");
    $("#modal_btn_" + id + "_close").click(function () {
      closeCallback.call(this);
      dialogJ.modal("hide");
    });
  }
  if (okCallback) {
    $("#modal_btn_" + id + "_ok").unbind("click");
    $("#modal_btn_" + id + "_ok").click(function () {
      var flag = okCallback.call(this);
      if (flag !== 'undefinded' && flag === false) {
        return;
      }
      if (!modalClickNotHide) {
        dialogJ.modal("hide");
      }
    });
  }

  var dialogTitleJ = $("#modal_" + id + " .modal-title");
  dialogTitleJ.text(title);

  if (!isNotShow) {
    dialogJ.modal('show');
    if (html) {
      bossDialogBody(id, html);
    }
  }

  if (completeCallback && typeof completeCallback === 'function') {
    completeCallback.call(this);
  }

  return dialogJ;
}

function bossDialogBody(id, html) {

  var dialogBodyJ = $("#modal_" + id + " .modal-body");
  dialogBodyJ.html(html);
  var dialogJ = $("#modal_" + id);
  dialogJ.modal('show');
}

function bossDialogRemoveBody(id) {

  var dialogBodyJ = $("#modal_" + id + " .modal-body");
  dialogBodyJ.find("*").remove();
}

/**
 * 图片上传预览js
 */
function setImagePreview(obj, apstr) {

  var docObj = obj;
  var imgObjPreview = document.getElementById("preview_" + apstr);
  if (docObj.files && docObj.files[0]) {
    //火狐下，直接设img属性
    imgObjPreview.style.display = 'block';
    imgObjPreview.style.width = '300px';
    imgObjPreview.style.height = '120px';
    //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
    imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
  } else {
    //IE下，使用滤镜
    docObj.select();
    var imgSrc = document.selection.createRange().text;
    var localImagId = document.getElementById("localImag");
    //必须设置初始大小
    localImagId.style.width = "300px";
    localImagId.style.height = "120px";
    //图片异常的捕捉，防止用户修改后缀来伪造图片
    try {
      localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
      localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
    } catch (e) {
      alert("您上传的图片格式不正确，请重新选择!");
      return false;
    }
    imgObjPreview.style.display = 'none';
    document.selection.empty();
  }
  return true;
}

/**
 * 禁用表单
 * @author xiongfang
 */
function disableForm(divId) {

  var $div = $('#' + divId);
  if ($div.size() <= 0) return;
  // disable form elements
  var $form = $("#" + divId).find("form");
  $("input", $form).attr("disabled", true);
  $("select", $form).attr("disabled", true);
  $("textarea", $form).attr("disabled", true);
  $("button", $form).attr("disabled", true);

  var left = $div.position().left;
  var top = $div.position().top;
  var width = $div.width();
  var height = $div.height();
  var $overrideLayer = $('<div>').addClass('o-' + divId).addClass('override').css({
    'position'        : 'absolute',
    'z-index'         : '9999',
    'width'           : width,
    'height'          : height,
    'left'            : left,
    'top'             : top,
    'opacity'         : 0.1,
    'background-color': '#000'
  });
  $div.append($overrideLayer);
  $('a[href="#' + divId + '"]').click(function () {
    var visible = $('#' + divId).siblings(':visible');
    if (visible.size() <= 0) return;
    $('.o-' + divId).hide().css({
      'position': 'absolute',
      width     : $('#' + divId).siblings(':visible').width(),
      height    : $('#' + divId).siblings(':visible').height(),
      left      : $('#' + divId).siblings(':visible').position().left,
      top       : $('#' + divId).siblings(':visible').position().top
    });
    setTimeout(function () {
      $('.o-' + divId).fadeIn().css({height: $('#' + divId).height()});
    }, 500);
  });
}

/**
 * input框只能输入数字
 * @param obj
 */
function checkNum(obj) {

  var val = $(obj).val();
  if (isNaN(val)) {
    alert("非法字符！");
    $(obj).val("");
  }
}

/**
 * textarea输入框剩余输入文字数量
 * @param obj
 * @param maxLength
 */
function inputCountDown(obj, maxLength) {

  maxLength = maxLength || 250;
  var length = $(obj).val().length;

  if (length > maxLength) {
    var text = $(obj).val().substr(0, maxLength);
    $(obj).val(text);
  } else {
    var rest = maxLength - length;
    $("#textarea-count-down").text(rest);
  }
}