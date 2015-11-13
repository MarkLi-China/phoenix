/**
 *
 * @param uri
 * @param from
 * @param callback,  {error, start, complete, success}
 * @param dataType: json, html, xml, default json
 * @returns
 */
var AjaxUpload = function (uri, form, callback, dataType) {

  this.method = "POST";
  this.uri = uri;
  this.callback = callback;

  this.isUpload = true;
  //the form can be form id or form object
  this.form = form;

  this.dataType = dataType || "json";

}

AjaxUpload.prototype = {

  ERROR_CODE: {
    ABORT    : -1,
    EXCEPTION: -2,
    CONNECT  : -3
  },

  handleUploadData: function (responseObject) {
    var responseText = responseObject.responseText;
    var data = responseText, type;
    if (typeof data === "string") {
      if (data.toLowerCase().substring(0, 5) === "<pre>") {
        data = data.substring(5, data.length - 6);
      }
      if (this.dataType === 'json') {
        data = $.parseJSON(data);
        type = "json";
      } else if (this.dataType === 'html') {
        type = "html";
      } else {
        type = this.dataType;
      }
    } else {
      data = responseText;
      type = "xml";
    }
    responseObject.type = type;
    responseObject.data = data;
  },
  /**
   * status: exception obj or response error obj
   */
  handleError     : function (errorObj) {
    // If a local callback was specified, fire it
    if (this.callback && this.callback.error) {
      this.callback.error.call(this.callback.scope || this, errorObj);
    }
  },

  handleComplete: function (responseObject) {

    if (this.callback && this.callback.complete) {
      this.callback.complete.call(this.callback.scope || this, responseObject);
    }
  },

  handleSuccess: function (responseObject) {

    if (this.callback && this.callback.success) {
      this.callback.success.call(this.callback.scope || this, responseObject);

    }

  },

  handleStart: function () {

    if (this.callback && this.callback.start) {
      this.callback.start.call(this.callback.scope || this);
    }
  },

  createExceptionObject: function (status, errorMsg) {
    var obj = {};
    obj.status = status;
    obj.statusText = errorMsg;
    return obj;
  },

  abort: function (isTimeout) {
    var abortStatus = false;

    if (this.isUpload) {
      var frameId = this.getFrameId();
      var io = document.getElementById(frameId);

      if (io) {
        // Remove all listeners on the iframe prior to
        // its destruction.
        // Destroy the iframe facilitating the transaction.
        document.body.removeChild(io);
        if (isTimeout) {
          window.clearTimeout(this.timeoutId);
          delete this.timeoutId;
        }

        abortStatus = true;
      }
    }
    if (abortStatus === true) {
      responseObject = this.createExceptionObject(this.ERROR_CODE.ABORT, "aborted");
    }

    return abortStatus;
  },

  /**
   * @description Determines if the transaction is still being processed.
   * @method isCallInProgress
   * @public
   * @static
   * @param {object} o The connection object returned by asyncRequest
   * @return {boolean}
   */
  isCallInProgress: function () {
    if (this.isUpload === true) {
      return document.getElementById(this.getFrameId()) ? true : false;
    }
    else {
      return false;
    }
  },

  getFrameId: function () {
    return '__uploadIO' + this.id;
  },

  _createFrame: function (secureUri) {

    // IE does not allow the setting of id and name attributes as object
    // properties via createElement().  A different iframe creation
    // pattern is required for IE.
    var frameId = this.getFrameId(),
      io;
    if (getOs() == "MSIE") {
      io = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');

      // IE will throw a security exception in an SSL environment if the
      // iframe source is undefined.
      if (typeof secureUri == 'boolean') {
        io.src = 'javascript:false';
      }
    }
    else {
      io = document.createElement('iframe');
      io.id = frameId;
      io.name = frameId;
    }

    io.style.position = 'absolute';
    io.style.top = '-1000px';
    io.style.left = '-1000px';

    document.body.appendChild(io);
  },
  start       : function () {
    this.handleStart();
    this.uploadFile(this.form, this.uri);
  },

  uploadFile    : function (form, uri, postData) {

    this._createFrame(this.secureUri ? this.secureUri : null);

    // Each iframe has an id prefix of "yuiIO" followed
    // by the unique transaction id.
    var frameId = this.getFrameId(),
      uploadEncoding = 'multipart/form-data',
      io = document.getElementById(frameId),
      ie8 = (document.documentMode && document.documentMode === 8) ? true : false,
      oElements, i, prop, obj, rawFormAttributes, uploadCallback;


    // Initialize the HTML form properties in case they are
    // not defined in the HTML form.
    form.setAttribute('action', uri);
    form.setAttribute('method', 'POST');
    form.setAttribute('target', frameId);

    if (postData) {
      oElements = this.appendPostData(form, postData);
    }
    // Start file upload.
    form.submit();

    // Start polling if a callback is present and the timeout
    // property has been defined.
    if (this.timeout) {
      this.timeoutId = window.setTimeout(function () {
        this.abort(true);
      }, this.timeout);
    }

    // Remove HTML elements created by appendPostData
    if (oElements && oElements.length > 0) {
      for (i = 0; i < oElements.length; i++) {
        form.removeChild(oElements[i]);
      }
    }


    // Create the upload callback handler that fires when the iframe
    // receives the load event.  Subsequently, the event handler is detached
    // and the iframe removed from the document.
    uploadCallback = function () {
      if (this.timeout) {
        window.clearTimeout(this.timeoutId);
        delete this.timeoutId;
      }

      obj = {};

      try {
        // responseText and responseXML will be populated with the same data from the iframe.
        // Since the HTTP headers cannot be read from the iframe
        if (io.contentWindow.document.XMLDocument) {
          obj.responseText = io.contentWindow.document.XMLDocument;
        } else if (io.contentWindow.document.body && io.contentWindow.document.body.innerText) {
          obj.responseText = io.contentWindow.document.body.innerText;
        } else {
          obj.responseText = io.contentWindow.document.documentElement.textContent;
        }

      }
      catch (e) {
      }
      this.handleUploadData(obj);
      this.handleSuccess(obj);
      this.handleComplete(obj);

      $(io).off();
      setTimeout(
        function () {
          document.body.removeChild(io);
        }, 100);
    };

    // Bind the onload handler to the iframe to detect the file upload response.
    $(io).on("load", $.proxy(uploadCallback, this));
  },
  appendPostData: function (from, postData) {
    var formElements = [],
      postMessage = postData.split('&'),
      i, delimitPos;

    for (i = 0; i < postMessage.length; i++) {
      delimitPos = postMessage[i].indexOf('=');
      if (delimitPos != -1) {
        formElements[i] = document.createElement('input');
        formElements[i].type = 'hidden';
        formElements[i].name = decodeURIComponent(postMessage[i].substring(0, delimitPos));
        formElements[i].value = decodeURIComponent(postMessage[i].substring(delimitPos + 1));
        from.appendChild(formElements[i]);
      }
    }

    return formElements;
  }

};