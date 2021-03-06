//==============================================================================================
//				OSBO (Original Script By Ognjen)
//==============================================================================================

var app = angular.module("ppa", ["ngRoute", "ngAnimate"]);

app.config(["$routeProvider", function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: '/app/html/partial/login.html'
	})
	.when('/pocetna', {
		templateUrl: '/app/html/partial/pocetna.html'
	})
	.when('/dobavljaci', {
		templateUrl: '/app/html/partial/dobavljaci.html'
	})
	.when('/nabavke', {
		templateUrl: '/app/html/partial/nabavke.html'
	})
	.when('/ugovori', {
		templateUrl: '/app/html/partial/ugovori.html'
	})
	.when('/ugovori_nabavke', {
		templateUrl: '/app/html/partial/ugovori_nabavke.html'
	})
	.when('/ugovori_dobavljaca', {
		templateUrl: '/app/html/partial/ugovori_dobavljaca.html'
	})
	.otherwise({
		redirectTo: '/'
	});
}
]);

//Data-share via service
app.service("DataShare", function(){
	var id = "";
	
	var addId = function(nId) {
		id = nId;
	};

	var getId = function(){
		return id;
	};

	return {addId: addId, getId: getId};

});

//Data-share via $rootScope
app.run(function($rootScope) {
    $rootScope.oznaka = "";
    $rootScope.interniBroj = "";
    $rootScope.maticniBroj = "";
});

//==============================================================================================
//								NABAVKE CONTROLLER
//==============================================================================================
app.controller("nabavkeCtrl", function($scope, $location, $http, $routeParams, DataShare, $rootScope){

	var URLnabavke = "/api/nabavke";
	var URLvrstePostupka = "/api/vrstePostupka";
	var URLvrstePredmeta = "/api/vrstePredmeta";

	$scope.nabavke = [];
	$scope.vrstePostupka = [];
	$scope.vrstePredmeta = [];

	$scope.novaNabavka = {};
	$scope.novaNabavka.oznaka = "";
	$scope.novaNabavka.procenjenaVrednost = "";
	$scope.novaNabavka.vrstaPostupkaId = "";
	$scope.novaNabavka.vrstaPredmetaId = "";
	$scope.novaNabavka.datumOtvaranja = "";

	$scope.trazenaNabavka = {};
	$scope.trazenaNabavka.oznaka = "";
	$scope.trazenaNabavka.procenjenaVrednostMin = "";
	$scope.trazenaNabavka.procenjenaVrednostMax = "";
	$scope.trazenaNabavka.vrstaPostupkaId = "";
	$scope.trazenaNabavka.vrstaPredmetaId = "";

	$scope.pageNum = 0;
	$scope.totalPages = "";

	$scope.totalNabavke = "";


	//==============================================================================================
	//	CRUD METODE
	//==============================================================================================
	
	var getNabavke = function() {

		var config = {params: {}};

		if($scope.trazenaNabavka.oznaka != "") {
			config.params.oznaka = $scope.trazenaNabavka.oznaka;
		}
		if($scope.trazenaNabavka.procenjenaVrednostMin != "") {
			config.params.procenjenaVrednostMin = $scope.trazenaNabavka.procenjenaVrednostMin;
		}
		if($scope.trazenaNabavka.procenjenaVrednostMax != "") {
			config.params.procenjenaVrednostMax = $scope.trazenaNabavka.procenjenaVrednostMax;
		}
		if($scope.trazenaNabavka.vrstaPostupkaId != "") {
			config.params.vrstaPostupkaId = $scope.trazenaNabavka.vrstaPostupkaId;
		}
		if($scope.trazenaNabavka.vrstaPredmetaId != "") {
			config.params.vrstaPredmetaId = $scope.trazenaNabavka.vrstaPredmetaId;
		}
		config.params.pageNum = $scope.pageNum;

		var promise = $http.get(URLnabavke, config);
		promise.then(
				function success(response){
					$scope.nabavke = response.data;
					$scope.totalNabavke = response.headers("totalNabavke");
					$scope.totalPages = response.headers("totalPages");
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePostupka = function () {
		var promise = $http.get(URLvrstePostupka);
		promise.then(
				function success(response){
					$scope.vrstePostupka = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePredmeta = function(){
		var promise = $http.get(URLvrstePredmeta);
		promise.then(
				function success(response){
					$scope.vrstePredmeta = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	getNabavke();
	getVrstePostupka();
	getVrstePredmeta();

	var postojiOznaka = function() {
		for (var i = 0; i < $scope.nabavke.length; i++) {
			if($scope.nabavke[i].oznaka === $scope.novaNabavka.oznaka) {
				return true;
			}
		}
		return false;
	};

	$scope.save = function(){
		if($scope.novaNabavka.id == null) {
			if(!postojiOznaka()) {
				var promise = $http.post(URLnabavke, $scope.novaNabavka);
				promise.then(
					function success(response){
						alert("Uspesno ste dodali nabavku!");
						getNabavke();
						$scope.novaNabavka = null;
					},
					function error(response){
						alert("Greska pri dodavanju nabavke!");
						console.log(response.data);
					}
				);
			} else {
				alert("Oznaka nabavke postoji!");
			}
		} else {
			if($scope.novaNabavka.oznaka === $rootScope.oznaka) {
				var promise = $http.put(URLnabavke + "/" + $scope.novaNabavka.id, $scope.novaNabavka);
				promise.then(
					function success(response){
						alert("Uspesno ste izmenili nabavku!");
						getNabavke();
						$scope.novaNabavka = null;
					},
					function error(response){
						alert("Nije moguce izmeniti nabavku!");
						console.log(response.data);
					}
				);
			} else {
				if(!postojiOznaka()) {
					var promise = $http.put(URLnabavke + "/" + $scope.novaNabavka.id, $scope.novaNabavka);
					promise.then(
						function success(response){
							alert("Uspesno ste izmenili nabavku!");
							getNabavke();
							$scope.novaNabavka = null;
						},
						function error(response){
							alert("Nije moguce izmeniti nabavku!");
							console.log(response.data);
						}
					);
				} else {
					alert("Oznaka nabavke postoji!");
				}
			}
		}

	};

	$scope.editHere = function(id) {
		$scope.show = true;
		var promise = $http.get(URLnabavke + "/" + id);
		promise.then(
				function success(response){
					$scope.novaNabavka = response.data;
//					$scope.novaNabavka.datumOtvaranja = new Date($scope.novaNabavka.datumOtvaranja);
					$rootScope.oznaka = response.data.oznaka;
				},
				function error(response){
					alert("Nije moguce dobaviti nabavku za izmenu!");
					console.log(response.data);
				}
		);
	};

	$scope.delete = function(id){
		var promise = $http.delete(URLnabavke + "/" + id);
		promise.then(
				function success(response){
					getNabavke();
				},
				function error(response){
					alert("Nije moguce obrisati nabavku!");
					console.log(response.data);
				}
		);
	}

	$scope.view = function(nId) {
		DataShare.addId(nId);
		$location.path('/ugovori_nabavke');
	}

	//==============================================================================================
	//	DODATNE METODE
	//==============================================================================================

	// CHANGE VIEW Method
	$scope.option = "Pretraga";
	$scope.optionText = "Unos nove nabavke";
	$scope.iconClass = "glyphicon glyphicon-search";
	$scope.show = true;

	$scope.changeView = function () {
		$scope.show = !$scope.show;
		if(!$scope.show) {
			$scope.option = "Unos";
			$scope.iconClass = "glyphicon glyphicon-plus";
			$scope.optionText = "Pretraga nabavki";
		} else {
			$scope.option = "Pretraga";
			$scope.iconClass = "glyphicon glyphicon-search";
			$scope.optionText = "Unos nove nabavke";
		}
	}

	$scope.search = function(){
		$scope.pageNum = 0;
		getNabavke();
	};


	// PROMENA STRANICE
	// >> NEXT method
	$scope.next = function(){
		if($scope.pageNum < $scope.totalPages - 1) {
			$scope.pageNum = $scope.pageNum + 1;
			getNabavke();
		}
	};

	// << PREV method
	$scope.prev = function(){
		if($scope.pageNum > 0) {
			$scope.pageNum = $scope.pageNum - 1;
			getNabavke();
		}
	};


});

//==============================================================================================
//								DOBAVLJACI CONTROLLER
//==============================================================================================
app.controller("dobavljaciCtrl", function($scope, $location, $http, $routeParams, DataShare, $rootScope){

	var URLdobavljaci = "/api/dobavljaci";

	$scope.dobavljaci = [];

	$scope.noviDobavljac = {};
	$scope.noviDobavljac.naziv = "";
	$scope.noviDobavljac.maticniBroj = "";

	$scope.trazeniDobavljac = {};
	$scope.trazeniDobavljac.naziv = "";
	$scope.trazeniDobavljac.maticniBroj = "";

	$scope.pageNum = 0;
	$scope.totalPages = "";

	$scope.totalDobavljaci = "";


	//==============================================================================================
	//	CRUD METODE
	//==============================================================================================

	var getDobavljaci = function() {

		var config = {params: {}};

		if($scope.trazeniDobavljac.naziv != "") {
			config.params.naziv = $scope.trazeniDobavljac.naziv;
		}
		if($scope.trazeniDobavljac.maticniBroj != "") {
			config.params.maticniBroj = $scope.trazeniDobavljac.maticniBroj;
		}
		config.params.pageNum = $scope.pageNum;

		var promise = $http.get(URLdobavljaci, config);
		promise.then(
				function success(response){
					$scope.dobavljaci = response.data;
					$scope.totalDobavljaci = response.headers("totalDobavljaci");
					$scope.totalPages = response.headers("totalPages");
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	getDobavljaci();
	
	var postojiMaticniBroj = function() {
		for (var i = 0; i < $scope.dobavljaci.length; i++) {
			if($scope.dobavljaci[i].maticniBroj === $scope.noviDobavljac.maticniBroj) {
				return true;
			}
		}
		return false;
	};

	$scope.save = function(){
		if($scope.noviDobavljac.id == null) {
			if(!postojiMaticniBroj()){
				var promise = $http.post(URLdobavljaci, $scope.noviDobavljac);
				promise.then(
						function success(response){
							getDobavljaci();
							alert("Uspesno ste dodatli dobavljača!");
							$scope.noviDobavljac = null;
						},
						function error(response){
							alert("Greska pri dodavanju dobavljaca!");
							console.log(response.data);
						}
				);
			} else {
				alert("Matični broj već postoji!");
			}
		} else {
			if($scope.noviDobavljac.maticniBroj === $rootScope.maticniBroj){
				var promise = $http.put(URLdobavljaci + "/" + $scope.noviDobavljac.id, $scope.noviDobavljac);
				promise.then(
						function success(response){
							alert("Uspesno ste izmenili dobavljaca!");
							getDobavljaci();
							$scope.noviDobavljac = null;
						},
						function error(response){
							alert("Nije moguce izmeniti ldobavljacaub!");
							console.log(response.data);
						}
				);
			} else {
				if(!postojiMaticniBroj()){
					var promise = $http.put(URLdobavljaci + "/" + $scope.noviDobavljac.id, $scope.noviDobavljac);
					promise.then(
							function success(response){
								alert("Uspesno ste izmenili dobavljaca!");
								getDobavljaci();
								$scope.noviDobavljac = null;
							},
							function error(response){
								alert("Nije moguce izmeniti ldobavljacaub!");
								console.log(response.data);
							}
					);
				}else{
					alert("Matični broj već postoji!");
				}
			}
		}
	};

	$scope.editHere = function(id) {
		$scope.show = true;
		var promise = $http.get(URLdobavljaci + "/" + id);
		promise.then(
				function success(response){
					$scope.noviDobavljac = response.data;
					$rootScope.maticniBroj = response.data.maticniBroj;
				},
				function error(response){
					alert("Nije moguce dobaviti dobavljaca za izmenu!");
					console.log(response.data);
				}
		);
	};

	$scope.delete = function(id){
		var promise = $http.delete(URLdobavljaci + "/" + id);
		promise.then(
				function success(response){
					getDobavljaci();
				},
				function error(response){
					alert("Nije moguce obrisati dobavljaca!");
					console.log(response.data);
				}
		);
	}

	$scope.view = function(nId) {
		DataShare.addId(nId);
		$location.path('/ugovori_dobavljaca');
	}

	//==============================================================================================
	//	DODATNE METODE
	//==============================================================================================

	// CHANGE VIEW Method
	$scope.option = "Pretraga";
	$scope.optionText = "Unos novog dobavljača";
	$scope.iconClass = "glyphicon glyphicon-search";
	$scope.show = true;

	$scope.changeView = function () {
		$scope.show = !$scope.show;
		if(!$scope.show) {
			$scope.option = "Unos";
			$scope.iconClass = "glyphicon glyphicon-plus";
			$scope.optionText = "Pretraga dobavljača";
		} else {
			$scope.option = "Pretraga";
			$scope.iconClass = "glyphicon glyphicon-search";
			$scope.optionText = "Unos novog dobavljača";
		}
	}
	
	$scope.search = function(){
		$scope.pageNum = 0;
		getDobavljaci();
	};


	// PROMENA STRANICE
	// >> NEXT method
	$scope.next = function(){
		if($scope.pageNum < $scope.totalPages - 1) {
			$scope.pageNum = $scope.pageNum + 1;
			getDobavljaci();
		}
	};

	// << PREV method
	$scope.prev = function(){
		if($scope.pageNum > 0) {
			$scope.pageNum = $scope.pageNum - 1;
			getDobavljaci();
		}
	};
});

//==============================================================================================
//								UGOVORI CONTROLLER
//==============================================================================================
app.controller("ugovoriCtrl", function($scope, $location, $http, $routeParams, DataShare, $rootScope){

	var URLugovori = "/api/ugovori";
	var URLnabavke = "/api/nabavke";
	var URLvrstePostupka = "/api/vrstePostupka";
	var URLvrstePredmeta = "/api/vrstePredmeta";

	$scope.ugovori = [];
	$scope.nabavke = [];
	$scope.vrstePostupka = [];
	$scope.vrstePredmeta = [];

	$scope.noviUgovor = {};
	$scope.noviUgovor.interniBroj = "";
	$scope.noviUgovor.ugovorenaVrednost = "";
	$scope.noviUgovor.datumZakljucenja = "";
	$scope.noviUgovor.nabavkaId = "";
	$scope.noviUgovor.dobavljacNaziv = "";
	$scope.noviUgovor.dobavljacMaticniBroj = "";

	$scope.trazeniUgovor = {};
	$scope.trazeniUgovor.interniBroj = "";
	$scope.trazeniUgovor.ugovorenaVrednostMin = "";
	$scope.trazeniUgovor.ugovorenaVrednostMax = "";
	$scope.trazeniUgovor.datumZakljucenjaOd = "";
	$scope.trazeniUgovor.datumZakljucenjaDo = "";
	$scope.trazeniUgovor.nabavkaId = "";
	$scope.trazeniUgovor.dobavljacNaziv = "";
	$scope.trazeniUgovor.dobavljacMaticniBroj = "";
	$scope.trazeniUgovor.vrstaPostupkaId = "";
	$scope.trazeniUgovor.vrstaPredmetaId = "";

	$scope.pageNum = 0;
	$scope.totalPages = "";
	$scope.totalUgovori = "";

	//==============================================================================================
	//	CRUD METODE
	//==============================================================================================

	var getUgovori = function() {

		var config = {params: {}};

		if($scope.trazeniUgovor.interniBroj != "") {
			config.params.interniBroj = $scope.trazeniUgovor.interniBroj;
		}
		if($scope.trazeniUgovor.ugovorenaVrednostMin != "") {
			config.params.ugovorenaVrednostMin = $scope.trazeniUgovor.ugovorenaVrednostMin;
		}
		if($scope.trazeniUgovor.ugovorenaVrednostMax != "") {
			config.params.ugovorenaVrednostMax = $scope.trazeniUgovor.ugovorenaVrednostMax;
		}
		if($scope.trazeniUgovor.datumZakljucenjaOd != "") {
			config.params.datumZakljucenjaOd = $scope.trazeniUgovor.datumZakljucenjaOd;
		}
		if($scope.trazeniUgovor.datumZakljucenjaDo != "") {
			config.params.datumZakljucenjaDo = $scope.trazeniUgovor.datumZakljucenjaDo;
		}
		if($scope.trazeniUgovor.nabavkaId != "") {
			config.params.nabavkaId = $scope.trazeniUgovor.nabavkaId;
		}
		if($scope.trazeniUgovor.dobavljacNaziv != "") {
			config.params.dobavljacNaziv = $scope.trazeniUgovor.dobavljacNaziv;
		}
		if($scope.trazeniUgovor.dobavljacMaticniBroj != "") {
			config.params.dobavljacMaticniBroj = $scope.trazeniUgovor.dobavljacMaticniBroj;
		}
		if($scope.trazeniUgovor.vrstaPostupkaId != "") {
			config.params.vrstaPostupkaId = $scope.trazeniUgovor.vrstaPostupkaId;
		}
		if($scope.trazeniUgovor.vrstaPredmetaId != "") {
			config.params.vrstaPredmetaId = $scope.trazeniUgovor.vrstaPredmetaId;
		}
		config.params.pageNum = $scope.pageNum;

		var promise = $http.get(URLugovori, config);
		promise.then(
				function success(response){
					$scope.ugovori = response.data;
					$scope.totalUgovori = response.headers("totalUgovori");
					$scope.totalPages = response.headers("totalPages");
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getNabavke = function() {
		var promise = $http.get(URLnabavke+"/zaUgovore");
		promise.then(
				function success(response){
					$scope.nabavke = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePostupka = function(){
		var promise = $http.get(URLvrstePostupka);
		promise.then(
				function success(response){
					$scope.vrstePostupka = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePredmeta = function(){
		var promise = $http.get(URLvrstePredmeta);
		promise.then(
				function success(response){
					$scope.vrstePredmeta = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	getUgovori();
	getNabavke();
	getVrstePostupka();
	getVrstePredmeta();

	var postojiInterniBroj = function() {
		for (var i = 0; i < $scope.ugovori.length; i++) {
			if($scope.ugovori[i].interniBroj === $scope.noviUgovor.interniBroj) {
				return true;
			}
		}
		return false;
	};
	
	$scope.save = function(){
		if($scope.noviUgovor.id == null) {
			if(!postojiInterniBroj()){
				var promise = $http.post(URLugovori, $scope.noviUgovor);
				promise.then(
						function success(response){
							getUgovori();
							alert("Uspesno ste dodali ugovor!");
							$scope.noviUgovor = null;
						},
						function error(response){
							alert("Greska pri dodavanju ugovora!");
							console.log(response.data);
						}
				);
			} else {
				alert("Interni broj već postoji!");
			}
		} else {
			if($scope.noviUgovor.interniBroj === $rootScope.interniBroj) {
				var promise = $http.put(URLugovori + "/" + $scope.noviUgovor.id, $scope.noviUgovor);
				promise.then(
						function success(response){
							alert("Uspesno ste izmenili ugovor!");
							getUgovori();
							$scope.noviUgovor = null;
						},
						function error(response){
							alert("Nije moguce izmeniti ugovor!");
							console.log(response.data);
						}
				);
			} else {
				if(!postojiInterniBroj()) {
					var promise = $http.put(URLugovori + "/" + $scope.noviUgovor.id, $scope.noviUgovor);
					promise.then(
							function success(response){
								alert("Uspesno ste izmenili ugovor!");
								getUgovori();
								$scope.noviUgovor = null;
							},
							function error(response){
								alert("Nije moguce izmeniti ugovor!");
								console.log(response.data);
							}
					);
				} else {
					alert("Interni broj već postoji!");
				}
			}
		}
	};

	$scope.editHere = function(id) {
		$scope.show = true;
		var promise = $http.get(URLugovori + "/" + id);
		promise.then(
				function success(response){
					$scope.noviUgovor = response.data;
//					$scope.noviUgovor.datumZakljucenja = new Date($scope.noviUgovor.datumZakljucenja);
					$rootScope.interniBroj = response.data.interniBroj;
				},
				function error(response){
					alert("Nije moguce dobaviti ugovor za izmenu!");
					console.log(response.data);
				}
		);
	};

	$scope.delete = function(id){
		var promise = $http.delete(URLugovori + "/" + id);
		promise.then(
				function success(response){
					getUgovori();
				},
				function error(response){
					alert("Nije moguce obrisati ugovor!");
					console.log(response.data);
				}
		);
	}

	//==============================================================================================
	//	DODATNE METODE
	//==============================================================================================

	// CHANGE VIEW Method
	$scope.option = "Pretraga";
	$scope.optionText = "Unos novog ugovora";
	$scope.iconClass = "glyphicon glyphicon-search";
	$scope.show = true;

	$scope.changeView = function () {
		$scope.show = !$scope.show;
		if(!$scope.show) {
			$scope.option = "Unos";
			$scope.iconClass = "glyphicon glyphicon-plus";
			$scope.optionText = "Pretraga ugovora";
		} else {
			$scope.option = "Pretraga";
			$scope.iconClass = "glyphicon glyphicon-search";
			$scope.optionText = "Unos novog ugovora";
		}
	}

	$scope.search = function(){
		$scope.pageNum = 0;
		getUgovori();
	};


	// PROMENA STRANICE
	// >> NEXT method
	$scope.next = function(){
		if($scope.pageNum < $scope.totalPages - 1) {
			$scope.pageNum = $scope.pageNum + 1;
			getUgovori();
		}
	};

	// << PREV method
	$scope.prev = function(){
		if($scope.pageNum > 0) {
			$scope.pageNum = $scope.pageNum - 1;
			getUgovori();
		}
	};

});

//==============================================================================================
//								UGOVORI NABAVKE CONTROLLER
//==============================================================================================
app.controller("ugovoriNabavkeCtrl", function($scope, $location, $http, $routeParams, DataShare){

	var URLnabavke = "/api/nabavke";
	var nabavkaId = DataShare.getId();
	var URLvrstePostupka = "/api/vrstePostupka";
	var URLvrstePredmeta = "/api/vrstePredmeta";

	$scope.ugovori = [];
	$scope.nabavke = [];
	$scope.vrstePostupka = [];
	$scope.vrstePredmeta = [];

	$scope.pageNum = 0;
	$scope.totalPages = "";
	$scope.totalUgovori = "";

	//==============================================================================================
	//	CRUD METODE
	//==============================================================================================

	var getUgovori = function() {

		var config = {params: {}};

		config.params.pageNum = $scope.pageNum;

		var promise = $http.get(URLnabavke + "/" + nabavkaId + "/ugovori", config);
		promise.then(
				function success(response){
					$scope.ugovori = response.data;
					$scope.totalUgovori = response.headers("totalUgovori");
					$scope.totalPages = response.headers("totalPages");
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getNabavke = function() {
		var promise = $http.get(URLnabavke+"/zaUgovore");
		promise.then(
				function success(response){
					$scope.nabavke = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePostupka = function(){
		var promise = $http.get(URLvrstePostupka);
		promise.then(
				function success(response){
					$scope.vrstePostupka = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePredmeta = function(){
		var promise = $http.get(URLvrstePredmeta);
		promise.then(
				function success(response){
					$scope.vrstePredmeta = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	getUgovori();
	getNabavke();
	getVrstePostupka();
	getVrstePredmeta();

	//==============================================================================================
	//	DODATNE METODE
	//==============================================================================================

	$scope.back = function () {
		$location.path('/nabavke');
	};

	// PROMENA STRANICE
	// >> NEXT method
	$scope.next = function(){
		if($scope.pageNum < $scope.totalPages - 1) {
			$scope.pageNum = $scope.pageNum + 1;
			getUgovori();
		}
	};

	// << PREV method
	$scope.prev = function(){
		if($scope.pageNum > 0) {
			$scope.pageNum = $scope.pageNum - 1;
			getUgovori();
		}
	};

});

//==============================================================================================
//								UGOVORI DOBAVLJACA CONTROLLER
//==============================================================================================
app.controller("ugovoriDobavljacaCtrl", function($scope, $location, $http, $routeParams, DataShare){

	var URLdobavljaci = "/api/dobavljaci";
	var URLnabavke = "/api/nabavke";
	var dobavljacId = DataShare.getId();
	var URLvrstePostupka = "/api/vrstePostupka";
	var URLvrstePredmeta = "/api/vrstePredmeta";

	$scope.ugovori = [];
	$scope.nabavke = [];
	$scope.vrstePostupka = [];
	$scope.vrstePredmeta = [];

	$scope.pageNum = 0;
	$scope.totalPages = "";
	$scope.totalUgovori = "";

	//==============================================================================================
	//	CRUD METODE
	//==============================================================================================

	var getUgovori = function() {

		var config = {params: {}};

		config.params.pageNum = $scope.pageNum;

		var promise = $http.get(URLdobavljaci + "/" + dobavljacId + "/ugovori", config);
		promise.then(
				function success(response){
					$scope.ugovori = response.data;
					$scope.totalUgovori = response.headers("totalUgovori");
					$scope.totalPages = response.headers("totalPages");
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getNabavke = function() {
		var promise = $http.get(URLnabavke+"/zaUgovore");
		promise.then(
				function success(response){
					$scope.nabavke = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePostupka = function(){
		var promise = $http.get(URLvrstePostupka);
		promise.then(
				function success(response){
					$scope.vrstePostupka = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	var getVrstePredmeta = function(){
		var promise = $http.get(URLvrstePredmeta);
		promise.then(
				function success(response){
					$scope.vrstePredmeta = response.data;
				},
				function error(response){
					console.log(response.data);
				}
		);
	};

	getUgovori();
	getNabavke();
	getVrstePostupka();
	getVrstePredmeta();

	//==============================================================================================
	//	DODATNE METODE
	//==============================================================================================

	$scope.back = function () {
		$location.path('/dobavljaci');
	};

	// PROMENA STRANICE
	// >> NEXT method
	$scope.next = function(){
		if($scope.pageNum < $scope.totalPages - 1) {
			$scope.pageNum = $scope.pageNum + 1;
			getUgovori();
		}
	};

	// << PREV method
	$scope.prev = function(){
		if($scope.pageNum > 0) {
			$scope.pageNum = $scope.pageNum - 1;
			getUgovori();
		}
	};

});

//==============================================================================================
//LOGIN CONTROLLER
//==============================================================================================
app.controller("loginCtrl", function($scope, $location, $http){

	var URLlogin = "/api/login";

	$scope.korisnik = {};
	$scope.korisnik.korisnickoIme = "";
	$scope.korisnik.lozinka = "";

//	==============================================================================================
//	CRUD METODE
//	==============================================================================================

	$scope.search = function() {

		var config = {params: {}};

		if($scope.korisnik.korisnickoIme != "") {
			config.params.korisnickoIme = $scope.korisnik.korisnickoIme;
		}
		if($scope.korisnik.korisnickoIme != "") {
			config.params.korisnickoIme = $scope.korisnik.korisnickoIme;
		}

		var promise = $http.get(URLlogin, config);
		promise.then(
				function success(response){
					$location.path('/pocetna');
				},
				function error(response){
					console.log(response.data);
					$location.path('/login');
				}
		);
	};
});