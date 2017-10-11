( function() {

	'use strict';

	/* App Module */

	angular.module( 'addressBook', [ 'ngRoute', 'controllers', 'services', 'metawidget' ] )

	.config( [ '$routeProvider', function( $routeProvider ) {

		$routeProvider.when( '/contact/:contactId', {
			templateUrl: 'partials/contact-detail.html',
			controller: 'contactController'
		} ).when( '', {
			templateUrl: 'partials/contact-none.html'
		} ).otherwise( {
			redirectTo: ''
		} );
	} ] );
} )();