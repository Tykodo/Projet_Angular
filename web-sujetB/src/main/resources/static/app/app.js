var app = angular.module('store',[]);
app.controller('StoreController', ['$http', function($http) {
	var store = this;
	store.phones=[];
	$http.get('/phones').then(function successCallback(response){
		store.phones = response.data;
	});	
}]);

app.controller("PhoneController", ['$http', '$window', function($http, $window){
	this.phone = {};
	this.addPhone = function(phone){
		$http.post('/phones/addPhone', this.phone).then(function succesCallback(reponse){
			$window.location.reload();
		});
		StoreController.store.phones.push(this.phone);
		this.phone = {};
	};
	
	this.editPhone = function(phone){
		$http.post('/phones/edit/'+phone.id, this.phone).then(function succesCallback(reponse){
			$window.location.reload();
		});
		/*StoreController.store.phones.push(this.phone);
		this.phone = {};*/
	};
	
	this.removePhone = function(phone){
		$http.post('/phones/remove/'+phone.id).then(function succesCallback(reponse){
			$window.location.reload();
		});
		/*StoreController.store.phones.push();
		this.phone = {};*/
	};
}]);

app.directive('phonePanel', function(){
	return{
		restrict: 'E',
		templateUrl: 'phone-panel.html',
		controllerAs: 'panel',
		
		controller: function(){
		this.tab = 1;

		this.selectTab = function(setTab) {
			this.tab = setTab;
		};

		this.isSelected = function(checkTab) {
			return this.tab === checkTab;
		};
	},
};});
