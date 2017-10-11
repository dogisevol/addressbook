( function() {

	'use strict';

	angular.module( 'controllers', [] ).controller( 'addressBookController', function( $scope, $location, contacts, metawidgetConfig ) {
		contacts.then( function( result ) {
			$scope.contacts = result.data;
		} );

		$scope.metawidgetConfig = metawidgetConfig;

		$scope.search = {
			firstName: '',
			lastName: '',
			type: ''
		};

		$scope.searchActions = {
			search: function() {
				$scope.filter = {};
				if ( $scope.search.firstName !== '' ) {
					$scope.filter.firstName = $scope.search.firstName;
				}
				if ( $scope.search.lastName !== '' ) {
					$scope.filter.lastName = $scope.search.lastName;
				}
				if ( $scope.search.type !== '' ) {
					$scope.filter.type = $scope.search.type;
				}
			},

			createPersonal: function() {

				$location.path( '/contact/personal' );
			},

			createBusiness: function() {

				$location.path( '/contact/business' );
			}
		};
	} )

	.controller( 'contactController', function( $scope, $routeParams, $location, contacts, metawidgetConfig ) {

		// Constructor
		switch ( $routeParams.contactId ) {
			case 'personal':
			case 'business':
				$scope.readOnly = false;
				$scope.current = {};
				$scope.current.title = "Mr";
				$scope.current.type = $routeParams.contactId;
				if ( $scope.current.type === 'personal' ) {
					$scope.dialogTitle = 'Personal Contact';
				} else {
					$scope.dialogTitle = 'Business Contact';
				}
				break;

			default:
				$scope.readOnly = true;
				contacts.then( function( result ) {

					var contactId = parseInt( $routeParams.contactId );

					for ( var loop = 0, length = result.data.length; loop < length; loop++ ) {
						if ( result.data[loop].id === contactId ) {
							// Return a copy of the entry, in case the user hits
							// cancel
							$scope.current = angular.fromJson( angular.toJson( result.data[loop] ) );
							$scope.dialogTitle = $scope.current.title + ' ' + $scope.current.firstName + ' ' + $scope.current.lastName + ' - ';

							if ( $scope.current.type === 'personal' ) {
								$scope.dialogTitle += 'Personal Contact';
							} else {
								$scope.dialogTitle += 'Business Contact';
							}
							break;
						}
					}
				} );
		}

		$scope.metawidgetConfig = metawidgetConfig;

		// CRUD operations

		$scope.crudActions = {

			edit: function() {

				$scope.readOnly = false;
			},

			save: function() {

				if ( $scope.current.firstname === undefined ) {
					alert( 'Firstname is required' );
					return;
				}

				if ( $scope.current.surname === undefined ) {
					alert( 'Surname is required' );
					return;
				}

				contacts.then( function( result ) {

					var loop, length;

					if ( $scope.current.id === undefined ) {

						// Save new

						var nextId = 0;
						for ( loop = 0, length = result.data.length; loop < length; loop++ ) {
							if ( result.data[loop].id > nextId ) {
								nextId = result.data[loop].id;
							}
						}
						$scope.current.id = nextId + 1;
						result.data.push( $scope.current );
					} else {

						// Update existing

						for ( loop = 0, length = result.data.length; loop < length; loop++ ) {
							if ( result.data[loop].id === $scope.current.id ) {
								result.data.splice( loop, 1, $scope.current );
								break;
							}
						}
					}
					$location.path( '' );
				} );
			},

			"delete": function() {

				contacts.then( function( result ) {

					for ( var loop = 0, length = result.data.length; loop < length; loop++ ) {
						if ( result.data[loop].id === $scope.current.id ) {
							result.data.splice( loop, 1 );
							break;
						}
					}
					$location.path( '' );
				} );
			},

			cancel: function() {

				$location.path( '' );
			}
		};

		// Communications table

		$scope.communication = {
			type: "",
			value: ""
		};

		$scope.addCommunication = function() {

			if ( $scope.communication.type === '' ) {
				alert( 'Communication type is required' );
				return;
			}

			if ( $scope.communication.value === '' ) {
				alert( 'Communication value is required' );
				return;
			}

			$scope.current.communications = $scope.current.communications || [];
			$scope.current.communications.push( angular.fromJson( angular.toJson( $scope.communication ) ) );
			$scope.communication.type = '';
			$scope.communication.value = '';
		};

		$scope.removeCommunication = function( index ) {

			$scope.current.communications.splice( index, 1 );
		};
	} );
} )();
