/**
 * 加载等待
 * 创建人：马群晶
 * 创建时间：20140723
 * 修改时间：20140725
 */
(function () {
  var doc = document, head = doc.head, body = doc.body;
  addStyle('@-webkit-keyframes mask-layer-box-circle-key{from { -webkit-transform:rotate(0) }to{ -webkit-transform:rotate(360deg)}}' +
  '.mask-layer{display:none;position: fixed;width:100%;height:100%;top:0;left:0;background:#000;z-index:1000;opacity:0.5}' +
  '.mask-layer-box{font-size:14px;font-family:"Microsoft YaHei";width:150px;height:100px;position:absolute;border-radius:8px;color:#fff;text-align:center;top:50%;left:50%;margin-top:-50px;margin-left:-75px;}' +
  '.mask-layer-box-circle{width:30px;height:30px;border-radius:30px;margin:10px auto 20px;border:3px solid #ddd;border-top-color:transparent;border-right-color:transparent;box-shadow:0 0 6px #333;-webkit-animation:mask-layer-box-circle-key 1s linear 0 infinite;}');

  var maskLayer = doc.createElement('div');
  maskLayer.id = 'maskLayer';
  maskLayer.className = 'mask-layer';
  body.appendChild(maskLayer);
  maskLayer.innerHTML = '<div class="mask-layer-box"><div class="mask-layer-box-circle"></div>正在加载...</div>';

  /**
   * 添加样式到内嵌样式
   * @param css
   */
  function addStyle(css) {
    var styles = head.getElementsByTagName("style"), style;
    if (styles.length == 0) {//如果不存在style元素则创建
      style = doc.createElement('style');
      head.insertBefore(style, null);
    }
    style = styles[0];
    style.appendChild(doc.createTextNode(css))
  }
})();