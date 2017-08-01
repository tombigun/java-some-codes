package com.tombigun.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_webcamjs implements EntryPoint {

    public void onModuleLoad() {

        WebCam webCam = new WebCam();
        webCam.initWebCam(500, 375);

        Image image = new Image("img");


        final Button openButton = new Button("open");
        final Button closeButton = new Button("close");
        final Button takeButton = new Button("takePhoto");

        openButton.addClickHandler(clickEvent -> webCam.play());
        closeButton.addClickHandler(clickEvent -> webCam.pause());
        takeButton.addClickHandler(clickEvent -> webCam.takephoto(data_uri -> image.setUrl(data_uri)));


        RootPanel.get("webcam").add(webCam);
        RootPanel.get("images").add(image);
        RootPanel.get("openButtonContainer").add(openButton);
        RootPanel.get("closeButtonContainer").add(closeButton);
        RootPanel.get("takeButtonContainer").add(takeButton);
    }
}
