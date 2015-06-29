;
(function ($, window, document) {
    angular.module('BaseModule', [])
        .constant('globalConfig', {
        })
        .factory('httpService', httpService)
        .factory('util', util);

    httpService.$inject = ['$q'];
    function util() {
        return {
            getUrlParam: function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) {
                    return unescape(r[2]);
                }
                return null;
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