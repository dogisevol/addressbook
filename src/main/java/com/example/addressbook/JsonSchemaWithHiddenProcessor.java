package com.example.addressbook;

import org.metawidget.inspectionresultprocessor.iface.DomInspectionResultProcessor;
import org.metawidget.inspectionresultprocessor.json.schema.JsonSchemaMappingProcessor;
import org.w3c.dom.Element;

public class JsonSchemaWithHiddenProcessor extends JsonSchemaMappingProcessor{

    @Override
    protected boolean shouldRemove(Element element) {
        return false;
    }
}