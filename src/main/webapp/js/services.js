( function() {

	'use strict';

	angular.module( 'services', [] )

	.factory( 'contacts', function( $http ) {
		return $http.get( 'contact' );
	} )

	.factory( 'metawidgetConfig', function( $http ) {
		var _tableLayout = new metawidget.layout.HeadingTagLayoutDecorator( {
			delegate: new metawidget.layout.TableLayout( {
				tableStyleClass: "table-form",
				columnStyleClasses: [ "table-label-column", "table-component-column", "table-required-column" ],
				footerStyleClass: "buttons"
			} )
		} );

		return {
			search: {
                inspector: new metawidget.inspector.CompositeInspector( [ new metawidget.inspector.PropertyTypeInspector()]),
                inspectionResultProcessors: [ function( inspectionResult, mw ) {
                    $http.get('schema/contactType' ).then( function( result ) {
                        metawidget.util.combineInspectionResults( inspectionResult, {properties: {type: result.data}});
                        mw.buildWidgets( inspectionResult );
                    });
				} ],
				layout: _tableLayout
			},
			form: {
				inspectionResultProcessors: [ function( inspectionResult, mw, toInspect, type, names ) {
					if ( names === undefined && toInspect !== undefined && toInspect.type !== undefined ) {
						$http.get('schema/' + toInspect.type.toLowerCase() ).then( function( result ) {
							metawidget.util.combineInspectionResults( inspectionResult, result.data );
							$http.get('schema/address' ).then( function( result ) {
							    metawidget.util.combineInspectionResults( inspectionResult, result.data );
                                mw.buildWidgets( inspectionResult );
							});
						} );
					} else {
						return inspectionResult;
					}
				} ],
				layout: _tableLayout
			},
			buttons: {
				inspector: new metawidget.inspector.CompositeInspector( [ new metawidget.inspector.PropertyTypeInspector(), function() {

					return {
						properties: {
							edit: {
								hidden: "{{!readOnly}}"
							},
							save: {
								hidden: "{{readOnly}}"
							},
							"delete": {
								hidden: "{{readOnly || !current.id}}"
							}
						}
					};
				} ] ),
				layout: new metawidget.layout.SimpleLayout()
			},

			simple: {
                inspectionResultProcessors: [ function( inspectionResult, mw, toInspect, type, names ) {
                    if ( type === 'communication' && names.length === 1 && names[0] === 'type' ) {
                        $http.get('schema/contactType' + toInspect.type ).then( function( result ) {
                            metawidget.util.combineInspectionResults( inspectionResult, result.data );
                            mw.buildWidgets( inspectionResult );
                        } );
                    } else {
                        return inspectionResult;
                    }
                } ],
				layout: new metawidget.layout.SimpleLayout()
			}
		};
	} );
} )();