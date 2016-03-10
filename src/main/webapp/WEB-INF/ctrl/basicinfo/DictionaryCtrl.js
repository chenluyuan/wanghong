myApp.config(['$routeProvider',function($routeProvider,$routeParams) {
	$routeProvider.
	when('/basicinfo/dictionary', {
		templateUrl: ctx + '/views/basicinfo/DictionaryListTpl.html?v=&' + sysVersion,
		controller: 'DictionaryCtrl'
	})
}]);
myApp.navNames['/basicinfo/dictionary']='字典信息';


myApp.controller('DictionaryCtrl', function($scope, $uibModal, $http, confirmDialogs){
	$scope.pagination = {start: 0, limit: 20, maxSize: 8, currentPage: 1, limitOptions:[10,20,50,100]};
	$scope.searchForm = {extLimit: $scope.pagination};

	$scope.search = function() {
		$scope.promise = $http.post(ctx + '/basicinfo/DictionaryCtrl/search', $scope.searchForm).success(function(data){
			$scope.dataList = data;
		});
	};
	$scope.pageChanged = function() {
		$scope.pagination.start = ($scope.pagination.currentPage - 1) * $scope.pagination.limit;
		$scope.search();
	};
	
	$scope.clearSearch = function() {
		$scope.searchForm.searchParm = '';
		$scope.search();
	}
	
	$scope.edit = function(id) {
		$uibModal.open({
			templateUrl: ctx + '/views/basicinfo/DictionaryEditTpl.html?v=',
			controller: 'DictionaryEditCtrl',
			backdrop: 'static',
			resolve: {
				id: function() {return id;}
			}
		}).result.then(function (data) {
			if(data == 'success') {
				$scope.search();
			}
		});
	};
	
	$scope.del = function(item) {
		confirmDialogs.normal('确认', '确认删除 ' + item.name + '?')
		.result.then(function (btn) {
			if(btn == 'ok') {
				$scope.promise = $http.get(ctx + '/basicinfo/DictionaryCtrl/delete?id='+item.id).success(function(data){
					if(data.result == "success") {
						$scope.$root.$broadcast('notify', {type:'success',title:'提示',info:'删除成功',timeOut:2000});
						$scope.search();
					}
					else {
						confirmDialogs.error('错误', data.info);
					}
				});
			}
		});
	};
	
	$scope.search();
});
myApp.controller('DictionaryEditCtrl', function($scope, $http, $uibModalInstance, confirmDialogs,id){
	$scope.editForm = {};
	$scope.editForm.id = id;
	$scope.statusArray = [{name: '活跃', value: true},{name: '不活跃', value: false}];
	if(id && id > 0) {
		$scope.title = '编辑';
		$scope.promise = $http.get(ctx + '/basicinfo/DictionaryCtrl/getDetailInfo?id='+id).success(function(data){
			$scope.editForm = data.data;
		});
	}
	else {
		$scope.title = '新增';
	}

	$scope.save = function() {
		$scope.promise = $http.post(ctx + '/basicinfo/DictionaryCtrl/save', $scope.editForm).success(function(data){
			$uibModalInstance.close('success');
			if(data.result == 'success') {
				$scope.$root.$broadcast('notify', {type:'success',title:'提示',info:'保存成功',timeOut:2000});
			}
			else {
				confirmDialogs.error('错误', data.info);
			}
		});
	};
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	}
});
