<%@ page language="java" contentType="text/html;charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="sysVersion" value="${applicationScope.SysVersion}"></c:set>

<!DOCTYPE html>
<html lang="zh-CN" ng-app="myApp">
	<head>
		<title>网红推广后台管理系统</title>
		<link rel="shortcut icon" href="${ctx}/resources/images/flow.png"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width">
		<link rel="stylesheet" href="${ctx}/resources/css/bootstrap/css/bootstrap.min.css?v=${sysVersion}"/>
		<link rel="stylesheet" href="${ctx}/resources/css/MaterialIcons/material-icons.css?v=${sysVersion}">
		<link rel="stylesheet" href="${ctx}/resources/css/fontawesome/css/font-awesome.min.css?v=${sysVersion}">
		<link rel="stylesheet" href="${ctx}/resources/js/angular/togglemenu_boot/togglemenu.css?v=${sysVersion}">
		<link rel="stylesheet" href="${ctx}/resources/js/angular/angular-aside/css/angular-aside.min.css?v=${sysVersion}">
		<link rel="stylesheet" href="${ctx}/resources/css/app.css?v=${sysVersion}"/>
		
		<script type="text/javascript">
			var ctx = '${ctx}';
			var sysVersion = '${sysVersion}';
		</script>
		
		<link rel="stylesheet" href="${ctx}/resources/js/angular/angular-busy/angular-busy.css?v=${sysVersion}"/>
		<link rel="stylesheet" href="${ctx}/resources/js/angular/toaster/toaster.css?v=${sysVersion}"/>
		
		<script src="${ctx}/resources/js/angular/angular.js?v=${sysVersion}"></script>
		<script src="${ctx}/resources/js/angular/i18n/angular-locale_zh-cn.js?v=${sysVersion}"></script>
		
		<script src="${ctx}/resources/js/angular/angular-route.js?v=${sysVersion}"></script>
		<script src="${ctx}/resources/js/angular/angular-sanitize.min.js?v=${sysVersion}"></script>
		<script src="${ctx}/resources/js/angular/ui-bootstrap-tpls-1.2.0.min.js"></script>
		<script src="${ctx}/resources/js/angular/angular-animate/angular-animate.min.js?v=${sysVersion}"></script>
		<script src="${ctx}/resources/js/angular/angular-busy/angular-busy.js?v=${sysVersion}"></script>
		<script src="${ctx}/resources/js/angular/toaster/toaster.js?v=${sysVersion}"></script>
		
		<script src="${ctx}/resources/js/angular/togglemenu_boot/togglemenu.js?v=${sysVersion}"></script>
		<script src="${ctx}/resources/js/angular/angular-aside/js/angular-aside.min.js?v=${sysVersion}"></script>
		<script src="${ctx}/resources/js/angular/ng-file-upload-shim.min.js"></script>
		<script src="${ctx}/resources/js/angular/ng-file-upload.min.js"></script>

		<script src="${ctx}/app/app.js?v=${sysVersion}"></script>
		
		<script type="text/javascript">
			var menuList = eval('(' + '[{"icon":"fa-folder","subMenus":[{"icon":"fa-file-o","genFile":{"nodeId":"182","projectId":"121","type":"ngjs"},"name":"网红信息","path":"basicinfo/wanghonginfo","type":"link"},{"icon":"fa-file-o","genFile":{"nodeId":"183","projectId":"121","type":"ngjs"},"name":"字典信息","path":"basicinfo/dictionary","type":"link"}],"name":"基础信息","type":"toggle"}]' + ')');
			var projectName = 'wanghong';
			myApp.config(['$routeProvider',
	        	function($routeProvider,$routeParams) {
	          		$routeProvider.
	          		when('/home', {
		          		templateUrl: ctx + '/views/home.html?v=' + sysVersion,
		          		controller: 'HomeCtrl'
	         		})
	         }]);
	         myApp.navNames['/home']='首页';
	         myApp.controller('HomeCtrl', function($rootScope, $scope, $http, $location, $uibModal, toaster, confirmDialogs){
	         });
		</script>
		
		<script src="${ctx}/app/confirmDialogs.js?v=${sysVersion}"></script>
		<script src="${ctx}/ctrl/basicinfo/WanghonginfoCtrl.js"></script>
		<script src="${ctx}/ctrl/basicinfo/DictionaryCtrl.js"></script>
	</head>

	<body ng-controller="MainCtrl">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
				<span style="font-size: 22px;margin-left: 10px;line-height: 45px;cursor: pointer;" ng-if="!leftNavLock" class="pull-left leftNavMenuToggle glyphicon glyphicon-menu-hamburger" aria-hidden="true" ng-click="openAside()"></span>
		    	<span style="font-size: 22px;margin-left: 10px;line-height: 45px;cursor: pointer;" ng-if="leftNavLock" class="pull-left hidden-md hidden-lg leftNavMenuToggle glyphicon glyphicon-menu-hamburger" aria-hidden="true" ng-click="openAside()"></span>
				<a role="button" class="navbar-brand " href="${ctx}/#/home">wanghong</a>
			</div>
			<nav class="collapse navbar-collapse bs-navbar-collapse">
				<%-- 
				<ul class="nav navbar-nav">
					<li ng-repeat="menu in menuList" ng-if="!menu.subMenus" ng-class="{active:isNavActive(menu.path)}">
						<a ng-href="${ctx}#/{{menu.path}}">{{menu.name}}</a>
					</li>
					<li ng-repeat="menu in menuList" ng-if="menu.subMenus" class="dropdown" uib-dropdown="" ng-class="{active:isSubNavActive(menu.subMenus)}">
						<a role="button" class="dropdown-toggle" uib-dropdown-toggle="" aria-haspopup="true" aria-expanded="false">{{menu.name}} <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li ng-repeat="subMenu in menu.subMenus" ng-class="{active:isNavActive(subMenu.path)}">
								<a href="${ctx}#/{{subMenu.path}}">{{subMenu.name}}</a>
							</li>
						</ul>
					</li>
				</ul>
				 --%>
				<ul class="nav navbar-nav navbar-right" style="margin-right: 15px;">
		            <li><a href="${ctx}/#/home" tooltip-placement="bottom" uib-tooltip="设置"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 设置</a></li>
		            <li><a href="${ctx}/sys/logout" tooltip-placement="bottom" uib-tooltip="退出"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 退出</a></li>
		       </ul>
			</nav>
		</nav>
		<div role="main">
			<div layout layout-align="center top" class="navtogglediv navtogglediv-show-hide" ng-show="$root.isRouteLoading" ng-cloak>
				<div style="font-size:52px;" class="fa fa-cog fa-spin"></div>
			</div>
			
			<div class="container-fluid" >
				<div class="row">
					<div ng-show="leftNavLock" class="col-md-2 hidden-sm hidden-xs leftLockNav-show-hide" style="padding: 0px;margin-top: 50px;">
						<ul class="docs-menu">
							<div style="color: #357BB7;padding: 10px 10px 0px;">
								<p>
	                                <i>菜单功能</i>
	                                <span style="font-size: 22px;cursor: pointer;color:#333;" class="pull-right material-icons" ng-click="toggleLeftNavLock()" uib-tooltip="隐藏菜单" tooltip-placement="left">lock</span>
	                            </p>
							</div>
							<li style="cursor: pointer;" ng-repeat="menu in menu.menus" ng-class="{'childActive' : isSectionSelected(menu)}">
					      		 <menu-link menu="menu" ng-if="menu.type === 'link'"></menu-link>
					             <menu-toggle menu="menu" ng-if="menu.type === 'toggle'"></menu-toggle>
					      	</li>
						</ul>
					</div>
					<div ng-class="{'col-md-10':leftNavLock}" class="leftLockNav-show-hide col-sm-12 col-xs-12" ng-style="pageContentStyle">
						<h3 style="etter-spacing: -1px;font-weight:normal;margin-top:10px;">{{getNavName()}}</h3>
						<div ng-view></div>
					</div>
				</div>
				<toaster-container toaster-options="{'time-out': 3000, 'close-button':true, 'position-class': 'toast-bottom-right'}"></toaster-container>
			</div>
			
			<footer class="footer">
				<div class="container">
					<p class="text-muted" align="center">wanghong</p>
				</div>
		    </footer>
		</div>
	</body>
</html>
