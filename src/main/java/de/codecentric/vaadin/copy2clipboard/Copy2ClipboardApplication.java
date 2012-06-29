package de.codecentric.vaadin.copy2clipboard;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.Application;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;

import de.codecentric.vaadin.copy2clipboard.Copy2ClipboardButton.ClipboardEvent;
import de.codecentric.vaadin.copy2clipboard.Copy2ClipboardButton.ClipboardListener;

/**
 * Test application for Copy2ClipboardButton.
 * 
 * @author henning.treu@codecentric.de
 */
public class Copy2ClipboardApplication extends Application {

    /**
     * Generated UID.
     */
    private static final long serialVersionUID = 1198903699329175622L;

    /**
     * The main window of the vaadin app.
     */
    private Window mainWindow;

    /**
     * (non-Javadoc)
     * 
     * @see com.vaadin.Application#init()
     */
    @Override
    public void init() {
	mainWindow = new Window();
	setMainWindow(mainWindow);

	// Add a Copy2ClipboardButton to the main page:

	Copy2ClipboardButton button = new Copy2ClipboardButton();
	button.setCaption("Copy");
	button.setClipboardText("copy this to the clipboard");
	button.addListener(new CopyListener("from page: copied to clipboard"));

	mainWindow.addComponent(button);

	// Add a Copy2ClipboardButton to a panel:

	Copy2ClipboardButton c2c = new Copy2ClipboardButton();
	c2c.setCaption("panelTest");
	c2c.setClipboardText("clipboard text from panel");
	c2c.addListener(new CopyListener("from panel: copied to clipboard"));

	Panel panel = new Panel();
	panel.addComponent(c2c);
	mainWindow.addComponent(panel);

	// add a Copy2ClipboardButton to a popup:

	Copy2ClipboardButton popupCopy = new Copy2ClipboardButton("copy", true);
	popupCopy.addListener(new CopyListener("from popup: copied to clipboard"));

	final PopupButton popup = new PopupButton("click me");
	popupCopy.setClipboardText("popup copy!!!");
	popup.addComponent(popupCopy);

	// make the popup stay visible:
	popupCopy.addListener(new ClipboardListener() {

	    @Override
	    public void copiedToClipboard(ClipboardEvent event) {
		popup.setPopupVisible(true);
	    }
	});

	mainWindow.addComponent(popup);
    }

    /**
     * A simple {@link ClipboardListener} implementation. It notifies the user of a successful copy
     * action.
     * 
     */
    private class CopyListener implements ClipboardListener {

	/**
	 * The notification.
	 */
	private String notification;

	/**
	 * Constructor.
	 * 
	 * @param notification
	 *            the notification.
	 */
	private CopyListener(String notification) {
	    this.notification = notification;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see de.codecentric.vaadin.copy2clipboard.Copy2ClipboardButton.ClipboardListener#copiedToClipboard(de.codecentric.vaadin.copy2clipboard.Copy2ClipboardButton.ClipboardEvent)
	 */
	@Override
	public void copiedToClipboard(ClipboardEvent event) {
	    mainWindow.showNotification(notification);
	}
    }
}