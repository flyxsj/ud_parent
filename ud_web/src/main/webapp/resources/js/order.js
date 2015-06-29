;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('OrderModule', ['BaseModule'])
            .controller('AddOrderCtrl', AddOrderCtrl)
            .controller('ListOrderCtrl', ListOrderCtrl)
            .controller('CommentOrderCtrl', CommentOrderCtrl)
            .factory('orderService', orderService);
        orderService.$inject = ['httpService'];
        ListOrderCtrl.$inject = ['orderService'];
        AddOrderCtrl.$inject = ['$scope', 'orderService'];
        CommentOrderCtrl.$inject = ['orderService', 'util'];
        angular.bootstrap(document, ['OrderModule']);
    });
    function ListOrderCtrl(orderService) {
        var that = this;
        that.accept = accept;
        that.showStart = showStart;
        that.start = start;
        that.accept = accept;
        that.end = end;
        var area = $('#optArea');

        function showStart(orderNumber) {
            area.show();
            area.find('input[name="orderNumber"]').val(orderNumber);
        }

        function start() {
            var orderNumber = area.find('input[name="orderNumber"]').val();
            var serviceCode = area.find('input[name="serviceCode"]').val();
            orderService.start(orderNumber, serviceCode).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.reload();
                    } else if (data.code == 'invalid_service_code') {
                        alert('预约码无效');
                    }
                }
            );
        }

        function accept(orderNumber) {
            orderService.accept(orderNumber).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.reload();
                    }
                }
            );
        }

        function end(orderNumber) {
            orderService.end(orderNumber).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.reload();
                    }
                }
            );
        }
    }

    function AddOrderCtrl($scope, orderService) {
        var that = this;
        that.entity = {
            studentId: '1',
            driverId: '',
            type: 'drive',
            course: 'exercise2',
            day: ''
        };
        that.address = {
            info: '',
            longitude: '',
            latitude: ''
        };
        that.startTimeRange = [];
        that.endTimeRange = [];
        $scope.startTime = '9:00';
        $scope.endTime = '';
        that.driverList = [];
        that.searching = false;
        that.create = create;
        that.search = search;
        that.reserve = reserve;
        activate();

        function activate() {
            $('#txtDay').datepicker();
            for (var i = 0; i < 24; i++) {
                var item = {
                    'value': i + ':00',
                    'label': i + ':00'
                };
                that.startTimeRange.push(item);
                that.endTimeRange.push(item);
            }
            $scope.$watch('startTime', function () {
                var s = $scope.startTime.split(':')[0];
                var e = '';
                if (s < 23) {
                    e = (+s + 1) + ':00';
                } else {
                    e = '23:00';
                }
                $scope.endTime = e;
            });
            var ac = new BMap.Autocomplete({
                    "input": "txtAddress"
                }
            );
            ac.setLocation('广州');
            ac.addEventListener('onconfirm', function (e) {
                var val = e.item.value;
                var addr = val.province + val.city + val.district + val.street + val.business;
                setPlace(addr);
            });

            function setPlace(addr) {
                var myGeo = new BMap.Geocoder();
                myGeo.getPoint(addr, function (point) {
                    if (point) {
                        that.address.info = addr;
                        that.address.latitude = point['lat'];
                        that.address.longitude = point['lng'];
                        console.log(that.address);
                    }
                }, '广州');
            }
        }

        function create() {
            var obj = angular.copy(that.entity);
            obj.address = that.address.info;
            obj.longitude = that.address.longitude;
            obj.latitude = that.address.latitude;
            orderService.create(obj).then(
                function (data) {
                    if (data.code == 'ok') {
                    }
                }
            );
        }

        function search() {
            var obj = angular.copy(that.entity);
            obj.lon = that.address.longitude;
            obj.lat = that.address.latitude;
            obj.startTime = $scope.startTime;
            obj.endTime = $scope.endTime;
            orderService.matchDriver(obj).then(
                function (result) {
                    if (result.code == 'ok') {
                        that.driverList = result['data'];
                        that.searching = true;
                    }
                }
            );
        }

        function reserve() {
            var obj = angular.copy(that.entity);
            obj.driverId = $('input[name="driverId"]:checked').val();
            obj.address = that.address.info;
            obj.lon = that.address.longitude;
            obj.lat = that.address.latitude;
            obj.startTime = $scope.startTime;
            obj.endTime = $scope.endTime;
            if (obj.type == 'drive') {
                obj.course = '';
            }
            orderService.reserve(obj).then(
                function (result) {
                    if (result.code == 'ok') {
                    }
                }
            );
        }
    }

    function CommentOrderCtrl(orderService, util) {
        var that = this;
        that.score = 3;
        that.comment = '';
        that.saveComment = saveComment;
        activate();

        function activate() {
        }

        function saveComment() {
            var obj = {
                'orderNumber': util.getUrlParam('orderNumber'),
                'score': that.score,
                'comment': that.comment
            };
            orderService.comment(obj).then(
                function (data) {
                    if (data.code == 'ok') {
                    }
                }
            );
        }
    }

    function orderService(httpService) {
        return {
            create: create,
            get: get,
            deleteEntity: deleteEntity,
            matchDriver: matchDriver,
            reserve: reserve,
            start: start,
            accept: accept,
            end: end,
            comment: comment
        };

        function deleteEntity(id) {
            return httpService.callAPI('/admin/order/delete.do?id=' + id);
        }

        function get(id) {
            return httpService.callAPI('/order/rest/get.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/order/rest/create.do', {
                data: entity
            });
        }

        function reserve(entity) {
            return httpService.callAPI('/order/rest/reserve.do', {
                data: entity
            });
        }

        function accept(orderNumber) {
            return httpService.callAPI('/order/rest/accept.do', {
                data: {
                    'orderNumber': orderNumber
                }
            });
        }

        function start(orderNumber, serviceCode) {
            return httpService.callAPI('/order/rest/start.do', {
                data: {
                    'orderNumber': orderNumber,
                    'serviceCode': serviceCode
                }
            });
        }

        function end(orderNumber) {
            return httpService.callAPI('/order/rest/end.do', {
                data: {
                    'orderNumber': orderNumber
                }
            });
        }

        function comment(entity) {
            return httpService.callAPI('/order/rest/comment.do', {
                data: entity
            });
        }

        function matchDriver(entity) {
            return httpService.callAPI('/order/rest/match.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);