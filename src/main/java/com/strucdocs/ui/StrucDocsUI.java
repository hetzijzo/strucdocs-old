package com.strucdocs.ui;

import com.strucdocs.repository.BandRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class StrucDocsUI
        extends UI {

    private BandRepository bandRepository;

    @Autowired
    public StrucDocsUI(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();

        layout.addComponent(new TextField("Name"));
        layout.addComponent(new TextField("Instrument"));
        //layout.addComponent(new Button("Click Me!", event -> event.getButton().setCaption("You made click!")));
        setContent(layout);
    }
}