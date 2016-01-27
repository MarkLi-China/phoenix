(function(n) {
	function t(t) {
		this.opt = t, this.ele = n("#" + this.opt.id)
	}
	t.prototype = {
		cubeSize: {
			x: 0,
			y: 0
		},
		nowIndex: 0,
		canChose: !0,
		nextIndex: function() {
			var n = this.nowIndex + 1;
			return n >= this.opt.imgs.length && (n = 0), n
		},
		init: function() {
			this.preLoad(), this.fill(), this.resize();
			var t = this;
			n(window).resize(function() {
				t.resize()
			})
		},
		preLoad: function() {
			var t = this.opt.imgs;
			n.each(t, function(n, t) {
				var i = document.createElement("img");
				i.src = t
			})
		},
		begin: function() {
			var n = this;
			this.timer = setInterval(function() {
				var t = n.nowIndex + 1;
				t >= n.opt.imgs.length && (t = 0), n.choseImg(t)
			}, n.opt.interval)
		},
		stop: function() {
			clearInterval(this.timer)
		},
		fill: function() {
			for (var n = '<div class="slider-box">', t, i = 0; i < this.opt.x; i++) for (t = 0; t < this.opt.y; t++) n += '<div id="{0}" class="{1}"><div class="slider_inner_a"></div><div class="slider_inner_b"></div></div>'.format(this.opt.id + "_" + i + "_" + t, "slider_cube");
			this.opt.showBar && (n += '<div class="slider_bar">', n += '<span class="active"></span>', n += new Array(this.opt.imgs.length).join("<span></span>"), n += "</div>"), n += "</div>", this.ele.html(n), this.eleBox = this.ele.children(".slider-box"), this.bindEvents()
		},
		bindEvents: function() {
			var t = this;
			if (this.opt.showBar) this.eleBox.children(".slider_bar").on("click", "span", function() {
				var i = n(this);
				t.choseImg(i.index())
			});
			this.opt.urls.length <= 0 || (t.eleBox.find(".slider_cube").click(function() {
				window.location.href = t.opt.urls[t.nowIndex]
			}).css("cursor", "pointer"), t.eleBox.mouseenter(function() {
				t.stop()
			}).mouseleave(function() {
				t.begin()
			}))
		},
		resize: function() {
			var f, e, r, u, o, i, t;
			for (this.eleBox.height(this.eleBox.width() / this.opt.scale), f = this.eleBox.width(), e = this.eleBox.height(), this.opt.border ? (r = (f - this.opt.x + 1) / this.opt.x, u = (e - this.opt.y + 1) / this.opt.y) : (r = f / this.opt.x, u = e / this.opt.y), this.cubeSize = {
				x: r,
				y: u
			}, this.opt.x == 1 && (r = f), this.opt.y == 1 && (u = elehei), o = this.opt.border ? 1 : 0, i = 0; i < this.opt.x; i++) for (t = 0; t < this.opt.y; t++) n("#" + this.opt.id + "_" + i + "_" + t).css({
				left: i * r + i * o + "px",
				top: t * u + t * o + "px",
				width: r + "px",
				height: u + "px"
			}).find(".slider_inner_a,.slider_inner_b").css({
				"background-image": "url({0})".format(this.opt.imgs[this.nowIndex]),
				"background-position": "{0}px {1}px".format(-i * r, -t * u),
				"background-size": "{0}px {1}px".format(f, e)
			})
		},
		choseImg: function(n) {
			var t = this,
				i;
			n != t.nowIndex && t.canChose && (t.canChose = !1, setTimeout(function() {
				t.canChose = !0
			}, t.opt.delay + 200), this.nowIndex = n, i = this.opt.imgs[n], this.effectIndex = this.effectIndex || 0, this.effectIndex >= this.effects.length && (this.effectIndex = 0), this.effects[this.effectIndex](this, i), this.effectIndex++, t.opt.showBar && t.eleBox.children(".slider_bar").find("span").removeClass("active").eq(t.nowIndex).addClass("active"))
		},
		effects: [function(t, i) {
			var r = t.opt.delay / (t.opt.x * t.opt.y);
			t.effectTemplate(function(u, f, e, o) {
				u.css({
					top: -t.cubeSize.y + "px",
					"background-image": "url({0})".format(i)
				});
				var s = (e + t.opt.x * o) * r * 4 / 5;
				u.delay(s).animate({
					top: "0"
				}, {
					duration: r * 10,
					easing: "easeOutBounce"
				}), f.delay(s).animate({
					top: t.cubeSize.y + "px"
				}, {
					duration: r * 10,
					easing: "easeOutBounce"
				}).will(function() {
					n(this).css({
						top: "0",
						"background-image": "url({0})".format(i)
					})
				})
			})
		}, function(t, i) {
			var r = t.opt.delay / t.opt.x;
			t.effectTemplate(function(u, f, e) {
				u.css({
					left: -t.cubeSize.x + "px",
					"background-image": "url({0})".format(i)
				});
				var s = e * r;
				u.delay(s).animate({
					left: "0"
				}, {
					duration: r * 2
				}), f.delay(s).animate({
					left: t.cubeSize.x + "px"
				}, {
					duration: r * 2
				}).will(function() {
					n(this).css({
						left: "0",
						"background-image": "url({0})".format(i)
					})
				})
			})
		}, function(t, i) {
			var r = t.opt.delay / (t.opt.x * t.opt.y);
			t.effectTemplate(function(u, f, e, o) {
				u.css({
					opacity: "0",
					"background-image": "url({0})".format(i)
				});
				var s = (e + t.opt.x * o) * r;
				u.delay(s).animate({
					opacity: "1"
				}, {
					duration: r * 4
				}), f.delay(s).animate({
					opacity: "0"
				}, {
					duration: r * 4
				}).will(function() {
					n(this).css({
						opacity: "1",
						"background-image": "url({0})".format(i)
					})
				})
			})
		}, function(t, i) {
			for (var u = [0], f = t.opt.delay / (t.opt.x * t.opt.y), r = 1, e = t.opt.x * t.opt.y; r < e; r++) u[r] = u[r - 1] + f;
			u.sort(function() {
				return Math.random() > .5 ? 1 : -1
			}), t.effectTemplate(function(r, e, o, s) {
				var a = Math.floor(Math.random() * 4),
					c = ["top", "left", "top", "left"][a],
					v = [-t.cubeSize.y, t.cubeSize.x, t.cubeSize.y, -t.cubeSize.x][a],
					h = {},
					l;
				h[c] = v + "px", h["background-image"] = "url({0})".format(i), r.css(h), l = u[o + s * t.opt.x], h = {}, h[c] = "0", r.delay(l * 3 / 4).animate(h, f * 4), h = {}, h[c] = -v + "px", e.delay(l * 3 / 4).animate(h, f * 4).will(function() {
					h = {}, h[c] = "0", h["background-image"] = "url({0})".format(i), n(this).css(h)
				})
			})
		}],
		effectTemplate: function(t) {
			for (var i, r = 0; r < this.opt.y; r++) for (i = 0; i < this.opt.x; i++) {
				var u = n("#{0}_{1}_{2}".format(this.opt.id, i, r)),
					e = u.children(".slider_inner_a"),
					f = u.children(".slider_inner_b");
				t(e, f, i, r)
			}
		}
	}, n.fn.slider = function(i) {
		var u = {
			id: this.id,
			imgs: [],
			urls: [],
			x: 2,
			y: 2,
			scale: 4 / 3,
			delay: 800,
			interval: 5e3,
			border: !1
		},
			f = n.extend({}, u, i),
			r = new t(f);
		return r.init(), n(this).data("slider", r), r.begin(), r
	}
})(jQuery)
