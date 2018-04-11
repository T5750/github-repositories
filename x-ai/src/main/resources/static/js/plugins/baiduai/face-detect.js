duAI([20], {
        1061 : function(t, e, i) {
            t.exports = i(1062)
        },
        1062 : function(t, e, i) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            });
            var n = i(70),
                o = i.n(n),
                a = i(164),
                s = i.n(a),
                r = i(8),
                l = i.n(r),
                c = (i(230), i(83)),
                d = i.n(c),
                h = i(78),
                u = i.n(h),
                f = i(79),
                p = i.n(f),
                m = i(68),
                v = i(69),
                g = i(167),
                y = i(154),
                x = i(1063),
                b = function() {
                    function t(e) {
                        var i = this,
                            n = e.selector,
                            o = e.image,
                            a = e.type,
                            s = e.apiType,
                            r = e.toCheck,
                            c = void 0 === r || r,
                            d = e.scale,
                            h = void 0 === d ? 1 : d,
                            f = e.success,
                            p = e.fail;
                        if (u()(this, t), !l()(n).context) throw "DemoCanvas：未寻找到容器!";
                        if (this.container = l()(n), this.type = a, this.scale = h, this.apiType = s, this.image = new Image, this.success = f || l.a.noop, this.fail = p || l.a.noop, this.image.onerror = function() {
                                i.fail(),
                                    new v.a("图片加载失败，请重试")
                            },
                                c) { ({
                            url: this.checkByUrl,
                            stream: this.checkByStream
                        })[this.type](o, s).then(function(t) {
                                i.image.onload = function() {
                                    i.render(!0)
                                },
                                    i.image.src = t
                            },
                            function(t) {
                                i.image.onload = function() {
                                    i.render(!1)
                                },
                                    i.image.src = t
                            })
                        } else this.image.onload = function() {
                            i.render(!0)
                        },
                            this.image.src = o
                    }
                    return p()(t, [{
                        key: "checkByUrl",
                        value: function(t, e) {
                            var i = l.a.Deferred();
                            return Object(m.c)({
                                imageUrl: t,
                                type: e,
                                success: function(t) {
                                    var e = t.data["Content-Type"],
                                        n = t.data["Content-Length"];
                                    return ! e && !n || 0 !== t.errno ? void i.reject(g) : /image\/(png|bmp|jpg|jpeg)/.test(e) ? n > 2097152 ? void i.reject(x) : void i.resolve(t.data.image_data) : void i.reject(y)
                                },
                                fail: function() {
                                    i.reject(g)
                                }
                            }),
                                i.promise()
                        }
                    },
                        {
                            key: "checkByStream",
                            value: function(t) {
                                var e = l.a.Deferred(),
                                    i = new FileReader;
                                return t ? (i.readAsDataURL(t), i.onload = function(i) {
                                    return /image\/(png|bmp|jpeg)/.test(t.type) ? t.size > 2097152 ? (e.reject(x), !1) : void e.resolve(i.target.result) : (e.reject(y), !1)
                                },
                                    i.onerror = function() {
                                        e.reject(g)
                                    },
                                    e.promise()) : (e.reject(g), e.promise())
                            }
                        },
                        {
                            key: "render",
                            value: function(t) {
                                var e = this.container.width(),
                                    i = this.container.height(),
                                    n = this.image.width,
                                    o = this.image.height,
                                    a = l()("<canvas>您的浏览器暂时不支持canvas，请选用现代浏览器！</canvas>").attr("width", n).attr("height", o);
                                a[0].getContext("2d").drawImage(this.image, 0, 0);
                                var s = n / e,
                                    r = o / i,
                                    c = this.scale * (s > 1 || r > 1 ? 1 / (s >= r ? s: r) : 1);
                                a.css({
                                    position: "absolute",
                                    left: "50%",
                                    top: "50%",
                                    "-webkit-transform": "translate(-50%, -50%) scale(" + c + ")",
                                    "-moz-transform": "translate(-50%, -50%) scale(" + c + ")",
                                    "-o-transform": "translate(-50%, -50%) scale(" + c + ")",
                                    transform: "translate(-50%, -50%) scale(" + c + ")"
                                }),
                                    a.attr("data-scale", c),
                                    this.container.empty().append(a),
                                    t ? this.success(this.image.src) : this.fail()
                            }
                        }]),
                        t
                } (),
                C = b,
                w = (i(1064), i(1066), [i(393), i(394), i(395), i(396), i(397), i(398), i(399), i(400)]),
                E = l()("#demo-json").find("> p"),
                _ = l()("#result-gallery"),
                I = _.find("ul"),
                P = l()(".tech-intro-detail"),
                $ = l()("#demo-result"),
                N = $.find(".canvas-container"),
                D = l()("#demo-photo-upload").find("> input"),
                k = l()("#demo-photo-url"),
                S = l()("#face-details");
            l()(".case-indicator > li").click(function() {
                var t = this;
                l()(".case-indicator > li").each(function(e, i) {
                    l()(i).toggleClass("active", e === l()(t).index())
                }),
                    l()(".case-item").each(function(e, i) {
                        l()(i).toggleClass("active", e === l()(t).index())
                    })
            }),
                l()(window).scroll(d()(function() {
                        l()(document).scrollTop() >= 100 && l()(".tech-intro-detail").trigger("demo")
                    },
                    300)),
                P.one("demo",
                    function() {
                        P.addClass("scanned")
                    });
            var j = !1,
                M = function() {
                    j = !1,
                        E.empty(),
                        N.attr("class", "canvas-container"),
                        l()("#demo-photo-upload, #scan-photo").removeClass("disabled"),
//                        D.val(""),
                        _.hide(),
                        I.empty(),
                        S.hide().empty()
                },
                O = function(t) {
                    for (var e = $.find("canvas"), i = e.attr("data-scale"), n = e[0].getContext("2d"), o = 0, a = t.length; o < a; o++) {
                        var s = t[o],
                            r = s.location;
                        n.save(),
                            n.lineWidth = 4 / i,
                            n.strokeStyle = "rgba(0, 115, 235, 0.8)",
                            n.translate(r.left, r.top),
                            n.rotate(s.rotation_angle / 180 * Math.PI),
                            n.rect(0, 0, r.width, r.height),
                            n.stroke(),
                            n.restore()
                    }
                },
                T = function(t, e) {
                    for (var i = $.find("canvas"), n = i.attr("data-scale"), o = i[0].getContext("2d"), a = e ? {
                            x: t.location.left,
                            y: t.location.top
                        }: {
                            x: 0,
                            y: 0
                        },
                             s = e ? t.rotation_angle: 0, r = 0, l = t.landmark72.length; r < l; r++) {
                        var c = t.landmark72[r];
                        o.beginPath(),
                            o.lineWidth = 1,
                            o.fillStyle = "rgba(0, 115, 235, 0.8)",
                            o.strokeStyle = "transparent";
                        var d = (function(t, e) {
                                return 360 * Math.atan(e / t) / (2 * Math.PI)
                            } (c.x - a.x, c.y - a.y) - s) / 180 * Math.PI,
                            h = function(t, e) {
                                return Math.sqrt(Math.pow(t, 2) + Math.pow(e, 2))
                            } (c.x - a.x, c.y - a.y);
                        o.arc(h * Math.cos(d), h * Math.sin(d), 2 / n, 0, 2 * Math.PI),
                            o.fill(),
                            o.stroke(),
                            o.closePath()
                    }
                },
                R = function(t) {
                    _.toggle(t)
                },
                A = function(t) {
                    var e = new Image;
                    e.onload = function() {
                        for (var i = 0,
                                 n = t.result.length; i < n; i++) {
                            var o = t.result[i],
                                a = l()("<canvas>").attr("width", o.location.width).attr("height", o.location.height),
                                s = a[0].getContext("2d");
                            s.rotate( - o.rotation_angle * Math.PI / 180),
                                s.translate( - o.location.left, -o.location.top),
                                s.drawImage(e, 0, 0);
                            var r = l()('<li><img src="' + a[0].toDataURL() + '"></li>');
                            r.data("face", o).data("isAll", !1),
                                I.append(r)
                        }
                    },
                        e.src = _.find("img").eq(0).attr("src")
                },
                F = function(t, e) {
                    var i = l()('<li class="active"><img src="' + t + '"></li>');
                    i.data("face", e).data("isAll", !0),
                        I.empty().append(i)
                },
                U = {
                    age: {
                        name: "年龄",
                        transform: function(t) {
                            return Math.round(t)
                        }
                    },
                    race: {
                        name: "人种",
                        transform: function(t) {
                            return {
                                yellow: "黄种人",
                                white: "白种人",
                                black: "黑种人",
                                arabs: "阿拉伯人"
                            } [t]
                        }
                    },
                    gender: {
                        name: "性别",
                        transform: function(t) {
                            return {
                                male: "男性",
                                female: "女性"
                            } [t]
                        }
                    },
                    expression: {
                        name: "表情",
                        transform: function(t) {
                            return {
                                0 : "不笑",
                                1 : "微笑",
                                2 : "大笑"
                            } [t]
                        }
                    },
                    glasses: {
                        name: "眼镜",
                        transform: function(t) {
                            return {
                                0 : "无眼镜",
                                1 : "普通眼镜",
                                2 : "墨镜"
                            } [t]
                        }
                    }
                },
                V = function(t, e) {
                    if (S.empty(), e) return S.hide(),
                        !1;
                    S.show(),
                        s()(U).forEach(function(e) {
                            var i = U[e].name,
                                n = U[e].transform(t[e]);
                            S.append(l()("<li></li>").html(i + " : " + n))
                        })
                },
                z = function(t, e, i) {
                    E.empty(),
                        R(!1),
                        N.attr("class", "canvas-container loading"),
                        S.hide().empty();
                    var n = {
                        success: function(t) {
                            l()("#demo-photo-upload, #scan-photo").removeClass("disabled"),
                                E.html(o()(t, null, "\t")),
                                N.removeClass("loading");
                            var i = t.errno,
                                n = t.msg,
                                a = t.data;
                            if (0 !== i || !a.result_num) return N.toggleClass("error-upload-fail", 107 === i).toggleClass("error-timeout", 28 === i).toggleClass("error-image-format", 106 === i),
                                N.toggleClass("error-no-result", !a || !a.result_num),
                                N.empty(),
                                j = !1,
                            -1 === [106, 107, 28, 0].indexOf(i) && new v.a(n),
                                j;
                            N.toggleClass("has-result", a.result_num >= 1),
                                F(e, a.result),
                                1 === a.result_num ? (R(!1), T(a.result[0]), V(a.result[0], !1)) : (R(!0), A(a), O(a.result), V(null, !0)),
                                j = !1
                        },
                        fail: function(t) {
                            new v.a("接口发生错误：" + t.status + " - " + t.statusText),
                                M()
                        }
                    };
                    "url" === t ? n.imageUrl = i: "stream" === t && (n.image = e),
                        Object(m.e)(n)
                };
            D.change(function(t) {
                var e = l()(t.target);
//                if ("" === e.val()) return ! 1;
//                if (j) return void new v.a("操作正在进行中，请稍候再试！");
                j = !0,
                    l()("#demo-photo-upload, #scan-photo").addClass("disabled");
                var i = e[0].files[0];
                new C({
                    selector: "#demo-result .canvas-container",
                    image: i,
                    type: "stream",
                    success: function(t) {
//                        D.val(""),
                            z("stream", t)
                    },
                    fail: M
                })
            }),
                l()("#scan-photo").click(function(t) {
//                    var e = l()(t.target);
//                    if (j) return void new v.a("操作正在进行中，请稍候再试！");
                    if (!e.hasClass("disabled") && k.val()) {
                        j = !0,
                            l()("#demo-photo-upload, #scan-photo").addClass("disabled");
                        var i = k.val();
                        new C({
                            selector: "#demo-result .canvas-container",
                            image: i,
                            type: "url",
                            apiType: "face",
                            success: function(t) {
                                z("url", t, i)
                            },
                            fail: M
                        })
                    }
                }),
                l()("#demo-photo-upload").click(function() {
                    if (l()(this).hasClass("disabled")) return ! 1
                });
            var H = l()(".demo-card-list > li");
            H.each(function(t, e) {
                l()(e).find("img").attr("src", "" + w[t])
            }),
                H.click(function(t) {
//                    var e = l()(t.target);
//                    if (j) return void new v.a("操作正在进行中，请稍候再试！");
                    if (!e.hasClass("active")) {
                        j = !0,
                            e.addClass("active").siblings().removeClass("active");
                        var i = "" + window.location.protocol + l()(this).find("img").attr("src");
                        l()("#demo-photo-upload, #scan-photo").addClass("disabled"),
                            new C({
                                selector: "#demo-result .canvas-container",
                                image: i,
                                type: "url",
                                success: function(t) {
                                    z("url", t, i)
                                },
                                fail: M
                            })
                    }
                }),
                _.on("click", "li",
                    function(t) {
                        var e = l()(t.target);
                        if (!e.hasClass("active")) {
                            e.addClass("active").siblings().removeClass("active");
                            var i = e.data("face"),
                                n = e.data("isAll");
                            new C({
                                selector: "#demo-result .canvas-container",
                                image: l()(this).find("img").attr("src"),
                                toCheck: !1,
                                success: function() {
                                    n ? O(i) : T(i, !0),
                                        V(i, n)
                                },
                                fail: M
                            })
                        }
                    }),
                H.eq(0).click()
        },
        1063 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/error/too-large.png"
        },
        1064 : function(t, e) {},
        1066 : function(t, e, i) {
            t.exports = i.p + "temp/src/view/technology/face-detect.hbs"
        },
        150 : function(t, e, i) {
            var n = i(148);
            t.exports = (n.
                default || n).template({
                compiler: [7, ">= 4.0.0"],
                main: function(t, e, i, n, o) {
                    var a, s, r = null != e ? e: t.nullContext || {},
                        l = i.helperMissing,
                        c = t.escapeExpression;
                    return '<div class="ai-modal alert" id="' + c((s = null != (s = i.id || (null != e ? e.id: e)) ? s: l, "function" == typeof s ? s.call(r, {
                        name: "id",
                        hash: {},
                        data: o
                    }) : s)) + '">\n    <header class="modal-header">\n        <h3>' + c((s = null != (s = i.title || (null != e ? e.title: e)) ? s: l, "function" == typeof s ? s.call(r, {
                        name: "title",
                        hash: {},
                        data: o
                    }) : s)) + '</h3>\n        <a class="modal-x"></a>\n    </header>\n    <section class="modal-content">\n        <div style="text-align: center; color: #666;font-size: 14px;margin-bottom: 20px;">\n            ' + (null != (s = null != (s = i.content || (null != e ? e.content: e)) ? s: l, a = "function" == typeof s ? s.call(r, {
                        name: "content",
                        hash: {},
                        data: o
                    }) : s) ? a: "") + '\n        </div>\n        <div style="text-align: center;">\n            <button type="button" class="btn-normal cancel">确定</button>\n        </div>\n    </section>\n</div>\n'
                },
                useData: !0
            })
        },
        154 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/error/image-format.png"
        },
        164 : function(t, e, i) {
            t.exports = {
                default:
                    i(165),
                __esModule: !0
            }
        },
        165 : function(t, e, i) {
            i(166),
                t.exports = i(43).Object.keys
        },
        166 : function(t, e, i) {
            var n = i(152),
                o = i(149);
            i(199)("keys",
                function() {
                    return function(t) {
                        return o(n(t))
                    }
                })
        },
        167 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/error/not-found.png"
        },
        230 : function(t, e, i) {
            "use strict";
            function n() {
                var t = +I.filter(function(t, e) {
                    return a()(e).prop("checked")
                }).val();
                t === _.VERIFIED ? (w.show(), E.hide()) : t === _.UNVERIFIED && (w.hide(), E.show())
            }
            var o = i(8),
                a = i.n(o),
                s = i(231),
                r = (i.n(s), i(232)),
                l = (i.n(r), a()("#face-total-verified")),
                c = a()("#face-total-unverified"),
                d = a()("#face-cooperation-verified"),
                h = a()("#face-cooperation-unverified"),
                u = a()("#face-critical-verified"),
                f = a()("#face-critical-unverified"),
                p = a()("#current-value-verified"),
                m = a()("#current-value-unverified"),
                v = a()("#rangeslider-verified"),
                g = a()("#rangeslider-unverified"),
                y = a()("#output-month-verified"),
                x = a()("#output-month-unverified"),
                b = a()("#output-day-verified"),
                C = a()("#output-day-unverified"),
                w = a()("#company-verified-wrapper"),
                E = a()("#company-unverified-wrapper"),
                _ = {
                    VERIFIED: 0,
                    UNVERIFIED: 1
                },
                I = a()('input[name="verify-status"]');
            I.on("change", n),
                n(),
                v.rangeslider({
                    polyfill: !1,
                    onInit: function(t, e) {
                        y.text(0),
                            b.text(0)
                    },
                    onSlide: function(t, e) {
                        p.css({
                            left: t
                        }).text(e + "QPS");
                        var i = a()("#js-rangeslider-0"),
                            n = 500 * (e - 5),
                            o = 50 * (e - 5);
                        y.text(n),
                            b.text(o),
                            e > 25 ? (l.css("opacity", 0), d.css({
                                opacity: 1,
                                "z-index": "3"
                            }), u.css("color", "#3090ff"), p.text(">25QPS"), i.addClass("rangeslider-over")) : (l.css("opacity", 1), d.css({
                                opacity: 0,
                                "z-index": "2"
                            }), u.css("color", "#666"), i.removeClass("rangeslider-over"))
                    }
                }),
                g.rangeslider({
                    polyfill: !1,
                    onInit: function(t, e) {
                        x.text(0),
                            C.text(0)
                    },
                    onSlide: function(t, e) {
                        m.css({
                            left: t
                        }).text(e + "QPS");
                        var i = a()("#js-rangeslider-1"),
                            n = 500 * (e - 2),
                            o = 50 * (e - 2);
                        x.text(n),
                            C.text(o),
                            e > 22 ? (c.css("opacity", 0), h.css({
                                opacity: 1,
                                "z-index": "3"
                            }), f.css("color", "#3090ff"), m.text(">22QPS"), i.addClass("rangeslider-over")) : (c.css("opacity", 1), h.css({
                                opacity: 0,
                                "z-index": "2"
                            }), f.css("color", "#666"), i.removeClass("rangeslider-over"))
                    }
                })
        },
        231 : function(t, e, i) {
            var n, o, a; !
                function(s) {
                    "use strict";
                    o = [i(8)],
                        n = s,
                    void 0 !== (a = "function" == typeof n ? n.apply(e, o) : n) && (t.exports = a)
                } (function(t) {
                    "use strict";
                    function e(t, e) {
                        var i = Array.prototype.slice.call(arguments, 2);
                        return setTimeout(function() {
                                return t.apply(null, i)
                            },
                            e)
                    }
                    function i(t, e) {
                        return e = e || 100,
                            function() {
                                if (!t.debouncing) {
                                    var i = Array.prototype.slice.apply(arguments);
                                    t.lastReturnVal = t.apply(window, i),
                                        t.debouncing = !0
                                }
                                return clearTimeout(t.debounceTimeout),
                                    t.debounceTimeout = setTimeout(function() {
                                            t.debouncing = !1
                                        },
                                        e),
                                    t.lastReturnVal
                            }
                    }
                    function n(t) {
                        return t && (0 === t.offsetWidth || 0 === t.offsetHeight || !1 === t.open)
                    }
                    function o(t) {
                        for (var e = [], i = t.parentNode; n(i);) e.push(i),
                            i = i.parentNode;
                        return e
                    }
                    function a(t, e) {
                        function i(t) {
                            void 0 !== t.open && (t.open = !t.open)
                        }
                        var n = o(t),
                            a = n.length,
                            s = [],
                            r = t[e];
                        if (a) {
                            for (var l = 0; l < a; l++) s[l] = n[l].style.cssText,
                                n[l].style.setProperty ? n[l].style.setProperty("display", "block", "important") : n[l].style.cssText += ";display: block !important",
                                n[l].style.height = "0",
                                n[l].style.overflow = "hidden",
                                n[l].style.visibility = "hidden",
                                i(n[l]);
                            r = t[e];
                            for (var c = 0; c < a; c++) n[c].style.cssText = s[c],
                                i(n[c])
                        }
                        return r
                    }
                    function s(t, e) {
                        var i = parseFloat(t);
                        return Number.isNaN(i) ? e: i
                    }
                    function r(t) {
                        return t.charAt(0).toUpperCase() + t.substr(1)
                    }
                    function l(n, o) {
                        if (this.$window = t(window), this.$document = t(document), this.$element = t(n), this.options = t.extend({},
                                u, o), this.polyfill = this.options.polyfill, this.orientation = this.$element[0].getAttribute("data-orientation") || this.options.orientation, this.onInit = this.options.onInit, this.onSlide = this.options.onSlide, this.onSlideEnd = this.options.onSlideEnd, this.DIMENSION = f.orientation[this.orientation].dimension, this.DIRECTION = f.orientation[this.orientation].direction, this.DIRECTION_STYLE = f.orientation[this.orientation].directionStyle, this.COORDINATE = f.orientation[this.orientation].coordinate, this.polyfill && h) return ! 1;
                        this.identifier = "js-" + c + "-" + d++,
                            this.startEvent = this.options.startEvent.join("." + this.identifier + " ") + "." + this.identifier,
                            this.moveEvent = this.options.moveEvent.join("." + this.identifier + " ") + "." + this.identifier,
                            this.endEvent = this.options.endEvent.join("." + this.identifier + " ") + "." + this.identifier,
                            this.toFixed = (this.step + "").replace(".", "").length - 1,
                            this.$fill = t('<div class="' + this.options.fillClass + '" />'),
                            this.$handle = t('<div class="' + this.options.handleClass + '" />'),
                            this.$range = t('<div class="' + this.options.rangeClass + " " + this.options[this.orientation + "Class"] + '" id="' + this.identifier + '" />').insertAfter(this.$element).prepend(this.$fill, this.$handle),
                            this.$element.css({
                                position: "absolute",
                                width: "1px",
                                height: "1px",
                                overflow: "hidden",
                                opacity: "0"
                            }),
                            this.handleDown = t.proxy(this.handleDown, this),
                            this.handleMove = t.proxy(this.handleMove, this),
                            this.handleEnd = t.proxy(this.handleEnd, this),
                            this.init();
                        var a = this;
                        this.$window.on("resize." + this.identifier, i(function() {
                                e(function() {
                                        a.update(!1, !1)
                                    },
                                    300)
                            },
                            20)),
                            this.$document.on(this.startEvent, "#" + this.identifier + ":not(." + this.options.disabledClass + ")", this.handleDown),
                            this.$element.on("change." + this.identifier,
                                function(t, e) {
                                    if (!e || e.origin !== a.identifier) {
                                        var i = t.target.value,
                                            n = a.getPositionFromValue(i);
                                        a.setPosition(n)
                                    }
                                })
                    }
                    Number.isNaN = Number.isNaN ||
                        function(t) {
                            return "number" == typeof t && t !== t
                        };
                    var c = "rangeslider",
                        d = 0,
                        h = function() {
                            var t = document.createElement("input");
                            return t.setAttribute("type", "range"),
                            "text" !== t.type
                        } (),
                        u = {
                            polyfill: !0,
                            orientation: "horizontal",
                            rangeClass: "rangeslider",
                            disabledClass: "rangeslider--disabled",
                            activeClass: "rangeslider--active",
                            horizontalClass: "rangeslider--horizontal",
                            verticalClass: "rangeslider--vertical",
                            fillClass: "rangeslider__fill",
                            handleClass: "rangeslider__handle",
                            startEvent: ["mousedown", "touchstart", "pointerdown"],
                            moveEvent: ["mousemove", "touchmove", "pointermove"],
                            endEvent: ["mouseup", "touchend", "pointerup"]
                        },
                        f = {
                            orientation: {
                                horizontal: {
                                    dimension: "width",
                                    direction: "left",
                                    directionStyle: "left",
                                    coordinate: "x"
                                },
                                vertical: {
                                    dimension: "height",
                                    direction: "top",
                                    directionStyle: "bottom",
                                    coordinate: "y"
                                }
                            }
                        };
                    return l.prototype.init = function() {
                        this.update(!0, !1),
                        this.onInit && "function" == typeof this.onInit && this.onInit()
                    },
                        l.prototype.update = function(t, e) {
                            t = t || !1,
                            t && (this.min = s(this.$element[0].getAttribute("min"), 0), this.max = s(this.$element[0].getAttribute("max"), 100), this.value = s(this.$element[0].value, Math.round(this.min + (this.max - this.min) / 2)), this.step = s(this.$element[0].getAttribute("step"), 1)),
                                this.handleDimension = a(this.$handle[0], "offset" + r(this.DIMENSION)),
                                this.rangeDimension = a(this.$range[0], "offset" + r(this.DIMENSION)),
                                this.maxHandlePos = this.rangeDimension - this.handleDimension,
                                this.grabPos = this.handleDimension / 2,
                                this.position = this.getPositionFromValue(this.value),
                                this.$element[0].disabled ? this.$range.addClass(this.options.disabledClass) : this.$range.removeClass(this.options.disabledClass),
                                this.setPosition(this.position, e)
                        },
                        l.prototype.handleDown = function(t) {
                            if (t.preventDefault(), this.$document.on(this.moveEvent, this.handleMove), this.$document.on(this.endEvent, this.handleEnd), this.$range.addClass(this.options.activeClass), !((" " + t.target.className + " ").replace(/[\n\t]/g, " ").indexOf(this.options.handleClass) > -1)) {
                                var e = this.getRelativePosition(t),
                                    i = this.$range[0].getBoundingClientRect()[this.DIRECTION],
                                    n = this.getPositionFromNode(this.$handle[0]) - i,
                                    o = "vertical" === this.orientation ? this.maxHandlePos - (e - this.grabPos) : e - this.grabPos;
                                this.setPosition(o),
                                e >= n && e < n + this.handleDimension && (this.grabPos = e - n)
                            }
                        },
                        l.prototype.handleMove = function(t) {
                            t.preventDefault();
                            var e = this.getRelativePosition(t),
                                i = "vertical" === this.orientation ? this.maxHandlePos - (e - this.grabPos) : e - this.grabPos;
                            this.setPosition(i)
                        },
                        l.prototype.handleEnd = function(t) {
                            t.preventDefault(),
                                this.$document.off(this.moveEvent, this.handleMove),
                                this.$document.off(this.endEvent, this.handleEnd),
                                this.$range.removeClass(this.options.activeClass),
                                this.$element.trigger("change", {
                                    origin: this.identifier
                                }),
                            this.onSlideEnd && "function" == typeof this.onSlideEnd && this.onSlideEnd(this.position, this.value)
                        },
                        l.prototype.cap = function(t, e, i) {
                            return t < e ? e: t > i ? i: t
                        },
                        l.prototype.setPosition = function(t, e) {
                            var i, n;
                            void 0 === e && (e = !0),
                                i = this.getValueFromPosition(this.cap(t, 0, this.maxHandlePos)),
                                n = this.getPositionFromValue(i),
                                this.$fill[0].style[this.DIMENSION] = n + this.grabPos + "px",
                                this.$handle[0].style[this.DIRECTION_STYLE] = n + "px",
                                this.setValue(i),
                                this.position = n,
                                this.value = i,
                            e && this.onSlide && "function" == typeof this.onSlide && this.onSlide(n, i)
                        },
                        l.prototype.getPositionFromNode = function(t) {
                            for (var e = 0; null !== t;) e += t.offsetLeft,
                                t = t.offsetParent;
                            return e
                        },
                        l.prototype.getRelativePosition = function(t) {
                            var e = r(this.COORDINATE),
                                i = this.$range[0].getBoundingClientRect()[this.DIRECTION],
                                n = 0;
                            return void 0 !== t.originalEvent["client" + e] ? n = t.originalEvent["client" + e] : t.originalEvent.touches && t.originalEvent.touches[0] && void 0 !== t.originalEvent.touches[0]["client" + e] ? n = t.originalEvent.touches[0]["client" + e] : t.currentPoint && void 0 !== t.currentPoint[this.COORDINATE] && (n = t.currentPoint[this.COORDINATE]),
                            n - i
                        },
                        l.prototype.getPositionFromValue = function(t) {
                            var e;
                            return e = (t - this.min) / (this.max - this.min),
                                Number.isNaN(e) ? 0 : e * this.maxHandlePos
                        },
                        l.prototype.getValueFromPosition = function(t) {
                            var e, i;
                            return e = t / (this.maxHandlePos || 1),
                                i = this.step * Math.round(e * (this.max - this.min) / this.step) + this.min,
                                Number(i.toFixed(this.toFixed))
                        },
                        l.prototype.setValue = function(t) {
                            t === this.value && "" !== this.$element[0].value || this.$element.val(t).trigger("input", {
                                origin: this.identifier
                            })
                        },
                        l.prototype.destroy = function() {
                            this.$document.off("." + this.identifier),
                                this.$window.off("." + this.identifier),
                                this.$element.off("." + this.identifier).removeAttr("style").removeData("plugin_" + c),
                            this.$range && this.$range.length && this.$range[0].parentNode.removeChild(this.$range[0])
                        },
                        t.fn[c] = function(e) {
                            var i = Array.prototype.slice.call(arguments, 1);
                            return this.each(function() {
                                var n = t(this),
                                    o = n.data("plugin_" + c);
                                o || n.data("plugin_" + c, o = new l(this, e)),
                                "string" == typeof e && o[e].apply(o, i)
                            })
                        },
                        "rangeslider.js is available in jQuery context e.g $(selector).rangeslider(options);"
                })
        },
        232 : function(t, e) {},
        393 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-1.jpg"
        },
        394 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-2.jpg"
        },
        395 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-3.jpg"
        },
        396 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-4.jpg"
        },
        397 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-5.jpg"
        },
        398 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-6.jpg"
        },
        399 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-7.jpg"
        },
        400 : function(t, e, i) {
            t.exports = i.p + "1515141530954/ai_images/technology/face-detect/demo-card-8.jpg"
        },
        68 : function(t, e, i) {
            "use strict";
            function n(t) {
                var e = t.image,
                    i = void 0 === e ? null: e,
                    n = t.imageUrl,
                    o = void 0 === n ? null: n,
                    a = t.type,
                    s = void 0 === a ? "commontext": a,
                    r = t.success,
                    l = void 0 === r ? f.a.noop: r,
                    c = t.fail,
                    d = void 0 === c ? f.a.noop: c;
                f.a.post("/face", {
                    type: s,
                    image: i,
                    image_url: o
                }).success(l).fail(d)
            }
            function o(t) {
                var e = t.image,
                    i = void 0 === e ? null: e,
                    n = t.imageUrl,
                    o = void 0 === n ? null: n,
                    a = t.type,
                    s = void 0 === a ? "commontext": a,
                    r = t.success,
                    l = void 0 === r ? f.a.noop: r,
                    c = t.fail,
                    d = void 0 === c ? f.a.noop: c;
                f.a.post("/face", {
                    type: s,
                    image: i,
                    image_url: o
                }).success(l).fail(d)
            }
            function a(t) {
                var e = t.image,
                    i = void 0 === e ? null: e,
                    n = t.imageUrl,
                    o = void 0 === n ? null: n,
                    a = t.success,
                    s = void 0 === a ? f.a.noop: a,
                    r = t.fail,
                    l = void 0 === r ? f.a.noop: r;
                f.a.post("/face", {
                    type: s,
                    image: i,
                    image_url: o
                }).success(s).fail(l)
            }
            function s(t) {
                var e = t.image,
                    i = void 0 === e ? null: e,
                    n = t.imageUrl,
                    o = void 0 === n ? null: n,
                    a = t.success,
                    s = void 0 === a ? f.a.noop: a,
                    r = t.fail,
                    l = void 0 === r ? f.a.noop: r;
                f.a.post("/face", {
                    type: "pornography",
                    image: i,
                    image_url: o
                }).success(s).fail(l)
            }
            function r(t) {
                var e = t.image,
                    i = void 0 === e ? null: e,
                    n = t.imageUrl,
                    o = void 0 === n ? null: n,
                    a = t.success,
                    s = void 0 === a ? f.a.noop: a,
                    r = t.fail,
                    l = void 0 === r ? f.a.noop: r;
                f.a.post("/face", {
                    type: "terror",
                    image: i,
                    image_url: o
                }).success(s).fail(l)
            }
            function l(t) {
                var e = t.imageUrl,
                    i = void 0 === e ? null: e,
                    n = t.type,
                    o = t.success,
                    a = void 0 === o ? f.a.noop: o,
                    s = t.fail,
                    r = void 0 === s ? f.a.noop: s;
                f.a.post("/face", {
                    action: "getHeader",
                    type: n,
                    image_url: i
                }).success(a).fail(r)
            }
            function c(t) {
                var e = t.words,
                    i = void 0 === e ? null: e,
                    n = t.success,
                    o = void 0 === n ? f.a.noop: n,
                    a = t.fail,
                    s = void 0 === a ? f.a.noop: a;
                f.a.post("/face", {
                    type: "wakescore",
                    kw: i
                }).success(o).fail(s)
            }
            function d(t) {
                var e = t.words,
                    i = void 0 === e ? null: e,
                    n = t.success,
                    o = void 0 === n ? f.a.noop: n;
                window.open("/face?type=wakedownload&kw=" + i, "_blank"),
                    o()
            }
            function h(t) {
                var e = t.data,
                    i = void 0 === e ? {}: e,
                    n = t.success,
                    o = void 0 === n ? f.a.noop: n,
                    a = t.fail,
                    s = void 0 === a ? f.a.noop: a;
                f.a.post("/face", {
                    type: "tns",
                    spd: i.speed,
                    vol: i.vol,
                    per: i.person,
                    tex: i.text
                }).success(o).fail(s)
            }
            e.d = n,
                e.f = o,
                e.e = a,
                e.g = s,
                e.h = r,
                e.c = l,
                e.a = c,
                e.b = d,
                e.i = h;
            var u = i(8),
                f = i.n(u)
        },
        69 : function(t, e, i) {
            "use strict";
            var n = i(170),
                o = i.n(n),
                a = i(78),
                s = i.n(a),
                r = i(79),
                l = i.n(r),
                c = i(177),
                d = i.n(c),
                h = i(178),
                u = i.n(h),
                f = i(179),
                p = i.n(f),
                m = i(8),
                v = i.n(m),
                g = i(180),
                y = i(150),
                x = i.n(y),
                b = function(t) {
                    function e(t) {
                        s()(this, e);
                        var i = d()(this, (e.__proto__ || o()(e)).call(this));
                        return i.title = "提示",
                            i.content = t || "",
                            i.init(),
                            i
                    }
                    return p()(e, t),
                        l()(e, [{
                            key: "init",
                            value: function() {
                                var t = x()({
                                    id: this.id,
                                    title: this.title,
                                    content: this.content
                                });
                                v()(this.container).append(t),
                                    this.bindEvent(),
                                    u()(e.prototype.__proto__ || o()(e.prototype), "show", this).call(this)
                            }
                        },
                            {
                                key: "hide",
                                value: function() {
                                    this.getModal().hide().remove(),
                                        v()("body").removeClass("modal-show")
                                }
                            },
                            {
                                key: "bindEvent",
                                value: function() {
                                    var t = this;
                                    u()(e.prototype.__proto__ || o()(e.prototype), "bindEvent", this).call(this),
                                        this.getModal().on("click", "button.cancel",
                                            function(e) {
                                                e.preventDefault(),
                                                    t.hide()
                                            })
                                }
                            }]),
                        e
                } (g.a);
            e.a = b
        },
        70 : function(t, e, i) {
            t.exports = {
                default:
                    i(74),
                __esModule: !0
            }
        },
        74 : function(t, e, i) {
            var n = i(43),
                o = n.JSON || (n.JSON = {
                    stringify: JSON.stringify
                });
            t.exports = function(t) {
                return o.stringify.apply(o, arguments)
            }
        }
    },
    [1061]);