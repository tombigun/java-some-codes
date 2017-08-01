/**
 * 
 */
package com.tombigun.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * webcamjs控件
 * 
 */
public class WebCam extends Composite {

	private final SimplePanel simplePanel = new SimplePanel();
	
	private final String frameName;
	public WebCam() {
		initWidget(simplePanel);
		
		frameName = System.currentTimeMillis() + "";
	}
	
	public void initWebCam(double width, double height) {
		Frame frame = new Frame(GWT.getModuleBaseURL() + "../html/webcam.html?t=" + frameName);
		frame.getElement().getStyle().setBorderWidth(0, Unit.PX);
		frame.getElement().setId(frameName);
		frame.getElement().setPropertyString("name", frameName);
		frame.getElement().setPropertyString("overflow", "hidden");
		frame.getElement().getStyle().setWidth(width, Unit.PX);
		frame.getElement().getStyle().setHeight(height, Unit.PX);

		simplePanel.setSize(width+"px",height+"px");
		simplePanel.setWidget(frame);
	}
	
	public final native String takephoto(WebcamCallBack cb) /*-{
		try {
			var frameName = this.@com.tombigun.gwt.client.WebCam::frameName;
			
			var resp = function(data_uri) {
				cb.@com.tombigun.gwt.client.WebCam.WebcamCallBack::response(Ljava/lang/String;)(data_uri);
			}
			
			$wnd.frames[frameName].takephoto(resp);
		} catch(e){}
		
		return "";
	}-*/;
	
	public final native String play() /*-{
		try {
			var frameName = this.@com.tombigun.gwt.client.WebCam::frameName;
			return $wnd.frames[frameName].play();
		} catch(e){}
		
		return "";
	}-*/;
	
	public final native String pause() /*-{
		try {
			var frameName = this.@com.tombigun.gwt.client.WebCam::frameName;
			return $wnd.frames[frameName].pause();
		} catch(e){}
		
		return "";
	}-*/;
	
	public interface WebcamCallBack{
		public void response(String data_uri);
	}
}
