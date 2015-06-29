;
(function ($, window, document) {
    angular.module('BaseModule', [])
        .constant('globalConfig', {
        })
        .factory('httpService', httpService)
        .factory('util', util);
    httpService.$inject = ['$q'];
    $(function () {
        if ($.datepicker) {
            var options = $.extend(
                {},
                $.datepicker.regional['zh'],
                { dateFormat: 'yy-mm-dd'}
            );
            $.datepicker.setDefaults(options);
        }
    });
    function util() {
        return {
            log: function (info) {
                if (console && console.log) {
                    console.log(info);
                }
            },
            getUrlParam: function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) {
                    return unescape(r[2]);
                }
                return null;
            },
            autoAddress: function (domId, area, cb) {
                var that = this;
                if (!BMap) {
                    that.log('Please import baidu map js file firstly');
                }
                var ac = new BMap.Autocomplete({
                        "input": domId
                    }
                );
                var result = {};
                if (!area) {
                    area = '广州';
                }
                ac.setLocation(area);
                ac.addEventListener('onconfirm', function (e) {
                    var val = e.item.value;
                    var addr = val.province + val.city + val.district + val.street + val.business;
                    setPlace(addr);
                });

                function setPlace(addr) {
                    new BMap.Geocoder().getPoint(addr, function (pos) {
                        if (pos) {
                            result.address = addr;
                            result.latitude = pos['lat'];
                            result.longitude = pos['lng'];
                        }
                        cb(result);
                        that.log('You are entering address:' + JSON.stringify(result));
                    }, area);
                }
            }
        }
    }

    function httpService($q) {
        $.ajaxSetup({
            headers: {"X-Request-Type": 'ajax'}
        });
        return {
            callAPI: callAPI
        };
        function callAPI(url, opts) {
            var deferred = $q.defer();
            url = contextPath + url;
            var options = {
                type: "POST",
                url: url,
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data.code === 'timeout') {
                        alert('请先登录');
                        window.location.href = contextPath + '/admin/user/logindex.do';
                        deferred.reject('timeout');
                    } else {
                        deferred.resolve(data);
                    }
                },
                error: function () {
                    deferred.reject('unknown error');
                }
            };
            $.extend(options, opts);
            $.ajax(options);
            return deferred.promise;
        }
    }
})(jQuery, window, document);