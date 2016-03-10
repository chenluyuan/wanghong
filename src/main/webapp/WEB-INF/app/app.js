var myApp = angular.module('myApp', ['ui.bootstrap', 'ngRoute', 'ngSanitize','cgBusy', 'toaster', 'util.treeMenu','confirmDialogs', 'ngAside', 'ngFileUpload']);
myApp.navNames = [];
myApp.config(['$routeProvider',
	function($routeProvider,$routeParams) {
		$routeProvider.
		otherwise({
			redirectTo: '/home'
		});
  	}
]);

myApp.factory('MenuService', ['$location','$rootScope','$http','$window',function($location, $rootScope, $http, $window) {
	var self;
	return self = {
	    menus: menuList,
	    toggleSelectMenu: function(menu) {
	    	self.openedMenu = (self.openedMenu === menu ? null : menu);
	    },
	    selectMenu: function(menu) {
	    	self.openedMenu = menu;
	    },
	    isMenuSelected: function(menu) {
	    	return self.openedMenu === menu;
	    },
	    isSelected: function(menu) {
	    	var path = menu.path;
			return $location.path().indexOf(path, $location.path().length - path.length) !== -1;
	    }
	}
}]);

myApp.controller('MainCtrl', function($rootScope, $scope, $window, $http, $location, $uibModal, toaster, $timeout,$aside, MenuService, Upload){
	$rootScope.projectName = projectName;
	$scope.getNavName = function() {
		var navPath = $location.path();
		if(myApp.navNames[navPath]) {
			return myApp.navNames[navPath];
		}
	};
	
	
	angular.element($window).bind('load', function(){
		onWindowSizeChanged();
	});
	
	angular.element($window).bind('resize', function(){
		if(this.resizeTO) clearTimeout(this.resizeTO);
        this.resizeTO = setTimeout(function() {
        	onWindowSizeChanged();
        }, 100);
	});
	
	function onWindowSizeChanged() {
		var topOffset = 50;
		var footerOffset = 32;
	    var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
	    var height = ((window.innerHeight > 0) ? window.innerHeight : screen.height) - 1;
	    height = height - topOffset - footerOffset;
	    if (height < 1) height = 1;
	    
		$scope.pageContentStyle = {
				"border-left": "1px solid #e7e7e7",
				"border-bottom": "1px solid #e7e7e7",
				"background-color" : "#fff",
				"margin-top": (topOffset + 2) + "px",
				"min-height": height+"px"
		}
	    $scope.$apply();
	}
	
	$scope.$on('notify', function(event, toastData) {
		toaster.pop(toastData.type, toastData.title, toastData.info, toastData.timeOut);
	});
	
	$rootScope.$on('$routeChangeStart', function(event, currentRoute, previousRoute) {
		$rootScope.isRouteLoading = true;
	});
    $rootScope.$on('$routeChangeSuccess', function() {
    	$timeout(function() {
    		$rootScope.isRouteLoading = false;
    	}, 50);
    });
	
    $rootScope.leftNavLock = true;
    $scope.toggleLeftNavLock = function() {
    	$rootScope.leftNavLock = !$rootScope.leftNavLock;
    }
    
    $scope.menu = MenuService;
	this.menuClick = function(menu) {
		$location.path(menu.path);
		if($scope.navbarCollapsed) {
			$scope.navbarCollapsed = false;
		}

		if(navSideInstance) {
			navSideInstance.close();
		}
	}
	
	this.isOpen = function(menu) {
		return MenuService.isMenuSelected(menu)
 	}
	this.toggleOpen = function(menu) {
		MenuService.toggleSelectMenu(menu);
	}
	this.isSelected = function(menu) {
		return MenuService.isSelected(menu);
	}
	
	var navSideInstance;
	$scope.openAside = function() {
		navSideInstance = $aside.open({
			template: '\
				<div class="modal-header">\
				    <h4 class="modal-title">{{$root.projectName}}</h4>\
				</div>\
				<div class="modal-body" style="padding: 0px;">\
				    <ul class="docs-menu" style="margin-top: 10px;">\
				    	<div style="color: #357BB7;padding: 10px 10px 0px;">\
							<p>\
								<i>菜单功能</i>\
								<span style="font-size: 22px;cursor: pointer;color:#333;" class="pull-right material-icons" ng-click="toggleLeftNavLock()" uib-tooltip="锁定菜单" tooltip-placement="left">lock_open</span>\
							</p>\
						</div>\
						<li style="cursor: pointer;" ng-repeat="menu in menu.menus" ng-class="{\'childActive\' : isSectionSelected(menu)}">\
							<menu-link menu="menu" ng-if="menu.type === \'link\'"></menu-link>\
				             <menu-toggle menu="menu" ng-if="menu.type === \'toggle\'"></menu-toggle>\
				      	</li>\
					</ul>\
				</div>\
				<div class="modal-footer" style="position:fixed;bottom: 10px;">\
				    <button class="btn btn-warning" type="button" ng-click="cancel()">退出登录</button>\
				</div>\
			',
			placement: 'left',
			windowClass: 'leftNavSide',
			backdrop: true,
			animation: true,
			controller: function($rootScope, $scope, MenuService, $uibModalInstance) {
				$scope.menu = MenuService;
				$scope.toggleLeftNavLock = function() {
					$rootScope.leftNavLock = !$rootScope.leftNavLock;
					$uibModalInstance.close();
				}
          	}
        })
	}
});
