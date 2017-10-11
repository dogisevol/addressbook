( function() {

	'use strict';

	describe( 'AddressBook App', function() {

		beforeEach( function() {

			browser().navigateTo( 'index.html' );
		} );

		it( 'should redirect index.html to root', function() {

			expect( browser().location().url() ).toBe( '' );
		} );

		it( 'should allow searching contacts', function() {

			expect( element( '#table-search tbody tr:eq(0)' ).attr( 'id' ) ).toBe( 'table-searchFirstName-row' );
			expect( element( '#table-search tbody tr:eq(0) td' ).attr( 'id' ) ).toBe( 'table-searchFirstName-cell' );

			expect( element( '#table-search' ).attr( 'class' ) ).toBe( 'table-form' );
			expect( element( '#table-search tbody tr:eq(0) th label' ).text() ).toBe( 'First Name:' );
			expect( element( '#table-search tbody tr:eq(0) th label' ).attr( 'for' ) ).toBe( 'searchFirstName' );
			expect( element( '#table-search tbody tr:eq(0) td input' ).attr( 'type' ) ).toBe( 'text' );
			expect( element( '#table-search tbody tr:eq(0) td input' ).attr( 'id' ) ).toBe( 'searchFirstName' );
			expect( element( '#table-search tbody tr:eq(1) th label' ).attr( 'for' ) ).toBe( 'searchLastName' );
			expect( element( '#table-search tbody tr:eq(1) td input' ).attr( 'type' ) ).toBe( 'text' );
			expect( element( '#table-search tbody tr:eq(1) td input' ).attr( 'id' ) ).toBe( 'searchLastName' );
			expect( element( '#table-search tbody tr:eq(2) th' ).text() ).toBe( 'Type:' );
			expect( element( '#table-search tbody tr:eq(2) th label' ).attr( 'for' ) ).toBe( 'searchType' );
			expect( element( '#table-search tbody tr:eq(2) td select' ).attr( 'id' ) ).toBe( 'searchType' );
			expect( element( '#table-search tfoot tr td' ).attr( 'colspan' ) ).toBe( '3' );
			expect( element( '#table-search tfoot tr td' ).attr( 'class' ) ).toBe( 'buttons' );
			expect( element( '#table-search tfoot tr td input:eq(0)' ).attr( 'value' ) ).toBe( 'Search' );
			expect( element( '#table-search tfoot tr td input:eq(1)' ).attr( 'value' ) ).toBe( 'Create Personal' );
			expect( element( '#table-search tfoot tr td input:eq(2)' ).attr( 'value' ) ).toBe( 'Create Business' );
			expect( element( '#table-search tfoot tr td input' ).count() ).toBe( 3 );

			select( 'search.type' ).option( '' );
			element( '#searchActionsSearch' ).click();
		} );

		it( 'should allow editing contacts', function() {
            element( 'a:eq(1)' ).click();
			expect( element( '#table-current tbody tr:eq(1) th' ).text() ).toBe( 'Dob:' );
			expect( element( '#table-current tbody tr:eq(1) th label' ).attr( 'for' ) ).toBe( 'currentDob' );
			element( '#crudActionsEdit' ).click();
			element( '#crudActionsCancel' ).click();
		} );
	} );
} )();