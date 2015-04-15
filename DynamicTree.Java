package usecase;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * This class creates the functionality for the tree inside the GUI.
 * 
 * @author Wesley Krug, Gabriel Steponovich,
 * Michael Brecker, Halston Raddatz
 * @version Winter 2015
 */
class DynamicTree extends JPanel {
  /**
  * Serializable.
  */
  private static final long serialVersionUID = 1L;
  
  /**
   * The root node.
   */
  private DefaultMutableTreeNode rootNode;
  
  /**
   * The tree model.
   */
  private DefaultTreeModel treeModel;
  
  /**
   * The tree.
   */
  private JTree tree;
  
  /**
   * The toolkit for the tree.
   */
  private Toolkit toolkit = Toolkit.getDefaultToolkit();

 
  private PopClickListener popUp;
  private JPopupMenu popMenu;

 
  
  /**
   * Constructor for the tree. It sets up what the tree will
   * look like in the GUI.
   */
  public DynamicTree(JPopupMenu popMenu) {
    super(new GridLayout(1, 0));

    rootNode = new DefaultMutableTreeNode("Root Node");
    treeModel = new DefaultTreeModel(rootNode);
    UIManager.put("Tree.rendererFillBackground", false);
    tree = new JTree(treeModel);
    tree.setRootVisible(false);
    tree.getSelectionModel().setSelectionMode(
        TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.setShowsRootHandles(true);
    tree.setBackground(Color.WHITE);
    this.popMenu = popMenu;
    popUp = new PopClickListener(this.popMenu);
    popUp.addListener(tree);
    JScrollPane scrollPane = new JScrollPane(tree);
    add(scrollPane);
    
  
    
  }
  
  public TreePath getPathForLocation(int x, int y) {
	  return tree.getPathForLocation(x, y);
  }
  
  public void setSelectionPath(TreePath path) {
	  tree.setSelectionPath(path);
  }
  /**
   * This finds which usecase selected in the tree.
   * 
   * @return the currently selected usecase.
   */
  public UseCase selectedUsecase() {
	  if (((DefaultMutableTreeNode) tree.getSelectionPath(
			  ).getLastPathComponent()).getUserObject() instanceof UseCase) {
		  return (UseCase) ((DefaultMutableTreeNode) tree.getSelectionPath(
				  ).getLastPathComponent()).getUserObject();
	  } else {
		  return null;
	  }
  }
  
  /**
   * This finds which term is selected in the tree.
   * 
   * @return the currently selected usecase.
   */
  public String selectedTerm() {
	  if (((DefaultMutableTreeNode) tree.getSelectionPath(
			  ).getLastPathComponent()).getUserObject() instanceof String) {
		return (String) ((DefaultMutableTreeNode) tree.getSelectionPath(
					  ).getLastPathComponent()).getUserObject();
	  } else {
		  return null;
	  }
	  
  }
  
  /** Remove all nodes except the root node. */
  public void clear() {
    rootNode.removeAllChildren();
    treeModel.reload();
  }

  /** Remove the currently selected node. */
  public void removeCurrentNode() {
    TreePath currentSelection = tree.getSelectionPath();
    if (currentSelection != null) {
      DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (
    		  currentSelection.getLastPathComponent());
      MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
      if (parent != null) {
        treeModel.removeNodeFromParent(currentNode);
        return;
      }
    }

    // Either there was no selection, or the root was selected.
    toolkit.beep();
  }

  /** Add child to the currently selected node. 
   * 
   * @param child is the node to add.
   * @return returns a node.
   */
  public DefaultMutableTreeNode addObject(final Object child) {
    DefaultMutableTreeNode parentNode = null;
    TreePath parentPath = tree.getSelectionPath();

    if (parentPath == null) {
      parentNode = rootNode;
    } else {
      parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
    }

    return addObject(parentNode, child, true);
  }

  /**
   * Add child to the currently selected node.
   * 
   * @param parent to add the child to.
   * @param child is the node to add.
   * @return returns a node.
   */
  public DefaultMutableTreeNode addObject(final DefaultMutableTreeNode parent,
      final Object child) {
      return addObject(parent, child, false);
  }
  
  /**
   * Add child to the currently selected node.
   * 
   * @param parent to add the child to.
   * @param child is the object to add.
   * @param shouldBeVisible boolean to show new child
   * @return returns a node.
   */
  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
      final Object child, final boolean shouldBeVisible) {
	  DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

	  if (parent == null) {
		  parent = rootNode;
	  }

	  //It is key to invoke this on TreeModel, and NOT DefaultMutableTreeNode
	  treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
	  // Make sure the user can see the lovely new node.
	  if (shouldBeVisible) {
		  tree.scrollPathToVisible(new TreePath(childNode.getPath()));
	  }
	  return childNode;
   }
  
  /**
   * Gets the selected item in the tree.
   * 
   * @return the selected item from the tree.
   */
  public DefaultMutableTreeNode getSelectedItem() {
	DefaultMutableTreeNode node;
    node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    System.out.println(node);
	return node;
  }

}
