package com.example.addressbook;

import com.example.addressbook.domain.Address;
import com.example.addressbook.domain.BusinessContact;
import com.example.addressbook.domain.PersonalContact;
import com.example.addressbook.domain.ref.CommunicationType;
import com.example.addressbook.domain.ref.PersonType;
import com.example.addressbook.service.AddressBookService;
import org.metawidget.inspectionresultprocessor.iface.DomInspectionResultProcessor;
import org.metawidget.inspectionresultprocessor.json.JsonTypeMappingProcessor;
import org.metawidget.inspectionresultprocessor.json.schema.JsonSchemaMappingProcessor;
import org.metawidget.inspectionresultprocessor.sort.ComesAfterInspectionResultProcessor;
import org.metawidget.inspector.annotation.MetawidgetAnnotationInspector;
import org.metawidget.inspector.composite.CompositeInspector;
import org.metawidget.inspector.composite.CompositeInspectorConfig;
import org.metawidget.inspector.propertytype.PropertyTypeInspector;
import org.metawidget.util.XmlUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.w3c.dom.Element;

@SpringBootApplication
public class AddessBookApp {
    @Autowired
    private AddressBookService addressBookService;

    public static void main(String[] args) {
        SpringApplication.run(AddessBookApp.class, args);
    }

    //Prepare JSON Schemas on application start
    @Bean
    InitializingBean init() {
        return () -> {
            CompositeInspector mInspector = new CompositeInspector(new CompositeInspectorConfig().setInspectors(
                    new MetawidgetAnnotationInspector(),
                    new PropertyTypeInspector()
            ));

            DomInspectionResultProcessor[] mInspectionResultProcessors = new DomInspectionResultProcessor[]{
                    new ComesAfterInspectionResultProcessor<Object>(),
            };

            //Prepare businessContact

            addressBookService.setPersonSchema(getJson(PersonalContact.class.getName(), mInspector, mInspectionResultProcessors));
            addressBookService.setBusinessSchema(getJson(BusinessContact.class.getName(), mInspector, mInspectionResultProcessors));
            addressBookService.setAddressSchema(getJson(Address.class.getName(), mInspector, mInspectionResultProcessors));
            addressBookService.setPersonTypeSchema(getJson(PersonType.class.getName(), mInspector, mInspectionResultProcessors));
            addressBookService.setCommunicationTypeSchema(getJson(CommunicationType.class.getName(), mInspector, mInspectionResultProcessors));
        };
    }

    private String getJson(String className, CompositeInspector mInspector, DomInspectionResultProcessor[] mInspectionResultProcessors) {
        Element inspectionResult = mInspector.inspectAsDom(null, className);
        for (DomInspectionResultProcessor<Element, Object> inspectionResultProcessor : mInspectionResultProcessors) {
            inspectionResult = inspectionResultProcessor.processInspectionResultAsDom(
                    inspectionResult, null, null, className, (String[]) null);
        }
        return XmlUtils.inspectionResultToJsonSchema(inspectionResult);
    }

}
