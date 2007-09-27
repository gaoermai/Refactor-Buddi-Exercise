/*
 * Created on Aug 6, 2007 by wyatt
 */
package org.homeunix.thecave.buddi.view.menu.items;

import java.awt.event.ActionEvent;
import java.util.Date;

import org.homeunix.thecave.buddi.i18n.BuddiKeys;
import org.homeunix.thecave.buddi.i18n.keys.MenuKeys;
import org.homeunix.thecave.buddi.model.BudgetCategory;
import org.homeunix.thecave.buddi.model.swing.MyBudgetTreeTableModel;
import org.homeunix.thecave.buddi.plugin.api.exception.InvalidValueException;
import org.homeunix.thecave.buddi.plugin.api.util.TextFormatter;
import org.homeunix.thecave.buddi.view.MainFrame;
import org.homeunix.thecave.moss.swing.MossMenuItem;
import org.homeunix.thecave.moss.util.Log;

public class EditCopyBudgetCategoryValuesForward extends MossMenuItem{
	public static final long serialVersionUID = 0;

	public EditCopyBudgetCategoryValuesForward(MainFrame frame) {
		super(frame, TextFormatter.getTranslation(MenuKeys.MENU_EDIT_COPY_VALUES_TO_PREVIOUS_BUDGET_PERIOD));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (BudgetCategory bc : ((MainFrame) getFrame()).getSelectedBudgetCategories()) {
			long amount = bc.getAmount(new Date());
			try {
				bc.setAmount(bc.getBudgetPeriodType().getBudgetPeriodOffset(new Date(), -1), amount);
			}
			catch (InvalidValueException ive){
				Log.error(ive);
			}
		}

		((MainFrame) getFrame()).fireStructureChanged();
		((MainFrame) getFrame()).updateContent();
	}
	
	@Override
	public void updateMenus() {
		super.updateMenus();
		
		MyBudgetTreeTableModel treeTableModel = ((MainFrame) getFrame()).getMyBudgetPanel().getTreeTableModel();
		this.setText(TextFormatter.getTranslation(MenuKeys.MENU_EDIT_COPY_VALUES_TO_NEXT_BUDGET_PERIOD) 
				+ " (" + treeTableModel.getColumnName(2) + " "
				+ TextFormatter.getTranslation(BuddiKeys.TO)
				+ " " + treeTableModel.getColumnName(1) + ")");
		
		this.setEnabled(((MainFrame) getFrame()).getSelectedBudgetCategories().size() > 0);
	}
}
