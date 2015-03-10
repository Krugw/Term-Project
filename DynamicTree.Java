import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

class DynamicTree extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
protected DefaultMutableTreeNode rootNode;
  protected DefaultTreeModel treeModel;
  protected JTree tree;
  private Toolkit toolkit = Toolkit.getDefaultToolkit();

  public DynamicTree() {
    super(new GridLayout(1, 0));

    rootNode = new DefaultMutableTreeNode("Root Node");
    treeModel = new DefaultTreeModel(rootNode);
    UIManager.put("Tree.rendererFillBackground", false);
    tree = new JTree(treeModel);
    tree.setEditable(true);
    tree.setRootVisible(false);
    tree.getSelectionModel().setSelectionMode(
        TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.setShowsRootHandles(true);
    tree.setBackground(Color.GRAY);
    JScrollPane scrollPane = new JScrollPane(tree);
    add(scrollPane);
  }
  public UseCase selectedUsecase(){
	  if(((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getUserObject() instanceof UseCase)
	  return (UseCase) ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getUserObject();
	  return null;
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
      DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
          .getLastPathComponent());
      MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
      if (parent != null) {
        treeModel.removeNodeFromParent(currentNode);
        return;
      }
    }

    // Either there was no selection, or the root was selected.
    toolkit.beep();
  }

  /** Add child to the currently selected node. */
  public DefaultMutableTreeNode addObject(Object child) {
    DefaultMutableTreeNode parentNode = null;
    TreePath parentPath = tree.getSelectionPath();

    if (parentPath == null) {
      parentNode = rootNode;
    } else {
      parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
    }

    return addObject(parentNode, child, true);
  }

  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
      Object child) {
    return addObject(parent, child, false);
  }
  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
      Object child, boolean shouldBeVisible) {
    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

    if (parent == null) {
      parent = rootNode;
    }

    // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
    // Make sure the user can see the lovely new node.
    if (shouldBeVisible) {
      tree.scrollPathToVisible(new TreePath(childNode.getPath()));
    }
    return childNode;
  }
public DefaultMutableTreeNode getSelectedItem(){
	DefaultMutableTreeNode node;
    node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	return node;
}
}
