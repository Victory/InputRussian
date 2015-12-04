package org.dfhu.inputrussian;

import java.io.File;

import org.dfhu.inputrussian.rudb.PhraseDb;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;

public class ExportView extends VerticalLayout implements View {

    private Navigator navigator;
    Button homeNav;
    Button download;

    public ExportView(Navigator navigator) {
        this.navigator = navigator;
        homeNav = new Button("Home");
        homeNav.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo("");
            }
        });
        addComponent(homeNav);

        RichTextArea ta = new RichTextArea();
        ta.setCaption("Edit before you download");
        addComponent(ta);

        download = new Button("Download");
        addComponent(download);
        Resource res = new FileResource(new File(PhraseDb.getDbPath()));
        FileDownloader downloader = new FileDownloader(res);
        downloader.extend(download);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub
    }

}
