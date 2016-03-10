myApp.config(['$routeProvider',function($routeProvider,$routeParams) {
	$routeProvider.
	when('/basicinfo/wanghonginfo', {
		templateUrl: ctx + '/views/basicinfo/WanghonginfoListTpl.html?v=&' + sysVersion,
		controller: 'WanghonginfoCtrl'
	})
}]);
myApp.navNames['/basicinfo/wanghonginfo']='网红信息';


myApp.controller('WanghonginfoCtrl', function($scope, $uibModal, $http, confirmDialogs, Upload, $window){
	$scope.pagination = {start: 0, limit: 20, maxSize: 8, currentPage: 1, limitOptions:[10,20,50,100]};
	$scope.searchForm = {extLimit: $scope.pagination};
	//$scope.typeArray = [{name: '类别', value: 'TYPE'},{name: '微信好友数', value: 'WX_FRIEND_NO'},{name: '微博好友数', value: 'WB_FRIEND_NO'},{name: '地区', value: 'AREA'},{name: '学校', value: 'SCHOOL'},{name: '评级', value: 'LEVEL'},{name: '备注', value: 'REMARK'}];

	$scope.search = function() {
		$scope.promise = $http.post(ctx + '/basicinfo/WanghonginfoCtrl/search', $scope.searchForm).success(function(data){
			$scope.dataList = data;
		});
	};
	$scope.pageChanged = function() {
		$scope.pagination.start = ($scope.pagination.currentPage - 1) * $scope.pagination.limit;
		$scope.search();
	};
	
	$scope.clearSearch = function() {
		$scope.searchForm.type = '';
		$scope.searchForm.name = '';
		$scope.searchForm.wxFriendNo = '';
		$scope.searchForm.wbFriendNo = '';
		$scope.searchForm.wbLink = '';
		$scope.searchForm.area = '';
		$scope.searchForm.school = '';
		$scope.searchForm.level = '';
		$scope.searchForm.remark = '';
		$scope.search();
	};

	$scope.upload = function (file) {
		if(file) {
			$scope.promise = Upload.upload({
				url: '/basicinfo/WanghonginfoCtrl/upload',
				data: {file: file}
			}).then(function (resp) {
				if(resp.data.result == "success") {
					$scope.$root.$broadcast('notify', {type:'success',title:'提示',info:'上传成功',timeOut:2000});
					$scope.search();
				}
				else {
					confirmDialogs.error('错误', resp.data.info);
				}
			});
		}
	};

	$scope.exportExcel = function() {
		if($scope.choseArr.length === 0) {
			confirmDialogs.error('错误', '请至少选择一条记录！');
			return;
		}
		$window.open(ctx + '/basicinfo/WanghonginfoCtrl/export?ids='+$scope.choseArr.join(','));
	};

	$scope.choseArr=[];//定义数组用于存放前端显示
	$scope.x=false;//默认未选中

	$scope.all = function (c) {//全选
		if(c){
			$scope.x=true;
			angular.forEach($scope.dataList.invdata, function(item) {
				$scope.choseArr.push(item.id);
			});
		}else{
			$scope.x=false;
			$scope.choseArr=[];
		}
	};

	$scope.chk = function (z,x) {//单选或者多选
		if (x) {//选中
			$scope.choseArr.push(z);
		} else {
			$scope.choseArr.splice($scope.choseArr.indexOf(z), 1);
		}
	};


	$scope.edit = function(id) {
		$uibModal.open({
			templateUrl: ctx + '/views/basicinfo/WanghonginfoEditTpl.html?v=',
			controller: 'WanghonginfoEditCtrl',
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
				$scope.promise = $http.get(ctx + '/basicinfo/WanghonginfoCtrl/delete?id='+item.id).success(function(data){
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
myApp.controller('WanghonginfoEditCtrl', function($scope, $http, $uibModalInstance, confirmDialogs,id){
	$scope.editForm = {};
	$scope.editForm.id = id;
	if(id && id > 0) {
		$scope.title = '编辑';
		$scope.promise = $http.get(ctx + '/basicinfo/WanghonginfoCtrl/getDetailInfo?id='+id).success(function(data){
			$scope.editForm = data.data;
		});
	}
	else {
		$scope.title = '新增';
	}

	$scope.save = function() {
		$scope.promise = $http.post(ctx + '/basicinfo/WanghonginfoCtrl/save', $scope.editForm).success(function(data){
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
