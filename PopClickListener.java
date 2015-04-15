package usecase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTree;

public class PopClickListener extends MouseAdapter{
	private JPopupMenu popMenu;
	
	public PopClickListener(JPopupMenu popMenu) {
		this.popMenu = popMenu;
	}
	
    public void mousePressed(MouseEvent me){
        if (me.isPopupTrigger())
            doPop(me);
    }

    public void mouseReleased(MouseEvent me){
        if (me.isPopupTrigger())
            doPop(me);
    }

    private void doPop(MouseEvent me){
        
        popMenu.show(me.getComponent(), me.getX(), me.getY());
    }
    
    public void addListener(JTree tree) {
    	tree.addMouseListener(new PopClickListener(popMenu));
    }

    

}
