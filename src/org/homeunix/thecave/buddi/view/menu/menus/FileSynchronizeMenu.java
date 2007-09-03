/*
 * Created on Aug 7, 2007 by wyatt
 */
package org.homeunix.thecave.buddi.view.menu.menus;

import org.homeunix.thecave.buddi.i18n.keys.MenuKeys;
import org.homeunix.thecave.buddi.model.prefs.PrefsModel;
import org.homeunix.thecave.buddi.plugin.BuddiPluginFactory;
import org.homeunix.thecave.buddi.plugin.api.BuddiSynchronizePlugin;
import org.homeunix.thecave.buddi.view.menu.items.PluginSynchronizeEntry;
import org.homeunix.thecave.moss.swing.MossDocumentFrame;
import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.moss.swing.MossMenu;

public class FileSynchronizeMenu extends MossMenu {
	public static final long serialVersionUID = 0;
	
	public FileSynchronizeMenu(MossFrame frame) {
		super(frame, PrefsModel.getInstance().getTranslator().get(MenuKeys.MENU_FILE_SYNCHRONIZE));
	}
	
	@Override
	public void updateMenus() {
		this.removeAll();
		
		for (BuddiSynchronizePlugin plugin : BuddiPluginFactory.getSynchronizePlugins()) {
			if (getFrame() instanceof MossDocumentFrame)
				this.add(new PluginSynchronizeEntry((MossDocumentFrame) getFrame(), plugin));
		}
		
		this.setEnabled(getFrame() instanceof MossDocumentFrame && this.getComponentCount() > 0);
		
		super.updateMenus();
	}
}
